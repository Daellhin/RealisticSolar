package com.daellhin.realisticsolar.datagen.providers.base;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public abstract class BaseItemModelProvider extends ItemModelProvider {
	public BaseItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, RealisticSolar.MODID, existingFileHelper);
	}

	public void itemGeneratedDefaultModel(Item item) {
		getBuilder(item.getRegistryName().getPath()).parent(getExistingFile(mcLoc("item/generated")))
				.texture("layer0", "item/" + item.getRegistryName().getPath());
	}

}
