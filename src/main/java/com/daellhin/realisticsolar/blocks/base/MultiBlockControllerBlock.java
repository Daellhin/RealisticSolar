package com.daellhin.realisticsolar.blocks.base;

import com.daellhin.realisticsolar.items.ModItems;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
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
	 * When multiblock is unformed use behaves as regular, when formed use opens GUI. Block can be formed with wrench(never opens GUI)
	 */
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (world.isClientSide) {
			return (isMultiblockFormed(state) || player.getItemInHand(hand)
					.getItem() == ModItems.WRENCH_ITEM.get()) ? ActionResultType.SUCCESS : ActionResultType.PASS;
		} else {
			// forming
			if (player.getItemInHand(hand)
					.getItem() == ModItems.WRENCH_ITEM.get()) {
				if (isMultiblockFormed(state)) {
					player.sendMessage(new TranslationTextComponent("multiblock.already_formed").withStyle(TextFormatting.GREEN), Util.NIL_UUID);
				} else {
					if (isMultiblockValid(state, world, pos)) {
						formMultiblock(state, world, pos);
						player.sendMessage(new TranslationTextComponent("multiblock.formed").withStyle(TextFormatting.GREEN), Util.NIL_UUID);
					} else {
						player.sendMessage(new TranslationTextComponent("multiblock.unable_to_form").withStyle(TextFormatting.RED), Util.NIL_UUID);
					}
				}
				return ActionResultType.SUCCESS;
			}
			// open gui
			if (isMultiblockFormed(state)) {
				TileEntity tileEntity = world.getBlockEntity(pos);
				if (tileEntity instanceof INamedContainerProvider) {
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getBlockPos());
					return ActionResultType.SUCCESS;
				}
			}
			return ActionResultType.PASS;
		}
	}

	/**
	 * Sends message to the player that the multiblock is destroyed (destroying the multiblock is handled by onRemove)
	 */
	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, TileEntity tile, ItemStack itemStack) {
		if (!world.isClientSide && isMultiblockFormed(state)) {
			player.sendMessage(new TranslationTextComponent("multiblock.destroyed").withStyle(TextFormatting.RED), Util.NIL_UUID);
		}
		super.playerDestroy(world, player, pos, state, tile, itemStack);
	}

	/**
	 * Destroys the multiblock when block is broken(player, piston, explosion) and multiblock is formed
	 */
	@Override
	public void onRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!world.isClientSide && (state.getBlock() != newState.getBlock()) && isMultiblockFormed(state)) {
			destroyMultiblock(state, world, pos);
		}
		super.onRemove(state, world, pos, newState, isMoving);
	}

	public abstract boolean isMultiblockFormed(BlockState state);

	public abstract boolean isMultiblockValid(BlockState state, World world, BlockPos pos);

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
