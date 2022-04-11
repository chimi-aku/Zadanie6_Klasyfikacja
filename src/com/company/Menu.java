package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static ArrayList<KeyMeasurement> trainingSamples;
    private static ArrayList<KeyMeasurement> testSample; // One Test sample, because it's leave-one-out approach
    private static String testFileName;

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

                    displayMenu();
                    break;
                case "2":
                    displayMenu();
                    break;
                case "3":
                    displayMenu();
                    break;
                case "4":
                    displayMenu();
                    break;
                case "5":
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


    private static ArrayList<KeyMeasurement> selectTestSample() {

        String keystrokesPath = new File("").getAbsolutePath();
        keystrokesPath += "\\keystrokes";
        displayAllFilesInDirectory(getAllFileNamesFromDirectory(keystrokesPath));

        System.out.println("\n Choose test data file: (type in file name below)");

        Scanner in = new Scanner(System.in);
        testFileName = in.nextLine();
        String filePath = keystrokesPath + "\\" + testFileName;

        ArrayList<KeyMeasurement> testSample = FileHandler.readFile(filePath);

        return testSample;
    }

    private static ArrayList<KeyMeasurement> loadTrainingSamples() {

        // Loading all Sample except test sample which choose user

        String keystrokesPath = new File("").getAbsolutePath();
        keystrokesPath += "\\keystrokes";

        List<String> fileNamesList = getAllFileNamesFromDirectory(keystrokesPath);

        ArrayList<KeyMeasurement> trainingSamples = new ArrayList<KeyMeasurement>();

        for(String fileName : fileNamesList) {

            // Get data from sample
            if(fileName != testFileName){
                String filePath = keystrokesPath + "\\" + fileName;
                ArrayList<KeyMeasurement> sample = FileHandler.readFile(filePath);

                // Add data to array containing all training Samples Data
                for (KeyMeasurement s : sample) {
                    trainingSamples.add(s);
                }
            }


        }

        return trainingSamples;


    }



}
