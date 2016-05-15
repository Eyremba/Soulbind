package net.shortninja.soulbind.data;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.profile.Load;
import net.shortninja.soulbind.data.profile.Save;
import net.shortninja.soulbind.data.profile.User;

import org.bukkit.entity.Player;

public class DataManager
{
	User user = Soulbind.get().user;
	
	public void attemptLoad(Player player)
	{
		String uuid = player.getUniqueId().toString();
		
    	if(!hasJoined(uuid))
    	{
    		new Load(player, true);
    	}else new Load(player, false);
	}
	
	public void loadData()
	{
        for(Player player : Soulbind.get().getOnlinePlayers())
        {
        	attemptLoad(player);	
        }
	}	
	
	public void saveData()
	{
		for(User user : user.getOnlineUsers())
		{
			new Save(user);
		}
		
		for(User user : user.getOfflineUsers())
		{
			new Save(user);
		}
		
		Soulbind.get().dataFile.save();
	}
	
	private boolean hasJoined(String uuid)
	{
		return Soulbind.get().dataFile.get().contains(uuid);
	}
}
