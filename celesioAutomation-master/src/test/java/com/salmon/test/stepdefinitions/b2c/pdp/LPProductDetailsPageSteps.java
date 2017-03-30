package com.salmon.test.stepdefinitions.b2c.pdp;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.b2c.AccountData;
import com.salmon.test.models.b2c.Product;
import com.salmon.test.pageobjects.b2c.pdp.LPProductDetailsPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPProductDetailsPageSteps {
    private LPProductDetailsPage lpProductDetailsPage;
    private AccountData accountData;

    public LPProductDetailsPageSteps(LPProductDetailsPage lpProductDetailsPage, AccountData accountData) {
        this.lpProductDetailsPage = lpProductDetailsPage;
        this.accountData = accountData;
    }

    @Given("^The user is on \"(.*?)\" PDP$")
    public void the_user_is_on_PDP(String product) throws Throwable {
        lpProductDetailsPage.goToProductDetailsPage(product);
    }

    @Given("^The user navigates back to \"([^\"]*)\" PDP and all cookies are set to be \"([^\"]*)\"$")
    public void The_user_navigates_back_to_PDP_and_all_cookies_are_set_to_be(String product, String deleteCookies) throws Throwable {
        if (deleteCookies.equalsIgnoreCase("NOT DELETED")) {
            lpProductDetailsPage.goToProductDetailsPage(product, false);
        } else {
            lpProductDetailsPage.goToProductDetailsPage(product, true);
        }
    }

    @Then("^Breadcrumb \"(.*?)\" is displayed in PDP$")
    public void breadcrumb_is_displayed_in_PDP(String product) throws Throwable {
        assertThat(lpProductDetailsPage.isRelevantBreadCrumb(product)).isTrue();
    }

    @Then("^Appropriate product details are displayed on RHS$")
    public void appropriate_product_details_are_displayed_on_RHS(@Transpose List<Product> productDetails) throws Throwable {
        Product productElement = lpProductDetailsPage.getProductDetailsOnRHS();
        Product product = productDetails.get(0);
        assertThat(productElement.getProductName()).isEqualTo(product.getProductName());
        //assertThat(productElement.getUserRating()).containsIgnoringCase(product.getUserRating());
        assertThat(productElement.getPrice()).isEqualTo(product.getPrice());
        assertThat(productElement.getWasPrice()).isEqualTo(product.getWasPrice());
        assertThat(productElement.getSavePrice()).isEqualTo(product.getSavePrice());
        assertThat(productElement.getStock()).isEqualTo(product.getStock());
        assertThat(productElement.getQuantity()).isEqualTo(product.getQuantity());
        assertThat(lpProductDetailsPage.isProductImageDisplayedInPDP()).isEqualTo(product.isProductImage());
    }

    @And("^Appropriate product bundle details are displayed on RHS$")
    public void Appropriate_product_bundle_details_are_displayed_on_RHS(@Transpose List<Product> productDetails) throws Throwable {
        Product productElement = lpProductDetailsPage.getBundleProductDetailsRHS();
        Product product = productDetails.get(0);
        assertThat(productElement.getProductName()).isEqualTo(product.getProductName());
        //assertThat(productElement.getUserRating()).containsIgnoringCase(product.getUserRating());
        assertThat(productElement.getPrice()).isEqualTo(product.getPrice());
        assertThat(productElement.getQuantity()).isEqualTo(product.getQuantity());
        assertThat(lpProductDetailsPage.isProductImageDisplayedInPDP()).isEqualTo(product.isProductImage());
    }

    @And("^each item in  product bundle consists of$")
    public void each_item_in_product_bundle_consists_of(List<Product> productDetails) throws Throwable {
    }

    @Then("^All the info tabs are displayed and clickable$")
    public void all_the_info_tabs_are_displayed_and_clickable(List<String> infoTabs) throws Throwable {
        lpProductDetailsPage.infoTabsDisplayedAndClickable(infoTabs);
    }

    @When("^The user clicks on \"(.*?)\" in PDP$")
    public void the_user_clicks_on_in_PDP(String button) throws Throwable {
        lpProductDetailsPage.clickButton(button);
    }

    @When("^The user update product quantity to \"(.*?)\"$")
    public void the_user_update_product_quantity_to(String quantity) throws Throwable {
        lpProductDetailsPage.updateProductQuantity(quantity);
    }

    private String parseCurrency(String currency) {
        return currency.replaceAll("[^\\d.]+", "");
    }

    @When("^The user selects colour \"([^\"]*)\"$")
    public void The_user_selects_colour(String colour) throws Throwable {
        lpProductDetailsPage.selectProductColour(colour);
    }

    @And("^The user selects size \"([^\"]*)\"$")
    public void The_user_selects_size(String size) throws Throwable {
        lpProductDetailsPage.selectProductSize(size);
    }

    @And("^The attributes should match the ones selected in PDP$")
    public void The_attributes_should_match_the_ones_selected_in_PDP() throws Throwable {
        assertThat(lpProductDetailsPage.verifyListOfVariantsFromCheckOutBasketAreSameAsOnPDP()).isTrue();
    }

    @And("^The user clicks on Sign In/Register link$")
    public void The_user_clicks_on_Sign_In_Register_link() throws Throwable {
        assertThat(lpProductDetailsPage.getSignInAndRegisterText()).isEqualToIgnoringCase(lpProductDetailsPage.getB2cProp("signInORRegister.wishList"));
        lpProductDetailsPage.clickSignInAndRegisterLink();
    }

    @Then("^the \"([^\"]*)\" message is displayed for the wish list$")
    public void the_message_is_displayed_for_the_wish_list(String message) throws Throwable {
        assertThat(lpProductDetailsPage.getWishListSuccessMessage()).isEqualToIgnoringCase(lpProductDetailsPage.getB2cProp(message));
    }

    @When("^The user creates new wish list from PDP$")
    public void The_user_creates_new_wish_list_from_PDP() throws Throwable {
        accountData.setWishList(RandomGenerator.random(6, PermittedCharacters.ALPHABETS));
        lpProductDetailsPage.createNewWishList(accountData.getWishList());
    }

    @And("^The user selects \"([^\"]*)\" wish list from the wish list dropdown$")
    public void The_user_selects_wish_list_from_the_wish_list_dropdown(String wishListName) throws Throwable {
        if (wishListName.equalsIgnoreCase("NEW")) {
            wishListName = accountData.getWishList();
        } else accountData.setWishList(wishListName);
        lpProductDetailsPage.selectWishListByNameFromDropDown(wishListName);
    }

    @Then("^the \"(.*?)\" error message is displayed$")
    public void the_error_message_is_displayed(String errorMessage) throws Throwable {
        assertThat(lpProductDetailsPage.getPopUpErrorMessage()).isEqualToIgnoringCase(lpProductDetailsPage.getB2cProp(errorMessage));
    }

    @When("^I select and update quantity for a product in bundle for variant$")
    public void I_select_and_update_quantity_for_a_product_in_bundle_for_variant(@Transpose List<Product> productDetails) throws Throwable {
        assertThat(lpProductDetailsPage.selectAndUpdateQuantityForAProductItemVariantFromBundle(productDetails.get(0))).isTrue();
    }

    @When("^I click on add selected to basket$")
    public void I_click_on_add_selected_to_basket() throws Throwable {
        lpProductDetailsPage.addSelectedToBasket();
    }
}
