package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.plugin.Plugin;

public class EatEvent implements Listener {

    private final Plugin plugin;

    public PlayerKillPlayer(Plugin plugin) {
        this.plugin = plugin;
    }

    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(event.getItem().getType() == Material.CAKE) {
            eventInstance.updateScore(killer, 1);
        }


    }

}
