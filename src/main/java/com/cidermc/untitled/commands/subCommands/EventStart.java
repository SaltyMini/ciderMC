package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import org.bukkit.command.CommandSender;

public class EventStart extends CommandStruct {

    @Override
    public String getName() {
        return "events";
    }

    @Override
    public String getDescription() {
        return "handles the events";
    }

    @Override
    public String commandType() {
        return "events";
    }

    @Override
    public String getSyntax() {
        return "/events help";
    }

    @Override
    public void commandRun(CommandSender commandSender, String[] args) {
        //event command related stuff




    }
}
