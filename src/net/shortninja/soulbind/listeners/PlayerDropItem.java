package net.shortninja.soulbind.listeners;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItem implements Listener
{
	public PlayerDropItem()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onDrop(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		
		if(Soulbind.get().soulbindManager.isSoulbound(player.getName(), event.getItemDrop().getItemStack()))
		{
			Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageNoDropping, true);
			event.setCancelled(true);
		}
	}
}