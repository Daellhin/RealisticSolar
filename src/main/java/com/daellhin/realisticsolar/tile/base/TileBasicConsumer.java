package com.daellhin.realisticsolar.tile.base;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class TileBasicConsumer extends Tile implements IEnergyHandler, IEnergyReceiver {
    protected EnergyStorage storage = new EnergyStorage(0);
    public int power;
    public int used;

    public TileBasicConsumer(int used, int buffer) {
	this.used = used;
	storage.setMaxReceive(used);
	storage.setMaxExtract(0);
	storage.setMaxTransfer(used);
	storage.setCapacity(buffer);
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
	return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
	return storage.receiveEnergy(storage.getMaxExtract(), simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
	return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
	return storage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
	return storage.getMaxEnergyStored();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {

	super.readFromNBT(nbt);
	storage.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {

	super.writeToNBT(nbt);
	storage.writeToNBT(nbt);
    }

    @Override
    public void updateEntity() {
	if (storage.getEnergyStored() > 0) {
	    for (int i = 0; i < 6; i++) {
		int targetX = this.xCoord + ForgeDirection.getOrientation(i).offsetX;
		int targetY = this.yCoord + ForgeDirection.getOrientation(i).offsetY;
		int targetZ = this.zCoord + ForgeDirection.getOrientation(i).offsetZ;

		TileEntity tile = worldObj.getTileEntity(targetX, targetY, targetZ);
		if (tile instanceof IEnergyHandler) {
		    // Gets the maximum amount of energy that can be extracted in 1 tick
		    int maxExtract = storage.getMaxExtract();
		    // Simulates extracting all the energy to see how much is available to be
		    // extracted
		    int maxAvaliable = storage.extractEnergy(maxExtract, true);
		    // Sends "maxAvailable" to the target tile and records how much energy was
		    // accepted
		    int energyTransfered = ((IEnergyHandler) tile).receiveEnergy(ForgeDirection.getOrientation(i).getOpposite(), maxAvaliable, false);
		    // Extract the transferred energy from the internal storage
		    storage.extractEnergy(energyTransfered, false);
		}
	    }
	}

	used();
	power = storage.getEnergyStored();
    }

    protected abstract void used();

    public int getPowerScaled(int scaled) {
	return this.storage.getEnergyStored() * scaled / this.storage.getMaxEnergyStored();
    }

}
