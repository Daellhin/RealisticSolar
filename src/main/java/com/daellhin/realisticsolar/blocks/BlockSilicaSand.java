package com.daellhin.realisticsolar.blocks;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSilicaSand extends Block {
    public BlockSilicaSand() {
	super(Material.sand);
	this.setBlockName("blockSilicaSand");
	this.setBlockTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	this.setCreativeTab(RealisticSolarCreativeTab.tabRS);

	this.setHardness(2.0F);
	// this.setResistance();
	this.setHarvestLevel("shovel", 2);
	this.setLightLevel(0);
	this.setLightOpacity(16);
	this.setStepSound(Block.soundTypeSand);
    }

}
