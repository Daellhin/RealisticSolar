package com.daellhin.realisticsolar.creativetab;

import com.daellhin.realisticsolar.blocks.RealisticSolarBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RealisticSolarCreativeTab {
	public static CreativeTabs tabRS =  new CreativeTabs("tabRealisticSolar"){
		@Override 
		public Item getTabIconItem() {
			return new ItemStack(RealisticSolarBlocks.BlockAluminium).getItem();
			
		}
	};
	
	public static CreativeTabs tabRSM =  new CreativeTabs("tabRealisticSolarMachines"){
		@Override 
		public Item getTabIconItem() {
			return new ItemStack(RealisticSolarBlocks.BlockAluminium).getItem();
			
		}
	};

}
