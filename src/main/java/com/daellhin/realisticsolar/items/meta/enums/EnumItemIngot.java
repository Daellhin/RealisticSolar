package com.daellhin.realisticsolar.items.meta.enums;

public enum EnumItemIngot {
    Aluminium(0, "itemAluminium"),Silver(1, "itemSilver"), Boron(2, "itemBoron"), Phosphorus(3, "itemPhosphorus");

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
