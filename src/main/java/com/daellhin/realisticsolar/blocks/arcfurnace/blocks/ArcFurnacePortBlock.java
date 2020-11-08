package com.daellhin.realisticsolar.blocks.arcfurnace.blocks;

import com.daellhin.realisticsolar.blocks.arcfurnace.tiles.ArcFurnacePortTile;
import com.daellhin.realisticsolar.tools.builders.BlockBuilder;
import com.daellhin.realisticsolar.tools.enums.MultiblockPortType;

public class ArcFurnacePortBlock extends ArcFurnacePartBlock {

	private final MultiblockPortType type;

	public ArcFurnacePortBlock(MultiblockPortType type) {
		super(new BlockBuilder().basicProperties()
				.tileEntitySupplier(ArcFurnacePortTile::new));
		this.type = type;
	}

	public MultiblockPortType getType() {
		return type;
	}

}
