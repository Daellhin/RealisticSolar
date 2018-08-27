package com.daellhin.realisticsolar.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public abstract class TileThreeSlotMachine extends Tile implements ISidedInventory {
    protected ItemStack[] slot = new ItemStack[3];
    private String customName;

    @Override
    public ItemStack decrStackSize(int i, int j) {
	if (this.slot[i] != null) {
	    ItemStack itemStack;

	    if (this.slot[i].stackSize <= j) {
		itemStack = this.slot[i];
		this.slot[i] = null;
		return itemStack;
	    } else {
		itemStack = this.slot[i].splitStack(j);

		if (this.slot[i].stackSize == 0) {
		    this.slot[i] = null;
		    this.setInventorySlotContents(i, null);
		}

		return itemStack;
	    }
	}

	return null;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
	return this.slot[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
	if (this.slot[i] != null) {
	    ItemStack itemStack = this.slot[i];
	    this.slot[i] = null;
	    return itemStack;
	}

	return null;
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
	return slot == 2 ? false : true;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
	return true;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
	this.slot[i] = itemStack;

	if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
	    itemStack.stackSize = this.getInventoryStackLimit();
	}
    }

    @Override
    public boolean canExtractItem(int i, ItemStack var2, int j) {
	return true;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
	return this.isItemValidForSlot(slot, itemStack);
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
	return null;
    }

    @Override
    public String getInventoryName() {
	return this.hasCustomInventoryName() ? this.customName : "container.threeSlotMachine";
    }

    @Override
    public boolean hasCustomInventoryName() {
	return this.customName != null && this.customName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
	return 64;
    }

    @Override
    public int getSizeInventory() {
	return this.slot.length;
    }

    @Override
    public void openInventory() {
    }

    @Override
    public void closeInventory() {
    }

    protected abstract boolean canProcess();

    protected abstract void processItem();
}