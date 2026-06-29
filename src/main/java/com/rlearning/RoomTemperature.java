package com.rlearning;

import java.time.Instant;

public class RoomTemperature {

    private int temperature;
    private String room;
    private Instant timestamp;

    public RoomTemperature(int temperature, String room, Instant timestamp) {
        this.temperature = temperature;
        this.room = room;
        this.timestamp = timestamp;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
