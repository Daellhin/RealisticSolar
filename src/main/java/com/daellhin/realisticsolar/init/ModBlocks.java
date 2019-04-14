package com.daellhin.realisticsolar.init;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.Reference;
import com.daellhin.realisticsolar.blocks.BlockNull;
import com.daellhin.realisticsolar.blocks.BlockPiramid;
import com.daellhin.realisticsolar.blocks.BlockSolarPanel;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MODID)

public class ModBlocks {
	public static final BlockNull block_null = null;
	public static final Block block_aluminium = null;
	public static final Block block_aluminium_ore = null;
	public static final BlockSolarPanel block_solar_panel = null;
	public static final BlockPiramid block_piramid = null;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.0f, 3.0f)).setRegistryName(Reference.MODID, "block_aluminium"),	
			new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0f, 3.0f)).setRegistryName(Reference.MODID, "block_aluminium_ore"),
			new BlockSolarPanel(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.0f, 3.0f)).setRegistryName("block_solar_panel"),	
			new BlockPiramid(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.0f, 3.0f)).setRegistryName("block_piramid"),
			new BlockNull(Block.Properties.create(Material.WATER)).setRegistryName("block_null")

		);	
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new ItemBlock(block_aluminium, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(block_aluminium.getRegistryName()),
				new ItemBlock(block_aluminium_ore, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(block_aluminium_ore.getRegistryName()),
				new ItemBlock(block_solar_panel, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(block_solar_panel.getRegistryName()),
				new ItemBlock(block_piramid, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(block_piramid.getRegistryName()),
				new ItemBlock(block_null, new Item.Properties().group(RealisticSolar.Item_Group)).setRegistryName(block_null.getRegistryName())
			);
	}

}
