package com.daellhin.realisticsolar.blocks.fluidtank;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.FluidTankTile;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;

public class FluidTankBlock extends BaseBlock {

	public static final String REGNAME = "fluid_tank_block";

	public FluidTankBlock() {
		super(new BlockBuilder().basicMachineProperties().tileEntitySupplier(FluidTankTile::new));
	}
    
	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			FluidUtil.interactWithFluidHandler(player, hand, world, pos, Direction.UP);
		}
		return super.onBlockActivated(state, world, pos, player, hand, hit);
	}

}
