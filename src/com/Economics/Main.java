package com.Economics;

import Tests.CountryServiceTest;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CountryService countryService = new CountryService();

        Map<String, CountryFinancialResults> countryToStatistics = countryService.loadData(CountryServiceTest.fileWithoutHeaders);

        Map<String, CountryFinancialResults> rankedCountries = countryService.calculateBigMacIndex(countryToStatistics);

        rankedCountries.forEach((k,v)-> System.out.println(k +": " + v.getCountryName() +" " + v.getPricePerBigMac() + " " + v.getAverageSalary()));

        CountryFinancialResults.printOverallResults(rankedCountries);

        countryService.queryCountryIndex(rankedCountries);


    }

}


