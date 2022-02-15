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
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		//player.openBook(player.getItemInHand(hand), hand); TODO does this work when removed
		Minecraft.getInstance()
				.setScreen(new HandBookScreen(new StringTextComponent(this.getRegistryName()
						.getPath())));
		return super.use(world, player, hand);
	}

}