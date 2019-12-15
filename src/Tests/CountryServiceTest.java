package Tests;

import com.Economics.CountryFinancialResults;
import com.Economics.CountryService;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryServiceTest {
    Map<String, CountryFinancialResults> countryToStatistics;

    private final String countryName = "Australia";
    private final String countryCurrency = "AUD";
    private final double countryPricePerBurger = 4.26;
    private final double countryAverageSalary = 53349.41;

    public static final String fileWithNoFormattingApplied = "bigMacData.csv";
    public static final String fileWithFormattedAverageSalary = "testDataSets/bigMacData_testData_AVGSalaryFormattedAccounting.csv";
    public static final String fileWithFormattedBurgerPrice = "testDataSets/bigMacData_testData_burgerPriceFormattedAccounting.csv";
    public static final String fileWithFormattedNumbers = "testDataSets/bigMacData_testData_doublesFormattedAsAccounting.csv";
    public static final String fileWithoutHeaders = "testDataSets/bigMacData_testData_noHeaders.csv";


    @Test
    void loadDataNoFormattingApplied() {
        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData(fileWithNoFormattingApplied);
        assertionChecker(countryToStatistics);
    }

    @Test
    void loadDataAverageSalaryAccountingFormat() {
        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData(fileWithFormattedAverageSalary);
        System.out.println(countryToStatistics.size());
        assertionChecker(countryToStatistics);
    }

    @Test
    void loadDataBurgerPriceAccountingFormat() {
        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData(fileWithFormattedBurgerPrice);
        assertionChecker(countryToStatistics);
    }

    @Test
    void loadDataDoublesFormattedAsAccounting() {
        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData(fileWithFormattedNumbers);
        assertionChecker(countryToStatistics);
    }

    @Test
    void loadDataNoHeadersPresent() {
        Map<String, CountryFinancialResults> countryToStatistics = CountryService.loadData(fileWithoutHeaders);

        countryToStatistics.forEach((k, v) -> System.out.println(v.toString()));
        assertionChecker(countryToStatistics);
    }


    void assertionChecker(Map<String, CountryFinancialResults> countryToStatistics) {
        CountryFinancialResults australia = countryToStatistics.get(countryName);
        System.out.println(australia.toString());
        assertEquals(17, countryToStatistics.size());
        assertEquals(countryName, australia.getCountryName());
        assertEquals(countryCurrency, australia.getCurrencyAbbreviation());
        assertEquals(countryPricePerBurger, australia.getPricePerBigMac());
        assertEquals(countryAverageSalary, australia.getAverageSalary());
    }

    //Helped to identify BOM present before first record. BOM preventing from finding "Australia", but this country was actually loaded.
    void checkEachCountryPresentAfterLoad(Map<String, CountryFinancialResults> countryToStatistics){
        System.out.println(countryToStatistics.containsKey("Australia"));
        System.out.println(countryToStatistics.containsKey("Hungary"));
        System.out.println(countryToStatistics.containsKey("United States"));
        System.out.println(countryToStatistics.containsKey("Japan"));
        System.out.println(countryToStatistics.containsKey("Britain"));
        System.out.println(countryToStatistics.containsKey("Switzerland"));
        System.out.println(countryToStatistics.containsKey("New Zealand"));
        System.out.println(countryToStatistics.containsKey("Canada"));
        System.out.println(countryToStatistics.containsKey("Czech Republic"));
        System.out.println(countryToStatistics.containsKey("South Korea"));
        System.out.println(countryToStatistics.containsKey("Sweden"));
        System.out.println(countryToStatistics.containsKey("Norway"));
        System.out.println(countryToStatistics.containsKey("Denmark"));
        System.out.println(countryToStatistics.containsKey("Poland"));
        System.out.println(countryToStatistics.containsKey("Mexico"));
        System.out.println(countryToStatistics.containsKey("Israel"));
        System.out.println(countryToStatistics.containsKey("Chile"));
    }

}