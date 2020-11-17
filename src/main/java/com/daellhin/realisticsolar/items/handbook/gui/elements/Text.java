package com.daellhin.realisticsolar.items.handbook.gui.elements;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;

public class Text {
	protected String text;
	protected Alignement alignement;
	/**
	 * Max amount of chars before the line should wrap, -1 = no wrap
	 */
	protected float size;
	protected int wrap;
	protected int color;
	protected int x;
	protected int y;
	
	
	
	public Text(String text, Alignement alignement, float size, int wrap, int color, int x, int y) {
		this.text = text;
		this.alignement = alignement;
		this.size = size;
		this.wrap = wrap;
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public void draw(FontRenderer font, int relX, int relY, int pageCenter) {
		if (alignement == Alignement.RIGHT) {
			drawRightAlignedString(font, text, relX + x, relY + y, size, color);
		} else if (alignement == Alignement.CENTER) {
			drawCenteredString(font, text, relX + x, relY + y, size, color);
		} else {
			drawString(font, text, relX + x, relY + y, size, color);
		}
	}

	protected void drawString(FontRenderer font, String text, int x, int y, float size, int color) {
		drawStringInternal(font, text, x, y, size, color, 0);
	}

	protected void drawCenteredString(FontRenderer font, String text, int x, int y, float size, int color) {
		// TODO add loop to use \n nexline
		drawStringInternal(font, text, x, y, size, color, font.getStringWidth(text) / 2);
	}

	protected void drawRightAlignedString(FontRenderer font, String text, int x, int y, float size, int color) {
		drawStringInternal(font, text, x, y, size, color, font.getStringWidth(text));
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
