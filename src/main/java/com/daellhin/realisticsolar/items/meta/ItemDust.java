package com.daellhin.realisticsolar.items.meta;

import java.util.List;

import com.daellhin.realisticsolar.creativetab.RealisticSolarCreativeTab;
import com.daellhin.realisticsolar.items.meta.enums.EnumItemDust;
import com.daellhin.realisticsolar.lib.ModInfo;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemDust extends Item {
	protected IIcon[] icons;
	
	public ItemDust() {
		this.setUnlocalizedName("ItemDust");
		this.setCreativeTab(RealisticSolarCreativeTab.tabRS);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int metadata = stack.getItemDamage();
		EnumItemDust en = EnumItemDust.values()[metadata];
		return "item." + en.getName();
		
	}
	
	@Override 
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for(EnumItemDust en : EnumItemDust.values()) {
			list.add(new ItemStack(item, 1, en.getMeta()));
		}
	}
	
	@Override
	public void registerIcons(IIconRegister reg) {
		icons = new IIcon[EnumItemDust.count()];
		
		for(EnumItemDust en: EnumItemDust.values()) {
			icons[en.getMeta()] = reg.registerIcon(ModInfo.MOD_ID + ":" + en.getName());
		}
	}
	
	@Override
	public IIcon getIconFromDamage(int meta) {
		if(meta >= 0 && meta < EnumItemDust.count()) {
			return icons[meta];
		}
		else {
			return null;
		}
	}
	
}