package com.daellhin.realisticsolar.items.handbook;

import com.daellhin.realisticsolar.RealisticSolar;
import net.minecraft.util.ResourceLocation;

public class Page {

    public static Page[] pages = { 
	    new Page(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "Hi", "Hi"),
	    new Page(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "Hiii", "Hi"),
	    new Page(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "Hi", "Hi") };
    public ResourceLocation image;
    public String textLeft;
    public String textRight;
    public int textureSize;

    public Page(ResourceLocation image, String textLeft, String textRight) {
	this.image = image;
	this.textLeft = textLeft;
	this.textRight = textRight;
    }

    public ResourceLocation getImage() {
	return image;
    }

    public String getTextLeft() {
	return textLeft;
    }

    public String getTextRight() {
	return textRight;
    }
}
