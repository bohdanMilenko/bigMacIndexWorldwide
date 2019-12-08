package com.economics;

import java.util.*;

public class Main {

    public static void main(String[] args) {


        Map<String, CountryFinancialResults> countryToStatistics = Util.loadData();

        Map<Integer, CountryFinancialResults> rankedCountries = Util.calculateBigMacIndex(countryToStatistics);

        rankedCountries.forEach((k, v) -> System.out.println(k + ". People in " + v.getCountryName() + " can purchase "
                + v.getAverageSalary() / v.getPricePerBigMac() + " burgers."));

//        Map<String, CountryStatistics> sortedMap = countryToStatistics
//                .entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.comparing()))
//                .collect(
//                        Collectors.toMap(e -> e.getKey(), Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        //sortedMap.forEach((k,v) -> System.out.println(k + " " + v.getCurrencyAbbreviation() + " " + + v.getAverageSalary()));


    }


}



