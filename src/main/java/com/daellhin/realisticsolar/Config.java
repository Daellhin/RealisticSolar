	package com.daellhin.realisticsolar;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
public class Config {

	private static final Builder COMMON_BUILDER = new Builder();
	private static final Builder CLIENT_BUILDER = new Builder();
	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;

	// category
	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_POWER = "power";
	public static final String CATEGORY_WORLDGEN = "worldgen";

	// subcategory
	public static final String SUBCATEGORY_SOLARPANEL = "solarpanel";
	public static final String SUBCATEGORY_COALGENERATOR = "coalgenerator";
	public static final String SUBCATEGORY_ARCFURNACE = "arcfurnace";
	public static final String SUBCATEGORY_ALUMINIUMORE = "aluminiumore";

	// solar panel
	public static IntValue SOLARPANEL_MAXPOWER;
	public static IntValue SOLARPANEL_GENERATE;
	public static IntValue SOLARPANEL_SEND;

	// coal generator
	public static IntValue COALGENERATOR_MAXPOWER;
	public static IntValue COALGENERATOR_GENERATE;
	public static IntValue COALGENERATOR_SEND;
	public static BooleanValue COALGENERATOR_ENABLED;// TODO remove from blocks when false

	// arc furnace
	public static IntValue ARCFURNACE_MAXPOWER;
	public static IntValue ARCFURNACE_USAGE;
	public static IntValue ARCFURNACE_RECEIVE;
	public static IntValue ARCFURNACE_TICKS;

	// aluminium ore
	public static IntValue ALUMINIUMORE_VEINSIZE;
	public static IntValue ALUMINIUMORE_CHANCE;
	public static BooleanValue ALUMINIUMORE_ENABLED;// TODO remove ore from Oregen and blocks when false

	static {
		COMMON_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
		setupGeneralConfig();
		COMMON_BUILDER.pop();
		COMMON_BUILDER.comment("Power settings").push(CATEGORY_POWER);
		setupSolarPanelConfig();
		setupCoalGeneratorConfig();
		setupArcFurnaceConfig();
		COMMON_BUILDER.pop();
		COMMON_BUILDER.comment("WorldGen settings.").push(CATEGORY_POWER);
		setupAluminiumOreConfig();
		COMMON_BUILDER.pop();
		COMMON_CONFIG = COMMON_BUILDER.build();
		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}

	private static void setupGeneralConfig() {
		COMMON_BUILDER.comment("Solar Panel settings").push(CATEGORY_GENERAL);
		COMMON_BUILDER.pop();
	}

	private static void setupSolarPanelConfig() {
		COMMON_BUILDER.comment("Solar Panel settings").push(SUBCATEGORY_SOLARPANEL);
		SOLARPANEL_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Solar Panel").defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
		SOLARPANEL_GENERATE = COMMON_BUILDER.comment("Power generation per tick").defineInRange("generate", 10, 0, Integer.MAX_VALUE);
		SOLARPANEL_SEND = COMMON_BUILDER.comment("Max power sent per tick").defineInRange("send", 100, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupCoalGeneratorConfig() {
		COMMON_BUILDER.comment("Coal Generator settings").push(SUBCATEGORY_COALGENERATOR);
		COALGENERATOR_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Coal Generator").defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
		COALGENERATOR_GENERATE = COMMON_BUILDER.comment("Power generation per tick").defineInRange("generate", 1000, 0, Integer.MAX_VALUE);
		COALGENERATOR_SEND = COMMON_BUILDER.comment("Max power sent per tick").defineInRange("send", 1000, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupArcFurnaceConfig() {
		COMMON_BUILDER.comment("Arc Furnace settings").push(SUBCATEGORY_ARCFURNACE);
		ARCFURNACE_MAXPOWER = COMMON_BUILDER.comment("Maximum power for the Arc Furnace").defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
		ARCFURNACE_USAGE = COMMON_BUILDER.comment("Power usage per tick").defineInRange("usage", 1000, 0, Integer.MAX_VALUE);
		ARCFURNACE_RECEIVE = COMMON_BUILDER.comment("Max power received per tick").defineInRange("receive", 1000, 0, Integer.MAX_VALUE);
		ARCFURNACE_TICKS = COMMON_BUILDER.comment("Ticks per recipe").defineInRange("ticks", 60, 0, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();
	}

	private static void setupAluminiumOreConfig() {
		COMMON_BUILDER.comment("Aluminium Ore settings").push(SUBCATEGORY_ALUMINIUMORE);
		ALUMINIUMORE_VEINSIZE = COMMON_BUILDER.comment("Maximum veinsize for Aluminium Ore").defineInRange("veinSize", 20, 1, 100);
		ALUMINIUMORE_CHANCE = COMMON_BUILDER.comment("Chance of Aluminium Ore generating.").defineInRange("chance", 20, 1, 100);
		COMMON_BUILDER.pop();
	}

	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
		configData.load();
		spec.setConfig(configData);
	}

	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
	}

	@SubscribeEvent
	public static void onReload(final ModConfig.Reloading configEvent) {
	}
}
