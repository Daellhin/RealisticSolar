package com.daellhin.realisticsolar.setup;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceBlock;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceContainer;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceTile;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorBlock;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorContainer;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorTile;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorBottomBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorContainer;
import com.daellhin.realisticsolar.blocks.siemensreactor.SiemensReactorTile;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;
import com.daellhin.realisticsolar.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RealisticSolar.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Registration {

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
	event.getRegistry().registerAll(ModBlocks.BLOCKS);
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
	event.getRegistry().registerAll(ModItems.ITEMS);
	
	Item.Properties properties = new Item.Properties().group(RealisticSolar.setup.getTab());
	for (Block block : ModBlocks.BLOCKS) {
	    event.getRegistry().register(new BlockItem(block, properties).setRegistryName(block.getRegistryName()));
	}
    }

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
	event.getRegistry().registerAll(
		TileEntityType.Builder.create(SolarPanelTile::new, ModBlocks.SOLARPANEL_BLOCK).build(null).setRegistryName(SolarPanelBlock.RegName),
		TileEntityType.Builder.create(ArcFurnaceTile::new, ModBlocks.ARCFURNACE_BLOCK).build(null).setRegistryName(ArcFurnaceBlock.RegName),
		TileEntityType.Builder.create(CoalGeneratorTile::new, ModBlocks.COALGENERATOR_BLOCK).build(null).setRegistryName(CoalGeneratorBlock.RegName),
		TileEntityType.Builder.create(SiemensReactorTile::new, ModBlocks.SIEMENSREACTOR_BOTTOM_BLOCK).build(null).setRegistryName(SiemensReactorBottomBlock.RegName));
    }

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
	event.getRegistry().registerAll(IForgeContainerType.create((windowId, inv, data) -> {
	    BlockPos pos = data.readBlockPos();
	    return new ArcFurnaceContainer(windowId, RealisticSolar.proxy.getClientWorld(), pos, inv, RealisticSolar.proxy.getClientPlayer());
	}).setRegistryName(ArcFurnaceBlock.RegName), IForgeContainerType.create((windowId, inv, data) -> {
	    BlockPos pos = data.readBlockPos();
	    return new CoalGeneratorContainer(windowId, RealisticSolar.proxy.getClientWorld(), pos, inv, RealisticSolar.proxy.getClientPlayer());
	}).setRegistryName(CoalGeneratorBlock.RegName), IForgeContainerType.create((windowId, inv, data) -> {
	    BlockPos pos = data.readBlockPos();
	    return new SiemensReactorContainer(windowId, RealisticSolar.proxy.getClientWorld(), pos, inv, RealisticSolar.proxy.getClientPlayer());
	}).setRegistryName(SiemensReactorBottomBlock.RegName));
    }
}
