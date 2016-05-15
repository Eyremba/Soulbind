package net.shortninja.soulbind.data.file;

import java.io.File;
import java.io.IOException;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class DataFile
{
	private static DataFile instance = new DataFile();
	private final String FILE_NAME = "data.yml";
	private FileConfiguration data;
	private File dataFile;
	
	public static DataFile getInstance()
	{
		return instance;
	}
	
	public void setup(Plugin plugin)
	{
		dataFile = new File(plugin.getDataFolder(), FILE_NAME);
		
		if(!dataFile.exists())
		{
			try
			{
				dataFile.createNewFile();
			}catch(IOException exception)
			{
				Soulbind.get().sendConsoleMessage("Error occured while initializing '" + FILE_NAME + "'!", true);
			}
		}
		
		data = YamlConfiguration.loadConfiguration(dataFile);
	}
	
	public FileConfiguration get()
	{
		return data;
	}
	
	public void save()
	{
		try
		{
			data.save(dataFile);
		}catch(IOException e)
		{
			Soulbind.get().sendConsoleMessage("Error occured while saving '" + FILE_NAME + "'!", true);
		}
	}
}