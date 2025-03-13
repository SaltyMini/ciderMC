package com.cidermc.untitled;

import com.cidermc.untitled.commands.commandManager;
import com.cidermc.untitled.gui.playerCountGUI;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.xenondevs.invui.InvUI;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("cider").setExecutor(new commandManager());
        getServer().getPluginManager().registerEvents(new playerCountGUI(), this);
        InvUI.getInstance().setPlugin(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
