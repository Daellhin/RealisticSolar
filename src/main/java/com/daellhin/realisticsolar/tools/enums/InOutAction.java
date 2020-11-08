package com.daellhin.realisticsolar.tools.enums;

public enum InOutAction {
	INPUT(true, false),
	OUTPUT(false, true),
	BOTH(true, true),
	NONE(false, false);

	private final boolean input;
	private final boolean output;

	InOutAction(boolean input, boolean output) {
		this.input = input;
		this.output = output;
	}

	public boolean canInput() {
		return input;
	}

	public boolean canOutput() {
		return output;
	}
}