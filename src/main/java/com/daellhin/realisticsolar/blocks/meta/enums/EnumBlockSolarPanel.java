package com.daellhin.realisticsolar.blocks.meta.enums;

public enum EnumBlockSolarPanel {
	SolarPanelLow(0, "blockSolarPanelLow", 10),
	SolarPanelMed(1, "blockSolarPanelMed", 20),
	SolarPanelHigh(2, "blockSolarPanelHigh", 50),
	SolarPanelVHigh(3, "blockSolarPanelVHigh", 100),
	SolarPanelVVHigh(4, "blockSolarPanelVVHigh", 200);
	
	private final int meta;
	private final String name;
	private final int maxGen;//maxrf per tick
	private final int buffer;//capacity
	
	private EnumBlockSolarPanel(int meta, String name, int MaxGen) {
		this.meta = meta;
		this.name = name;
		this.maxGen = MaxGen;
		this.buffer = MaxGen;
			
	}
	
	public int getMeta() {
		return meta;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMaxGen() {
		return maxGen;
	}

	public int getBuffer() {
		return buffer;
	}

	public static int count() {
		return values().length;
	}
	
}