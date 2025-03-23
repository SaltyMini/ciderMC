package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.plugin.Plugin;

public class CraftEvent implements Listener {

    private final Plugin plugin;

    public CraftEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("craftEvent")) { return; }

        if(event.getResult().toString().equalsIgnoreCase(eventInstance.getTarget())) {

            Player player = (Player) event.getWhoClicked();

            eventInstance.updateScore(player, 1);
        }


    }
}

//exp get
