package com.cidermc.untitled.commands;


import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class tabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, Command command, String alias, String[] args) {
        if(!command.getLabel().equalsIgnoreCase("inventorycount")
                || !alias.equalsIgnoreCase("inventorycount")
                || !(commandSender instanceof Player player)) {
            return null;
        }
        if(args.length >= 1) {
            return null;
        }

        if(!player.hasPermission("cider.inventorycount.other")) {
            return null;
        }

        return List.of(Arrays.toString(Material.values()));
    }
}
