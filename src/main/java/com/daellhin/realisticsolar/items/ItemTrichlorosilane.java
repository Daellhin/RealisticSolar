package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.item.Item;

public class ItemTrichlorosilane extends Item {
	public ItemTrichlorosilane() {
		this.setUnlocalizedName("itemTrichlorosilane");
		this.setTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
		this.setCreativeTab(RealisticSolarCreativeTab.tabRS);
	}

}
