package com.daellhin.realisticsolar.setup;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.AluminiumBlock;
import com.daellhin.realisticsolar.blocks.AluminiumOreBlock;
import com.daellhin.realisticsolar.blocks.CustomModelBlock;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceBlock;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceContainer;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceTile;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorBlock;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorContainer;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorTile;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;
import com.daellhin.realisticsolar.items.AluminiumItem;

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
         event.getRegistry().registerAll(
         	new AluminiumBlock(),
            new SolarPanelBlock(),
            new CustomModelBlock(),
            new AluminiumOreBlock(),
            new ArcFurnaceBlock(),
            new CoalGeneratorBlock()
        );
     }
     
     @SubscribeEvent
     public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        Item.Properties properties = new Item.Properties().group(RealisticSolar.setup.getTab());
         
         //blockItems
     	event.getRegistry().registerAll(
 			new BlockItem(ModBlocks.ALUMINIUM_BLOCK, properties).setRegistryName(AluminiumBlock.RegName),
 			new BlockItem(ModBlocks.SOLARPANEL_BLOCK, properties).setRegistryName(SolarPanelBlock.RegName),
 			new BlockItem(ModBlocks.CUSTOMMODEL_BLOCK, properties).setRegistryName(CustomModelBlock.RegName),
 			new BlockItem(ModBlocks.ALUMINIUMORE_BLOCK, properties).setRegistryName(AluminiumOreBlock.RegName),
 			new BlockItem(ModBlocks.ARCFURNACE_BLOCK, properties).setRegistryName(ArcFurnaceBlock.RegName),
 			new BlockItem(ModBlocks.COALGENERATOR_BLOCK, properties).setRegistryName(CoalGeneratorBlock.RegName)
     	);
     	
     	//items
     	event.getRegistry().register(new AluminiumItem());
     	
     }
     
     @SubscribeEvent
     public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
         event.getRegistry().registerAll(
         	TileEntityType.Builder.create(SolarPanelTile::new, ModBlocks.SOLARPANEL_BLOCK).build(null).setRegistryName(SolarPanelBlock.RegName),
         	TileEntityType.Builder.create(ArcFurnaceTile::new, ModBlocks.ARCFURNACE_BLOCK).build(null).setRegistryName(ArcFurnaceBlock.RegName),
         	TileEntityType.Builder.create(CoalGeneratorTile::new, ModBlocks.COALGENERATOR_BLOCK).build(null).setRegistryName(CoalGeneratorBlock.RegName)
         );
     }
     
     @SubscribeEvent
     public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
         event.getRegistry().registerAll(
        		 
         	IForgeContainerType.create((windowId, inv, data) -> {
         		BlockPos pos = data.readBlockPos();
         		return new ArcFurnaceContainer(windowId, RealisticSolar.proxy.getClientWorld(), pos, inv, RealisticSolar.proxy.getClientPlayer());
         	}).setRegistryName(ArcFurnaceBlock.RegName),
         	
         	IForgeContainerType.create((windowId, inv, data) -> {
         		BlockPos pos = data.readBlockPos();
         		return new CoalGeneratorContainer(windowId, RealisticSolar.proxy.getClientWorld(), pos, inv, RealisticSolar.proxy.getClientPlayer());
         	}).setRegistryName(CoalGeneratorBlock.RegName)
         	
         );
     }
     

//     @SubscribeEvent
//     public static void registerSounds(RegistryEvent.Register<SoundEvent> sounds) {
//         ModSounds.init(sounds.getRegistry());
//     }

}
