package us.blockbox.shopui.locale;

//Created 11/23/2016 10:15 PM


import us.blockbox.shopui.ShopUI;

public enum ShopMessage{
	OPEN_FAILED("Sorry, can't access the shop right now. Try again in a minute."),
	PLAYER_INVENTORY_FULL("Your inventory is full!"),
	PLAYER_MONEY_INSUFFICIENT(null),
	PLAYER_PERMISSION_INSUFFICIENT("You don't have permission."),
	COMMAND_ADD_FAILED("Add failed. The specified name might already be in the config, or the file may not exist.");

	private static final String prefix = ShopUI.prefix;
	private final String msg;

	ShopMessage(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return prefix + this.msg;
	}
}

