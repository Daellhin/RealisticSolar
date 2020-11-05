package com.daellhin.realisticsolar.fluids.handling;

public enum TankAction {
	FILL(true, false),
	DRAIN(false, true),
	BOTH(true, true),
	NONE(false, false);

	private final boolean fill;
	private final boolean drain;

	TankAction(boolean fill, boolean drain) {
		this.fill = fill;
		this.drain = drain;
	}

	public boolean canFill() {
		return fill;
	}

	public boolean canDrain() {
		return drain;
	}
}