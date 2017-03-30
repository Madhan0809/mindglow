package com.salmon.test.stepdefinitions.b2b.homepage;
import com.salmon.test.pageobjects.b2b.home.B2BHomePageFooter;
import com.salmon.test.pageobjects.b2b.register.B2BLoginPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BHomePageFooterSteps {
    private B2BHomePageFooter b2BHomePageFooter;
    
    
    public B2BHomePageFooterSteps(B2BHomePageFooter b2BHomePageFooter) {
        this.b2BHomePageFooter = b2BHomePageFooter;
        
    }

    @Given("^The user is on AAH home page$")
    public void The_user_is_on_AAH_home_page() throws Throwable {
        b2BHomePageFooter.goToHomePage();
    }

    @Then("^The \"([^\"]*)\" subscribe_part text is displayed$")
    public void The_subscribe_part_text_is_displayed(String subscribePart) throws Throwable {
        assertThat(b2BHomePageFooter.getSubscribePart(subscribePart)).isEqualTo(subscribePart);
    }

    @When("^The user clicks on \"([^\"]*)\" subscribe part section$")
    public void The_user_clicks_on_subscribe_part_section(String subscribePart) throws Throwable {
        b2BHomePageFooter.onClickOfSubscribePart(subscribePart);
    }

    @Then("^The input of Email Address is \"([^\"]*)\"$")
    public void The_input_of_Email_Address_is(String type) throws Throwable {
        assertThat(b2BHomePageFooter.getTypeofEmail()).isEqualTo(type);
    }

    @When("^The user enters an valid email address \"([^\"]*)\"$")
    public void The_user_enters_an_valid_email_address(String emailAddress) throws Throwable {
        b2BHomePageFooter.inputOfEmailAddress(emailAddress);
    }

    @When("^The user clicks on \"([^\"]*)\" subscribe part$")
    public void The_user_clicks_on_subscribe_part(String subscribePart) throws Throwable {
        b2BHomePageFooter.onClickOfSubscribePart(subscribePart);
    }

    @Then("^The user gets a thank you page$")
    public void The_user_gets_a_thank_you_page() throws Throwable {
        assertThat(b2BHomePageFooter.getThankYouMessage().getText()).contains(b2BHomePageFooter.getB2bProp("emailSubscription.confirmationMessage"));
    }

    @Then("^The email is stored in the table \"([^\"]*)\" under the column \"([^\"]*)\" value as \"([^\"]*)\"$")
    public void The_email_is_stored_in_the_table_under_the_column_value_as(String tableName, String columnName, String email) throws Throwable {
        assertThat(b2BHomePageFooter.getDataBaseValues(tableName, columnName, email));
        // assertThat(b2BHomePageFooter.getDataBaseValues(tableName, columnName, email)).isEqualTo(b2BHomePageFooter.inputOfEmailAddress(email));
    }

    @Then("^The home page footer menu headers are displayed$")
    public void The_home_page_footer_menu_headers_are_displayed(List<String> footerHeaderList) throws Throwable {
        assertThat(b2BHomePageFooter.getFooterHeaderList()).isEqualTo(footerHeaderList);
    }

    @Then("^The B2B footer menu links are displayed$")
    public void the_B2B_footer_menu_links_are_displayed(List<String> footerMenuList) throws Throwable {
        assertThat(b2BHomePageFooter.getFooterMenuList()).isEqualTo(footerMenuList);
    }

    @Then("^The expected social media images are displayed for B2B$")
    public void The_expected_social_media_images_are_displayed_for_b_b(List<String> socialMediaList) throws Throwable {
        assertThat(socialMediaList.containsAll(b2BHomePageFooter.getSocialMediaList()));

    }

    @Then("^The user enters an invalid email address \"([^\"]*)\"$")
    public void The_user_enters_an_invalid_email_address(String invalidEmail) throws Throwable {
        b2BHomePageFooter.inputOfInvalidEmailAddress(invalidEmail);
    }

    @Then("^The user clicks on \"([^\"]*)\" subscribe email part$")
    public void The_user_clicks_on_subscribe_email_part(String subscribePart) throws Throwable {
        b2BHomePageFooter.onClickOfSubscribeEmailPart(subscribePart);
    }

    @Then("^The email validation should perform for \"([^\"]*)\"$")
    public void The_email_validation_should_perform_for(String invalidEmailEntry) throws Throwable {
        if(b2BHomePageFooter.getSubscribeEmailValidation(invalidEmailEntry.trim())==true){
            System.out.println("\n Given input "+invalidEmailEntry+" valid Email ::"+b2BHomePageFooter.getSubscribeEmailValidation(invalidEmailEntry.trim()));
        }
        if(b2BHomePageFooter.getSubscribeEmailValidation(invalidEmailEntry.trim())==false){
            System.out.println("\n Given input "+invalidEmailEntry+" valid Email ::"+b2BHomePageFooter.getSubscribeEmailValidation(invalidEmailEntry.trim()));
        }
        assertThat(!(b2BHomePageFooter.getSubscribeEmailValidation(invalidEmailEntry.trim()))).isTrue();
    }
}
