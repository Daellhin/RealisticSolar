package com.daellhin.realisticsolar.init;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.tiles.TileEntitySolarPanel;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = RealisticSolar.MODID, bus = Bus.MOD)
@ObjectHolder(RealisticSolar.MODID)

public class ModTileEntities {
	public static TileEntityType<TileEntitySolarPanel> tile_solar_panel = null;

	@SubscribeEvent
	public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event) {
		event.getRegistry().register(tile_solar_panel = TileEntityType.register(ModBlocks.block_solar_panel.getRegistryName().toString(), TileEntityType.Builder.create(TileEntitySolarPanel::new)));
		
	}

}