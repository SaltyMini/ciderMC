package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.plugin.Plugin;

public class ExpEvent implements Listener {

    private final Plugin plugin;

    public ExpEvent(Plugin plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void expGet(PlayerExpChangeEvent event) {

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("playerExp")) { return; }

        Player player = (Player) event.getPlayer();

        if(event.getAmount() > 0) {

            eventInstance.updateScore(player, 1);


        }
    }
}
