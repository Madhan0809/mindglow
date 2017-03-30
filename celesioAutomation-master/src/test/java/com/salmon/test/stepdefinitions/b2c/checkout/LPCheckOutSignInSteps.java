package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.pageobjects.b2c.checkout.LPCheckOutSignInPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LPCheckOutSignInSteps {
    private LPCheckOutSignInPage lpCheckOutSignInPage;

    public LPCheckOutSignInSteps(LPCheckOutSignInPage lpCheckOutSignInPage) {
        this.lpCheckOutSignInPage = lpCheckOutSignInPage;
    }

    @Given("^The user is on CheckOut SignIn page$")
    public void the_user_is_on_CheckOut_SignIn_page() throws Throwable {
        lpCheckOutSignInPage.goToCheckOutSignInPage();
    }

    @When("^The user clicks Add to basket button$")
    public void the_user_clicks_Add_to_basket_button() throws Throwable {
        lpCheckOutSignInPage.onClickOfAddToBasket();
    }

    @And("^The user adds the same product \"(.*?)\" times to the basket$")
    public void The_user_adds_the_same_product_times_to_the_basket(int countOfAddToBasket) throws Throwable {
        for (int i = 0; i < countOfAddToBasket; i++) {
            lpCheckOutSignInPage.onClickOfAddToBasket();
        }
    }

    @When("^The user clicks mini shop cart$")
    public void the_user_clicks_mini_shop_cart() throws Throwable {
        lpCheckOutSignInPage.onClickOfMiniShopcart();
    }

    @Then("^\"(.*?)\" is displayed on mini shop cart$")
    public void is_displayed_on_mini_shop_cart(String subtotal) throws Throwable {
        assertThat(lpCheckOutSignInPage.getSubtotal()).isEqualTo(subtotal);
    }

	
	/*@When("^The user clicks Go to Cart button on mini shop cart$")
    public void the_user_clicks_Go_to_Cart_button_on_mini_shop_cart() throws Throwable {
		lpCheckOutSignInPage.onClickOfGoToCart();
	}*/

    @Then("^The shopcart page opens$")
    public void the_shopcart_page_opens() throws Throwable {
        assertThat(lpCheckOutSignInPage.isShopCartPage()).isTrue();
    }

    @When("^The user clicks Secure Checkout button on shopcart page$")
    public void the_user_clicks_Secure_Checkout_button_on_shopcart_page() throws Throwable {
        lpCheckOutSignInPage.onClickOfSecureCheckout();
    }

    @Then("^The CheckOut SignIn page opens$")
    public void the_CheckOut_SignIn_page_opens() throws Throwable {
        assertThat(lpCheckOutSignInPage.getSignInHeader()).isEqualToIgnoringCase(lpCheckOutSignInPage.getB2cProp("checkout.signIn.header"));
    }

    @When("^The user inputs \"(.*?)\" and \"(.*?)\" as logon ID and password on CheckOut SignIn page$")
    public void the_user_inputs_and_as_logon_ID_and_password_on_CheckOut_SignIn_page(String loginID, String password) throws Throwable {
        if (loginID.contains("csr.") || (loginID.contains("b2c."))) {
            loginID = lpCheckOutSignInPage.getProp(loginID);
            password = lpCheckOutSignInPage.getProp(password);
        }
        lpCheckOutSignInPage.inputLoginIDPWD(loginID, password);
    }

    @When("^The user clicks \"(.*?)\" button on CheckOut SignIn page$")
    public void the_user_clicks_button_on_CheckOut_SignIn_page(String button) throws Throwable {
        lpCheckOutSignInPage.onClickOfButton(button);
    }

    @Then("^The Register page opens from CheckOut SignIn page$")
    public void the_Register_page_opens_from_CheckOut_SignIn_page() throws Throwable {
        assertThat(lpCheckOutSignInPage.isRegisterOpen()).isTrue();
    }

    @Then("^The error message for \"(.*?)\" is displayed on CheckOut SignIn page\\.$")
    public void the_error_message_for_is_displayed_on_CheckOut_SignIn_page(String message) throws Throwable {
        if ("Incorrect Credentials".equalsIgnoreCase(message)) {
            assertThat(lpCheckOutSignInPage.getErrorMessage()).isEqualTo(lpCheckOutSignInPage.getB2cProp("checkout.incorrectIdOrPwd"));
        } else if ("Enter LogonID".equalsIgnoreCase(message)) {
            assertThat(lpCheckOutSignInPage.getErrorMessage()).isEqualTo(lpCheckOutSignInPage.getB2cProp("checkout.enterLogonId"));
        }
    }

    @Then("^The \"(.*?)\" page opens$")
    public void the_page_opens(String delivery) throws Throwable {
        assertThat(lpCheckOutSignInPage.getDeliveryPage()).isEqualToIgnoringCase(delivery);
    }
}
