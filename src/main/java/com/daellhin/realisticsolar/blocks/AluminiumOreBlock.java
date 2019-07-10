package com.daellhin.realisticsolar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AluminiumOreBlock extends Block{
	public static final String RegName = "aluminium_ore_block";

	public AluminiumOreBlock() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(2.0f)
				.sound(SoundType.STONE)
				.lightValue(0)
		);
		setRegistryName(RegName);
	}

}
