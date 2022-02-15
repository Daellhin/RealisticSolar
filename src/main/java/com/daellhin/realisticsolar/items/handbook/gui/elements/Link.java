package com.daellhin.realisticsolar.items.handbook.gui.elements;

import java.net.MalformedURLException;
import java.net.URL;

import com.daellhin.realisticsolar.items.handbook.HandBookScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;

public class Link extends Text {
	protected String location;
	protected LinkFunction linkFunction = LinkFunction.INTERNAL;
	protected int hoveredColor = 43690;
	/**
	 * Handles hovering and clicking (does not render)
	 */
	protected transient LinkButton linkButton;
	
	public Link() {
		System.out.println("default ctor used");
	}

	public Link(String text, String location, LinkFunction linkFunction, Alignement alignement, float size, int wrap, int color, int hoveredColor, int x, int y) {
		System.out.println("parameterised ctor used");
		this.text = text;
		this.location = location;
		this.linkFunction = linkFunction;
		this.size = size;
		this.wrap = wrap;
		this.color = color;
		this.hoveredColor = hoveredColor;
		this.x = x;
		this.y = y;
	}

	public void initialize(HandBookScreen handBookScreen, FontRenderer font, int relX, int relY) {
		linkButton = new LinkButton(relX + x, relY + y, new StringTextComponent(text), font, (button) -> {
			if (linkFunction == LinkFunction.INTERNAL) {
				handBookScreen.changeChapter(location);
			} else {
				Minecraft.getInstance()
						.setScreen(new ConfirmOpenLinkScreen((p_213069_2_) -> {
							if (p_213069_2_) {
								try {
									Util.getPlatform()
											.openUrl(new URL(location));
								} catch (MalformedURLException e) {
									e.printStackTrace();
								}
							}

							Minecraft.getInstance()
									.setScreen(handBookScreen);
						}, location, true));
			}
		});
	}

	@Override
	public void draw(FontRenderer font, int relX, int relY, int pageCenter) {
		//font.drawString(text, relX + x, relY + y, linkButton.isHovered() ? hoveredColor : color);
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
