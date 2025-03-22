package com.cidermc.untitled.eventHandlers;

import com.cidermc.untitled.currentEvent;
import net.bytebuddy.utility.nullability.NeverNull;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class EatEvent implements Listener {

    private final Plugin plugin;

    public EatEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();

        currentEvent eventInstance = currentEvent.getInstance(plugin);

        if(!eventInstance.getEventState()) { return; } //return if event is not active
        if(!eventInstance.getCurrentEventType().equals("consume")) { return; }

        String targetName = eventInstance.getTarget();
        Material mat = Material.valueOf(targetName.toUpperCase());

        if(event.getItem().getType() == mat) {
            eventInstance.updateScore(player, 1);
        }


    }

}
