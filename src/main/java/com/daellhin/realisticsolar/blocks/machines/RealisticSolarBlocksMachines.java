package com.daellhin.realisticsolar.blocks.machines;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class RealisticSolarBlocksMachines {
	public static Block BlockWasher;
	public static Block BlockCoalCrucible;
	public static Block BlockIngotCrucible;
	public static Block BlockRodCrucible;
	public static Block BlockFZRodCrucible;
	public static Block BlockEArcFurnace;
	public static Block BlockSPReactor;
	public static Block BlockUMGSiReactor;
	public static Block BlockDopingReactor;
	public static Block BlockSawingMachine;
	public static Block BlockApplier;
	public static Block BlockAssembler;

	public static void init() {
		BlockWasher = new BlockWasher(Material.iron);
		BlockCoalCrucible = new BlockCoalCrucible(Material.rock);
		BlockIngotCrucible = new BlockIngotCrucible(Material.iron);
		BlockRodCrucible = new BlockRodCrucible(Material.iron);
		BlockFZRodCrucible = new BlockFZRodCrucible(Material.iron);
		BlockEArcFurnace = new BlockEArcFurnace(Material.iron);
		BlockSPReactor = new BlockSPReactor(Material.iron);
		BlockUMGSiReactor = new BlockUMGSiReactor(Material.iron);
		BlockDopingReactor = new BlockDopingReactor(Material.iron);
		BlockSawingMachine = new BlockSawingMachine(Material.iron);
		BlockApplier = new BlockApplier(Material.iron);
		BlockAssembler = new BlockAssembler(Material.iron);

		GameRegistry.registerBlock(BlockWasher, BlockWasher.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockCoalCrucible, BlockCoalCrucible.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockIngotCrucible, BlockIngotCrucible.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockRodCrucible, BlockRodCrucible.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockFZRodCrucible, BlockFZRodCrucible.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockEArcFurnace, BlockEArcFurnace.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockSPReactor, BlockSPReactor.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockUMGSiReactor, BlockUMGSiReactor.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockDopingReactor, BlockDopingReactor.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockSawingMachine, BlockSawingMachine.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockApplier, BlockApplier.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockAssembler, BlockAssembler.getUnlocalizedName().substring(5));

	}

}
