package com.daellhin.realisticsolar.blocks.arcfurnace;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurnace.tiles.ArcFurnaceControllerTile;
import com.daellhin.realisticsolar.blocks.base.PlayerInventoryContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class ArcFurnaceContainer extends PlayerInventoryContainer {

	private ArcFurnaceControllerTile tileEntity;
	private PlayerEntity playerEntity;
	private IItemHandler playerInventory;
	protected final int inventorySize;

	public ArcFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		super(ModBlocks.ARCFURNACE_CONTAINER.get(), windowId);
		this.tileEntity = (ArcFurnaceControllerTile) world.getTileEntity(pos);
		this.playerEntity = playerEntity;
		this.playerInventory = new InvWrapper(playerInventory);
		this.inventorySize = ArcFurnaceControllerTile.INPUT_SLOTS;

		layoutPlayerInventorySlots(this.playerInventory, 8, 99);
		layoutMachineInventorySlots();
		syncEnergy();
		syncProgress();
	}

	private void layoutMachineInventorySlots() {
		this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
				.ifPresent(itemHandler -> {
					// Input
					addSlot(new SlotItemHandler(itemHandler, 0, 24, 19));
					addSlot(new SlotItemHandler(itemHandler, 1, 24, 43));
					addSlot(new SlotItemHandler(itemHandler, 2, 24, 66));
					// Output
					addSlot(new SlotItemHandler(itemHandler, 3, 134, 43));
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

	// Doesn't work yet
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack transferStack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			// Stack already in slot
			ItemStack currentHeldStack = slot.getStack();
			transferStack = currentHeldStack.copy();

			if (index < inventorySize) {
				if (!this.mergeItemStack(currentHeldStack, inventorySize, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(currentHeldStack, 0, inventorySize, false)) {
				return ItemStack.EMPTY;
			}

			if (currentHeldStack.getCount() == 0) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return transferStack;
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable
				.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
	}

	public ArcFurnaceControllerTile getTileEntity() {
		return tileEntity;
	}
}