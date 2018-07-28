package com.daellhin.realisticsolar.crafting;

import com.daellhin.realisticsolar.blocks.RealisticSolarBlocks;
import com.daellhin.realisticsolar.items.RealisticSolarItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class RealisticSolarCrafting {

	public static void init() {
		//Items
			//GameRegistry.addRecipe(new ItemStack(RealisticSolarItems.ItemAluminium, 9), new Object[]{"B  ","   ","   ",'B', RealisticSolarBlocks.BlockAluminium});
		
		//Blocks
			//GameRegistry.addRecipe(new ItemStack(RealisticSolarBlocks.BlockAluminium), new Object[]{"III","III","III",'I', RealisticSolarItems.ItemAluminium});
		
	}

}
