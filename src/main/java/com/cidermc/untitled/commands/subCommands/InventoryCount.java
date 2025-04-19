package com.cidermc.untitled.commands.subCommands;

import com.cidermc.untitled.commands.CommandStruct;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static java.lang.Boolean.valueOf;
import static org.bukkit.Bukkit.getServer;

public class InventoryCount extends CommandStruct {
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
    public String[] getAliases() {
        return new String[] {"invcount", "ic"};
    }


    @Override
    public void commandRun(CommandSender commandSender, String[] args) {
        Player player = (Player) commandSender;
        if (!player.hasPermission("cider.inventorycount")) {
            player.sendMessage("You dont have permission to do this");
            return;
        }

        if (args.length < 1) {
            String inventoryCount = String.valueOf(player.getInventory().getSize());
            player.sendMessage("You have: " + inventoryCount + " items in your inventory");
            return;
        }


        //TODO: FIX THIS
        if(Boolean.parseBoolean(args[0]) && !Boolean.parseBoolean(args[1])) {
            if(getServer().getOnlinePlayers().contains(args[0])) {
                String inventoryCount = String.valueOf(player.getInventory().getSize());
                player.sendMessage("Player has: " + inventoryCount + " items in their inventory");
            } else {
                player.sendMessage("Player is not online");
            }
        } else {

            if(!player.hasPermission("cider.inventorycount.other")) {
                player.sendMessage("You dont have permission to do this");
                return;
            }

            Material material = Material.valueOf(args[1]);

            PlayerInventory inv = player.getInventory();
            ItemStack[] items = inv.getContents();
            int hasItem = 0;

            for(ItemStack item : items) {
                if(item != null && item.getType() == material) {
                    hasItem += item.getAmount();
                }
            }

            player.sendMessage("Player has " + hasItem + " of " + material);

        }
    }

}
