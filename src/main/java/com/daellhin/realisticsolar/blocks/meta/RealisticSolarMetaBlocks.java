package com.daellhin.realisticsolar.blocks.meta;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class RealisticSolarMetaBlocks {
    public static Block BlockSolarPanel;
    public static Block BlockOre;

    public static void init() {
	BlockSolarPanel = new BlockSolarPanel();
	BlockOre = new BlockOre();

	GameRegistry.registerBlock(BlockSolarPanel, ItemBlockSolarPanel.class,BlockSolarPanel.getUnlocalizedName().substring(5));
	GameRegistry.registerBlock(BlockOre, ItemBlockOre.class, BlockOre.getUnlocalizedName().substring(5));

    }

}
