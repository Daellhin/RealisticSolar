package com.daellhin.realisticsolar.items.handbook.gui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.items.handbook.gui.chapters.Chapter;
import com.daellhin.realisticsolar.items.handbook.gui.chapters.IndexChapter;
import com.google.gson.Gson;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class ChapterRegistry {

	private Map<String, Supplier<? extends Chapter>> chapters = new HashMap<>();

	public ChapterRegistry() {
		addChapter("index", IndexChapter.class);
		addChapter("about", Chapter.class);
		addChapter("arc_furnace", Chapter.class);
	}

	private void addChapter(String name, Class<? extends Chapter> classOf) {
		chapters.put(name, () -> createChapter(new ResourceLocation(RealisticSolar.MODID, "pages/" + name + ".json"), classOf, name));
	}

	private Chapter createChapter(ResourceLocation location, Class<? extends Chapter> classOf, String name) {
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
			chapter.setName(name);

			if (chapter instanceof IndexChapter) {
				((IndexChapter) chapter).chapters = getChapters();
			}

			chapter.initialize();
		} catch (FileNotFoundException e) {
			System.out.println("Chapter file not found at: " + location);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chapter;
	}

	public Chapter getChapter(String name) {
		if (chapters.containsKey(name)) {
			return chapters.get(name)
					.get();
		}
		throw new IllegalStateException("Unknow chapter: " + name);
	}

	/**
	 * Returns chapters to create the index links
	 */
	public Set<String> getChapters() {
		Set<String> output = new HashSet<String>(chapters.keySet());
		output.removeAll(Arrays.asList("index"));
		return output;
	}

}
