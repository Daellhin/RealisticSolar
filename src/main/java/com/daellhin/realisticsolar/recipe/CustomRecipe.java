package com.daellhin.realisticsolar.recipe;

import net.minecraft.item.ItemStack;

public class CustomRecipe {

	private final ItemStack[] input;
	private final ItemStack[] output;

	public CustomRecipe(ItemStack[] input, ItemStack[] output) {
		this.input = input;
		this.output = output;
	}

	public ItemStack getInput(int index) {
		System.out.println(input[index].toString());
		return input[index];
	}

	public ItemStack getOutput(int index) {
		return output[index];
	}

	// returns the input length
	public int getInputLength() {
		return input.length;
	}
}
