package com.daellhin.realisticsolar.blocks.coalgenerator;

import java.util.ArrayList;
import java.util.List;

import com.daellhin.realisticsolar.RealisticSolar;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

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
	private final int ENERGY_X = 176;
	private final int ENERGY_Y = 0;
	private final int ENERGY_WIDTH = 10;
	private final int ENERGY_HEIGHT = 63 + 1;

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
		s.add("Power: ");
		s.add(">  " + tileEntity.getEnergy());
		s.add("Fuel: ");
		s.add(">  " + tileEntity.getProgress());
		if (isInRect(guiLeft + ENERGY_GUI_X - 1, guiTop + ENERGY_GUI_Y, ENERGY_WIDTH - 1, ENERGY_HEIGHT, mouseX, mouseY)) {
			GuiUtils.drawHoveringText(s, mouseX - guiLeft, mouseY - guiTop, width, height, -1, font);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.minecraft.getTextureManager().bindTexture(GUI);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;
		// GUI background
		this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
		// flame
		int flameHeight = (int) (tileEntity.fractionOfProgress() * FLAME_HEIGHT);
		this.blit(relX + FLAME_GUI_X, (relY + FLAME_GUI_Y) + (FLAME_HEIGHT - flameHeight), FLAME_X, (FLAME_Y + FLAME_HEIGHT) - flameHeight, FLAME_WIDTH, flameHeight);
		// energy bar
		int energyHeight = (int) (tileEntity.getFractionOfEnergy() * ENERGY_HEIGHT);
		this.blit(relX + ENERGY_GUI_X, (relY + ENERGY_GUI_Y) + (ENERGY_HEIGHT - energyHeight), ENERGY_X, (ENERGY_Y + ENERGY_HEIGHT) - energyHeight, ENERGY_WIDTH, energyHeight);
	}

	/**
	 * Returns true if the given x,y coordinates are within the given rectangle
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param mouseX
	 * @param mouseY
	 */
	private static boolean isInRect(int x, int y, int width, int height, int mouseX, int mouseY) {
		return ((mouseX >= x && mouseX <= x + width) && (mouseY >= y && mouseY <= y + height));
	}
}
