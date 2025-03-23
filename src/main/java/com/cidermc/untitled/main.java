package com.cidermc.untitled;

import com.cidermc.untitled.commands.CommandManager;
import com.cidermc.untitled.eventHandlers.BlockBreak;
import com.cidermc.untitled.eventHandlers.MobKill;
import com.cidermc.untitled.eventHandlers.OnJoinLeave;
import com.cidermc.untitled.gui.RanksGUI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.invui.InvUI;

import java.io.File;
import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        generateConfigs();

        Objects.requireNonNull(getCommand("cider")).setExecutor(new CommandManager());
        getServer().getPluginManager().registerEvents(new RanksGUI(), this);
    // runs on start
        InvUI.getInstance().setPlugin(this);

        currentEvent.getInstance(this);

        getServer().getPluginManager().registerEvents(new MobKill(this), this);
        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new OnJoinLeave(), this);
    }

    @Override
    public void onDisable() {

        currentEvent eventInstance = currentEvent.getInstance(this);
        eventInstance.saveScore();

    }

    private void generateConfigs() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        saveDefaultConfig();

        String[] configFiles = {
                "events.yml",
                "playerScores.yml"
        };

        for (String fileName : configFiles) {
            File configFile = new File(getDataFolder(), fileName);
            if (!configFile.exists()) {
                saveResource(fileName, false);
            }
        }
    }
}
