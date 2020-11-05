package com.daellhin.realisticsolar.fluids.hydrogenchloride;

import java.util.Random;

import com.daellhin.realisticsolar.fluids.ModFluids;
import com.daellhin.realisticsolar.tools.ModDamageSources;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HydrogenChlorideBlock extends FlowingFluidBlock {
	private static final Random rand = new Random();

	public HydrogenChlorideBlock() {
		super(() -> ModFluids.HYDROGEN_CHLORIDE_SOURCE.get(), Block.Properties.create(Material.WATER)
				.doesNotBlockMovement()
				.hardnessAndResistance(100.0f)
				.noDrops());
	}

	@Override
	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entityIn) {
		if (entityIn.isLiving()) {
			((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 100, 2));
			if (rand.nextFloat() < 0.04) {
				entityIn.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 0.1F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F);
			}
			entityIn.attackEntityFrom(ModDamageSources.ACID, 4.0f);

		} else {
			entityIn.playSound(SoundEvents.BLOCK_LAVA_EXTINGUISH, 0.1F + rand.nextFloat() * 0.2F, 0.9F + rand.nextFloat() * 0.15F);
			entityIn.remove();
		}
	}

}
