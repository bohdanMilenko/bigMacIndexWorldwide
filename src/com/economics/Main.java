package com.economics;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Map<String, CountryStatistics> countryToStatistics = new HashMap<>();
        try {
            Scanner scanner = new Scanner(new BufferedReader(new FileReader("bigMacData.csv")));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] countryInfo = line.split(",");
                CountryStatistics countryStatistics = createCountryRecord(countryInfo);

                countryToStatistics.put(countryStatistics.getCountryName(), countryStatistics);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Map<Integer, CountryStatistics> rankedCountries = calculateBigMacIndex(countryToStatistics);

        rankedCountries.forEach((k,v) -> System.out.println(k+". People in " + v.getCountryName() + " can purchase "
                + v.getAverageSalary()/v.getPricePerBigMac() + " burgers."));

//        Map<String, CountryStatistics> sortedMap = countryToStatistics
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.comparing()))
//                .collect(
//                        Collectors.toMap(e -> e.getKey(), Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        //sortedMap.forEach((k,v) -> System.out.println(k + " " + v.getCurrencyAbbreviation() + " " + + v.getAverageSalary()));


    }

    private static CountryStatistics createCountryRecord(String[] info) {
        String countryName = info[0];
        String currencyAbbreviation = info[1];
        double bigMacPrice = Double.parseDouble(info[2]);
        double averageSalary = Double.parseDouble(info[3]);
        return new CountryStatistics(countryName, currencyAbbreviation, bigMacPrice, averageSalary);
    }

    private static Map<Integer, CountryStatistics> calculateBigMacIndex(Map<String, CountryStatistics> inputMap) {
        CountryStatistics highestIndexRecord = null;
        Map<String, CountryStatistics> loopingMap = new HashMap<>(inputMap);
        Map<Integer, CountryStatistics> countryToBigMacIndex = new LinkedHashMap<>();
        while (!loopingMap.isEmpty()) {
            int i = 1;
            for (Map.Entry<String, CountryStatistics> record : loopingMap.entrySet()) {
                highestIndexRecord = record.getValue();
                double currentSalary = record.getValue().getAverageSalary();
                double currentPrice = record.getValue().getPricePerBigMac();
                double highestRecordsSalary = highestIndexRecord.getAverageSalary();
                double highestRecordsPrice = highestIndexRecord.getPricePerBigMac();
                if (highestIndexRecord == null || (currentSalary / currentPrice) > (highestRecordsSalary / highestRecordsPrice)) {
                    highestIndexRecord = record.getValue();
                    countryToBigMacIndex.put(i, highestIndexRecord);
                    loopingMap.remove(highestIndexRecord.getCountryName());
                    i++;
                }
            }
        }
        return countryToBigMacIndex;
    }
}



