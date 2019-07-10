package com.daellhin.realisticsolar.setup;


import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.arcfurance.ArcFurnaceScreen;
import com.daellhin.realisticsolar.blocks.solarpanel.SolarPanelScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init() {
        ScreenManager.registerFactory(ModBlocks.SOLARPANEL_CONTAINER, SolarPanelScreen::new);
        ScreenManager.registerFactory(ModBlocks.ARCFURNACE_CONTAINER, ArcFurnaceScreen::new);
        
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