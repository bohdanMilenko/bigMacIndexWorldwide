package com.economics;

import java.text.DecimalFormat;
import java.util.Formatter;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        Map<String, CountryFinancialResults> countryToStatistics = Util.loadData();

        Map<String, CountryFinancialResults> rankedCountries = Util.calculateBigMacIndex(countryToStatistics);

        CountryFinancialResults.printOverallResults(rankedCountries);

        Util.queryCountryIndex();



//        Map<String, CountryStatistics> sortedMap = countryToStatistics
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.comparing()))
//                .collect(
//                        Collectors.toMap(e -> e.getKey(), Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        //sortedMap.forEach((k,v) -> System.out.println(k + " " + v.getCurrencyAbbreviation() + " " + + v.getAverageSalary()));


    }


}



