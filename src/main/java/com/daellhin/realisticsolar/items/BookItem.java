package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.RealisticSolar;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BookItem extends Item {

    // TODO book register, functionality
    public static final String RegName = "book_item";

    public BookItem() {
	super(new Item.Properties().maxStackSize(1).group(RealisticSolar.setup.itemGroup));
	setRegistryName(RegName);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
	// TODO Auto-generated method stub
	return super.onItemUse(context);
    }

    public ActionResult<ItemStack> oItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	// TODO Auto-generated method stub
	return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}