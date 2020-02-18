package com.daellhin.realisticsolar.items.book;

import com.daellhin.realisticsolar.RealisticSolar;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BookScreen extends Screen {

    private static final ResourceLocation FULL = new ResourceLocation(RealisticSolar.MODID, "textures/gui/u.png");
    private static final ResourceLocation TEST = new ResourceLocation(RealisticSolar.MODID, "textures/gui/test.png");

    
    int xSize = 280;
    int ySize = 180;

    public BookScreen(ITextComponent name) {
	super(name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
	renderBackground();
	renderForeground(mouseX, mouseY);
	super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    public void renderBackground(int value) {
	super.renderBackground(0);
	GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	// gui background
	this.minecraft.getTextureManager().bindTexture(FULL);
	blit(relX, relY,  0, 0, this.xSize, this.ySize, 256 * 2, 256 * 2);
	this.minecraft.getTextureManager().bindTexture(TEST);
	blit(relX, relY, 10, -50, 50, 64, 64, 64, 128); 
	minecraft.getItemRenderer().renderItemIntoGUI(minecraft.player.getHeldItemOffhand(), 200, 200);
	GlStateManager.enableLighting();
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
	return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
    
    //TODO make pages 
    protected void renderForeground(int mouseX, int mouseY) {
    }
}
