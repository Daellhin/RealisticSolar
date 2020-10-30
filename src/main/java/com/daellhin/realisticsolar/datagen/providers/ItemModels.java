package com.daellhin.realisticsolar.datagen.providers;

import com.daellhin.realisticsolar.datagen.providers.base.BaseItemModelProvider;
import com.daellhin.realisticsolar.fluids.ModFluids;
import com.daellhin.realisticsolar.items.ModItems;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class ItemModels extends BaseItemModelProvider {

	public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		itemGeneratedDefaultModel(ModItems.ALUMINIUM_ITEM.get());
		itemGeneratedDefaultModel(ModItems.HANDBOOK_ITEM.get());
		itemGeneratedDefaultModel(ModItems.WRENCH_ITEM.get());
		itemGeneratedDefaultModel(ModItems.METALURGICAL_SILICON_ITEM.get());
		itemGeneratedDefaultModel(ModFluids.HYROGEN_CHLORIDE_BUCKET.get());
		itemGeneratedDefaultModel(ModItems.WOODCHIP_ITEM.get());
	}

}
