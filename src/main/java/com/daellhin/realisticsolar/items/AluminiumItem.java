package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.item.Item;

public class AluminiumItem extends Item {
    public AluminiumItem() {
        super(new Item.Properties()
                .maxStackSize(64)
                .group(RealisticSolar.setup.itemGroup));
        setRegistryName("aluminium_item");
    }
}