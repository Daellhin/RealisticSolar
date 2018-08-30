package com.daellhin.realisticsolar.gui.machines;

import org.lwjgl.opengl.GL11;

import com.daellhin.realisticsolar.gui.container.ContainerApplier;
import com.daellhin.realisticsolar.tile.machines.TileApplier;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiApplier extends GuiContainer {
    private final ResourceLocation GuiTexture = new ResourceLocation("rs:textures/gui/threeSlotMachine.png");
    private TileApplier applier;

    public GuiApplier(InventoryPlayer inventoryPlayer, TileApplier applier) {
	super(new ContainerApplier(inventoryPlayer, applier));
	this.applier = applier;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	String string = this.applier.hasCustomInventoryName() ? this.applier.getInventoryName() : I18n.format(this.applier.getInventoryName(), new Object[0]);
	this.fontRendererObj.drawString(string, this.xSize / 2 - this.fontRendererObj.getStringWidth(string), 6, 4210752);
	this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
	this.fontRendererObj.drawString(String.valueOf(this.applier.getProgressScaled(50) * 2) + "%", 84, this.ySize - 133, 4210752);
	this.fontRendererObj.drawString(GuiScript.Arrow(applier), 75, this.ySize - 126, 4210752);

    }

    @Override
    public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
	Minecraft.getMinecraft().getTextureManager().bindTexture(GuiTexture);
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	int x = (width - xSize) / 2;
	int y = (height - ySize) / 2;
	drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

    }
}
