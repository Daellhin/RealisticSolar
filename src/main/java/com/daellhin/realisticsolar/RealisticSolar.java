package com.daellhin.realisticsolar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("realisticsolar")
public class RealisticSolar {
	public static RealisticSolar instance;
	public static final String modid = "realisticsolar";
	private static final Logger logger =LogManager.getLogger(modid);
	
	public RealisticSolar() {
		instance = this;
				
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);

		MinecraftForge.EVENT_BUS.register(this);
		
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		logger.info("Setup method registerd");
		
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		logger.info("ClientRegistries method registerd");
	
	}
}
