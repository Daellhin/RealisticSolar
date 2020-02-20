package com.daellhin.realisticsolar.blocks.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class MachineBlock extends Block {
    // abstract class for blocks with GUI, tileEntity, customModel and facing blockState
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.values());

    public MachineBlock(Properties properties) {
	super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
	return true;
    }

    @Override
    public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
	if (!world.isRemote) {
	    TileEntity tileEntity = world.getTileEntity(pos);
	    if (tileEntity instanceof INamedContainerProvider) {
		NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
	    } else {
		throw new IllegalStateException("The named container provider is missing!");
	    }
	}
	return true;
    }

    public static Direction getFacingFromEntity(BlockPos clickedBlock, LivingEntity entity) {
	return Direction.getFacingFromVector((float) (entity.posX - clickedBlock.getX()), (float) (entity.posY - clickedBlock.getY()), (float) (entity.posZ - clickedBlock.getZ()));
    }
    
    @Override
    public BlockRenderType getRenderType(BlockState state) {
	return BlockRenderType.MODEL;
    }
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
	return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }
}
