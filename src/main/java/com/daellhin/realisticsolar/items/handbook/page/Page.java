package com.daellhin.realisticsolar.items.handbook.page;

import com.daellhin.realisticsolar.RealisticSolar;
import net.minecraft.util.ResourceLocation;

public class Page {

    private ResourceLocation image;
    private String textRight;
    // private int textureSize;

    public Page(ResourceLocation image, String textRight) {
	this.image = image;
	this.textRight = textRight;
    }

    public ResourceLocation getImage() {
	return image;
    }

    public String getTextRight() {
	return textRight;
    }

    public static Page[] createPages() {
	Page[] pages = { 
		new IntroPage(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "page_intro", "page_disclaimer"),
		new BlockPage(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/arc_furnace.png"), "page_arc_furnace_right", "page_arc_furnace_caption", "page_arc_furnace_title", new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/arc_furnace_gui.png")),
		new Page(new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png"), "Hi") };
	return pages;
    }
}
