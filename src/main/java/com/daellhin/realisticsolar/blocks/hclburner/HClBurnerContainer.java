package com.daellhin.realisticsolar.blocks.hclburner;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class HClBurnerContainer extends Container {

	private HClBurnerTile tileEntity;
	private PlayerEntity playerEntity;
	private IItemHandler playerInventory;

	public HClBurnerContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
		super(ModBlocks.HCL_BURNER_CONTAINER.get(), windowId);
		tileEntity = (HClBurnerTile) world.getBlockEntity(pos);
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(playerInventory);
		syncProgress();
	}

	private void syncProgress() {
		addDataSlot(new IntReferenceHolder() {

			@Override
			public int get() {
				return tileEntity.getProgress();
			}

			@Override
			public void set(int progress) {
				tileEntity.setProgress(progress);
			}
		});
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		return stillValid(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, ModBlocks.HCL_BURNER_CONTROLLER_BLOCK.get());
	}

	public HClBurnerTile getTileEntity() {
		return tileEntity;
	}
}
