package com.daellhin.realisticsolar.blocks.arcfurnace;

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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class ArcFurnaceBlock extends MachineBlock {

    public static final String RegName = "arc_furnace_block";

    public static final BooleanProperty POWERED = BooleanProperty.create("powered");
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public ArcFurnaceBlock() {
	super(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f).lightValue(14));
	setRegistryName(RegName);
	setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getLightValue(BlockState state) {
	return state.get(BlockStateProperties.POWERED) ? super.getLightValue(state) : 0;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext) {
	return SHAPE;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
	return new ArcFurnaceTile();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }

}