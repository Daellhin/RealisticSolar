package com.daellhin.realisticsolar.tile.machines;

import com.daellhin.realisticsolar.crafting.MachineRecipes.ApplierRecipes;
import com.daellhin.realisticsolar.items.meta.RealisticSolarMetaItems;
import com.daellhin.realisticsolar.tile.base.TileThreeSlotMachine;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileApplier extends TileThreeSlotMachine {
    private static final int PROCESS_TIME = 10;
    private static final Item BUFFER_ITEM1 = RealisticSolarMetaItems.ItemIngot;
    private static final int METADATA1 = 1;
    public int buffer = 0;
    private int processTimeRemaining;
    public int timeSpentProcessing;
    private String inventoryName = "applier";
    private String name;

    @Override
    public void updateEntity() {
	boolean shouldMarkDirty = false;
	ItemStack slotB = getStackInSlot(1);

	// Check for bufferItem in bufferSlot and set buffer to 1 = true
	if (slotB != null && buffer == 0) {
	    if (slotB.getItem() == TileApplier.BUFFER_ITEM1 && slotB.getItemDamage() == METADATA1) {
		this.buffer = 1;
		shouldMarkDirty = true;

		if (slotB.stackSize == 0) {
		    slotB = null;
		    this.setInventorySlotContents(1, null);
		    shouldMarkDirty = true;
		}
	    }
	} else {
	    this.buffer = 0;

	}

	if (this.processTimeRemaining > 0 && this.canProcess()) {
	    this.processTimeRemaining--;
	    this.timeSpentProcessing++;
	}

	if (!worldObj.isRemote && this.canProcess()) {
	    if (this.timeSpentProcessing == PROCESS_TIME) {
		this.processTimeRemaining = 0;
		this.timeSpentProcessing = 0;
		this.processItem();
	    } else if (this.buffer == 1 && this.processTimeRemaining == 0) {
		this.processTimeRemaining = TileApplier.PROCESS_TIME;
	    }
	}

	if (shouldMarkDirty) {
	    this.markDirty();
	}

    }

    /**
     * Returns true if the grindstone can smelt an item, i.e. has a source item,
     * destination stack isn't full, etc.
     */
    @Override
    protected boolean canProcess() {
	if (this.slots[0] == null) {
	    return false;
	} else {
	    ItemStack itemstack = ApplierRecipes.processing().getProcessResult(this.slots[0]);
	    if (itemstack == null) {
		return false;
	    }
	    if (buffer == 0) {
		return false;
	    }

	    if (this.slots[2] == null)
		return true;
	    if (!this.slots[2].isItemEqual(itemstack))
		return false;
	    int result = slots[2].stackSize + itemstack.stackSize;
	    return result <= getInventoryStackLimit() && result <= this.slots[2].getMaxStackSize();
	}
    }

    /**
     * Turn one item from the grindstone source stack into the appropriate processed
     * item in the furnace result stack
     */
    @Override
    public void processItem() {
	ItemStack itemstack = ApplierRecipes.processing().getProcessResult(this.slots[0]);

	if (this.slots[2] == null) {
	    this.slots[2] = itemstack.copy();
	} else if (this.slots[2].getItem() == itemstack.getItem()) {
	    this.slots[2].stackSize += itemstack.stackSize;
	}

	this.slots[0].stackSize--;
	getStackInSlot(1).stackSize--;

	if (this.slots[0].stackSize <= 0) {
	    this.slots[0] = null;
	}

    }

    // sets the InventoryName
    @Override
    public String getInventoryName() {
	return this.hasCustomInventoryName() ? this.name : "container." + inventoryName;
    }

    @Override
    public int getProgressScaled(int scaled) {
	return this.timeSpentProcessing * scaled / TileApplier.PROCESS_TIME;
    }

    public static Item getBufferItem(int n) {
	switch (n) {
	case 1:
	    return BUFFER_ITEM1;
	}
	return null;
    }

    public static int getBufferItemMetadata(int n) {
	switch (n) {
	case 1:
	    return METADATA1;
	}
	return 0;
    }
}