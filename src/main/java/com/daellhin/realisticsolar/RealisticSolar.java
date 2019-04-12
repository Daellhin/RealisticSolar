package com.daellhin.realisticsolar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.daellhin.realisticsolar.world.OreGeneration;

import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * @author lorin
 * 
 * ToDo:
 * 
 * multiblocks
 * forgeEnergy
 * customModels
 * GUIs
 * crafting recipie's
 * machines
 * animations
 * oreDictionary
 * 
 */
@Mod(Reference.MODID)
@Mod.EventBusSubscriber(modid = Reference.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class RealisticSolar {
	public static RealisticSolar instance;
	private static final Logger logger =LogManager.getLogger(Reference.MODID);
	
	public static final ItemGroup Item_Group = new ItemGroupRealisticSolar();
	
	
	public RealisticSolar() {
		instance = this;
				
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		OreGeneration.setupOreGeneration();
		logger.info("Setup method registerd");

	}
		
	private void clientRegistries(final FMLClientSetupEvent event) {
		logger.info("ClientRegistries method registerd");
	 
	}

}
