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
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class SolarPanelTile extends TileEntity implements ITickableTileEntity {

	private boolean isSunVisible;
	private CustomEnergyStorage energyStorage = createEnergy();
	private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

	public SolarPanelTile() {
		super(ModBlocks.SOLARPANEL_TILE.get());
	}

	@Override
	public void tick() {
		if (!level.isClientSide) {
			updateSunState();

			if (isSunVisible) {
				// the solar panel has no buffer so has to send out power here
				sendOutPower(energy, Config.SOLARPANEL_SEND.get());
			}

			BlockState blockState = level.getBlockState(worldPosition);
			if (blockState.getValue(BlockStateProperties.POWERED) != isSunVisible) {
				level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, isSunVisible), 3);
			}
		}
	}

	private void updateSunState() {
		this.isSunVisible = getSkyLight(level, 1.0f) > 0.0F;
	}

//	private static float getSkyLightOld(World world, BlockPos pos) {
//		float sunBrightness = limit((float) Math.cos(world.getSunAngle(1.0F)) * 2.0F + 0.2F, 0.0F, 1.0F);
//		//if (!BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.SANDY)) {
//			sunBrightness *= (1.0F - world.getRainLevel(1.0F) * 5.0F / 16.0F);
//			sunBrightness *= (1.0F - world.getThunderLevel(1.0F) * 5.0F / 16.0F);
//			sunBrightness = limit(sunBrightness, 0.0F, 1.0F);
//		//}
//		return world.getLightFor(LightType.SKY, pos) / 15.0F * sunBrightness;
//	}
//	
    /**
     * Vanilla copy of {@link net.minecraft.client.world.ClientWorld#getSkyDarken(float)} used to be World#getSunBrightness
     * source: https://github.com/mekanism/Mekanism/blob/1.16.x/src/main/java/mekanism/common/util/WorldUtils.java
     */
    private float getSkyLight(World world, float partialTicks) {
        float f = world.getTimeOfDay(partialTicks);
        float f1 = 1.0F - (MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.2F);
        f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
        f1 = 1.0F - f1;
        f1 = (float) (f1 * (1.0D - world.getRainLevel(partialTicks) * 5.0F / 16.0D));
        f1 = (float) (f1 * (1.0D - world.getThunderLevel(partialTicks) * 5.0F / 16.0D));
        return f1 * 0.8F + 0.2F;
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
		return new CustomEnergyStorage(0, 0, 100) {

			@Override
			protected void onEnergyChanged() {
			}

		};
	}

	private void sendOutPower(LazyOptional<IEnergyStorage> energyLO, int send) {
		energyLO.ifPresent(energy -> {
			AtomicInteger sendable = new AtomicInteger(send);
			for (Direction direction : Direction.values()) {
				TileEntity te = level.getBlockEntity(worldPosition.relative(direction));
				if (te != null) {
					boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction)
							.map(handler -> {
								if (handler.canReceive()) {
									int received = handler.receiveEnergy(sendable.get(), false);
									sendable.addAndGet(-received);
									return sendable.get() > 0;
								} else {
									return true;
								}
							})
							.orElse(true);
					if (!doContinue) {
						return;
					}
				}
			}
		});
	}

}
