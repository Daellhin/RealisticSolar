package com.daellhin.realisticsolar.blocks.arcfurnace.enums;

import net.minecraft.util.IStringSerializable;

public enum ArcFurnaceMultiblockPart implements IStringSerializable {
	//@formatter:off
	
	// unformed
	UNFORMED("unformed", 0, 0, 0),
	
	// x = 0
	IP0P0N2("ip0p0n2", 0, 0, -2),
	IP0P0N1("ip0p0n1", 0, 0, -1),
	IP0P0P0("ip0p0p0", 0, 0,  0),
	IP0P0P1("ip0p0p1", 0, 0,  1),
	IP0P0P2("ip0p0p2", 0, 0,  2),

	IP0P1N2("ip0p1n2", 0, 1, -2),
	IP0P1N1("ip0p1n1", 0, 1, -1),
	IP0P110("ip0p110", 0, 1,  0),
	IP0P1P1("ip0p1p1", 0, 1,  1),
	IP0P1P2("ip0p1p2", 0, 1,  2),

	IP0P2N2("ip0p2n2", 0, 2, -2),
	IP0P2N1("ip0p2n1", 0, 2, -1),
	IP0P2P0("ip0p2p0", 0, 2,  0),
	IP0P2P1("ip0p2p1", 0, 2,  1),
	IP0P2P2("ip0p2p2", 0, 2,  2),

	// x = 1
	IP1P0N2("ip1p0n2", 1, 0, -2),
	IP1P0N1("ip1p0n1", 1, 0, -1),
	IP1P0P0("ip1p0p0", 1, 0,  0),
	IP1P0P1("ip1p0p1", 1, 0,  1),
	IP1P0P2("ip1p0p2", 1, 0,  2),

	IP1P1N2("ip1p1n2", 1, 1, -2),
	IP1P1N1("ip1p1n1", 1, 1, -1),
	IP1P110("ip1p110", 1, 1,  0),
	IP1P1P1("ip1p1p1", 1, 1,  1),
	IP1P1P2("ip1p1p2", 1, 1,  2),

	IP1P2N2("ip1p2n2", 1, 2, -2),
	IP1P2N1("ip1p2n1", 1, 2, -1),
	IP1P2P0("ip1p2p0", 1, 2,  0),
	IP1P2P1("ip1p2p1", 1, 2,  1),
	IP1P2P2("ip1p2p2", 1, 2,  2),

	// x = 2
	IP2P0N2("ip2p0n2", 2, 0, -2),
	IP2P0N1("ip2p0n1", 2, 0, -1),
	IP2P0P0("ip2p0p0", 2, 0,  0),
	IP2P0P1("ip2p0p1", 2, 0,  1),
	IP2P0P2("ip2p0p2", 2, 0,  2),

	IP2P1N2("ip2p1n2", 2, 1, -2),
	IP2P1N1("ip2p1n1", 2, 1, -1),
	IP2P110("ip2p110", 2, 1,  0),
	IP2P1P1("ip2p1p1", 2, 1,  1),
	IP2P1P2("ip2p1p2", 2, 1,  2),

	IP2P2N2("ip2p2n2", 2, 2, -2),
	IP2P2N1("ip2p2n1", 2, 2, -1),
	IP2P2P0("ip2p2p0", 2, 2,  0),
	IP2P2P1("ip2p2p1", 2, 2,  1),
	IP2P2P2("ip2p2p2", 2, 2,  2);
	
	//@formatter:on

	private final String name;
	private final int dx;
	private final int dy;
	private final int dz;

	ArcFurnaceMultiblockPart(String name, int dx, int dy, int dz) {
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