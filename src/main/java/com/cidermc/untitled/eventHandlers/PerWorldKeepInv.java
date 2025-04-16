package com.cidermc.untitled.eventHandlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;



public class PerWorldKeepInv implements Listener {

    private final Plugin plugin;

    public PerWorldKeepInv(Plugin plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void playerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();

        if(!player.hasPermission("cider.pwki.bypass")) {
            return;
        }

        File eventsFile = new File(plugin.getDataFolder(), "events.yml");
        FileConfiguration file = YamlConfiguration.loadConfiguration(eventsFile);

        String world = player.getWorld().toString();

        if(file.contains("per-world-keep-inventory." + world)) {
            event.setKeepInventory(file.getBoolean("per-world-keep-inventory." + world));
        } else {
            if(!file.contains("per-world-keep-inventory.default")) { return; }
            event.setKeepInventory(file.getBoolean("per-world-keep-inventory.default"));
        }

    }

}
