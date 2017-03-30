package com.salmon.test.stepdefinitions.csr.customer;

import com.salmon.test.pageobjects.csr.customer.CSRCustomerTicklerPage;
import cucumber.api.java.en.When;

public class CSRCustomerTicklerSteps {

    private CSRCustomerTicklerPage csrCustomerTicklerPage;

    public CSRCustomerTicklerSteps(CSRCustomerTicklerPage csrCustomerTicklerPage) {
        this.csrCustomerTicklerPage = csrCustomerTicklerPage;
    }

    @When("^I Add new customer tickler from  customer Details$")
    public void I_Add_new_customer_tickler_from_customer_Details() throws Throwable {
        csrCustomerTicklerPage.addNewTicklerButton().click();
    }
}
