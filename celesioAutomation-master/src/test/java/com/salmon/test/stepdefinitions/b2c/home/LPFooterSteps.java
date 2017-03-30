package com.salmon.test.stepdefinitions.b2c.home;
import com.salmon.test.pageobjects.b2c.home.LPFooterPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPFooterSteps {
    private LPFooterPage lpFooterPage;

    public LPFooterSteps(LPFooterPage lpFooterPage) {
        this.lpFooterPage = lpFooterPage;
    }

    @Then("^The \"(.*?)\" subscribe_part is displayed$")
    public void the_subscribe_part_is_displayed(String subscribePart) throws Throwable {
        assertThat(lpFooterPage.getSubscribePart(subscribePart)).isEqualTo(subscribePart);
    }

    @Then("^The footer menu headers are displayed$")
    public void the_footer_menu_headers_are_displayed(List<String> footerHeaderList) throws Throwable {
        assertThat(lpFooterPage.getFooterHeaderList()).isEqualTo(footerHeaderList);
    }

    @Then("^The footer menu links are displayed$")
    public void the_footer_menu_links_are_displayed(List<String> footerMenuList) throws Throwable {
        assertThat(lpFooterPage.getFooterMenuList()).isEqualTo(footerMenuList);
    }

    @Then("^The social media images are displayed$")
    public void the_social_media_images_are_displayed(List<String> socialMediaList) throws Throwable {
        assertThat(lpFooterPage.getSocialMediaList()).isEqualTo(socialMediaList);
    }

    @When("^The user clicks on \"(.*?)\" subscribe_part$")
    public void the_user_clicks_on_subscribe_part(String subscribePart) throws Throwable {
        lpFooterPage.onClickOfsubscribePart(subscribePart);
    }

    @When("^The user enters an email address \"(.*?)\"$")
    public void the_user_enters_an_email_address(String emailAddress) throws Throwable {
        lpFooterPage.inputOfemailAddress(emailAddress);
    }

    @Then("^The type of Email Address is \"(.*?)\"$")
    public void the_type_of_Email_Address_is(String type) throws Throwable {
        assertThat(lpFooterPage.getTypeofEmail()).isEqualTo(type);
    }
    
    @Then("^The Registered pharmacy logo is displayed$")
    public void the_registered_pharmacy_logo_is_displayed() throws Throwable{
        assertThat(lpFooterPage.isPharmacyLogoDisplayed()).isTrue();
    }
    
    @Then("^Switch to new window and check if \"(.*?)\" page is displayed$")
    public void switch_to_new_window_and_check_pharmacy_page_is_displayed(String pageTitle) throws Throwable{
        assertThat(lpFooterPage.isRedirectedToPharmacyPage(pageTitle)).isTrue();
    }
}
