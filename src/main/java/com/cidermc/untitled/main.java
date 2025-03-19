package com.cidermc.untitled;

import com.cidermc.untitled.commands.commandManager;
import com.cidermc.untitled.gui.ranksGUI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.invui.InvUI;

import java.util.Objects;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("cider")).setExecutor(new commandManager());
        getServer().getPluginManager().registerEvents(new playerCountGUI(), this);
        getServer().getPluginManager().registerEvents(new ranksGUI(), this);
    // runs on start
        InvUI.getInstance().setPlugin(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
