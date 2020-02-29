package com.daellhin.realisticsolar.blocks.coalgenerator;

import java.util.ArrayList;
import java.util.List;
import com.daellhin.realisticsolar.RealisticSolar;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CoalGeneratorScreen extends ContainerScreen<CoalGeneratorContainer> {

    private CoalGeneratorTile tileEntity;
    private ResourceLocation GUI = new ResourceLocation(RealisticSolar.MODID, "textures/gui/coal_generator_gui.png");
    // variables of the flame
    private final int FLAME_GUI_X = 82;
    private final int FLAME_GUI_Y = 43;
    private final int FLAME_X = 186;
    private final int FLAME_Y = 0;
    private final int FLAME_WIDTH = 14;
    private final int FLAME_HEIGHT = 14;
    // variables of the energy bar
    private final int ENERGY_GUI_X = 157;
    private final int ENERGY_GUI_Y = 12;
    private final int ENERGY_GUI_Y_BOTTOM = 73;
    private final int ENERGY_X = 176;
    private final int ENERGY_Y_BOTTOM = 62;
    private final int ENERGY_WIDTH = 10;
    private final int ENERGY_HEIGHT = 63;

    public CoalGeneratorScreen(CoalGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
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
	s.add("" + tileEntity.fractionOfTicksComplete());
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
	// flame
	this.blit(relX + FLAME_GUI_X, relY + FLAME_GUI_Y, FLAME_X, FLAME_Y, (int) (tileEntity.fractionOfTicksComplete() * FLAME_WIDTH), FLAME_HEIGHT);
	// energy bars

	int height = (int) (tileEntity.fractionOfEnergy() * ENERGY_HEIGHT);
	this.blit(relX + ENERGY_GUI_X, relY + ENERGY_GUI_Y_BOTTOM - height, ENERGY_X, ENERGY_Y_BOTTOM - height, ENERGY_WIDTH, height);
    }

    // Returns true if the given x,y coordinates are within the given rectangle
    private static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY) {
	return ((mouseX >= x && mouseX <= x + xSize) && (mouseY >= y && mouseY <= y + ySize));
    }
}
