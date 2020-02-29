package com.daellhin.realisticsolar.blocks.coalgenerator;

import static com.daellhin.realisticsolar.blocks.ModBlocks.COALGENERATOR_TILE;
import javax.annotation.Nonnull;
import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.blocks.base.GeneratorTile;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CoalGeneratorTile extends GeneratorTile implements ITickableTileEntity, INamedContainerProvider {

    public static final int INPUT_SLOTS = 3;
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    private Item[] validItems = { Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK };
    private boolean powered;
    private int progress;

    public CoalGeneratorTile() {
	super(COALGENERATOR_TILE);
    }

    @Override
    public void tick() {
	if (!world.isRemote) {
	    energy.ifPresent(energy -> {
		if (progress > 0) {
		    ((CustomEnergyStorage) energy).addEnergy(100);
		    progress--;
		    markDirty();
		}
		else {
		    int index = findValidItem(inputHandler.getStackInSlot(0).getItem());
		    if (index != -1) {
			if (index > 2) {
				progress = 100;
			}
			else {
				progress = 1000;
			}
			inputHandler.extractItem(0, 1, false);
			powered = true;
			markDirty();
		    }
		    else {
			powered = false;
			
		    }
		}
	    });
	    BlockState blockState = world.getBlockState(pos);
	    if (blockState.get(BlockStateProperties.POWERED) != powered) {
		world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, powered), 3);
	    }
	    sendOutPower(energy, Config.COALGENERATOR_SEND.get());
	}
    }

    private int findValidItem(Item inputItem) {
	int i = 0;
	for (Item validItem : validItems) {
	    if (inputItem == validItem) {
		return i;
	    }
	    i++;
	}
	return -1;
    }

    private ItemStackHandler inputHandler = new ItemStackHandler(INPUT_SLOTS) {

	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
	    for (Item item : validItems) {
		if (stack.getItem() == item) {
		    return true;
		}
	    }
	    return false;
	}

	@Override
	protected void onContentsChanged(int slot) {
	    markDirty();
	}
    };

    @SuppressWarnings("unchecked")
    @Override
    public void read(CompoundNBT tag) {
	inputHandler.deserializeNBT((CompoundNBT) tag.get("itemsIn"));
	CompoundNBT energyTag = tag.getCompound("energy");
	energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
	progress = tag.getInt("progress");
	super.read(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompoundNBT write(CompoundNBT tag) {
	tag.put("itemsIn", inputHandler.serializeNBT());
	energy.ifPresent(h -> {
	    CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
	    tag.put("energy", compound);
	});
	tag.putInt("progress", progress);
	return super.write(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
	if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	    return LazyOptional.of(() -> (T) inputHandler);
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
	return new CoalGeneratorContainer(i, world, pos, playerInventory, playerEntity);
    }

    private IEnergyStorage createEnergy() {
	return new CustomEnergyStorage(Config.COALGENERATOR_MAXPOWER.get(), Config.COALGENERATOR_SEND.get());
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

    public double fractionOfEnergy() {
	return getEnergy() / (double) Config.COALGENERATOR_MAXPOWER.get();
    }

    public double fractionOfTicksComplete() {
	if (progress == 0) {
	    return 0;
	}
	return progress / (double) 100;
    }
}
