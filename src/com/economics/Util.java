package com.economics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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


    public static Map<String, CountryFinancialResults> calculateBigMacIndex(Map<String, CountryFinancialResults> inputMap) {

        List<Map.Entry<String,CountryFinancialResults>> list = new ArrayList<>(inputMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<String, CountryFinancialResults> result = new LinkedHashMap<>();
        for (Map.Entry<String,CountryFinancialResults> record : list){
            result.put(record.getKey(),record.getValue());
        }
        return result;
    }
}
