package com.daellhin.realisticsolar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockPiramid extends Block {
	private static final VoxelShape SHAPE = makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

	public BlockPiramid(Properties properties) {
		super(properties);
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public VoxelShape getShape(final IBlockState state, final IBlockReader worldIn, final BlockPos pos) {
		return SHAPE;
}

}