package com.daellhin.realisticsolar.init;

import com.daellhin.realisticsolar.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(Reference.MODID)

public class ModBlocks {
	public static final Block block_aluminium = null;
	public static final Block block_aluminium_ore = null;
	public static final Block block_solar_panel = null;
	public static final Block block_piramid = null;

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(
			new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.0f, 3.0f)).setRegistryName(Reference.MODID, "block_aluminium"),	
			new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0f, 3.0f)).setRegistryName(Reference.MODID, "block_aluminium_ore"),
			new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.0f, 3.0f)).setRegistryName(Reference.MODID, "block_solar_panel"),	
			new Block(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(3.0f, 3.0f)).setRegistryName(Reference.MODID, "block_piramid")	

		);
		
	}
	
	
}
