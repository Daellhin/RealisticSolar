package com.daellhin.realisticsolar.blocks.meta.enums;

public enum EnumBlockOre {
	AluminiumOre(0, "blockAluminiumOre"), 
	SilverOre(1, "blockSilverOre"),
	PhosphorusOre(2, "blockPhosphorusOre"),
	BoronOre(3, "blockBoronOre"),
	QuartziteOre(4, "blockQuartziteOre");
	
	private final int meta;
	private final String name;
	
	private EnumBlockOre(int meta, String name) {
		this.meta = meta;
		this.name = name;
			
	}
	
	public int getMeta() {
		return meta;
	}
	
	public String getName() {
		return name;
	}

	public static int count() {
		return values().length;
	}

}
