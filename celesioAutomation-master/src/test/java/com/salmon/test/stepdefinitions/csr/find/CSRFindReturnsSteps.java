package com.salmon.test.stepdefinitions.csr.find;

import com.salmon.test.pageobjects.csr.find.CSRFindReturnsPage;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindReturnsSteps {
    private CSRFindReturnsPage CSRFindReturnsPage;

    public CSRFindReturnsSteps(CSRFindReturnsPage CSRFindReturnsPage) {
        this.CSRFindReturnsPage = CSRFindReturnsPage;
    }

    @When("^I enter the search criteria to find returns")
    public void I_enter_the_search_criteria_to_find_returns(@Transpose DataTable fieldValueTable) throws Throwable {
        CSRFindReturnsPage.clearSearchFields();

        Map<String, String> fieldValueMap = fieldValueTable.asMap(String.class, String.class);
        CSRFindReturnsPage.enterSearchQuery(fieldValueMap);
    }

    @Then("^the search results contains a return with$")
    public void the_search_results_contains_a_order_with(@Transpose DataTable searchResultsTable) {
        Map<String, String> searchResultsMap = searchResultsTable.asMap(String.class, String.class);
        assertThat(CSRFindReturnsPage.verifySearchResults(searchResultsMap)).isTrue();
    }
}
