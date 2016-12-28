package us.blockbox.shopui;

import org.bukkit.inventory.ItemStack;

//Created 11/20/2016 5:55 PM
public class ShopCategory{
	private final String shopId;
	private final String shopNameColored;
	private final ItemStack itemStack;

	public ShopCategory(String shopId,String shopNameColored,ItemStack itemStack){
		this.shopId = shopId;
		this.shopNameColored = shopNameColored;
		this.itemStack = itemStack;
	}

	public String getShopId(){
		return shopId;
	}

	public ItemStack getItemStack(){
		return itemStack;
	}

	public String getShopNameColored(){
		return shopNameColored;
	}
}
