package com.systemsample;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SensorType {
    TEMPERATURE("Temperature"), HUMIDITY("Humidity");

    private String viewName;

    SensorType(String viewName) {
        this.viewName = viewName;
    }

    @JsonValue
    public String getViewName() {
        return viewName;
    }
}
