package com.daellhin.realisticsolar.blocks.coalgenerator;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.PlayerInventoryContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CoalGeneratorContainer extends PlayerInventoryContainer {

	private CoalGeneratorTile tileEntity;
	private PlayerEntity playerEntity;
	private IItemHandler playerInventory;

	public CoalGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
		super(ModBlocks.COALGENERATOR_CONTAINER.get(), windowId);
		tileEntity = (CoalGeneratorTile) world.getTileEntity(pos);
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(playerInventory);
		layoutPlayerInventorySlots(this.playerInventory, 8, 84);
		layoutMachineInventorySlots();
		syncEnergy();
		syncProgress();
	}

	private void layoutMachineInventorySlots() {
		tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.ifPresent(h -> {
					addSlot(new SlotItemHandler(h, 0, 80, 20));
				});
	}

	private void syncEnergy() {
		trackInt(new IntReferenceHolder() {

			@Override
			public int get() {
				return tileEntity.getEnergy();
			}

			@Override
			public void set(int energy) {
				tileEntity.setEnergy(energy);
			}
		});
	}

	private void syncProgress() {
		trackInt(new IntReferenceHolder() {

			@Override
			public int get() {
				return tileEntity.getProgress();
			}

			@Override
			public void set(int progress) {
				tileEntity.setProgress(progress);
			}
		});
	}

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack stack = slot.getStack();
			itemstack = stack.copy();
			if (index == 0) {
				if (!this.mergeItemStack(stack, 1, 37, true)) {
					return ItemStack.EMPTY;
				}
				slot.onSlotChange(stack, itemstack);
			} else {
				if (stack.getItem() == Items.COAL || stack.getItem() == Items.COAL_BLOCK) {
					if (!this.mergeItemStack(stack, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index < 28) {
					if (!this.mergeItemStack(stack, 28, 37, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
					return ItemStack.EMPTY;
				}
			}
			if (stack.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
			if (stack.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}
			slot.onTake(playerIn, stack);
		}
		return itemstack;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.COALGENERATOR_BLOCK
				.get());
	}

	public CoalGeneratorTile getTileEntity() {
		return tileEntity;
	}
}