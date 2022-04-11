package com.company;

import java.io.File;
import java.util.Scanner;

public class Menu {


    public static void  handleMenu() {

        displayMenu();
        Scanner in = new Scanner(System.in);
        String choose = "";

        while(true) {
            choose = in.nextLine();

            switch (choose) {
                case "1":

                    String path = new File("").getAbsolutePath();
                    path += "\\keystrokes\\#01_1.txt";

                    System.out.println(path);
                    FileHandler.readFile(path);

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

        System.out.println("1. Select Test Data ");
        System.out.println("2. Select Naive Bayes Classification");
        System.out.println("3. Select kNN Classification");
        System.out.println("4. Select Metric");
        System.out.println("5. Start Classification");
        System.out.println("6. Quit");

    }



}
