package com.systemsample.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.systemsample.SensorType;
import java.time.Instant;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SensorSnapshotDto {
    @JsonProperty("Sensor Type")
    private SensorType type;
    @JsonProperty("Timestamp")
    private Instant timestamp;
    @JsonProperty("Current Value")
    private String current;
    @JsonProperty("Max Value")
    private String max;
    @JsonProperty("Min Value")
    private String min;
    @JsonProperty("Avg Value")
    private String avg;
}

