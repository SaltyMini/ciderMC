package com.cidermc.untitled.commands;

import com.cidermc.untitled.commands.subCommands.InventoryCount;
import com.cidermc.untitled.commands.subCommands.Ranks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {

    private final ArrayList<CommandStruct> commandStruct = new ArrayList<>(); //list of all commands

    public CommandManager() {
        commandStruct.add(new InventoryCount()); //adds inventoryCount command to command structure
        commandStruct.add(new Ranks());
        //TODO: add new commands
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(command.getLabel().equalsIgnoreCase("cider")) {
            // Check if args is empty

             if (args.length == 0) {
                  commandSender.sendMessage("Use /cider help to see available commands");
                 return true;
             }

            if(args[0].equalsIgnoreCase("help")) {
                help(commandSender, args);
                return true;
            }

            for (CommandStruct cmd : getCommandStruct()) {
                if (args[0].equalsIgnoreCase(cmd.getName())
                        || Arrays.asList(cmd.getAliases()).contains(args[0])) {
                    commandSender.sendMessage("Command found: " + cmd.getName());
                    cmd.commandRun(commandSender, args);
                }
            }

        }

        for (CommandStruct cmd : getCommandStruct()) {
            if (command.getLabel().equalsIgnoreCase(cmd.getName())
                    || Arrays.asList(cmd.getAliases()).contains(command.getLabel())) {
                commandSender.sendMessage("Command found: " + cmd.getName());
                cmd.commandRun(commandSender, args);
                return true;
            }
        }

        return true;
    }

    public void help(CommandSender commandSender, String[] args) {
    // Handle help command
        if (args[0].equalsIgnoreCase("help")) {

            int countMax;

            try {
                countMax = Integer.parseInt(args[0]) * 10;
            } catch (NumberFormatException e) {
                countMax = 10;
            }

            int count = countMax - 10;

            for(CommandStruct cmd : getCommandStruct()) {
                while (count < countMax) {
                    commandSender.sendMessage("/" + cmd.getSyntax() + " - " + cmd.getDescription());
                    count++;
                }
            }
        }
    }

    public ArrayList<CommandStruct> getCommandStruct() {
        return commandStruct;
    } // returns the command list
}