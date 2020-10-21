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
	public boolean isMultiblockFormed(BlockState blockState) {
		return blockState.get(ARCFURNACEPART) != ArcFurnaceMultiblockPart.UNFORMED;
	}

	@Override
	public boolean isMultiblockValid(BlockState blockState, World world, BlockPos pos) {
		Direction facing = blockState.get(BlockStateProperties.FACING).getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			System.out.printf("dx: %2d, dy: %2d, dz: %2d = %s ? %s%n", part.getDx(), part.getDy(), part.getDz(), world.getBlockState(currentPos).getBlock(), part.getBlock());
			if (part.getBlock() != world.getBlockState(currentPos).getBlock()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void formMultiblock(BlockState blockState, World world, BlockPos pos) {
		Direction facing = blockState.get(BlockStateProperties.FACING).getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			world.setBlockState(currentPos, world.getBlockState(currentPos).with(ARCFURNACEPART, ArcFurnaceMultiblockPart.valueOf(part.name()))
					.with(BlockStateProperties.FACING, blockState.get(BlockStateProperties.FACING)), 3);
		}
	}

	@Override
	public void destroyMultiblock(BlockState blockState, World world, BlockPos pos) {
		Direction facing = blockState.get(BlockStateProperties.FACING).getOpposite();

		for (ArcFurnaceMultiblockPattern part : ArcFurnaceMultiblockPattern.values()) {
			BlockPos currentPos = pos.offset(facing, part.getDx()).up(part.getDy()).offset(facing.rotateY(), part.getDz());
			world.setBlockState(currentPos, world.getBlockState(currentPos).with(ARCFURNACEPART, ArcFurnaceMultiblockPart.UNFORMED), 3);
		}
	}
}