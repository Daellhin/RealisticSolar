package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.base.BaseContainerScreen;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class SiemensReactorScreen extends BaseContainerScreen<SiemensReactorContainer> {

	public SiemensReactorScreen(SiemensReactorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
		drawString(matrixStack, Minecraft.getInstance().font, "DEV TEST", 10, 10, 0xffffff);
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
	}
}
