package com.daellhin.realisticsolar.blocks.hclburner;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.base.BaseContainerScreen;
import com.daellhin.realisticsolar.fluids.handling.CustomFluidTank;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class HClBurnerScreen extends BaseContainerScreen<HClBurnerContainer> {

	private HClBurnerTile tileEntity;

	public HClBurnerScreen(HClBurnerContainer container, PlayerInventory inv, ITextComponent name) {
		super(container, inv, name, new ResourceLocation(RealisticSolar.MODID, "textures/gui/empty_gui.png"), 176, 181);
		this.tileEntity = container.getTileEntity();
	}

	@Override
	protected void renderLabels(MatrixStack matrixStack, int mouseX, int mouseY) {
	}

	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
		this.minecraft.getTextureManager()
				.bind(backgroundTexture);
		int relX = (this.width - this.imageWidth) / 2;
		int relY = (this.height - this.imageHeight) / 2;

		// GUI background
		this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

		// GUI elements
		CustomFluidTank tank;
		tank = tileEntity.getInputChlorideTank();
		drawString(matrixStack, font, String
				.format("WEST|  Chloride: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 10, 000000);
		tank = tileEntity.getInputHydrogenTank();
		drawString(matrixStack, font, String
				.format("EAST|  Hydrogen: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 40, 000000);
		tank = tileEntity.getInputWaterTank();
		drawString(matrixStack, font, String
				.format("NORTH| Water: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 70, 000000);
		tank = tileEntity.getOutputHClTank();
		drawString(matrixStack, font, String.format("SOUTH| HCl: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 100, 000000);

		drawString(matrixStack, font, "Progress: " + tileEntity.getProgress(), relX + 10, relY + 120, 000000);

	}

}
