package com.daellhin.realisticsolar.items.handbook;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.handbook.gui.Chapter;
import com.daellhin.realisticsolar.items.handbook.gui.ChapterRegistry;
import com.daellhin.realisticsolar.items.handbook.gui.IndexChapter;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Link;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class HandBookScreen extends Screen {

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

	public HandBookScreen(ITextComponent name) {
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
		buttonChapters = new PageButton(relX + 20 + 40, relY + 152, BUTTONS, 36, 0, 13, 12, (button) -> changeChapter("index"));

		addButton(buttonNextPage);
		addButton(buttonPreviousPage);
		addButton(buttonChapters);

		for (Link link : currentChapter.getLinks(currentPage)) {
			link.initialize(this.font, (button) -> System.out.println("click"), relX, relY);
			addButton(link.getLinkButton());
		}
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		renderBackground();
		buttonPreviousPage.visible = currentPage > 0;
		buttonNextPage.visible = currentPage < (currentChapter.getPageAmount() - 1);
		buttonChapters.visible = !(currentChapter instanceof IndexChapter);

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

	private void changeChapter(String chapterName) {
		currentChapter = chapters.getChapter(chapterName);
		init();
	}

}
