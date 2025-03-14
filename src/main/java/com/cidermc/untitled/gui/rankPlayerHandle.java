package com.cidermc.untitled.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import net.milkbowl.vault.economy.Economy;


public class rankPlayerHandle implements Listener {

    public static boolean hasRequirementMoney(Player player, String requirement) {

        String moneyRequirment = requirement;
        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign

        Economy economy = Bukkit.getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        double playerBal = economy.getBalance(player);

        if(playerBal < Double.parseDouble(moneyRequirment)) return false;

        return true;
    }

    public static boolean hasRequirementMCMMO(Player player, String requirement) {

        int requiredPowerLevel = Integer.parseInt(requirement.split(" ")[0]); //gets just the power level needed


        //format of mcmmoRequirement, "100 MCMMO power level required"

        return true;
    }

    public static void rankPlayerHandle(Player player, String rank, String[] requirments, String... lore) {}





}

