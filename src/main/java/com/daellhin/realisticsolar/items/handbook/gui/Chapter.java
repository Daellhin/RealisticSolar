package com.daellhin.realisticsolar.items.handbook.gui;

import java.util.Set;

import com.daellhin.realisticsolar.items.handbook.gui.elements.Link;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Page;

import net.minecraft.client.gui.FontRenderer;

public class Chapter {
	Page[] pages;
	private transient boolean indexChapter = false;
	
	public Chapter createIndexChapter(Set<String> set) {
		pages[0].createIndexLinks(set);
		indexChapter = true;
		return this;
	}
	
	public void draw(int page, FontRenderer font, int relX, int relY, int pageCenter) {
		pages[page].draw(font, relX, relY, pageCenter);
	}

	public int getPageAmount() {
		return pages.length;
	}
	
	public Link[] getLinks(int page) {
		return pages[page].getLinks();
	}

	public boolean isIndexChapter() {
		return indexChapter;
	}

}
