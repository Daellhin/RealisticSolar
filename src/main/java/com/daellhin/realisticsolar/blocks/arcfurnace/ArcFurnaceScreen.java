package com.daellhin.realisticsolar.blocks.arcfurnace;

import java.util.ArrayList;
import java.util.List;
import com.daellhin.realisticsolar.RealisticSolar;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ArcFurnaceScreen extends ContainerScreen<ArcFurnaceContainer> {

    private ArcFurnaceTile tileEntity;
    private ResourceLocation GUI = new ResourceLocation(RealisticSolar.MODID, "textures/gui/arc_furnace_gui.png");
    // variables of the arrow
    private final int ARROW_GUI_X = 44;
    private final int ARROW_GUI_Y = 23;
    private final int ARROW_X = 0;
    private final int ARROW_Y = 181;
    private final int ARROW_WIDTH = 86;
    private final int ARROW_HEIGHT = 52;
    // variables of the energy bar
    private final int ENERGY_GUI_X = 157;
    private final int ENERGY_GUI_Y = 18;
    private final int ENERGY_GUI_Y_BOTTOM = 81;
    private final int ENERGY_X = 176;
    private final int ENERGY_Y_BOTTOM = 63;
    private final int ENERGY_WIDTH = 10;
    private final int ENERGY_HEIGHT = 62;

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
	List<String> s = new ArrayList<>();
	s.add("Power: " + tileEntity.getEnergy());
	if (isInRect(guiLeft + ENERGY_GUI_X - 1, guiTop + ENERGY_GUI_Y, ENERGY_WIDTH - 1, ENERGY_HEIGHT, mouseX, mouseY)) {
	    net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(s, mouseX - guiLeft, mouseY - guiTop, width, height, -1, font);
	}
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.minecraft.getTextureManager().bindTexture(GUI);
	int relX = (this.width - this.xSize) / 2;
	int relY = (this.height - this.ySize) / 2;
	// GUI background
	this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
	// progress arrow
	this.blit(relX + ARROW_GUI_X, relY + ARROW_GUI_Y, ARROW_X, ARROW_Y, (int) (tileEntity.fractionOfTicksComplete() * ARROW_WIDTH), ARROW_HEIGHT);
	// energy bar
	int height = (int) (tileEntity.fractionOfEnergy() * ENERGY_HEIGHT);
	this.blit(relX + ENERGY_GUI_X, relY + ENERGY_GUI_Y_BOTTOM - height, ENERGY_X, ENERGY_Y_BOTTOM - height, ENERGY_WIDTH, height);
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    private static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
	return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
}