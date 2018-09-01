package com.daellhin.realisticsolar.blocks.meta;

import java.util.List;

import com.daellhin.realisticsolar.blocks.meta.enums.EnumBlockSolarPanel;
import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;
import com.daellhin.realisticsolar.tile.TileSolarPanel;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSolarPanel extends Block implements ITileEntityProvider {
    protected IIcon[][] icons = new IIcon[EnumBlockSolarPanel.count()][6];

    public BlockSolarPanel() {
	super(Material.iron);
	this.setBlockName("blockSolarPanel");
	this.setCreativeTab(RealisticSolarCreativeTab.tabRS);

	this.setHardness(2.0F);
	// this.setResistance();
	this.setHarvestLevel("pickaxe", 2);
	this.setLightLevel(0);
	this.setLightOpacity(16);
	this.setStepSound(Block.soundTypeMetal);

    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
	for (EnumBlockSolarPanel en : EnumBlockSolarPanel.values()) {
	    this.icons[en.getMeta()][0] = reg.registerIcon(ModInfo.MOD_ID + ":" + "blockSolarPanel2");
	    this.icons[en.getMeta()][1] = reg.registerIcon(ModInfo.MOD_ID + ":" + en.getName());

	    for (int i = 2; i < 6; i++) {
		this.icons[en.getMeta()][i] = reg.registerIcon(ModInfo.MOD_ID + ":" + "blockSolarPanel1");
	    }
	}
    }

    @Override
    public IIcon getIcon(int side, int meta) {
	if (meta >= 0 && meta < EnumBlockSolarPanel.count()) {
	    return icons[meta][side];
	}
	return null;
    }

    @Override
    public int damageDropped(int meta) {
	return meta;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	for (EnumBlockSolarPanel en : EnumBlockSolarPanel.values()) {
	    list.add(new ItemStack(item, 1, en.getMeta()));
	}
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
	int maxGen = 0;
	int buffer = 0;

	int maxGen0 = 10;
	int maxGen1 = 20;
	int maxGen2 = 50;
	int maxGen3 = 100;
	int maxGen4 = 200;

	int buffer0 = 10;
	int buffer1 = 20;
	int buffer2 = 50;
	int buffer3 = 100;
	int buffer4 = 200;

	switch (meta) {
	case 0:
	    maxGen = maxGen0;
	    buffer = buffer0;
	    break;
	case 1:
	    maxGen = maxGen1;
	    buffer = buffer1;
	    break;
	case 2:
	    maxGen = maxGen2;
	    buffer = buffer2;
	    break;
	case 3:
	    maxGen = maxGen3;
	    buffer = buffer3;
	    break;
	case 4:
	    maxGen = maxGen4;
	    buffer = buffer4;
	    break;
	}
	return new TileSolarPanel(maxGen, buffer);
    }
}
