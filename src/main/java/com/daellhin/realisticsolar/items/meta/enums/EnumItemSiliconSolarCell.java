package com.daellhin.realisticsolar.items.meta.enums;

public enum EnumItemSiliconSolarCell {
    MultiSolarCellLow(0, "itemMultiSolarCellLow"), MultiSolarCellMed(1, "itemMultiSolarCellMed"),
    MultiSolarCellHigh(2, "itemMultiSolarCellHigh"), MonoSolarCellVHigh(3, "itemMonoSolarCellVHigh"),
    MonoSolarCellVVHigh(4, "itemMonoSolarCellVVHigh");

    private final int meta;
    private final String name;

    private EnumItemSiliconSolarCell(int meta, String name) {
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
