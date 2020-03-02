package com.daellhin.realisticsolar.setup;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    public ItemGroup itemGroup = new ItemGroup("rs_itemgroup1") {

	@Override
	public ItemStack createIcon() {
	    return new ItemStack(ModBlocks.ALUMINIUM_BLOCK);
	}
    };

    public void init() {
    }

    public ItemGroup getTab() {
	return itemGroup;
    }
}
