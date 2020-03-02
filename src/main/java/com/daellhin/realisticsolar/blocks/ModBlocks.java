package com.daellhin.realisticsolar.blocks;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceContainer;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceTile;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorBlock;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorContainer;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorTile;
import com.daellhin.realisticsolar.blocks.random.AluminiumBlock;
import com.daellhin.realisticsolar.blocks.random.AluminiumOreBlock;
import com.daellhin.realisticsolar.blocks.random.SilicaSandBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorBottomBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorContainer;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorTile;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorTopBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {   
    	// random
	@ObjectHolder(RealisticSolar.MODID + ":" + AluminiumBlock.RegName)
	public static AluminiumBlock ALUMINIUM_BLOCK = new AluminiumBlock();
	
	@ObjectHolder(RealisticSolar.MODID + ":" + AluminiumOreBlock.RegName)
	public static AluminiumOreBlock ALUMINIUMORE_BLOCK = new AluminiumOreBlock();
	
	@ObjectHolder(RealisticSolar.MODID + ":" + SilicaSandBlock.RegName)
	public static SilicaSandBlock SILICASAND_BLOCK = new SilicaSandBlock();

	// solar panel
	@ObjectHolder(RealisticSolar.MODID + ":" + SolarPanelBlock.RegName)
	public static SolarPanelBlock SOLARPANEL_BLOCK = new SolarPanelBlock();

	@ObjectHolder(RealisticSolar.MODID + ":" + SolarPanelBlock.RegName)
	public static TileEntityType<SolarPanelTile> SOLARPANEL_TILE;
	
	// arc furnace
	@ObjectHolder(RealisticSolar.MODID + ":" + ArcFurnaceBlock.RegName)
	public static ArcFurnaceBlock ARCFURNACE_BLOCK = new ArcFurnaceBlock();
	
	@ObjectHolder(RealisticSolar.MODID + ":" + ArcFurnaceBlock.RegName)
	public static TileEntityType<ArcFurnaceTile> ARCFURNACE_TILE;

	@ObjectHolder(RealisticSolar.MODID + ":" + ArcFurnaceBlock.RegName)
	public static ContainerType<ArcFurnaceContainer> ARCFURNACE_CONTAINER;
	
	// coal generator
	@ObjectHolder(RealisticSolar.MODID + ":" + CoalGeneratorBlock.RegName)
	public static CoalGeneratorBlock COALGENERATOR_BLOCK = new CoalGeneratorBlock();

	@ObjectHolder(RealisticSolar.MODID + ":" + CoalGeneratorBlock.RegName)
	public static TileEntityType<CoalGeneratorTile> COALGENERATOR_TILE;

	@ObjectHolder(RealisticSolar.MODID + ":" + CoalGeneratorBlock.RegName)
	public static ContainerType<CoalGeneratorContainer> COALGENERATOR_CONTAINER;
	
	// siemens reactor
	@ObjectHolder(RealisticSolar.MODID + ":" + SiemensReactorTopBlock.RegName)
	public static SiemensReactorTopBlock SIEMENSREACTOR_TOP_BLOCK = new SiemensReactorTopBlock();
	
	@ObjectHolder(RealisticSolar.MODID + ":" + SiemensReactorBottomBlock.RegName)
	public static SiemensReactorBottomBlock SIEMENSREACTOR_BOTTOM_BLOCK = new SiemensReactorBottomBlock();

	@ObjectHolder(RealisticSolar.MODID + ":" + SiemensReactorBottomBlock.RegName)
	public static TileEntityType<SiemensReactorTile> SIEMENSREACTOR_TILE;

	@ObjectHolder(RealisticSolar.MODID + ":" + SiemensReactorBottomBlock.RegName)
	public static ContainerType<SiemensReactorContainer> SIEMENSREACTOR_CONTAINER;

    	public static Block[] BLOCKS = {ALUMINIUM_BLOCK, ALUMINIUMORE_BLOCK, SOLARPANEL_BLOCK, ARCFURNACE_BLOCK, COALGENERATOR_BLOCK, SIEMENSREACTOR_TOP_BLOCK, SIEMENSREACTOR_BOTTOM_BLOCK, SILICASAND_BLOCK};
}