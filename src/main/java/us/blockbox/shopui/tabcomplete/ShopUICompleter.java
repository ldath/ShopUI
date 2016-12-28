package us.blockbox.shopui.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import us.blockbox.shopui.SubCommandHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Created 11/30/2016 3:30 AM
public class ShopUICompleter implements TabCompleter{

	private static final SubCommandHandler sub = SubCommandHandler.getInstance();

	@Override
	public List<String> onTabComplete(CommandSender sender,Command command,String alias,String[] args){
		if(args.length == 1){
			if(args[0].length() > 0){
				final String arg = args[0].toLowerCase();
				final List<String> matches = new ArrayList<>();
				for(String s : sub.getSubCommands()){
					if(s.startsWith(arg)){
						matches.add(s);
					}
				}
				return matches;
			}
			return new ArrayList<>(sub.getSubCommands());
		}
		return Collections.emptyList();
	}
}
