package com.daellhin.realisticsolar.crafting.MachineRecipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.daellhin.realisticsolar.items.meta.RealisticSolarMetaItems;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ApplierRecipes {
    public static final ApplierRecipes INSTANCE = new ApplierRecipes();
    private Map recipeList = new HashMap();

    public static ApplierRecipes processing() {
	return INSTANCE;
    }

    public ApplierRecipes() {
	this.addItemRecipe(new ItemStack(RealisticSolarMetaItems.ItemSiliconWafer, 1, 5), new ItemStack(RealisticSolarMetaItems.ItemSiliconSolarCell, 1, 1));
	this.addItemRecipe(new ItemStack(RealisticSolarMetaItems.ItemSiliconWafer, 1, 6), new ItemStack(RealisticSolarMetaItems.ItemSiliconSolarCell, 1, 2));
	this.addItemRecipe(new ItemStack(RealisticSolarMetaItems.ItemSiliconWafer, 1, 7), new ItemStack(RealisticSolarMetaItems.ItemSiliconSolarCell, 1, 3));
	this.addItemRecipe(new ItemStack(RealisticSolarMetaItems.ItemSiliconWafer, 1, 8), new ItemStack(RealisticSolarMetaItems.ItemSiliconSolarCell, 1, 4));

    }

    // this.addBlockRecipe(Input, new ItemStack(Output));
    public void addBlockRecipe(Block input, ItemStack output) {
	this.addRecipeToList(new ItemStack(Item.getItemFromBlock(input), 1, 32767), output);
    }

    // this.addItemRecipe(new ItemStack(Input), new ItemStack(Output));
    public void addItemRecipe(ItemStack input, ItemStack output) {
	this.addRecipeToList(input, output);
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
	return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }

    public Map<ItemStack, ItemStack> getRecipeList() {
	return this.recipeList;
    }

}
