package com.daellhin.realisticsolar;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";
    public static final String SUBCATEGORY_SOLARPANEL = "solarpanel";
    public static final String SUBCATEGORY_COALGENERATOR = "coalgenerator";
    public static final String SUBCATEGORY_ARCFURNACE = "arcfurnace";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.IntValue SOLARPANEL_MAXPOWER;
    public static ForgeConfigSpec.IntValue SOLARPANEL_GENERATE;
    public static ForgeConfigSpec.IntValue SOLARPANEL_SEND;
    
    public static ForgeConfigSpec.BooleanValue COALGENERATOR_ENABLED;
    public static ForgeConfigSpec.IntValue COALGENERATOR_MAXPOWER;
    public static ForgeConfigSpec.IntValue COALGENERATOR_GENERATE;
    public static ForgeConfigSpec.IntValue COALGENERATOR_SEND;
    public static ForgeConfigSpec.IntValue COALGENERATOR_TICKS;
    
    public static ForgeConfigSpec.IntValue ARCFURNACE_MAXPOWER;
    public static ForgeConfigSpec.IntValue ARCFURNACE_USAGE;
    public static ForgeConfigSpec.IntValue COALGENERATOR_RECEIVE;
    public static ForgeConfigSpec.IntValue ARCFURNACE_TICKS;
    
    public static ForgeConfigSpec.IntValue ALUMINIUMORE_CHANCE;
    public static ForgeConfigSpec.BooleanValue WORLDGEN_ENABLED;
    
    static {

        COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        
        setupGeneralConfig();
        
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Power settings").push(CATEGORY_POWER);

        setupSolarPanelConfig();
        setupCoalGeneratorConfig();  
        setupArcFurnaceConfig();  

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
        
    }
    
    private static void setupGeneralConfig() {
        COMMON_BUILDER.comment("SolarPanel settings").push(CATEGORY_GENERAL);


        COMMON_BUILDER.pop();
    }

    private static void setupSolarPanelConfig() {
        COMMON_BUILDER.comment("SolarPanel settings").push(SUBCATEGORY_SOLARPANEL);

        SOLARPANEL_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Solar Panel")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        SOLARPANEL_GENERATE = COMMON_BUILDER.comment("Power generation per tick")
                .defineInRange("generate", 10, 0, Integer.MAX_VALUE);
        SOLARPANEL_SEND = COMMON_BUILDER.comment("Max power sent per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    private static void setupCoalGeneratorConfig() {
        COMMON_BUILDER.comment("Coal Generator settings").push(SUBCATEGORY_COALGENERATOR);

        COALGENERATOR_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Coal Generator")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        COALGENERATOR_GENERATE = COMMON_BUILDER.comment("Power generation per tick")
                .defineInRange("generate", 1000, 0, Integer.MAX_VALUE);
        COALGENERATOR_SEND = COMMON_BUILDER.comment("Max power sent per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);
        COALGENERATOR_TICKS = COMMON_BUILDER.comment("Ticks per piece of coal")
                .defineInRange("ticks", 60, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    
    private static void setupArcFurnaceConfig() {
        COMMON_BUILDER.comment("Arc Furnace settings").push(SUBCATEGORY_ARCFURNACE);

        ARCFURNACE_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Arc Furnace")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        ARCFURNACE_USAGE = COMMON_BUILDER.comment("Power usage per tick")
                .defineInRange("generate", 1000, 0, Integer.MAX_VALUE);
        COALGENERATOR_RECEIVE = COMMON_BUILDER.comment("Max power received per tick")
                .defineInRange("receive", 100, 0, Integer.MAX_VALUE);
        ARCFURNACE_TICKS = COMMON_BUILDER.comment("Ticks per recipe")
                .defineInRange("ticks", 60, 0, Integer.MAX_VALUE);

        COMMON_BUILDER.pop();
    }
    


    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.ConfigReloading configEvent) {
    }

}
