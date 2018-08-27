package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.item.Item;

public class ItemGlassPanel extends Item {
    public ItemGlassPanel() {
	this.setUnlocalizedName("itemGlassPanel");
	this.setTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	this.setCreativeTab(RealisticSolarCreativeTab.tabRS);
    }

}
