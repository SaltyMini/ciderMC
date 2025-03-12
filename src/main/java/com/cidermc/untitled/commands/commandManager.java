package com.cidermc.untitled.commands;

import com.cidermc.untitled.commands.subCommands.inventoryCount;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Console;
import java.util.ArrayList;
import java.util.Locale;

public class commandManager implements CommandExecutor {

    private final ArrayList<commandStruct> commandStruct = new ArrayList<>() {

    }; //list of all commands

    public commandManager() {
        commandStruct.add(new inventoryCount()); //adds inventoryCount command to command structure
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {


        for(int i = 0; i < getCommandStruct().size(); i++) { //loop over commands to see if the passed command exists
            if(args[0].equalsIgnoreCase(getCommandStruct().get(i).getName())) { // ^^

                String usage = getCommandStruct().get(i).usageArea(); //get expected usage

                if(args[0].equalsIgnoreCase("help")) { // Simple help command
                    commandSender.sendMessage("Commands:");
                    for(int j = 0; j < getCommandStruct().size(); j++) { //Loops over each command for the size od getCommandStruct
                        commandSender.sendMessage("/" + getCommandStruct().get(j).getName() + " - " + getCommandStruct().get(j).getDescription());
                        //gets the command name and description at the array position of the array
                    }
                    return true;
                }

                if(commandSender instanceof Player player && (usage.equalsIgnoreCase("player") || usage.equalsIgnoreCase("all"))) {
                    getCommandStruct().get(i).commandRun(commandSender, args, usage); //check if player is running player or all command
                } else if (!(commandSender instanceof Player) && usage.equalsIgnoreCase("console")) {
                    getCommandStruct().get(i).commandRun(commandSender, args, usage);
                } else {
                    commandSender.sendMessage("You cannot run this command"); //Edge case
                }

            }

        }

        return true;
    }

    public ArrayList<commandStruct> getCommandStruct() {
        return commandStruct;
    } // returns the command list
}
