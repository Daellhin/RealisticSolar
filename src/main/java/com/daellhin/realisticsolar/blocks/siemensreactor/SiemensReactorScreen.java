package com.daellhin.realisticsolar.blocks.siemensreactor;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SiemensReactorScreen extends ContainerScreen<SiemensReactorContainer> {

	public SiemensReactorScreen(SiemensReactorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	}
}
