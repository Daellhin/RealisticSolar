package com.daellhin.realisticsolar.blocks;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceBlock;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceContainer;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceTile;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorBlock;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorContainer;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorTile;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;

import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {
	@ObjectHolder(RealisticSolar.MODID + ":" + AluminiumBlock.RegName)
	public static AluminiumBlock ALUMINIUM_BLOCK;
	
	@ObjectHolder(RealisticSolar.MODID + ":" + AluminiumOreBlock.RegName)
	public static AluminiumOreBlock ALUMINIUMORE_BLOCK;

	// solar panel
	@ObjectHolder(RealisticSolar.MODID + ":" + SolarPanelBlock.RegName)
	public static SolarPanelBlock SOLARPANEL_BLOCK;

	@ObjectHolder(RealisticSolar.MODID + ":" + SolarPanelBlock.RegName)
	public static TileEntityType<SolarPanelTile> SOLARPANEL_TILE;
	
	// arc furnace
	@ObjectHolder(RealisticSolar.MODID + ":" + ArcFurnaceBlock.RegName)
	public static ArcFurnaceBlock ARCFURNACE_BLOCK;
	
	@ObjectHolder(RealisticSolar.MODID + ":" + ArcFurnaceBlock.RegName)
	public static TileEntityType<ArcFurnaceTile> ARCFURNACE_TILE;

	@ObjectHolder(RealisticSolar.MODID + ":" + ArcFurnaceBlock.RegName)
	public static ContainerType<ArcFurnaceContainer> ARCFURNACE_CONTAINER;
	
	// coal generator
	@ObjectHolder(RealisticSolar.MODID + ":" + CoalGeneratorBlock.RegName)
	public static CoalGeneratorBlock COALGENERATOR_BLOCK;

	@ObjectHolder(RealisticSolar.MODID + ":" + CoalGeneratorBlock.RegName)
	public static TileEntityType<CoalGeneratorTile> COALGENERATOR_TILE;

	@ObjectHolder(RealisticSolar.MODID + ":" + CoalGeneratorBlock.RegName)
	public static ContainerType<CoalGeneratorContainer> COALGENERATOR_CONTAINER;

}