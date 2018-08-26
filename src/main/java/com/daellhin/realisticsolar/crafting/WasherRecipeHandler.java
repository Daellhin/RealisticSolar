package com.daellhin.realisticsolar.crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class WasherRecipeHandler {
	public static final WasherRecipeHandler INSTANCE = new WasherRecipeHandler();
	private Map<ItemStack, ItemStack> recipeList = new HashMap<ItemStack, ItemStack>();
	private Map<ItemStack, Float> experienceList = new HashMap<ItemStack, Float>();
	
	public static WasherRecipeHandler processing()
    {
        return WasherRecipeHandler.INSTANCE;
    }
	
	public WasherRecipeHandler(){}
	
	public void addBlockRecipe(Block input, ItemStack output, float xp)
	{
		this.addItemRecipe(Item.getItemFromBlock(input), output, xp);
	}
	
	public void addItemRecipe(Item input, ItemStack output, float xp)
	{
		this.addRecipeToList(new ItemStack(input, 1, 32767), output, xp);
	}
	
	public void addRecipeToList(ItemStack input, ItemStack output, float xp)
	{
		this.recipeList.put(input, output);
		this.experienceList.put(input, xp);
	}
	
	public ItemStack getProcessResult(ItemStack itemStack)
    {
        Iterator<?> iterator = this.recipeList.entrySet().iterator();
        Entry<?, ?> entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry<?, ?>)iterator.next();
        }
        while (!this.isItemDamaged(itemStack, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }
	
	public float getProcessXPResult(ItemStack itemStack_)
    {
        float ret = itemStack_.getItem().getSmeltingExperience(itemStack_);
        if (ret != -1) return ret;

        Iterator<Entry<ItemStack, Float>> iterator = this.experienceList.entrySet().iterator();
        Entry<?, ?> entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry<?, ?>)iterator.next();
        }
        while (!this.isItemDamaged(itemStack_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
	
	private boolean isItemDamaged(ItemStack input, ItemStack output)
    {
        return output.getItem() == input.getItem() && (output.getItemDamage() == 32767 || output.getItemDamage() == input.getItemDamage());
    }
	
	public Map<ItemStack, ItemStack> getRecipeList()
	{
		return this.recipeList;
	}
}
