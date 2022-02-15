package com.daellhin.realisticsolar.blocks.base;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

/**
 * Abstract class for container with a playerInventory
 *
 */
public abstract class PlayerInventoryContainer extends Container {

	public PlayerInventoryContainer(ContainerType<?> type, int id) {
		super(type, id);
	}

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int verAmount, int dx, int dy) {
		for (int j = 0; j < verAmount; j++) {
			index = addSlotRange(handler, index, x, y, horAmount, dx);
			y += dy;
		}
		return index;
	}

	public void layoutPlayerslots(IItemHandler playerInventory, int x, int y) {
		// Player inventory
		addSlotBox(playerInventory, 9, x, y, 9, 3, 18, 18);

		// Player hotbar
		addSlotRange(playerInventory, 0, x, y + 58, 9, 18);
	}

}