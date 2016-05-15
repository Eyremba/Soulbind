package net.shortninja.soulbind.listeners;

import java.util.ArrayList;
import java.util.List;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.util.SoulRevive;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener
{
	public PlayerDeath()
	{
		Bukkit.getPluginManager().registerEvents(this, Soulbind.get());
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onDeath(PlayerDeathEvent event)
	{
		Player player = event.getEntity();
		ItemStack[] soulboundItems = Soulbind.get().soulbindManager.getSoulboundItems(player.getName(), player.getInventory());
		//ItemStack[] soulboundItems = soulboundList.toArray(new ItemStack[soulboundList.size()]);
		
		if(soulboundItems.length > 0)
		{
			SoulRevive revive = new SoulRevive(player.getUniqueId().toString(), soulboundItems);
			
			Soulbind.get().soulbindManager.addRevive(revive);
			event.getDrops().removeAll(itemsToKeep(player.getName(), event.getDrops(), soulboundItems));
		}
	}
	
	private List<ItemStack> itemsToKeep(String name, List<ItemStack> drops, ItemStack[] soulboundItems)
	{
		List<ItemStack> itemsToKeep = new ArrayList<ItemStack>();
		
		for(ItemStack item : drops)
		{
			if(Soulbind.get().soulbindManager.isSoulbound(name, item))
			{
				itemsToKeep.add(item);
			}
		}
		
		return itemsToKeep;
	}
}
