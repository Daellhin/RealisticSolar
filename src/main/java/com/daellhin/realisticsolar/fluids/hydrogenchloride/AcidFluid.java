package com.daellhin.realisticsolar.fluids.hydrogenchloride;

import net.minecraft.fluid.IFluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class AcidFluid extends ForgeFlowingFluid{

	protected AcidFluid(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public boolean isSource(IFluidState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLevel(IFluidState p_207192_1_) {
		// TODO Auto-generated method stub
		return 0;
	}

}
