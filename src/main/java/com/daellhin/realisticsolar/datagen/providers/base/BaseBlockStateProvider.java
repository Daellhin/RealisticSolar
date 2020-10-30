package com.daellhin.realisticsolar.datagen.providers.base;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;

public abstract class BaseBlockStateProvider extends BlockStateProvider {

	protected BaseBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, RealisticSolar.MODID, exFileHelper);
	}

	// ------------------------

	public void simpleBlockDefaultModel(Block block) {
		super.simpleBlock(block, defaultModel(block.getRegistryName().getPath()));
	}

	public void simpleBlockEmptyModel(Block block) {
		super.simpleBlock(block, emptyModel(block.getRegistryName().getPath()));
	}

	public void horizontalBlockDefaultModel(Block block) {
		horizontalFacingBlock(block, defaultModel(block.getRegistryName().getPath()));
	}

	public void horizontalBlockEmptyModel(Block block) {
		horizontalFacingBlock(block, emptyModel(block.getRegistryName().getPath()));
	}

	public void horizontalPoweredBlockEmptyModel(Block block) {
		String registryName = block.getRegistryName().getPath();
		horizontalOrientePoweredBlock(block, emptyModel(registryName + "_idle"), emptyModel(registryName + "_powered"));
	}

	public void simpleBlockItemDefaultModel(Block block) {
		super.simpleBlockItem(block, defaultModel(block.getRegistryName().getPath()));
	}
	
	public void simpleFluidBlockItem(Block block) {
		super.simpleBlockItem(block, defaultModel(block.getRegistryName().getPath() + "_source"));
	}

	// -----------------------------------

	protected ModelFile defaultModel(String registryName) {
		String modelName = registryName;
		String textureName = "block/" + registryName;
		return models().cubeAll(modelName, modLoc(textureName));
	}

	protected ModelFile emptyModel(String registryName) {
		// points to an empty texture so no errors are thrown, must be replaced with a valid model file
		String modelName = registryName;
		String textureName = "block/empty";
		return models().cubeAll(modelName, modLoc(textureName));
	}

	// -----------------------------------

	public void horizontalFacingBlock(Block block, ModelFile model) {
		VariantBlockStateBuilder builder = getVariantBuilder(block);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			builder.partialState().with(BlockStateProperties.HORIZONTAL_FACING, dir).modelForState().modelFile(model)
					.rotationY((int) dir.getHorizontalAngle()).addModel();
		}
	}

	public void horizontalOrientePoweredBlock(Block block, ModelFile modelIdle, ModelFile modelPowered) {
		VariantBlockStateBuilder builder = getVariantBuilder(block);
		for (Direction dir : Direction.Plane.HORIZONTAL) {
			builder.partialState().with(BlockStateProperties.HORIZONTAL_FACING, dir).with(BlockStateProperties.POWERED, false).modelForState()
					.modelFile(modelIdle).rotationY((int) dir.getHorizontalAngle()).addModel();
			builder.partialState().with(BlockStateProperties.HORIZONTAL_FACING, dir).with(BlockStateProperties.POWERED, true).modelForState()
					.modelFile(modelPowered).rotationY((int) dir.getHorizontalAngle()).addModel();
		}
	}

}