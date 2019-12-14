package com.Economics;

import CustomExceptions.IncorrectlyProcessedStringFormatException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountryService {

    public static final String UTF8_BOM = "\uFEFF";

    /**
     * @return
     */
    //One functional - change to increase abstraction - check for headers
    //Optional interface - wrapper
    public static Map<String, CountryFinancialResults> loadData(String filePath) {
        Map<String, CountryFinancialResults> stringCountryFinancialResultsHashMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filePath)))) {
            String possibleHeaders = scanner.nextLine();
            if (!checkIfHasHeaders(possibleHeaders)) {
                possibleHeaders = isValidLine(possibleHeaders);
                if(possibleHeaders.startsWith(UTF8_BOM)){
                    possibleHeaders = possibleHeaders.substring(1);
                }
                String[] countryInfo = possibleHeaders.split(",");
                CountryFinancialResults countryFinancialResults = createCountryRecord(countryInfo);
                stringCountryFinancialResultsHashMap.put(countryFinancialResults.getCountryName(), countryFinancialResults);
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                line = isValidLine(line);
                if (line.equals("")) {
                    throw new IncorrectlyProcessedStringFormatException("Line contained more comas than expected!");
                }
                String[] countryInfo = line.split(",");
                CountryFinancialResults countryFinancialResults = createCountryRecord(countryInfo);
                stringCountryFinancialResultsHashMap.put(countryFinancialResults.getCountryName(), countryFinancialResults);
            }
           // stringCountryFinancialResultsHashMap.forEach( (k,v) -> System.out.println(v.toString()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return stringCountryFinancialResultsHashMap;
    }


    private static boolean checkIfHasHeaders(String possibleHeaders) {
        String headers = cleanSingleRecord(possibleHeaders);
        String[] line = headers.split(",");
        try {
            Double.parseDouble(line[2]);
            Double.parseDouble(line[3]);
        } catch (NumberFormatException e) {
            System.out.println("Headers are present");
            return true;
        }
        System.out.println("No headers in the csv");
        return false;
    }

    private static String isValidLine(String inputString) {
        String outputString = "";
        int comasCount = 0;
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == ',') {
                comasCount++;
            }
        }
        switch (comasCount) {
            case 3:
                outputString = cleanSingleRecord(inputString);
                return outputString;
            case 4:
                outputString = handleExtraComa(inputString);
                return outputString;
            default:
                return outputString;
        }
    }

    private static String cleanSingleRecord(String inputString) {
        String outputString = "";
        outputString = inputString.replaceAll("\\$", "");
        outputString = outputString.replace("\"", "");
        return outputString;
    }

    private static String handleExtraComa(String inputString) {
        String outputString = "";
        String[] countryInfo = inputString.split(",");
        for (int i = 2; i < countryInfo.length; i++) {
            String record = countryInfo[i];
            record = cleanSingleRecord(record);
            record = record.replaceAll(" ", "");
            record = record.replaceAll(",", "");
            outputString = outputString + "," + record;
        }
        String countryName = cleanSingleRecord(countryInfo[0]);
        String countryCurrency = cleanSingleRecord(countryInfo[1]);
        outputString = countryName + "," + countryCurrency + outputString;
        int comaPlace = outputString.lastIndexOf(",");
        StringBuilder sb = new StringBuilder(outputString);
        sb.replace(comaPlace, comaPlace + 1, "");
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static CountryFinancialResults createCountryRecord(String[] info) {
        //Regex format is: "Any number of digits . Any number of digits"
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
                System.out.println(Arrays.toString(info));
                throw new IncorrectlyProcessedStringFormatException("Wrong type of data inside of file! Check if numbers are formatted as numbers");
            }
        } else {
            System.out.println(Arrays.toString(info));
            throw new IncorrectlyProcessedStringFormatException("File is not read correctly! Contains more than 4 variables in line.");

        }
    }


    public static Map<String, CountryFinancialResults> calculateBigMacIndex(Map<String, CountryFinancialResults> inputMap) {
        List<Map.Entry<String, CountryFinancialResults>> list = new ArrayList<>(inputMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, CountryFinancialResults> result = new HashMap<>();
        int i = 1;
        for (Map.Entry<String, CountryFinancialResults> record : list) {
            record.getValue().setRank(i);
            i++;
            result.put(record.getKey(), record.getValue());
        }
        return result;
    }

    static void queryCountryIndex(Map<String, CountryFinancialResults> inputMap) {
        System.out.println("Please enter a country: ");
        String requestedCountry = UserInputService.getStringFromCustomer();
        int i = 0;
        if (inputMap.containsKey(requestedCountry)) {
            CountryFinancialResults foundRecord = inputMap.get(requestedCountry);
            DecimalFormat df = new DecimalFormat("###,###");
            System.out.println(foundRecord.getCountryName() + " is " + foundRecord.getRank() + " country in the world to get the cheapest Big Mac. " +
                    "Average citizen of " + foundRecord.getCountryName() + " can buy " + df.format(foundRecord.getBigMacIndex()) + " burgers!");
        } else {
            System.out.println("No info, try another one");
            queryCountryIndex(inputMap);
            i++;
            if (i == 3) {
                throw new NoSuchElementException("Failed to find info about this country");
            }

        }
    }
}
