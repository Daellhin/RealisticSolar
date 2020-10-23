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

public class ArcFurnaceControllerBlock extends MultiBlockControllerBlock {

	public static final String REGNAME = "arc_furnace_controller_block";
	public static final EnumProperty<ArcFurnaceMultiblockPart> ARCFURNACEPART = EnumProperty.create("type", ArcFurnaceMultiblockPart.class);

	public ArcFurnaceControllerBlock() {
		super(new BlockBuilder().basicMachineProperties().tileEntitySupplier(ArcFurnaceTile::new));
		setDefaultState(stateContainer.getBaseState().with(BlockStateProperties.FACING, Direction.NORTH).with(BlockStateProperties.POWERED, false)
				.with(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED));
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(BlockStateProperties.FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED, ARCFURNACEPART);
	}

	@Override
	public boolean isMultiblockFormed(BlockState state) {
		return state.get(ARCFURNACEPART) != ArcFurnaceMultiblockPart.UNFORMED;
	}

	@Override
	public boolean isMultiblockValid(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.FACING).getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			if (part.getBlock() != world.getBlockState(currentPos).getBlock()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void formMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.FACING).getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			world.setBlockState(currentPos, world.getBlockState(currentPos).with(ARCFURNACEPART, ArcFurnaceMultiblockPart.valueOf(part.name()))
					.with(BlockStateProperties.FACING, state.get(BlockStateProperties.FACING)), 3);
		}
	}

	@Override
	public void destroyMultiblock(BlockState state, World world, BlockPos pos) {
		Direction facing = state.get(BlockStateProperties.FACING).getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			BlockState currentState = world.getBlockState(currentPos);

			// check if block has not been removed
			if (currentState.getProperties().contains(ARCFURNACEPART)) {
				world.setBlockState(currentPos, world.getBlockState(currentPos).with(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED), 3);
			}
		}
	}
}