package com.daellhin.realisticsolar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.daellhin.realisticsolar.world.OreGeneration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/*TODO:
	multiblocks,
	models,
	animations,
	solar,
	custom machines and crafting,
	GUI's,
	config,
	...
*/
@Mod(RealisticSolar.MODID)
@Mod.EventBusSubscriber(modid = RealisticSolar.MODID, bus = Bus.MOD)

public class RealisticSolar {
	public static RealisticSolar INSTANCE;
	public static final String MODID = "realisticsolar";
	private static final Logger logger =LogManager.getLogger(MODID);
	
	public static final ItemGroupRealisticSolar item_group = new ItemGroupRealisticSolar();

	public RealisticSolar() {
		INSTANCE = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		ConfigRealisticSolar.register(ModLoadingContext.get());
		
	}
	
	private void commonSetup(final FMLCommonSetupEvent event) {
		OreGeneration.setupOreGeneration();
		logger.info("Setup method registerd");

	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		logger.info("ClientRegistries method registerd");
	 
	}

}