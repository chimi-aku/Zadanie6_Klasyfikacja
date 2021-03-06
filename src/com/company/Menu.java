package com.company;

import java.io.File;
import java.util.*;

public class Menu {

    private static ArrayList<Sample> allSamples;
    private static ArrayList<Sample> trainingSamples;
    private static Sample testSample ; // One Test sample, because it's leave-one-out approach
    private static String testFileName;
    private static Map<String, Double> distances = new HashMap<>();
    private static Map<String, Boolean> classificationResults = new HashMap<>();

    private static String distanceMetric = "manhattan";
    private static String classificationMethod = "knn";

    private static int k = 10; // k - Nearest Neighbor

    public static void  handleMenu() {

        displayMenu();
        Scanner in = new Scanner(System.in);
        String choose = "";

        while(true) {

            choose = in.nextLine();

            switch (choose) {
                case "1" -> {
                    testSample = selectTestSample();
                    trainingSamples = loadTrainingSamples();
                    calculateVectorsOfFeaturesOfTestSample();
                    calculateVectorsOfFeaturesOfTrainingsSamples();
                    displayMenu();
                }
                case "2" -> displayMenu();
                case "3" -> {
                    chooseKForKNN();
                    displayMenu();
                }
                case "4" -> {
                    chooseMetric();
                    displayMenu();
                }
                case "5" -> {
                    distances = DistancesHandler.calculateDistances(testSample, trainingSamples, distanceMetric);
                    KNN knn = new KNN(k, testSample, distances);
                    Boolean classificationRes = knn.classificate();
                    System.out.println(classificationRes);
                    displayMenu();
                }
                case "6" -> {
                    System.out.println("Total");
                    allSamples = loadAllSamples();
                    totalClassification(allSamples);
                    displayMenu();
                }
                case "7" -> {
                    System.out.println("Quiting...");
                    System.exit(0);
                }
                default -> {
                    System.out.println("There is no such command!");
                    displayMenu();
                }
            }
        }


    }

    private static void  displayMenu() {

        System.out.println("1. Select Test Sample ");
        System.out.println("2. Select Naive Bayes Classification");
        System.out.println("3. Select kNN Classification");
        System.out.println("4. Select Metric");
        System.out.println("5. Start Single Classification");
        System.out.println("6. Total Classification");
        System.out.println("7. Quit");

    }

    private static void chooseKForKNN() {

        Scanner in = new Scanner(System.in);
        String choose = "";

        System.out.println("Type in number of k - nearest neighbors (1 - 30)");

        choose = in.nextLine();
        int number = Integer.parseInt(choose);

        if(number < 1 || number > 30) {
            chooseKForKNN();
        }
        else {
            k = number;
        }


    }

    private static void chooseMetric() {

        displayDistanceMetricMenu();
        Scanner in = new Scanner(System.in);
        String choose = "";

        while(true) {

            choose = in.nextLine();

            switch (choose) {
                case "1" -> {
                    distanceMetric = "manhattan";
                    handleMenu();
                }
                case "2" -> {
                    distanceMetric = "euclidean";
                    handleMenu();
                }
                case "3" -> {
                    distanceMetric = "discreet";
                    handleMenu();
                }
                case "4" -> {
                    distanceMetric = "standard";
                    handleMenu();
                }
                case "5" -> {
                    distanceMetric = "max";
                    handleMenu();
                }
                case "6" -> {
                    distanceMetric = "lorentzian";
                    handleMenu();
                }
                default -> {
                    System.out.println("There is no such command!");
                    displayDistanceMetricMenu();
                }
            }
        }
    }

    private static void displayDistanceMetricMenu() {
        System.out.println("1. Select Manhattan ");
        System.out.println("2. Select Euclidean ");
        System.out.println("3. Select Discreet ");
        System.out.println("4. Select Max ");
        System.out.println("5. Select Standard ");
        System.out.println("6. Select Lorentzian ");
    }


