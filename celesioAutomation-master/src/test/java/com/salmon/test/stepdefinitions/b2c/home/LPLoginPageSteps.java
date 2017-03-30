package com.salmon.test.stepdefinitions.b2c.home;
import com.salmon.test.pageobjects.b2c.home.LPLoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPLoginPageSteps {
    private LPLoginPage lpLoginPage;

    public LPLoginPageSteps(LPLoginPage lpLoginPage) {
        this.lpLoginPage = lpLoginPage;
    }

    @When("^The user clicks Sign In button$")
    public void the_user_clicks_Sign_In_button() throws Throwable {
        lpLoginPage.onClickOfSignIn();
    }

    @Then("^The login page is displayed$")
    public void The_login_page_is_displayed() throws Throwable {
        assertThat(lpLoginPage.isSignInShown()).isTrue();
    }

    @When("^The user clicks \"(.*?)\" button on login page$")
    public void the_user_clicks_button_on_login_page(String button) throws Throwable {
        lpLoginPage.onClickOfButton(button);
    }

    @Then("^The Register page opens$")
    public void the_Register_page_opens() throws Throwable {
        assertThat(lpLoginPage.isRegisterOpen()).isTrue();
    }

    @When("^The user inputs \"(.*?)\" and \"(.*?)\" as logon ID and password on login page$")
    public void the_user_inputs_and_as_logon_ID_and_password_on_login_page(String loginID, String password) throws Throwable {
        lpLoginPage.inputLoginIDPWD(loginID, password);
    }

    @Then("^The user signs in successfully$")
    public void the_user_signs_in_successfully() throws Throwable {
        assertThat(lpLoginPage.isSignIn()).isTrue();
    }

    @Then("^The error message for \"(.*?)\" is displayed on login page\\.$")
    public void the_error_message_for_is_displayed_on_login_page(String message) throws Throwable {
        if ("Incorrect Credentials".equalsIgnoreCase(message)) {
            assertThat(lpLoginPage.getErrorMessage()).isEqualTo(lpLoginPage.getB2cProp("checkout.incorrectIdOrPwd"));
        } else if ("Enter LogonID".equalsIgnoreCase(message)) {
            assertThat(lpLoginPage.getErrorMessage()).isEqualTo(lpLoginPage.getB2cProp("checkout.enterLogonId"));
        }
    }

    @Then("^The login page disappears$")
    public void the_login_page_disappears() throws Throwable {
        assertThat(lpLoginPage.isSignInDisappear()).isTrue();
    }

    @When("^The user clicks \"(.*?)\" on the PLP$")
    public void the_user_clicks_on_the_PLP(String productName) throws Throwable {
        lpLoginPage.clickOnProductTitle(productName);
    }

    @And("^Relevant details are displayed on login page$")
    public void Relevant_details_are_displayed_on_login_page(List<String> expectedLoginPageLabels) throws Throwable {
        assertThat(lpLoginPage.getLoginPageLabelDetails()).isEqualTo(expectedLoginPageLabels);
    }

    @Given("^The user navigates to Registration page$")
    public void The_user_navigates_to_Registration_page() throws Throwable {
        this.the_user_clicks_Sign_In_button();
        this.The_login_page_is_displayed();
        this.the_user_clicks_button_on_login_page("Register");
        this.the_Register_page_opens();
    }
}
