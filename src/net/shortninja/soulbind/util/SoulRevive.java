package net.shortninja.soulbind.util;

import org.bukkit.inventory.ItemStack;

public class SoulRevive
{
	private String uuid;
	private ItemStack[] contents;
	
	public SoulRevive(String uuid, ItemStack[] contents)
	{
		this.uuid = uuid;
		this.contents = contents;
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public ItemStack[] getContents()
	{
		return contents;
	}
}