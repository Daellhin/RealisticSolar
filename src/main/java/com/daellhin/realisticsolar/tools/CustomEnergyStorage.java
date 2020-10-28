package com.daellhin.realisticsolar.tools;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;

public class CustomEnergyStorage extends EnergyStorage implements INBTSerializable<CompoundNBT> {

	public CustomEnergyStorage(int capacity, int maxTransfer) {
		super(capacity, maxTransfer);
	}

	public CustomEnergyStorage(int capacity, int maxReceive, int maxExtract) {
		super(capacity, maxReceive, maxExtract);
	}

	protected void onEnergyChanged() {
	}

	public boolean isFull() {
		return this.energy >= getMaxEnergyStored();
	}

	public void addEnergy(int energy) {
		this.energy += energy;
		if (isFull()) {
			this.energy = getMaxEnergyStored();
		}
		onEnergyChanged();
	}

	public void consumeEnergy(int energy) {
		this.energy -= energy;
		if (this.energy < 0) {
			this.energy = 0;
		}
		onEnergyChanged();
	}

	@Override
	public CompoundNBT serializeNBT() {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("energy", getEnergyStored());
		return tag;
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		setEnergy(nbt.getInt("energy"));
	}

	// setters
	public void setEnergy(int energy) {
		this.energy = energy;
		onEnergyChanged();
	}
}
