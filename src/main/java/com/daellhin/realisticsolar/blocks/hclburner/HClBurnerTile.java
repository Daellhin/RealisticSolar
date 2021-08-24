package com.daellhin.realisticsolar.blocks.hclburner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Nullable;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.fluids.ModFluids;
import com.daellhin.realisticsolar.fluids.handling.CustomFluidTank;
import com.daellhin.realisticsolar.fluids.handling.TankContainer;
import com.daellhin.realisticsolar.tools.enums.InOutAction;

import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.resources.IResource;
import net.minecraft.resources.IResourceManager;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;

public class HClBurnerTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
	private static final int TANK_SIZE = 10 * 1000;
	private static final int USAGE = 10;

	private CustomFluidTank inputHydrogenTank = new CustomFluidTank(TANK_SIZE, "input_hydrogen").setTankAction(InOutAction.INPUT)
			.onContentsChanged(() -> onContentsChanged())
			.setValidator(fluidStack -> fluidStack.getFluid()
					.equals(ModFluids.HYDROGEN.getSource()));
	private CustomFluidTank inputChlorideTank = new CustomFluidTank(TANK_SIZE, "input_chloride").setTankAction(InOutAction.INPUT)
			.onContentsChanged(() -> onContentsChanged())
			.setValidator(fluidStack -> fluidStack.getFluid()
					.equals(ModFluids.CHLORIDE.getSource()));
	private CustomFluidTank inputWaterTank = new CustomFluidTank(TANK_SIZE, "input_water").setTankAction(InOutAction.INPUT)
			.onContentsChanged(() -> onContentsChanged())
			.setValidator(fluidStack -> fluidStack.getFluid()
					.equals(Fluids.WATER));
	private CustomFluidTank outputHClTank = new CustomFluidTank(TANK_SIZE, "output_hcl").setTankAction(InOutAction.OUTPUT)
			.onContentsChanged(() -> onContentsChanged())
			.setValidator(fluidStack -> fluidStack.getFluid()
					.equals(ModFluids.HYDROGEN_CHLORIDE.getSource()));

	private TankContainer tankContainer = new TankContainer();

	private int progress;

	public HClBurnerTile() {
		super(ModBlocks.HCL_BURNER_TILE.get());
		tankContainer.add(inputHydrogenTank, Direction.EAST);
		tankContainer.add(inputChlorideTank, Direction.WEST);
		tankContainer.add(inputWaterTank, Direction.NORTH);
		tankContainer.add(outputHClTank, Direction.SOUTH);
	}
	
	public static IResource getResource(ResourceLocation location) throws IOException {
        IResourceManager manager = Minecraft.getInstance().getResourceManager();
        IResource res = manager.getResource(location);
        System.out.println(res.getLocation());
        return res;
}

	@Override
	public void tick() {
		if (!world.isRemote) {
			if (progress > 0) {
				progress--;
				if (progress == 0) {
					inputHydrogenTank.drainForced(USAGE, FluidAction.EXECUTE);
					inputChlorideTank.drainForced(USAGE, FluidAction.EXECUTE);
					inputWaterTank.drainForced(USAGE, FluidAction.EXECUTE);
					outputHClTank.fillForced(new FluidStack(ModFluids.HYDROGEN_CHLORIDE.getSource(), USAGE), FluidAction.EXECUTE);
				}
				return;
			} else {
				if (inputHydrogenTank.canDrain(USAGE) && inputChlorideTank.canDrain(USAGE) && inputWaterTank.canDrain(USAGE) && outputHClTank
						.canAdd(USAGE)) {
					progress = 10;
				}
			}

			BlockState blockState = world.getBlockState(pos);
			if (blockState.get(BlockStateProperties.POWERED) != progress > 0) {
				world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, progress > 0), 3);
			}
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side) {
		if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
			return tankContainer.getCapabilityForSide(side)
					.cast();
		}
		return super.getCapability(capability, side);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		return write(super.getUpdateTag());
	}

	@Nullable
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket packet) {
		read(packet.getNbtCompound());
	}

	@Override
	public void read(CompoundNBT tag) {
		super.read(tag);
		tankContainer.readFromNBT(tag);
	}

	@Override
	public CompoundNBT write(CompoundNBT tag) {
		tag = super.write(tag);
		tankContainer.writeToNBT(tag);
		return super.write(tag);
	}

	@Override
	public ITextComponent getDisplayName() {
		return new StringTextComponent(getType().getRegistryName()
				.getPath());
	}

	@Override
	public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new HClBurnerContainer(i, world, pos, playerInventory, playerEntity);
	}

	private void onContentsChanged() {
		BlockState state = world.getBlockState(pos);
		world.notifyBlockUpdate(pos, state, state, 3);
		markDirty();
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public CustomFluidTank getInputHydrogenTank() {
		return inputHydrogenTank;
	}

	public CustomFluidTank getInputChlorideTank() {
		return inputChlorideTank;
	}

	public CustomFluidTank getInputWaterTank() {
		return inputWaterTank;
	}

	public CustomFluidTank getOutputHClTank() {
		return outputHClTank;
	}

}