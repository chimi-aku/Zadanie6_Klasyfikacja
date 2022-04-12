package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DistancesHandler {

    public static Map<String, Double> calculateDistances(Sample testSample, ArrayList<Sample> trainingSamples, String metric) {

        Map<String, Double> distances = new HashMap<>();

        for(Sample sample : trainingSamples) {

            String sampleName = sample.getSampleName();
            Double dist = 0.0;

            if(metric == "manhattan") {
                dist = calculateManhattanDistanceBetweenTwoSamples(testSample, sample);
            }


            distances.put(sampleName, dist);
        }

        return distances;
    }


    public static Double calculateManhattanDistanceBetweenTwoSamples(Sample testSample, Sample secondSample) {

        Double distance = 0.0;
        Map<String, Double> testSampleVector = getVectorWithDTAVG(testSample);
        Map<String, Double> secondSampleVector = getVectorWithDTAVG(secondSample);

        // Iterating over two hashmaps

        Iterator<Map.Entry<String, Double>> firstIterator = testSampleVector.entrySet().iterator();
        Iterator<Map.Entry<String, Double>> secondIterator = secondSampleVector.entrySet().iterator();

        while(secondIterator.hasNext()) {

            // next hashmaps element
            Map.Entry<String, Double> firstEntry = firstIterator.next();
            Map.Entry<String, Double> secondEntry = secondIterator.next();

            // calculating Manhattan Distance
            distance += Math.abs(firstEntry.getValue() - secondEntry.getValue());
        }


        return distance;
    }

    private static Map<String, Double> getVectorWithDTAVG(Sample sample) {

        // Geting vector with only dwell average time. This will be esential to calulate every distance

        Map<String, Double> vector = new HashMap<>();

        // Iterating HashMap through for loop
        for (Map.Entry<String, Feature> set :
                sample.getVectorOfFeatures().entrySet()) {

            vector.put(set.getKey(), set.getValue().getDwellTimeAvg());

        }

        return vector;
    }

}
