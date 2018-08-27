package com.daellhin.realisticsolar.tile;

import com.daellhin.realisticsolar.tile.machines.TileApplier;
import com.daellhin.realisticsolar.tile.machines.TileWasher;

import cpw.mods.fml.common.registry.GameRegistry;

public class RealisticSolarTiles {
    public static void init() {
	GameRegistry.registerTileEntity(TileSolarPanel.class, "rs.tileSolarPanel");
	GameRegistry.registerTileEntity(TileWasher.class, "rs.tilewasher");
	GameRegistry.registerTileEntity(TileApplier.class, "rs.tileApplier");

    }

}
