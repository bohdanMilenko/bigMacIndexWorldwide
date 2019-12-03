package com.economics;

import java.util.Map;

public class CountryStatistics {

    private String currencyAbbreviation;
    private double pricePerBigMac;
    private double averageSalary;

    public CountryStatistics(String currencyAbbreviation, double pricePerBigMac, double averageSalary) {
        this.currencyAbbreviation = currencyAbbreviation;
        this.pricePerBigMac = pricePerBigMac;
        this.averageSalary = averageSalary;
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
