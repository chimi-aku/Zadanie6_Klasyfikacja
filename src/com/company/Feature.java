package com.company;

public class Feature {

    private String key;
    private Double dwellTimeSum;
    private Double dwellTimeAvg;
    private Double numberOfKey;

    public Feature(String key) {
        this.key = key;
        this.dwellTimeSum = 0.0;
        this.dwellTimeAvg = 0.0;
        this.numberOfKey = 0.0;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getDwellTimeSum() {
        return dwellTimeSum;
    }

    public void setDwellTimeSum(Double dwellTimeSum) {
        this.dwellTimeSum = dwellTimeSum;
    }

    public Double getDwellTimeAvg() {
        return dwellTimeAvg;
    }

    public void setDwellTimeAvg(Double dwellTimeAvg) {
        this.dwellTimeAvg = dwellTimeAvg;
    }

    public Double getNumberOfKey() {
        return numberOfKey;
    }

    public void setNumberOfKey(Double numberOfKey) {
        this.numberOfKey = numberOfKey;
    }
}
