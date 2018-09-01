package com.daellhin.realisticsolar.gui;

import com.daellhin.realisticsolar.gui.container.ContainerApplier;
import com.daellhin.realisticsolar.gui.container.ContainerWasher;
import com.daellhin.realisticsolar.gui.machines.GuiApplier;
import com.daellhin.realisticsolar.gui.machines.GuiWasher;
import com.daellhin.realisticsolar.tile.machines.TileApplier;
import com.daellhin.realisticsolar.tile.machines.TileWasher;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    public static final int GUIID_WASHER = 0;
    public static final int GUIID_APPLIER = 1;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	switch (ID) {
	case GUIID_WASHER:
	    return new ContainerWasher(player.inventory, (TileWasher) world.getTileEntity(x, y, z));

	case GUIID_APPLIER:
	    return new ContainerApplier(player.inventory, (TileApplier) world.getTileEntity(x, y, z));
	}
	return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	switch (ID) {

	case GUIID_WASHER:
	    return new GuiWasher(player.inventory, (TileWasher) world.getTileEntity(x, y, z));

	case GUIID_APPLIER:
	    return new GuiApplier(player.inventory, (TileApplier) world.getTileEntity(x, y, z));

	}
	return null;
    }
}
