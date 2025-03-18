package com.cidermc.untitled.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import net.milkbowl.vault.economy.Economy;
import com.gmail.nossr50.api.ExperienceAPI;


public class rankPlayerHandle implements Listener {

    public static boolean hasRequirementMoney(Player player, String requirement) {

        String moneyRequirment = requirement;
        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign

        Economy economy = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        double playerBal = economy.getBalance(player);

        return !(playerBal < Double.parseDouble(moneyRequirment));
    }

    public static boolean hasRequirementMCMMO(Player player, String requirement) {

        int requiredPowerLevel = Integer.parseInt(requirement.split(" ")[0]); //gets just the power level needed


        int powerLevel = ExperienceAPI.getPowerLevel(player);

        return powerLevel >= requiredPowerLevel;
    }


    public static void rankPlayerHandle(Player player, String rank, String[] requirments, String... lore) {
        
    }





}

