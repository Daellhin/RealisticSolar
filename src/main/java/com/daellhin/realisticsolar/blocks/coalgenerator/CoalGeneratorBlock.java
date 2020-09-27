package com.daellhin.realisticsolar.blocks.coalgenerator;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

public class CoalGeneratorBlock extends BaseBlock {

	public static final String REGNAME = "coal_generator_block";

	public CoalGeneratorBlock() {
		super(new BlockBuilder().basicMachineProperties().tileEntitySupplier(CoalGeneratorTile::new).shiftInformation("information." + REGNAME));
	}

}