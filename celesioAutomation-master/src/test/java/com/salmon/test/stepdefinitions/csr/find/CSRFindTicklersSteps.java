package com.salmon.test.stepdefinitions.csr.find;

import com.salmon.test.pageobjects.csr.find.CSRFindTicklersPage;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindTicklersSteps {
    private CSRFindTicklersPage CSRFindTicklersPage;

    public CSRFindTicklersSteps(CSRFindTicklersPage CSRFindTicklersPage) {
        this.CSRFindTicklersPage = CSRFindTicklersPage;
    }

    @When("^I enter the search criteria to find ticklers")
    public void I_enter_the_search_criteria_to_find_ticklers(@Transpose DataTable fieldValueTable) throws Throwable {
        CSRFindTicklersPage.clearSearchFields();

        Map<String, String> fieldValueMap = fieldValueTable.asMap(String.class, String.class);
        CSRFindTicklersPage.enterSearchQuery(fieldValueMap);
    }

    @Then("^the search results contains a tickler with$")
    public void the_search_results_contains_a_tickler_with(@Transpose DataTable searchResultsTable) {
        Map<String, String> searchResultsMap = searchResultsTable.asMap(String.class, String.class);
        assertThat(CSRFindTicklersPage.verifySearchResults(searchResultsMap)).isTrue();
    }

}
