package com.daellhin.realisticsolar.blocks.siemensreactor;

import static com.daellhin.realisticsolar.blocks.ModBlocks.SIEMENSREACTOR_CONTAINER;
import com.daellhin.realisticsolar.blocks.base.PlayerInventoryContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class SiemensReactorContainer extends PlayerInventoryContainer{

    public SiemensReactorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(SIEMENSREACTOR_CONTAINER, windowId);
    }

}
