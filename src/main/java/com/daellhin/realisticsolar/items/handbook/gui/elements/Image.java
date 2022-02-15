package com.daellhin.realisticsolar.items.handbook.gui.elements;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Text.Alignement;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class Image {
	private String location;
	private int width;
	private int height;
	private int desiredWidth;
	private int desiredHeight = -1;
	private int x;
	private int y;
	protected Alignement alignement = Alignement.LEFT;

	public Image() {
	}

	public void draw(MatrixStack matrixStack, int relX, int relY) {
		Minecraft.getInstance()
				.getTextureManager()
				.bind(new ResourceLocation(RealisticSolar.MODID, location));

		int xOffset = 0;

		if (alignement == Alignement.RIGHT) {
			xOffset = -desiredWidth;
		} else if (alignement == Alignement.CENTER) {
			xOffset = -desiredWidth / 2;
		}

		if (desiredHeight == -1) {
			desiredHeight = (int) (height * ((double) desiredWidth / width));
		}

		blit(matrixStack, relX + x + xOffset, relY + y, desiredWidth, (int) desiredHeight, 0, 0, width, height, width, height);
	}

	private void blit(MatrixStack matrixStack, int x, int y, int desiredWidth, int desiredHeight, int textureX, int textureY, int width, int height, int textureWidth, int textureHeight) {
		AbstractGui.blit(matrixStack, x, y, desiredWidth, desiredHeight, textureX, textureY, width, height, textureWidth, textureHeight);
	}

}
