package com.daellhin.realisticsolar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.daellhin.realisticsolar.blocks.AluminiumBlock;
import com.daellhin.realisticsolar.blocks.AluminiumOreBlock;
import com.daellhin.realisticsolar.blocks.CustomModelBlock;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelBlock;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelContainer;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelTile;
import com.daellhin.realisticsolar.items.AluminiumItem;
import com.daellhin.realisticsolar.setup.ClientProxy;
import com.daellhin.realisticsolar.setup.IProxy;
import com.daellhin.realisticsolar.setup.ModSetup;
import com.daellhin.realisticsolar.setup.ServerProxy;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("realisticsolar")
public class RealisticSolar {

    public static final String MODID = "realisticsolar";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();

    public RealisticSolar() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
        
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("realisticsolar-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("realisticsolar-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {
        setup.init();
        proxy.init();
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().register(new AluminiumBlock());
            event.getRegistry().register(new SolarPanelBlock());
            event.getRegistry().register(new CustomModelBlock());
            event.getRegistry().register(new AluminiumOreBlock());

        }
        
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            
            //blockItems
        	event.getRegistry().register(new BlockItem(ModBlocks.ALUMINIUM_BLOCK, properties).setRegistryName("aluminium_block"));
        	event.getRegistry().register(new BlockItem(ModBlocks.SOLARPANEL_BLOCK, properties).setRegistryName("solar_panel_block"));
        	event.getRegistry().register(new BlockItem(ModBlocks.CUSTOMMODEL_BLOCK, properties).setRegistryName("custom_model_block"));
        	event.getRegistry().register(new BlockItem(ModBlocks.ALUMINIUMORE_BLOCK, properties).setRegistryName("aluminium_ore_block"));
        	
        	//items
        	event.getRegistry().register(new AluminiumItem());
        	
        }
        
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
            event.getRegistry().register(TileEntityType.Builder.create(SolarPanelTile::new, ModBlocks.SOLARPANEL_BLOCK).build(null).setRegistryName("solar_panel_block"));
        }
        
        @SubscribeEvent
        public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
            event.getRegistry().register(IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                return new SolarPanelContainer(windowId, RealisticSolar.proxy.getClientWorld(), pos, inv, RealisticSolar.proxy.getClientPlayer());
            }).setRegistryName("solar_panel_block"));
        }
    }
}