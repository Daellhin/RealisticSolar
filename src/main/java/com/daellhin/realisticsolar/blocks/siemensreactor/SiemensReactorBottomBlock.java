package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class SiemensReactorBottomBlock extends BaseBlock {

	public static final String REGNAME = "siemens_reactor_bottom_block";
	// TODO check if block is formed +(maybe change facing according to other block)
	// public static final BooleanProperty FORMED =
	// BooleanProperty.create("formed");
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

	public SiemensReactorBottomBlock() {
		super(new BlockBuilder().basicProperties().tileEntitySupplier(SiemensReactorTile::new));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext) {
		return SHAPE;
	}

}