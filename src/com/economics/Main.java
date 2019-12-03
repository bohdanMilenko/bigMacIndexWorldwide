package com.economics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map<String, CountryStatistics> countryToFinancialResults = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("bigMacData.csv")));
            scanner.useDelimiter(",");
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String countryName = scanner.next();
                scanner.skip(scanner.delimiter());
                String currencyAbbreviation = scanner.next();
                scanner.skip(scanner.delimiter());
                String bigMacPrice = scanner.next();
                scanner.skip(scanner.delimiter());
                String averageSalary = scanner.next();


                System.out.println(averageSalary);
               // CountryStatistics statistics = new CountryStatistics(currencyAbbreviation, bigMacPrice, averageSalary);
               // countryToFinancialResults.put(countryName , statistics);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        countryToFinancialResults.forEach((k,v)-> System.out.println(k));
    }
}
