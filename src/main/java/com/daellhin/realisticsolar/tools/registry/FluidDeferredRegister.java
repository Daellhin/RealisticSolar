package com.daellhin.realisticsolar.tools.registry;

import java.util.ArrayList;
import java.util.List;

import com.daellhin.realisticsolar.fluids.base.AcidBlock;
import com.daellhin.realisticsolar.setup.ModSetup;

import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Flowing;
import net.minecraftforge.fluids.ForgeFlowingFluid.Source;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidDeferredRegister {

	private static final ResourceLocation OVERLAY = new ResourceLocation("minecraft", "block/water_overlay");

	private final List<FluidRegistryObject<?, ?, ?, ?>> allFluids = new ArrayList<>();

	private final String modid;

	private final DeferredRegister<Fluid> fluidRegister;
	private final DeferredRegister<Block> blockRegister;
	private final DeferredRegister<Item> itemRegister;

	public FluidDeferredRegister(String modid) {
		this.modid = modid;
		blockRegister = DeferredRegister.create(ForgeRegistries.BLOCKS, modid);
		fluidRegister = DeferredRegister.create(ForgeRegistries.FLUIDS, modid);
		itemRegister = DeferredRegister.create(ForgeRegistries.ITEMS, modid);
	}

	public FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> registerDefaultFluid(String name) {
		String sourceName = name + "_source";
		String flowingName = name + "_flowing";
		FluidAttributes.Builder builder = FluidAttributes
				.builder(new ResourceLocation(modid, "block/fluid/" + sourceName), new ResourceLocation(modid, "block/fluid/" + flowingName));

		return registerFluid(name, builder);
	}

	public FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> registerFluid(String name, FluidAttributes.Builder builder) {
		String sourceName = name + "_source";
		String flowingName = name + "_flowing";
		String bucketName = name + "_bucket";
		String blockName = name + "_block";

		// All fluids use block/water_overlay for being against glass as vanilla water
		builder.overlay(OVERLAY);

		// Create the registry object with dummy entries that we can use as part of the supplier but that works as use in suppliers
		FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> fluidRegistryObject = new FluidRegistryObject<>(modid, name);

		// Pass in suppliers that are wrapped instead of direct references to the registry objects, so that when we update the registry object to
		// point to a new object it gets updated properly.
		ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(fluidRegistryObject::getSource, fluidRegistryObject::getFlowing,
				builder).bucket(fluidRegistryObject::getBucket)
						.block(fluidRegistryObject::getBlock);

		// Update the references to objects that are retrieved from the deferred registers
		// Note: The block properties used are a copy of the ones for water
		fluidRegistryObject.updateSource(fluidRegister.register(sourceName, () -> new Source(properties)));
		fluidRegistryObject.updateFlowing(fluidRegister.register(flowingName, () -> new Flowing(properties)));
		fluidRegistryObject.updateBucket(itemRegister.register(bucketName, () -> new BucketItem(fluidRegistryObject::getSource,
				new Item.Properties().maxStackSize(1)
						.group(ModSetup.ITEM_GROUP)
						.containerItem(Items.BUCKET))));
		fluidRegistryObject.updateBlock(blockRegister.register(blockName, () -> new FlowingFluidBlock(fluidRegistryObject::getSource,
				Block.Properties.create(Material.WATER)
						.doesNotBlockMovement()
						.hardnessAndResistance(100.0F)
						.noDrops())));

		allFluids.add(fluidRegistryObject);
		return fluidRegistryObject;
	}

	public FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> registerAcidFluid(String name) {
		String sourceName = name + "_source";
		String flowingName = name + "_flowing";
		String bucketName = name + "_bucket";
		String blockName = name + "_block";
		FluidAttributes.Builder builder = FluidAttributes
				.builder(new ResourceLocation(modid, "block/fluid/" + sourceName), new ResourceLocation(modid, "block/fluid/" + flowingName));

		// All fluids use block/water_overlay for being against glass as vanilla water
		builder.overlay(OVERLAY);

		// Create the registry object with dummy entries that we can use as part of the supplier but that works as use in suppliers
		FluidRegistryObject<Source, Flowing, FlowingFluidBlock, BucketItem> fluidRegistryObject = new FluidRegistryObject<>(modid, name);

		// Pass in suppliers that are wrapped instead of direct references to the registry objects, so that when we update the registry object to
		// point to a new object it gets updated properly.
		ForgeFlowingFluid.Properties properties = new ForgeFlowingFluid.Properties(fluidRegistryObject::getSource, fluidRegistryObject::getFlowing,
				builder).bucket(fluidRegistryObject::getBucket)
						.block(fluidRegistryObject::getBlock);

		// Update the references to objects that are retrieved from the deferred registers
		fluidRegistryObject.updateSource(fluidRegister.register(sourceName, () -> new Source(properties)));
		fluidRegistryObject.updateFlowing(fluidRegister.register(flowingName, () -> new Flowing(properties)));
		fluidRegistryObject.updateBucket(itemRegister.register(bucketName, () -> new BucketItem(fluidRegistryObject::getSource,
				new Item.Properties().maxStackSize(1)
						.group(ModSetup.ITEM_GROUP)
						.containerItem(Items.BUCKET))));
		fluidRegistryObject.updateBlock(blockRegister.register(blockName, () -> new AcidBlock(fluidRegistryObject::getSource)));

		allFluids.add(fluidRegistryObject);
		return fluidRegistryObject;
	}

	public void register(IEventBus bus) {
		blockRegister.register(bus);
		fluidRegister.register(bus);
		itemRegister.register(bus);
	}

	public List<FluidRegistryObject<?, ?, ?, ?>> getAllFluids() {
		return allFluids;
	}
}