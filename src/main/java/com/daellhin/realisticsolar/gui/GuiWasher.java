package com.daellhin.realisticsolar.gui;

import com.daellhin.realisticsolar.gui.container.ContainerWasher;
import com.daellhin.realisticsolar.tile.TileWasher;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiWasher extends GuiContainer{
	private final ResourceLocation texture =  new ResourceLocation("rs:textures/gui/threeSlotMachine.png");
	
	private TileWasher washer;
	
	private static final int LEFT_INDENT_STONE = 57;
	private static final int TOP_INDENT_STONE = 37;
	private static final int LEFT_INDENT_TEXTURE_STONE = 176;
	private static final int TEXTURE_WIDTH_STONE = 14;
	private static final int TEXTURE_HEIGHT_STONE = 14;
	
	private static final int LEFT_INDENT_PROGRESS = 79;
	private static final int TOP_INDENT_PROGRESS = 35;
	private static final int LEFT_INDENT_TEXTURE_PROGRESS = 176;
	private static final int TOP_INDENT_TEXTURE_PROGRESS = 14;
	private static final int TEXTURE_HEIGHT_PROGRESS = 17;
	
	public GuiWasher(InventoryPlayer inventoryPlayer, TileWasher washer)
	{
		super(new ContainerWasher(inventoryPlayer, washer));
		
		this.washer = washer;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	@Override
	public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) 
	{
		//Draw the gui screen
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(this.guiLeft, this.guiTop,
				0, 0, 
				this.xSize, this.ySize);
		/*
		//Stone bar
		int i = (int) this.washer.getStoneScaled(14);
		drawTexturedModalRect(guiLeft+LEFT_INDENT_STONE, guiTop+TOP_INDENT_STONE+TEXTURE_HEIGHT_STONE-i, 
				LEFT_INDENT_TEXTURE_STONE, TEXTURE_HEIGHT_STONE-i, 
				TEXTURE_WIDTH_STONE, i);
		
        //Progress bar
		int i1 = this.washer.getProgressScaled(24);
        this.drawTexturedModalRect(guiLeft + LEFT_INDENT_PROGRESS, guiTop + TOP_INDENT_PROGRESS,
        		LEFT_INDENT_TEXTURE_PROGRESS, TOP_INDENT_TEXTURE_PROGRESS,
        		i1 + 1, TEXTURE_HEIGHT_PROGRESS);
        */
	}
}

