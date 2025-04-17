package com.cidermc.untitled.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import net.milkbowl.vault.economy.Economy;
import com.gmail.nossr50.api.ExperienceAPI;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;


public class RankPlayerHandle implements Listener {

    public static boolean hasRequirementMoney(Player player, String requirement) {
        String moneyRequirment = requirement;

        if (!(moneyRequirment.charAt(0) == '$')) {
            player.sendMessage(requirement);
            throw new IllegalArgumentException("Invalid money requirement format. Expected format: $<amount>");
        }

        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign

        Economy economy = Objects.requireNonNull(getServer().getServicesManager().getRegistration(Economy.class)).getProvider();

        double playerBal = economy.getBalance(player);

        return !(playerBal < Double.parseDouble(moneyRequirment));
    }

    public static boolean hasRequirementMCMMO(Player player, String requirement) {
        int requiredPowerLevel = Integer.parseInt(requirement.split(" ")[0]); //gets just the power level needed
        int powerLevel = ExperienceAPI.getPowerLevel(player);

        return powerLevel >= requiredPowerLevel;
    }


    public static boolean playerRankUpAttempt(Player player, String rank, String[] requirements, String[] bonuses) {

        String moneyRequirment = requirements[0];

        if (!(moneyRequirment.charAt(0) == '$')) {
            throw new IllegalArgumentException("Invalid money requirement format. Expected format: $<amount>");
        }

        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign
        Economy economy = Objects.requireNonNull(getServer().getServicesManager().getRegistration(Economy.class)).getProvider();

        player.sendMessage("Attempting to rank up to " + rank);

        if(!economy.has(player, Double.parseDouble(moneyRequirment))) {
            player.sendMessage(moneyRequirment);

            player.sendMessage("You don't have enough money to buy this rank!");
            return true;
        }


        player.sendMessage("Removing " + moneyRequirment + " from your balance!");
        economy.withdrawPlayer(player, Double.parseDouble(moneyRequirment));


        if(requirements.length > 1) {
            if (RankPlayerHandle.hasRequirementMCMMO(player, requirements[1])) {
                player.sendMessage("Your Power Level is not high enough to buy this rank!");
                return true;
            }
        }
        //TODO: CHECK THE REST OF MCMMO


        //rank the player up#


        getServer().dispatchCommand(getServer().getConsoleSender(), "lp user " + player.getName() + " promote ranks");
        getServer().dispatchCommand(getServer().getConsoleSender(), "tags set " + player.getName() + " " + rank);
        player.sendMessage("You have been ranked up to " + rank + "!");


        String blocksBonus = bonuses[0];
        String rareKeysBonus = bonuses[1];
        String seasonalKeysBonus = bonuses[2];

        blocksBonus = blocksBonus.split(" ")[0];
        rareKeysBonus = rareKeysBonus.split(" ")[0];
        seasonalKeysBonus = seasonalKeysBonus.split(" ")[0];

        if(Integer.parseInt(blocksBonus) != 0) {
            //TODO give players blocks
            player.sendMessage("TODO: You have been given " + blocksBonus + " blocks!");
        }

        if(Integer.parseInt(rareKeysBonus) != 0) {
            player.sendMessage("TODO: You have been given " + rareKeysBonus + " rare keys!");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"crate key give " + player.getName() + " rareKey 1");
        }

        if(Integer.parseInt(seasonalKeysBonus) != 0) {

            player.sendMessage("TODO: You have been given " + seasonalKeysBonus + " seasonal keys!");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),"crate key give " + player.getName() + " seasonalKey 1");
        }

        RanksGUI.closeGUI(player);

        return false;
    }





}

