package com.daellhin.realisticsolar.blocks.solarpanel;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

public class SolarPanelBlock extends Block{
	public static final String RegName = "solar_panel_block";
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.values());	
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	
    public SolarPanelBlock(){
		super(Properties.create(Material.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(2.0f)
		);
		setRegistryName(RegName);	
		setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));

	}

	@Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new SolarPanelTile();
    }
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		 return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	    builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
	}
	

}