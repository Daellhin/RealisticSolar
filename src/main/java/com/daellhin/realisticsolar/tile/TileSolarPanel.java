package com.daellhin.realisticsolar.tile;

import com.daellhin.realisticsolar.tile.base.TileBasicGenerator;

import net.minecraftforge.common.util.ForgeDirection;

public class TileSolarPanel extends TileBasicGenerator {
    private boolean canGenerate;

    public TileSolarPanel(int generated, int buffer) {
	super(generated, buffer);
    }

    @Override
    protected void generate() {
	if (worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) {
	    this.canGenerate = true;
	} else {
	    this.canGenerate = false;
	}

	if (this.canGenerate) {
	    int h;
	    int i = this.worldObj.getBlockLightValue(this.xCoord, this.yCoord + 1, this.zCoord);
	    if (i == 15) {
		storage.modifyEnergyStored(generated);
	    } else if (i > 9) {
		storage.modifyEnergyStored((generated * i) / 15);
	    } else {
		storage.modifyEnergyStored((generated * i) / 1000);
	    }

	}
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
	return true;
    }
}
