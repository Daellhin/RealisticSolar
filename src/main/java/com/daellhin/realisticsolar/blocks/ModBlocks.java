package com.daellhin.realisticsolar.blocks;

import static com.daellhin.realisticsolar.setup.Registration.BLOCKS;
import static com.daellhin.realisticsolar.setup.Registration.CONTAINERS;
import static com.daellhin.realisticsolar.setup.Registration.TILES;

import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceContainer;
import com.daellhin.realisticsolar.blocks.arcfurnace.blocks.ArcFurnaceControllerBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.blocks.ArcFurnacePartBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.blocks.ArcFurnacePortBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.tiles.ArcFurnaceControllerTile;
import com.daellhin.realisticsolar.blocks.arcfurnace.tiles.ArcFurnacePortTile;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorBlock;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorContainer;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorTile;
import com.daellhin.realisticsolar.blocks.fancy.FancyBlock;
import com.daellhin.realisticsolar.blocks.fancy.FancyBlockTile;
import com.daellhin.realisticsolar.blocks.hclburner.HClBurnerContainer;
import com.daellhin.realisticsolar.blocks.hclburner.HClBurnerControllerBlock;
import com.daellhin.realisticsolar.blocks.hclburner.HClBurnerTile;
import com.daellhin.realisticsolar.blocks.magic.MagicBlock;
import com.daellhin.realisticsolar.blocks.magic.MagicTile;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorContainer;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorControllerBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorPartBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorTile;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;
import com.daellhin.realisticsolar.setup.ModSetup;
import com.daellhin.realisticsolar.tools.enums.MultiblockPortType;

