package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import com.cidermc.untitled.gui.RanksGUI;
import com.cidermc.untitled.main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ranks extends CommandStruct {
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
    public String[] getAliases() {
        return new String[] {"ranks", "r"};
    }

    @Override
    public void commandRun(CommandSender commandSender, String[] args) {

        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("This command can only be used by players");
            return;
        }

        try {
            main plugin = main.getPlugin(main.class);
            plugin.getRanksGUI().openGui(player);
            player.sendMessage("Opening ranks GUI");
        } catch (Exception e) {
            commandSender.sendMessage("Error opening GUI: " + e.getMessage());
        }

    }
}
