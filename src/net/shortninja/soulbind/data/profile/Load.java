package net.shortninja.soulbind.data.profile;

import java.util.prefs.Preferences;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.file.DataFile;

import org.bukkit.entity.Player;

/**
 * Class for loading player data into memory.
 * 
 * February 20, 2016 at 6:41 AM
 * 
 * @author Shortninja
 */

public class Load
{
	private DataFile dataFile = Soulbind.get().dataFile;
	private String uuid;
	private String name;
	
	public Load(Player player, boolean isNew)
	{
		this.uuid = player.getUniqueId().toString();
		this.name = player.getName();
		
		if(isNew)
		{
			Soulbind.get().user.addOnlineUser(new User(uuid, name, 0));
		}else load();
	}
	
	public void load()
	{
		int soulbinds = dataFile.get().getInt(uuid + ".soulbinds");
		User user = new User(uuid, name, soulbinds);
		
		Soulbind.get().user.addOnlineUser(user);
	}
}