import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

	// Needed to force class loading
	public static void register() {
	}

	// classless
	public static final RegistryObject<Block> ALUMINIUM_BLOCK = BLOCKS.register("aluminium_block", () -> new Block(Properties.create(Material.IRON)
			.sound(SoundType.METAL)
			.hardnessAndResistance(2.0f)
			.harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> ALUMINIUMORE_BLOCK = BLOCKS
			.register("aluminium_ore_block", () -> new Block(Properties.create(Material.ROCK)
					.sound(SoundType.STONE)
					.hardnessAndResistance(2.0f)
					.harvestTool(ToolType.PICKAXE)));

	public static final RegistryObject<Block> SILICASAND_BLOCK = BLOCKS.register("silica_sand_block", () -> new FallingBlock(Properties.create(Material.SAND)
			.sound(SoundType.SAND)
			.hardnessAndResistance(2.0f)
			.harvestTool(ToolType.SHOVEL)));

	// Solar Panel
	public static final RegistryObject<Block> SOLARPANEL_BLOCK = BLOCKS.register(SolarPanelBlock.REGNAME, SolarPanelBlock::new);
	public static final RegistryObject<TileEntityType<SolarPanelTile>> SOLARPANEL_TILE = TILES
			.register(SolarPanelBlock.REGNAME, () -> TileEntityType.Builder.create(SolarPanelTile::new, SOLARPANEL_BLOCK.get())
					.build(null));

	// Coal Generator
	public static final RegistryObject<Block> COALGENERATOR_BLOCK = BLOCKS.register(CoalGeneratorBlock.REGNAME, CoalGeneratorBlock::new);
	public static final RegistryObject<TileEntityType<CoalGeneratorTile>> COALGENERATOR_TILE = TILES
			.register(CoalGeneratorBlock.REGNAME, () -> TileEntityType.Builder.create(CoalGeneratorTile::new, COALGENERATOR_BLOCK.get())
					.build(null));
	public static final RegistryObject<ContainerType<CoalGeneratorContainer>> COALGENERATOR_CONTAINER = CONTAINERS
			.register(CoalGeneratorBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
				return new CoalGeneratorContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player);
			}));

	// Arc Furnace
	// controller
	public static final RegistryObject<Block> ARCFURNACE_CONTROLLER_BLOCK = BLOCKS
			.register(ArcFurnaceControllerBlock.REGNAME, ArcFurnaceControllerBlock::new);

	// parts
	public static final RegistryObject<Block> ARCFURNACE_CASING_BLOCK = BLOCKS.register("arc_furnace_casing_block", ArcFurnacePartBlock::new);
	public static final RegistryObject<Block> ARCFURNACE_ELECTRODE_BLOCK = BLOCKS.register("arc_furnace_electrode_block", ArcFurnacePartBlock::new);

	// ports
	public static final RegistryObject<Block> ARCFURNACE_ITEM_INPUT_PORT_BLOCK = BLOCKS
			.register("arcfurnace_item_input_port_block", () -> new ArcFurnacePortBlock(MultiblockPortType.ITEM_INPUT));
	public static final RegistryObject<Block> ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK = BLOCKS
			.register("arcfurnace_item_output_port_block", () -> new ArcFurnacePortBlock(MultiblockPortType.ITEM_OUTPUT));
	public static final RegistryObject<Block> ARCFURNACE_ENERGY_INPUT_PORT_BLOCK = BLOCKS
			.register("arcfurnace_energy_input_port_block", () -> new ArcFurnacePortBlock(MultiblockPortType.ENERGY_INPUT));

	// tiles
	public static final RegistryObject<TileEntityType<ArcFurnaceControllerTile>> ARCFURNACE_CONTROLLER_TILE = TILES
			.register(ArcFurnaceControllerBlock.REGNAME, () -> TileEntityType.Builder
					.create(ArcFurnaceControllerTile::new, ARCFURNACE_CONTROLLER_BLOCK.get())
					.build(null));

	public static final RegistryObject<TileEntityType<ArcFurnacePortTile>> ARCFURNACE_PORT_TILE = TILES
			.register("arcfurnace_port", () -> TileEntityType.Builder
					.create(ArcFurnacePortTile::new, ARCFURNACE_ITEM_INPUT_PORT_BLOCK.get(), ARCFURNACE_ITEM_OUTPUT_PORT_BLOCK
							.get(), ARCFURNACE_ENERGY_INPUT_PORT_BLOCK.get())
					.build(null));

	// container
	public static final RegistryObject<ContainerType<ArcFurnaceContainer>> ARCFURNACE_CONTAINER = CONTAINERS
			.register(ArcFurnaceControllerBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
				return new ArcFurnaceContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player);
			}));

	// Siemens Reactor
	// controller
	public static final RegistryObject<Block> SIEMENSREACTOR_CONTROLLER_BLOCK = BLOCKS
			.register(SiemensReactorControllerBlock.REGNAME, SiemensReactorControllerBlock::new);

	// parts
	public static final RegistryObject<Block> SIEMENSREACTOR_TOP_BLOCK = BLOCKS.register("siemens_reactor_top_block", SiemensReactorPartBlock::new);

	// common
	public static final RegistryObject<TileEntityType<SiemensReactorTile>> SIEMENSREACTOR_TILE = TILES
			.register(SiemensReactorControllerBlock.REGNAME, () -> TileEntityType.Builder
					.create(SiemensReactorTile::new, SIEMENSREACTOR_CONTROLLER_BLOCK.get())
					.build(null));
	public static final RegistryObject<ContainerType<SiemensReactorContainer>> SIEMENSREACTOR_CONTAINER = CONTAINERS
			.register(SiemensReactorControllerBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
				return new SiemensReactorContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player);
			}));

	// HCl Burner
	public static final RegistryObject<Block> HCL_BURNER_CONTROLLER_BLOCK = BLOCKS
			.register(HClBurnerControllerBlock.REGNAME, HClBurnerControllerBlock::new);
	public static final RegistryObject<TileEntityType<HClBurnerTile>> HCL_BURNER_TILE = TILES
			.register(HClBurnerControllerBlock.REGNAME, () -> TileEntityType.Builder.create(HClBurnerTile::new, HCL_BURNER_CONTROLLER_BLOCK.get())
					.build(null));
	public static final RegistryObject<ContainerType<HClBurnerContainer>> HCL_BURNER_CONTAINER = CONTAINERS
			.register(HClBurnerControllerBlock.REGNAME, () -> IForgeContainerType.create((windowId, inv, data) -> {
				return new HClBurnerContainer(windowId, inv.player.getEntityWorld(), data.readBlockPos(), inv, inv.player);
			}));
	
	// fancyblock
    public static final RegistryObject<FancyBlock> FANCYBLOCK = BLOCKS.register("fancyblock", FancyBlock::new);
    //public static final RegistryObject<Item> FANCYBLOCK_ITEM = ITEMS.register("fancyblock", () -> new BlockItem(FANCYBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<FancyBlockTile>> FANCYBLOCK_TILE = TILES.register("fancyblock", () -> TileEntityType.Builder.create(FancyBlockTile::new, FANCYBLOCK.get()).build(null));

    // magicblock
    public static final RegistryObject<MagicBlock> MAGICBLOCK = BLOCKS.register("magicblock", MagicBlock::new);
    //public static final RegistryObject<Item> MAGICBLOCK_ITEM = ITEMS.register("magicblock", () -> new BlockItem(MAGICBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<MagicTile>> MAGICBLOCK_TILE = TILES.register("magicblock", () -> TileEntityType.Builder.create(MagicTile::new, MAGICBLOCK.get()).build(null));
	
	// generate blockItems
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> registry = event.getRegistry();
		Item.Properties properties = new Item.Properties().group(ModSetup.ITEM_GROUP);
		BLOCKS.getEntries()
				.stream()
				.map(RegistryObject::get)
				.filter(block -> !(block instanceof FlowingFluidBlock))
				.forEach(block -> {
					registry.register(new BlockItem(block, properties).setRegistryName(block.getRegistryName()));
				});

	}

}