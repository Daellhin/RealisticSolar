package com.daellhin.realisticsolar.datagen.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.SurvivesExplosion;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseLootTableProvider extends LootTableProvider {
	// loot table explanations:
	// https://gist.github.com/misode/66456e57372ce62cd9b65d1052521069

	private static final Logger LOGGER = LogManager.getLogger();
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();
	private final DataGenerator generator;

	public BaseLootTableProvider(DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
		this.generator = dataGeneratorIn;
	}

	protected abstract void addTables();

	protected void addSimpleTable(Block block) {
		lootTables.put(block, createSimpleTable(block));
	}

	protected LootTable.Builder createSimpleTable(Block block) {
		LootPool.Builder builder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block));
		return LootTable.builder().addLootPool(builder);
	}

	protected void addSimpleTableSurvesExplosion(Block block) {
		lootTables.put(block, createSimpleTableSurvivesExplosion(block));
	}

	protected LootTable.Builder createSimpleTableSurvivesExplosion(Block block) {
		LootPool.Builder builder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block))
				.acceptCondition(SurvivesExplosion.builder());
		return LootTable.builder().addLootPool(builder);
	}

	protected void addAdvancedTable(Block block) {
		lootTables.put(block, createAdvancedTable(block));
	}

	protected LootTable.Builder createAdvancedTable(Block block) {
		LootPool.Builder builder = LootPool.builder().rolls(ConstantRange.of(1)).addEntry(ItemLootEntry.builder(block)
				// TODO CopyName doesn't seem to work, the custom name is not given to the item
				.acceptFunction(CopyName.builder(CopyName.Source.BLOCK_ENTITY))
				.acceptFunction(CopyNbt.builder(CopyNbt.Source.BLOCK_ENTITY).addOperation("energy", "BlockEntityTag.energy", CopyNbt.Action.REPLACE))
				.acceptFunction(SetContents.builder().addLootEntry(DynamicLootEntry.func_216162_a(new ResourceLocation("minecraft", "contents")))));
		return LootTable.builder().addLootPool(builder);
	}

	@Override
	public void act(DirectoryCache cache) {
		addTables();

		Map<ResourceLocation, LootTable> tables = new HashMap<>();
		for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
			tables.put(entry.getKey().getLootTable(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
		}
		writeTables(cache, tables);
	}

	private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
		Path outputFolder = this.generator.getOutputFolder();
		tables.forEach((key, lootTable) -> {
			Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
			try {
				IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
			} catch (IOException e) {
				LOGGER.error("Couldn't write loot table {}", path, e);
			}
		});
	}

	@Override
	public String getName() {
		return "RealisticSolar LootTables";
	}
}
