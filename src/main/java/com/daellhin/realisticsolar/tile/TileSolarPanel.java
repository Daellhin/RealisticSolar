package com.daellhin.realisticsolar.tile;

import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarPanel extends TileBasicGenerator {
	private boolean canGenerate;
	
	public TileSolarPanel(int generated, int buffer){
		super(generated, buffer);
	}

	@Override
	protected void generate(){	
		this.canGenerate = true;
	
		if(this.canGenerate) {
			storage.modifyEnergyStored(generated);
		}
	}
	@Override
	public boolean canConnectEnergy(ForgeDirection from){
		return true;
	}
}
