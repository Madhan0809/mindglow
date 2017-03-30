package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.models.b2c.AddressForm;
import com.salmon.test.pageobjects.b2c.checkout.LPPaymentPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPPaymentSteps {
    private LPPaymentPage lpPaymentPage;

    public LPPaymentSteps(LPPaymentPage lpPaymentPage) throws Throwable {
        this.lpPaymentPage = lpPaymentPage;
    }

    @When("^I submit order using Paypal Payment Option$")
    public void I_submit_order_using_Paypal_Payment_Option() throws Throwable {
        lpPaymentPage.submitOrderUsingPayPal();
    }

    @Given("^I enter payment card details for card type \"([^\"]*)\"$")
    public void I_enter_payment_card_details_for_card_type(String typeOfCard) throws Throwable {
        lpPaymentPage.enterCardDetails(typeOfCard);
    }

    @When("^I submit order details and confirm terms$")
    public void I_submit_order_details_and_confirm_terms() throws Throwable {
        lpPaymentPage.submitOrderAndAgreeTerms();
    }

    @When("^I \"([^\"]*)\" billing address with name \"([^\"]*)\"$")
    public void I_billing_address_with_name(String addressAction, String addressName) throws Throwable {
        lpPaymentPage.addressAction(addressAction, addressName);
    }

    @Then("^the payment screen contains \"([^\"]*)\" billing address details$")
    public void the_payment_screen_contains_billing_address_details(String addressName) throws Throwable {
        assertThat(lpPaymentPage.verifyBillingAddress(addressName)).containsIgnoringCase(lpPaymentPage.getB2cProp("address.saved." + addressName));
    }

    @When("^I choose \"([^\"]*)\" billing address for guest user on payment details$")
    public void I_choose_billing_address_for_guest_user_on_payment_details(String billingAddressType, @Transpose List<AddressForm> addressForm) throws Throwable {
        lpPaymentPage.chooseAndEnterBillingAddressForGuest(billingAddressType, addressForm.get(0));
    }

    @Given("^I select \"([^\"]*)\" Payment Option$")
    public void I_select_Payment_Option(String paymentOption) throws Throwable {
        lpPaymentPage.selectPaymentOption(paymentOption);

    }

    @And("^I complete secure verification for card type \"([^\"]*)\"$")
    public void I_complete_secure_verification_for_card_type(String cardType) throws Throwable {
        if (cardType.equalsIgnoreCase("visa3d")) {
            lpPaymentPage.complete3DVerification();
        }
    }

    @And("^delivery info is \"([^\"]*)\", charge \"([^\"]*)\"  shipping address as \"([^\"]*)\"$")
    public void delivery_info_is_charge_shipping_address_as(String shippingMethod, String charge, String shippingAddress) throws Throwable {
        assertThat(lpPaymentPage.getShippingType()).containsIgnoringCase(shippingMethod);
        assertThat(lpPaymentPage.getShippingAddress()).containsIgnoringCase(lpPaymentPage.getB2cProp("address.saved." + shippingAddress));
        if(charge.equals("£0")){
            assertThat(lpPaymentPage.getShippingCharge()).containsIgnoringCase(charge);
        }
        else
            assertThat(lpPaymentPage.getShippingCharge()).isNotEqualTo("£0");
    }

}
