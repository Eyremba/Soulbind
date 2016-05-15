package net.shortninja.soulbind.data.profile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class User
{
	public List<ItemStack> soulboundItems = new ArrayList<ItemStack>();
	private static Set<User> onlineUsers = new HashSet<User>();
	private static Set<User> offlineUsers = new HashSet<User>();
	private String uuid;
	private String name;
	private boolean isOnline;
	private int soulbinds;
	
	public User(){}
	
	public User(String uuid, String name, int soulbinds)
	{
		this.uuid = uuid;
		this.name = name;
		this.soulbinds = soulbinds;
	}
	
	public User getOnlineUser(Player player)
	{
		User user = null;
		
		for(User u : onlineUsers)
		{
			if(u.getUuid().equals(player.getUniqueId().toString()))
			{
				user = u;
			}
		}
		
		if(user == null)
		{
			user = new User(player.getUniqueId().toString(), player.getName(), 0);
			Soulbind.get().sendConsoleMessage("Was forced to load new user! Contact me ASAP!", true);
		}
		
		return user;
	}
	
	public User getOfflineUser(Player player)
	{
		User user = null;
		
		for(User u : offlineUsers)
		{
			if(u.getUuid().equals(player.getUniqueId().toString()))
			{
				user = u;
			}
		}
		
		if(user == null)
		{
			user = new User(player.getUniqueId().toString(), player.getName(), 0);
		}
		
		return user;
	}
	
	public Set<User> getOnlineUsers()
	{
		return onlineUsers;
	}
	
	public Set<User> getOfflineUsers()
	{
		return offlineUsers;
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getSoulbinds()
	{
		return soulbinds;
	}
	
	public boolean isOfflineUser(Player player)
	{
		User offlineUser = getOfflineUser(player);
		
		return offlineUsers.contains(offlineUser);
	}
	
	public boolean isOnline()
	{
		return isOnline;
	}
	
	public void addOnlineUser(User user)
	{
		onlineUsers.add(user);
	}
	
	public void removeOnlineUser(User user)
	{
		onlineUsers.remove(user);
	}
	
	public void addOfflineUser(User user)
	{
		offlineUsers.add(user);
	}
	
	public void removeOfflineUser(User user)
	{
		offlineUsers.remove(user);
	}
	
	public void setOnline(boolean isOnline)
	{
		this.isOnline = isOnline;
	}
	
	public void setSoulbinds(int soulbinds)
	{
		this.soulbinds = soulbinds;
	}
}
