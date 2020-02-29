package com.daellhin.realisticsolar.blocks.coalgenerator;

import javax.annotation.Nullable;
import com.daellhin.realisticsolar.blocks.base.MachineBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockReader;

public class CoalGeneratorBlock extends MachineBlock {

    public static final String RegName = "coal_generator_block";
    public static final BooleanProperty POWERED = BooleanProperty.create("powered");

    public CoalGeneratorBlock() {
	super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f).lightValue(13));
	setRegistryName(RegName);
	setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getLightValue(BlockState state) {
	return state.get(BlockStateProperties.POWERED) ? super.getLightValue(state) : 0;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	return new CoalGeneratorTile();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }

}