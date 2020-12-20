package com.systemsample.alarm;

import com.systemsample.SensorType;
import com.systemsample.SensorValue;
import com.systemsample.SensorsData;
import com.systemsample.SensorsDataListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlarmConditionDetector implements SensorsDataListener {

    @Autowired
    private AlarmSystem alarmSystem;

    private final SensorValue temperatureThreshold = new SensorValue(99.00f);
    private final SensorValue humidityThreshold = new SensorValue(55.00f);

    @Override
    @EventListener
    public void handle(SensorsData data) {
        if (temperatureThreshold.compareTo(data.getTemperature()) < 0) {
            log.info("Exceeded {} threshold {} (max allowed:{})", SensorType.TEMPERATURE, data.getTemperature(), temperatureThreshold);
            alarmSystem.alarm(SensorType.TEMPERATURE, data);
        }

        if (humidityThreshold.compareTo(data.getHumidity()) < 0) {
            log.info("Exceeded {} threshold {} (max allowed:{})", SensorType.HUMIDITY, data.getHumidity(), humidityThreshold);
            alarmSystem.alarm(SensorType.HUMIDITY, data);
        }
    }


}
