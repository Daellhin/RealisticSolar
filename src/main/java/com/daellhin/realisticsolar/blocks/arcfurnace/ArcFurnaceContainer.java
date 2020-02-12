package com.daellhin.realisticsolar.blocks.arcfurnace;

import static com.daellhin.realisticsolar.blocks.ModBlocks.ARCFURNACE_CONTAINER;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.BaseInventoryContainer;
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

public class ArcFurnaceContainer extends BaseInventoryContainer {

    private ArcFurnaceTile tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public ArcFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
	super(ARCFURNACE_CONTAINER, windowId);
	this.tileEntity = (ArcFurnaceTile) world.getTileEntity(pos);
	this.playerEntity = playerEntity;
	this.playerInventory = new InvWrapper(playerInventory);
	layoutMachineInventorySlots();
	layoutPlayerInventorySlots(this.playerInventory, 8, 99);
	// sync energy
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
	// sync progress
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

    private void layoutMachineInventorySlots() {
	this.tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(itemHandler -> {
	    // Input
	    addSlot(new SlotItemHandler(itemHandler, 0, 24, 19));
	    addSlot(new SlotItemHandler(itemHandler, 1, 24, 43));
	    addSlot(new SlotItemHandler(itemHandler, 2, 24, 66));
	    // Output
	    addSlot(new SlotItemHandler(itemHandler, 3, 134, 43));
	});
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	ItemStack itemstack = ItemStack.EMPTY;
	Slot slot = this.inventorySlots.get(index);
	if (slot != null && slot.getHasStack()) {
	    ItemStack stack = slot.getStack();
	    itemstack = stack.copy();
	    if (index < ArcFurnaceTile.SIZE) {
		if (!this.mergeItemStack(stack, ArcFurnaceTile.SIZE, this.inventorySlots.size(), true)) {
		    return ItemStack.EMPTY;
		}
	    } else if (!this.mergeItemStack(stack, 0, ArcFurnaceTile.SIZE, false)) {
		return ItemStack.EMPTY;
	    }
	    if (stack.isEmpty()) {
		slot.putStack(ItemStack.EMPTY);
	    } else {
		slot.onSlotChanged();
	    }
	}
	return itemstack;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
	return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.ARCFURNACE_BLOCK);
    }

    public ArcFurnaceTile getTileEntity() {
	return tileEntity;
    }
}