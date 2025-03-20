package com.cidermc.untitled;

import org.bukkit.entity.Entity;

import java.util.ArrayList;

public class currentEvent {

    private static currentEvent instance;

    public static currentEvent getInstance() {
        if (instance == null) {
            synchronized (currentEvent.class) {
                instance = new currentEvent();
            }
        }
        return instance;
    }

    //TODO initialise this in onEnable
    //TODO pull data from yml file

    public ArrayList<String> playerScores = new ArrayList<String>();
    //format for storing data
    //playerName--score-- player names on evens scores on odd

    private final String currentEventName = null;
    private final String currentEventType = null; //mobKill
    private boolean eventActive = false;
    private final Entity currentEntity = null;

    public void setEventActive(boolean args) {
        this.eventActive = args;
    }

    public boolean getEventState() {
        return this.eventActive;
    }

    public Entity getCurrentEntity() {
        return this.currentEntity;
    }

    public String getCurrentEventType() {
        return this.currentEventType;
    }

    public String getCurrentEventName(String type) {
        return this.currentEventName;
    }

    //TODO save datya to yml file



}
