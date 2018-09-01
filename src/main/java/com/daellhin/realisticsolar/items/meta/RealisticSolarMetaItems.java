package com.daellhin.realisticsolar.items.meta;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RealisticSolarMetaItems {
    public static Item ItemDust;
    public static Item ItemIngot;
    public static Item ItemSilicon;
    public static Item ItemSiliconSolarCell;
    public static Item ItemSiliconWafer;

    public static final void init() {
	ItemDust = new ItemDust();
	ItemIngot = new ItemIngot();
	ItemSilicon = new ItemSilicon();
	ItemSiliconSolarCell = new ItemSiliconSolarCell();
	ItemSiliconWafer = new ItemSiliconWafer();

	GameRegistry.registerItem(ItemDust, ItemDust.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemIngot, ItemIngot.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemSilicon, ItemSilicon.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemSiliconSolarCell, ItemSiliconSolarCell.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemSiliconWafer, ItemSiliconWafer.getUnlocalizedName().substring(5));

    }

}
