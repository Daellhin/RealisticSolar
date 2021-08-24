package com.daellhin.realisticsolar.blocks.fancy;

import com.daellhin.realisticsolar.RealisticSolar;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IDynamicBakedModel;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class FancyBakedModel implements IDynamicBakedModel {

	public static final ResourceLocation TEXTURE = new ResourceLocation(RealisticSolar.MODID, "block/fancyblock");

	private TextureAtlasSprite getTexture() {
		return Minecraft.getInstance()
				.getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE)
				.apply(TEXTURE);
	}

	@Override
	public boolean func_230044_c_() {
		return false;
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

	/*
	 * x: Red, goes to the east when facing north y: Green, goes up z: blue, goes to the south when facing north
	 */
	private static Vec3d v(double x, double y, double z) {
		return new Vec3d(x, y, z);
	}

	@Nonnull
	@Override
	public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand, @Nonnull IModelData extraData) {

//        RenderType layer = MinecraftForgeClient.getRenderLayer();
//
//        //BlockState mimic = extraData.getData(FancyBlockTile.MIMIC);
//      BlockState mimic = null;
//        if (mimic != null && !(mimic.getBlock() instanceof FancyBlock)) {
//            if (layer == null || RenderTypeLookup.canRenderInLayer(mimic, layer)) {
//                IBakedModel model = Minecraft.getInstance().getBlockRendererDispatcher().getBlockModelShapes().getModel(mimic);
//                try {
//                    return model.getQuads(mimic, side, rand, EmptyModelData.INSTANCE);
//                } catch (Exception e) {
//                    return Collections.emptyList();
//                }
//            }
//            return Collections.emptyList();
//        }
//
//        if (side != null || (layer != null && !layer.equals(RenderType.getSolid()))) {
//            return Collections.emptyList();
//        }

		TextureAtlasSprite texture = getTexture();
		List<BakedQuad> quads = new ArrayList<>();

//        double l = .2;
//        double r = 1-.2;
//        quads.add(createQuad(v(l, r, l), v(l, r, r), v(r, r, r), v(r, r, l), texture)); // top
//        quads.add(createQuad(v(l, l, l), v(r, l, l), v(r, l, r), v(l, l, r), texture)); // bottom
//        quads.add(createQuad(v(r, r, r), v(r, l, r), v(r, l, l), v(r, r, l), texture)); // east
//        quads.add(createQuad(v(l, r, l), v(l, l, l), v(l, l, r), v(l, r, r), texture)); // west
//        quads.add(createQuad(v(r, r, l), v(r, l, l), v(l, l, l), v(l, r, l), texture)); // north
//        quads.add(createQuad(v(l, r, r), v(l, l, r), v(r, l, r), v(r, r, r), texture)); // south

		double w = 0.2; // cube width
		double x = 0.8 * new Random().nextDouble(); // cube x offset
		double y = 0.8 * new Random().nextDouble(); // cube y offset
		double z = 0.8 * new Random().nextDouble(); // cube z offset
		double xw = w + x;
		double zw = w + z;
		double yw = w + y;

		// y value is constant in quad
		// v2 and v4 are swapped for over quads
		quads.add(createQuad(v(x, yw, z), v(x, yw, zw), v(xw, yw, zw), v(xw, yw, z), texture)); // correct top
		quads.add(createQuad(v(x, y, z), v(xw, y, z), v(xw, y, zw), v(x, y, zw), texture)); // correct bottom

		// z value is constant in quad
		// v1 and v4, v2 and v3 are swapped over quads
		quads.add(createQuad(v(xw, yw, z), v(xw, y, z), v(x, y, z), v(x, yw, z), texture)); // correct north
		quads.add(createQuad(v(x, yw, zw), v(x, y, zw), v(xw, y, zw), v(xw, yw, zw), texture)); // correct south

		// top left -> bottom left -> bottom right -> top right
		// x value is constant in quad
		// v1 and v4, v2 and v3 are swapped over quads
		quads.add(createQuad(v(xw, yw, zw), v(xw, y, zw), v(xw, y, z), v(xw, yw, z), texture)); // correct east
		quads.add(createQuad(v(x, yw, z), v(x, y, z), v(x, y, zw), v(x, yw, zw), texture)); // correct west

		return quads;
	}

	private void simpleCube() {
		TextureAtlasSprite texture = getTexture();
		List<BakedQuad> quads = new ArrayList<>();

		double w = 0.2; // cube width

		// y value is constant in quad
		// v2 and v4 are swapped for over quads
		quads.add(createQuad(v(0, w, 0), v(0, w, w), v(w, w, w), v(w, w, 0), texture)); // correct top
		quads.add(createQuad(v(0, 0, 0), v(w, 0, 0), v(w, 0, w), v(0, 0, w), texture)); // correct bottom

		// z value is constant in quad
		// v1 and v4, v2 and v3 are swapped over quads
		quads.add(createQuad(v(w, w, 0), v(w, 0, 0), v(0, 0, 0), v(0, w, 0), texture)); // correct north
		quads.add(createQuad(v(0, w, w), v(0, 0, w), v(w, 0, w), v(w, w, w), texture)); // correct south

		// top left -> bottom left -> bottom right -> top right
		// x value is constant in quad
		// v1 and v4, v2 and v3 are swapped over quads
		quads.add(createQuad(v(w, w, w), v(w, 0, w), v(w, 0, 0), v(w, w, 0), texture)); // correct east
		quads.add(createQuad(v(0, w, 0), v(0, 0, 0), v(0, 0, w), v(0, w, w), texture)); // correct west
	}

	@Override
	public boolean isAmbientOcclusion() {
		return true;
	}

	@Override
	public boolean isGui3d() {
		return false;
	}

	@Override
	public boolean isBuiltInRenderer() {
		return false;
	}

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return getTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return ItemOverrideList.EMPTY;
	}

	@Override
	public ItemCameraTransforms getItemCameraTransforms() {
		return ItemCameraTransforms.DEFAULT;
	}
}
