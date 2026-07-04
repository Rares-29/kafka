package com.rlearning;

import java.time.Instant;

public record RoomTemperature(int sensorId, int temperature, String room, Instant timestamp) {
}
