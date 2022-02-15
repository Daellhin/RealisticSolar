package com.daellhin.realisticsolar.items.handbook.gui.elements;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

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

	public PageButton(int xPos, int yPos, ResourceLocation resourceLocation, int textureX, int textureY, int buttonWidth, int buttonHeight, IPressable onPress) {
		super(xPos, yPos, buttonWidth, buttonHeight, new StringTextComponent(""), onPress);
		this.resourceLocation = resourceLocation;
		this.textureX = textureX;
		this.textureY = textureY;
	}

	@Override
	public void renderButton(MatrixStack matrixStack,int mouseX, int mouseY, float partialTicks) {
		Minecraft.getInstance()
				.getTextureManager()
				.bind(resourceLocation);
		blit(matrixStack, this.x, this.y, textureX + (this.isHovered ? this.width : 0), textureY, this.width, this.height, 64, 64);
	}
}
