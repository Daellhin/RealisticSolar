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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class MultiBlockControllerBlock extends BaseBlock {

	public MultiBlockControllerBlock(BlockBuilder builder) {
		super(builder);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		if (world.isRemote) {
			return isMultiblockFormed(blockState) ? ActionResultType.SUCCESS : ActionResultType.PASS;
		} else {
			// forming
			if (player.getHeldItem(hand).getItem() == ModItems.WRENCH_ITEM.get()) {
				if (isMultiblockFormed(blockState)) {
					player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Multiblock already formed"));
				} else {
					if (isMultiblockValid(blockState, world, pos)) {
						formMultiblock(blockState, world, pos);
						player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Multiblock formed"));
					} else {
						player.sendMessage(new StringTextComponent(TextFormatting.RED + "Unable to form multiblock"));
					}
				}
				return ActionResultType.SUCCESS;
			}
			// open gui
			if (isMultiblockFormed(blockState)) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity instanceof INamedContainerProvider) {
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
					return ActionResultType.SUCCESS;
				}
			}
			return ActionResultType.PASS;
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, BlockState blockState, PlayerEntity player) {
		if (!world.isRemote && isMultiblockFormed(blockState)) {
			destroyMultiblock(blockState, world, pos);
			player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Multiblock destroyed"));
		}
		super.onBlockHarvested(world, pos, blockState, player);
	}

	public abstract boolean isMultiblockValid(BlockState blockState, World world, BlockPos pos);

	public abstract boolean isMultiblockFormed(BlockState blockState);

	public abstract void formMultiblock(BlockState blockState, World world, BlockPos pos);

	public abstract void destroyMultiblock(BlockState blockState, World world, BlockPos pos);

}
