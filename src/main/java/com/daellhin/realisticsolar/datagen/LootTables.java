package com.daellhin.realisticsolar.datagen;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		lootTables.put(ModBlocks.ALUMINIUM_BLOCK.get(), createStandardTable("firstblock", ModBlocks.ALUMINIUM_BLOCK.get()));
		// lootTables.put(Registration.FANCYBLOCK.get(), createStandardTable("fancyblock", Registration.FANCYBLOCK.get()));
	}
}