package com.daellhin.realisticsolar.tile.base;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class Tile extends TileEntity{
    protected long ticks = 0L;

    /** Called on the first world tick of this tile */
    public void start(){

    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if(ticks == 0){
            start();
        }
        else if(ticks + 1 == Long.MAX_VALUE){
            ticks = 0;
        }
        ticks++;
    }

    public World world(){
        return getWorldObj();
    }

    public int xi() { return xCoord;}
    public int yi() { return yCoord;}
    public int zi() { return zCoord;}
}