package com.salmon.test.stepdefinitions.b2c.storelocator;


import com.salmon.test.pageobjects.b2c.storelocator.LPStoreLocatorDetailsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPStoreLocatorDetailsSteps {

    private LPStoreLocatorDetailsPage lpStoreLocatorDetailsPage;

    public LPStoreLocatorDetailsSteps(LPStoreLocatorDetailsPage lpStoreLocatorDetailsPage) {
        this.lpStoreLocatorDetailsPage = lpStoreLocatorDetailsPage;
    }

    @Given("^The user is on Store locator details page$")
    public void the_user_is_on_Store_locator_details_page() throws Throwable {
        lpStoreLocatorDetailsPage.goToStoreLocatorDetailsPage();
    }

    @Then("^Appropriate store details map is displayed$")
    public void appropriate_store_details_map_is_displayed() throws Throwable {
        assertThat(lpStoreLocatorDetailsPage.isAppropriateMapDisplayed()).isTrue();
    }

    @Then("^Appropriate Store Labels are displayed$")
    public void appropriate_Store_Labels_are_displayed(List<String> labels) throws Throwable {
        assertThat(lpStoreLocatorDetailsPage.getStoreDetailServices("Labels")).isEqualTo(labels);
    }

    @Then("^Relevant store locator details are displayed$")
    public void relevant_store_locator_details_are_displayed() throws Throwable {
        assertThat(lpStoreLocatorDetailsPage.getStoreLocatorDetails()).isEqualTo(3);
    }

    @Then("^Appropriate services are displayed$")
    public void appropriate_services_are_displayed(List<String> services) throws Throwable {
        assertThat(lpStoreLocatorDetailsPage.getStoreDetailServices("Details")).hasSameElementsAs(services);
    }

    @Then("^Store details Breadcrumb \"(.*?)\" should be displayed$")
    public void store_details_Breadcrumb_should_be_displayed(String breadCrumb) throws Throwable {
        assertThat(lpStoreLocatorDetailsPage.getRelevantStoreDetailsBreadCrumb()).isEqualTo(breadCrumb);
    }
}
