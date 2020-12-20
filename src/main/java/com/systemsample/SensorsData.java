package com.systemsample;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SensorsData {
    private final Instant timestamp;
    private final SensorValue humidity;
    private final SensorValue temperature;
}
