package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

public class PlayerKillPlayer implements Listener {

    private final Plugin plugin;

    public PlayerKillPlayer(Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("playerKill")) { return; }

        Player killer = event.getEntity().getKiller();

        if(killer != null) {
         eventInstance.updateScore(killer, 1);
        }

    }

}
