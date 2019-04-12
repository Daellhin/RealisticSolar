package com.daellhin.realisticsolar.world;

import com.daellhin.realisticsolar.init.ModBlocks;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.CompositeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.MinableConfig;
import net.minecraft.world.gen.placement.CountRange;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {
	public static void setupOreGeneration() {
		for(Biome biome : ForgeRegistries.BIOMES) {
			CountRangeConfig overworldOrePlacement= new CountRangeConfig(10, 16, 16, 50);
			biome.addFeature(Decoration.UNDERGROUND_ORES, new CompositeFeature<>(Feature.MINABLE, new MinableConfig(MinableConfig.IS_ROCK, ModBlocks.block_aluminium_ore.getDefaultState(), 20), new CountRange(), overworldOrePlacement));
		
		}
	}

}
