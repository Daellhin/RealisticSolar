package com.daellhin.realisticsolar.items.handbook.gui.elements;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.ITextComponent;

public class LinkButton extends Button {

	public LinkButton(int x, int y, ITextComponent text, FontRenderer font, IPressable onPress) {
		super(x, y, font.width(text), 10, text, onPress);
	}

	@Override
	public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		// needed to not show the default button texture
	}
	
	public boolean isHovered() {
		return isHovered;
	}

}
