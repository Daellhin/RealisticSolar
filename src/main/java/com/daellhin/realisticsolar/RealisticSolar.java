package com.daellhin.realisticsolar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.daellhin.realisticsolar.blocks.BlockAluminium;
import com.daellhin.realisticsolar.blocks.BlockSolarPanel;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.items.ItemAluminium;
import com.daellhin.realisticsolar.setup.ClientProxy;
import com.daellhin.realisticsolar.setup.IProxy;
import com.daellhin.realisticsolar.setup.ModSetup;
import com.daellhin.realisticsolar.setup.ServerProxy;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("realisticsolar")
public class RealisticSolar {

    public static final String MODID = "realisticsolar";

    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public static ModSetup setup = new ModSetup();

    @SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger();

    public RealisticSolar() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
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
            event.getRegistry().register(new BlockAluminium());
            event.getRegistry().register(new BlockSolarPanel());

        }
        
        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            Item.Properties properties = new Item.Properties().group(setup.itemGroup);
            
        	event.getRegistry().register(new BlockItem(ModBlocks.BLOCKALUMINIUM, properties).setRegistryName("block_aluminium"));
        	event.getRegistry().register(new BlockItem(ModBlocks.BLOCKSOLARPANEL, properties).setRegistryName("block_solar_panel"));
        	event.getRegistry().register(new ItemAluminium());
        }
    }
}