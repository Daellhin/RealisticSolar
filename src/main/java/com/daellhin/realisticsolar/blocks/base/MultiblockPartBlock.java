package com.daellhin.realisticsolar.blocks.base;

import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

/**
 * Base for part(slave) blocks of a multiblock
 *
 */
public abstract class MultiblockPartBlock extends BaseBlock {

	public MultiblockPartBlock(BlockBuilder builder) {
		super(builder);
	}

	/**
	 * Sends message to the player that the multiblock is destroyed (destroying the multiblock is handled by onReplaced)
	 */
	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isRemote && isMultiblockFormed(state, world, pos)) {
			player.sendMessage(new TranslationTextComponent("multiblock.destroyed").applyTextStyle(TextFormatting.RED));
		}
		super.onBlockHarvested(world, pos, state, player);
	}

	/**
	 * Destroys the multiblock when block is broken(player, piston, explosion) and multiblock is formed
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!world.isRemote && (state.getBlock() != newState.getBlock()) && isMultiblockFormed(state, world, pos)) {
			destroyMultiblock(state, world, pos);
		}
		super.onReplaced(state, world, pos, newState, isMoving);
	}

	public abstract boolean isMultiblockFormed(BlockState state, World world, BlockPos pos);

	public abstract BlockPos getControllerBlockPos(BlockState state, World world, BlockPos pos);

	public abstract void destroyMultiblock(BlockState state, World world, BlockPos pos);

}
