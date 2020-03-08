package com.daellhin.realisticsolar.recipe;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.items.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class CustomRecipeRegistry {

    private static boolean isInit = false;
    private static List<CustomRecipe> customRecipeList = new ArrayList<>();

    public static List<CustomRecipe> getCustomRecipeList() {
	if (!isInit) {
	    init();
	    isInit = true;
	}
	return customRecipeList;
    }

    @Nullable
    public static CustomRecipe getRecipe(ItemStack[] inputs) {
	for (CustomRecipe recipe : getCustomRecipeList()) {
	    if (inputs.length == recipe.getILength()) {
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

    // order of items corresponds with the order of the slots
    private static void init() {
	customRecipeList.add(new CustomRecipe(new ItemStack[] { new ItemStack(ModItems.WOODCHIP_ITEM), new ItemStack(ModBlocks.SILICASAND_BLOCK), new ItemStack(Items.COAL) },
		new ItemStack[] { new ItemStack(ModItems.METALURGICAL_SILICON_ITEM) }));

    }
}