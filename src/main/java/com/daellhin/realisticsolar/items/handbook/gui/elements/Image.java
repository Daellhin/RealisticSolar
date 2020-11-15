package com.daellhin.realisticsolar.items.handbook.gui.elements;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

public class Image {
	private String location;
	private int width;
	private int height;
	private int desiredWidth;
	private int desiredHeight;
	private int x;
	private int y;

	public void draw(int relX, int relY) {
		Minecraft.getInstance()
				.getTextureManager()
				.bindTexture(new ResourceLocation(RealisticSolar.MODID, location));

		blit(relX + x, relY + y, (int) (desiredWidth * 1.2), (int) (desiredHeight * 1.2), 0, 0, width, height, width, height);
	}

	private void blit(int x, int y, int desiredWidth, int desiredHeight, int textureX, int textureY, int width, int height, int textureWidth, int textureHeight) {
		AbstractGui.blit(x, y, desiredWidth, desiredHeight, textureX, textureY, width, height, textureWidth, textureHeight);
	}

}
