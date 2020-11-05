package com.daellhin.realisticsolar.blocks.hclburner;

import com.daellhin.realisticsolar.blocks.base.BaseBlock;
import com.daellhin.realisticsolar.tools.BlockBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
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
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (!world.isRemote) {
			boolean succes = FluidUtil.interactWithFluidHandler(player, hand, world, pos, hit.getFace());
			if (succes) {
				return ActionResultType.SUCCESS;
			} else {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity instanceof INamedContainerProvider) {
					NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
					return ActionResultType.SUCCESS;
				}
			}
		}
		return ActionResultType.SUCCESS;

	}

}
