package com.daellhin.realisticsolar.util;

import java.util.Random;

import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.World;

public class Utils {
    /**
     * Drop an itemstack as entity
     */
    public static final void dropItemIntoWorld(World world, Vector3i pos, ItemStack item) {
        Random rand = new Random();

        if (item != null && item.getCount() > 0) {
            float rx = rand.nextFloat() * 0.8F + 0.1F;
            float ry = rand.nextFloat() * 0.8F + 0.1F;
            float rz = rand.nextFloat() * 0.8F + 0.1F;

            ItemEntity entityItem = new ItemEntity(world,
                    pos.getX() + rx, pos.getY() + ry, pos.getZ() + rz,
                    item.copy());

            if (item.hasTag()) {
                entityItem.getItem().setTag(item.getTag().copy());
            }

            float factor = 0.05F;
            entityItem.setDeltaMovement(
            		rand.nextGaussian() * factor, 
            		rand.nextGaussian() * factor + 0.2F,
            		rand.nextGaussian() * factor);
            world.addFreshEntity(entityItem);
            item.setCount(0);
        }
    }

}
