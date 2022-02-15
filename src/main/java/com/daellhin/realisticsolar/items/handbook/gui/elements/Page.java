package com.daellhin.realisticsolar.items.handbook.gui.elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import com.daellhin.realisticsolar.items.handbook.gui.elements.Link.LinkFunction;
import com.daellhin.realisticsolar.items.handbook.gui.elements.Text.Alignement;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;

public class Page {
	protected Image[] images;
	protected Text[] text;
	protected Link[] links;

	public void draw(FontRenderer font, MatrixStack matrixStack, int relX, int relY, int pageCenter) {
		for (Image image : images) {
			image.draw(matrixStack, relX, relY);
		}
		for (Text text : this.text) {
			text.draw(font, relX, relY, pageCenter);
		}
		for (Link link : this.links) {
			link.draw(font, relX, relY, pageCenter);
		}
	}

	public Link[] getLinks() {
		return links;
	}

	private int extractInt(String s) {
		String num = s.replaceAll("\\D", "");
		// return 0 if no digits found
		return num.isEmpty() ? 0 : Integer.parseInt(num);
	}

	public void createIndexLinks(Set<String> chapters) {
		List<Link> list = new ArrayList<Link>(Arrays.asList(links));
		int x = 155;
		int y = 35;

		Map<String, String> I18nChapters = chapters.stream()
				.collect(Collectors.toMap(e -> I18n.get("chapter_" + e), e -> e));

		List<Entry<String, String>> sortedChapters = I18nChapters.entrySet()
				.stream()
				.sorted(Comparator.comparingInt(e -> extractInt(e.getKey())))
				.collect(Collectors.toList());

		for (Entry<String, String> e : sortedChapters) {
			list.add(new Link(e.getKey(), e.getValue(), LinkFunction.INTERNAL, Alignement.LEFT, 1, -1, 0, 43690, x, y));
			y += 12;
		}

		links = list.toArray(new Link[0]);
	}

}