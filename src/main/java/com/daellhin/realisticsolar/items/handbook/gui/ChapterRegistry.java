package com.daellhin.realisticsolar.items.handbook.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.daellhin.realisticsolar.RealisticSolar;
import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ChapterRegistry {

	private Map<String, Supplier<Chapter>> chapters = new HashMap<>();

	public ChapterRegistry() {
		add("start", () -> createChapter(new ResourceLocation(RealisticSolar.MODID, "pages/start.json"), Chapter.class));
		add("arc_furnace", () -> createChapter(new ResourceLocation(RealisticSolar.MODID, "pages/arc_furnace.json"), Chapter.class));
		add("index", () -> createChapter(new ResourceLocation(RealisticSolar.MODID, "pages/index.json"), IndexChapter.class));
	}

	public void add(String name, Supplier<Chapter> supplier) {
		chapters.put(name, supplier);
	}

	public Chapter getChapter(String name) {
		if (chapters.containsKey(name)) {
			return chapters.get(name)
					.get();
		}
		// TODO add safe way, by returning error page
		return null;
	}

	public Chapter createChapter(ResourceLocation location, Class<? extends Chapter> classOf) {
		Chapter chapter = null;
		try {
			InputStreamReader reader = new InputStreamReader(Minecraft.getInstance()
					.getResourceManager()
					.getResource(location)
					.getInputStream());
			String text = new BufferedReader(reader).lines()
					.collect(Collectors.joining("\n"));

			Gson gson = new Gson();
			chapter = gson.fromJson(text, classOf);

		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chapter;
	}

}
