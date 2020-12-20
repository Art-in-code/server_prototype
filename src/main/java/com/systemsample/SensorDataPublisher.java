package com.systemsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SensorDataPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(SensorsData data) {
        applicationEventPublisher.publishEvent(data);
    }
}
