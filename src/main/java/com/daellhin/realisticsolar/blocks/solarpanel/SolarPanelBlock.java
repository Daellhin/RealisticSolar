package com.daellhin.realisticsolar.blocks.solarpanel;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

public class SolarPanelBlock extends BaseBlock {

	public static final String REGNAME = "solar_panel_block";

	public SolarPanelBlock() {
		super(new BlockBuilder().basicMachineProperties().tileEntitySupplier(SolarPanelTile::new).shiftInformation("information." + REGNAME));
	}
}