package com.daellhin.realisticsolar.items.handbook.gui.chapters;

import com.daellhin.realisticsolar.items.handbook.gui.elements.Link;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Page;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;

public class Chapter {
	Page[] pages;
	String name;
	int index;
	
	public Chapter() {
	}
	
	public void initialize() {
	}

	public void draw(MatrixStack matrixStack, int page, FontRenderer font, int relX, int relY, int pageCenter) {
		pages[page].draw(font, matrixStack, relX, relY, pageCenter);
	}

	public int getPageAmount() {
		return pages.length;
	}
	
	public Link[] getLinks(int page) {
		return pages[page].getLinks();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
