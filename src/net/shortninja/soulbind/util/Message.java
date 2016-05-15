package net.shortninja.soulbind.util;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message
{
	public final String colorize(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
	
	public String message(String message)
	{
		String prefix = Soulbind.get().options.messagePrefix.length() > 0 ? Soulbind.get().options.messagePrefix + " " : "";
				
		return colorize(prefix + message);
	}
	
	public void sendMessage(Player player, String message, boolean prefix)
	{
		if(player == null || message.length() == 0)
		{
			return;
		}
		
		if(prefix)
		{
			player.sendMessage(message(message));
		}else player.sendMessage(message);
	}
	
	public void sendMessage(CommandSender sender, String message, boolean prefix)
	{
		if(sender == null || message.length() == 0)
		{
			return;
		}
		
		if(prefix)
		{
			sender.sendMessage(message(message));
		}else sender.sendMessage(message);
	}
	
	public String longLine()
	{
		return colorize("&7&m------------------------------------------------");
	}
}