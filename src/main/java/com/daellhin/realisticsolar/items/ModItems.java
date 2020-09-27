package com.daellhin.realisticsolar.items;

import static com.daellhin.realisticsolar.setup.Registration.ITEMS;

import com.daellhin.realisticsolar.items.handbook.HandBookItem;
import com.daellhin.realisticsolar.setup.ModSetup;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {

	public static void register() {
		// Needed to force class loading
	}

	private static final Item.Properties basicItemProperties = new Item.Properties().group(ModSetup.ITEM_GROUP);

	// classless
	public static final RegistryObject<Item> ALUMINIUM_ITEM = ITEMS.register("aluminium_item", () -> new Item(basicItemProperties));
	public static final RegistryObject<Item> WOODCHIP_ITEM = ITEMS.register("wood_chip_item", () -> new Item(basicItemProperties));
	public static final RegistryObject<Item> METALURGICAL_SILICON_ITEM = ITEMS.register("metallurgical_silicon_item", () -> new Item(basicItemProperties));

	// class
	public static final RegistryObject<Item> HANDBOOK_ITEM = ITEMS.register(HandBookItem.REGNAME, HandBookItem::new);

}
