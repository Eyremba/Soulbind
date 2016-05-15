package net.shortninja.soulbind.listeners;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.profile.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener
{
	private User user = Soulbind.get().user;
	
	public PlayerQuit()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onQuit(PlayerQuitEvent event)
	{
		Player player = event.getPlayer();
		User newUser = user.getOnlineUser(player);
		
		user.addOfflineUser(newUser);
		user.removeOfflineUser(newUser);
	}
}
