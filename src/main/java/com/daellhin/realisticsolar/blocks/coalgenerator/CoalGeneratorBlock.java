package com.daellhin.realisticsolar.blocks.coalgenerator;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;
import com.daellhin.realisticsolar.util.Utils;

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
		registerDefaultState(defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.setValue(BlockStateProperties.POWERED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, context.getNearestLookingDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED);
	}
	
	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isClientSide) {
			TileEntity tileEntity = world.getBlockEntity(pos);
			if (tileEntity instanceof INamedContainerProvider) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getBlockPos());
				return ActionResultType.SUCCESS;
			}
		}
		return super.use(state, world, pos, player, hand, hit);
	}

	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
			// drops everything in the inventory
			worldIn.getBlockEntity(pos)
					.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
					.ifPresent(h -> {
						for (int i = 0; i < h.getSlots(); i++) {
							//spawnAsEntity(worldIn, pos, h.getStackInSlot(i));
							Utils.dropItemIntoWorld(worldIn, pos, h.getStackInSlot(i));
						}
					});
		}
		super.onRemove(state, worldIn, pos, newState, isMoving);
	}

}