package com.salmon.test.stepdefinitions.csr.customer;

import com.salmon.test.pageobjects.csr.customer.CSREditResultPage;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;

import java.util.Map;

public class CSREditCustomerDetailSteps {

    private CSREditResultPage csrEditResultPage;

    public CSREditCustomerDetailSteps(CSREditResultPage csrEditResultPage) {
        this.csrEditResultPage = csrEditResultPage;
    }

    @Then("^I can EDIT the customer Details$")
    public void I_can_EDIT_the_customer_Details(DataTable fieldValueTable) throws Throwable {
        Map<String, String> fieldValueMap = fieldValueTable.asMap(String.class, String.class);
        for (String fieldName : fieldValueMap.keySet()) {
            if ((fieldName.contains("title")) || (fieldName.contains("country"))) {
                csrEditResultPage.selectElementByFieldName(fieldName).selectByVisibleText(csrEditResultPage.getCsrProp(fieldValueMap.get(fieldName)));
            } else {
                csrEditResultPage.getElementByFieldName(fieldName).clear();
                csrEditResultPage.getElementByFieldName(fieldName).sendKeys(csrEditResultPage.getCsrProp(fieldValueMap.get(fieldName)));
            }
        }
    }

    @Then("^the customer details are saved successfully$")
    public void the_customer_details_are_saved_successfully() throws Throwable {

    }
}
