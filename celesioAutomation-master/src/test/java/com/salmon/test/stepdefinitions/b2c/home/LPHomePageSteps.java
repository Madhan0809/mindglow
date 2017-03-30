package com.salmon.test.stepdefinitions.b2c.home;
import com.salmon.test.pageobjects.b2c.home.LPHomePage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LPHomePageSteps {
    private LPHomePage lpHomePage;

    public LPHomePageSteps(LPHomePage lpHomePage) {
        this.lpHomePage = lpHomePage;
    }

    @Given("^The user is on home page as a guest$")
    public void the_user_is_on_home_page_as_a_guest() throws Throwable {
        lpHomePage.gotoHomePage();
    }

    @Given("^I navigate to My Accounts Page$")
    public void I_navigate_to_My_Accounts_Page() throws Throwable {
        lpHomePage.clickOnMyAccountLink();
    }

    @When("^The user clicks on Home icon$")
    public void the_user_clicks_on_Home_icon() throws Throwable {
        lpHomePage.clickOnLloydsPharmacyHomeLink();
    }

    @Then("^The user shall be navigated to the home page$")
    public void the_user_shall_be_navigated_to_the_home_page() throws Throwable {
        assertThat(lpHomePage.verifyHomePage()).isTrue();
    }

    @When("^The user clicks on Store locator icon$")
    public void the_user_clicks_on_Store_locator_icon() throws Throwable {
        lpHomePage.clickOnStoreLocatorLink();
    }

    @Then("^The user shall be navigated to store locator page$")
    public void the_user_shall_be_navigated_to_store_locator_page() throws Throwable {
        assertThat(lpHomePage.isRelevantStoreLocatorPage()).isTrue();
    }

    @When("^The user clicks on Sign-in icon$")
    public void the_user_clicks_on_Sign_in_icon() throws Throwable {
        lpHomePage.clickOnSignInLink();
    }

    @Then("^sign-in/register panel will be displayed$")
    public void sign_in_register_panel_will_be_displayed() throws Throwable {
        assertThat(lpHomePage.verifyTheResultantTabOnSignInClick()).isTrue();
    }

    @When("^The user clicks on Sign into Online Doctor icon$")
    public void the_user_clicks_on_Sign_into_Online_Doctor_icon() throws Throwable {
        lpHomePage.clickOnOnlineDoctorLink();
    }

    @Then("^The user shall be navigated to the Online Doctor website with the title \"(.*?)\"\\.$")
    public void the_user_shall_be_navigated_to_the_Online_Doctor_website_with_the_title(String onlineDoctorPage) throws Throwable {
        assertThat(lpHomePage.verifyTheResultantTabOnHeaderClick(onlineDoctorPage)).isEqualTo(onlineDoctorPage);
    }

    @When("^The user clicks on View our Blog icon$")
    public void the_user_clicks_on_View_our_Blog_icon() throws Throwable {
        lpHomePage.clickOnBlogLink();
    }

    @Then("^The user shall be navigated to the blog page with the title \"(.*?)\"\\.$")
    public void the_user_shall_be_navigated_to_the_blog_page_with_the_title(String blogPage) throws Throwable {
        assertThat(lpHomePage.verifyTheResultantTabOnHeaderClick(blogPage)).containsIgnoringCase(blogPage);
    }

    @When("^The user clicks on Contact us icon$")
    public void the_user_clicks_on_Contact_us_icon() throws Throwable {
        lpHomePage.clickOnContactLink();
    }

    @Then("^The user shall be navigated to the contact us page with the title \"(.*?)\"\\.$")
    public void the_user_shall_be_navigated_to_the_contact_us_page_with_the_title(String contactUsPage) throws Throwable {
        assertThat(lpHomePage.getCurrentPageTitle()).isEqualTo(contactUsPage);
    }

    @When("^The user clicks on Brand logo image$")
    public void the_user_clicks_on_Brand_logo_image() throws Throwable {
        lpHomePage.clickOnBrandLogoLink();
    }

    @Then("^The user shall be navigated to the home page with the title \"(.*?)\"$")
    public void the_user_shall_be_navigated_to_the_home_page_with_the_title(String homePage) throws Throwable {
        assertThat(lpHomePage.getCurrentPageTitle()).isEqualTo(homePage);
    }


    @When("^The user click on Sign-out text link$")
    public void the_user_click_on_Sign_out_text_link() throws Throwable {
        lpHomePage.clickOnSignOutLink();
    }

    @Then("^The user should be logged out of site$")
    public void the_user_should_be_logged_out_of_site() throws Throwable {
        assertThat(lpHomePage.isUserSignedIn()).isTrue();
        assertThat(lpHomePage.isUserSignedOut()).isFalse();
    }

    @When("^The user click on browser back button$")
    public void The_user_click_on_browser_back_button() throws Throwable {
        lpHomePage.getWebDriver().navigate().back();
    }
}
