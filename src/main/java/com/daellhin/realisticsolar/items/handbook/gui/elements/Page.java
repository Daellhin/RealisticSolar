package com.daellhin.realisticsolar.items.handbook.gui.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.daellhin.realisticsolar.items.handbook.gui.elements.Link.LinkFunction;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Text.Alignement;

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

	public void createIndexLinks(Set<String> set) {
		List<Link> list = new ArrayList<Link>(Arrays.asList(links));
		int x = 150;
		int y = 30;

		for (String e : set) {
			list.add(new Link(e, e, LinkFunction.INTERNAL, Alignement.LEFT, 1, -1, 0, 10000, x, y));
			y += 20;
		}

		links = list.toArray(new Link[0]);
	}

}