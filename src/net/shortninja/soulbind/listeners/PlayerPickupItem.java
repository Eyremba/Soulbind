package net.shortninja.soulbind.listeners;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem implements Listener
{
	public PlayerPickupItem()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPickup(PlayerPickupItemEvent event)
	{
		Player player = event.getPlayer();
		
		if(Soulbind.get().soulbindManager.isSoulbound(event.getItem().getItemStack()))
		{
			if(!Soulbind.get().soulbindManager.isSoulbound(player.getName(), event.getItem().getItemStack()))
			{
				Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageNoPickup, true);
				event.setCancelled(true);
				return;
			}
		}
	}
}
