package com.company;

import java.util.*;

public class Sample {

    private String sampleName;
    private ArrayList<KeyMeasurement> keyMeasurements;
    private Map<String, Feature> vectorOfFeatures = new HashMap<>();
    private ArrayList<String> keyList = new ArrayList<>();


    public Sample(String sampleName, ArrayList<KeyMeasurement> keyMeasurements) {
        this.sampleName = sampleName;
        this.keyMeasurements = keyMeasurements;
    }

    public void calculateVectorOfFeaturesPTAVG() {

        // Calculate Vector of Features based on average press time

        for(KeyMeasurement k : keyMeasurements) {

            Feature feature;
            String key = k.getKey();

            if(!vectorOfFeatures.containsKey(key)) {
                feature = new Feature(key);
                keyList.add(key);
            }
            else {
                feature = vectorOfFeatures.get(key);
            }

            Double currDwellTimeSum = feature.getDwellTimeSum();
            currDwellTimeSum += k.getReleaseTime();
            feature.setDwellTimeSum(currDwellTimeSum);

            Double numberOfKey = feature.getNumberOfKey();
            numberOfKey++;
            feature.setNumberOfKey(numberOfKey);

            vectorOfFeatures.put(key, feature);

        }

        // Calculating AVG dwell time for each key
        for (String keyName : keyList) {

            Feature feature = vectorOfFeatures.get(keyName);

            Double sum = feature.getDwellTimeSum();
            Double num = feature.getNumberOfKey();

            feature.setDwellTimeAvg(sum / num);

        }

        System.out.println("Calc done");

    }
}
