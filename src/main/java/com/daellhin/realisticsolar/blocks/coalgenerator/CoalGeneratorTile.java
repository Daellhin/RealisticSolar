package com.daellhin.realisticsolar.blocks.coalgenerator;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;

import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.blocks.ModBlocks;
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

public class CoalGeneratorTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
	public static final int INPUT_SLOTS = 3;
	private Item[] validItems = { Items.COAL, Items.CHARCOAL, Items.COAL_BLOCK };
	private int progress;

	private ItemStackHandler inputHandler = createInputHandler();
	private CustomEnergyStorage energyStorage = createEnergy();

	private LazyOptional<IItemHandler> inputSlotHolder = LazyOptional.of(() -> inputHandler);
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	public CoalGeneratorTile() {
		super(ModBlocks.COALGENERATOR_TILE.get());
	}

	@Override
	public void remove() {
		super.remove();
		inputSlotHolder.invalidate();
		energy.invalidate();
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (progress > 0) {
				energyStorage.addEnergy(Config.COALGENERATOR_GENERATE.get());
				progress--;
				markDirty();
			} else {
				if (!energyStorage.isFull()) {
					int index = findValidItem(inputHandler.getStackInSlot(0)
							.getItem());
					if (index != -1) {
						if (index < 2) {
							progress = 100;
						} else {
							progress = 1000;
						}
						inputHandler.extractItem(0, 1, false);
						markDirty();
					}
				}
			}

			BlockState blockState = world.getBlockState(pos);
			if (blockState.get(BlockStateProperties.POWERED) != progress > 0) {
				world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, progress > 0), 3);
			}
			sendOutPower(energy, Config.COALGENERATOR_SEND.get());
		}
	}

	private int findValidItem(Item inputItem) {
		for (int i = 0; i < validItems.length; i++) {
			if (inputItem == validItems[i]) {
				return i;
			}
		}
		return -1;
	}

	private ItemStackHandler createInputHandler() {
		return new ItemStackHandler(INPUT_SLOTS) {

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

	}

	@Override
	public void read(CompoundNBT tag) {
		inputHandler.deserializeNBT((CompoundNBT) tag.get("itemsIn"));
		energyStorage.deserializeNBT(tag.getCompound("energy"));
		progress = tag.getInt("progress");
		super.read(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag.put("itemsIn", inputHandler.serializeNBT());
		tag.put("energy", energyStorage.serializeNBT());
		tag.putInt("progress", progress);
		return super.write(tag);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return inputSlotHolder.cast();
		}
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}

	public CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(Config.ARCFURNACE_MAXPOWER.get(), Config.ARCFURNACE_RECEIVE.get()) {

			@Override
			protected void onEnergyChanged() {
				markDirty();
			}

		};
	}

	public void sendOutPower(LazyOptional<IEnergyStorage> energyLO, int send) {
		AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
		if (capacity.get() > 0) {
			for (Direction direction : Direction.values()) {
				TileEntity te = world.getTileEntity(pos.offset(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction)
							.map(handler -> {
								if (handler.canReceive()) {
									int received = handler.receiveEnergy(Math.max(capacity.get(), send), false);
									capacity.addAndGet(-received);
									energyStorage.consumeEnergy(received);
									markDirty();
									return capacity.get() > 0;
								} else {
									return true;
								}
							})
							.orElse(true);
					if (!doContinue) {
						return;
					}
				}
			}
		}

	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName()
				.getPath());
	}

	@Override
	public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new CoalGeneratorContainer(i, world, pos, playerInventory, playerEntity);
	}

	// progress
	public int getProgress() {
		return progress;
	}

	public void setProgress(int value) {
		this.progress = value;
	}

	// TODO correct flame icon
	public double fractionOfProgress() {
		if (progress == 0) {
			return 0;
		}
		return progress / (double) 100;
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
