package com.daellhin.realisticsolar.tools;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;

public class GuiUtils {

	public static void drawScaledSplitString(FontRenderer font, String text, int x, int y, int wrap, float size, int color) {
		GL11.glScalef(size, size, size);
		float mSize = (float) Math.pow(size, -1);
		//font.drawSplitString(I18n.get(text), Math.round(x / size), Math.round(y / size), Math.round(wrap / size), color);
		GL11.glScalef(mSize, mSize, mSize);
	}

	public static void drawScaledCenteredString(FontRenderer font, String text, int x, int y, float size, int color) {
		// TODO add loop to use \n nexline
		GL11.glScalef(size, size, size);
		//font.drawString(I18n.get(text), (float) (Math.round(x / size) - font.width(text) / 2), (float) Math.round(y / size), color);
		float mSize = (float) Math.pow(size, -1);
		GL11.glScalef(mSize, mSize, mSize);
	}

	public static void drawScaledRightAlignedString(FontRenderer font, String text, int x, int y, float size, int color) {
		GL11.glScalef(size, size, size);
		//font.drawString(I18n.get(text), (float) (Math.round(x / size) - font.width(text)), (float) Math.round(y / size), color);
		float mSize = (float) Math.pow(size, -1);
		GL11.glScalef(mSize, mSize, mSize);
	}

}
