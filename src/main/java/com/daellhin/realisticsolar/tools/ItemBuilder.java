package com.daellhin.realisticsolar.tools;

import com.daellhin.realisticsolar.setup.ModSetup;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Items;

public class ItemBuilder {
	private Properties properties;
	private boolean shiftInformation;

	public ItemBuilder basicProperties() {
		this.properties = new Item.Properties().group(ModSetup.ITEM_GROUP);
		return this;
	}

	public ItemBuilder unstackableItemProperties() {
		this.properties = new Item.Properties().maxStackSize(1)
				.group(ModSetup.ITEM_GROUP);
		return this;
	}

	public ItemBuilder bucketItemProperties() {
		this.properties = new Item.Properties().maxStackSize(1)
				.group(ModSetup.ITEM_GROUP)
				.containerItem(Items.BUCKET);
		return this;
	}

	public ItemBuilder addShiftInformation() {
		this.shiftInformation = true;
		return this;
	}

	// getters
	public Properties getProperties() {
		return properties;
	}

	public boolean getShiftInformation() {
		return shiftInformation;
	}

}
