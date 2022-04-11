package com.company;

public class KeyMeasurement {

    private String key;
    private Double pressTime;
    private Double releaseTime;

    public KeyMeasurement(String key, Double pressTime, Double releaseTime) {
        this.key = key;
        this.pressTime = pressTime;
        this.releaseTime = releaseTime;
    }
}
