package com.daellhin.realisticsolar.setup;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.fluids.ModFluids;
import com.daellhin.realisticsolar.items.ModItems;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RealisticSolar.MODID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RealisticSolar.MODID);
	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RealisticSolar.MODID);
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RealisticSolar.MODID);
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, RealisticSolar.MODID);

	public static void init() {
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		FLUIDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
		CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());

		ModItems.register();
		ModBlocks.register();
		ModFluids.register();
	}
}
