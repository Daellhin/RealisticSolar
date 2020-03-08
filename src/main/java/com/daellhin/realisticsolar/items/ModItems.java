package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.handbook.HandBookItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {

    public static Item.Properties properties = new Item.Properties().group(RealisticSolar.setup.getTab());
    
    @ObjectHolder(RealisticSolar.MODID + ":" + "aluminium_item")
    public static Item ALUMINIUM_ITEM = new Item(properties).setRegistryName("aluminium_item");
    
    @ObjectHolder(RealisticSolar.MODID + ":" + "wood_chip_item")
    public static Item WOODCHIP_ITEM = new Item(properties).setRegistryName("wood_chip_item");
    
    @ObjectHolder(RealisticSolar.MODID + ":" + "metallurgical_silicon_item")
    public static Item METALURGICAL_SILICON_ITEM = new Item(properties).setRegistryName("metallurgical_silicon_item");
    
    @ObjectHolder(RealisticSolar.MODID + ":" + HandBookItem.RegName)
    public static HandBookItem HANDBOOK_ITEM = new HandBookItem();
    
    public static Item[] ITEMS = {ALUMINIUM_ITEM, WOODCHIP_ITEM, HANDBOOK_ITEM, METALURGICAL_SILICON_ITEM};
}
