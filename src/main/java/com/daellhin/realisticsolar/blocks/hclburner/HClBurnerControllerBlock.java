package com.daellhin.realisticsolar.blocks.hclburner;

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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.network.NetworkHooks;

public class HClBurnerControllerBlock extends BaseBlock {

	public static final String REGNAME = "hcl_burner_controller_block";

	public HClBurnerControllerBlock() {
		super(new BlockBuilder().basicMachineProperties()
				.tileEntitySupplier(HClBurnerTile::new));
		setDefaultState(stateContainer.getBaseState()
				.with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
				.with(BlockStateProperties.POWERED, false));
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			boolean succes = FluidUtil.interactWithFluidHandler(player, hand, world, pos, hit.getFace());
			if (!succes) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity instanceof INamedContainerProvider) {
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.SUCCESS;

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

}
