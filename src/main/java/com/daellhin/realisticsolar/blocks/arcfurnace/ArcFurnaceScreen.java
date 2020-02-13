package com.daellhin.realisticsolar.blocks.arcfurnace;

import com.daellhin.realisticsolar.RealisticSolar;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ArcFurnaceScreen extends ContainerScreen<ArcFurnaceContainer> {

    // coordinates of the arrow
    final int ARROW_GUI_X = 44;
    final int ARROW_GUI_Y = 23;
    final int ARROW_X = 0;
    final int ARROW_Y = 181;
    final int ARROW_WIDTH = 86;
    final int ARROW_HEIGHT = 52;
    // coordinates of the energy bar
    final int ENERGY_GUI_X = 157;
    final int ENERGY_GUI_Y_BOTTOM = 81;
    final int ENERGY_X = 176;
    final int ENERGY_Y_BOTTOM = 63;
    final int ENERGY_WIDTH = 10;
    final int ENERGY_HEIGHT = 63;
    private ArcFurnaceTile tileEntity;
    private ResourceLocation GUI = new ResourceLocation(RealisticSolar.MODID, "textures/gui/arc_furnace_gui.png");

    public ArcFurnaceScreen(ArcFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
	super(container, inv, name);
	this.xSize = 176;
	this.ySize = 181;
	this.tileEntity = container.getTileEntity();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
	this.renderBackground();
	super.render(mouseX, mouseY, partialTicks);
	this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	this.font.drawString("Energy: " + tileEntity.getEnergy(), 10, 0, 0xffffff);
	this.font.drawString("Progress: " + tileEntity.getProgress(), 100, 0, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.minecraft.getTextureManager().bindTexture(GUI);
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	// gui background
	this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	// progress arrow
	this.blit(relX + ARROW_GUI_X, relY + ARROW_GUI_Y, ARROW_X, ARROW_Y, (int) (tileEntity.fractionOfTicksComplete() * ARROW_WIDTH), ARROW_HEIGHT);
	// energy bar
	int height = (int) (tileEntity.fractionOfEnergy() * ENERGY_HEIGHT);
	this.blit(relX + ENERGY_GUI_X, relY + ENERGY_GUI_Y_BOTTOM-height, ENERGY_X, ENERGY_Y_BOTTOM-height, ENERGY_WIDTH, height);
    }
}