package com.daellhin.realisticsolar.blocks.magic;

import com.daellhin.realisticsolar.RealisticSolar;

import com.daellhin.realisticsolar.setup.Registration;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.Random;
import java.util.function.Consumer;

import static com.daellhin.realisticsolar.blocks.ModBlocks.MAGICBLOCK_TILE;;

public class MagicRenderer extends TileEntityRenderer<MagicTile> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(RealisticSolar.MODID, "block/fancyblock");

	public MagicRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	/*
	 * x: Red, goes to the east when facing north y: Green, goes up z: blue, goes to the south when facing north
	 */
	private static Vec3d v(double x, double y, double z) {
		return new Vec3d(x, y, z);
	}

	private void putVertex(BakedQuadBuilder builder, Vec3d normal, double x, double y, double z, float u, float v, TextureAtlasSprite sprite, float r, float g, float b) {

		ImmutableList<VertexFormatElement> elements = builder.getVertexFormat()
				.getElements()
				.asList();
		for (int j = 0; j < elements.size(); j++) {
			VertexFormatElement e = elements.get(j);
			switch (e.getUsage()) {
			case POSITION:
				builder.put(j, (float) x, (float) y, (float) z, 1.0f);
				break;
			case COLOR:
				builder.put(j, r, g, b, 1.0f);
				break;
			case UV:
				switch (e.getIndex()) {
				case 0:
					float iu = sprite.getInterpolatedU(u);
					float iv = sprite.getInterpolatedV(v);
					builder.put(j, iu, iv);
					break;
				case 2:
					builder.put(j, (short) 0, (short) 0);
					break;
				default:
					builder.put(j);
					break;
				}
				break;
			case NORMAL:
				builder.put(j, (float) normal.x, (float) normal.y, (float) normal.z);
				break;
			default:
				builder.put(j);
				break;
			}
		}
	}

	private BakedQuad createQuad(Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4, TextureAtlasSprite sprite) {
		Vec3d normal = v3.subtract(v2)
				.crossProduct(v1.subtract(v2))
				.normalize();
		int tw = sprite.getWidth();
		int th = sprite.getHeight();

		BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(Direction.getFacingFromVector(normal.x, normal.y, normal.z));

		putVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v2.x, v2.y, v2.z, 0, th, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v3.x, v3.y, v3.z, tw, th, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v4.x, v4.y, v4.z, tw, 0, sprite, 1.0f, 1.0f, 1.0f);

		return builder.build();
	}

	private void addCube(IVertexBuilder builder, MatrixStack stack, double w, double x, double y, double z, TextureAtlasSprite texture, int combinedLight, int combinedOverlay) {
		double xw = w + x;
		double zw = w + z;
		double yw = w + y;

		builder.addQuad(stack
				.getLast(), createQuad(v(x, yw, z), v(x, yw, zw), v(xw, yw, zw), v(xw, yw, z), texture), 1.0f, 1.0f, 1.0f, combinedLight, combinedOverlay);
		builder.addQuad(stack
				.getLast(), createQuad(v(x, y, z), v(xw, y, z), v(xw, y, zw), v(x, y, zw), texture), 1.0f, 1.0f, 1.0f, combinedLight, combinedOverlay);

		builder.addQuad(stack
				.getLast(), createQuad(v(xw, yw, z), v(xw, y, z), v(x, y, z), v(x, yw, z), texture), 1.0f, 1.0f, 1.0f, combinedLight, combinedOverlay);
		builder.addQuad(stack
				.getLast(), createQuad(v(x, yw, zw), v(x, y, zw), v(xw, y, zw), v(xw, yw, zw), texture), 1.0f, 1.0f, 1.0f, combinedLight, combinedOverlay);

		builder.addQuad(stack
				.getLast(), createQuad(v(xw, yw, zw), v(xw, y, zw), v(xw, y, z), v(xw, yw, z), texture), 1.0f, 1.0f, 1.0f, combinedLight, combinedOverlay);
		builder.addQuad(stack
				.getLast(), createQuad(v(x, yw, z), v(x, y, z), v(x, y, zw), v(x, yw, zw), texture), 1.0f, 1.0f, 1.0f, combinedLight, combinedOverlay);
	}

	@Override
	public void render(MagicTile tileEntity, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {

		TextureAtlasSprite texture = Minecraft.getInstance()
				.getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE)
				.apply(TEXTURE);
		IVertexBuilder builder = buffer.getBuffer(RenderType.getSolid());

		matrixStack.push();

		int x = 0;
		int y = 0;
		int z = 0;
		for (int i = 0; i < tileEntity.getProgress(); i++) {
			addCube(builder, matrixStack, 0.2, 0.2 * x, 0.2 * y, 0.2 * z, texture, combinedLight, combinedOverlay);

			x = x + 1;
			z = z + x / 5;
			y = y + ((x / 5) * (z / 5));

			x = x % 5;
			z = z % 5;
		}


		matrixStack.pop();
	}

	public static void register() {
		ClientRegistry.bindTileEntityRenderer(MAGICBLOCK_TILE.get(), MagicRenderer::new);
	}

}
