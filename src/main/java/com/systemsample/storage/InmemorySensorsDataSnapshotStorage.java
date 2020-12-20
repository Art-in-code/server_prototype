package com.systemsample.storage;

import com.systemsample.SensorValue;
import com.systemsample.SensorsData;
import com.systemsample.SensorsDataListener;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InmemorySensorsDataSnapshotStorage implements SensorsDataSnapshotStorage, SensorsDataListener {

    private final AtomicReference<SensorsDataSnapshot> snapshot = new AtomicReference<>();

    @Override
    public void updateSnapshot(SensorsData data) {
        snapshot.accumulateAndGet(SensorsDataSnapshot.builder().latestSensorsData(data).build(), this::accumulate);
    }

    @Override
    public SensorsDataSnapshot getSnapshot() {
        return snapshot.get();
    }

    @Override
    @EventListener
    public void handle(SensorsData data) {
        updateSnapshot(data);
    }

    private SensorsDataSnapshot accumulate(SensorsDataSnapshot prev, SensorsDataSnapshot next) {
        SensorValue newHumidity = next.getLatestSensorsData().getHumidity();
        SensorValue newTemperature = next.getLatestSensorsData().getTemperature();
        if (prev != null) {
            long overallRecordsNumber = prev.getOverallRecordsNumber();
            return SensorsDataSnapshot.builder()
                    .overallRecordsNumber(overallRecordsNumber + 1)
                    .latestSensorsData(next.getLatestSensorsData())
                    .maxHumidity(prev.getMaxHumidity().max(newHumidity))
                    .avgHumidity(prev.getAvgHumidity().asAvg(newHumidity, overallRecordsNumber))
                    .minHumidity(prev.getMinHumidity().min(newHumidity))
                    .maxTemperature(prev.getMaxTemperature().max(newTemperature))
                    .avgTemperature(prev.getAvgTemperature().asAvg(newTemperature, overallRecordsNumber))
                    .minTemperature(prev.getMinTemperature().min(newTemperature))
                    .build();
        }
        return SensorsDataSnapshot.builder()
                .overallRecordsNumber(1)
                .latestSensorsData(next.getLatestSensorsData())
                .maxHumidity(newHumidity)
                .avgHumidity(newHumidity)
                .minHumidity(newHumidity)
                .maxTemperature(newHumidity)
                .avgTemperature(newTemperature)
                .minTemperature(newTemperature)
                .build();

    }

}
