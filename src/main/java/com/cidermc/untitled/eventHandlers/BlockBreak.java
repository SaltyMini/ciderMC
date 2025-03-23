package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

public class BlockBreak implements Listener {

    private final Plugin plugin;

    public BlockBreak(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent event) {

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("blockBreak")) { return; }

        if(event.getBlock().getType().equals(eventInstance.getTarget())) {
            Player player = (Player) event.getPlayer();
            eventInstance.updateScore(player, 1);
        }

    }

}
