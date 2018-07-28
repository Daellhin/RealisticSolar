package com.daellhin.realisticsolar.items.meta;

import java.util.List;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.items.meta.enums.EnumItemIngot;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemIngot extends Item {
	protected IIcon[] icons;
	
	public ItemIngot() {
		this.setUnlocalizedName("ItemIngot");
		this.setCreativeTab(RealisticSolarCreativeTab.tabRS);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int metadata = stack.getItemDamage();
		EnumItemIngot en = EnumItemIngot.values()[metadata];
		return "item." + en.getName();
		
	}
	
	@Override 
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(EnumItemIngot en : EnumItemIngot.values()) {
			list.add(new ItemStack(item, 1, en.getMeta()));
		}
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[EnumItemIngot.count()];
		
		for(EnumItemIngot en: EnumItemIngot.values()) {
			icons[en.getMeta()] = reg.registerIcon(ModInfo.MOD_ID + ":" + en.getName());
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		if(meta >= 0 && meta < EnumItemIngot.count()) {
			return icons[meta];
		}
		else {
			return null;
		}
	}
	
}