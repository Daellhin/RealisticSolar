package com.daellhin.realisticsolar.items.handbook.page;

import net.minecraft.util.ResourceLocation;

public class IntroPage extends Page{

    public IntroPage(ResourceLocation image, String textLeft, String textRight) {
	super(image, textLeft, textRight);
    }
    
    public String getTextLeft() {
	return textLeft;
    }
}
