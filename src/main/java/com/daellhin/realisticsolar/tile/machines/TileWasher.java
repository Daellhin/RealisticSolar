package com.daellhin.realisticsolar.tile.machines;

import com.daellhin.realisticsolar.crafting.MachineRecipes.WasherRecipes;
import com.daellhin.realisticsolar.tile.base.TileThreeSlotMachine;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class TileWasher extends TileThreeSlotMachine {
    private static final int MAX_BUFFER = 16;
    private static final int PROCESS_TIME = 150;
    private static final Item BUFFER_ITEM1 = Items.water_bucket;
    private static final Item BUFFER_ITEM2 = Items.potionitem;
    public int buffer = 0;
    private int processTimeRemaining = 0;
    public int timeSpentProcessing = 0;
    private String inventoryName = "washer";
    private String name;
    private boolean canUse;

    public TileWasher(int used, int buffer) {
	super(used, buffer);

    }

    @Override
    public void updateEntity() {
	boolean shouldMarkDirty = false;
	ItemStack slotB = getStackInSlot(1);

	// Check for bufferBlock in bufferSlot and fill buffer
	if (slotB != null && buffer == 0) {
	    if (slotB.getItem() == TileWasher.BUFFER_ITEM1 || slotB.getItem() == TileWasher.BUFFER_ITEM2) {
		this.buffer += TileWasher.MAX_BUFFER;
		slotB.stackSize--;
		shouldMarkDirty = true;

		if (slotB.stackSize == 0) {
		    slotB = null;
		    this.setInventorySlotContents(1, null);
		    shouldMarkDirty = true;
		}
	    }
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
	    } else if (this.buffer >= 1 && this.processTimeRemaining == 0) {
		this.processTimeRemaining = TileWasher.PROCESS_TIME;
	    }
	}

	if (shouldMarkDirty) {
	    this.markDirty();
	}

    }

    public int getBufferScaled(int scaled) {
	return this.buffer * scaled / TileWasher.MAX_BUFFER;
    }

    /**
     * Returns true if the grindstone can smelt an item, i.e. has a source item,
     * destination stack isn't full, etc.
     */
    @Override
    protected boolean canProcess() {
	if (this.slots[0] == null || this.buffer == 0) {
	    return false;

	} else {
	    ItemStack itemstack = WasherRecipes.processing().getProcessResult(this.slots[0]);
	    if (itemstack == null) {
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
	ItemStack itemstack = WasherRecipes.processing().getProcessResult(this.slots[0]);

	if (this.slots[2] == null) {
	    this.slots[2] = itemstack.copy();
	} else if (this.slots[2].getItem() == itemstack.getItem()) {
	    this.slots[2].stackSize += itemstack.stackSize;
	}

	this.slots[0].stackSize--;
	this.buffer--;

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
	return this.timeSpentProcessing * scaled / TileWasher.PROCESS_TIME;
    }

    public static Item getBufferItem(int n) {
	switch (n) {
	case 1:
	    return BUFFER_ITEM1;
	case 2:
	    return BUFFER_ITEM2;
	}
	return null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTags) {
	super.readFromNBT(nbtTags);
	buffer = (int) nbtTags.getDouble("buffer");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTags) {
	super.writeToNBT(nbtTags);
	nbtTags.setDouble("buffer", buffer);

    }

    @Override
    protected void used() {
	this.canUse = true;

	if (this.canUse) {
	    storage.modifyEnergyStored(used);
	}
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
	return true;
    }
}