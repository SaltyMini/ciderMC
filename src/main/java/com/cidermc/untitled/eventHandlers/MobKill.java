package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.Plugin;

public class MobKill implements Listener {

    private final Plugin plugin;

    public MobKill(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobKill(EntityDeathEvent event) {


        //player event stuff
        if (event.getEntity().getKiller() == null) {
         return;
        }

        currentEvent eventInstance = currentEvent.getInstance(plugin);


        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("mobKill")) { return; } //checks if its a mobkill event

        Player player = event.getEntity().getKiller();

        String targetName = eventInstance.getTarget();
        EntityType entityType = EntityType.valueOf(targetName.toUpperCase());


        if(event.getEntity().getType().equals(entityType)) {
            eventInstance.updateScore(player, 1);
        }
    }

}
