package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class EventScoreReload extends CommandStruct {

    @Override
    public String getName() {
        return "eventReload";
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

    public EventScoreReload(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void commandRun(CommandSender commandSender, String[] args) {

        if(!commandSender.hasPermission("cider.event.reload")) { return;
        }

        commandSender.sendMessage("Reloading event score config");
        commandSender.sendMessage("If player scores have changed since the edited");
        commandSender.sendMessage("score file was opened they will be lost");
        commandSender.sendMessage("do /cider scorereloadconfirm to confirm");

    }
}
