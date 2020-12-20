package com.systemsample;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SensorValue implements Comparable<SensorValue> {

    private final float value;

    @Override
    public int compareTo(SensorValue o) {
        return Float.compare(value, o.value);
    }

    public SensorValue max(SensorValue v) {
        return this.compareTo(v) > 0 ? this : v;
    }

    public SensorValue min(SensorValue v) {
        return this.compareTo(v) > 0 ? v : this;
    }

    public SensorValue asAvg(SensorValue augment, long elementsCount) {
        return new SensorValue(
                elementsCount * value / (elementsCount + 1) + augment.value / (elementsCount + 1));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
