package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.commandStruct;
import com.cidermc.untitled.gui.ranksGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ranks extends commandStruct {
    @Override
    public String getName() {
        return "ranks";
    }

    @Override
    public String getDescription() {
        return "ranks manager";
    }

    @Override
    public String commandType() {
        return "rank";
    }

    @Override
    public String getSyntax() {
        return "/cider ranks";
    }

    @Override
    public void commandRun(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;

        ranksGUI ranksGUI = new ranksGUI();
        ranksGUI.openGui(player);

    }
}
