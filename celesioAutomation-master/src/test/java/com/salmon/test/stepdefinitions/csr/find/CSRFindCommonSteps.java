package com.salmon.test.stepdefinitions.csr.find;

import com.salmon.test.pageobjects.csr.find.CSRFindCommonPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindCommonSteps {
    private CSRFindCommonPage csrFindCommonPage;

    public CSRFindCommonSteps(CSRFindCommonPage csrFindCommonPage) {
        this.csrFindCommonPage = csrFindCommonPage;
    }

    @And("^I clear the search results$")
    public void I_clear_the_search_results() throws Throwable {
        csrFindCommonPage.clearSearchResultsTable();
    }

    @Then("^the results are cleared and \"([^\"]*)\" message is displayed$")
    public void the_results_are_cleared_and_message_is_displayed(String noCustomerMessage) throws Throwable {
        assertThat(csrFindCommonPage.getCsrProp(noCustomerMessage)).isEqualToIgnoringCase(csrFindCommonPage.checkSearchResults());
    }

    @Then("^the \"([^\"]*)\" header is displayed for the search screen$")
    public void the_header_is_displayed_for_the_search_screen(String findFormHeader) throws Throwable {
        assertThat(csrFindCommonPage.getFindFormHeader()).isEqualTo(csrFindCommonPage.getCsrProp(findFormHeader));
    }


    @Then("^\"([^\"]*)\" matching \"([^\"]*)\" label is displayed$")
    public void matching_label_is_displayed(String singleORMultiple, String matchLabel) throws Throwable {

        if (singleORMultiple.equals("SINGLE")) {
            assertThat(csrFindCommonPage.getMatchCount().equals(1));
        } else if (singleORMultiple.equals("MULTIPLE")) {
            assertThat(csrFindCommonPage.getMatchCount() > 1);
        }

        assertThat(csrFindCommonPage.matchLabel().contains(matchLabel));
    }


    @And("^the results page is paginated by (\\d+) results per page$")
    public void the_results_page_is_paginated_by_results_per_page(Double paginateBy) throws Throwable {
        assertThat(csrFindCommonPage.noOfPageElements().size()).isEqualTo(csrFindCommonPage.calculateExpectedPageCount(paginateBy));
        assertThat(csrFindCommonPage.isPageActiveForNextPageNavigation()).isTrue();
        assertThat(csrFindCommonPage.isPageActiveForPreviousPageNavigation()).isTrue();
    }

    @Then("^the form validation message \"([^\"]*)\" is displayed$")
    public void the_form_validation_message_is_displayed(String noCustomerMessage) throws Throwable {
        assertThat(csrFindCommonPage.checkValidations().stream().map(WebElement::getText).filter(noCustomerMessage::equals).findFirst().isPresent());
    }
}