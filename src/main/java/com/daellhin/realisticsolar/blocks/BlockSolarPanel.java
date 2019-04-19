package com.daellhin.realisticsolar.blocks;

import com.daellhin.realisticsolar.tiles.TileEntitySolarPanel;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockSolarPanel extends BlockContainer
{
    public BlockSolarPanel(Properties properties){
    	super(properties);
    }

    @Override
    public boolean onBlockActivated(IBlockState state, World world, BlockPos pos, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (!player.getHeldItem(hand).isEmpty())
        {
            return false;
        }
        if (!world.isRemote)
        {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileEntitySolarPanel)
            {
                player.sendMessage(new TextComponentString(I18n.format("solarpanel.chat.power") + " " + ((TileEntitySolarPanel) tile).powerStorage.getEnergyStored()));
            }
        }
        return true;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader world)
    {
        return new TileEntitySolarPanel();
    }
}
