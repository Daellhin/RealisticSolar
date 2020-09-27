package com.daellhin.realisticsolar.blocks.solarpanel;

import java.util.concurrent.atomic.AtomicInteger;

import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class SolarPanelTile extends TileEntity implements ITickableTileEntity {

	private boolean isSunVisible;
	private int progress;
	public CustomEnergyStorage energyStorage = createEnergy();
	public LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	public SolarPanelTile() {
		super(ModBlocks.SOLARPANEL_TILE.get());
	}

	@Override
	public void tick() {
		if (!world.isRemote) {
			updateSunState();

			if (isSunVisible) {
				energyStorage.addEnergy(Config.SOLARPANEL_GENERATE.get());
				markDirty();
			}

			BlockState blockState = world.getBlockState(pos);
			if (blockState.get(BlockStateProperties.POWERED) != isSunVisible) {
				world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, isSunVisible), 3);
			}

			sendOutPower(energy, Config.SOLARPANEL_SEND.get());
		}
	}

	private void updateSunState() {
		this.isSunVisible = getSkyLight(this.getWorld(), this.pos.up()) > 0.0F;
	}

	private static float getSkyLight(World world, BlockPos pos) {
		float sunBrightness = limit((float) Math.cos(world.getCelestialAngleRadians(1.0F)) * 2.0F + 0.2F, 0.0F, 1.0F);
		if (!BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.SANDY)) {
			sunBrightness *= (1.0F - world.getRainStrength(1.0F) * 5.0F / 16.0F);
			sunBrightness *= (1.0F - world.getThunderStrength(1.0F) * 5.0F / 16.0F);
			sunBrightness = limit(sunBrightness, 0.0F, 1.0F);
		}
		return world.getLightFor(LightType.SKY, pos) / 15.0F * sunBrightness;
	}

	private static float limit(float value, float min, float max) {
		if ((Float.isNaN(value)) || (value <= min)) {
			return min;
		}
		if (value >= max) {
			return max;
		}
		return value;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == CapabilityEnergy.ENERGY) {
			return energy.cast();
		}
		return super.getCapability(cap, side);
	}

	public CustomEnergyStorage createEnergy() {
		return new CustomEnergyStorage(Config.ARCFURNACE_MAXPOWER.get(), Config.ARCFURNACE_RECEIVE.get()) {

			@Override
			protected void onEnergyChanged() {
				markDirty();
			}

		};
	}

	private void sendOutPower(LazyOptional<IEnergyStorage> energyLO, int send) {
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

	// progress
	public int getProgress() {
		return progress;
	}

	public void setProgress(int value) {
		this.progress = value;
	}

	// energy
	public int getEnergy() {
		return getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
	}

	public double getFractionOfEnergy() {
		return getEnergy() / (double) Config.SOLARPANEL_MAXPOWER.get();
	}

}
