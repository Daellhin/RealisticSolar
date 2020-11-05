package com.daellhin.realisticsolar.blocks.arcfurnace;

import com.daellhin.realisticsolar.blocks.arcfurnace.enums.ArcFurnaceMultiblockPart;
import com.daellhin.realisticsolar.blocks.arcfurnace.enums.ArcFurnaceMultiblockPattern;
import com.daellhin.realisticsolar.blocks.base.MultiBlockControllerBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

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
				.tileEntitySupplier(ArcFurnaceTile::new));
		setDefaultState(stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(BlockStateProperties.POWERED, false)
				.with(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState()
				.with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED, ARCFURNACEPART);
	}

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

	@Override
	public boolean isMultiblockFormed(BlockState state) {
		return true;
	}

	@Override
	public boolean isMultiblockValid(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING)
				.getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx())
					.up(part.getDy())
					.offset(facing.rotateY(), part.getDz());
			if (part.getBlock() != world.getBlockState(currentPos)
					.getBlock()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void formMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING)
				.getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx())
					.up(part.getDy())
					.offset(facing.rotateY(), part.getDz());
			world.setBlockState(currentPos, world.getBlockState(currentPos)
					.with(ARCFURNACEPART, ArcFurnaceMultiblockPart.valueOf(part.name()))
					.with(BlockStateProperties.HORIZONTAL_FACING, state.get(BlockStateProperties.HORIZONTAL_FACING)), 3);
		}
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING)
				.getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx())
					.up(part.getDy())
					.offset(facing.rotateY(), part.getDz());
			BlockState currentState = world.getBlockState(currentPos);

			// check if block has not been removed
			if (currentState.getProperties()
					.contains(ARCFURNACEPART)) {
				world.setBlockState(currentPos, world.getBlockState(currentPos)
						.with(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED), 3);
			}
		}
	}
}