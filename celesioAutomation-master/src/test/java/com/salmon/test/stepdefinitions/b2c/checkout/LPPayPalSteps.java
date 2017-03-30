package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.pageobjects.b2c.checkout.LPPayPalPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LPPayPalSteps {
    private LPPayPalPage lpPayPalPage;

    public LPPayPalSteps(LPPayPalPage lpPayPalPage) throws Throwable {
        this.lpPayPalPage = lpPayPalPage;
    }

    @When("^I enter payPalUsername \"([^\"]*)\" and payPalPassword \"([^\"]*)\"$")
    public void I_enter_payPalUsername_and_payPalPassword(String payPalUsernameValue, String payPalPasswordValue) throws Throwable {
        lpPayPalPage.enterPayPalLoginDetails(payPalUsernameValue, payPalPasswordValue);
    }

    @And("^i click on continue with PayPal Balance$")
    public void i_click_on_continue_with_PayPal_Balance() throws Throwable {
        lpPayPalPage.continueWithPayPalBalance();

    }

    @Then("^the delivery address is firstName \"([^\"]*)\" lastName \"([^\"]*)\" and addressType \"([^\"]*)\" on paypal review screen$")
    public void the_delivery_address_is_firstName_lastName_and_addressType_on_paypal_review_screen(String firstName, String lastName, String addressType) throws Throwable {
        String deliveryAddress = lpPayPalPage.getB2cProp("address.saved." + addressType);
        assertThat(lpPayPalPage.getPayPalNameInfo()).isEqualTo(firstName + " " + lastName);
        assertThat(lpPayPalPage.getPayPalDeliveryAddress()).containsIgnoringCase(deliveryAddress);
    }
}
