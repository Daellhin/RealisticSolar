package com.daellhin.realisticsolar.blocks.siemensreactor;

import com.daellhin.realisticsolar.blocks.base.MultiBlockControllerBlock;
import com.daellhin.realisticsolar.blocks.siemensreactor.enums.SiemensReactorMultiblockPart;
import com.daellhin.realisticsolar.blocks.siemensreactor.enums.SiemensReactorMultiblockPattern;
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

public class SiemensReactorControllerBlock extends MultiBlockControllerBlock {

	public static final String REGNAME = "siemens_reactor_controller_block";
	public static final EnumProperty<SiemensReactorMultiblockPart> SIEMENSREACTORPART = EnumProperty.create("type", SiemensReactorMultiblockPart.class);

	public SiemensReactorControllerBlock() {
		super(new BlockBuilder().basicProperties().tileEntitySupplier(SiemensReactorTile::new)
				.shape(Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)));
		setDefaultState(stateContainer.getBaseState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).with(BlockStateProperties.POWERED, false)
				.with(SIEMENSREACTORPART, SiemensReactorMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.POWERED, SIEMENSREACTORPART);
	}

	@Override
	public boolean isMultiblockFormed(BlockState state) {
		return state.get(SIEMENSREACTORPART) != SiemensReactorMultiblockPart.UNFORMED;
	}

	@Override
	public boolean isMultiblockValid(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING).getOpposite();

		for (SiemensReactorMultiblockPattern part : SiemensReactorMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			if (part.getBlock() != world.getBlockState(currentPos).getBlock()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void formMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING).getOpposite();

		for (SiemensReactorMultiblockPattern part : SiemensReactorMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			world.setBlockState(currentPos, world.getBlockState(currentPos).with(SIEMENSREACTORPART, SiemensReactorMultiblockPart.valueOf(part.name()))
					.with(BlockStateProperties.HORIZONTAL_FACING, state.get(BlockStateProperties.HORIZONTAL_FACING)), 3);
		}
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.HORIZONTAL_FACING).getOpposite();

		for (SiemensReactorMultiblockPattern part : SiemensReactorMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			BlockState currentState = world.getBlockState(currentPos);

			// check if block has not been removed
			if (currentState.getProperties().contains(SIEMENSREACTORPART)) {
				world.setBlockState(currentPos, world.getBlockState(currentPos).with(SIEMENSREACTORPART, SiemensReactorMultiblockPart.UNFORMED), 3);
			}
		}
		
	}

}