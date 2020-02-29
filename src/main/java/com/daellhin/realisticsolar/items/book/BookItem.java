package com.daellhin.realisticsolar.items.book;

import java.util.List;
import com.daellhin.realisticsolar.RealisticSolar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BookItem extends Item {

    public static final String RegName = "book_item";

    public BookItem() {
	super(new Item.Properties().maxStackSize(1).group(RealisticSolar.setup.itemGroup));
	setRegistryName(RegName);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	playerIn.openBook(playerIn.getHeldItem(handIn), handIn);
	Minecraft.getInstance().displayGuiScreen(new BookScreen(new StringTextComponent("Hi")));
	return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
	tooltip.add(new StringTextComponent("Handbook for RealisticSolar").applyTextStyle(TextFormatting.GRAY));
	super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}