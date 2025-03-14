package com.cidermc.untitled.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.net.http.WebSocket;

public class rankPlayerHandle implements Listener {

    public static boolean hadRequirementMoney(Player player, String[] requirements) {

        String moneyRequirment = requirements[0];
        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign

        
        return true;
    }

    public static boolean hadRequirementMCMMO(Player player, String[] requirements) {

        String moneyRequirment = requirements[0];
        moneyRequirment = moneyRequirment.substring(1); //Remove $ sign


        return true;
    }

    public static void rankPlayerHandle(Player player, String rank, String[] requirments, String... lore) {}





}

