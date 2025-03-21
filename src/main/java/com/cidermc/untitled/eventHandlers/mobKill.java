package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;

public class mobKill implements Listener {


    @EventHandler
    public void onMobKill(EntityDeathEvent event) {

        currentEvent eventInstance = currentEvent.getInstance();

        if(!eventInstance.getEventState()) { return; } //return if event is not active

        if(!eventInstance.getCurrentEventType().equals("mobKill")) { return; } //checks if its a mobkill event

        ArrayList<String> scores = eventInstance.playerScores; //gets current scores
        Player player = event.getEntity().getKiller();




    }

}
