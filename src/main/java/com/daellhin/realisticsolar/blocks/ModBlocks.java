package com.daellhin.realisticsolar.blocks;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder(RealisticSolar.MODID + ":block_aluminium")
    public static BlockAluminium BLOCKALUMINIUM;
    
    @ObjectHolder(RealisticSolar.MODID + ":custom_model")
    public static CustomModel CUSTOMMODEL;
    
    @ObjectHolder(RealisticSolar.MODID + ":block_solar_panel")
    public static BlockSolarPanel BLOCKSOLARPANEL;
    
    @ObjectHolder(RealisticSolar.MODID + ":block_solar_panel")
    public static TileEntityType<TileSolarPanel> BLOCKSOLARPANEL_TILE;
    
    @ObjectHolder(RealisticSolar.MODID + ":block_solar_panel")
    public static ContainerType<SolarPanelContainer> BLOCKSOLARPANEL_CONTAINER;
    
    
    
    

}