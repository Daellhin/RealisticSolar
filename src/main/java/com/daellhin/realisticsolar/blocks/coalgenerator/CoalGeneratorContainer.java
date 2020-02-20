package com.daellhin.realisticsolar.blocks.coalgenerator;

import static com.daellhin.realisticsolar.blocks.ModBlocks.COALGENERATOR_CONTAINER;
import com.daellhin.realisticsolar.blocks.ModBlocks;
import com.daellhin.realisticsolar.blocks.base.PlayerInventoryContainer;
import com.daellhin.realisticsolar.tools.CustomEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class CoalGeneratorContainer extends PlayerInventoryContainer {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventory;

    public CoalGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
	super(COALGENERATOR_CONTAINER, windowId);
	tileEntity = world.getTileEntity(pos);
	this.playerEntity = player;
	this.playerInventory = new InvWrapper(playerInventory);
	tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
	    addSlot(new SlotItemHandler(h, 0, 80, 20));
	});
	layoutPlayerInventorySlots(this.playerInventory, 8, 84);
	trackInt(new IntReferenceHolder() {

	    @Override
	    public int get() {
		return getEnergy();
	    }

	    @Override
	    public void set(int value) {
		tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((CustomEnergyStorage) h).setEnergy(value));
	    }
	});
    }

    public int getEnergy() {
	return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	ItemStack itemstack = ItemStack.EMPTY;
	Slot slot = this.inventorySlots.get(index);
	if (slot != null && slot.getHasStack()) {
	    ItemStack stack = slot.getStack();
	    itemstack = stack.copy();
	    if (index == 0) {
		if (!this.mergeItemStack(stack, 1, 37, true)) {
		    return ItemStack.EMPTY;
		}
		slot.onSlotChange(stack, itemstack);
	    } else {
		if (stack.getItem() == Items.COAL || stack.getItem() == Items.COAL_BLOCK) {
		    if (!this.mergeItemStack(stack, 0, 1, false)) {
			return ItemStack.EMPTY;
		    }
		} else if (index < 28) {
		    if (!this.mergeItemStack(stack, 28, 37, false)) {
			return ItemStack.EMPTY;
		    }
		} else if (index < 37 && !this.mergeItemStack(stack, 1, 28, false)) {
		    return ItemStack.EMPTY;
		}
	    }
	    if (stack.isEmpty()) {
		slot.putStack(ItemStack.EMPTY);
	    } else {
		slot.onSlotChanged();
	    }
	    if (stack.getCount() == itemstack.getCount()) {
		return ItemStack.EMPTY;
	    }
	    slot.onTake(playerIn, stack);
	}
	return itemstack;
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
	return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.COALGENERATOR_BLOCK);
    }
}