package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class SiemensReactorTopBlock extends BaseBlock {

	public static final String REGNAME = "siemens_reactor_top_block";

	public SiemensReactorTopBlock() {
		super(new BlockBuilder().basicProperties().shape(Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
		setDefaultState(stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH).with(BlockStateProperties.POWERED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(BlockStateProperties.FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
		if (!world.isRemote) {
			BlockState blockStateBelow = world.getBlockState(pos.add(0, -1, 0));
			if (blockStateBelow.getBlock() == ModBlocks.SIEMENSREACTOR_BOTTOM_BLOCK.get()) {
				blockStateBelow.getBlock().onBlockActivated(blockStateBelow, world, pos.add(0, -1, 0), player, hand, rayTraceResult);
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isRemote) {
			BlockState stateUnder = world.getBlockState(pos.add(0, -1, 0));
			if (stateUnder.getBlock() == ModBlocks.SIEMENSREACTOR_BOTTOM_BLOCK.get()) {
				world.setBlockState(pos.add(0, -1, 0), stateUnder.with(SiemensReactorBottomBlock.FORMED, false), 3);
			}

		}
		super.onBlockHarvested(world, pos, state, player);
	}

}