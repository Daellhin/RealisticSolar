package com.daellhin.realisticsolar.items.meta.enums;

public enum EnumItemDust {
    AluminiumDust(0, "itemAluminiumDust"), SilverDust(1, "itemSilverDust"), MetalSiliconDust(2, "itemMetalSiliconDust");

    private final int meta;
    private final String name;

    private EnumItemDust(int meta, String name) {
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
