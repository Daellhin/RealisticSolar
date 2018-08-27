package com.daellhin.realisticsolar.blocks.meta;

import java.util.List;

import com.daellhin.realisticsolar.blocks.meta.enums.EnumBlockOre;
import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockOre extends Block {
    protected IIcon[] icons = new IIcon[EnumBlockOre.count()];

    public BlockOre() {
	super(Material.rock);
	this.setBlockName("blockOre");
	this.setCreativeTab(RealisticSolarCreativeTab.tabRS);

	this.setHardness(2.0F);
	// this.setResistance();
	this.setHarvestLevel("pickaxe", 2);
	this.setLightLevel(0);
	this.setLightOpacity(16);
	this.setStepSound(Block.soundTypeStone);

    }

    @Override
    public void registerBlockIcons(IIconRegister reg) {
	for (EnumBlockOre en : EnumBlockOre.values()) {
	    icons[en.getMeta()] = reg.registerIcon(ModInfo.MOD_ID + ":" + en.getName());
	}
    }

    @Override
    public IIcon getIcon(int side, int meta) {
	if (meta >= 0 && meta < EnumBlockOre.count()) {
	    return icons[meta];
	}
	return null;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	for (EnumBlockOre en : EnumBlockOre.values()) {
	    list.add(new ItemStack(item, 1, en.getMeta()));
	}
    }

    @Override
    public int damageDropped(int meta) {
	return meta;
    }

}
