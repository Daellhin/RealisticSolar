package com.daellhin.realisticsolar.items.handbook.gui;

import com.daellhin.realisticsolar.items.handbook.gui.elements.Link;

import net.minecraft.client.gui.FontRenderer;

public class Chapter {
	Page[] pages;
	
	public void draw(int page, FontRenderer font, int relX, int relY, int pageCenter) {
		pages[page].draw(font, relX, relY, pageCenter);
	}

	public int getPageAmount() {
		return pages.length;
	}
	
	public Link[] getLinks(int page) {
		return pages[page].getLinks();
	}

}
