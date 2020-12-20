package com.systemsample.controller;

import com.systemsample.SensorType;
import com.systemsample.SensorsData;
import com.systemsample.controller.dto.AlarmDto;
import com.systemsample.controller.dto.SensorSnapshotDto;
import com.systemsample.storage.SensorsDataSnapshot;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public Optional<SensorSnapshotDto> convert(SensorType type, SensorsDataSnapshot dataSnapshot) {
        switch (type) {
            case HUMIDITY:
                return Optional.of(convertHumidity(dataSnapshot));
            case TEMPERATURE:
                return Optional.of(convertTemperature(dataSnapshot));
            default:
                return Optional.empty();
        }
    }

    public Optional<AlarmDto> convert(SensorType type, SensorsData sensorsData) {
        switch (type) {
            case HUMIDITY:
                return  Optional.of(new AlarmDto()
                        .setType(type)
                        .setTimestamp(sensorsData.getTimestamp())
                        .setValue(sensorsData.getHumidity().toString()));
            case TEMPERATURE:
                return  Optional.of(new AlarmDto()
                        .setType(type)
                        .setTimestamp(sensorsData.getTimestamp())
                        .setValue(sensorsData.getTemperature().toString()));
            default:
                return Optional.empty();
        }
    }


    private SensorSnapshotDto convertTemperature(SensorsDataSnapshot dataSnapshot) {
        return new SensorSnapshotDto()
                .setType(SensorType.TEMPERATURE)
                .setAvg(dataSnapshot.getAvgTemperature().toString())
                .setMax(dataSnapshot.getMaxTemperature().toString())
                .setMin(dataSnapshot.getMinTemperature().toString())
                .setTimestamp(dataSnapshot.getLatestSensorsData().getTimestamp())
                .setCurrent(dataSnapshot.getLatestSensorsData().getTemperature().toString());
    }

    private SensorSnapshotDto convertHumidity(SensorsDataSnapshot dataSnapshot) {
        return new SensorSnapshotDto()
                .setType(SensorType.HUMIDITY)
                .setAvg(dataSnapshot.getAvgHumidity().toString())
                .setMax(dataSnapshot.getMaxHumidity().toString())
                .setMin(dataSnapshot.getMinHumidity().toString())
                .setTimestamp(dataSnapshot.getLatestSensorsData().getTimestamp())
                .setCurrent(dataSnapshot.getLatestSensorsData().getHumidity().toString());
    }

}
