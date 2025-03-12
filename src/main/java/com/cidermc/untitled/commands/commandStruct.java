package com.cidermc.untitled.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class commandStruct {

    public abstract String getName(); //name of command
    public abstract String getDescription(); //description of command
    public abstract String commandType();
    public abstract String getSyntax(); //correct command syntax
    public abstract String usageArea(); //player/console/all

    public abstract void commandRun(CommandSender commandSender, String[] args, String usage); //vars to be passed into command methodvars for console command method

}
