package net.shortninja.soulbind.data.profile;

import net.shortninja.soulbind.Soulbind;
import net.shortninja.soulbind.data.file.DataFile;


/**
 * Class for saving loaded player data.
 * 
 * February 20, 2016 at 6:41 AM
 * 
 * @author Shortninja
 */

public class Save
{
	private DataFile dataFile = Soulbind.get().dataFile;
	private User user;
	private String uuid;

	public Save(User user)
	{
		this.user = user;
		this.uuid = user.getUuid();
		
		save();
	}
	
	public void save()
	{
		dataFile.get().set(uuid + ".soulbinds", user.getSoulbinds());
	}
}