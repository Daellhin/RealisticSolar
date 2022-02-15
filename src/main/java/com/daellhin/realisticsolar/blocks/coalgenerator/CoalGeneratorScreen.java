package com.daellhin.realisticsolar.blocks.coalgenerator;

import java.util.ArrayList;
import java.util.List;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.base.BaseContainerScreen;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class CoalGeneratorScreen extends BaseContainerScreen<CoalGeneratorContainer> {

	private CoalGeneratorTile tileEntity;

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
		super(container, inv, name, new ResourceLocation(RealisticSolar.MODID, "textures/gui/coal_generator_gui.png"), 176, 181);
		this.tileEntity = container.getTileEntity();
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
		List<ITextProperties> s = new ArrayList<>();
		s.add(new StringTextComponent("Power: "));
		s.add(new StringTextComponent(">  " + tileEntity.getEnergy()));
		s.add(new StringTextComponent("Fuel: "));
		s.add(new StringTextComponent(">  " + tileEntity.getProgress()));
		if (isInRect(leftPos + ENERGY_GUI_X - 1, topPos + ENERGY_GUI_Y, ENERGY_WIDTH - 1, ENERGY_HEIGHT, mouseX, mouseY)) {
			GuiUtils.drawHoveringText(matrixStack, s, mouseX - leftPos, mouseY - topPos, width, height, -1, font);
		}
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		this.minecraft.getTextureManager()
				.bind(backgroundTexture);
		int relX = (this.width - this.imageWidth) / 2;
		int relY = (this.height - this.imageHeight) / 2;

		// GUI background
		this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
		// flame
		int flameHeight = (int) (tileEntity.fractionOfProgress() * FLAME_HEIGHT);
		this.blit(matrixStack, relX + FLAME_GUI_X, (relY + FLAME_GUI_Y) + (FLAME_HEIGHT - flameHeight), FLAME_X, (FLAME_Y + FLAME_HEIGHT) - flameHeight, FLAME_WIDTH, flameHeight);
		// energy bar
		int energyHeight = (int) (tileEntity.getFractionOfEnergy() * ENERGY_HEIGHT);
		this.blit(matrixStack, relX + ENERGY_GUI_X, (relY + ENERGY_GUI_Y) + (ENERGY_HEIGHT - energyHeight), ENERGY_X, (ENERGY_Y + ENERGY_HEIGHT) - energyHeight, ENERGY_WIDTH, energyHeight);
	}

}
