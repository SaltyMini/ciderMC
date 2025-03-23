package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.Plugin;

public class ExplodeEvent implements Listener {

    private final Plugin plugin;

    public ExplodeEvent(Plugin plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void explodeEvent(EntityExplodeEvent event) {

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("blockExplode")) { return; }

        Entity explodingEntity = event.getEntity();

        if (explodingEntity instanceof TNTPrimed) {
            TNTPrimed tnt = (TNTPrimed) explodingEntity;
            if (tnt.getSource() instanceof Player) {
                Player player = (Player) tnt.getSource();
                 if (player != null) {
                     String targetName = eventInstance.getTarget();
                     event.blockList().stream()
                            .filter(block -> block.getType().name().equals(targetName))
                            .forEach(block -> eventInstance.updateScore(player, 1));
                 }
            }
        }

    }

}
