package com.daellhin.realisticsolar.init;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RealisticSolar.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(RealisticSolar.MODID)

public class ModItems {
	public static final Item item_aluminium = null;
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new Item(new Item.Properties().group(RealisticSolar.item_group)).setRegistryName("item_aluminium")	
		);
	}
	
}
