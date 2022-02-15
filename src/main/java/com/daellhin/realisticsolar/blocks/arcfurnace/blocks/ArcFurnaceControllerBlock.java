package com.daellhin.realisticsolar.blocks.arcfurnace.blocks;

import com.daellhin.realisticsolar.blocks.arcfurnace.enums.ArcFurnaceMultiblockPart;
import com.daellhin.realisticsolar.blocks.arcfurnace.enums.ArcFurnaceMultiblockPattern;
import com.daellhin.realisticsolar.blocks.arcfurnace.tiles.ArcFurnaceControllerTile;
import com.daellhin.realisticsolar.blocks.base.MultiBlockControllerBlock;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;
import com.daellhin.realisticsolar.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class ArcFurnaceControllerBlock extends MultiBlockControllerBlock {

	public static final String REGNAME = "arc_furnace_controller_block";
	public static final EnumProperty<ArcFurnaceMultiblockPart> ARCFURNACEPART = EnumProperty.create("type", ArcFurnaceMultiblockPart.class);

	public ArcFurnaceControllerBlock() {
		super(new BlockBuilder().basicMachineProperties()
				.tileEntitySupplier(ArcFurnaceControllerTile::new));
//		setDefaultState(stateContainer.getBaseState()
//				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
//				.setValue(BlockStateProperties.POWERED, false)
//				.with(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, context.getNearestLookingDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED, ARCFURNACEPART);
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

	@Override
	public boolean isMultiblockFormed(BlockState state) {
		return state.getValue(ARCFURNACEPART) != ArcFurnaceMultiblockPart.UNFORMED;
	}

	@Override
	public boolean isMultiblockValid(BlockState state, World world, BlockPos pos) {
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)
				;

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.relative(facing, part.getDx())
					.above(part.getDy())
					.relative(facing.getClockWise(), part.getDz());

			if (part.getBlock() != world.getBlockState(currentPos)
					.getBlock()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void formMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)
				;

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.relative(facing, part.getDx())
					.above(part.getDy())
					.relative(facing.getClockWise(), part.getDz());
			world.setBlock(currentPos, world.getBlockState(currentPos)
					.setValue(ARCFURNACEPART, ArcFurnaceMultiblockPart.valueOf(part.name()))
					.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING)), 3);
		}
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)
				;

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.relative(facing, part.getDx())
					.above(part.getDy())
					.relative(facing.getClockWise(), part.getDz());
			BlockState currentState = world.getBlockState(currentPos);

			// check if block has not been removed(player, explosion, piston)
			if (currentState.getProperties()
					.contains(ARCFURNACEPART)) {
				world.setBlock(currentPos, world.getBlockState(currentPos)
						.setValue(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED), 3);
			}
		}
	}
}