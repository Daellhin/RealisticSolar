package com.daellhin.realisticsolar.blocks.magic;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;

import static com.daellhin.realisticsolar.blocks.ModBlocks.MAGICBLOCK_TILE;

import com.daellhin.realisticsolar.Config;;

public class MagicTile extends TileEntity implements ITickableTileEntity {

	private int progress = 0;

	public MagicTile() {
		super(MAGICBLOCK_TILE.get());
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos(), getPos().add(1, 3, 1));
	}

	@Override
	public void tick() {
		if (world.isRemote) {
			progress = (progress + 1) % 126;
			markDirty();
		}
	}
	
	// progress
	public int getProgress() {
		return progress;
	}


}
