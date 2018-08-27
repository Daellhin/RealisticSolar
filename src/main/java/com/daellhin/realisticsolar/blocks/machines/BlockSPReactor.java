package com.daellhin.realisticsolar.blocks.machines;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSPReactor extends Block {
    public BlockSPReactor(Material material) {
	super(material);
	this.setBlockName("blockSPReactor");
	this.setBlockTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	this.setCreativeTab(RealisticSolarCreativeTab.tabRSM);

	this.setHardness(2.0F);
	// this.setResistance();
	this.setHarvestLevel("pickaxe", 2);
	this.setLightLevel(0);
	this.setLightOpacity(16);
	this.setStepSound(Block.soundTypeMetal);
    }

}
