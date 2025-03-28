package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import com.cidermc.untitled.currentEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class EventStart extends CommandStruct {

    @Override
    public String getName() {
        return "eventStart";
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
        return "/cider eventshelp";
    }


    private final Plugin plugin;

    public EventStart(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void commandRun(CommandSender commandSender, String[] args) {

        if(!commandSender.hasPermission("cider.event.start")) { return;
        }

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        eventInstance.setEventState(true);

    }
}
