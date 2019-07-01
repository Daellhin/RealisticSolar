package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.item.Item;

public class ItemAluminium extends Item {
    public ItemAluminium() {
        super(new Item.Properties()
                .maxStackSize(64)
                .group(RealisticSolar.setup.itemGroup));
        setRegistryName("item_aluminium");
    }
}