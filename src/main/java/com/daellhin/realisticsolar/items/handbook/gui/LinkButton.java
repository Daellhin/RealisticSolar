package com.daellhin.realisticsolar.items.handbook.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button;

public class LinkButton extends Button {

	public LinkButton(int x, int y, String text, FontRenderer font, IPressable onPress) {
		super(x, y, font.getStringWidth(text), 10, text, onPress);
	}

	@Override
	public void renderButton(int mouseX, int mouseY, float partialTicks) {
		// needed to not show the default button texture
	}
	
	public boolean isHovered() {
		return isHovered;
	}

}
