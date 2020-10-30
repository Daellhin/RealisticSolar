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
	protected void registerStatesAndModels() {
		// blocks
		simpleBlockDefaultModel(ModBlocks.ALUMINIUM_BLOCK.get());
		simpleBlockDefaultModel(ModBlocks.ALUMINIUMORE_BLOCK.get());
		simpleBlockDefaultModel(ModBlocks.SILICASAND_BLOCK.get());
		simpleBlockEmptyModel(ModFluids.HYROGEN_CHLORIDE_BLOCK.get());

		horizontalBlockEmptyModel(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
		horizontalBlockEmptyModel(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());

		horizontalPoweredBlockEmptyModel(ModBlocks.SOLARPANEL_BLOCK.get());
		horizontalPoweredBlockEmptyModel(ModBlocks.COALGENERATOR_BLOCK.get());
		horizontalPoweredBlockEmptyModel(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());

		// blockItems
		simpleBlockItemDefaultModel(ModBlocks.ALUMINIUM_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ALUMINIUMORE_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.SILICASAND_BLOCK.get());
		simpleFluidBlockItem(ModFluids.HYROGEN_CHLORIDE_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.SIEMENSREACTOR_CONTROLLER_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_CASING_BLOCK.get());
		simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_ELECTRODE_BLOCK.get());
		//simpleBlockItemDefaultModel(ModBlocks.ARCFURNACE_CONTROLLER_BLOCK.get());
		//simpleBlockItemDefaultModel(ModBlocks.SOLARPANEL_BLOCK.get());
		//simpleBlockItemDefaultModel(ModBlocks.COALGENERATOR_BLOCK.get());

	}

}