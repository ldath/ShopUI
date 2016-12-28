package us.blockbox.shopui.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import us.blockbox.shopui.locale.ShopMessage;

import static us.blockbox.shopui.ShopInventory.getShopMenu;

//Created 11/20/2016 4:26 AM
public class CommandShop implements CommandExecutor{
	@Override
	public boolean onCommand(CommandSender sender,Command command,String label,String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		if(!sender.hasPermission("shopui.command.shop")){
			sender.sendMessage(ShopMessage.PLAYER_PERMISSION_INSUFFICIENT.getMsg());
			return true;
		}
		Player p = (Player)sender;

		final Inventory menu = getShopMenu(p);
		if(menu == null){
			sender.sendMessage(ShopMessage.OPEN_FAILED.getMsg());
			return true;
		}
		p.openInventory(menu);
		return true;
	}
}
