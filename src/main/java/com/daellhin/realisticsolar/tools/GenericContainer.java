package com.daellhin.realisticsolar.tools;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;

public class GenericContainer {
	public static ContainerType<Container> createContainerType(String registryName) {
        ContainerType<Container> containerType = IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            TileEntity te = RealisticSolar.proxy.getClientWorld().getTileEntity(pos);
            if (!(te instanceof INamedContainerProvider)) {
                throw new IllegalStateException("Something went wrong getting the GUI");
            }
            return ((INamedContainerProvider) te).createMenu(windowId, inv, RealisticSolar.proxy.getClientPlayer());
        });
        containerType.setRegistryName(registryName);
        return containerType;
    }

}
