package com.daellhin.realisticsolar.setup;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceScreen;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorScreen;
import com.daellhin.realisticsolar.blocks.fancy.FancyModelLoader;
import com.daellhin.realisticsolar.blocks.hclburner.HClBurnerScreen;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorScreen;
import com.daellhin.realisticsolar.blocks.magic.MagicRenderer;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = RealisticSolar.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {

	public static void init(final FMLClientSetupEvent event) {
		ScreenManager.registerFactory(ModBlocks.COALGENERATOR_CONTAINER.get(), CoalGeneratorScreen::new);
		ScreenManager.registerFactory(ModBlocks.ARCFURNACE_CONTAINER.get(), ArcFurnaceScreen::new);
		ScreenManager.registerFactory(ModBlocks.SIEMENSREACTOR_CONTAINER.get(), SiemensReactorScreen::new);
		ScreenManager.registerFactory(ModBlocks.HCL_BURNER_CONTAINER.get(), HClBurnerScreen::new);
		
        MagicRenderer.register();
	}
	
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelRegistryEvent event) {
        ModelLoaderRegistry.registerLoader(new ResourceLocation(RealisticSolar.MODID, "fancyloader"), new FancyModelLoader());
    }
}
