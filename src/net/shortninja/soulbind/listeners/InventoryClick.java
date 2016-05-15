package net.shortninja.soulbind.listeners;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener
{
	public InventoryClick()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent event)
	{
		Player player = (Player) event.getWhoClicked();
		InventoryType inventory = event.getInventory().getType();
		ItemStack item = event.getCurrentItem();
		
		if(item == null)
		{
			return;
		}
		
		
		if(!Soulbind.get().soulbindManager.isSoulbound(player.getName(), item))
		{
			return;
		}
		
		for(InventoryType i : CONTAINERS)
		{
			if(inventory == i)
			{
				Soulbind.get().message.sendMessage(player, Soulbind.get().options.messageNoMove, true);
				event.setCancelled(true);
			}
		}
	}
	
	private final InventoryType[] CONTAINERS =
	{
		InventoryType.BEACON, InventoryType.BREWING, InventoryType.CHEST,
		InventoryType.WORKBENCH, InventoryType.DISPENSER, InventoryType.DROPPER,
		InventoryType.ENDER_CHEST, InventoryType.FURNACE, InventoryType.HOPPER,
	};
}
