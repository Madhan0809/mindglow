package com.salmon.test.stepdefinitions.b2b.homepage;
import com.salmon.test.pageobjects.b2b.home.B2BHomePageHeader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BHomePageHeaderSteps {
    private B2BHomePageHeader aAHHomePageHeader;

    public B2BHomePageHeaderSteps(B2BHomePageHeader aAHHomePageHeader) {
        this.aAHHomePageHeader = aAHHomePageHeader;
    }

    @Given("^The user is on AAH home page as a guest$")
    public void The_user_is_on_AAH_home_page_as_a_guest() throws Throwable {
        aAHHomePageHeader.gotoHomePage();
    }

    @When("^The user clicks on AAHEnterprise icon$")
    public void The_user_clicks_on_AAHEnterprise_icon() throws Throwable {
        aAHHomePageHeader.clickOnHomePageAAHIcon();
    }

    @Then("^The user shall be navigated to the AAH home page$")
    public void The_user_shall_be_navigated_to_the_AAH_home_page() throws Throwable {
        assertThat(aAHHomePageHeader.verifyHomePage()).isTrue();
    }

    @Then("^user has access to select only one account$")
    public void user_has_access_to_select_only_one_account() throws Throwable {
        aAHHomePageHeader.selectAccountDropDownValue(0);
    }
    
    @When("^The user inputs text as \"([^\"]*)\" in search box$")
    public void The_user_inputs_text_as_in_search_box(String searchString) throws Throwable {
        aAHHomePageHeader.inputOfSearchString(searchString);
    }

    @Then("^the service returned (\\d+) auto suggest product results list$")
    public void the_service_returned_auto_suggest_product_results_list(int noOfProducts) throws Throwable {
        assertThat(aAHHomePageHeader.getAutoSuggestProducts().size()).isEqualTo(noOfProducts);
    }

    @Given("^The user is on AAH  Sign-in page as a guest$")
    public void The_user_is_on_AAH_Sign_in_page_as_a_guest() throws Throwable {
        aAHHomePageHeader.gotoHomePage();
    }

    @And("^The user is on AAH home page as a user$")
    public void The_user_is_on_AAH_home_page_as_a_user() throws Throwable {
        assertThat(aAHHomePageHeader.verifyUserOnHomePage()).isTrue();
    }

    @And("^The user provide default username and password$")
    public void The_user_provide_username_and_password() throws Throwable {
        aAHHomePageHeader.provideDefaultLoginDetails();
    }

    @And("^The user provide multi account username and password$")
    public void The_user_provide_multi_account_username_and_password() throws Throwable {
        aAHHomePageHeader.provideMultiAccountLoginDetails();
    }
    
    @And("^User clicks on Sign In button$")
    public void User_clicks_on_Sign_In_button() throws Throwable {
        aAHHomePageHeader.clickOnSignInButton();
    }

    @And("^The user clicks on Signout$")
    public void The_user_clicks_on_Signout() throws Throwable {
        aAHHomePageHeader.clickOnSignOutButton();
    }

    @And("^clicks on \"([^\"]*)\" button$")
    public void clicks_on_button(String shopComplete) throws Throwable {
        aAHHomePageHeader.clickOnShopCompleteButton(shopComplete);
      }

    @Then("^User can choose \"([^\"]*)\" account from accounts dropdown$")
    public void User_can_choose_account_from_accounts_dropdown(String selectedAccount) throws Throwable {
        if(selectedAccount.equalsIgnoreCase("first")){
            aAHHomePageHeader.selectAccountDropDownValue(new Integer(1));
        } else if(selectedAccount.equalsIgnoreCase("second")){
            aAHHomePageHeader.selectAccountDropDownValue(new Integer(2));
        }
    }
    
    @When("^User switch the location as \"(.*?)\" from header$")
    public void user_switch_the_location_from_header(String location) throws Throwable{
        if(location.startsWith("b2b.")){
            location=aAHHomePageHeader.getProp(location);
        }
        aAHHomePageHeader.selectFromLocationDropdown(location);
    }
}
