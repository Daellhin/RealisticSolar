package com.daellhin.realisticsolar.fluids.handling;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

import com.daellhin.realisticsolar.tools.enums.InOutAction;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class CustomFluidTank extends FluidTank {

	private InOutAction tankAction;
	private Runnable onContentsChanged;
	private String name;

	public CustomFluidTank(int capacity, String name) {
		super(capacity);
		this.name = name;

		// defaults
		this.tankAction = InOutAction.BOTH;
		this.onContentsChanged = () -> {};
	}

	public CustomFluidTank setTankAction(InOutAction tankAction) {
		this.tankAction = tankAction;
		return this;
	}

	public CustomFluidTank onContentsChanged(Runnable onContentChange) {
		// System.out.println("Contents changed " + name);
		this.onContentsChanged = onContentChange;
		return this;
	}

	public InOutAction getTankAction() {
		return tankAction;
	}

	@Override
	public CustomFluidTank setValidator(Predicate<FluidStack> validator) {
		return (CustomFluidTank) super.setValidator(validator);
	}

	@Override
	public FluidTank readFromNBT(CompoundNBT compound) {
		return super.readFromNBT(compound.getCompound(name));
	}

	@Override
	public CompoundNBT writeToNBT(CompoundNBT nbt) {
		return super.writeToNBT(nbt);
	}

	public void putTag(CompoundNBT tag, CompoundNBT tankNBT) {
		tag.put(name, tankNBT);
	}

	@Override
	protected void onContentsChanged() {
		onContentsChanged.run();
	}

	@Override
	public int fill(FluidStack resource, FluidAction action) {
		return getTankAction().canInput() ? super.fill(resource, action) : 0;
	}

	@Nonnull
	@Override
	public FluidStack drain(FluidStack resource, FluidAction action) {
		return getTankAction().canOutput() ? drainInternal(resource, action) : FluidStack.EMPTY;
	}

	private FluidStack drainInternal(FluidStack resource, FluidAction action) {
		if (resource.isEmpty() || !resource.isFluidEqual(fluid)) {
			return FluidStack.EMPTY;
		}
		return drain(resource.getAmount(), action);
	}

	@Nonnull
	@Override
	public FluidStack drain(int maxDrain, FluidAction action) {
		return getTankAction().canOutput() ? drainInternal(maxDrain, action) : FluidStack.EMPTY;
	}

	@Nonnull
	private FluidStack drainInternal(int maxDrain, FluidAction action) {
		int drained = maxDrain;
		if (fluid.getAmount() < drained) {
			drained = fluid.getAmount();
		}
		FluidStack stack = new FluidStack(fluid, drained);
		if (action.execute() && drained > 0) {
			fluid.shrink(drained);
			onContentsChanged();
		}
		return stack;
	}

	public int fillForced(FluidStack resource, FluidAction action) {
		return super.fill(resource, action);
	}

	public boolean canAdd(int amount) {
		return getFluidAmount() + amount <= getCapacity();
	}

	public boolean canDrain(int amount) {
		return getFluidAmount() - amount >= 0;
	}

	@Nonnull
	public FluidStack drainForced(FluidStack resource, FluidAction action) {
		if (resource.isEmpty() || !resource.isFluidEqual(fluid)) {
			return FluidStack.EMPTY;
		}
		return drainForced(resource.getAmount(), action);
	}

	@Nonnull
	public FluidStack drainForced(int maxDrain, FluidAction action) {
		return drainInternal(maxDrain, action);
	}

}
