package com.daellhin.realisticsolar.blocks.meta;

import com.daellhin.realisticsolar.blocks.meta.enums.EnumBlockOre;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockOre extends ItemBlock{
	public ItemBlockOre(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
	}
	
	@Override 
	public int getMetadata(int meta) {
		return meta;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		EnumBlockOre en = EnumBlockOre.values()[stack.getItemDamage()];
		return "tile." +en.getName();
	}

}
