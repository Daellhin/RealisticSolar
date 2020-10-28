package com.daellhin.realisticsolar.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		System.out.println("Started Data generation");
		DataGenerator generator = event.getGenerator();

		generator.addProvider(new Recipes(generator));
		generator.addProvider(new LootTables(generator));

		if (event.includeClient()) {
			// generator.addProvider(new BlockStates(generator, event.getExistingFileHelper()));
			// generator.addProvider(new Items(generator, event.getExistingFileHelper()));
		}
	}
}