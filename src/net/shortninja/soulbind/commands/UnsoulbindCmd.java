package net.shortninja.soulbind.commands;

import java.util.Iterator;
import java.util.List;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.profile.User;
import net.shortninja.soulbind.util.hex.Items;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class UnsoulbindCmd
{
	public UnsoulbindCmd(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(!sender.hasPermission(Soulbind.get().options.soulbindUsePermission))
		{
			Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageNoPermission, true);
			return;
		}
		
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			ItemStack item = player.getItemInHand();
			User user = Soulbind.get().user.getOnlineUser(player);
			
			if(!Soulbind.get().soulbindManager.isSoulbound(player.getName(), item))
			{
				Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageNoSoulbindFound, true);
				return;
			}
			
			removeSoulbind(player.getName(), item);
			user.setSoulbinds(user.getSoulbinds() - 1);
			Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageSoulbindRemoved, true);
		}else Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageMustBeIngame, true);
	}
	
	private ItemStack removeSoulbind(String name, ItemStack item)
	{
		List<String> itemLore = item.getItemMeta().getLore();
		
		for(Iterator<String> iterator = itemLore.iterator(); iterator.hasNext();)
		{
		    String string = iterator.next();
		    
		    if(string.equals(Soulbind.get().options.soulbindLore.replace("%player%", name)))
		    {
		        iterator.remove();
		    }
		}
		
		return Items.editor(item).setLore(itemLore).build();
	}
}
