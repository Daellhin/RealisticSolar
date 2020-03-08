package com.daellhin.realisticsolar.items.handbook.page;

import net.minecraft.util.ResourceLocation;

public class IntroPage extends Page {

    private String textLeft;

    public IntroPage(ResourceLocation image, String textRight, String textLeft) {
	super(image, textRight);
	this.textLeft = textLeft;
    }

    public String getTextLeft() {
	return textLeft;
    }
}
