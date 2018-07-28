package com.daellhin.realisticsolar.blocks.meta;

import com.daellhin.realisticsolar.blocks.meta.enums.EnumBlockSolarPanel;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockSolarPanel extends ItemBlock{
	public ItemBlockSolarPanel(Block block) {
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
		EnumBlockSolarPanel en = EnumBlockSolarPanel.values()[stack.getItemDamage()];
		return "tile." +en.getName();
	}

}
