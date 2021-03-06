package com.daellhin.realisticsolar.blocks.arcfurnace.tiles;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;

import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceContainer;
import com.daellhin.realisticsolar.recipe.CustomRecipe;
import com.daellhin.realisticsolar.recipe.CustomRecipeRegistry;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;
import com.daellhin.realisticsolar.tools.OutputStackHandler;
import com.daellhin.realisticsolar.tools.enums.MultiblockPortType;

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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

public class ArcFurnaceControllerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	public static final int INPUT_SLOTS = 3;
	public static final int OUTPUT_SLOTS = 1;

	private CustomRecipe currentRecipe;
	private int progress;

	private ItemStackHandler inputHandler = createInputHandler();
	private ItemStackHandler outputHandler = createOutputHandler();
	private ItemStackHandler outputSlotWrapper = new OutputStackHandler(outputHandler);
	private CustomEnergyStorage energyStorage = createEnergy();

	private LazyOptional<IItemHandler> inputSlotHolder = LazyOptional.of(() -> inputHandler);
	private final LazyOptional<IItemHandler> outputSlotWrapperHolder = LazyOptional.of(() -> outputSlotWrapper);
	private LazyOptional<IItemHandler> combinedSlotHolder = LazyOptional.of(() -> new CombinedInvWrapper(inputHandler, outputSlotWrapper));
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);;

	public ArcFurnaceControllerTile() {
		super(ModBlocks.ARCFURNACE_CONTROLLER_TILE.get());
	}

	@Override
	public void remove() {
		super.remove();
		inputSlotHolder.invalidate();
		combinedSlotHolder.invalidate();
		outputSlotWrapperHolder.invalidate();
		energy.invalidate();
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			energy.ifPresent(energy -> {
				if (!isInputEmpty()) {
					AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
					if (capacity.get() < Config.ARCFURNACE_USAGE.get()) {
						return;
					}
					if (progress <= 0) {
						currentRecipe = getRecipe();
						if (currentRecipe != null) {
							startProcess(currentRecipe);
						}
					} else {
						energy.extractEnergy(Config.ARCFURNACE_USAGE.get(), false);
						if (progress - 1 <= 0) {
							if (currentRecipe == null) {
								currentRecipe = getRecipe();
							}
							if (canProcess(currentRecipe)) {
								doProcess(currentRecipe);
								progress = 0;
								markDirty();
							}
						} else {
							progress--;
							markDirty();
						}
					}
				} else {
					progress = 0;
				}
			});

			BlockState blockState = world.getBlockState(pos);
			if (blockState.get(BlockStateProperties.POWERED) != progress > 0) {
				world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, progress > 0), 3);
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

	/**
	 * returns false when all slots are not empty
	 * 
	 * @return
	 */
	private boolean isInputEmpty() {
		for (int i = 0; i < INPUT_SLOTS; i++) {
			if (inputHandler.getStackInSlot(i)
					.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private void startProcess(CustomRecipe recipe) {
		ItemStack output = recipe.getOutput(0);
		if (insertOutput(output.copy(), true)) {
			progress = Config.ARCFURNACE_TICKS.get();
			markDirty();
			return;
		}
	}

	private boolean canProcess(CustomRecipe recipe) {
		ItemStack output = recipe.getOutput(0);
		return insertOutput(output.copy(), true);
	}

	private void doProcess(CustomRecipe recipe) {
		ItemStack output = recipe.getOutput(0);
		insertOutput(output.copy(), false);
		inputHandler.extractItem(0, 1, false);
		inputHandler.extractItem(1, 1, false);
		inputHandler.extractItem(2, 1, false);
	}

	private CustomRecipe getRecipe() {
		return CustomRecipeRegistry
				.getRecipe(new ItemStack[] { inputHandler.getStackInSlot(0), inputHandler.getStackInSlot(1), inputHandler.getStackInSlot(2) });
	}

	// Something might be wrong here, null pointers on readNBT
	private ItemStackHandler createInputHandler() {
		return new ItemStackHandler(INPUT_SLOTS) {

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				return CustomRecipeRegistry.isItemValid(slot, stack);
			}

			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}

		};
	}

	private ItemStackHandler createOutputHandler() {
		return new ItemStackHandler(OUTPUT_SLOTS) {

			@Override
			protected void onContentsChanged(int slot) {
				markDirty();
			}

		};
	}

	public CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(Config.ARCFURNACE_MAXPOWER.get(), Config.ARCFURNACE_RECEIVE.get()) {

			@Override
			protected void onEnergyChanged() {
				markDirty();
			}

		};
	}

	@Override
	public void read(CompoundNBT tag) {
		inputHandler.deserializeNBT((CompoundNBT) tag.get("itemsIn"));
		outputHandler.deserializeNBT((CompoundNBT) tag.get("itemsOut"));
		energyStorage.deserializeNBT(tag.getCompound("energy"));
		progress = tag.getInt("progress");
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("itemsIn", inputHandler.serializeNBT());
		tag.put("itemsOut", outputHandler.serializeNBT());
		tag.put("energy", energyStorage.serializeNBT());
		tag.putInt("progress", progress);
		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (side == null) {
				return combinedSlotHolder.cast();
			} else if (side == Direction.UP) {
				return inputSlotHolder.cast();
			} else {
				return outputSlotWrapperHolder.cast();
			}
		}
		if (capability == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(capability, side);
	}

	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side, MultiblockPortType type) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (type == MultiblockPortType.ITEM_INPUT) {
				return inputSlotHolder.cast();
			} else if (type == MultiblockPortType.ITEM_OUTPUT) {
				return outputSlotWrapperHolder.cast();
			}
		}
		if (capability == CapabilityEnergy.ENERGY) {
			if (type == MultiblockPortType.ENERGY_INPUT) {
				return energy.cast();
			}
		}
		return super.getCapability(capability, side);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName()
				.getPath());
	}

	@Override
	public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new ArcFurnaceContainer(windowID, world, pos, playerInventory, playerEntity);
	}

	// progress
	public int getProgress() {
		return progress;
	}

	public void setProgress(int value) {
		this.progress = value;
	}

	public double fractionOfProgress() {
		if (progress == 0) {
			return 0;
		}
		int reverseProgress = Config.ARCFURNACE_TICKS.get() - progress;
		return reverseProgress / (double) Config.ARCFURNACE_TICKS.get();
	}

	// energy
	public int getEnergy() {
		return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored)
				.orElse(0);
	}

	public void setEnergy(int energy) {
		getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage) h).setEnergy(energy));
	}

	public double getFractionOfEnergy() {
		return getEnergy() / (double) Config.ARCFURNACE_MAXPOWER.get();
	}

}