package com.daellhin.realisticsolar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockAluminium extends Block {
	public BlockAluminium(){
		super(Properties.create(Material.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(2.0f)
			.lightValue(0)
		);
		setRegistryName("block_aluminium");	
	}
}
