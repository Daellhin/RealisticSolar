package com.daellhin.realisticsolar.items;

import java.util.List;

import com.daellhin.realisticsolar.tools.ItemBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BaseItem extends Item {

	private final boolean shiftInformation;

	public BaseItem(ItemBuilder builder) {
		super(builder.getProperties());
		this.shiftInformation = builder.getShiftInformation();
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		if (shiftInformation) {
			if (Screen.hasShiftDown()) {
				list.add(new TranslationTextComponent("information." + this.getRegistryName().getPath()));
			} else {
				list.add(new TranslationTextComponent("information.press_shift"));
			}
		}
	}

}
