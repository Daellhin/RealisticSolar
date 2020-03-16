package com.daellhin.realisticsolar.items.handbook;

import com.daellhin.realisticsolar.RealisticSolar;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public class CustomButton extends Button {

    private static final ResourceLocation FULL = new ResourceLocation(RealisticSolar.MODID, "textures/gui/hand_book_gui.png");
    int xPos;
    int yPos;
    boolean isNextButton;

    public CustomButton(int xPos, int yPos, boolean isNextButton, IPressable onPress) {
	super(xPos, yPos, 18, 10, "", onPress);
	this.xPos = xPos;
	this.yPos = yPos;
	this.isNextButton = isNextButton;
    }

    @Override
    public void renderButton(int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
	//GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	Minecraft minecraft = Minecraft.getInstance();
	minecraft.getTextureManager().bindTexture(FULL);
	int textureX = 280;
	int textureY = 0;
	if (this.isHovered) {
	    textureX += 18;
	}
	if (!isNextButton) {
	    textureY += 10;
	}
	blit(xPos, yPos, textureX, textureY, 18, 10, 256 * 2, 256 * 2);
    }
}
