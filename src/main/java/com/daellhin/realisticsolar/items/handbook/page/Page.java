package com.daellhin.realisticsolar.items.handbook.page;

import com.daellhin.realisticsolar.RealisticSolar;
import net.minecraft.util.ResourceLocation;

public class Page {

    private static final Page[] PAGES = { 
	    new IntroPage(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "page_intro", "page_disclaimer"),
	    new BlockPage(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/arc_furnace.png"), "page_arc_furnace_title", "", ""),
	    new Page(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "Hi", "Hi") 
    };
    public ResourceLocation image;
    public String textLeft;
    public String textRight;
    public int textureSize;

    public Page(ResourceLocation image, String textLeft, String textRight) {
	this.image = image;
	this.textLeft = textLeft;
	this.textRight = textRight;
    }

    public String getTextRight() {
	return textRight;
    }
    public ResourceLocation getImage() {
	return image;
    }
 
    public static Page[] getPages() {
	return PAGES;
    }
}
