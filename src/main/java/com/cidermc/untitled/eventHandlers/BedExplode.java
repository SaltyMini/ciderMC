package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class BedExplode implements Listener {

    private final Plugin plugin;

    public BedExplode(Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void blockInteract(PlayerInteractEvent event) {

        if(event.getClickedBlock() == null && !event.getClickedBlock().getType().toString().contains("BED")) {
            currentEvent eventInstance = currentEvent.getInstance(plugin);

            if(!eventInstance.getEventState()) { return; } //return if event is not active
            if(eventInstance.getCurrentEventType().equals("bedExplode")) { return; }

                if(event.getPlayer().getWorld().getName().equalsIgnoreCase("world_nether") || event.getPlayer().getWorld().toString().equalsIgnoreCase("world_the_end")) {
                    Player player = event.getPlayer();
                    eventInstance.updateScore(player, 1);
            }
        }



    }
}
