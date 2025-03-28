package com.cidermc.untitled;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class currentEvent {

    private static currentEvent instance;
    private final Plugin plugin;

    private Map<String, Integer> playerScores = new HashMap<>();
    private String currentEventName = "none";
    private String currentEventType = "none"; //mobKill

    private String target = "none";
    private boolean eventActive = false;

    private currentEvent(Plugin plugin) {
        this.plugin = plugin;
        loadFromConfig();
    }

    public static currentEvent getInstance(Plugin plugin) {
        if (instance == null) {
            synchronized (currentEvent.class) {
                instance = new currentEvent(plugin);
            }
        }
        return instance;
    }

    //TODO file curation if not there and effor checking




    private void loadScores() {
        File playerScoresFile = new File(plugin.getDataFolder(), "playerScores.yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(playerScoresFile);

            if (pFile.contains("Scores")) {
                for (String key : Objects.requireNonNull(pFile.getConfigurationSection("Scores")).getKeys(false)) {
                    playerScores.put(key, pFile.getInt("Scores." + key)); }
            }
    }

    private void loadFromConfig() {
        File eventsFile = new File(plugin.getDataFolder(), "events.yml");
        FileConfiguration eFile = YamlConfiguration.loadConfiguration(eventsFile);

        String currentEvent = eFile.getString("event", "event1");
        this.eventActive = eFile.getBoolean("eventActive", false);

        this.currentEventName = eFile.getString(currentEvent + ".eventName", "none");
        this.currentEventType = eFile.getString(currentEvent+ ".eventType", "none");
        this.target = eFile.getString(currentEvent + ".target", "none");
    }

    public synchronized void saveScore() {
        File playerScoresFile = new File(plugin.getDataFolder(), "playerScores.yml");
        FileConfiguration pFile = YamlConfiguration.loadConfiguration(playerScoresFile);

        for (Map.Entry<String, Integer> entry : playerScores.entrySet()) {
            pFile.set("Scores." + entry.getKey(), entry.getValue());
        }

        try {
            pFile.save(playerScoresFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void eventReload() {
        saveScore();

        loadFromConfig();
        return;
    }

    public void scoreReload() {
        loadScores();

        return;
    }

    public int getScore(Player player) {
        return playerScores.getOrDefault(player.getName(), 0);
    }

    public void updateScore(Player player, int score) {
        String playerName = player.getName();
        playerScores.merge(playerName, score, Integer::sum);
    }


    public String getTarget() {
        return target;
    }

    public void setEventState(boolean args) {
        File playerScoresFile = new File(plugin.getDataFolder(), "events.yml");
        FileConfiguration eFile = YamlConfiguration.loadConfiguration(playerScoresFile);

        eFile.set("eventActive", args);

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

}
