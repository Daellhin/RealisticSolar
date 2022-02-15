package com.daellhin.realisticsolar.blocks.siemensreactor.enums;

import net.minecraft.util.IStringSerializable;

public enum SiemensReactorMultiblockPart implements IStringSerializable {
	//@formatter:off
	
	// unformed
	UNFORMED("unformed", 0, 0, 0),
	
	// x = 0
	IP0P0P0("ip0p0p0", 0, 0, 0),
	IP0P1P0("ip0p1p0", 0, 1, 0);

	
	//@formatter:on

	private final String name;
	private final int dx;
	private final int dy;
	private final int dz;

	SiemensReactorMultiblockPart(String name, int dx, int dy, int dz) {
		this.name = name;
		this.dx = dx;
		this.dy = dy;
		this.dz = dz;
	}

	// getters
	@Override
	public String getSerializedName() {
		return name;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public int getDz() {
		return dz;
	}
}