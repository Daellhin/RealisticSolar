package com.daellhin.realisticsolar.blocks;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelContainer;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

	@ObjectHolder(RealisticSolar.MODID + ":aluminium_block")
	public static AluminiumBlock ALUMINIUM_BLOCK;
	
	@ObjectHolder(RealisticSolar.MODID + ":aluminium_ore_block")
	public static AluminiumOreBlock ALUMINIUMORE_BLOCK;

	@ObjectHolder(RealisticSolar.MODID + ":custom_model_block")
	public static CustomModelBlock CUSTOMMODEL_BLOCK;

	@ObjectHolder(RealisticSolar.MODID + ":solar_panel_block")
	public static SolarPanelBlock SOLARPANEL_BLOCK;

	@ObjectHolder(RealisticSolar.MODID + ":solar_panel_block")
	public static TileEntityType<SolarPanelTile> SOLARPANEL_TILE;

	@ObjectHolder(RealisticSolar.MODID + ":solar_panel_block")
	public static ContainerType<SolarPanelContainer> SOLARPANEL_CONTAINER;

}