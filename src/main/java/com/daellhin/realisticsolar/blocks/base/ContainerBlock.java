package com.daellhin.realisticsolar.blocks.base;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerBlock extends BlockContainer {
    protected ContainerBlock(Material material) {
	super(material);

    }

    @Override
    public TileEntity createNewTileEntity(World world, int var1) {
	return null;
    }

}
