package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler {



    public  static void readFile(String path) {

        Scanner fileIn = null;

        try {
            fileIn = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int counter = 0;

        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();

            String[] separetedLine = line.split(", ");

            String key = separetedLine[0].trim();
            String pressTime = separetedLine[1].trim();
            String releseTime = separetedLine[1].trim();


            System.out.println(counter + " " + line);
            counter++;
        }

    }


}
