package com.daellhin.realisticsolar.crafting.MachineRecipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.daellhin.realisticsolar.blocks.RealisticSolarBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WasherRecipes {
    public static final WasherRecipes INSTANCE = new WasherRecipes();
    private Map recipeList = new HashMap();

    public static WasherRecipes processing() {
	return INSTANCE;
    }

    public WasherRecipes() {
	this.addBlockRecipe(Blocks.sand, new ItemStack(RealisticSolarBlocks.BlockCleanSand));

    }

    public void addBlockRecipe(Block input, ItemStack output) {
	this.addItemRecipe(Item.getItemFromBlock(input), output);
    }

    public void addItemRecipe(Item input, ItemStack output) {
	this.addRecipeToList(new ItemStack(input, 1, 32767), output);
    }

    public void addRecipeToList(ItemStack input, ItemStack output) {
	this.recipeList.put(input, output);
    }

    public ItemStack getProcessResult(ItemStack itemStack) {
	Iterator<?> iterator = this.recipeList.entrySet().iterator();
	Entry<?, ?> entry;

	do {
	    if (!iterator.hasNext()) {
		return null;
	    }

	    entry = (Entry<?, ?>) iterator.next();
	} while (!this.isItemDamaged(itemStack, (ItemStack) entry.getKey()));

	return (ItemStack) entry.getValue();
    }

    private boolean isItemDamaged(ItemStack input, ItemStack output) {
	return output.getItem() == input.getItem()
		&& (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }

    public Map<ItemStack, ItemStack> getRecipeList() {
	return this.recipeList;
    }
}
