package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.commandStruct;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class inventoryCount extends commandStruct {
    @Override
    public String getName() {
        return "inventoryCount";
    }

    @Override
    public String getDescription() {
        return "Counts the items in the players inventory";
    }

    @Override
    public String commandType() {
        return "fun";
    }

    @Override
    public String getSyntax() {
        return "/cider inventorycount";
    }

    @Override
    public String usageArea() {
        return "player";
    }

    @Override
    public void commandRun(CommandSender commandSender, String[] args, String usage) {
        Player player = (Player) commandSender;
        if (player.hasPermission("cider.inventorycount")) {
            player.sendMessage("You dont have permission to do this");
            return;
        }

        String inventoryCount = String.valueOf(player.getInventory().getSize());
        player.sendMessage("You have: " + inventoryCount + " items in your inventory");

    }

}
