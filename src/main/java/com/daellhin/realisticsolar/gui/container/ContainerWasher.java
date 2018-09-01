package com.daellhin.realisticsolar.gui.container;

import com.daellhin.realisticsolar.crafting.MachineRecipes.WasherRecipes;
import com.daellhin.realisticsolar.gui.slot.SlotOutput;
import com.daellhin.realisticsolar.tile.machines.TileWasher;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerWasher extends Container {
    public TileWasher washer;
    private int lastProcessTime;
    private int lastStoneContents;

    public ContainerWasher(InventoryPlayer inventoryPlayer, TileWasher washer) {
	this.washer = washer;

	this.addSlotToContainer(new Slot(washer, 0, 56, 17));
	this.addSlotToContainer(new Slot(washer, 1, 56, 53));
	this.addSlotToContainer(new SlotOutput(washer, 2, 116, 35));

	// Create the inventory slots
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 9; j++) {
		this.addSlotToContainer(new Slot(inventoryPlayer, 9 + j + i * 9, 8 + j * 18, 84 + 18 * i));
	    }
	}

	// Create the hotbar slots
	for (int i = 0; i < 9; i++) {
	    this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + 18 * i, 142));
	}
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafter) {
	super.addCraftingToCrafters(crafter);
	crafter.sendProgressBarUpdate(this, 0, this.washer.timeSpentProcessing);
	crafter.sendProgressBarUpdate(this, 1, this.washer.buffer);
    }

    @Override
    public void detectAndSendChanges() {
	super.detectAndSendChanges();

	for (int i = 0; i < this.crafters.size(); i++) {
	    ICrafting crafter = (ICrafting) this.crafters.get(i);

	    if (this.lastProcessTime != this.washer.timeSpentProcessing) {
		crafter.sendProgressBarUpdate(this, 0, this.washer.timeSpentProcessing);
	    }
	    if (this.lastStoneContents != this.washer.buffer) {
		crafter.sendProgressBarUpdate(this, 1, this.washer.buffer);
	    }

	    this.lastProcessTime = this.washer.timeSpentProcessing;
	    this.lastStoneContents = this.washer.buffer;
	}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int progressBar, int var2) {
	if (progressBar == 0)
	    this.washer.timeSpentProcessing = var2;
	if (progressBar == 1)
	    this.washer.buffer = var2;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
	return this.washer.isUseableByPlayer(player);
    }

    // Called when the player shift-clicks an item
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
	ItemStack itemstack = null;
	Slot slot = (Slot) this.inventorySlots.get(index);

	if (slot != null && slot.getHasStack()) {
	    ItemStack itemstack1 = slot.getStack();
	    itemstack = itemstack1.copy();

	    if (index == 2) {
		if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
		    return null;
		}

		slot.onSlotChange(itemstack1, itemstack);
	    } else if (index != 1 && index != 0) {
		if (WasherRecipes.processing().getProcessResult(itemstack1) != null) {
		    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
			return null;
		    }
		} else if (itemstack1.getItem() == TileWasher.getBufferItem(1)) {
		    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
			return null;
		    }
		} else if (itemstack1.getItem() == TileWasher.getBufferItem(2)) {
		    if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
			return null;
		    }
		} else if (index >= 3 && index < 30) {
		    if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
			return null;
		    }
		} else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
		    return null;
		}
	    } else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
		return null;
	    }

	    if (itemstack1.stackSize == 0) {
		slot.putStack((ItemStack) null);
	    } else {
		slot.onSlotChanged();
	    }

	    if (itemstack1.stackSize == itemstack.stackSize) {
		return null;
	    }

	    slot.onPickupFromSlot(player, itemstack1);
	}

	return itemstack;
    }

}
