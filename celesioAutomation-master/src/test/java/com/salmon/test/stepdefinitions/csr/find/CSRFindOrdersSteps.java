package com.salmon.test.stepdefinitions.csr.find;

import com.salmon.test.pageobjects.csr.find.CSRFindOrdersPage;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindOrdersSteps {
    private CSRFindOrdersPage csrFindOrdersPage;

    public CSRFindOrdersSteps(CSRFindOrdersPage csrFindOrdersPage) {
        this.csrFindOrdersPage = csrFindOrdersPage;
    }

    @When("^I enter the search criteria to find orders")
    public void I_enter_the_search_criteria_to_find_orders(@Transpose DataTable fieldValueTable) throws Throwable {
        csrFindOrdersPage.clearSearchFields();

        Map<String, String> fieldValueMap = fieldValueTable.asMap(String.class, String.class);
        csrFindOrdersPage.enterSearchQuery(fieldValueMap);
    }

    @Then("^the search results contains a order with$")
    public void the_search_results_contains_a_order_with(@Transpose DataTable searchResultsTable) {
        Map<String, String> searchResultsMap = searchResultsTable.asMap(String.class, String.class);
        assertThat(csrFindOrdersPage.verifySearchResults(searchResultsMap)).isTrue();
    }

}
