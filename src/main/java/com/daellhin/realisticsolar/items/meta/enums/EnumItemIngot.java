package com.daellhin.realisticsolar.items.meta.enums;

public enum EnumItemIngot {
	Aluminium(0, "itemAluminium"),
	Boron(1, "itemBoron"),
	Phosphorus(2, "itemPhosphorus"),
	Silver(3, "itemSilver");

	private final int meta;
	private final String name;
	
	private EnumItemIngot(int meta, String name) {
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
