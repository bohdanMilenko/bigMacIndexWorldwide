package com.economics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Map<String, CountryStatistics> countryToStatistics = new HashMap<>();

        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("bigMacData.csv")));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] countryInfo = line.split(",");
                CountryStatistics countryStatistics =  CountryStatistics.createCountryRecord(countryInfo);

                countryToStatistics.put(countryStatistics.getCountryName(), countryStatistics);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        countryToStatistics.forEach((k,v)-> System.out.println(k));
    }



}
