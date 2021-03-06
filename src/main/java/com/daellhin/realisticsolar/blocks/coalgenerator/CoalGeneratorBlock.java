package com.daellhin.realisticsolar.blocks.coalgenerator;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;

public class CoalGeneratorBlock extends BaseBlock {

	public static final String REGNAME = "coal_generator_block";

	public CoalGeneratorBlock() {
		super(new BlockBuilder().basicMachineProperties()
				.tileEntitySupplier(CoalGeneratorTile::new)
				.addShiftInformation());
		setDefaultState(stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(BlockStateProperties.POWERED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState()
				.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
	}

	@SuppressWarnings("deprecation")
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			TileEntity tileEntity = world.getTileEntity(pos);
			if (tileEntity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
				return ActionResultType.SUCCESS;
			}
		}
		return super.onBlockActivated(state, world, pos, player, hand, hit);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			// drops everything in the inventory
			worldIn.getTileEntity(pos)
					.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
					.ifPresent(h -> {
						for (int i = 0; i < h.getSlots(); i++) {
							spawnAsEntity(worldIn, pos, h.getStackInSlot(i));
						}
					});
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

}