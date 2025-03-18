package com.cidermc.untitled.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import net.milkbowl.vault.economy.Economy;
import com.gmail.nossr50.api.ExperienceAPI;

import static org.bukkit.Bukkit.getServer;


public class rankPlayerHandle implements Listener {

    public static boolean hasRequirementMoney(Player player, String requirement) {

        String moneyRequirment = requirement;
        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign

        Economy economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        double playerBal = economy.getBalance(player);

        return !(playerBal < Double.parseDouble(moneyRequirment));
    }

    public static boolean hasRequirementMCMMO(Player player, String requirement) {
        int requiredPowerLevel = Integer.parseInt(requirement.split(" ")[0]); //gets just the power level needed
        int powerLevel = ExperienceAPI.getPowerLevel(player);

        return powerLevel >= requiredPowerLevel;
    }


    public static boolean playerRankUpAttempt(Player player, String rank, String[] requirements, String[] bonuses) {

        if(rankPlayerHandle.hasRequirementMoney(player, requirements[0])) {
            player.sendMessage("You don't have enough money to buy this rank!");
            return true;
        }

        if(requirements[1] != null) {
            if (rankPlayerHandle.hasRequirementMCMMO(player, requirements[1])) {
                player.sendMessage("Your Power Level is not high enough to buy this rank!");
                return true;
            }
        }

        //rank the player up

        getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + player.getName() + " promote ranks");
        getServer().dispatchCommand(getServer().getConsoleSender(), "tags set " + player.getName() + " " + rank);

        //handle bonuses


        return false;
    }





}

