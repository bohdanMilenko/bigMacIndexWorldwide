package com.Economics;

import java.util.Map;

public class Main {

    public static void main(String[] args) {


        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData("bigMacData.csv");

        Map<String, CountryFinancialResults> rankedCountries = CountryService.calculateBigMacIndex(countryToStatistics);

        CountryFinancialResults.printOverallResults(rankedCountries);

        CountryService.queryCountryIndex(rankedCountries);


    }

}


