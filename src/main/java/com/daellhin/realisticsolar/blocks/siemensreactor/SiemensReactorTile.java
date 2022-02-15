package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class SiemensReactorTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {

	public SiemensReactorTile() {
		super(ModBlocks.SIEMENSREACTOR_TILE.get());
	}

	@Override
	public void tick() {
	}

	@Override
	public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new SiemensReactorContainer(i, level, worldPosition, playerInventory, playerEntity);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName()
				.getPath());
	}

}
