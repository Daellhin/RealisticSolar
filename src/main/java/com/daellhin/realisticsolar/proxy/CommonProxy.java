package com.daellhin.realisticsolar.proxy;

import com.daellhin.realisticsolar.blocks.RealisticSolarBlocks;
import com.daellhin.realisticsolar.blocks.machines.RealisticSolarBlocksMachines;
import com.daellhin.realisticsolar.blocks.meta.RealisticSolarMetaBlocks;
import com.daellhin.realisticsolar.crafting.RealisticSolarCrafting;
import com.daellhin.realisticsolar.gen.OreGeneration;
import com.daellhin.realisticsolar.items.RealisticSolarItems;
import com.daellhin.realisticsolar.items.meta.RealisticSolarMetaItems;
import com.daellhin.realisticsolar.tile.RealisticSolarTiles;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
	RealisticSolarItems.init();
	RealisticSolarMetaItems.init();
	RealisticSolarTiles.init();

    }

    public void Init(FMLInitializationEvent event) {
	RealisticSolarBlocks.init();
	RealisticSolarBlocksMachines.init();
	RealisticSolarMetaBlocks.init();
	RealisticSolarCrafting.init();

    }

    public void PostInit(FMLPostInitializationEvent event) {
	GameRegistry.registerWorldGenerator(new OreGeneration(), 0);

    }

}
