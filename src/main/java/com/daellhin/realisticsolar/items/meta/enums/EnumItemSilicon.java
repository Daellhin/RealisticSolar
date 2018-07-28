package com.daellhin.realisticsolar.items.meta.enums;

public enum EnumItemSilicon {
	MetalSiliconLow(0, "itemMetalSiliconLow"),
	MetalSiliconMed(1, "itemMetalSiliconMed"),
	PolySiliconLow(2, "itemPolySiliconLow"),
	PolySiliconMed(3, "itemPolySiliconMed"),
	PolySiliconHigh(4, "itemPolySiliconHigh"),
	MultiSiliconLow(5, "itemMultiSiliconLow"),
	MultiSiliconMed(6, "itemMultiSiliconMed"),
	MultiSiliconHigh(7, "itemMultiSiliconHigh"),
	MonoSiliconVHigh(8, "itemMonoSiliconVHigh"), 
	MonoSiliconVVHigh(9, "itemMonoSiliconVVHigh");

	private final int meta;
	private final String name;
	
	private EnumItemSilicon(int meta, String name) {
		this.meta = meta;
		this.name = name; 
	}
	
	public int getMeta() {
		return this.meta;
	}

	public String getName() {
		return this.name;
	}
	
	public static int count() {
		return values().length;
	}
	
}