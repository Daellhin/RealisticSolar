package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;

public class SiemensReactorTopBlock extends BaseBlock {

	public static final String REGNAME = "siemens_reactor_top_block";
	// TODO check if block is formed +(maybe change facing according to other block)
	// public static final BooleanProperty FORMED =
	// BooleanProperty.create("formed");

	public SiemensReactorTopBlock() {
		super(new BlockBuilder().basicProperties().shape(Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
	}

}