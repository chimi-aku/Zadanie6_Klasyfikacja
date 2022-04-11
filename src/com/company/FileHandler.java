package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {



    public static ArrayList<KeyMeasurement> readFile(String path) {

        Scanner fileIn = null;

        try {
            fileIn = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int counter = 0;

        ArrayList<KeyMeasurement> keyMeasurements = new ArrayList<KeyMeasurement>();

        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();

            String[] separetedLine = line.split(",");

            // SOME PROBLEM WITH SOME FILE
            if(separetedLine.length == 3) {
                String key = separetedLine[0].trim();
                String pressTime = separetedLine[1].trim();
                String releaseTime = separetedLine[2].trim();

                KeyMeasurement measurement = new KeyMeasurement(key, Double.parseDouble(pressTime), Double.parseDouble(releaseTime));
                keyMeasurements.add(measurement);


                System.out.println(counter + " " + line);
                counter++;
            }

        }

        return keyMeasurements;

    }


}
