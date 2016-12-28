package us.blockbox.shopui.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import us.blockbox.shopui.command.CommandShop;

//Created 11/25/2016 8:28 PM
public class CommandShopPreProcessListener implements Listener{
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onCommandPreProcess(PlayerCommandPreprocessEvent e){
		if(e.getMessage().trim().equalsIgnoreCase("/shop")){
			e.setCancelled(true);
			new CommandShop().onCommand(e.getPlayer(),Bukkit.getPluginCommand("shop"),"",null);
		}
	}
}