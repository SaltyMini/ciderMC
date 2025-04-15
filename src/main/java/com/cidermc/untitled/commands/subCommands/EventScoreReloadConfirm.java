package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import com.cidermc.untitled.currentEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class EventScoreReloadConfirm extends CommandStruct {

    @Override
    public String getName() {
        return "eventScoreReloadConfirm";
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

    @Override
    public String[] getAliases() {
        return new String[0];
    }


    private final Plugin plugin;

    public EventScoreReloadConfirm(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void commandRun(CommandSender commandSender, String[] args) {

        if(!commandSender.hasPermission("cider.event.reload")) { return;
        }

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        commandSender.sendMessage("Reloading event score config");
        eventInstance.scoreReload();

    }
}
