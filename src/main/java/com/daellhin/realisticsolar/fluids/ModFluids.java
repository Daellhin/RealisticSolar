package com.daellhin.realisticsolar.fluids;

import static com.daellhin.realisticsolar.setup.Registration.BLOCKS;
import static com.daellhin.realisticsolar.setup.Registration.FLUIDS;
import static com.daellhin.realisticsolar.setup.Registration.ITEMS;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.fluids.hydrogenchloride.HydrogenChlorideBlock;
import com.daellhin.realisticsolar.setup.ModSetup;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;
import net.minecraftforge.fml.RegistryObject;;

public class ModFluids {
	// Needed to force class loading
	public static void register() {
	}

	// hydrogen chloride
	// fluid
	public static final RegistryObject<FlowingFluid> HYDROGEN_CHLORIDE_SOURCE = FLUIDS
			.register("hydrogen_chloride_source", () -> new ForgeFlowingFluid.Source(ModFluids.HYDROGEN_CHLORIDE_PROPERTIES));
	public static final RegistryObject<FlowingFluid> HYDROGEN_CHLORIDE_FLOWING = FLUIDS
			.register("hydrogen_chloride_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.HYDROGEN_CHLORIDE_PROPERTIES));

	// block
	public static final RegistryObject<FlowingFluidBlock> HYROGEN_CHLORIDE_BLOCK = BLOCKS
			.register("hydrogen_chloride_block", () -> new HydrogenChlorideBlock());

	// item
	public static final RegistryObject<Item> HYROGEN_CHLORIDE_BUCKET = ITEMS
			.register("hydrogen_chloride_bucket", () -> new BucketItem(() -> HYDROGEN_CHLORIDE_SOURCE.get(), new Item.Properties().maxStackSize(1)
					.group(ModSetup.ITEM_GROUP)));

	// properties
	public static final Properties HYDROGEN_CHLORIDE_PROPERTIES = new Properties(() -> HYDROGEN_CHLORIDE_SOURCE.get(),
			() -> HYDROGEN_CHLORIDE_FLOWING.get(),
			FluidAttributes
					.builder(new ResourceLocation(RealisticSolar.MODID, "block/hydrogen_chloride_block_source"), new ResourceLocation(
							RealisticSolar.MODID, "block/hydrogen_chloride_block_flowing"))
					.overlay(new ResourceLocation(RealisticSolar.MODID, "block/hydrogen_chloride_block_overlay")))
							.block(() -> HYROGEN_CHLORIDE_BLOCK.get())
							.bucket(() -> HYROGEN_CHLORIDE_BUCKET.get());

}