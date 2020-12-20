package com.systemsample.simulator;

import com.systemsample.SensorDataPublisher;
import com.systemsample.SensorValue;
import com.systemsample.SensorsData;
import java.time.Instant;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorsSimulator {

    private final Random random = new Random();

    @Autowired
    private SensorDataPublisher sensorDataPublisher;

    private SensorValue getHumidity() {
        return new SensorValue(getRandomInRange(0, 100));
    }

    private SensorValue getTemperature() {
        return new SensorValue(getRandomInRange(-50, 150));
    }

    private static float getRandomInRange(float min, float max) {
        return (float) Math.random() * (max - min) + min;
    }

    @Scheduled(fixedRate = 30000)
    public void doPublish() {
        sensorDataPublisher.publish(SensorsData.builder()
                .timestamp(Instant.now())
                .humidity(getHumidity())
                .temperature(getTemperature())
                .build());
    }
}
