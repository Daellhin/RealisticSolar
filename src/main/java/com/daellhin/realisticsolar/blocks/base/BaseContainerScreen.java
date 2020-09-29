package com.daellhin.realisticsolar.blocks.base;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class BaseContainerScreen<T extends Container> extends ContainerScreen<T> {

	protected ResourceLocation backgroundTexture;

	public BaseContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn, ResourceLocation backgroundTexture, int textureWidth, int textureHeight) {
		super(screenContainer, inv, titleIn);
		this.backgroundTexture = backgroundTexture;
		this.xSize = textureWidth;
		this.ySize = textureHeight;
	}

	public BaseContainerScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	/**
	 * Returns true if the given x,y coordinates are within the given rectangle
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param mouseX
	 * @param mouseY
	 */
	protected static boolean isInRect(int x, int y, int width, int height, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height));
	}

}
