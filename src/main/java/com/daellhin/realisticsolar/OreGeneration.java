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

	public static void init() {
		for (Biome biome : ForgeRegistries.BIOMES) {
			// OrefeatureConfig : vein size
			// CountrangeConfig : count, bottomOffset, topOffset, maximum
			OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(FillerBlockType.NATURAL_STONE, ModBlocks.ALUMINIUMORE_BLOCK.get().getDefaultState(), Config.ALUMINIUMORE_VEINSIZE.get());
			CountRangeConfig countRangeConfig = new CountRangeConfig(Config.ALUMINIUMORE_CHANCE.get(), 20, 0, 100);

			biome.addFeature(Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(oreFeatureConfig).withPlacement(Placement.COUNT_RANGE.configure(countRangeConfig)));
		}
	}
}
