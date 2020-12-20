package com.systemsample.alarm;

import com.systemsample.SensorType;
import com.systemsample.SensorsData;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class AlarmSystem {

   private final Map<SensorType, SensorsData> activeAlarms = new ConcurrentHashMap<>();

    public void alarm(SensorType type, SensorsData sensorsData) {
        activeAlarms.putIfAbsent(type, sensorsData);
    }

    public void resetAlarm() {
        activeAlarms.clear();
    }

    public Map<SensorType, SensorsData> getActiveAlarms() {
        return activeAlarms.isEmpty() ? Collections.emptyMap() : new EnumMap<>(activeAlarms);
    }
}
