package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.items.ModItems;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class SiemensReactorBottomBlock extends BaseBlock {

	public static final String REGNAME = "siemens_reactor_bottom_block";
	public static final BooleanProperty FORMED = BooleanProperty.create("formed");

	public SiemensReactorBottomBlock() {
		super(new BlockBuilder().basicProperties().tileEntitySupplier(SiemensReactorTile::new).shape(Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
		setDefaultState(stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH).with(BlockStateProperties.POWERED, false).with(FORMED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(BlockStateProperties.FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED, FORMED);
	}

	@Override
	public ActionResultType onBlockActivated(BlockState blockState, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult) {
		if (!world.isRemote) {
			// forming
			if (player.getHeldItem(hand).getItem() == ModItems.WRENCH_ITEM.get()) {
				if (blockState.get(FORMED)) {
					player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Multiblock is already formed"));
				} else {
					BlockState blockStateAbove = world.getBlockState(pos.add(0, 1, 0));
					if (blockStateAbove.getBlock() == ModBlocks.SIEMENSREACTOR_TOP_BLOCK.get()) {
						world.setBlockState(pos, blockState.with(FORMED, true), 3);
						player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Formed the multiblock"));
					} else {
						player.sendMessage(new StringTextComponent(TextFormatting.RED + "Unable to form multiblock (invalid block)"));
					}
				}
			}
			// open gui
			else {
				if (blockState.get(FORMED)) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity instanceof INamedContainerProvider) {
						player.sendMessage(new StringTextComponent(TextFormatting.GREEN + "Open gui"));
						NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
					}
				}
			}
		}
		return ActionResultType.SUCCESS;
	}

}