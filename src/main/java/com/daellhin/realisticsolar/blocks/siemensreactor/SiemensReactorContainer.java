package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.PlayerInventoryContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SiemensReactorContainer extends PlayerInventoryContainer {

	public SiemensReactorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
		super(ModBlocks.SIEMENSREACTOR_CONTAINER.get(), windowId);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return false;
	}

}
