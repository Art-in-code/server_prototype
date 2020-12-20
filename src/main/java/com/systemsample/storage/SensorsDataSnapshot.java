package com.systemsample.storage;

import com.systemsample.SensorValue;
import com.systemsample.SensorsData;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SensorsDataSnapshot {
    private final SensorValue maxTemperature;
    private final SensorValue minTemperature;
    private final SensorValue avgTemperature;
    private final SensorValue maxHumidity;
    private final SensorValue minHumidity;
    private final SensorValue avgHumidity;
    private final SensorsData latestSensorsData;
    private final long overallRecordsNumber;
}
