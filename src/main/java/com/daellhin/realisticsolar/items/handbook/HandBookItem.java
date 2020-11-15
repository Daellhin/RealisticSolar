package com.daellhin.realisticsolar.items.handbook;

import com.daellhin.realisticsolar.items.base.BaseItem;
import com.daellhin.realisticsolar.tools.builders.ItemBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class HandBookItem extends BaseItem {

	public HandBookItem(ItemBuilder builder) {
		super(builder);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
		player.openBook(player.getHeldItem(hand), hand);
		Minecraft.getInstance()
				.displayGuiScreen(new HandBookScreenNew(new StringTextComponent(this.getRegistryName()
						.getPath())));
		return super.onItemRightClick(world, player, hand);
	}

}