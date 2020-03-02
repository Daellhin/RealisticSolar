package com.daellhin.realisticsolar.blocks.random;

import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SilicaSandBlock extends FallingBlock {

    public static final String RegName = "silica_sand_block";

    public SilicaSandBlock() {
	super(Properties.create(Material.SAND)
		.hardnessAndResistance(2.0f)
		.sound(SoundType.SAND)
		.lightValue(0)
		.harvestTool(ToolType.SHOVEL)
	);
	setRegistryName(RegName);
    }
}
