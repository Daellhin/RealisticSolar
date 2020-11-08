package com.daellhin.realisticsolar.items;

import static com.daellhin.realisticsolar.setup.Registration.ITEMS;

import com.daellhin.realisticsolar.items.base.BaseItem;
import com.daellhin.realisticsolar.items.handbook.HandBookItem;
import com.daellhin.realisticsolar.tools.builders.ItemBuilder;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

	// Needed to force class loading
	public static void register() {
	}

	// classless
	public static final RegistryObject<Item> ALUMINIUM_ITEM = ITEMS
			.register("aluminium_item", () -> new BaseItem(new ItemBuilder().basicProperties()));
	public static final RegistryObject<Item> WOODCHIP_ITEM = ITEMS
			.register("wood_chip_item", () -> new BaseItem(new ItemBuilder().basicProperties()));
	public static final RegistryObject<Item> METALURGICAL_SILICON_ITEM = ITEMS
			.register("metallurgical_silicon_item", () -> new BaseItem(new ItemBuilder().basicProperties()));
	public static final RegistryObject<Item> WRENCH_ITEM = ITEMS
			.register("wrench_item", () -> new BaseItem(new ItemBuilder().unstackableItemProperties()
					.addShiftInformation()));

	// class
	public static final RegistryObject<Item> HANDBOOK_ITEM = ITEMS
			.register("hand_book_item", () -> new HandBookItem(new ItemBuilder().unstackableItemProperties()
					.addShiftInformation()));

}
