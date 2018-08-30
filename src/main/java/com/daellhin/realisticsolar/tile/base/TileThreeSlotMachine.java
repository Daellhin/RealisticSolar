package com.daellhin.realisticsolar.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public abstract class TileThreeSlotMachine extends Tile implements ISidedInventory {
    protected ItemStack[] slots = new ItemStack[3];
    private String customName;

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
	super.readFromNBT(nbt);
	NBTTagList list = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
	slots = new ItemStack[getSizeInventory()];

	for (int i = 0; i < list.tagCount(); ++i) {
	    NBTTagCompound comp = list.getCompoundTagAt(i);
	    int j = comp.getByte("Slot") & 255;
	    if (j >= 0 && j < slots.length) {
		slots[j] = ItemStack.loadItemStackFromNBT(comp);
	    }
	}
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
	super.writeToNBT(nbt);
	NBTTagList list = new NBTTagList();

	for (int i = 0; i < slots.length; ++i) {
	    if (slots[i] != null) {
		NBTTagCompound comp = new NBTTagCompound();
		comp.setByte("Slot", (byte) i);
		slots[i].writeToNBT(comp);
		list.appendTag(comp);
	    }
	}

	nbt.setTag("Items", list);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
	if (this.slots[i] != null) {
	    ItemStack itemStack;

	    if (this.slots[i].stackSize <= j) {
		itemStack = this.slots[i];
		this.slots[i] = null;
		return itemStack;
	    } else {
		itemStack = this.slots[i].splitStack(j);

		if (this.slots[i].stackSize == 0) {
		    this.slots[i] = null;
		    this.setInventorySlotContents(i, null);
		}

		return itemStack;
	    }
	}

	return null;
    }

    public int getProgressScaled(int scaled) {
	return 0;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
	return this.slots[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
	if (this.slots[i] != null) {
	    ItemStack itemStack = this.slots[i];
	    this.slots[i] = null;
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
	this.slots[i] = itemStack;

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
	return this.hasCustomInventoryName() ? this.customName : customName;
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
	return this.slots.length;
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