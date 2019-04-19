package com.daellhin.realisticsolar.blocks;

import net.minecraft.block.Block;

public class BlockBasic extends Block{
	public BlockBasic(String string, Block.Properties properties) {
		super(properties);
		setRegistryName(string);
	}

}
