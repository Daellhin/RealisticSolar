package com.daellhin.realisticsolar.blocks.base;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;

/**
 * Base class for creating all blocks
 * 
 */
public abstract class BaseBlock extends Block {

	private final Supplier<TileEntity> tileEntitySupplier;
	private final boolean shiftInformation;
	private final VoxelShape shape;

	public BaseBlock(BlockBuilder builder) {
		super(builder.getProperties());
		this.shiftInformation = builder.getShiftInformation();
		this.tileEntitySupplier = builder.getTileEntitySupplier();
		this.shape = builder.getShape();
	}

	/**
	 * Adds tooltip to the item, shift to show, when present uses the registryName to search in lang file
	 */
	@Override
	public void appendHoverText(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
		if (shiftInformation) {
			if (Screen.hasShiftDown()) {
				list.add(new TranslationTextComponent("information." + this.getRegistryName()
						.getPath()));
			} else {
				list.add(new TranslationTextComponent("information.press_shift"));
			}
		}
	}

	@Override
	public boolean hasTileEntity(BlockState blockState) {
		return tileEntitySupplier != null;
	}

	@Override
	public TileEntity createTileEntity(BlockState blockState, IBlockReader world) {
		if (this.hasTileEntity(blockState)) {
			return tileEntitySupplier.get();
		} else {
			return null;
		}
	}

	// getters

	@Override
	public VoxelShape getShape(BlockState blockState, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if (shape != null) {
			return shape;
		}
		return super.getShape(blockState, worldIn, pos, context);
	}

	// move to class
//	
//	@Override
//	public int getLightValue(BlockState state) {
//		if(state.)
//		return state.getValue(BlockStateProperties.POWERED) ? super.getLightValue(state) : 0;
//	}

}