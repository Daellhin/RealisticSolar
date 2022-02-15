package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.base.MultiBlockControllerBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.enums.SiemensReactorMultiblockPart;
import com.daellhin.realisticsolar.blocks.siemensreactor.enums.SiemensReactorMultiblockPattern;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SiemensReactorControllerBlock extends MultiBlockControllerBlock {

	public static final String REGNAME = "siemens_reactor_controller_block";
	public static final EnumProperty<SiemensReactorMultiblockPart> SIEMENSREACTORPART = EnumProperty
			.create("type", SiemensReactorMultiblockPart.class);

	public SiemensReactorControllerBlock() {
		super(new BlockBuilder().basicProperties()
				.tileEntitySupplier(SiemensReactorTile::new)
				.shape(Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
		registerDefaultState(defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.setValue(BlockStateProperties.POWERED, false)
				.setValue(SIEMENSREACTORPART, SiemensReactorMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.defaultBlockState()
				.setValue(BlockStateProperties.HORIZONTAL_FACING, context.getNearestLookingDirection());
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED, SIEMENSREACTORPART);
	}

	@Override
	public boolean isMultiblockFormed(BlockState state) {
		return state.getValue(SIEMENSREACTORPART) != SiemensReactorMultiblockPart.UNFORMED;
	}

	@Override
	public boolean isMultiblockValid(BlockState state, World world, BlockPos pos) {
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)
				.getOpposite();

		for (SiemensReactorMultiblockPattern part : SiemensReactorMultiblockPattern.values()) {
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
				.getOpposite();

		for (SiemensReactorMultiblockPattern part : SiemensReactorMultiblockPattern.values()) {
			BlockPos currentPos = pos.relative(facing, part.getDx())
					.above(part.getDy())
					.relative(facing.getClockWise(), part.getDz());
			world.setBlock(currentPos, world.getBlockState(currentPos)
					.setValue(SIEMENSREACTORPART, SiemensReactorMultiblockPart.valueOf(part.name()))
					.setValue(BlockStateProperties.HORIZONTAL_FACING, state.getValue(BlockStateProperties.HORIZONTAL_FACING)), 3);
		}
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING)
				.getOpposite();

		for (SiemensReactorMultiblockPattern part : SiemensReactorMultiblockPattern.values()) {
			BlockPos currentPos = pos.relative(facing, part.getDx())
					.above(part.getDy())
					.relative(facing.getClockWise(), part.getDz());
			BlockState currentState = world.getBlockState(currentPos);

			// check if block has not been removed
			if (currentState.getProperties()
					.contains(SIEMENSREACTORPART)) {
				world.setBlock(currentPos, world.getBlockState(currentPos)
						.setValue(SIEMENSREACTORPART, SiemensReactorMultiblockPart.UNFORMED), 3);
			}
		}

	}

}