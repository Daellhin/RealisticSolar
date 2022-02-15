package com.daellhin.realisticsolar.blocks.arcfurnace.tiles;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurnace.blocks.ArcFurnacePortBlock;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class ArcFurnacePortTile extends TileEntity {

	public ArcFurnacePortTile() {
		super(ModBlocks.ARCFURNACE_PORT_TILE.get());
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		BlockState state = level.getBlockState(worldPosition);

		if ((state.getBlock() instanceof ArcFurnacePortBlock)) {
			ArcFurnacePortBlock portBlock = ((ArcFurnacePortBlock) state.getBlock());		
			
			if (portBlock.isMultiblockFormed(state, level, worldPosition)) {	
				BlockPos controllerPos = portBlock.getControllerBlockPos(state, level, worldPosition);
				TileEntity te = level.getBlockEntity(controllerPos);			
				
				if (te instanceof ArcFurnaceControllerTile) {
					return ((ArcFurnaceControllerTile) te).getCapability(capability, side, portBlock.getType());
				}
			}
		}
		return super.getCapability(capability, side);
	}

}
