package com.daellhin.realisticsolar.items.handbook.gui.elements;

import com.daellhin.realisticsolar.items.handbook.HandBookScreen;
import com.daellhin.realisticsolar.items.handbook.gui.LinkButton;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.util.Util;

public class Link extends Text {
	protected String location;
	protected LinkFunction linkFunction;
	protected int hoveredColor;
	// handles hovering and clicking (does not render)
	protected transient LinkButton linkButton;

	public Link(String text, String location, LinkFunction linkFunction, Alignement alignement, float size, int wrap, int color, int hoveredColor, int x, int y) {
		this.location = location;
		this.hoveredColor = hoveredColor;
		this.linkFunction = linkFunction;
	}

	public void initialize(HandBookScreen handBookScreen, FontRenderer font, int relX, int relY) {
		linkButton = new LinkButton(relX + x, relY + y, text, font, (button) -> {
			if (linkFunction == LinkFunction.INTERNAL) {
				handBookScreen.changeChapter(location);
			} else {
				Minecraft.getInstance()
						.displayGuiScreen(new ConfirmOpenLinkScreen((p_213069_2_) -> {
							if (p_213069_2_) {
								Util.getOSType()
										.openURI(location);
							}

							Minecraft.getInstance()
									.displayGuiScreen(handBookScreen);
						}, location, true));
			}
		});
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

	public enum LinkFunction {
		INTERNAL,
		EXTERNAL
	}

}
