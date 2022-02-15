package com.daellhin.realisticsolar.blocks.solarpanel;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

public class SolarPanelBlock extends BaseBlock {

	public static final String REGNAME = "solar_panel_block";

	public SolarPanelBlock() {
		super(new BlockBuilder().basicMachineProperties()
				.tileEntitySupplier(SolarPanelTile::new));
		registerDefaultState(defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.setValue(BlockStateProperties.POWERED, false));

	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, context.getNearestLookingDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
	}
}