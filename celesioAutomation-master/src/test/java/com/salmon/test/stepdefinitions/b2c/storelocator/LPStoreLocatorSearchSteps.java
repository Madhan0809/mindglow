package com.salmon.test.stepdefinitions.b2c.storelocator;
import com.salmon.test.pageobjects.b2c.storelocator.LPStoreLocatorSearchPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPStoreLocatorSearchSteps {
    private LPStoreLocatorSearchPage lpStoreLocatorSearchPage;

    public LPStoreLocatorSearchSteps(LPStoreLocatorSearchPage lpStoreLocatorSearchPage) {
        this.lpStoreLocatorSearchPage = lpStoreLocatorSearchPage;
    }

    @Given("^The user is on Store locator search page$")
    public void the_user_is_on_Store_locator_search_page() throws Throwable {
        lpStoreLocatorSearchPage.goToStoreLocatorSearchPage();
    }

    @Then("^Store search breadcrumb \"(.*?)\" should be displayed$")
    public void store_search_breadcrumb_should_be_displayed(String breadCrumb) throws Throwable {
        assertThat(lpStoreLocatorSearchPage.getStoreSearchBreadCrumb()).isEqualTo(breadCrumb);
    }

    @Then("^The \"(.*?)\" services list is displayed$")
    public void the_services_list_is_displayed(String serviceName, List<String> serviceList) throws Throwable {
        assertThat(lpStoreLocatorSearchPage.getServicesList(serviceName)).hasSameSizeAs(serviceList);
    }

    @When("^The user enters a town or post code \"(.*?)\" in the search box$")
    public void the_user_enters_a_town_or_post_code_in_the_search_box(String townOrPost) throws Throwable {
        lpStoreLocatorSearchPage.populateTownOrPostCode(townOrPost);
    }

    @When("^Click on Find Pharmacy$")
    public void click_on_Find_Pharmacy() throws Throwable {
        lpStoreLocatorSearchPage.onClickOfPostFindPharmacy();
    }

    @Then("^The user should be navigated to store results page$")
    public void the_user_should_be_navigated_to_store_results_page() throws Throwable {
        assertThat(lpStoreLocatorSearchPage.getStoreResultsBreadCrumb()).isEqualTo("Results");
    }
}
