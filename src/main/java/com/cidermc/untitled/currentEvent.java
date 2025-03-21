package com.cidermc.untitled;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class currentEvent {

    private static currentEvent instance;
    private final Plugin plugin;
    //TODO initialise this in onEnable
    //TODO pull data from yml file
    public Map<String, Integer> playerScores = new HashMap<>();
    private String currentEventName = null;
    private String currentEventType = null; //mobKill

    private String target = null;
    private boolean eventActive = false;

    private currentEvent(Plugin plugin) {
        this.plugin = plugin;
        loadFromConfig();
    }

    public void loadFromConfig() {
        File configFile = new File(plugin.getDataFolder(), "events.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        this.currentEventName = config.getString("event.eventName", "No Event");
        this.currentEventType = config.getString("event.eventType", "none");
        this.target = config.getString("event.target", "none");
        this.eventActive = config.getBoolean("event.active", false);
    }

    /**
    public void saveToConfig() {
        File configFile = new File(plugin.getDataFolder(), "playerScores.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        config.set("event.eventName", currentEventName);
        config.set("event.eventType", currentEventType);
        config.set("event.target", target);
        config.set("event.active", eventActive);

        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     **/


    public static currentEvent getInstance(Plugin plugin) {
        if (instance == null) {
            synchronized (currentEvent.class) {
                instance = new currentEvent(plugin);
            }
        }
        return instance;
    }

    public String getMobTarget() {
        return target;
    }

    public int getScore(Player player) {
        return playerScores.getOrDefault(player.getName(), 0);
    }

    public void updateScore(Player player, int score) {
        String playerName = player.getName();
        playerScores.merge(playerName, score, Integer::sum);
    }

    public void setEventActive(boolean args) {
        this.eventActive = args;
    }

    public boolean getEventState() {
        return this.eventActive;
    }

    public String getCurrentEventType() {
        return this.currentEventType;
    }

    public String getCurrentEventName() {
        return this.currentEventName;
    }

    //TODO save datya to yml file



}
