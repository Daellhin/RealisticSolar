package com.daellhin.realisticsolar.items.meta.enums;

public enum EnumItemSiliconWafer {
    MultiSiliconWaferLow(0, "itemMultiSiliconWaferLow"), MultiSiliconWaferMed(1, "itemMultiSiliconWaferMed"), MultiSiliconWaferHigh(2, "itemMultiSiliconWaferHigh"),
    MonoSiliconWaferVHigh(3, "itemMonoSiliconWaferVHigh"), MonoSiliconWaferVVHigh(4, "itemMonoSiliconWaferVVHigh"), DopedMultSiliconWaferMed(5, "itemDopedMultiSiliconWaferMed"),
    DopedMultiSiliconWaferHigh(6, "itemDopedMultiSiliconWaferHigh"), DopedMonoSiliconWaferVHigh(7, "itemDopedMonoSiliconWaferVHigh"), DopedMonoSiliconWaferVVHigh(8, "itemDopedMonoSiliconWaferVVHigh");

    private final int meta;
    private final String name;

    private EnumItemSiliconWafer(int meta, String name) {
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
