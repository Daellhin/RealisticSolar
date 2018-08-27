package com.daellhin.realisticsolar.gui.container;

import com.daellhin.realisticsolar.crafting.MachineRecipes.ApplierRecipes;
import com.daellhin.realisticsolar.gui.slot.SlotOutput;
import com.daellhin.realisticsolar.tile.machines.TileApplier;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerApplier extends Container {
    public TileApplier applier;
    private int lastProcessTime;
    private int lastStoneContents;

    public ContainerApplier(InventoryPlayer inventoryPlayer, TileApplier applier) {
	this.applier = applier;

	this.addSlotToContainer(new Slot(applier, 0, 56, 17));
	this.addSlotToContainer(new Slot(applier, 1, 56, 53));
	this.addSlotToContainer(new SlotOutput(applier, 2, 116, 35));

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
	crafter.sendProgressBarUpdate(this, 0, this.applier.timeSpentProcessing);
	crafter.sendProgressBarUpdate(this, 1, this.applier.buffer);
    }

    @Override
    public void detectAndSendChanges() {
	super.detectAndSendChanges();

	for (int i = 0; i < this.crafters.size(); i++) {
	    ICrafting crafter = (ICrafting) this.crafters.get(i);

	    if (this.lastProcessTime != this.applier.timeSpentProcessing) {
		crafter.sendProgressBarUpdate(this, 0, this.applier.timeSpentProcessing);
	    }
	    if (this.lastStoneContents != this.applier.buffer) {
		crafter.sendProgressBarUpdate(this, 1, this.applier.buffer);
	    }

	    this.lastProcessTime = this.applier.timeSpentProcessing;
	    this.lastStoneContents = this.applier.buffer;
	}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int progressBar, int var2) {
	if (progressBar == 0)
	    this.applier.timeSpentProcessing = var2;
	if (progressBar == 1)
	    this.applier.buffer = var2;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
	return this.applier.isUseableByPlayer(player);
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
		if (ApplierRecipes.processing().getProcessResult(itemstack1) != null) {
		    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
			return null;
		    }
		} else if (itemstack1.getItem() == Item.getItemFromBlock(Blocks.cobblestone)) {
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
