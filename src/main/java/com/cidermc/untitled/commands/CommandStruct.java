package com.cidermc.untitled.commands;

import org.bukkit.command.CommandSender;

public abstract class CommandStruct {

    public abstract String getName(); //name of command
    public abstract String getDescription(); //description of command
    public abstract String commandType();
    public abstract String getSyntax(); //correct command syntax

    public abstract void commandRun(CommandSender commandSender, String[] args); //vars to be passed into command methodvars for console command method

}
