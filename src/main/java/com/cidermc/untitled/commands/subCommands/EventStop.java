package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import com.cidermc.untitled.currentEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class EventStop extends CommandStruct {

    @Override
    public String getName() {
        return "eventStop";
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
        return "/cider eventhelp";
    }


    private final Plugin plugin;

    public EventStop(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void commandRun(CommandSender commandSender, String[] args) {

        if(!commandSender.hasPermission("cider.event.start")) { return;
        }

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        eventInstance.setEventState(false);

    }
}
