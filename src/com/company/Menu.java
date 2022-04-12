package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static ArrayList<Sample> trainingSamples;
    private static Sample testSample ; // One Test sample, because it's leave-one-out approach
    private static String testFileName;

    private static String distanceMetric = "manhattan";

    public static void  handleMenu() {

        displayMenu();
        Scanner in = new Scanner(System.in);
        String choose = "";

        while(true) {

            choose = in.nextLine();

            switch (choose) {
                case "1":
                    testSample = selectTestSample();
                    trainingSamples = loadTrainingSamples();

                    calculateVectorsOfFeaturesOfTestSample();
                    calculateVectorsOfFeaturesOfTrainingsSamples();

                    displayMenu();
                    break;
                case "2":

                    displayMenu();
                    break;
                case "3":
                    displayMenu();
                    break;
                case "4":
                    chooseMetric();
                    displayMenu();
                    break;
                case "5":

                    DistancesHandler.calculateManhattanDistanceBetweenTwoSamples(testSample, trainingSamples.get(0));

                    displayMenu();
                    break;
                case "6":
                    System.out.println("Quiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("There is no such command!");
                    displayMenu();
                    break;
            }
        }


    }

    private static void  displayMenu() {

        System.out.println("1. Select Test Sample ");
        System.out.println("2. Select Naive Bayes Classification");
        System.out.println("3. Select kNN Classification");
        System.out.println("4. Select Metric");
        System.out.println("5. Start Classification");
        System.out.println("6. Quit");

    }

    private static void chooseMetric() {

        displayDistanceMetricMenu();
        Scanner in = new Scanner(System.in);
        String choose = "";

        while(true) {

            choose = in.nextLine();

            switch (choose) {
                case "1":
                    distanceMetric = "manhattan";
                    displayMenu();
                    break;
            }
        }
    }

    private static void displayDistanceMetricMenu() {
        System.out.println("1. Select Manhattan ");
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

        Sample testSample = new Sample(testFileName, FileHandler.readFile(filePath));

        return testSample;
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

    private static void calculateVectorsOfFeaturesOfTrainingsSamples() {

        for(Sample sample : trainingSamples) {

            sample.calculateOneVectorOfFeaturesPTAVG();

        }

    }

    private static void calculateVectorsOfFeaturesOfTestSample() {

        testSample.calculateOneVectorOfFeaturesPTAVG();

    }



}
