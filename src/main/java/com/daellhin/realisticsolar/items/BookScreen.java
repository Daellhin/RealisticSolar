package com.daellhin.realisticsolar.items;

import com.daellhin.realisticsolar.RealisticSolar;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BookScreen extends Screen {

    private ResourceLocation GUI = new ResourceLocation(RealisticSolar.MODID, "textures/gui/arc_furnace_gui.png");
    int xSize = 176;
    int ySize = 181;

    public BookScreen(ITextComponent name) {
	super(name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
	this.renderBackground();
	this.renderForeground(mouseX, mouseY);
	super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(int value) {
	GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.minecraft.getTextureManager().bindTexture(GUI);
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	// gui background
	this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
	return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
    
    protected void renderForeground(int mouseX, int mouseY) {
	if (true) {
	    this.font.drawString("power", mouseX + 9, mouseY +7, 0xffffff);
	}
    }
}
