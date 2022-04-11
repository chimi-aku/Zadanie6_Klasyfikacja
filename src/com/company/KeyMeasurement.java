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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getPressTime() {
        return pressTime;
    }

    public void setPressTime(Double pressTime) {
        this.pressTime = pressTime;
    }

    public Double getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Double releaseTime) {
        this.releaseTime = releaseTime;
    }
}
