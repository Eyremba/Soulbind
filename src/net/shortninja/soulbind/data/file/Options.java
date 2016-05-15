package net.shortninja.soulbind.data.file;

import net.shortninja.soulbind.Soulbind;

import org.bukkit.configuration.file.FileConfiguration;

public class Options
{
	private static FileConfiguration config = Soulbind.get().getConfig();

	public String soulbindUsePermission = config.getString("soulbind-use-permission");
	public String soulbindLimitPermission = config.getString("soulbind-limit-permission");
	public String soulbindLore = Soulbind.get().message.colorize(config.getString("soulbind-lore"));
	public int soulbindCheckInterval = config.getInt("check-interval") * 20;
	public int soulbindAutosaveInterval = config.getInt("autosave-interval") * 20;
	
	public String messagePrefix = config.getString("prefix");
	public String messageSoulbindApplied = config.getString("soulbind-applied");
	public String messageSoulbindGiven = config.getString("soulbind-given");
	public String messageSoulbindRemoved = config.getString("soulbind-removed");
	public String messageMustHoldItem = config.getString("must-hold-item");
	public String messageWasNotHolding = config.getString("was-not-holding");
	public String messageAlreadySoulbound = config.getString("already-soulbound");
	public String messageReachedLimit = config.getString("reached-limit");
	public String messageNoStacks = config.getString("no-stacks");
	public String messageNoSoulbindFound = config.getString("no-soulbind-found");
	public String messageNoDropping = config.getString("no-dropping");
	public String messageNoPickup = config.getString("no-pickup");
	public String messageNoMove = config.getString("no-move");
	public String messageNoPermission = config.getString("no-permission");
	public String messageUnknownCommand = config.getString("unknown-command");
	public String messageTooManyArguments = config.getString("invalid-arguments");
	public String messagePlayerIsOffline = config.getString("player-is-offline");
	public String messageMustBeIngame = config.getString("must-be-ingame");
}