package com.daellhin.realisticsolar.blocks.fancy;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.IModelData;
import net.minecraftforge.client.model.data.ModelDataMap;
import net.minecraftforge.client.model.data.ModelProperty;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

import static com.daellhin.realisticsolar.blocks.ModBlocks.FANCYBLOCK_TILE;


public class FancyBlockTile extends TileEntity {

    public static final ModelProperty<BlockState> MIMIC = new ModelProperty<>();

    private BlockState mimic;

    public FancyBlockTile() {
        super(FANCYBLOCK_TILE.get());
    }

    public void setMimic(BlockState mimic) {
        this.mimic = mimic;
        markDirty();
        world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
    }

    public BlockState getMimic() {
        return mimic;
    }

    // The getUpdateTag()/handleUpdateTag() pair is called whenever the client receives a new chunk
    // it hasn't seen before. i.e. the chunk is loaded

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        writeMimic(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundNBT tag) {
        // This is actually the default but placed here so you
        // know this is the place to potentially have a lighter read() that only
        // considers things needed client-side
        read(tag);
    }

    // The getUpdatePacket()/onDataPacket() pair is used when a block update happens on the client
    // (a blockstate change or an explicit notificiation of a block update from the server). It's
    // easiest to implement them based on getUpdateTag()/handleUpdateTag()

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        BlockState oldMimic = mimic;
        CompoundNBT tag = pkt.getNbtCompound();
        handleUpdateTag(tag);
        if (!Objects.equals(oldMimic, mimic)) {
            ModelDataManager.requestModelDataRefresh(this);
            world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE + Constants.BlockFlags.NOTIFY_NEIGHBORS);
        }
    }

    @Nonnull
    @Override
    public IModelData getModelData() {
        return new ModelDataMap.Builder()
                .withInitial(MIMIC, mimic)
                .build();
    }

    @Override
    public void read(CompoundNBT tag) {
        super.read(tag);
        readMimic(tag);
    }

    private void readMimic(CompoundNBT tag) {
        if (tag.contains("mimic")) {
            mimic = NBTUtil.readBlockState(tag.getCompound("mimic"));
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        writeMimic(tag);
        return super.write(tag);
    }

    private void writeMimic(CompoundNBT tag) {
        if (mimic != null) {
            tag.put("mimic", NBTUtil.writeBlockState(mimic));
        }
    }
}
