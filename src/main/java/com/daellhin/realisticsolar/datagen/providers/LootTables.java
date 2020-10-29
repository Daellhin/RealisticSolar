package com.daellhin.realisticsolar.datagen.providers;

import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.data.DataGenerator;

public class LootTables extends BaseLootTableProvider {

	public LootTables(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected void addTables() {
		addSimpleTable(ModBlocks.ALUMINIUM_BLOCK.get());
		addSimpleTableSurvesExplosion(ModBlocks.ALUMINIUMORE_BLOCK.get());
		addSimpleTableSurvesExplosion(ModBlocks.SILICASAND_BLOCK.get());
		addSimpleTable(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
		addSimpleTable(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());
		addSimpleTable(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
		addSimpleTable(ModBlocks.SOLARPANEL_BLOCK.get());
		addSimpleTable(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
	
		addAdvancedTable(ModBlocks.COALGENERATOR_BLOCK.get());
		addAdvancedTable(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
	}
}