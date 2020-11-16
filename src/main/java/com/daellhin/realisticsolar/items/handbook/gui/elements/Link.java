package com.daellhin.realisticsolar.items.handbook.gui.elements;

import com.daellhin.realisticsolar.items.handbook.LinkButton;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.Button.IPressable;

public class Link extends Text {
	protected String location;
	protected LinkButton linkButton;
	protected int hoveredColor;

	public void initialize(FontRenderer font, IPressable onPress, int relX, int relY) {
		linkButton = new LinkButton(relX + x, relY + y, text, font, onPress);
	}

	@Override
	public void draw(FontRenderer font, int relX, int relY, int pageCenter) {
		font.drawString(text, relX + x, relY + y, linkButton.isHovered() ? hoveredColor : color);
	}

	public String getLocation() {
		return location;
	}

	public LinkButton getLinkButton() {
		return linkButton;
	}

}
