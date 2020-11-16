package com.daellhin.realisticsolar.items.handbook.gui;

import com.daellhin.realisticsolar.items.handbook.gui.elements.Image;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Link;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Text;

import net.minecraft.client.gui.FontRenderer;

public class Page {
	protected Image[] images;
	protected Text[] text;
	protected Link[] links;

	public void draw(FontRenderer font, int relX, int relY, int pageCenter) {
		for (Image image : images) {
			image.draw(relX, relY);
		}
		for (Text text : this.text) {
			text.draw(font, relX, relY, pageCenter);
		}
		for (Link link : this.links) {
			link.draw(font, relX, relY, pageCenter);
		}
	}

	public Link[] getLinks() {
		return links;
	}

}