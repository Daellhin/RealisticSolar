package com.daellhin.realisticsolar.blocks.arcfurnace;

import java.util.ArrayList;
import java.util.List;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.arcfurnace.tiles.ArcFurnaceControllerTile;
import com.daellhin.realisticsolar.blocks.base.BaseContainerScreen;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.client.gui.GuiUtils;

public class ArcFurnaceScreen extends BaseContainerScreen<ArcFurnaceContainer> {

	private ArcFurnaceControllerTile tileEntity;

	// variables of the arrow
	private final int ARROW_GUI_X = 44;
	private final int ARROW_GUI_Y = 23;
	private final int ARROW_X = 0;
	private final int ARROW_Y = 181;
	private final int ARROW_WIDTH = 86;
	private final int ARROW_HEIGHT = 52;

	// variables of the energy bar
	private final int ENERGY_GUI_X = 157;
	private final int ENERGY_GUI_Y = 19;
	private final int ENERGY_X = 176;
	private final int ENERGY_Y = 0;
	private final int ENERGY_WIDTH = 10;
	private final int ENERGY_HEIGHT = 63 + 1;

	public ArcFurnaceScreen(ArcFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name, new ResourceLocation(RealisticSolar.MODID, "textures/gui/arc_furnace_gui.png"), 176, 181);
		this.tileEntity = container.getTileEntity();
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
		List<ITextProperties> s = new ArrayList<>();
		s.add(new StringTextComponent("Power: "));
		s.add(new StringTextComponent(">  " + tileEntity.getEnergy()));
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
		// progress arrow
		this.blit(matrixStack, relX + ARROW_GUI_X, relY + ARROW_GUI_Y, ARROW_X, ARROW_Y, (int) (tileEntity.fractionOfProgress() * ARROW_WIDTH), ARROW_HEIGHT);
		// energy bar
		int height = (int) (tileEntity.getFractionOfEnergy() * ENERGY_HEIGHT);
		this.blit(matrixStack, relX + ENERGY_GUI_X, (relY + ENERGY_GUI_Y) + (ENERGY_HEIGHT - height), ENERGY_X, (ENERGY_Y + ENERGY_HEIGHT) - height, ENERGY_WIDTH, height);
	}

}