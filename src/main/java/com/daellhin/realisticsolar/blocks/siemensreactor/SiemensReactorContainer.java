package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.PlayerInventoryContainer;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class SiemensReactorContainer extends PlayerInventoryContainer {

	private SiemensReactorTile tileEntity;
	private PlayerEntity playerEntity;
	private IItemHandler playerInventory;

	public SiemensReactorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		super(ModBlocks.SIEMENSREACTOR_CONTAINER.get(), windowId);
		this.tileEntity = (SiemensReactorTile) world.getTileEntity(pos);
		this.playerEntity = playerEntity;
		this.playerInventory = new InvWrapper(playerInventory);
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.SIEMENSREACTOR_BOTTOM_BLOCK.get());
	}

}
