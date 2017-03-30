package com.salmon.test.stepdefinitions.b2c.register;
import com.salmon.test.models.b2c.AccountData;
import com.salmon.test.models.b2c.Registration;
import com.salmon.test.pageobjects.b2c.register.LPRegistrationPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPRegistrationSteps {
    private LPRegistrationPage lpRegistrationPage;
    private AccountData accountData;

    public LPRegistrationSteps(LPRegistrationPage lpRegistrationPage, AccountData accountData) {
        this.accountData = accountData;
        this.lpRegistrationPage = lpRegistrationPage;
    }

    @When("^The user enters all the relevant details on the registration page$")
    public void the_user_enters_all_the_relevant_details_on_the_registration_page(@Transpose List<Registration> userDetails) throws Throwable {
        Registration registration = lpRegistrationPage.enterDetailsOnTheRegistrationPage(userDetails.get(0));
        accountData.setRegistration(registration);
    }

    @When("^The user enters postcode \"([^\"]*)\" on registration page and clicks postcode finder$")
    public void The_user_enters_postcode_on_registration_page_and_clicks_postcode_finder(String postcodeKey) throws Throwable {
        lpRegistrationPage.enterPostCode(postcodeKey);
    }

    @Then("^The user should be navigated to \"([^\"]*)\" details page$")
    public void The_user_should_be_navigated_to_details_page(String myAccount) throws Throwable {
        if(lpRegistrationPage.getWebDriver().getTitle().contains("Problem loading page")){
            lpRegistrationPage.getWebDriver().navigate().refresh();
        }
        assertThat(lpRegistrationPage.checkPageTitleContains(myAccount));
    }

    @Then("^The user should see error messages for all the mandatory fields on the page$")
    public void The_user_should_see_error_messages_for_all_the_mandatory_fields_on_the_page(List<String> errorMsgs) throws Throwable {
        assertThat(lpRegistrationPage.getAllErrorMessages()).isEqualTo(errorMsgs);
    }

    @And("^The user clicks on \"([^\"]*)\" on registration page$")
    public void The_user_clicks_on_on_registration_page(String button) throws Throwable {
        lpRegistrationPage.onClickOfAButton(button);
    }


    @Then("^All the relevant text labels should be displayed$")
    public void All_the_relevant_text_labels_should_be_displayed(List<String> labels) throws Throwable {
        assertThat(lpRegistrationPage.getTextLabels()).isEqualTo(labels);
    }

    @Then("^Appropriate error message \"([^\"]*)\" should be displayed$")
    public void Appropriate_error_message_should_be_displayed(String errorMsg) throws Throwable {
        assertThat(lpRegistrationPage.getAppropriateErrorMessage())
                .isEqualToIgnoringCase(lpRegistrationPage.getB2cProp(errorMsg));
    }

    @Then("^Appropriate fields for entering address should be \"([^\"]*)\"$")
    public void Appropriate_fields_for_entering_address_should_be(String visibility) throws Throwable {
        if ("shown".equalsIgnoreCase(visibility)) {
            assertThat(lpRegistrationPage.isManualAddressVisible()).isTrue();
        } else if ("hidden".equalsIgnoreCase(visibility)) {
            assertThat(lpRegistrationPage.isManualAddressVisible()).isFalse();
        }
    }
}
