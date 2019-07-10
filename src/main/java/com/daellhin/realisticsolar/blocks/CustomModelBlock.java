package com.daellhin.realisticsolar.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class CustomModelBlock extends Block {
	public static final String RegName = "custom_model_block";

    protected static final VoxelShape IRON_CHEST_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);	
	public CustomModelBlock() {
		super(Properties.create(Material.IRON)
		.sound(SoundType.METAL)
		.hardnessAndResistance(2.0f)
		.lightValue(0)
	);
	setRegistryName(RegName);	
		
	}
	
    @Override
    public boolean isSolid(BlockState state){
        return false;
    }
  
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext){
        return IRON_CHEST_SHAPE;
    }
    
    @Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
		if (entity != null) {
		    world.setBlockState(pos, state.with(BlockStateProperties.FACING, getFacingFromEntity(pos, entity)), 2);
		}
	}
	
    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
	    return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
	}
    
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	    builder.add(BlockStateProperties.FACING);
	}

}
