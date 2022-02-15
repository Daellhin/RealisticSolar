package com.daellhin.realisticsolar.datagen.providers;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.datagen.providers.base.BaseBlockStateProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockStates extends BaseBlockStateProvider {

	public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, exFileHelper);
	}

	@Override
	// all block models have to be created/edited manually(to much variation to make functions)
	protected void registerStatesAndModels() {
//		// blocks
//		//simpleBlockEmptyModel(ModBlocks.ALUMINIUM_BLOCK.get());
//		simpleBlock(ModBlocks.ALUMINIUMORE_BLOCK.get());
//		simpleBlock(ModBlocks.SILICASAND_BLOCK.get());
//
//		simpleBlock(ModFluids.HYDROGEN_CHLORIDE.getBlock());
//		simpleBlock(ModFluids.HYDROGEN.getBlock());
//		simpleBlock(ModFluids.CHLORIDE.getBlock());
//
//		horizontalBlockEmptyModel(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
//		horizontalBlockEmptyModel(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
//		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
//		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());
//		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get());
//		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ITEM_INPUT_PORT_BLOCK.get());
//		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK.get());
//
//		horizontalPoweredBlockEmptyModel(ModBlocks.SOLARPANEL_BLOCK.get());
//		horizontalPoweredBlockEmptyModel(ModBlocks.COALGENERATOR_BLOCK.get());
//		horizontalPoweredBlockEmptyModel(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
//		horizontalPoweredBlockEmptyModel(ModBlocks.HCL_BURNER_CONTROLLER_BLOCK.get());
//
//		// blockItems
//		simpleBlockItemDefaultModel(ModBlocks.ALUMINIUM_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.ALUMINIUMORE_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.SILICASAND_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ITEM_INPUT_PORT_BLOCK.get());
//		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK.get());
//
//		poweredBlockItemDefaultModel(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
//		poweredBlockItemDefaultModel(ModBlocks.SOLARPANEL_BLOCK.get());
//		poweredBlockItemDefaultModel(ModBlocks.COALGENERATOR_BLOCK.get());
//		poweredBlockItemDefaultModel(ModBlocks.HCL_BURNER_CONTROLLER_BLOCK.get());

	}

}