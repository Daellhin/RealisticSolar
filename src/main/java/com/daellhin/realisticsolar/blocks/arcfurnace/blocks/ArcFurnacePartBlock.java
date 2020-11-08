package com.daellhin.realisticsolar.blocks.arcfurnace.blocks;

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
	
	// Use when creating ArcFurnacePart blocks
	public ArcFurnacePartBlock() {
		this(new BlockBuilder().basicProperties());
	}
	
	// Use when creating ArcFurnacePort blocks
	public ArcFurnacePartBlock(BlockBuilder builder) {
		super(builder);
		setDefaultState(stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(ArcFurnaceControllerBlock.ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState()
				.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, ArcFurnaceControllerBlock.ARCFURNACEPART);
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
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING).getOpposite();
		ArcFurnaceMultiblockPart part = state.get(ArcFurnaceControllerBlock.ARCFURNACEPART);
		return pos.offset(facing, part.getDx())
				.up(-part.getDy())
				.offset(facing.rotateY(), part.getDz());
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		BlockPos controllerPos = getControllerBlockPos(state, world, pos);
		BlockState controllerBlockState = world.getBlockState(controllerPos);
		((ArcFurnaceControllerBlock) controllerBlockState.getBlock()).destroyMultiblock(controllerBlockState, world, controllerPos);
	}

}
