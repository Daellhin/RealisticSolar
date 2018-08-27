package com.daellhin.realisticsolar.gen;

import java.util.Random;

import com.daellhin.realisticsolar.blocks.meta.RealisticSolarMetaBlocks;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class OreGeneration implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
	    IChunkProvider chunkProvider) {
	switch (world.provider.dimensionId) {
	case -1:
	    generateNether(random, chunkX, chunkZ, world);
	    break;

	case 0:
	    generateOverworld(random, chunkX, chunkZ, world);
	    break;

	case 1:
	    generateEnd(random, chunkX, chunkZ, world);
	    break;
	}
    }

    public void generateNether(Random rand, int chunkX, int chunkZ, World world) {
    }

    public void generateOverworld(Random rand, int chunkX, int chunkZ, World world) {
	generateOre(RealisticSolarMetaBlocks.BlockOre, 0, world, rand, chunkX, chunkZ, 3, 9, 50, 0, 100, Blocks.stone);
	generateOre(RealisticSolarMetaBlocks.BlockOre, 1, world, rand, chunkX, chunkZ, 3, 9, 50, 0, 100, Blocks.stone);
	generateOre(RealisticSolarMetaBlocks.BlockOre, 2, world, rand, chunkX, chunkZ, 3, 9, 50, 0, 100, Blocks.stone);
	generateOre(RealisticSolarMetaBlocks.BlockOre, 3, world, rand, chunkX, chunkZ, 3, 9, 50, 0, 100, Blocks.stone);
	generateOre(RealisticSolarMetaBlocks.BlockOre, 4, world, rand, chunkX, chunkZ, 3, 9, 50, 0, 100, Blocks.stone);
    }

    public void generateEnd(Random rand, int chunkX, int chunkZ, World world) {
    }

    public void generateOre(Block block, int metadata, World world, Random rand, int chunkX, int chunkZ,
	    int minVeinSize, int maxVeinSize, int chance, int minY, int maxY, Block generateIn) {
	int veinSize = minVeinSize + rand.nextInt(maxVeinSize - minVeinSize);
	int heightRange = maxY - minY;
	WorldGenMinable gen = new WorldGenMinable(block, metadata, veinSize, generateIn);
	for (int i = 0; i < chance; i++) {
	    int XRand = chunkX * 16 + rand.nextInt(16);
	    int YRand = rand.nextInt(heightRange) + minY;
	    int ZRand = chunkZ * 16 + rand.nextInt(16);

	    gen.generate(world, rand, XRand, YRand, ZRand);
	}

    }

}
