package com.daellhin.realisticsolar;

import com.daellhin.realisticsolar.setup.ClientSetup;
import com.daellhin.realisticsolar.setup.ModSetup;
import com.daellhin.realisticsolar.setup.Registration;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/* TODO basic multiblock structure
 * 
 * TODO JEI integration
 * TODO more fuctioning blocks
 * TODO more pages in the book
 * TODO more recipes
 * TODO multiblocks
 * TODO animations(wait for gecoLib)
 * TODO update transfer stack in slot
 * TODO update readme
 * TODO redo config files
 */

/*
 * BUG progress stops syncing when gui is closed and all items are processed 
 * BUG blocks with custom machine block model have black parts when blocks are next to them
 */
//The value here should match an entry in the META-INF/mods.toml file
@Mod("realisticsolar")
public class RealisticSolar {

	public static final String MODID = "realisticsolar";

	public RealisticSolar() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

		Registration.init();

		// Register the setup method for modloading
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
	}
}
