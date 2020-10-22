package com.daellhin.realisticsolar.blocks.arcfurnace;

import com.daellhin.realisticsolar.blocks.arcfurnace.enums.ArcFurnaceMultiblockPart;
import com.daellhin.realisticsolar.blocks.base.MultiblockPartBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArcFurnacePartBlock extends MultiblockPartBlock {

	public ArcFurnacePartBlock() {
		super(new BlockBuilder().basicProperties());
		setDefaultState(stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH)
				.with(ArcFurnaceControllerBlock.ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(BlockStateProperties.FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING, ArcFurnaceControllerBlock.ARCFURNACEPART);
	}

	@Override
	public boolean isMultiblockFormed(BlockState state, World world, BlockPos pos) {
		BlockPos controllerPos = getControllerBlockPos(state, world, pos);
		BlockState controllerBlockState = world.getBlockState(controllerPos);
		
		if (controllerBlockState.getBlock() instanceof ArcFurnaceControllerBlock) {
			return ((ArcFurnaceControllerBlock) controllerBlockState.getBlock()).isMultiblockFormed(controllerBlockState);
		}
		return false;
	}

	@Override
	public BlockPos getControllerBlockPos(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.FACING);
		ArcFurnaceMultiblockPart part = state.get(ArcFurnaceControllerBlock.ARCFURNACEPART);
		return pos.offset(facing, part.getDx()).up(-part.getDy()).offset(facing.rotateY(), part.getDz());
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		BlockPos controllerPos = getControllerBlockPos(state, world, pos);
		BlockState controllerBlockState = world.getBlockState(controllerPos);
		((ArcFurnaceControllerBlock) controllerBlockState.getBlock()).destroyMultiblock(controllerBlockState, world, controllerPos);
	}

}
