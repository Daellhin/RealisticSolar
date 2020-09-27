package com.daellhin.realisticsolar.setup;

import com.daellhin.realisticsolar.OreGeneration;
import com.daellhin.realisticsolar.RealisticSolar;
import com.daellhin.realisticsolar.blocks.ModBlocks;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RealisticSolar.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {

	public static final ItemGroup ITEM_GROUP = new ItemGroup(RealisticSolar.MODID + ".itemgroup1") {

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModBlocks.ALUMINIUM_BLOCK.get());
		}
	};

	public static void init(final FMLCommonSetupEvent event) {
		OreGeneration.init();
	}
}
