package com.Economics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryService {

    private static Map<String, CountryFinancialResults> countyToIndexMap;


    /**
     * @return
     */

    //One functional - change to increase abstraction - check for headers
    //Optional interface - wrapper
    public static Map<String, CountryFinancialResults> loadData(String filePath) {
        Map<String, CountryFinancialResults> stringCountryFinancialResultsHashMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            //Skip headers
            String possibleHeaders = scanner.nextLine();
            if(!checkIfHasHeaders(possibleHeaders)){
                String[] countryInfo = possibleHeaders.split(",");
                CountryFinancialResults countryFinancialResults = createCountryRecord(countryInfo);
                stringCountryFinancialResultsHashMap.put(countryFinancialResults.getCountryName(),countryFinancialResults);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] countryInfo = line.split(",");
                CountryFinancialResults countryFinancialResults = createCountryRecord(countryInfo);
                stringCountryFinancialResultsHashMap.put(countryFinancialResults.getCountryName(), countryFinancialResults);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return stringCountryFinancialResultsHashMap;
    }

    private static boolean checkIfHasHeaders(String possibleHeaders){
        String[] line = possibleHeaders.split(",");
        try {
            Double.parseDouble(line[2]);
            Double.parseDouble(line[3]);
        }catch (InputMismatchException e){
            System.out.println("Headers are present");
            return true;
        }
        return false;
    }


    //todo method that checks the accuracy of Line - isValidLine
    private static CountryFinancialResults createCountryRecord(String[] info) {
        //Regex format is: "Any number of digits . Any number of digits"
        //TODO - RENAME
        Pattern digitDotDigit = Pattern.compile("\\d+.\\d+");
        Matcher matcherIndex2 = digitDotDigit.matcher(info[2]);
        Matcher matcherIndex3 = digitDotDigit.matcher(info[3]);

        if (info.length == 4) {
            if (matcherIndex2.matches() && matcherIndex3.matches()) {
                String countryName = info[0];
                String currencyAbbreviation = info[1];
                double bigMacPrice = Double.parseDouble(info[2]);
                double averageSalary = Double.parseDouble(info[3]);
                return new CountryFinancialResults(countryName, currencyAbbreviation, bigMacPrice, averageSalary);
            } else {
                throw new IllegalArgumentException("Wrong type of data inside of file! Check if numbers are formatted as numbers");
            }
        } else {
            //correct msg + print line which causes problem
            throw new IllegalArgumentException("File is not read correctly, as the string is empty!");
        }
    }

    //TODO separate exception class to make it custom


    public static Map<String, CountryFinancialResults> calculateBigMacIndex(Map<String, CountryFinancialResults> inputMap) {

        List<Map.Entry<String, CountryFinancialResults>> list = new ArrayList<>(inputMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
//TODO GET RID OF LinkedHashMap
        Map<String, CountryFinancialResults> result = new LinkedHashMap<>();
        int i = 1;
        for (Map.Entry<String, CountryFinancialResults> record : list) {
            record.getValue().setRank(i);
            i++;
            result.put(record.getKey(), record.getValue());
        }
        countyToIndexMap = new LinkedHashMap<>(result);
        return result;
    }

    static void queryCountryIndex() {
        System.out.println("Please enter a country: ");
        String requestedCountry = UserInputService.getStringFromCustomer();
        int i = 0;
        if (countyToIndexMap.containsKey(requestedCountry)) {
            CountryFinancialResults foundRecord = countyToIndexMap.get(requestedCountry);
            DecimalFormat df = new DecimalFormat("###,###");
            System.out.println(foundRecord.getCountryName() + " is " + foundRecord.getRank() + " country in the world to get the cheapest Big Mac. " +
                    "Average citizen of " + foundRecord.getCountryName() + " can buy " + df.format(foundRecord.getBigMacIndex()) + " burgers!");
        } else {
            System.out.println("No info, try another one");
            queryCountryIndex();
            i++;
            if (i == 3) {
                throw new NoSuchElementException("Failed to find info about this country");
            }

        }
    }
}
