package com.daellhin.realisticsolar.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot
{
	
	public SlotOutput(IInventory inventory, int id, int x, int y)
	{
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack itemstack)
	{
		return false;
	}
}