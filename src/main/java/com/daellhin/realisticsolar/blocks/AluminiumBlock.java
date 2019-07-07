package com.daellhin.realisticsolar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AluminiumBlock extends Block {
	public AluminiumBlock(){
		super(Properties.create(Material.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(2.0f)
			.lightValue(0)
		);
		setRegistryName("aluminium_block");	
	}
}
