package com.daellhin.realisticsolar.blocks.machines;

import java.util.Random;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.gui.GuiHandler;
import com.daellhin.realisticsolar.lib.ModInfo;
import com.daellhin.realisticsolar.tile.machines.TileWasher;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWasher extends BlockContainer {
    private final Random rand = new Random();
    private static boolean keepInventory;

    public BlockWasher(Material material) {
	super(material);
	this.setBlockName("blockWasher");
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
	return new TileWasher(0, 100);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
	FMLNetworkHandler.openGui(player, RealisticSolar.instance, GuiHandler.GUIID_WASHER, world, x, y, z);
	return true;
    }

    @Override
    public void breakBlock(World worldIn, int n1, int n2, int n3, Block block, int n4) {
	if (!keepInventory) {
	    TileWasher tileWasher = (TileWasher) worldIn.getTileEntity(n1, n2, n3);

	    if (tileWasher != null) {
		for (int i1 = 0; i1 < tileWasher.getSizeInventory(); ++i1) {
		    ItemStack itemstack = tileWasher.getStackInSlot(i1);

		    if (itemstack != null) {
			float f = this.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = this.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = this.rand.nextFloat() * 0.8F + 0.1F;

			while (itemstack.stackSize > 0) {
			    int j1 = this.rand.nextInt(21) + 10;

			    if (j1 > itemstack.stackSize) {
				j1 = itemstack.stackSize;
			    }

			    itemstack.stackSize -= j1;
			    EntityItem entityitem = new EntityItem(worldIn, n1 + f, n2 + f1, n3 + f2, new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

			    if (itemstack.hasTagCompound()) {
				entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
			    }

			    float f3 = 0.05F;
			    entityitem.motionX = (float) this.rand.nextGaussian() * f3;
			    entityitem.motionY = (float) this.rand.nextGaussian() * f3 + 0.2F;
			    entityitem.motionZ = (float) this.rand.nextGaussian() * f3;
			    worldIn.spawnEntityInWorld(entityitem);
			}
		    }
		}

		worldIn.func_147453_f(n1, n2, n3, block);
	    }
	}

	super.breakBlock(worldIn, n1, n2, n3, block, n4);
    }

}
