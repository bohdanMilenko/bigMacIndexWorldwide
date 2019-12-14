package com.Economics;

import java.text.DecimalFormat;
import java.util.Map;

public class CountryFinancialResults implements Comparable<CountryFinancialResults> {

    private String countryName;
    private String currencyAbbreviation;
    private double pricePerBigMac;
    private double averageSalary;
    private double bigMacIndex;
    private int rank = 0;

    public CountryFinancialResults(String countryName, String currencyAbbreviation, double pricePerBigMac, double averageSalary) {
        this.countryName = countryName;
        this.currencyAbbreviation = currencyAbbreviation;
        this.pricePerBigMac = pricePerBigMac;
        this.averageSalary = averageSalary;
        this.bigMacIndex = (averageSalary / pricePerBigMac);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getBigMacIndex() {
        return bigMacIndex;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getPricePerBigMac() {
        return pricePerBigMac;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public static void printOverallResults(Map<String, CountryFinancialResults> rankedCountries) {
        DecimalFormat df = new DecimalFormat("#");
        rankedCountries.forEach((k, v) -> System.out.println(v.getRank() + ". " + k + ". People in " + v.getCountryName() + " can purchase "
                + df.format(v.getAverageSalary() / v.getPricePerBigMac()) + " burgers."));
    }

    @Override
    public int compareTo(CountryFinancialResults o) {
        if (this.bigMacIndex < o.bigMacIndex) {
            return 1;
        } else if (this.bigMacIndex > o.bigMacIndex) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return this.countryName +": \n" + "\t" + this.pricePerBigMac +  "\n\t" + this.averageSalary;
    }
}
