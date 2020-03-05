package com.daellhin.realisticsolar.items.handbook.page;

import net.minecraft.util.ResourceLocation;

public class BlockPage extends Page {

    private String title;

    public BlockPage(ResourceLocation image, String title, String textLeft, String textRight) {
	super(image, textLeft, textRight);
	this.title = title;
    }

    public String getTitle() {
	return title;
    }
}
