package com.daellhin.realisticsolar.tile;

import cpw.mods.fml.common.registry.GameRegistry;

public class RealisticSolarTiles {
	public static void init() {
		GameRegistry.registerTileEntity(TileSolarPanel.class, "rs.tileSolarPanel");
		
	}

}