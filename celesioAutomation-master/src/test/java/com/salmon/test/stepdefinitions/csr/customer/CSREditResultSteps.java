package com.salmon.test.stepdefinitions.csr.customer;

import com.salmon.test.pageobjects.csr.customer.CSREditResultPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class CSREditResultSteps {

    private CSREditResultPage csrEditResultPage;

    public CSREditResultSteps(CSREditResultPage csrEditResultPage) {
        this.csrEditResultPage = csrEditResultPage;
    }

    @And("^I click  on \"([^\"]*)\" tab for the selected result$")
    public void I_click_on_tab_for_the_selected_result(String tabName) throws Throwable {
    csrEditResultPage.getResultTab(tabName).click();
    }

    @When("^I submit the edited details$")
    public void I_submit_the_edited_details() throws Throwable {
        csrEditResultPage.submitButton().submit();
    }
}
