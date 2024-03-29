package com.daellhin.realisticsolar.fluids;

import static com.daellhin.realisticsolar.setup.Registration.FLUIDS;

import com.daellhin.realisticsolar.tools.registry.FluidRegistryObject;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.item.BucketItem;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;

public class ModFluids {

	// Needed to force class loading
	public static void register() {
	}

	// hydrogen chloride
	public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> HYDROGEN_CHLORIDE = FLUIDS
			.registerAcidFluid("hydrogen_chloride");

	// hydrogen
	// gas does not work, validator in tile entity is not used when checking if fluid can be placed in block
	//public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> HYDROGEN = FLUIDS.registerGas("hydrogen", 16777215);
	public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> HYDROGEN = FLUIDS.registerDefaultFluid("hydrogen");

	
	// hydrogen chloride
	//public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> CHLORIDE = FLUIDS.registerGas("chloride", 16777045);
	public static final FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> CHLORIDE = FLUIDS.registerDefaultFluid("chloride");

}