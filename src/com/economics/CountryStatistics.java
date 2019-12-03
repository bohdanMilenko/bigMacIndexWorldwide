package com.economics;

import java.util.Map;

public class CountryStatistics {

    private String countryName;
    private String currencyAbbreviation;
    private double pricePerBigMac;
    private double averageSalary;

    public CountryStatistics(String countryName,String currencyAbbreviation, double pricePerBigMac, double averageSalary) {
        this.countryName = countryName;
        this.currencyAbbreviation = currencyAbbreviation;
        this.pricePerBigMac = pricePerBigMac;
        this.averageSalary = averageSalary;
    }

    static CountryStatistics createCountryRecord(String[] info){
        String countryName = info[0];
        String currencyAbbreviation = info[1];
        double bigMacPrice = Double.parseDouble(info[2]);
        double averageSalary = Double.parseDouble(info[3]);
        return new CountryStatistics(countryName, currencyAbbreviation, bigMacPrice, averageSalary);
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrencyAbbreviation() {
        return currencyAbbreviation;
    }

    public void setCurrencyAbbreviation(String currencyAbbreviation) {
        this.currencyAbbreviation = currencyAbbreviation;
    }

    public double getPricePerBigMac() {
        return pricePerBigMac;
    }

    public void setPricePerBigMac(double pricePerBigMac) {
        this.pricePerBigMac = pricePerBigMac;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public void setAverageSalary(double averageSalary) {
        this.averageSalary = averageSalary;
    }
}
