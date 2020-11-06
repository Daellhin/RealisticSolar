package com.daellhin.realisticsolar.fluids;

import static com.daellhin.realisticsolar.setup.Registration.FLUIDS;

import com.daellhin.realisticsolar.tools.registry.FluidRegistryObject;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;

public class ModFluids {

	// Needed to force class loading
	public static void register() {
	}

	// new register test
	public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> HEAVY_WATER = FLUIDS
			.registerFluid("heavy_water", FluidAttributes.builder(new ResourceLocation("block/water_still"), new ResourceLocation("block/water_flow"))
					.color(0xFF0D1455));

	// new register test
	public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> HYDROGEN_CHLORIDE = FLUIDS
			.registerAcidFluid("hydrogen_chloride");

}