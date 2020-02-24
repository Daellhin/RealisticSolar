package com.daellhin.realisticsolar.blocks.base;

import java.util.concurrent.atomic.AtomicInteger;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

/**
 * abstract class for tileEntitys that generate power
 * 
 */
public abstract class GeneratorTile extends TileEntity {

    public GeneratorTile(TileEntityType<?> tileEntityTypeIn) {
	super(tileEntityTypeIn);
    }

    public void sendOutPower(LazyOptional<IEnergyStorage> energyLO, int send) {
	energyLO.ifPresent(energy -> {
	    AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
	    if (capacity.get() > 0) {
		for (Direction direction : Direction.values()) {
		    TileEntity te = world.getTileEntity(pos.offset(direction));
		    if (te != null) {
			boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
			    if (handler.canReceive()) {
				int received = handler.receiveEnergy(Math.min(capacity.get(), send), false);
				capacity.addAndGet(-received);
				((CustomEnergyStorage) energy).consumeEnergy(received);
				markDirty();
				return capacity.get() > 0;
			    } else {
				return true;
			    }
			}).orElse(true);
			if (!doContinue) {
			    return;
			}
		    }
		}
	    }
	});
    }
}
