package com.daellhin.realisticsolar.gui.machines;

import org.lwjgl.opengl.GL11;

import com.daellhin.realisticsolar.gui.container.ContainerWasher;
import com.daellhin.realisticsolar.tile.machines.TileWasher;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiWasher extends GuiContainer {
    private final ResourceLocation GuiTexture = new ResourceLocation("rs:textures/gui/threeSlotMachine.png");
    private TileWasher washer;
    private String Progress = null;

    public GuiWasher(InventoryPlayer inventoryPlayer, TileWasher washer) {
	super(new ContainerWasher(inventoryPlayer, washer));
	this.washer = washer;

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	String containerName = this.washer.hasCustomInventoryName() ? this.washer.getInventoryName() : I18n.format(this.washer.getInventoryName(), new Object[0]);
	this.fontRendererObj.drawString(containerName, this.xSize / 2 - this.fontRendererObj.getStringWidth(containerName), 6, 4210752);
	this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
	this.fontRendererObj.drawString(String.valueOf(this.washer.getProgressScaled(50) * 2) + "%", 84, this.ySize - 133, 4210752);
	this.fontRendererObj.drawString(GuiScript.Arrow(washer), 75, this.ySize - 126, 4210752);
	int n = washer.getBufferScaled(100);
	this.fontRendererObj.drawString(n + "%", 146 + GuiScript.Center(n), this.ySize - 102, 4210752);
	this.fontRendererObj.drawString("Water:", 140, this.ySize - 93, 4210752);

    }

    @Override
    public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
	Minecraft.getMinecraft().getTextureManager().bindTexture(GuiTexture);
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	int x = (width - xSize) / 2;
	int y = (height - ySize) / 2;
	drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

	// guiProgress bar not working
	// i1 = this.washer.getProgressScaled(24);
	// this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);

    }
}
