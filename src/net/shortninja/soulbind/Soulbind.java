package net.shortninja.soulbind;

import java.util.ArrayList;

import net.shortninja.soulbind.commands.SoulbindCmd;
import net.shortninja.soulbind.commands.UnsoulbindCmd;
import net.shortninja.soulbind.data.DataManager;
import net.shortninja.soulbind.data.file.DataFile;
import net.shortninja.soulbind.data.file.Options;
import net.shortninja.soulbind.data.profile.User;
import net.shortninja.soulbind.listeners.InventoryClick;
import net.shortninja.soulbind.listeners.PlayerDeath;
import net.shortninja.soulbind.listeners.PlayerDropItem;
import net.shortninja.soulbind.listeners.PlayerJoin;
import net.shortninja.soulbind.listeners.PlayerPickupItem;
import net.shortninja.soulbind.listeners.PlayerQuit;
import net.shortninja.soulbind.listeners.PlayerRespawn;
import net.shortninja.soulbind.util.Message;
import net.shortninja.soulbind.util.SoulbindManager;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Soulbind extends JavaPlugin
{
	private static Soulbind plugin;
	public DataFile dataFile = DataFile.getInstance();
	public User user;
	public DataManager dataManager;
	public Message message;
	public Options options;
	public SoulbindManager soulbindManager;
	
	@Override
	public void onEnable()
	{
		plugin = this;
		
		saveDefaultConfig();
		dataFile.setup(plugin);
		user = new User();
		dataManager = new DataManager();
		message = new Message();
		options = new Options();
		soulbindManager = new SoulbindManager();
		new Tasks();
		new InventoryClick();
		new PlayerDeath();
		new PlayerDropItem();
		new PlayerJoin();
		new PlayerPickupItem();
		new PlayerQuit();
		new PlayerRespawn();
		
		dataManager.loadData();
		
		sendConsoleMessage("Soulbind is now enabled!", false);
		sendConsoleMessage("Plugin created by Shortninja.", false);
	}
	
	public static Soulbind get()
	{
		return plugin;
	}
	
	@Override
	public void onDisable()
	{
		sendConsoleMessage("Soulbind has been disabled!", true);
		
		dataManager.saveData();
		plugin = null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(label.equalsIgnoreCase("soulbind"))
		{
			new SoulbindCmd(sender, cmd, label, args);
		}else if(label.equalsIgnoreCase("unsoulbind"))
		{
			new UnsoulbindCmd(sender, cmd, label, args);
		}
		
		return true;
	}
	
	public void sendConsoleMessage(String message, boolean isError)
	{
		ConsoleCommandSender console = getServer().getConsoleSender();
		String prefix = isError ? "&4[Soulbind] &c" : "&2[Soulbind] &a";
		
		console.sendMessage(this.message.colorize(prefix + message));
	}
	
	public ArrayList<Player> getOnlinePlayers()
	{
		ArrayList<Player> list = new ArrayList<>();
		
		for(World world : Bukkit.getWorlds())
		{
			for(Player online : world.getPlayers())
			{
				if(online != null)
				{
					list.add(online);
				}
			}
		}
		
		return list;
	}
}
