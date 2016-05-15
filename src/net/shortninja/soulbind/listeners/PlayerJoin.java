package net.shortninja.soulbind.listeners;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.profile.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener
{
	private User user = Soulbind.get().user;
	
	public PlayerJoin()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		
		if(user.isOfflineUser(player))
		{
			User offlineUser = user.getOfflineUser(player);
			
			user.addOnlineUser(offlineUser);
			user.removeOfflineUser(offlineUser);
		}else Soulbind.get().dataManager.attemptLoad(player);
	}
}
