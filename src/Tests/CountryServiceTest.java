package Tests;

import com.Economics.CountryFinancialResults;
import com.Economics.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CountryServiceTest {
    Map<String, CountryFinancialResults> countryToStatistics;

    private final String countryName = "Australia";
    private final String countryCurrency = "AUD";
    private final double countryPricePerBurger = 4.26;
    private final double countryAverageSalary = 53349.41;

    @BeforeEach
    void setUp() {
        countryToStatistics = CountryService.loadData("bigMacData.csv");
    }

    @Test
    void loadData() {
        assertTrue(countryToStatistics.size() > 0);
        CountryFinancialResults australia = countryToStatistics.get("Australia");
        assertEquals(countryName, australia.getCountryName());
        assertEquals(countryCurrency, australia.getCurrencyAbbreviation());
        assertEquals(countryPricePerBurger, australia.getPricePerBigMac());
        assertEquals(countryAverageSalary, australia.getAverageSalary());
    }
}