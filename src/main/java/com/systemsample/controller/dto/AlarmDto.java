package com.systemsample.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.systemsample.SensorType;
import java.time.Instant;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AlarmDto {
    @JsonProperty("Sensor Type")
    private SensorType type;
    @JsonProperty("Happened at")
    private Instant timestamp;
    @JsonProperty("Cause Value")
    private String value;
}
