package com.daellhin.realisticsolar.setup;


import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurnace.ArcFurnaceScreen;
import com.daellhin.realisticsolar.blocks.coalgenerator.CoalGeneratorScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.ARCFURNACE_CONTAINER, ArcFurnaceScreen::new);
        ScreenManager.registerFactory(ModBlocks.COALGENERATOR_CONTAINER, CoalGeneratorScreen::new);
        
    }

    @Override
    public World getClientWorld() {
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer() {
        return Minecraft.getInstance().player;
    }
}