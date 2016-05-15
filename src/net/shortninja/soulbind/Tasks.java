package net.shortninja.soulbind;

import net.shortninja.soulbind.data.profile.User;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Tasks
{
	Soulbind plugin = Soulbind.get();
	
	public Tasks()
	{
		new BukkitRunnable()
		{
			private int interval = 0;
			private long now = System.currentTimeMillis();
			private long later;
			
			public void run()
			{
				later = System.currentTimeMillis();
				
				for(Player player : Soulbind.get().getOnlinePlayers())
				{
					User user = plugin.user.getOnlineUser(player);
					int soulbinds = getSoulboundItemAmount(player.getName(), player.getInventory());
					
					user.setSoulbinds(soulbinds);
				}
				
				if(isASecond(now, later))
				{
					interval += (int)((later - now) / 1000);
					now = System.currentTimeMillis();
				}
				
				if(interval >= Soulbind.get().options.soulbindAutosaveInterval && interval != 0)
				{
					Soulbind.get().dataManager.saveData();
			        interval = 0;
				}
			}
		}.runTaskTimer(plugin, plugin.options.soulbindCheckInterval, plugin.options.soulbindCheckInterval);
	
		new BukkitRunnable()
		{
			public void run()
			{
				plugin.dataManager.saveData();
			}
		}.runTaskTimer(plugin, plugin.options.soulbindAutosaveInterval, plugin.options.soulbindAutosaveInterval);
	}
	
	private boolean isASecond(long now, long later)
	{
		return (later - now) >= 1000;
	}
	
	private int getSoulboundItemAmount(String name, PlayerInventory inventory)
	{
		int soulboundItems = 0;
		
		for(ItemStack item : (ItemStack[]) ArrayUtils.addAll(inventory.getContents(), inventory.getArmorContents()))
		{
			if(item == null)
			{
				continue;
			}
			
			if(plugin.soulbindManager.isSoulbound(name, item))
			{
				soulboundItems++;
			}
		}
		
		return soulboundItems;
	}
}
