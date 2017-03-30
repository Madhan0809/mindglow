package com.salmon.test.stepdefinitions.b2c.myAccount;
import com.salmon.test.models.b2c.AccountData;
import com.salmon.test.pageobjects.b2c.myAccount.LPMyAccountPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPMyAccountSteps {
    private LPMyAccountPage lpMyAccountPage;
    private AccountData accountData;

    public LPMyAccountSteps(LPMyAccountPage lpMyAccountPage, AccountData accountData) {
        this.lpMyAccountPage = lpMyAccountPage;
        this.accountData = accountData;
    }



    @Then("^the LHS menu on My Account consists of$")
    public void the_LHS_menu_on_My_Account_consists_of(List<String> expectedLhsMenu) throws Throwable {
        assertThat(lpMyAccountPage.getLhsMenuLinks()).containsAll(expectedLhsMenu);
    }

    @Then("^the LHS sub menu on My Account consists of$")
    public void the_LHS_sub_menu_on_My_Account_consists_of(List<String> expectedLhsSubMenu) throws Throwable {
        assertThat(lpMyAccountPage.getLhsSubMenuLinks()).containsAll(expectedLhsSubMenu);
    }

    @When("^I click \"([^\"]*)\" from  My Account LHS sub menu$")
    public void I_click_from_My_Account_LHS_sub_menu(String linkName) throws Throwable {
        lpMyAccountPage.clickLink(linkName);
    }

    @Then("^the Personal Information for user \"([^\"]*)\" is displayed$")
    public void the_Personal_Information_for_user_is_displayed(String userName) throws Throwable {
        lpMyAccountPage.verifyPersonalInformationDetails(userName);
    }

    @Then("^the Address/Collection Preferences for user \"([^\"]*)\" is displayed$")
    public void the_Address_Collection_Preferences_for_user_is_displayed(String userName) throws Throwable {
        lpMyAccountPage.verifyPersonalAddressCollectionPreferences(userName);
    }

    @When("^I select \"([^\"]*)\" from personal wish list page on My Account$")
    public void I_select_from_personal_wish_list_page_on_My_Account(String wishListName) throws Throwable {
        lpMyAccountPage.selectWishListByName(accountData.getWishList());
    }

    @Then("^the product \"([^\"]*)\" is displayed in personal wish list page on My Account$")
    public void the_product_is_displayed_in_personal_wish_list_page_on_My_Account(String productName) throws Throwable {
        assertThat(lpMyAccountPage.checkIfProductIsPresentInWishList(productName)).isEqualTo(lpMyAccountPage.getB2cProp(productName));
    }

    @Then("^the wish list empty message is displayed$")
    public void the_wish_list_empty_message_is_displayed() throws Throwable {
        assertThat(lpMyAccountPage.getEmptyWishListMessage()).isEqualToIgnoringCase(lpMyAccountPage.getB2cProp("wishlist.empty"));
    }

    @When("^I remove the \"([^\"]*)\" from personal wish list page on My Account$")
    public void I_remove_the_from_personal_wish_list_page_on_My_Account(String productName) throws Throwable {
        lpMyAccountPage.removeProductFromWishList(productName);
    }

    @When("^I \"([^\"]*)\" additional address with name \"([^\"]*)\"$")
    public void I_additional_address_with_name(String addressAction, String addressName) throws Throwable {
        lpMyAccountPage.addressAction(addressAction, addressName);
    }

    @Then("^the additional address for \"([^\"]*)\" is displayed$")
    public void the_additional_address_for_is_displayed(String addressType) throws Throwable {
        lpMyAccountPage.verifyAdditonalAddress(addressType);
    }

    @When("^I Edit and Update Preferred Address with name \"([^\"]*)\"$")
    public void I_Edit_and_Update_Preferred_Address_with_name(String addressType) throws Throwable {
        lpMyAccountPage.editOrDeletePreferredAddress(addressType);
    }

    @Then("^there is an address validation error message \"([^\"]*)\"$")
    public void there_is_a_address_validation_error_message(String errorMessage) throws Throwable {
    	if(errorMessage.startsWith("address.")){
    		errorMessage=lpMyAccountPage.getB2cProp("address.validation.message");
    	}
        assertThat(lpMyAccountPage.getErrorMessage()).isEqualToIgnoringCase(errorMessage);
    }
}
