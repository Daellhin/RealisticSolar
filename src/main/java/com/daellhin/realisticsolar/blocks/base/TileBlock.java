package com.daellhin.realisticsolar.blocks.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileBlock extends BlockContainer{

	protected TileBlock(Material material){
		super(material);

	}

	@Override
	public TileEntity createNewTileEntity(World world, int var1){
		return null;
	}
	
}
