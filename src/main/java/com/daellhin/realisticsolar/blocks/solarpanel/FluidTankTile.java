package com.daellhin.realisticsolar.blocks.solarpanel;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

import com.daellhin.realisticsolar.blocks.ModBlocks;

public class FluidTankTile extends TileEntity implements ITickableTileEntity {

	public static final int MAX_CONTENTS = 10000; // 10 buckets

	public FluidTankTile() {
		super(ModBlocks.FLUID_TANK_TILE.get());
	}

	private FluidTank tank = new FluidTank(MAX_CONTENTS) {
		@Override
		protected void onContentsChanged() {
			BlockState state = world.getBlockState(pos);
			world.notifyBlockUpdate(pos, state, state, 3);
			markDirty();
		}
	};

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT tag = super.getUpdateTag();
		CompoundNBT tankNBT = new CompoundNBT();
		tank.writeToNBT(tankNBT);
		//nbtTag.setTag("tank", tankNBT);
		tag.put("tank", tankNBT);
		return tag;
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		tank.readFromNBT(packet.getNbtCompound().getCompound("tank"));
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		tank.readFromNBT(compound.getCompound("tank"));
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		CompoundNBT tankNBT = new CompoundNBT();
		tank.writeToNBT(tankNBT);
		// compound.setTag("tank", tankNBT);
		tag.put("tank", tankNBT);
		return super.write(tag);
	}

	public FluidTank getTank() {
		return tank;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return LazyOptional.of(() -> (T) tank);
		}
		return super.getCapability(capability, side);
	}

	@Override
	public void tick() {
		System.out.printf("%s : %d%n", tank.getFluid().getTranslationKey(), tank.getFluidAmount());

	}

}