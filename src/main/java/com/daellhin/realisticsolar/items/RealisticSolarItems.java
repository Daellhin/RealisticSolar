package com.daellhin.realisticsolar.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class RealisticSolarItems {
    public static Item ItemGlassPanel;
    public static Item ItemAluminiumFrame;
    public static Item ItemTrichlorosilane;
    public static Item ItemHydrogenChloride;
    public static Item ItemSaw;

    public static final void init() {
	ItemGlassPanel = new ItemGlassPanel();
	ItemAluminiumFrame = new ItemAluminiumFrame();
	ItemTrichlorosilane = new ItemTrichlorosilane();
	ItemHydrogenChloride = new ItemHydrogenChloride();
	ItemSaw = new ItemSaw();

	GameRegistry.registerItem(ItemGlassPanel, ItemGlassPanel.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemAluminiumFrame, ItemAluminiumFrame.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemTrichlorosilane, ItemTrichlorosilane.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemHydrogenChloride, ItemHydrogenChloride.getUnlocalizedName().substring(5));
	GameRegistry.registerItem(ItemSaw, ItemSaw.getUnlocalizedName().substring(5));

    }

}
