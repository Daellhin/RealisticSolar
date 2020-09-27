package com.daellhin.realisticsolar.blocks.arcfurnace;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

public class ArcFurnaceBlock extends BaseBlock {

	public static final String REGNAME = "arc_furnace_block";

	public ArcFurnaceBlock() {
		super(new BlockBuilder().basicMachineProperties().tileEntitySupplier(ArcFurnaceTile::new));
	}

}