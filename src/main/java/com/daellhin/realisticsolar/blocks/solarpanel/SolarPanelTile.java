package com.daellhin.realisticsolar.blocks.solarpanel;

import static com.daellhin.realisticsolar.blocks.ModBlocks.SOLARPANEL_TILE;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class SolarPanelTile extends TileEntity implements ITickableTileEntity {
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);

    public boolean theSunIsVisible;
    
    private int counter; 
    
    public SolarPanelTile() {
		super(SOLARPANEL_TILE);
	}

    @Override
    public void tick() {
    	if (!world.isRemote) {
    		updateSunState();
    		
    		if(theSunIsVisible) {
            	energy.ifPresent(e -> ((CustomEnergyStorage)e).addEnergy(Config.SOLARPANEL_GENERATE.get()));
            	markDirty();
		         
    		}
	        
    		BlockState blockState = world.getBlockState(pos);
	        if (blockState.get(BlockStateProperties.POWERED) != theSunIsVisible) {
	            world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, theSunIsVisible), 3);
	        }
	        
    		sendOutPower();  
    	}
    }
    private void updateSunState(){
        this.theSunIsVisible = getSkyLight(this.getWorld(), this.pos.up()) > 0.0F;
    }
    
    public static float getSkyLight(World world, BlockPos pos){


        float sunBrightness = limit((float) Math.cos(world.getCelestialAngleRadians(1.0F)) * 2.0F + 0.2F, 0.0F, 1.0F);

        if (!BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.SANDY)) {
            sunBrightness *= (1.0F - world.getRainStrength(1.0F) * 5.0F / 16.0F);
            sunBrightness *= (1.0F - world.getThunderStrength(1.0F) * 5.0F / 16.0F);

            sunBrightness = limit(sunBrightness, 0.0F, 1.0F);
        }

        return world.getLightFor(LightType.SKY, pos) / 15.0F * sunBrightness;
    }
    
    private void sendOutPower() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canReceive()) {
                                        int received = handler.receiveEnergy(Math.min(capacity.get(), Config.SOLARPANEL_SEND.get()), false);
                                        capacity.addAndGet(-received);
                                        ((CustomEnergyStorage) energy).consumeEnergy(received);
                                        markDirty();
                                        return capacity.get() > 0;
                                    } else {
                                        return true;
                                    }
                                }
                        ).orElse(true);
                        if (!doContinue) {
                            return;
                        }
                    }
                }
            }
        });
    }
    
	
	@SuppressWarnings("unchecked")
	@Override
    public void read(CompoundNBT tag) {
        CompoundNBT energyTag = tag.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>)h).deserializeNBT(energyTag));
        
        counter = tag.getInt("counter");
        super.read(tag);
    }
	
    @SuppressWarnings("unchecked")
	@Override
    public CompoundNBT write(CompoundNBT tag) {
        energy.ifPresent(h -> {
            CompoundNBT compound = ((INBTSerializable<CompoundNBT>)h).serializeNBT();
            tag.put("energy", compound);
        });
        tag.putInt("counter", counter);
        return super.write(tag);
    }
    
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
   
	private IEnergyStorage createEnergy() {
        return new CustomEnergyStorage(Config.SOLARPANEL_MAXPOWER.get(), Config.SOLARPANEL_SEND.get());
    }
	
	public static float limit(float value, float min, float max)
    {
        if ((Float.isNaN(value)) || (value <= min))
        {
            return min;
        }

        if (value >= max)
        {
            return max;
        }

        return value;
    }
    
	
}
