package com.daellhin.realisticsolar.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CustomRecipeRegistry {

	private static boolean isInit = false;
	// never use directly, use getCustomRecipes
	private static List<CustomRecipe> customRecipes = new ArrayList<>();

	public static List<CustomRecipe> getCustomRecipes() {
		if (!isInit) {
			init();
			isInit = true;
		}
		return customRecipes;
	}

	@Nullable
	public static CustomRecipe getRecipe(ItemStack[] inputs) {
		for (CustomRecipe recipe : getCustomRecipes()) {
			if (inputs.length == recipe.getInputLength()) {
				boolean equal = true;
				for (int i = 0; i < inputs.length && equal; i++) {
					if (!ItemStack.areItemsEqual(inputs[i], recipe.getInput(i))) {
						equal = false;
					}
				}
				if (equal) {
					return recipe;
				}
			}
		}
		return null;
	}

	public static List<Item> getValidItems(int slot) {
		List<Item> output = new ArrayList<>();
		for (CustomRecipe customRecipe : getCustomRecipes()) {
			output.add(customRecipe.getInput(slot)
					.getItem());
		}

		return output;
	}

	public static boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		for (CustomRecipe recipe : getCustomRecipes()) {
			if (ItemStack.areItemsEqual(stack, recipe.getInput(slot))) {
				return true;
			}
		}
		return false;
	}

	// order of items corresponds with the order of the slots
	private static void init() {
		customRecipes
				.add(new CustomRecipe(new ItemStack[] { new ItemStack(ModItems.WOODCHIP_ITEM.get()), new ItemStack(ModBlocks.SILICASAND_BLOCK.get()),
						new ItemStack(Items.COAL) }, new ItemStack[] { new ItemStack(ModItems.METALURGICAL_SILICON_ITEM.get()) }));
	}
}