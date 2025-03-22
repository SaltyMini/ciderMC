package com.cidermc.untitled.commands;

import com.cidermc.untitled.commands.subCommands.InventoryCount;
import com.cidermc.untitled.commands.subCommands.Ranks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final ArrayList<CommandStruct> commandStruct = new ArrayList<>(); //list of all commands

    public CommandManager() {
        commandStruct.add(new InventoryCount()); //adds inventoryCount command to command structure
        commandStruct.add(new Ranks());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!command.getLabel().equalsIgnoreCase("cider")) { return true; }
        // Check if args is empty
        if (args.length == 0) {
            commandSender.sendMessage("Use /cider help to see available commands");
            return true;
        }

        // Handle help command
        if (args[0].equalsIgnoreCase("help")) {
            // If there's a second argument, show help for that specific command
            if (args.length > 1) {
                for (CommandStruct cmd : getCommandStruct()) {
                    if (cmd.getName().equalsIgnoreCase(args[1])) {
                        commandSender.sendMessage(cmd.getSyntax() + " - " + cmd.getDescription());
                        return true;
                    }
                }
                commandSender.sendMessage("Command not found: " + args[1]);
            } else { // Otherwise show all commands
                for (CommandStruct cmd : getCommandStruct()) {
                    commandSender.sendMessage("/" + cmd.getName() + " - " + cmd.getDescription());
                }
            }
            return true;
        }

        // Handle other commands
        for (CommandStruct cmd : getCommandStruct()) {
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

    public ArrayList<CommandStruct> getCommandStruct() {
        return commandStruct;
    } // returns the command list
}