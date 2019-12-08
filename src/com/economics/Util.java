package com.economics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Util {

    static Map<String, CountryFinancialResults> loadData() {
        Map<String, CountryFinancialResults> countryToStatistics = new HashMap<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("bigMacData.csv")));) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] countryInfo = line.split(",");
                CountryFinancialResults countryFinancialResults = createCountryRecord(countryInfo);
                countryToStatistics.put(countryFinancialResults.getCountryName(), countryFinancialResults);

            }
            return countryToStatistics;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    private static CountryFinancialResults createCountryRecord(String[] info) {
        String countryName = info[0];
        String currencyAbbreviation = info[1];
        double bigMacPrice = Double.parseDouble(info[2]);
        double averageSalary = Double.parseDouble(info[3]);
        return new CountryFinancialResults(countryName, currencyAbbreviation, bigMacPrice, averageSalary);
    }


    public static Map<Integer, CountryFinancialResults> calculateBigMacIndex(Map<String, CountryFinancialResults> inputMap) {

        Map<String, CountryFinancialResults> loopingMap = new HashMap<>(inputMap);
        Map<Integer, CountryFinancialResults> countryToBigMacIndex = new LinkedHashMap<>();
        CountryFinancialResults highestIndexRecord = loopingMap.get("Canada");
        while (!loopingMap.isEmpty()) {
            int i = 1;
            for (Map.Entry<String, CountryFinancialResults> record : loopingMap.entrySet()) {

                double currentSalary = record.getValue().getAverageSalary();
                double currentPrice = record.getValue().getPricePerBigMac();
                double highestRecordsSalary = highestIndexRecord.getAverageSalary();
                double highestRecordsPrice = highestIndexRecord.getPricePerBigMac();
                double currentBigMacIndex = (currentSalary / currentPrice);
                double highestBigMacIndex = (highestRecordsSalary / highestRecordsPrice);

                System.out.println("Inside loop " + i);
                if (currentBigMacIndex > highestBigMacIndex) {
                    highestIndexRecord = record.getValue();
                    countryToBigMacIndex.put(i, highestIndexRecord);
                    loopingMap.remove(highestIndexRecord.getCountryName());
                    i++;
                    System.out.println("Inside condition " + i);
                }
            }
        }
        return countryToBigMacIndex;
    }
}
