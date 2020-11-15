package com.daellhin.realisticsolar.blocks.arcfurnace.enums;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.block.Block;

public enum ArcFurnaceMultiblockPattern {
	//@formatter:off
	
	// X = 0
	IP0P0N2( 0,  0, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P0N1( 0,  0, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P0P0( 0,  0,  0, ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get()),
	IP0P0P1( 0,  0,  1, ModBlocks.ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK.get()),
	IP0P0P2( 0,  0,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	IP0P1N2( 0,  1, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P1N1( 0,  1, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P110( 0,  1,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P1P1( 0,  1,  1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P1P2( 0,  1,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	IP0P2N2( 0,  2, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P2N1( 0,  2, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P2P0( 0,  2,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P2P1( 0,  2,  1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP0P2P2( 0,  2,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	// X = 1
	IP1P0N2( 1,  0, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P0N1( 1,  0, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P0P0( 1,  0,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P0P1( 1,  0,  1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P0P2( 1,  0,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	IP1P1N2( 1,  1, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P1N1( 1,  1, -1, ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get()),
	IP1P110( 1,  1,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P1P1( 1,  1,  1, ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get()),
	IP1P1P2( 1,  1,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	IP1P2N2( 1,  2, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP1P2N1( 1,  2, -1, ModBlocks.ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get()),
	IP1P2P0( 1,  2,  0, ModBlocks.ARCFURNACE_ITEM_INPUT_PORT_BLOCK.get()),
	IP1P2P1( 1,  2,  1, ModBlocks.ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get()),
	IP1P2P2( 1,  2,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	// X = 2
	IP2P0N2( 2,  0, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P0N1( 2,  0, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P0P0( 2,  0,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P0P1( 2,  0,  1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P0P2( 2,  0,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),

	IP2P1N2( 2,  1, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P1N1( 2,  1, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P110( 2,  1,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P1P1( 2,  1,  1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P1P2( 2,  1,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	
	IP2P2N2( 2,  2, -2, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P2N1( 2,  2, -1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P2P0( 2,  2,  0, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P2P1( 2,  2,  1, ModBlocks.ARCFURNACE_CASING_BLOCK.get()),
	IP2P2P2( 2,  2,  2, ModBlocks.ARCFURNACE_CASING_BLOCK.get());
	
	//@formatter:on

	private final int dx;
	private final int dy;
	private final int dz;
	private final Block block;

	ArcFurnaceMultiblockPattern(int dx, int dy, int dz, Block block) {
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