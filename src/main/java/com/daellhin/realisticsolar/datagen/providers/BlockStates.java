package com.daellhin.realisticsolar.datagen.providers;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.datagen.providers.base.BaseBlockStateProvider;
import com.daellhin.realisticsolar.fluids.ModFluids;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class BlockStates extends BaseBlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, exFileHelper);
	}

	@Override
	// all block models have to be created/edited manually(to much variation to make functions)
	protected void registerStatesAndModels() {
		// blocks
		simpleBlockEmptyModel(ModBlocks.ALUMINIUM_BLOCK.get());
		simpleBlockEmptyModel(ModBlocks.ALUMINIUMORE_BLOCK.get());
		simpleBlockEmptyModel(ModBlocks.SILICASAND_BLOCK.get());

		simpleBlockEmptyModel(ModFluids.HYDROGEN_CHLORIDE.getBlock());
		simpleBlockEmptyModel(ModFluids.HYDROGEN.getBlock());
		simpleBlockEmptyModel(ModFluids.CHLORIDE.getBlock());

		horizontalBlockEmptyModel(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ITEM_INPUT_PORT_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK.get());

		horizontalPoweredBlockEmptyModel(ModBlocks.SOLARPANEL_BLOCK.get());
		horizontalPoweredBlockEmptyModel(ModBlocks.COALGENERATOR_BLOCK.get());
		horizontalPoweredBlockEmptyModel(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
		horizontalPoweredBlockEmptyModel(ModBlocks.HCL_BURNER_CONTROLLER_BLOCK.get());

		// blockItems
		simpleBlockItemDefaultModel(ModBlocks.ALUMINIUM_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ALUMINIUMORE_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.SILICASAND_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ITEM_INPUT_PORT_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK.get());

		poweredBlockItemDefaultModel(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
		poweredBlockItemDefaultModel(ModBlocks.SOLARPANEL_BLOCK.get());
		poweredBlockItemDefaultModel(ModBlocks.COALGENERATOR_BLOCK.get());
		poweredBlockItemDefaultModel(ModBlocks.HCL_BURNER_CONTROLLER_BLOCK.get());

	}

}