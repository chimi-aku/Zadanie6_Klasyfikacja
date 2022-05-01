package com.company;


import java.util.*;

public class DistancesHandler {

    public static Map<String, Double> calculateDistances(Sample testSample, ArrayList<Sample> trainingSamples, String metric) {

        Map<String, Double> distances = new HashMap<>();

        for(Sample sample : trainingSamples) {

            String sampleName = sample.getSampleName();
            Double dist = switch (metric) {
                case "manhattan" -> calculateManhattanDistanceBetweenTwoSamples(testSample, sample);
                case "euclidean" -> calculateEuclideanDistanceBetweenTwoSamples(testSample, sample);
                case "discreet" -> calculateDiscreetDistanceBetweenTwoSamples(testSample, sample);
                case "max" -> calculateMaxDistanceBetweenTwoSamples(testSample, sample);
                case "standard" -> calculateMetricGeneratedByTheStandardDistanceBetweenTwoSamples(testSample, sample);
                case "lorentzian" -> calculateLorentzianDistanceBetweenTwoSamples(testSample, sample);
                default -> 0.0;
            };


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

    public static Double calculateEuclideanDistanceBetweenTwoSamples(Sample testSample, Sample secondSample) {

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

            // calculating Euclidean Distance
            distance += Math.pow((firstEntry.getValue() - secondEntry.getValue()),2);
        }


        return Math.sqrt(distance);
    }

    public static Double calculateMetricGeneratedByTheStandardDistanceBetweenTwoSamples(Sample testSample, Sample secondSample) {

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

            // calculating Euclidean Distance
            distance += Math.pow((firstEntry.getValue() - secondEntry.getValue()),3);
        }

        return Math.cbrt(distance);
    }

    public static Double calculateDiscreetDistanceBetweenTwoSamples(Sample testSample, Sample secondSample) {

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

            // calculating Discreet Distance
            distance += (firstEntry.getValue().equals(secondEntry.getValue()) ?1:0);
        }


        return Math.sqrt(distance);
    }

    public static Double calculateMaxDistanceBetweenTwoSamples(Sample testSample, Sample secondSample) {

        Double distance = 0.0;
        Map<String, Double> testSampleVector = getVectorWithDTAVG(testSample);
        Map<String, Double> secondSampleVector = getVectorWithDTAVG(secondSample);

        // Iterating over two hashmaps

        Iterator<Map.Entry<String, Double>> firstIterator = testSampleVector.entrySet().iterator();
        Iterator<Map.Entry<String, Double>> secondIterator = secondSampleVector.entrySet().iterator();

        ArrayList<Double> distances = new ArrayList<>();

        while(secondIterator.hasNext()) {

            // next hashmaps element
            Map.Entry<String, Double> firstEntry = firstIterator.next();
            Map.Entry<String, Double> secondEntry = secondIterator.next();

            // calculating Maximum Distance
            distances.add(Math.abs((firstEntry.getValue() - secondEntry.getValue()))) ;
        }


        return Collections.max(distances);
    }

    public static Double calculateLorentzianDistanceBetweenTwoSamples(Sample testSample, Sample secondSample) {

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
            distance += Math.log(1 + Math.abs(firstEntry.getValue() - secondEntry.getValue()));
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
