package net.shortninja.soulbind.listeners;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerRespawn implements Listener
{
	public PlayerRespawn()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onRespawn(PlayerRespawnEvent event)
	{
		Player player = event.getPlayer();
		Inventory inventory = player.getInventory();
		
		if(Soulbind.get().soulbindManager.hasRevive(player))
		{
			ItemStack[] soulboundItems = Soulbind.get().soulbindManager.getSoulboundItems(player);
			
			inventory.setContents(soulboundItems);
		}
	}
}
