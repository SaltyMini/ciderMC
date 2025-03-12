package com.cidermc.untitled.commands.subCommands; // Counts the players online and keeps a history of them

import com.cidermc.untitled.commands.commandStruct;
import com.cidermc.untitled.gui.playerCountGUI;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class playerCount extends commandStruct implements Listener {
    @Override
    public String getName() {
        return "playercount";
    }

    @Override
    public String getDescription() {
        return "Counts and tracks the player count";
    }

    @Override
    public String commandType() {
        return "utils";
    }

    @Override
    public String getSyntax() {
        return "/cider playercount";
    }


    @Override
    public void commandRun(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        playerCountGUI gui = new playerCountGUI();
        gui.openInventory(player);







    }
}
