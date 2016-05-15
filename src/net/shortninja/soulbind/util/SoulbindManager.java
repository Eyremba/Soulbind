package net.shortninja.soulbind.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class SoulbindManager
{
	private static List<SoulRevive> revives = new ArrayList<SoulRevive>();
	
	public ItemStack[] getSoulboundItems(String name, PlayerInventory inventory)
	{
		ItemStack[] contents = (ItemStack[]) inventory.getContents();
		List<ItemStack> soulboundItems = new LinkedList<ItemStack>(Arrays.asList(contents));
		
		for(ItemStack item : contents)
		{
			if(item == null)
			{
				continue;
			}
			
			if(!isSoulbound(name, item))
			{
				soulboundItems.set(soulboundItems.indexOf(item), null);
			}
		}
		
		return soulboundItems.toArray(new ItemStack[soulboundItems.size()]);
	}
	
	public boolean isSoulbound(String name, ItemStack item)
	{
		boolean isSoulbound = false;
		
		if(item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			if(itemMeta.hasLore())
			{
				for(String string : itemMeta.getLore())
				{
					if(string.equals(Soulbind.get().options.soulbindLore.replace("%player%", name)))
					{
						isSoulbound = true;
						break;
					}
				}
			}
		}
		
		return isSoulbound;
	}
	
	public boolean isSoulbound(ItemStack item)
	{
		boolean isSoulbound = false;
		
		if(item.hasItemMeta())
		{
			ItemMeta itemMeta = item.getItemMeta();
			if(itemMeta.hasLore())
			{
				for(String string : itemMeta.getLore())
				{
					string = string.replace("%player%", "null");
					
					if(string.equals(Soulbind.get().options.soulbindLore.replace("%player%", "null")))
					{
						isSoulbound = true;
						break;
					}
				}
			}
		}
		
		return isSoulbound;
	}
	
	public boolean hasRevive(Player player)
	{
		boolean hasRevive = false;
		String uuid = player.getUniqueId().toString();
		
		for(SoulRevive revive : revives)
		{
			if(revive.getUuid().equals(uuid))
			{
				hasRevive = true;
			}
		}
		
		return hasRevive;
	}
	
	public ItemStack[] getSoulboundItems(Player player)
	{
		SoulRevive revive = null;
		String uuid = player.getUniqueId().toString();
		
		for(SoulRevive r : revives)
		{
			if(r.getUuid().equals(uuid))
			{
				revive = r;
			}
		}
		
		return revive.getContents();
	}
	
	public void addRevive(SoulRevive revive)
	{
		revives.add(revive);
	}
	
	public void removeRevive(String uuid)
	{
		SoulRevive revive = null;
		
		for(SoulRevive r : revives)
		{
			if(r.getUuid().equals(uuid))
			{
				revive = r;
			}
		}
		
		revives.remove(revive);
	}
}
