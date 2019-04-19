package com.daellhin.realisticsolar.tiles;

import com.daellhin.realisticsolar.init.ModTileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntitySolarPanel extends TileEntity implements ITickable{

	public TileEntitySolarPanel() {
		super(ModTileEntities.tile_solar_panel);

	}
	
	public static int POWER_CAP = 1000;
    public static int POWER_TRANSFER = 10;
    public static int POWER_CREATION = 1;

    public static final String NBT_ENERGY = "energy";

    public EnergyBuffer powerStorage;

    boolean hasLight = true;

    @Override
    public void onLoad(){
        powerStorage = new EnergyBuffer(POWER_CAP, POWER_TRANSFER, POWER_TRANSFER, 0);
    }

    @Override
    public void tick(){
        if(!world.isRemote){
            if (hasLight){
                //Generate power if there is light
                createPower();
            }

            //Output
            if (powerStorage.getEnergyStored() > 0){
                outputPower();
            }
        }
    }

    protected void createPower(){
        //Generate power
        powerStorage.receiveEnergy(POWER_CREATION, false);
        
    }

    /**
     * Outputs power to all 6 sides
     */
    protected void outputPower(){
        //Loop all 6 sides of our block
        for (EnumFacing facing : EnumFacing.values()){
            //Get position with offset
            BlockPos pos = getPos().offset(facing);

            //Check the block is loaded to avoid loading extra chunks
            if (world.isBlockLoaded(pos)){
                //Get tile from the world
                TileEntity tile = world.getTileEntity(pos);

                //Check tile exists
                if (tile != null){
                    LazyOptional<IEnergyStorage> storage = tile.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());

                    //Check to make sure the tile has the energy capability
                    if(storage.isPresent()){
                        //Get power
                        IEnergyStorage ies = storage.orElse(null); //TODO: this might be wonky, check back later when more examples of power caps are available

                        if(ies != null){
                            //Figure out the power we can give, simulate
                            int powerToGive = powerStorage.extractEnergy(Integer.MAX_VALUE, true);

                            //Give power to the tile, get power actually added
                            int powerGiven = ies.receiveEnergy(powerToGive, false);

                            //Drain power given from our tile
                            powerStorage.extractEnergy(powerGiven, false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void read(NBTTagCompound compound){
        super.read(compound);
        compound.setInt(NBT_ENERGY, powerStorage.getEnergyStored());
    }

    @Override
    public NBTTagCompound write(NBTTagCompound compound){
        powerStorage.setEnergy(compound.getInt(NBT_ENERGY));
        return super.write(compound);
    }
    
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, EnumFacing side){
        if(cap == CapabilityEnergy.ENERGY)
            return LazyOptional.of(() -> powerStorage).cast();
        else return LazyOptional.empty();
    }

}