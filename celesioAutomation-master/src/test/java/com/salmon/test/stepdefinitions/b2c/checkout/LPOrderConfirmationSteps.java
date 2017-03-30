package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.models.b2c.AccountData;
import com.salmon.test.pageobjects.b2c.checkout.LPOrderConfirmationPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LPOrderConfirmationSteps {
    private LPOrderConfirmationPage lpOrderConfirmationPage;
    private AccountData accountData;

    public LPOrderConfirmationSteps(LPOrderConfirmationPage lpOrderConfirmationPage, AccountData accountData) {
        this.lpOrderConfirmationPage = lpOrderConfirmationPage;
        this.accountData = accountData;
    }

    @Then("^the final order confirmation page is displayed$")
    public void the_final_order_confirmation_page_is_displayed() throws Throwable {
        assertThat(lpOrderConfirmationPage.getOrderConfirmationMessage()).containsIgnoringCase(lpOrderConfirmationPage.getB2cProp("orderConfirmation.message"));
        accountData.setOrderNo(lpOrderConfirmationPage.getTheOrderNumber());
        assertThat(accountData.getOrderNo()).isNotNull();
    }

    @And("^the order confirmation has \"([^\"]*)\" and shipping address as \"([^\"]*)\" for \"([^\"]*)\"$")
    public void the_order_confirmation_has_and_shipping_address_as_for(String shippingMethod, String shippingAddress, String productName) throws Throwable {
        if (shippingMethod.contains("Standard")) {
            assertThat(lpOrderConfirmationPage.getShippingMethod()).containsIgnoringCase(lpOrderConfirmationPage.getB2cProp("standardDelivery"));
        } else {
            assertThat(lpOrderConfirmationPage.getShippingMethod()).containsIgnoringCase(lpOrderConfirmationPage.getB2cProp("nextDayDelivery"));
        }
        assertThat(lpOrderConfirmationPage.getDeliveryAddress()).containsIgnoringCase(lpOrderConfirmationPage.getB2cProp("address.saved." + shippingAddress));
        assertThat(lpOrderConfirmationPage.getProductName()).containsIgnoringCase(productName);
    }

    @Then("^the order total on the confirmation page is displayed as$")
    public void the_order_total_on_the_confirmation_page_is_displayed_as(Map<String, String> orderValues) throws Throwable {
        assertThat(lpOrderConfirmationPage.getShippingCharge()).isEqualToIgnoringCase(orderValues.get("DeliveryCharge"));
        assertThat(lpOrderConfirmationPage.getSubTotal()).isEqualToIgnoringCase(orderValues.get("SubTotal"));
        assertThat(lpOrderConfirmationPage.getOrderTotal()).isEqualToIgnoringCase(orderValues.get("OrderTotal"));
    }
}
