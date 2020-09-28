package com.daellhin.realisticsolar.blocks.solarpanel;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class SolarPanelBlock extends BaseBlock {

	public static final String REGNAME = "solar_panel_block";

	public SolarPanelBlock() {
		super(new BlockBuilder().basicMachineProperties().tileEntitySupplier(SolarPanelTile::new));
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
}