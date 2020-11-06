package com.daellhin.realisticsolar.tools.registry;

import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;

@ParametersAreNonnullByDefault
public class FluidRegistryObject<SOURCE extends Fluid, FLOWING extends Fluid, BLOCK extends FlowingFluidBlock, BUCKET extends BucketItem>  {

    private RegistryObject<SOURCE> sourceRO;
    private RegistryObject<FLOWING> flowingRO;
    private RegistryObject<BLOCK> blockRO;
    private RegistryObject<BUCKET> bucketRO;

    public FluidRegistryObject(String modid, String name) {
        this.sourceRO = RegistryObject.of(new ResourceLocation(modid, name + "_source"), ForgeRegistries.FLUIDS);
        this.flowingRO = RegistryObject.of(new ResourceLocation(modid, name + "_flowing"), ForgeRegistries.FLUIDS);	
        this.blockRO = RegistryObject.of(new ResourceLocation(modid, name + "_block"), ForgeRegistries.BLOCKS);
        this.bucketRO = RegistryObject.of(new ResourceLocation(modid, name + "_bucket"), ForgeRegistries.ITEMS);
    }

    public SOURCE getSource() {
        return sourceRO.get();
    }

    public FLOWING getFlowing() {
        return flowingRO.get();
    }

    public BLOCK getBlock() {
        return blockRO.get();
    }

    public BUCKET getBucket() {
        return bucketRO.get();
    }

    void updateSource(RegistryObject<SOURCE> sourceRO) {
        this.sourceRO = Objects.requireNonNull(sourceRO);
    }

    void updateFlowing(RegistryObject<FLOWING> flowingRO) {
        this.flowingRO = Objects.requireNonNull(flowingRO);
    }

    void updateBlock(RegistryObject<BLOCK> blockRO) {
        this.blockRO = Objects.requireNonNull(blockRO);
    }

    void updateBucket(RegistryObject<BUCKET> bucketRO) {
        this.bucketRO = Objects.requireNonNull(bucketRO);
    }

}
