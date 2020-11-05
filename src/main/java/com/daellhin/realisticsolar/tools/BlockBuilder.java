package com.daellhin.realisticsolar.tools;

import java.util.function.Supplier;

import net.minecraft.block.Block.Properties;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.shapes.VoxelShape;

public class BlockBuilder {
	private Properties properties;
	private Supplier<TileEntity> tileEntitySupplier;
	private boolean shiftInformation;
	private VoxelShape shape;

	public BlockBuilder properties(Properties properties) {
		this.properties = properties;
		return this;
	}

	public BlockBuilder basicProperties() {
		this.properties = Properties.create(Material.IRON)
				.sound(SoundType.METAL)
				.hardnessAndResistance(2.0f)
				.lightValue(0);
		return this;
	}

	public BlockBuilder basicMachineProperties() {
		this.properties = Properties.create(Material.IRON)
				.sound(SoundType.METAL)
				.hardnessAndResistance(2.0f)
				.lightValue(13);
		return this;
	}

	public BlockBuilder tileEntitySupplier(Supplier<TileEntity> supplier) {
		this.tileEntitySupplier = supplier;
		return this;
	}

	public BlockBuilder addShiftInformation() {
		this.shiftInformation = true;
		return this;
	}

	public BlockBuilder shape(VoxelShape shape) {
		this.shape = shape;
		return this;
	}

	// getters
	public Properties getProperties() {
		return properties;
	}

	public Supplier<TileEntity> getTileEntitySupplier() {
		return tileEntitySupplier;
	}

	public boolean getShiftInformation() {
		return shiftInformation;
	}

	public VoxelShape getShape() {
		return shape;
	}

}
