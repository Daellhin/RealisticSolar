package com.daellhin.realisticsolar.items.handbook;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.handbook.gui.Chapter;
import com.daellhin.realisticsolar.items.handbook.gui.ChapterRegistry;
import com.daellhin.realisticsolar.items.handbook.gui.IndexChapter;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class HandBookScreenNew extends Screen {

	private static final ResourceLocation GUI = new ResourceLocation(RealisticSolar.MODID, "textures/gui/hand_book_gui.png");
	private static final ResourceLocation BUTTONS = new ResourceLocation(RealisticSolar.MODID, "textures/gui/hand_book_buttons.png");

	private final ChapterRegistry chapters = new ChapterRegistry();
	private Chapter currentChapter = chapters.getChapter("arc_furnace");
	private PageButton buttonNextPage;
	private PageButton buttonPreviousPage;
	private PageButton buttonChapters;
	private final int xSize = 280;
	private final int ySize = 180;
	private int currentPage = 0;

	public HandBookScreenNew(ITextComponent name) {
		super(name);
	}

	@Override
	protected void init() {
		super.init();          
		buttons.clear();
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;

		buttonNextPage = new PageButton(relX + 240, relY + 152, BUTTONS, 0, 0, 18, 10, (button) -> currentPage++);
		buttonPreviousPage = new PageButton(relX + 20, relY + 152, BUTTONS, 0, 10, 18, 10, (button) -> currentPage--);
		buttonChapters = new PageButton(relX + 20 + 40, relY + 152, BUTTONS, 0, 10, 18, 10, (button) -> currentChapter = chapters.getChapter("index"));
		
		addButton(buttonNextPage);
		addButton(buttonPreviousPage);
		addButton(buttonChapters);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		renderButtons(mouseX, mouseY, partialTicks);
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	public void renderBackground(int value) {
		super.renderBackground(0);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;

		// GUI background
		this.minecraft.getTextureManager()
				.bindTexture(GUI);
		blit(relX, relY, 0, 0, this.xSize, this.ySize, 256 * 2, 256 * 2);

		// Chapter
		currentChapter.draw(currentPage, font, relX, relY, 200);

	}

	protected void renderButtons(int mouseX, int mouseY, float partialTicks) {
		buttonPreviousPage.visible = currentPage > 0;
		buttonNextPage.visible = currentPage < (currentChapter.getPageAmount() - 1);
		buttonChapters.visible = !(currentChapter instanceof IndexChapter);
		
		buttonNextPage.render(mouseX, mouseY, partialTicks);
		buttonPreviousPage.render(mouseX, mouseY, partialTicks);
		buttonChapters.render(mouseX, mouseY, partialTicks);
	}

}
