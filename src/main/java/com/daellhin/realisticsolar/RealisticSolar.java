package com.daellhin.realisticsolar;

import com.daellhin.realisticsolar.gui.GuiHandler;
import com.daellhin.realisticsolar.lib.ModInfo;
import com.daellhin.realisticsolar.proxy.CommonProxy;
import com.daellhin.realisticsolar.utils.LogHelper;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_Name, version = ModInfo.MOD_ID)
public class RealisticSolar {

    @Mod.Instance
    public static RealisticSolar instance = new RealisticSolar();

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
	proxy.preInit(event);
	LogHelper.info("PreInitialization Complete");

    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	LogHelper.info("Gui Loaded");
	proxy.Init(event);
	LogHelper.info("Initialization Complete");

    }

    @Mod.EventHandler
    public void PostInit(FMLPostInitializationEvent event) {
	proxy.PostInit(event);
	LogHelper.info("PostInitialization Complete");

    }

}
