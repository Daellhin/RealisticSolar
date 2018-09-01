package com.daellhin.realisticsolar.blocks.machines;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.gui.GuiHandler;
import com.daellhin.realisticsolar.lib.ModInfo;
import com.daellhin.realisticsolar.tile.machines.TileApplier;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockApplier extends BlockContainer {
    public BlockApplier(Material material) {
	super(material);
	this.setBlockName("blockApplier");
	this.setBlockTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
	this.setCreativeTab(RealisticSolarCreativeTab.tabRSM);

	this.setHardness(2.0F);
	// this.setResistance();
	this.setHarvestLevel("pickaxe", 2);
	this.setLightLevel(0);
	this.setLightOpacity(16);
	this.setStepSound(Block.soundTypeMetal);

    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
	return new TileApplier(0, 0);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	FMLNetworkHandler.openGui(player, RealisticSolar.instance, GuiHandler.GUIID_APPLIER, world, x, y, z);
	return true;
    }

}
