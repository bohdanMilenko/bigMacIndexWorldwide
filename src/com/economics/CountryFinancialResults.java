package com.economics;

public class CountryFinancialResults implements  Comparable<CountryFinancialResults>{

    private String countryName;
    private String currencyAbbreviation;
    private double pricePerBigMac;
    private double averageSalary;
    private double bigMacIndex;
    private int rank = 0;

    public CountryFinancialResults(String countryName, String currencyAbbreviation, double pricePerBigMac, double averageSalary)  {
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

    @Override
    public int compareTo(CountryFinancialResults o) {
        return Double.compare(this.bigMacIndex, o.bigMacIndex);
    }
}
