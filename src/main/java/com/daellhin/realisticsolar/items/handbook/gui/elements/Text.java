package com.daellhin.realisticsolar.items.handbook.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;

public class Text {
	protected String text;
	protected int x;
	protected int y;
	protected Alignement alignement = Alignement.LEFT;
	protected float size = 1;
	/**
	 * Max amount of chars before the line should wrap, -1 = no wrap
	 */
	protected int wrap = -1;
	protected int color = 1;

	public Text() {
	}

	public void draw(FontRenderer font, int relX, int relY, int pageCenter) {
		switch (alignement) {
		case LEFT:
			drawLeftAlignedString(font, text, relX + x, relY + y, size, color);
			break;
		case RIGHT:
			drawRightAlignedString(font, text, relX + x, relY + y, size, color);
			break;
		case CENTER:
			drawCenteredString(font, text, relX + x, relY + y, size, color);
			break;
		default:
			throw new IllegalStateException("Invalid alignement: " + alignement);
		}
	}

	protected void drawLeftAlignedString(FontRenderer font, String text, int x, int y, float size, int color) {
		drawStringInternal(font, text, x, y, size, color, 0);
	}

	protected void drawCenteredString(FontRenderer font, String text, int x, int y, float size, int color) {
		// TODO add loop to use \n nexline
		drawStringInternal(font, text, x, y, size, color, font.getStringWidth(I18n.format(text)) / 2);
	}

	protected void drawRightAlignedString(FontRenderer font, String text, int x, int y, float size, int color) {
		drawStringInternal(font, text, x, y, size, color, font.getStringWidth(I18n.format(text)));
	}

	protected void drawStringInternal(FontRenderer font, String text, int x, int y, float size, int color, int offset) {
		GL11.glScalef(size, size, size);
		if (wrap == -1) {
			font.drawString(I18n.format(text), Math.round(x / size) - offset, Math.round(y / size), color);
		} else {
			font.drawSplitString(I18n.format(text), Math.round(x / size) - offset, Math.round(y / size), Math.round(wrap / size), color);
		}
		float mSize = (float) Math.pow(size, -1);
		GL11.glScalef(mSize, mSize, mSize);
	}

	public enum Alignement {
		LEFT,
		RIGHT,
		CENTER;
	}
}
