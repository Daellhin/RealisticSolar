package com.daellhin.realisticsolar.datagen.providers;

import java.util.function.Consumer;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;

public class Recipes extends RecipeProvider {

	public Recipes(DataGenerator generatorIn) {
		super(generatorIn);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
//        ShapedRecipeBuilder.shapedRecipe(ModBlocks.ALUMINIUMORE_BLOCK_ITEM.get())
//                .patternLine("xxx")
//                .patternLine("x#x")
//                .patternLine("xxx")
//                .key('x', Tags.Items.COBBLESTONE)
//                .key('#', Tags.Items.DYES_RED)
//                .setGroup("realisticsolar")
//                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
//                .build(consumer);
	}
}
