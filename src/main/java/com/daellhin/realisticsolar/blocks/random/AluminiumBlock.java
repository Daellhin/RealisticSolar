package com.daellhin.realisticsolar.blocks.random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class AluminiumBlock extends Block {

    public static final String RegName = "aluminium_block";

    public AluminiumBlock() {
	super(Properties.create(Material.IRON)
		.sound(SoundType.METAL)
		.hardnessAndResistance(2.0f)
		.lightValue(0)
	);
	setRegistryName(RegName);
    }
}
