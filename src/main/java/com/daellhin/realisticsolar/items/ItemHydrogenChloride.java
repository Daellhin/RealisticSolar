package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.item.Item;

public class ItemHydrogenChloride extends Item {
    public ItemHydrogenChloride() {
	this.setUnlocalizedName("itemHydrogenChloride");
	this.setTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	this.setCreativeTab(RealisticSolarCreativeTab.tabRS);
    }

}
