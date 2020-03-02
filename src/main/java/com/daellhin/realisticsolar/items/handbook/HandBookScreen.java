package com.daellhin.realisticsolar.items.handbook;

import com.daellhin.realisticsolar.RealisticSolar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class HandBookScreen extends Screen {

    private static final ResourceLocation FULL = new ResourceLocation(RealisticSolar.MODID, "textures/gui/hand_book_gui.png");
    private static final ResourceLocation LOGO = new ResourceLocation(RealisticSolar.MODID, "textures/gui/page/logo_transparent.png");
    private final int xSize = 280;
    private final int ySize = 180;
    private int currentPage = 0;
    private Page[] pages = Page.pages;
    private CustomButton buttonNextPage, buttonPreviousPage;
    public HandBookScreen(ITextComponent name) {
	super(name);
    }

    @Override
    protected void init() {
	super.init();
	buttons.clear();
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	addButton(buttonNextPage = new CustomButton(relX+240, relY+152, true, (button) -> {
	    System.out.println("Hi true");
	    currentPage++;
	}));
	addButton(buttonPreviousPage = new CustomButton(relX+20, relY+152, false, (button) -> {
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
	
	// page image
	this.minecraft.getTextureManager().bindTexture(pages[currentPage].getImage());
	blit(relX + 28, relY + 18, (int) (71 * 1.2), (int) (17 * 1.2), 0, 0, 71, 17, 128, 128);
	// text
	Minecraft.getInstance().fontRenderer.drawSplitString(I18n.format(pages[currentPage].getTextLeft()), relX + 20, relY + 55, 105, 000000);
	Minecraft.getInstance().fontRenderer.drawSplitString(I18n.format(pages[currentPage].getTextRight()), relX + 180, relY + 15, 105, 000000);
	// TODO maybe rework pages
    }

    protected void renderButtons(int mouseX, int mouseY, float partialTicks) {
        buttonPreviousPage.visible = currentPage > 0;
        buttonNextPage.visible = (currentPage < Page.pages.length - 1);
	buttonNextPage.render(mouseX, mouseY, partialTicks);
	buttonPreviousPage.render(mouseX, mouseY, partialTicks);
    }
}
