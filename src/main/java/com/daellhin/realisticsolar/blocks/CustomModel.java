package com.daellhin.realisticsolar.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CustomModel extends Block {
    protected static final VoxelShape IRON_CHEST_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);	
	public CustomModel() {
		super(Properties.create(Material.IRON)
		.sound(SoundType.METAL)
		.hardnessAndResistance(2.0f)
		.lightValue(0)
	);
	setRegistryName("custom_model");	
		
	}
	
    @Override
    public boolean isSolid(BlockState state){
        return false;
    }
  
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader iBlockReader, BlockPos pos, ISelectionContext selectionContext)
    {
        return IRON_CHEST_SHAPE;
    }
    

}