package com.daellhin.realisticsolar.items.base;

import java.util.List;
import java.util.function.Supplier;

import com.daellhin.realisticsolar.tools.builders.ItemBuilder;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BaseGasItem extends BucketItem {

	private final boolean shiftInformation;

	public BaseGasItem(Supplier<? extends Fluid> supplier, ItemBuilder builder) {
		super(supplier, builder.getProperties());
		this.shiftInformation = builder.getShiftInformation();
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		if (shiftInformation) {
			if (Screen.hasShiftDown()) {
				list.add(new TranslationTextComponent("information." + this.getRegistryName()
						.getPath()));
			} else {
				list.add(new TranslationTextComponent("information.press_shift"));
			}
		}
	}

}