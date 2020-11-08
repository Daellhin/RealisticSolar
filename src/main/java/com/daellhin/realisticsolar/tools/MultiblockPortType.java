package com.daellhin.realisticsolar.tools;

public enum MultiblockPortType {
	ITEM_INPUT("ITEM", "INPUT"),
	ITEM_OUTPUT("ITEM", "OUTPUT");

	private final String function;
	private final String capability;

	MultiblockPortType(String function, String capability) {
		this.function = function;
		this.capability = capability;
	}

	// getters
	public String getFunction() {
		return function;
	}

	public String getCapability() {
		return capability;
	}

}