package com.daellhin.realisticsolar.items.handbook;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

/**
 * Class for making a button to change the page, left right variant is changed b
 * 
 * @author Daellhin
 *
 */
public class PageButton extends Button {

	private final ResourceLocation resourceLocation;
	private int textureX;
	private int textureY;
	private final int buttonWidth;
	private final int buttonHeight;
	private final int xPos;
	private final int yPos;

	public PageButton(int xPos, int yPos, ResourceLocation resourceLocation, int textureX, int textureY, int buttonWidth, int buttonHeight, IPressable onPress) {
		super(xPos, yPos, buttonWidth, buttonHeight, "", onPress);
		this.xPos = xPos;
		this.yPos = yPos;
		this.resourceLocation = resourceLocation;
		this.textureX = textureX;
		this.textureY = textureY;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
	}

	@Override
	public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		Minecraft.getInstance()
				.getTextureManager()
				.bindTexture(resourceLocation);
		blit(xPos, yPos, textureX + (this.isHovered ? 18 : 0), textureY, buttonWidth, buttonHeight, 64, 64);
	}
}
