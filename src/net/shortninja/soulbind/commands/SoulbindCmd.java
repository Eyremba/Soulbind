package net.shortninja.soulbind.commands;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.profile.User;
import net.shortninja.soulbind.util.hex.Items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SoulbindCmd
{
	private CommandSender sender;
	
	public SoulbindCmd(CommandSender sender, Command cmd, String label, String[] args)
	{
		this.sender = sender;
		
		if(!sender.hasPermission(Soulbind.get().options.soulbindUsePermission))
		{
			Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageNoPermission, true);
			return;
		}
		
		if(args.length == 0)
		{
			if(sender instanceof Player)
			{
				Player player = (Player) sender;
				ItemStack item = player.getItemInHand();
				User user = Soulbind.get().user.getOnlineUser(player);
				int limit = getSoulbindLimit(player);
				
				if(item.getType() == Material.AIR)
				{
					Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageMustHoldItem, true);
					return;
				}else if(Soulbind.get().soulbindManager.isSoulbound(player.getName(), item))
				{
					Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageAlreadySoulbound, true);
					return;
				}else if(item.getAmount() > 1)
				{
					Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageNoStacks, true);
					return;
				}else if(user.getSoulbinds() >= limit && limit > 0)
				{
					Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageReachedLimit, true);
					return;
				}
				
				applySoulbind(player.getName(), item);
				Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageSoulbindApplied, true);
			}else Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageMustBeIngame, true);
		}else if(args.length == 1)
		{
			handleConsole(args[0]);
		}else Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageTooManyArguments, true);
	}
	
	private void handleConsole(String offlinePlayer)
	{
		Player onlinePlayer = Bukkit.getPlayer(offlinePlayer);
		
		if(onlinePlayer != null)
		{
			ItemStack item = onlinePlayer.getItemInHand();
			
			if(item.getType() == Material.AIR)
			{
				Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageWasNotHolding.replace("%player", onlinePlayer.getName()), true);
				return;
			}else if(Soulbind.get().soulbindManager.isSoulbound(onlinePlayer.getName(), item))
			{
				Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageAlreadySoulbound, true);
				return;
			}
			
			applySoulbind(onlinePlayer.getName(), item);
			Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messageSoulbindGiven.replace("%player%", onlinePlayer.getName()), true);
			Soulbind.get().message.sendMessage(onlinePlayer, Soulbind.get().options.messageSoulbindApplied, true);
		}else Soulbind.get().message.sendMessage(sender, Soulbind.get().options.messagePlayerIsOffline, true);
	}
	
	private ItemStack applySoulbind(String name, ItemStack item)
	{
		return Items.editor(item).addLore(Soulbind.get().options.soulbindLore.replace("%player%", name)).build();
	}
	
	private int getSoulbindLimit(Player player)
	{
		int limit = 0;
		String permission = Soulbind.get().options.soulbindLimitPermission.replace("[]", Integer.toString(limit));;
		
		if(player.hasPermission(permission))
		{
			return limit;
		}
		
		do
		{
			if(limit >= 1000)
			{
				break;
			}
			
			limit++;
			permission = Soulbind.get().options.soulbindLimitPermission.replace("[]", Integer.toString(limit));
			System.out.println(permission);
		}while(!player.hasPermission(permission));
		
		limit = Integer.valueOf(permission.substring(15));
		
		System.out.println(limit);
		
		return limit;
	}
}
