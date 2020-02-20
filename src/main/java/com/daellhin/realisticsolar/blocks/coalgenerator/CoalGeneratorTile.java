package com.daellhin.realisticsolar.blocks.coalgenerator;

import static com.daellhin.realisticsolar.blocks.ModBlocks.COALGENERATOR_TILE;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import com.daellhin.realisticsolar.Config;
import com.daellhin.realisticsolar.blocks.base.GeneratorTile;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class CoalGeneratorTile extends GeneratorTile implements ITickableTileEntity, INamedContainerProvider {

    private LazyOptional<IItemHandler> handler = LazyOptional.of(this::createHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    private int counter;
    private int RFmultiplier;
    private int TimeMultiplier;
    private boolean coalBlock;

    public CoalGeneratorTile() {
	super(COALGENERATOR_TILE);
    }

    @Override
    public void tick() {
	if (!world.isRemote) {
	    if (counter > 0) {
		counter--;
		if (counter <= 0) {
		    if (coalBlock) {
			RFmultiplier = 9;
		    } else {
			RFmultiplier = 1;
		    }
		    energy.ifPresent(e -> ((CustomEnergyStorage) e).addEnergy(Config.COALGENERATOR_GENERATE.get() * RFmultiplier));
		    markDirty();
		}
	    } else {
		handler.ifPresent(h -> {
		    ItemStack stack = h.getStackInSlot(0);
		    if (stack.getItem() == Items.COAL || stack.getItem() == Items.COAL) {
			if (stack.getItem() == Items.COAL) {
			    TimeMultiplier = 1;
			    coalBlock = false;
			} else if (stack.getItem() == Items.COAL_BLOCK) {
			    TimeMultiplier = 9;
			    coalBlock = true;
			}
			h.extractItem(0, 1, false);
			counter = Config.COALGENERATOR_TICKS.get() * TimeMultiplier;
			markDirty();
		    }
		});
	    }
	    BlockState blockState = world.getBlockState(pos);
	    if (blockState.get(BlockStateProperties.POWERED) != counter > 0) {
		world.setBlockState(pos, blockState.with(BlockStateProperties.POWERED, counter > 0), 3);
	    }
	    sendOutPower(energy, Config.COALGENERATOR_SEND.get());
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public void read(CompoundNBT tag) {
	CompoundNBT invTag = tag.getCompound("inv");
	handler.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(invTag));
	CompoundNBT energyTag = tag.getCompound("energy");
	energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
	counter = tag.getInt("counter");
	super.read(tag);
    }

    @SuppressWarnings("unchecked")
    @Override
    public CompoundNBT write(CompoundNBT tag) {
	handler.ifPresent(h -> {
	    CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
	    tag.put("inv", compound);
	});
	energy.ifPresent(h -> {
	    CompoundNBT compound = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
	    tag.put("energy", compound);
	});
	tag.putInt("counter", counter);
	return super.write(tag);
    }

    private IItemHandler createHandler() {
	return new ItemStackHandler(1) {

	    @Override
	    protected void onContentsChanged(int slot) {
		markDirty();
	    }

	    @Override
	    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		return stack.getItem() == Items.COAL || stack.getItem() == Items.COAL_BLOCK;
	    }

	    @Nonnull
	    @Override
	    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
		if (stack.getItem() != Items.COAL && stack.getItem() != Items.COAL_BLOCK) {
		    return stack;
		}
		return super.insertItem(slot, stack, simulate);
	    }
	};
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
	if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
	    return handler.cast();
	}
	if (cap == CapabilityEnergy.ENERGY) {
	    return energy.cast();
	}
	return super.getCapability(cap, side);
    }

    @Override
    public ITextComponent getDisplayName() {
	return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
	return new CoalGeneratorContainer(i, world, pos, playerInventory, playerEntity);
    }

    private IEnergyStorage createEnergy() {
	return new CustomEnergyStorage(Config.COALGENERATOR_MAXPOWER.get(), Config.COALGENERATOR_SEND.get());
    }
}
