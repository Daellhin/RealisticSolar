package com.daellhin.realisticsolar;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigRealisticSolar {
	public static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}

	public static void register(final ModLoadingContext context) {
		context.registerConfig(ModConfig.Type.COMMON, commonSpec);
		
	}
	
	public static class Common {
		public final BooleanValue fooBar;
		public final IntValue baz;

		Common(final ForgeConfigSpec.Builder builder) {
			builder.comment("Common config settings")
					.push("common");

			fooBar = builder
					.comment("This is an example boolean property.")
					.translation("testmod3.config.common.fooBar")
					.define("fooBar", false);
			baz = builder
					.comment("This is an example int property.")
					.translation("testmod3.config.client.baz")
					.defineInRange("baz", -100, Integer.MIN_VALUE, Integer.MAX_VALUE);

			builder.pop();
		}
	}

}