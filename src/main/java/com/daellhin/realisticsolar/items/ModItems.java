package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.book.BookItem;
import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

public class ModItems {

    public static Item.Properties properties = new Item.Properties().group(RealisticSolar.setup.getTab());
    
    @ObjectHolder(RealisticSolar.MODID + ":" + "aluminium_item")
    public static Item ALUMINIUMITEM = new Item(properties).setRegistryName("aluminium_item");
    
    @ObjectHolder(RealisticSolar.MODID + ":" + "book_item")
    public static BookItem BOOKITEM = new BookItem();
    
    public static Item[] ITEMS = { ALUMINIUMITEM, BOOKITEM };
}