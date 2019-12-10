package com.Economics;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserInputService {

    public static Scanner scanner = new Scanner(System.in);


    public static double getNumberFromCustomer2() {
        double numberToReturn = -1;
        try {
            do {
                numberToReturn = scanner.nextDouble();
                scanner.nextLine();
                if (numberToReturn < 0) {
                    System.out.println("Please enter a valid number >= 0!");
                }
            } while (numberToReturn < 0);
            return numberToReturn;
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number >= 0!");
            scanner.nextLine();
            getNumberFromCustomer();
        }
        return numberToReturn;
    }

    public static double getNumberFromCustomer() {
        double numberToReturn = -1;
        try {
            numberToReturn = scanner.nextDouble();
            //scanner.nextLine();
            if (numberToReturn < 0) {
                System.out.println("Please enter a valid number >= 0!");
                System.out.println("Current value: " + numberToReturn);
                numberToReturn = getNumberFromCustomer();
            }
            return numberToReturn;
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number >= 0!");
            scanner.nextInt();
            //scanner.nextLine();
            String abc =  scanner.nextLine();
            getNumberFromCustomer();

        }
        return numberToReturn;
    }

    public static String getStringFromCustomer() {
        String returnString = "";
        int i = 0;
        try {
            returnString = scanner.nextLine();
            while (true) {
                if (Pattern.matches("[a-zA-z]+", returnString)) {
                    return returnString;
                } else {
                    System.out.println("Please enter a string!");
                    returnString = scanner.nextLine();
                    i++;
                    if (i == 3) throw new RuntimeException("Invalid input 3 times in a row!");
                }
            }
        } catch (InputMismatchException  e) {
            System.out.println("Wrong input from customer " + e.getMessage());
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println("Unable to get user input: " + e.getMessage());
            e.printStackTrace();
        }
        return returnString.substring(0, 1).toUpperCase() + returnString.substring(1).toLowerCase();
    }


}
