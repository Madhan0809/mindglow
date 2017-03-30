package com.salmon.test.stepdefinitions.csr.find;

import com.salmon.test.pageobjects.csr.find.CSRFindCustomersPage;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindCustomersSteps {
    private CSRFindCustomersPage csrFindCustomersPage;

    public CSRFindCustomersSteps(CSRFindCustomersPage csrFindCustomersPage) {
        this.csrFindCustomersPage = csrFindCustomersPage;
    }

    @When("^I enter the search criteria to find customers$")
    public void I_enter_the_search_criteria_to_find_customers(@Transpose DataTable fieldValueTable) throws Throwable {
        csrFindCustomersPage.clearSearchFields();

        Map<String, String> fieldValueMap = fieldValueTable.asMap(String.class, String.class);
        csrFindCustomersPage.enterSearchQuery(fieldValueMap);
    }

    @Then("^the search results contains a customer with$")
    public void the_search_results_contains_a_customer_with(@Transpose DataTable searchResultsTable) {
        Map<String, String> searchResultsMap = searchResultsTable.asMap(String.class, String.class);
        assertThat(csrFindCustomersPage.verifySearchResults(searchResultsMap)).isTrue();
    }

}