package com.daellhin.realisticsolar.fluids.base;

import java.util.Random;
import java.util.function.Supplier;

import com.daellhin.realisticsolar.tools.ModDamageSources;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AcidBlock extends FlowingFluidBlock {
	private static final Random rand = new Random();

	public AcidBlock(Supplier<? extends FlowingFluid> sourceFluid) {
		super(sourceFluid, Properties.of(Material.WATER)
				.noCollission()
				.strength(100.0f)
				.noDrops());
	}

	@Override
	public void entityInside(BlockState state, World world, BlockPos pos, Entity entityIn) {
		if (entityIn.isAlive()) {
			((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.POISON, 100, 2));
			if (rand.nextFloat() < 0.04) {
				entityIn.playSound(SoundEvents.LAVA_EXTINGUISH, 0.1F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F);
			}
			entityIn.hurt(ModDamageSources.ACID, 4.0f);

		} else {
			entityIn.playSound(SoundEvents.LAVA_EXTINGUISH, 0.1F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F);
			entityIn.remove();
		}
	}

}
