package com.daellhin.realisticsolar.blocks.hclburner;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.base.BaseContainerScreen;
import com.daellhin.realisticsolar.fluids.handling.CustomFluidTank;

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
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		this.minecraft.getTextureManager()
				.bindTexture(backgroundTexture);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;

		// GUI background
		this.blit(relX, relY, 0, 0, this.xSize, this.ySize);

		// GUI elements
		CustomFluidTank tank;
		tank = tileEntity.getInputChlorideTank();
		this.font.drawString(String.format("WEST|  Chloride: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 10, 000000);
		tank = tileEntity.getInputHydrogenTank();
		this.font.drawString(String.format("EAST|  Hydrogen: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 40, 000000);
		tank = tileEntity.getInputWaterTank();
		this.font.drawString(String.format("NORTH| Water: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 70, 000000);
		tank = tileEntity.getOutputHClTank();
		this.font.drawString(String.format("SOUTH| HCl: %s / %s", tank.getFluidAmount(), tank.getCapacity()), relX + 10, relY + 100, 000000);

		this.font.drawString("Progress: " + tileEntity.getProgress(), relX + 10, relY + 120, 000000);

	}

}
