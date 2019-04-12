package com.daellhin.realisticsolar.init;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MODID)

public class ModItems {
	public static final Item item_aluminium = null;
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				//items
				new Item(new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(Reference.MODID, "item_aluminium"),	
				
				//itemBlocks
				new ItemBlock(ModBlocks.block_aluminium, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(ModBlocks.block_aluminium.getRegistryName()),
				new ItemBlock(ModBlocks.block_aluminium_ore, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(ModBlocks.block_aluminium_ore.getRegistryName()),
				new ItemBlock(ModBlocks.block_solar_panel, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(ModBlocks.block_solar_panel.getRegistryName()),
				new ItemBlock(ModBlocks.block_piramid, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(ModBlocks.block_piramid.getRegistryName())

			);
	}
	
}
