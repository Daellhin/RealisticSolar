package com.daellhin.realisticsolar.blocks.arcfurnace;

import static com.daellhin.realisticsolar.blocks.ModBlocks.ARCFURNACE_TILE;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.recipe.CustomRecipe;
import com.daellhin.realisticsolar.recipe.CustomRecipeRegistry;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class ArcFurnaceTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

    public static final int INPUT_SLOTS = 3;
    public static final int OUTPUT_SLOTS = 3;
    public static final int SIZE = INPUT_SLOTS + OUTPUT_SLOTS;
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    // ticks still remaining before completion
    private int progress;
    private boolean powered;

    public ArcFurnaceTile() {
	super(ARCFURNACE_TILE);
    }

    @Override
    public void tick() {
	if (!world.isRemote) {
	    energy.ifPresent(energy -> {
		AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
		if (capacity.get() < Config.ARCFURNACE_USAGE.get()) {
		    powered = false;
		    return;
		}
		if (progress <= 0) {
		    powered = false;
		    startSmelt();
		}
		if (progress > 0) {
		    powered = true;
		    capacity.addAndGet(10);
		    energy.extractEnergy(Config.ARCFURNACE_USAGE.get(), false);
		    progress--;
		    if (progress <= 0) {
			attemptSmelt();
		    }
		    markDirty();
		}
	    });
	    BlockState blockState = world.getBlockState(pos);
	    if (blockState.get(BlockStateProperties.POWERED) != powered) {
		world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, powered), 3);
	    }
	}
    }

    private boolean insertOutput(ItemStack output, boolean simulate) {
	for (int i = 0; i < OUTPUT_SLOTS; i++) {
	    ItemStack remaining = outputHandler.insertItem(i, output, simulate);
	    if (remaining.isEmpty()) {
		return true;
	    }
	}
	return false;
    }

    private void startSmelt() {
	ItemStack result = getResult();
	if (!result.isEmpty()) {
	    if (insertOutput(result.copy(), true)) {
		progress = Config.ARCFURNACE_TICKS.get();
		markDirty();
		return;
	    }
	}
    }

    private void attemptSmelt() {
	ItemStack result = getResult();
	if (!result.isEmpty()) {
	    // This copy is very important!(
	    if (insertOutput(result.copy(), false)) {
		inputHandler.extractItem(0, 1, false);
		inputHandler.extractItem(1, 1, false);
		inputHandler.extractItem(2, 1, false);
	    }
	}
    }

    private ItemStack getResult() {
	// @todo Make a cache for this! Both our own CustomRecipeRegistry.getRecipe()
	// and
	// getSmeltingResult() loop over all recipes. This is very slow!
	CustomRecipe recipe = CustomRecipeRegistry.getRecipe(new ItemStack[] { inputHandler.getStackInSlot(0), inputHandler.getStackInSlot(1), inputHandler.getStackInSlot(2) });
	if (recipe != null) {
	    return recipe.getOutput(0);
	}
	// @todo 1.13
	// return FurnaceRecipes.instance().getSmeltingResult(stackInSlot);
	return ItemStack.EMPTY;
    }

    private ItemStackHandler inputHandler = new ItemStackHandler(INPUT_SLOTS) {

	// TODO make ouput slot only acept output
	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
	    return true;
	}

	@Override
	protected void onContentsChanged(int slot) {
	    markDirty();
	}
    };
    private ItemStackHandler outputHandler = new ItemStackHandler(OUTPUT_SLOTS) {

	@Override
	protected void onContentsChanged(int slot) {
	    markDirty();
	}
    };
    private CombinedInvWrapper combinedHandler = new CombinedInvWrapper(inputHandler, outputHandler);

    @SuppressWarnings("unchecked")
    @Override
    public void read(CompoundNBT tag) {
	inputHandler.deserializeNBT((CompoundNBT) tag.get("itemsIn"));
	outputHandler.deserializeNBT((CompoundNBT) tag.get("itemsOut"));
	CompoundNBT energyTag = tag.getCompound("energy");
	energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
	progress = tag.getInt("progress");
	super.read(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompoundNBT write(CompoundNBT tag) {
	tag.put("itemsIn", inputHandler.serializeNBT());
	tag.put("itemsOut", outputHandler.serializeNBT());
	energy.ifPresent(h -> {
	    CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
	    tag.put("energy", compound);
	});
	tag.putInt("progress", progress);
	return super.write(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
	if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	    if (side == null) {
		return LazyOptional.of(() -> (T) combinedHandler);
	    } else if (side == Direction.UP) {
		return LazyOptional.of(() -> (T) inputHandler);
	    } else {
		return LazyOptional.of(() -> (T) outputHandler);
	    }
	}
	if (cap == CapabilityEnergy.ENERGY) {
	    return energy.cast();
	}
	return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
	return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
	return new ArcFurnaceContainer(i, world, pos, playerInventory, playerEntity);
    }

    private IEnergyStorage createEnergy() {
	return new CustomEnergyStorage(Config.ARCFURNACE_MAXPOWER.get(), Config.ARCFURNACE_USAGE.get());
    }

    // TODO chage default state of arrow now a = 1 when idle
    public double fractionOfTicksComplete() {
	int reverseProgress = Config.ARCFURNACE_TICKS.get() - progress;
	double a = reverseProgress / (double) Config.ARCFURNACE_TICKS.get();
	a = MathHelper.clamp(a, 0.0, 1.0);
	// System.out.println(a);
	return a;
    }
    
    // TODO chage default state of arrow now a = 1 when idle
    public double fractionOfEnergy() {
	double a = getEnergy() / (double) Config.ARCFURNACE_MAXPOWER.get();
	a = MathHelper.clamp(a, 0.0, 1.0);
	// System.out.println(a);
	return a;
    }


    public int getProgress() {
	return progress;
    }

    public void setProgress(int progress) {
	this.progress = progress;
    }

    public int getEnergy() {
	return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    public void setEnergy(int energy) {
	getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage) h).setEnergy(energy));
    }
}
