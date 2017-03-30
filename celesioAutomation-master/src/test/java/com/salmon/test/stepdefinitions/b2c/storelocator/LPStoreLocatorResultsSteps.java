package com.salmon.test.stepdefinitions.b2c.storelocator;

import com.salmon.test.pageobjects.b2c.storelocator.LPStoreLocatorResultsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.assertj.core.api.Assertions.*;

public class LPStoreLocatorResultsSteps {

    private LPStoreLocatorResultsPage lpStoreLocatorResultsPage;

    public LPStoreLocatorResultsSteps(LPStoreLocatorResultsPage lpStoreLocatorResultsPage) {
        this.lpStoreLocatorResultsPage = lpStoreLocatorResultsPage;
    }

    @Given("^The user is on Store locator results page$")
    public void the_user_is_on_Store_locator_results_page() throws Throwable {
       lpStoreLocatorResultsPage.goToStoreLocatorResultsPage();
    }

    @Then("^Store Results Breadcrumb \"(.*?)\" should be displayed$")
    public void store_Results_Breadcrumb_should_be_displayed(String breadCrumb) throws Throwable {
        assertThat(lpStoreLocatorResultsPage.getRelevantBreadCrumb()).isEqualTo(breadCrumb);
    }

    @Then("^\"(.*?)\" location is displayed$")
    public void location_is_displayed(String location) throws Throwable {
        assertThat(lpStoreLocatorResultsPage.getRelevantStoreLocationDisplayed()).isEqualTo(location);
    }

    @Then("^Appropriate store locator map is displayed$")
    public void appropriate_store_locator_map_is_displayed() throws Throwable {
        assertThat(lpStoreLocatorResultsPage.isAppropriateMapDisplayed()).isTrue();
    }

    @Then("^Relevant store locator results are displayed$")
    public void relevant_store_locator_results_are_displayed() throws Throwable {
        assertThat(lpStoreLocatorResultsPage.isRelevantStoreLocatorResultsDisplayed()).isTrue();
    }

    @Then("^The message \"(.*?)\" should be displayed$")
    public void the_message_should_be_displayed(String notFoundMsg) throws Throwable {
        assertThat(lpStoreLocatorResultsPage.getNoStoresErrorMsg()).isEqualToIgnoringCase(notFoundMsg);
    }

    @When("^The user clicks on view store details link$")
    public void the_user_clicks_on_view_store_details_link() throws Throwable {
        lpStoreLocatorResultsPage.OnClickOfViewDetailsLink();
    }

    @When("^The user enters \"(.*?)\" in the stores results search box$")
    public void the_user_enters_in_the_stores_results_search_box(String townOrPostCode) throws Throwable {
        lpStoreLocatorResultsPage.populateTownOrPostCode(townOrPostCode);
    }

    @Then("^The user should be navigated to \"([^\"]*)\" store details page$")
    public void The_user_should_be_navigated_to_store_details_page(String store) throws Throwable {
        assertThat(lpStoreLocatorResultsPage.getRelevantDetailsPage()).isEqualTo(store);
    }
}
