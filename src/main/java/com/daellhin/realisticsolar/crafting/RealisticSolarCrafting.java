package com.daellhin.realisticsolar.crafting;

import com.daellhin.realisticsolar.blocks.RealisticSolarBlocks;
import com.daellhin.realisticsolar.blocks.meta.RealisticSolarMetaBlocks;
import com.daellhin.realisticsolar.items.meta.RealisticSolarMetaItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class RealisticSolarCrafting {
    public static void init() {
	// Items
	GameRegistry.addRecipe(new ItemStack(RealisticSolarBlocks.BlockAluminium), "AAA", "AAA", "AAA", 'A', new ItemStack(RealisticSolarMetaItems.ItemIngot, 1,0));
	GameRegistry.addRecipe(new ItemStack(RealisticSolarBlocks.BlockSilver), "SSS", "SSS", "SSS", 'S', new ItemStack(RealisticSolarMetaItems.ItemIngot, 1, 1));
	
	// Blocks
	GameRegistry.addShapelessRecipe(new ItemStack(RealisticSolarMetaItems.ItemIngot, 9, 0),RealisticSolarBlocks.BlockAluminium);
	GameRegistry.addShapelessRecipe(new ItemStack(RealisticSolarMetaItems.ItemIngot, 9, 1),RealisticSolarBlocks.BlockSilver);
	
	// Furnace
	GameRegistry.addSmelting(new ItemStack(RealisticSolarMetaBlocks.BlockOre, 1, 1), new ItemStack(RealisticSolarMetaItems.ItemIngot, 1, 1), 0.5F);
	GameRegistry.addSmelting(new ItemStack(RealisticSolarMetaBlocks.BlockOre, 1, 0), new ItemStack(RealisticSolarMetaItems.ItemIngot, 1, 0), 0.5F);

    }

}
