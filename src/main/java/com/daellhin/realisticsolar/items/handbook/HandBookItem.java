package com.daellhin.realisticsolar.items.handbook;

import java.util.List;

import com.daellhin.realisticsolar.setup.ModSetup;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class HandBookItem extends Item {

	public static final String REGNAME = "hand_book_item";

	public HandBookItem() {
		super(new Item.Properties().maxStackSize(1).group(ModSetup.ITEM_GROUP));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.openBook(playerIn.getHeldItem(handIn), handIn);
		Minecraft.getInstance().displayGuiScreen(new HandBookScreen(new StringTextComponent(REGNAME)));
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		list.add(new TranslationTextComponent("item.information." + REGNAME));
		super.addInformation(stack, world, list, flag);
	}
}