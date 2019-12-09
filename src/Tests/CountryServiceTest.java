package Tests;

import com.Economics.CountryFinancialResults;
import com.Economics.CountryService;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;


class CountryServiceTest {

    Map<String, CountryFinancialResults>  countryToStatistics;

    @Before
    void setUp() {
        countryToStatistics = CountryService.loadData();

    }

    @Test
    void loadData() {
        assertTrue(countryToStatistics.size()>0);


    }

    @Test
    void calculateBigMacIndex() {
    }

    @Test
    void queryCountryIndex() {
    }
}