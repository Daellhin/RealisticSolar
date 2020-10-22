package com.daellhin.realisticsolar.blocks.base;

import com.daellhin.realisticsolar.items.ModItems;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Base for controller(master) block of a multiblock
 *
 */
public abstract class MultiBlockControllerBlock extends BaseBlock {

	public MultiBlockControllerBlock(BlockBuilder builder) {
		super(builder);
	}

	/**
	 * When multiblock is unformed onBlockActivated behaves as regular, when formed onBlockActivated opens GUI. Block can be formed with
	 * wrench(never opens GUI)
	 */
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isRemote) {
			return (isMultiblockFormed(state) || player.getHeldItem(hand).getItem() == ModItems.WRENCH_ITEM.get()) ? ActionResultType.SUCCESS
					: ActionResultType.PASS;
		} else {
			// forming
			if (player.getHeldItem(hand).getItem() == ModItems.WRENCH_ITEM.get()) {
				if (isMultiblockFormed(state)) {
					player.sendMessage(new TranslationTextComponent("multiblock.already_formed").applyTextStyle(TextFormatting.GREEN));
				} else {
					if (isMultiblockValid(state, world, pos)) {
						formMultiblock(state, world, pos);
						player.sendMessage(new TranslationTextComponent("multiblock.formed").applyTextStyle(TextFormatting.GREEN));
					} else {
						player.sendMessage(new TranslationTextComponent("multiblock.unable_to_form").applyTextStyle(TextFormatting.RED));
					}
				}
				return ActionResultType.SUCCESS;
			}
			// open gui
			if (isMultiblockFormed(state)) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity instanceof INamedContainerProvider) {
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
					return ActionResultType.SUCCESS;
				}
			}
			return ActionResultType.PASS;
		}
	}

	/**
	 * Sends message to the player that the multiblock is destroyed (destroying the multiblock is handled by onReplaced)
	 */
	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isRemote && isMultiblockFormed(state)) {
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
		if (!world.isRemote && (state.getBlock() != newState.getBlock()) && isMultiblockFormed(state)) {
			destroyMultiblock(state, world, pos);
		}
		super.onReplaced(state, world, pos, newState, isMoving);
	}

	public abstract boolean isMultiblockValid(BlockState state, World world, BlockPos pos);

	public abstract boolean isMultiblockFormed(BlockState state);

	public abstract void formMultiblock(BlockState state, World world, BlockPos pos);

	/**
	 * Needs to check if the blocks are still present, if false do not set blockState
	 * 
	 * @param state
	 * @param world
	 * @param pos
	 */
	public abstract void destroyMultiblock(BlockState state, World world, BlockPos pos);

}
