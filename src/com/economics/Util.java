package com.economics;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

class Util {

    private static Map<String, CountryFinancialResults> countyToIndexMap;

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
        int i=1;
        for (Map.Entry<String,CountryFinancialResults> record : list){
            record.getValue().setRank(i);
            i++;
            result.put(record.getKey(),record.getValue());
        }
        countyToIndexMap = new LinkedHashMap<>(result);
        return result;
    }

    static void queryCountryIndex(){
        System.out.println("Please enter a country: ");
        String requestedCountry = UserInputService.getStringFromCustomer();
        int i=0;
        if(countyToIndexMap.containsKey(requestedCountry)){
            CountryFinancialResults foundRecord = countyToIndexMap.get(requestedCountry);
            DecimalFormat df = new DecimalFormat("###,###.##");
            System.out.println(foundRecord.getCountryName() + " is " + foundRecord.getRank() + " country in the world to get the cheapest Big Mac. " +
                    "Average citizen of " + foundRecord.getCountryName() + " can buy " + df.format(foundRecord.getBigMacIndex()) + " burgers!");
        }else {
                queryCountryIndex();
                i++;
           if(i==3) {
               throw new NoSuchElementException("Failed to find info about this country");
           }

        }
    }
}
