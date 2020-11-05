package com.daellhin.realisticsolar.fluids.handling;

import java.util.HashMap;
import java.util.HashSet;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.LazyOptional;

public class TankContainer {
	private HashSet<CustomFluidTank> tanks;
	private HashMap<Direction, LazyOptional<CustomFluidTank>> lazyOptionals;

	public TankContainer() {
		tanks = new HashSet<>();
		lazyOptionals = new HashMap<Direction, LazyOptional<CustomFluidTank>>();

		lazyOptionals.put(null, LazyOptional.empty());
		for (Direction dir : Direction.values()) {
			lazyOptionals.put(dir, LazyOptional.empty());
		}
	}

	public void add(@Nonnull CustomFluidTank tank, Direction direction) {
		this.tanks.add(tank);
		this.lazyOptionals.put(direction, LazyOptional.of(() -> tank));
	}

	public LazyOptional<CustomFluidTank> getCapabilityForSide(Direction direction) {
		return lazyOptionals.get(direction);
	}

	public void readFromNBT(CompoundNBT tag) {
		tanks.forEach(tank -> tank.readFromNBT(tag));
	}

	public void writeToNBT(CompoundNBT compound) {
		tanks.forEach(tank -> {
			CompoundNBT tankNBT = new CompoundNBT();
			tank.writeToNBT(tankNBT);
			tank.putTag(compound, tankNBT);
		});
	}

	public void putTag(CompoundNBT tag, CompoundNBT tankNBT) {
		tanks.forEach(tank -> tank.putTag(tag, tankNBT));
	}

	public HashSet<CustomFluidTank> getTanks() {
		return tanks;
	}
}
