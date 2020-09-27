package com.daellhin.realisticsolar.items.handbook.page;

import net.minecraft.util.ResourceLocation;

public class BlockPage extends Page {

	public String caption;
	private String title;
	private ResourceLocation gui;

	public BlockPage(ResourceLocation image, String textRight, String caption, String title, ResourceLocation gui) {
		super(image, textRight);
		this.caption = caption;
		this.title = title;
		this.gui = gui;
	}

	public String getCaption() {
		return caption;
	}

	public String getTitle() {
		return title;
	}

	public ResourceLocation getGui() {
		return gui;
	}
}
