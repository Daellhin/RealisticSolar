package com.daellhin.realisticsolar;

import com.daellhin.realisticsolar.init.ModBlocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupRealisticSolar extends ItemGroup{

	public ItemGroupRealisticSolar() {
		super(Reference.MODID);

	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Item.BLOCK_TO_ITEM.get(ModBlocks.block_aluminium));
	}

}
