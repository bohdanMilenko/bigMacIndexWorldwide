package com.Economics;

import Tests.CountryServiceTest;

import java.util.Map;

public class Main {

    public static void main(String[] args) {


        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData(CountryServiceTest.fileWithoutHeaders);

        Map<String, CountryFinancialResults> rankedCountries = CountryService.calculateBigMacIndex(countryToStatistics);

        rankedCountries.forEach((k,v)-> System.out.println(k +": " + v.getCountryName() +" " + v.getPricePerBigMac() + " " + v.getAverageSalary()));

        CountryFinancialResults.printOverallResults(rankedCountries);

        CountryService.queryCountryIndex(rankedCountries);


    }

}


