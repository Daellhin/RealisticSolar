package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.base.MultiblockPartBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.enums.SiemensReactorMultiblockPart;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SiemensReactorPartBlock extends MultiblockPartBlock {

	public SiemensReactorPartBlock() {
		super(new BlockBuilder().basicProperties()
				.shape(Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
//		setDefaultState(stateContainer.getBaseState()
//				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
//				.with(SiemensReactorControllerBlock.SIEMENSREACTORPART, SiemensReactorMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, context.getNearestLookingDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, SiemensReactorControllerBlock.SIEMENSREACTORPART);
	}

	@Override
	public boolean isMultiblockFormed(BlockState state, World world, BlockPos pos) {
		BlockPos controllerPos = getControllerBlockPos(state, world, pos);
		BlockState controllerBlockState = world.getBlockState(controllerPos);

		if (controllerBlockState.getBlock() instanceof SiemensReactorControllerBlock) {
			return ((SiemensReactorControllerBlock) controllerBlockState.getBlock()).isMultiblockFormed(controllerBlockState);
		}
		return false;
	}

	@Override
	public BlockPos getControllerBlockPos(BlockState state, World world, BlockPos pos) {
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
		SiemensReactorMultiblockPart part = state.getValue(SiemensReactorControllerBlock.SIEMENSREACTORPART);
		return pos.relative(facing, part.getDx())
				.above(-part.getDy())
				.relative(facing.getClockWise(), part.getDz());
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		BlockPos controllerPos = getControllerBlockPos(state, world, pos);
		BlockState controllerBlockState = world.getBlockState(controllerPos);
		((SiemensReactorControllerBlock) controllerBlockState.getBlock()).destroyMultiblock(controllerBlockState, world, controllerPos);
	}

}