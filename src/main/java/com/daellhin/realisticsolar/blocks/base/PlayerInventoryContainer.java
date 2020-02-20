package com.daellhin.realisticsolar.blocks.base;

import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public abstract class PlayerInventoryContainer extends Container {

    public PlayerInventoryContainer(@Nullable ContainerType<?> type, int id) {
	super(type, id);
    }

    public int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    public int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
    	for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    public void layoutPlayerInventorySlots(IItemHandler playerInventory, int x, int y) {
	// Player inventory
	addSlotBox(playerInventory, 9, x, y, 9, 18, 3, 18);
	// Player hotbar
	y += 58;
	addSlotBox(playerInventory, 0, x, y, 9, 18, 1, 0);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
	return false;
    }

}