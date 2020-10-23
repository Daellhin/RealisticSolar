package com.daellhin.realisticsolar.blocks.siemensreactor.enums;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.block.Block;

public enum SiemensReactorMultiblockPattern {
	//@formatter:off
	
	IP0P0P0( 0,  0,  0, ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get()),
	IP0P1P0( 0,  1,  0, ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
	
	//@formatter:on

	private final int dx;
	private final int dy;
	private final int dz;
	private final Block block;

	SiemensReactorMultiblockPattern(int dx, int dy, int dz, Block block) {
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
		this.block = block;
	}

	// getters
	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public int getDz() {
		return dz;
	}

	public Block getBlock() {
		return block;
	}
}