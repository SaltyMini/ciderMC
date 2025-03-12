package com.cidermc.untitled.eventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.bukkit.Bukkit.getServer;


public class onJoinLeave {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int online = getServer().getOnlinePlayers().size();


        String date = dateUtil.getDate();

        String formattedForFile = date + "--" + online;
        //TODO: Write this to file
    }



    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int online = getServer().getOnlinePlayers().size();


        String date = dateUtil.getDate();

        String formattedForFile = date + "--" + online;
        //TODO: Write this to file
    }



    public class dateUtil {
        public static String getDate() {
            String datePattern = "dd-MM-yyyy kk-mm";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);
            return simpleDateFormat.format(new Date());
        }
    }
}

