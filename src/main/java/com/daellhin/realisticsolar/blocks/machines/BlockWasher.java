package com.daellhin.realisticsolar.blocks.machines;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.base.TileBlock;
import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.gui.GuiHandler;
import com.daellhin.realisticsolar.lib.ModInfo;
import com.daellhin.realisticsolar.tile.TileWasher;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWasher extends TileBlock {
	public BlockWasher (Material material) {
		super(material);
		this.setBlockName("blockWasher");
		this.setBlockTextureName(ModInfo.MOD_ID + ":" + this.getUnlocalizedName().substring(5));
		this.setCreativeTab(RealisticSolarCreativeTab.tabRSM);
		
		this.setHardness(2.0F);
		//this.setResistance();
		this.setHarvestLevel("pickaxe", 2 );
		this.setLightLevel(0);
		this.setLightOpacity(16);
		this.setStepSound(this.soundTypeMetal);
		
	}
	
	public TileEntity createNewTileEntity(World world, int var2) 
	{
		return new TileWasher();
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		FMLNetworkHandler.openGui(player, RealisticSolar.instance, GuiHandler.GUIID_WASHER, world, x, y, z);
		return true;
}

}
