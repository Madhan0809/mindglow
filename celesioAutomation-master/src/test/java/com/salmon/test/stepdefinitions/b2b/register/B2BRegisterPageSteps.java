package com.salmon.test.stepdefinitions.b2b.register;
import com.salmon.test.models.b2b.B2bAccountData;
import com.salmon.test.models.b2b.RegistrationDetails;
import com.salmon.test.pageobjects.b2b.register.B2BRegisterPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class B2BRegisterPageSteps {
    private static final Logger LOG = LoggerFactory.getLogger(B2BRegisterPageSteps.class);
    public  B2BRegisterPage b2BRegisterPage;
    private B2bAccountData accountData;

    public B2BRegisterPageSteps(B2BRegisterPage registerPage, B2bAccountData accountData) {
        this.accountData = accountData;
        this.b2BRegisterPage = registerPage;
    }
    @When("^User clicks on \"([^\"]*)\" button$")
    public void User_clicks_on_button(String registerButton) throws Throwable {
        b2BRegisterPage.clickButtonFromSignInPage(registerButton);
    }


    @And("^User click on \"([^\"]*)\" link$")
    public void User_click_on_link(String registrationLink) throws Throwable {
      b2BRegisterPage.clickOnRegistrationLink(registrationLink);
    }

    @And("^The register page should be displayed$")
    public void The_register_page_should_be_displayed() throws Throwable {
      assertThat(b2BRegisterPage.canAccessRegisterPage()).isTrue();
    }

    @When("^The user provides all the relevant details on the registration page$")
    public void The_user_provides_all_the_relevant_details_on_the_registration_page(@Transpose List<RegistrationDetails> userDetails) throws Throwable {
        RegistrationDetails registration = b2BRegisterPage.enterDetailsOnTheRegistrationPage(userDetails.get(0));
        accountData.setRegistration(registration);
    }
    
    @When("^The user provides all customized register info on the registration page$")
    public void the_user_provides_all_customized_register_info_on_the_registration_page(@Transpose List<RegistrationDetails> userDetails) throws Throwable{
        b2BRegisterPage.enterUserRegisteredInfo(userDetails.get(0));
    }

    @And("^The user clicks on \"([^\"]*)\" button on registration page$")
    public void The_user_clicks_on_button_on_registration_page(String submitRequest) throws Throwable {
         b2BRegisterPage.clickOnSubmitRequest();
      }

    @Then("^The user should get \"([^\"]*)\" message on Register confirmation page$")
    public void The_user_should_get_message_on_Register_confirmation_page(String requestSubmitMsg) throws Throwable {
      assertThat(b2BRegisterPage.verifyRegistrationConfirmation(requestSubmitMsg)).isTrue();
    }

    @Then("^All the relevant labels should be displayed$")
    public void All_the_relevant_labels_should_be_displayed(List<String> labels) throws Throwable {
        assertThat(b2BRegisterPage.getTextLabels()).isEqualTo(labels);
    }

    @Then("^The user should see error messages for all the mandatory fields$")
    public void The_user_should_see_error_messages_for_all_the_mandatory_fields(List<String> errorMsgs) throws Throwable {
        assertThat(b2BRegisterPage.getAllErrorMessages()).isEqualTo(errorMsgs);

    }

    @Then("^Appropriate error message \"([^\"]*)\" should be displayed on page$")
    public void Appropriate_error_message_should_be_displayed_on_page(String errorMsg) throws Throwable {
        assertThat(b2BRegisterPage.getAppropriateErrorMessage())
                .isEqualToIgnoringCase(b2BRegisterPage.getB2bProp(errorMsg));

    }

    @Given("^The user is on AAH Point page$")
    public void The_user_is_on_AAH_Point_page() throws Throwable {
        b2BRegisterPage.gotoAAHPointPage();
    }

    @When("^User clicks on \"([^\"]*)\" link on right section$")
    public void User_clicks_on_link_on_right_section(String accessWebsitesLink) throws Throwable {
        b2BRegisterPage.clickOnAAHPointLinks(accessWebsitesLink);
    }

    @When("^User clicks on \"([^\"]*)\" button on right section$")
    public void User_clicks_on_button_on_right_section(String accessWebsitesLink) throws Throwable {
        b2BRegisterPage.clickOnAAHPointLinks(accessWebsitesLink);
    }

    @Then("^User navigate to \"([^\"]*)\" page$")
    public void User_navigate_to_page(String signInPage) throws Throwable {
        assertThat(b2BRegisterPage.verifySignInPage(signInPage)).isTrue();
    }

    @Then("^User Navigate to \"([^\"]*)\" page$")
    public void User_Navigate_to_page(String pageTitle) throws Throwable {
        assertThat(b2BRegisterPage.verifyRegistrationLandingPage(pageTitle)).isTrue();
    }


    @When("^User click on \"([^\"]*)\" link on right section$")
    public void User_click_on_link_on_right_section(String registerLink) throws Throwable {
        b2BRegisterPage.clickOnAAHPointLinks(registerLink);
    }

    @Then("^User Navigate to Site Access page$")
    public void User_Navigate_to_Site_Access_page() throws Throwable {
         assertThat(b2BRegisterPage.verifySiteAccessPage()).isTrue();
         }

    @When("^User clicks on \"([^\"]*)\" site button$")
    public void User_clicks_on_site_button(String arg1) throws Throwable {
        LOG.info("\n : ---Shop Complete OTC--- : ");
        b2BRegisterPage.selectOTCOnlineButton();
        }
}
