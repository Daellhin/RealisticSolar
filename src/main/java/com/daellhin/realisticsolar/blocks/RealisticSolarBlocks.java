package com.daellhin.realisticsolar.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RealisticSolarBlocks {
    public static Block BlockAluminium;
    public static Block BlockSilver;
    public static Block BlockCleanSand;
    public static Block BlockSilicaSand;

    public static void init() {
	BlockAluminium = new BlockAluminium();
	BlockSilver = new BlockSilver();
	BlockCleanSand = new BlockCleanSand();
	BlockSilicaSand = new BlockSilicaSand();

	GameRegistry.registerBlock(BlockAluminium, BlockAluminium.getUnlocalizedName().substring(5));
	GameRegistry.registerBlock(BlockSilver, BlockSilver.getUnlocalizedName().substring(5));
	GameRegistry.registerBlock(BlockCleanSand, BlockCleanSand.getUnlocalizedName().substring(5));
	GameRegistry.registerBlock(BlockSilicaSand, BlockSilicaSand.getUnlocalizedName().substring(5));

    }

}
