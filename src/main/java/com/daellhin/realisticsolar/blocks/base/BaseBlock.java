package com.daellhin.realisticsolar.blocks.base;

import java.util.List;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * abstract class for creating blocks
 * 
 */
public abstract class BaseBlock extends Block {

	public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.values());
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	private final Supplier<TileEntity> tileEntitySupplier;
	private final String shiftInformation;

	public BaseBlock(BlockBuilder builder) {
		super(builder.getProperties());
		this.shiftInformation = builder.getShiftInformation();
		this.tileEntitySupplier = builder.getTileEntitySupplier();

		setDefaultState(stateContainer.getBaseState().with(FACING, Direction.NORTH).with(POWERED, false));
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
		if (shiftInformation != null) {
			if (Screen.hasShiftDown()) {
				list.add(new TranslationTextComponent("block.information." + shiftInformation));
			} else {
				list.add(new TranslationTextComponent("block.information.press_shift"));
			}
		}
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return tileEntitySupplier.get() != null;
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		if (tileEntitySupplier.get() != null) {
			return tileEntitySupplier.get();
		} else {
			return null;
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
			} else {
				throw new IllegalStateException("The named container provider is missing!");
			}
		}
		return ActionResultType.SUCCESS;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
	}

	// getters
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getLightValue(BlockState state) {
		return state.get(BlockStateProperties.POWERED) ? super.getLightValue(state) : 0;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

}