    private static List<String> getAllFileNamesFromDirectory(String path) {

        List<String> results = new ArrayList<String>();

        File[] files = new File(path).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.

        for (File file : files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }

        return results;
    }


    private static void displayAllFilesInDirectory(List<String> fileNamesList) {

        for(String file : fileNamesList) {
            System.out.println(file);
        }

    }


    private static Sample selectTestSample() {

        String keystrokesPath = new File("").getAbsolutePath();
        keystrokesPath += "\\keystrokes";
        displayAllFilesInDirectory(getAllFileNamesFromDirectory(keystrokesPath));

        System.out.println("\n Choose test data file: (type in file name below)");

        Scanner in = new Scanner(System.in);
        testFileName = in.nextLine();
        String filePath = keystrokesPath + "\\" + testFileName;

        return new Sample(testFileName, FileHandler.readFile(filePath));
    }


    private static ArrayList<Sample> loadTrainingSamples() {

        // Loading all Sample except test sample which choose user

        String keystrokesPath = new File("").getAbsolutePath();
        keystrokesPath += "\\keystrokes";

        List<String> fileNamesList = getAllFileNamesFromDirectory(keystrokesPath);

        ArrayList<Sample> trainingSamples = new ArrayList<Sample>();

        for(String fileName : fileNamesList) {

            // Get data from sample
            if(!fileName.equals(testFileName)){
                String filePath = keystrokesPath + "\\" + fileName;
                ArrayList<KeyMeasurement> measurements = FileHandler.readFile(filePath);

                Sample sample = new Sample(fileName, measurements);
                trainingSamples.add(sample);

            }


        }

        return trainingSamples;


    }


    private static ArrayList<Sample> loadAllSamples() {
        // Loading all Samples

        String keystrokesPath = new File("").getAbsolutePath();
        keystrokesPath += "\\keystrokes";

        List<String> fileNamesList = getAllFileNamesFromDirectory(keystrokesPath);

        ArrayList<Sample> allSamples = new ArrayList<Sample>();

        for(String fileName : fileNamesList) {

            // Get data from sample
            String filePath = keystrokesPath + "\\" + fileName;
            ArrayList<KeyMeasurement> measurements = FileHandler.readFile(filePath);

            Sample sample = new Sample(fileName, measurements);
            allSamples.add(sample);

        }

        return allSamples;

    }


    private static void calculateVectorsOfFeaturesOfTrainingsSamples() {

        for(Sample sample : trainingSamples) {

            sample.calculateOneVectorOfFeaturesPTAVG();

        }

    }


    private static void calculateVectorsOfFeaturesOfTestSample() {

        testSample.calculateOneVectorOfFeaturesPTAVG();

    }

    public static void totalClassification(ArrayList<Sample> allSamples) {


        for (Sample sample : allSamples) {

            testSample = sample;
            testFileName = sample.getSampleName();
            trainingSamples = new ArrayList<>();

            if(trainingSamples.size() != 0 ) {
                trainingSamples.clear();
            }


            for(Sample s : allSamples) {

                if(!s.getSampleName().equals(testFileName)) {
                    trainingSamples.add(s);
                }
            }

            calculateVectorsOfFeaturesOfTestSample();
            calculateVectorsOfFeaturesOfTrainingsSamples();

            distances = DistancesHandler.calculateDistances(testSample, trainingSamples, distanceMetric);

            if(classificationMethod == "knn") {
                KNN knn = new KNN(k, testSample, distances);
                Boolean classificationRes = knn.classificate();
                classificationResults.put(testFileName, classificationRes);


            }
            else if (classificationMethod == "naive_bayes") {

            }

        }

        displayClassificationResults();


    }

    private static void displayClassificationResults() {

        Double accuracy = 0.0;

        // Iterating HashMap through for loop
        for (Map.Entry<String, Boolean> set :
                classificationResults.entrySet()) {

            if(set.getValue() == true) {
                accuracy += 1;
            }

            System.out.println(set.getKey() + " result: " + set.getValue());
        }

        accuracy /= classificationResults.size();
        System.out.println(accuracy);

    }



}
