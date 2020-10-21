package com.daellhin.realisticsolar.blocks.base;

import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class MultiblockPartBlock extends BaseBlock {

	public MultiblockPartBlock(BlockBuilder builder) {
		super(builder);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState blockState, PlayerEntity player) {
		if (!world.isRemote && isMultiblockFormed(blockState)) {
			destroyMultiblock(blockState, world, pos);
			player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Multiblock destroyed"));
		}
		super.onBlockHarvested(world, pos, blockState, player);
	}

	public abstract boolean isMultiblockFormed(BlockState blockState);

	public abstract BlockPos getControllerBlock(BlockState blockState, World world, BlockPos pos);

	public abstract void destroyMultiblock(BlockState blockState, World world, BlockPos pos);

}
