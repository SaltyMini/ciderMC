package com.cidermc.untitled.commands;

import com.cidermc.untitled.commands.subCommands.inventoryCount;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class commandManager implements CommandExecutor {

    private final ArrayList<commandStruct> commandStruct = new ArrayList<>(); //list of all commands

    public commandManager() {
        commandStruct.add(new inventoryCount()); //adds inventoryCount command to command structure
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if args is empty
        if (args.length == 0) {
            commandSender.sendMessage("Use /cider help to see available commands");
            return true;
        }

        // Handle help command
        if (args[0].equalsIgnoreCase("help")) {
            // If there's a second argument, show help for that specific command
            if (args.length > 1 && !args[1].isEmpty()) {
                for (commandStruct cmd : getCommandStruct()) {
                    if (cmd.getName().equalsIgnoreCase(args[1])) {
                        commandSender.sendMessage(cmd.getSyntax() + " - " + cmd.getDescription());
                        return true;
                    }
                }
                commandSender.sendMessage("Command not found: " + args[1]);
            } else { // Otherwise show all commands
                for (commandStruct cmd : getCommandStruct()) {
                    commandSender.sendMessage("/" + cmd.getName() + " - " + cmd.getDescription());
                }
            }
            return true;
        }

        // Handle other commands
        for (commandStruct cmd : getCommandStruct()) {
            if (args[0].equalsIgnoreCase(cmd.getName())) {
                if (commandSender instanceof Player player) {
                    cmd.commandRun(commandSender, args);
                } else {
                    commandSender.sendMessage("You cannot run cider commands in the console");
                }
                return true;
            }
        }

        // Command not found
        commandSender.sendMessage("Unknown command. Use /cider help to see available commands");
        return true;
    }

    public ArrayList<commandStruct> getCommandStruct() {
        return commandStruct;
    } // returns the command list
}