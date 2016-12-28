package us.blockbox.shopui;

import org.bukkit.inventory.ItemStack;

public class ShopItem{
	private ItemStack itemStack;
	private double priceBuy;
	private double priceSell;
	private int quantityDefault;

	public ShopItem(ItemStack itemStack,double priceBuy,double priceSell){
		this(itemStack,priceBuy,priceSell,1);
	}

	public ShopItem(ItemStack itemStack,double priceBuy,double priceSell,int quantityDefault){
		this.itemStack = itemStack;
		this.priceBuy = priceBuy;
		this.priceSell = priceSell;
		this.quantityDefault = quantityDefault;
	}

	public ItemStack getItemStack(){
		return itemStack;
	}

	public double getPriceBuy(){
		return priceBuy;
	}

	public double getPriceSell(){
		return priceSell;
	}

	public int getQuantityDefault(){
		return quantityDefault;
	}
}
