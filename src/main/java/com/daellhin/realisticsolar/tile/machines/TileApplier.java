package com.daellhin.realisticsolar.tile.machines;

import com.daellhin.realisticsolar.crafting.MachineRecipes.ApplierRecipes;
import com.daellhin.realisticsolar.tile.base.TileThreeSlotMachine;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TileApplier extends TileThreeSlotMachine {
    private static final int MAX_BUFFER = 16;
    private static final int PROCESS_TIME = 200;
    private static final Block BUFFER_BLOCK = Blocks.cobblestone;
    public int buffer = 0;
    private int processTimeRemaining;
    public int timeSpentProcessing;

    @Override
    public void updateEntity() {
	boolean shouldMarkDirty = false;
	ItemStack slotStone = getStackInSlot(1);

	// Check for stone in buffer and fill from itemstack in bottom slot
	if (slotStone != null && slotStone.getItem() == Item.getItemFromBlock(TileApplier.BUFFER_BLOCK)
		&& buffer < TileApplier.MAX_BUFFER) {
	    this.buffer += TileApplier.MAX_BUFFER; // 1 cobblestone = 1 full tank
	    slotStone.stackSize--;
	    shouldMarkDirty = true;

	    if (slotStone.stackSize == 0) {
		slotStone = null;
		this.setInventorySlotContents(1, null);
		shouldMarkDirty = true;
	    }
	}

	// Decrement the time before the item is processed, and increment the time spent
	// processing it
	if (this.processTimeRemaining > 0 && this.canProcess()) {
	    this.processTimeRemaining--;
	    this.timeSpentProcessing++;
	}

	// Check for inventory contents and process any items
	if (!worldObj.isRemote && this.canProcess() && this.buffer >= 1 && this.processTimeRemaining == 0) {
	    this.processItem();
	    this.processTimeRemaining = TileApplier.PROCESS_TIME;
	    this.timeSpentProcessing = 0;
	}

	if (shouldMarkDirty) {
	    this.markDirty();
	}
    }

    public int getStoneScaled(int scaled) {
	return this.buffer * scaled / TileApplier.MAX_BUFFER;
    }

    public int getProgressScaled(int scaled) {
	return this.timeSpentProcessing * scaled / TileApplier.PROCESS_TIME;
    }

    /**
     * Returns true if the grindstone can smelt an item, i.e. has a source item,
     * destination stack isn't full, etc.
     */
    @Override
    protected boolean canProcess() {
	if (this.slot[0] == null || this.buffer == 0) {
	    return false;
	} else {
	    ItemStack itemstack = ApplierRecipes.processing().getProcessResult(this.slot[0]);
	    if (itemstack == null)
		return false;
	    if (this.slot[2] == null)
		return true;
	    if (!this.slot[2].isItemEqual(itemstack))
		return false;
	    int result = slot[2].stackSize + itemstack.stackSize;
	    return result <= getInventoryStackLimit() && result <= this.slot[2].getMaxStackSize();
	}
    }

    /**
     * Turn one item from the grindstone source stack into the appropriate processed
     * item in the furnace result stack
     */
    @Override
    public void processItem() {
	this.processTimeRemaining = TileApplier.PROCESS_TIME;

	if (this.canProcess()) {
	    ItemStack itemstack = ApplierRecipes.processing().getProcessResult(this.slot[0]);

	    if (this.slot[2] == null) {
		this.slot[2] = itemstack.copy();
	    } else if (this.slot[2].getItem() == itemstack.getItem()) {
		this.slot[2].stackSize += itemstack.stackSize;
	    }

	    this.slot[0].stackSize--;
	    this.buffer--;

	    if (this.slot[0].stackSize <= 0) {
		this.slot[0] = null;
	    }
	}
    }

}
