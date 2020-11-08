package com.daellhin.realisticsolar.tools.enums;

public enum MultiblockPortType {
	ITEM_INPUT("item", InOutAction.INPUT),
	ITEM_OUTPUT("item", InOutAction.OUTPUT),
	ENERGY_INPUT("energy", InOutAction.INPUT);

	private final String function;
	private final InOutAction action;

	MultiblockPortType(String function, InOutAction action) {
		this.function = function;
		this.action = action;
	}

	// getters
	public String getFunction() {
		return function;
	}

	public InOutAction getAction() {
		return action;
	}

}