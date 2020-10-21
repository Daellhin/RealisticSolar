package com.daellhin.realisticsolar.blocks;

import static com.daellhin.realisticsolar.setup.Registration.BLOCKS;
import static com.daellhin.realisticsolar.setup.Registration.CONTAINERS;
import static com.daellhin.realisticsolar.setup.Registration.ITEMS;
import static com.daellhin.realisticsolar.setup.Registration.TILES;

import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceContainer;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceControllerBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnacePartBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceTile;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorBlock;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorContainer;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorTile;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorBottomBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorContainer;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorTile;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorTopBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;
import com.daellhin.realisticsolar.setup.ModSetup;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModBlocks {

	// Needed to force class loading
	public static void register() {
	}

	// common
	private final static Item.Properties basicItemProperties = new Item.Properties().group(ModSetup.ITEM_GROUP);

	// classless
	public static final RegistryObject<Block> ALUMINIUM_BLOCK = BLOCKS.register("aluminium_block", () -> new Block(Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(2.0f).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Item> ALUMINIUM_BLOCK_ITEM = ITEMS.register("aluminium_block", () -> new BlockItem(ALUMINIUM_BLOCK.get(), basicItemProperties));

	public static final RegistryObject<Block> ALUMINIUMORE_BLOCK = BLOCKS.register("aluminium_ore_block", () -> new Block(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0f).harvestTool(ToolType.PICKAXE)));
	public static final RegistryObject<Item> ALUMINIUMORE_BLOCK_ITEM = ITEMS.register("aluminium_ore_block", () -> new BlockItem(ALUMINIUMORE_BLOCK.get(), basicItemProperties));

	public static final RegistryObject<Block> SILICASAND_BLOCK = BLOCKS.register("silica_sand_block", () -> new Block(Properties.create(Material.SAND).sound(SoundType.SAND).hardnessAndResistance(2.0f).harvestTool(ToolType.SHOVEL)));
	public static final RegistryObject<Item> SILICASAND_BLOCK_ITEM = ITEMS.register("silica_sand_block", () -> new BlockItem(SILICASAND_BLOCK.get(), basicItemProperties));

	// solar panel
	public static final RegistryObject<Block> SOLARPANEL_BLOCK = BLOCKS.register(SolarPanelBlock.REGNAME, SolarPanelBlock::new);
	public static final RegistryObject<Item> SOLARPANEL_BLOCK_ITEM = ITEMS.register(SolarPanelBlock.REGNAME, () -> new BlockItem(SOLARPANEL_BLOCK.get(), basicItemProperties));
	public static final RegistryObject<TileEntityType<SolarPanelTile>> SOLARPANEL_TILE = TILES.register(SolarPanelBlock.REGNAME, () -> TileEntityType.Builder.create(SolarPanelTile::new, SOLARPANEL_BLOCK.get()).build(null));

	// arc furnace
	// controller
	public static final RegistryObject<Block> ARCFURNACE_CONTROLLER_BLOCK = BLOCKS.register(ArcFurnaceControllerBlock.REGNAME, ArcFurnaceControllerBlock::new);
	public static final RegistryObject<Item> ARCFURNACE_CONTROLLER_BLOCK_ITEM = ITEMS.register(ArcFurnaceControllerBlock.REGNAME, () -> new BlockItem(ARCFURNACE_CONTROLLER_BLOCK.get(), basicItemProperties));
	
	// parts
	public static final RegistryObject<Block> ARCFURNACE_CASING_BLOCK = BLOCKS.register("arc_furnace_casing_block", ArcFurnacePartBlock::new);
	public static final RegistryObject<Item> ARCFURNACE_CASING_BLOCK_ITEM = ITEMS.register("arc_furnace_casing_block", () -> new BlockItem(ARCFURNACE_CASING_BLOCK.get(), basicItemProperties));
	
	public static final RegistryObject<Block> ARCFURNACE_ELECTRODE_BLOCK = BLOCKS.register("arc_furnace_electrode_block", ArcFurnacePartBlock::new);
	public static final RegistryObject<Item> ARCFURNACE_ELECTRODE_BLOCK_ITEM = ITEMS.register("arc_furnace_electrode_block", () -> new BlockItem(ARCFURNACE_ELECTRODE_BLOCK.get(), basicItemProperties));
	
	// common
	public static final RegistryObject<TileEntityType<ArcFurnaceTile>> ARCFURNACE_TILE = TILES.register(ArcFurnaceControllerBlock.REGNAME, () -> TileEntityType.Builder.create(ArcFurnaceTile::new, ARCFURNACE_CONTROLLER_BLOCK.get()).build(null));
	public static final RegistryObject<ContainerType<ArcFurnaceContainer>> ARCFURNACE_CONTAINER = CONTAINERS.register(ArcFurnaceControllerBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		World world = inv.player.getEntityWorld();
		return new ArcFurnaceContainer(windowId, world, pos, inv, inv.player);
	}));

	// coal generator
	public static final RegistryObject<Block> COALGENERATOR_BLOCK = BLOCKS.register(CoalGeneratorBlock.REGNAME, CoalGeneratorBlock::new);
	public static final RegistryObject<Item> COALGENERATOR_BLOCK_ITEM = ITEMS.register(CoalGeneratorBlock.REGNAME, () -> new BlockItem(COALGENERATOR_BLOCK.get(), basicItemProperties));
	public static final RegistryObject<TileEntityType<CoalGeneratorTile>> COALGENERATOR_TILE = TILES.register(CoalGeneratorBlock.REGNAME, () -> TileEntityType.Builder.create(CoalGeneratorTile::new, COALGENERATOR_BLOCK.get()).build(null));
	public static final RegistryObject<ContainerType<CoalGeneratorContainer>> COALGENERATOR_CONTAINER = CONTAINERS.register(CoalGeneratorBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		World world = inv.player.getEntityWorld();
		return new CoalGeneratorContainer(windowId, world, pos, inv, inv.player);
	}));

	// siemens reactor
	// bottom block
	public static final RegistryObject<Block> SIEMENSREACTOR_BOTTOM_BLOCK = BLOCKS.register(SiemensReactorBottomBlock.REGNAME, SiemensReactorBottomBlock::new);
	public static final RegistryObject<Item> SIEMENSREACTOR_BOTTOM_BLOCK_ITEM = ITEMS.register(SiemensReactorBottomBlock.REGNAME, () -> new BlockItem(SIEMENSREACTOR_BOTTOM_BLOCK.get(), basicItemProperties));
	
	// top block
	public static final RegistryObject<Block> SIEMENSREACTOR_TOP_BLOCK = BLOCKS.register(SiemensReactorTopBlock.REGNAME, SiemensReactorTopBlock::new);
	public static final RegistryObject<Item> SIEMENSREACTOR_TOP_BLOCK_ITEM = ITEMS.register(SiemensReactorTopBlock.REGNAME, () -> new BlockItem(SIEMENSREACTOR_TOP_BLOCK.get(), basicItemProperties));
	
	// common
	public static final RegistryObject<TileEntityType<SiemensReactorTile>> SIEMENSREACTOR_TILE = TILES.register(SiemensReactorBottomBlock.REGNAME, () -> TileEntityType.Builder.create(SiemensReactorTile::new, SIEMENSREACTOR_BOTTOM_BLOCK.get()).build(null));
	public static final RegistryObject<ContainerType<SiemensReactorContainer>> SIEMENSREACTOR_CONTAINER = CONTAINERS.register(SiemensReactorBottomBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		World world = inv.player.getEntityWorld();
		return new SiemensReactorContainer(windowId, world, pos, inv, inv.player);
	}));

}