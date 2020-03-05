package com.daellhin.realisticsolar.items.handbook;

import org.lwjgl.opengl.GL11;
import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.handbook.page.BlockPage;
import com.daellhin.realisticsolar.items.handbook.page.IntroPage;
import com.daellhin.realisticsolar.items.handbook.page.Page;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class HandBookScreen extends Screen {

    private static final ResourceLocation FULL = new ResourceLocation(RealisticSolar.MODID, "textures/gui/hand_book_gui.png");
    private static final Page[] PAGES = Page.getPages();
    private CustomButton buttonNextPage, buttonPreviousPage;
    private final int xSize = 280;
    private final int ySize = 180;
    private int currentPage = 1;

    public HandBookScreen(ITextComponent name) {
	super(name);
    }

    @Override
    protected void init() {
	super.init();
	buttons.clear();
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	addButton(buttonNextPage = new CustomButton(relX + 240, relY + 152, true, (button) -> {
	    System.out.println("Hi true");
	    currentPage++;
	}));
	addButton(buttonPreviousPage = new CustomButton(relX + 20, relY + 152, false, (button) -> {
	    System.out.println("Hi false");
	    currentPage--;
	}));
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
	renderBackground();
	renderButtons(mouseX, mouseY, partialTicks);
	super.render(mouseX, mouseY, partialTicks);
    }

    // blit(int x, int y, int desiredWidth, int desiredHeight, int textureX, int
    // textureY, int width, int height, int textureWidth, int textureHeight);
    @Override
    public void renderBackground(int value) {
	super.renderBackground(0);
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	// GUI background
	this.minecraft.getTextureManager().bindTexture(FULL);
	blit(relX, relY, 0, 0, this.xSize, this.ySize, 256 * 2, 256 * 2);
	// pages
	if (PAGES[currentPage] instanceof IntroPage) {
	    this.minecraft.getTextureManager().bindTexture(PAGES[currentPage].getImage());
	    blit(relX + 28, relY + 18, (int) (71 * 1.2), (int) (17 * 1.2), 0, 0, 71, 17, 128, 128);
	    drawSplitString(I18n.format(((IntroPage) PAGES[currentPage]).getTextLeft()), relX + 20, relY + 45, 110, 0.7f, 000000);
	}
	if (PAGES[currentPage] instanceof BlockPage) {
	    this.minecraft.getTextureManager().bindTexture(PAGES[currentPage].getImage());
	    blit(relX + 28, relY + 18, (int) (71 * 1.2), (int) (17 * 2.4), 0, 0, 1024, 512, 1024, 512);

	}
	drawSplitString(I18n.format(PAGES[currentPage].getTextRight()), relX + 160, relY + 30, 100, 0.7f, 000000);
    }

    public void drawSplitString(String text, int x, int y, int wrap, float size, int color) {
	GL11.glScalef(size, size, size);
	float mSize = (float) Math.pow(size, -1);
	Minecraft.getInstance().fontRenderer.drawSplitString(I18n.format(text), Math.round(x / size), Math.round(y / size), Math.round(wrap / size), color);
	GL11.glScalef(mSize, mSize, mSize);
    }

    protected void renderButtons(int mouseX, int mouseY, float partialTicks) {
	GlStateManager.scaled(1, 1, 1);
	buttonPreviousPage.visible = currentPage > 0;
	buttonNextPage.visible = (currentPage < PAGES.length - 1);
	buttonNextPage.render(mouseX, mouseY, partialTicks);
	buttonPreviousPage.render(mouseX, mouseY, partialTicks);
    }
}
