package com.cidermc.untitled;

import com.cidermc.untitled.commands.CommandManager;
import com.cidermc.untitled.eventHandlers.*;
import com.cidermc.untitled.gui.RanksGUI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.invui.InvUI;

import java.io.File;
import java.util.Objects;

public final class main extends JavaPlugin {
    private RanksGUI ranksGUI;

    @Override
    public void onEnable() {
        generateConfigs();

        this.ranksGUI = new RanksGUI();
        getServer().getPluginManager().registerEvents(this.ranksGUI, this);

        Objects.requireNonNull(getCommand("cider")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("ranks")).setExecutor(new CommandManager());
        Objects.requireNonNull(getCommand("inventorycount")).setExecutor(new CommandManager());

        InvUI.getInstance().setPlugin(this);
        currentEvent.getInstance(this);
        registerEvents();

    }
    public RanksGUI getRanksGUI() {
        return ranksGUI;
    }

    @Override
    public void onDisable() {

        currentEvent eventInstance = currentEvent.getInstance(this);
        eventInstance.saveScore();

    }

    private void registerEvents() {
        var pm = getServer().getPluginManager();
        pm.registerEvents(new BedExplode(this), this);
        pm.registerEvents(new BlockBreak(this), this);
        pm.registerEvents(new CraftEvent(this), this);
        pm.registerEvents(new EatEvent(this), this);
        pm.registerEvents(new ExpEvent(this), this);
        pm.registerEvents(new ExplodeEvent(this), this);
        pm.registerEvents(new MobKill(this), this);
        pm.registerEvents(new PerWorldKeepInv(this), this);
        pm.registerEvents(new PlayerKillPlayer(this), this);
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
