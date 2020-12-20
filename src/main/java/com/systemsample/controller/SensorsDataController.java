package com.systemsample.controller;

import com.systemsample.SensorType;
import com.systemsample.SensorsData;
import com.systemsample.alarm.AlarmSystem;
import com.systemsample.controller.dto.AlarmDto;
import com.systemsample.controller.dto.SensorSnapshotDto;
import com.systemsample.controller.dto.SystemStateDto;
import com.systemsample.storage.SensorsDataSnapshot;
import com.systemsample.storage.SensorsDataSnapshotStorage;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorsDataController {

    @Autowired
    private AlarmSystem alarmSystem;
    @Autowired
    private SensorsDataSnapshotStorage sensorsDataSnapshotStorage;
    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping("/system")
    public SystemStateDto getCurrentSystemState() {
      Map<SensorType, SensorsData> activeAlarms = alarmSystem.getActiveAlarms();
      SensorsDataSnapshot dataSnapshot = sensorsDataSnapshotStorage.getSnapshot();

        List<SensorSnapshotDto> sensors = Stream.of(SensorType.values())
                .map(type -> dtoConverter.convert(type, dataSnapshot))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        List<AlarmDto> alarms = activeAlarms.entrySet().stream()
                .map(entry -> dtoConverter.convert(entry.getKey(), entry.getValue()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return new SystemStateDto().setSensors(sensors).setAlarms(alarms);

    }

    @PostMapping("/system/alarms/reset")
    public void resetAlarms() {
        alarmSystem.resetAlarm();
    }

}
