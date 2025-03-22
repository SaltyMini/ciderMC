package com.cidermc.untitled;

import com.cidermc.untitled.commands.commandManager;
import com.cidermc.untitled.eventHandlers.blockBreak;
import com.cidermc.untitled.eventHandlers.mobKill;
import com.cidermc.untitled.eventHandlers.onJoinLeave;
import com.cidermc.untitled.gui.ranksGUI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.invui.InvUI;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("cider")).setExecutor(new commandManager());
        getServer().getPluginManager().registerEvents(new ranksGUI(), this);
    // runs on start
        InvUI.getInstance().setPlugin(this);

        currentEvent.getInstance(this);

        getServer().getPluginManager().registerEvents(new mobKill(this), this);
        getServer().getPluginManager().registerEvents(new blockBreak(), this);
        getServer().getPluginManager().registerEvents(new onJoinLeave(), this);
    }

    @Override
    public void onDisable() {

        currentEvent eventInstance = currentEvent.getInstance(this);
        eventInstance.saveScore();

    }
}
