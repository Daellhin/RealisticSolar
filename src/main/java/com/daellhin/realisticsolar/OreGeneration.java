package com.daellhin.realisticsolar;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
    
    // CountRangeConfig(Count, MinHeight, MaxHeightBase, MaxHeight)
    public static void setupOreGeneration() {
//	for (Biome biome : ForgeRegistries.BIOMES) {
//	    biome.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(Feature.ORE,
//		    new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.ALUMINIUMORE_BLOCK.getDefaultState(), 20), Placement.COUNT_RANGE, new CountRangeConfig(10, 20, 0, 100)));
//	}
    }
}
