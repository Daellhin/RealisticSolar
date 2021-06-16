package com.daellhin.realisticsolar.items.handbook.gui.chapters;

import java.util.Set;

public class IndexChapter extends Chapter {
	
	public Set<String> chapters;
	
	@Override
	public void initialize() {
		pages[0].createIndexLinks(chapters);
	}
	
}
