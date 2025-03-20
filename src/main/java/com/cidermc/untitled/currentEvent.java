package com.cidermc.untitled;

import java.util.ArrayList;

public class currentEvent {

    //TODO initialise this in onEnable
    //TODO pull data from yml file

    public ArrayList<String> playerScores = new ArrayList<String>();
    //format for storing data
    //playerName--score-- player names on evens scores on odd

    public String currentEventName;
    public String currentEventType; //mobKill



    public String getCurrentEventType() {
        return currentEventType;
    }

    public void getCurrentEventName(String type) {
        this.currentEventName = type;
    }

    //TODO save datya to yml file



}
