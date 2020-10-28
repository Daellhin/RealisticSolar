package com.daellhin.realisticsolar.datagen;


import java.util.function.Consumer;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraftforge.common.Tags;

public class Recipes extends RecipeProvider {

    public Recipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.ALUMINIUMORE_BLOCK_ITEM.get())
                .patternLine("xxx")
                .patternLine("x#x")
                .patternLine("xxx")
                .key('x', Tags.Items.COBBLESTONE)
                .key('#', Tags.Items.DYES_RED)
                .setGroup("realisticsolar")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
    }
}
