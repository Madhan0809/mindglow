package com.salmon.test.stepdefinitions.b2b.pdp;
import com.salmon.test.pageobjects.b2b.megamenu.B2BMegaMenu;
import com.salmon.test.pageobjects.b2b.pdp.B2BProductDetailsPage;
import com.salmon.test.pageobjects.b2b.plp.B2BProductListsPage;
import com.salmon.test.pageobjects.b2b.search.B2BSearchPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.assertj.core.api.Assertions.assertThat;

public class B2BProductDetailPageSteps {
    private static final Logger LOG = LoggerFactory.getLogger(B2BProductDetailPageSteps.class);
    private B2BProductDetailsPage b2bProductDetailPage;
    B2BMegaMenu b2BMegaMenu;
    B2BProductListsPage b2BProductListsPage;
    B2BSearchPage b2BSearchPage;
    
    
    public B2BProductDetailPageSteps(B2BProductDetailsPage b2bProductDetail) {
        b2bProductDetailPage = b2bProductDetail;
    }

    @Given("^The user is landing on page of \"(.*?)\" in AAH$")
    public void the_user_is_landing_on_page_of_PDP_in_AAH(String product) throws Throwable {
        //	b2bProductDetailPage.landingToPDPFromAAHLogin(b2bPageLandingModule,landingPageName);
        b2bProductDetailPage.goToProductDetailsPage(product);
    }

    @Then("^Check product main picture can be shown in PDP$")
    public void check_product_main_picture_can_be_shown_in_PDP() throws Throwable {
        assertThat(b2bProductDetailPage.isProductImageAvailable()).isTrue();
    }

    @Then("^Check all additional images can be displayed correctly$")
    public void check_all_additional_images_can_be_displayed_correctly() throws Throwable {
    }

    @When("^The user click on each additional image in PDP$")
    public void The_user_click_on_each_additional_images_in_PDP() throws Throwable {
    }

    @Then("^Check the product has no add to basket function$")
    public void check_the_product_has_no_add_to_basket_function() throws Throwable {
    }

    @Then("^Check the product \"(.*?)\" can be shown in PDP$")
    public void check_the_product_email_confirmation_should_be_shown_in_PDP(String checkItemName) throws Throwable {
    }

    @Then("^Check the product name \"(.*?)\" and description display in PDP$")
    public void check_the_product_name_and_description_display_in_PDP(String productName) throws Throwable {
        if(productName.startsWith("b2b.")){
            productName=b2bProductDetailPage.getProp(productName);
        }
        assertThat(b2bProductDetailPage.verifyProductNameDisplayed(productName)).isTrue();
    }

    @Then("^Check the product rating is (\\d+) in PDP$")
    public void check_the_product_rating_in_PDP(int reviewStar) throws Throwable {
        assertThat(b2bProductDetailPage.verifyReviewRating(reviewStar)).isTrue();
    }

    @Then("^Check the SKU of \"(.*?)\" display correctly$")
    public void check_the_sku_of_product_display(String skuDetails) throws Throwable {
        assertThat(b2bProductDetailPage.verifySkuDetailsDisplayed(skuDetails).equalsIgnoreCase(skuDetails.trim())).isTrue();
    }

    @Then("^Check the product \"([^\"]*)\" displayed in PDP$")
    public void Check_the_product_displayed_in_PDP(String productInfo) throws Throwable {
        assertThat(b2bProductDetailPage.verifyProductImageDisplayed(productInfo)).isTrue();
    }

    @And("^All the information tabs are displayed and clickable$")
    public void All_the_information_tabs_are_displayed_and_clickable(List<String> infoTabs) throws Throwable {
        assertThat(b2bProductDetailPage.infoTabsDisplayedAndClickable(infoTabs)).isTrue();
    }

    @Then("^Verify that Breadcrumb \"([^\"]*)\" displayed in PDP$")
    public void Verify_that_Breadcrumb_displayed_in_PDP(String arg1) throws Throwable {
        assertThat(b2bProductDetailPage.verifyBreadcrumbDisplayed()).isTrue();
    }

    @And("^Appropriate product details are displayed in correct format$")
    public void Appropriate_product_details_are_displayed(List<String> expectedDetails) throws Throwable {
        Map<String, String> productDetailsMap = b2bProductDetailPage.getProductDetailsOnRHS();
        for(String k: productDetailsMap.keySet()){
            assertThat(expectedDetails.contains(k)).isEqualTo(true);
        }
    }

    @When("^The user select colour \"([^\"]*)\"$")
    public void The_user_select_colour(String colour) throws Throwable {
        b2bProductDetailPage.selectProductColour(colour);
    }

    @And("^The user select size \"([^\"]*)\"$")
    public void The_user_select_size(String size) throws Throwable {
        b2bProductDetailPage.selectProductSize(size);
    }

    @And("^The user updates product quantity to \"([^\"]*)\"$")
    public void The_user_updates_product_quantity_to(String quantity) throws Throwable {
        b2bProductDetailPage.updateProductQuantity(quantity);
    }

    @And("^The user clicks on \"([^\"]*)\" option from PDP$")
    public void The_user_clicks_on_option_from_PDP(String addToBasket) throws Throwable {
        b2bProductDetailPage.addToBasket(addToBasket);
    }

    @Then("^The user navigates to \"([^\"]*)\" pop-up$")
    public void The_user_navigates_to_pop_up(String favouriteMsg) throws Throwable {
        assertThat(b2bProductDetailPage.verifyAddToFavouritesMsgDisplayed(favouriteMsg)).isTrue();
    }

    @When("^The user select \"([^\"]*)\" option$")
    public void The_user_select_option(String favouriteOpt) throws Throwable {
        if(favouriteOpt.startsWith("favourite.")){
            favouriteOpt=b2bProductDetailPage.getB2bProp(favouriteOpt);
        }
        b2bProductDetailPage.selectFavourite(favouriteOpt);
    }

    @And("^add new favourite as \"([^\"]*)\"$")
    public void add_new_favourite_as(String favourite) throws Throwable {
        b2bProductDetailPage.createNewFavourite(favourite);
    }

    @Then("^click on \"([^\"]*)\" button$")
    public void click_on_button(String buttonName) throws Throwable {
        b2bProductDetailPage.clickOnAddToFavButton(buttonName);
    }

    @Given("^The user is navigating to PDP on click \"([^\"]*)\" product Name$")
    public void The_user_is_navigating_to_PDP_on_click_product_Name(String productName) throws Throwable {
        if(productName.startsWith("b2b.")){
            productName=b2bProductDetailPage.getProp(productName);
        }
        b2BMegaMenu = new B2BMegaMenu();
        b2BMegaMenu.onHoverOfMainMenu("Shop By Category");
        b2BMegaMenu.onHoverOfDepartment("Baby & Children");
        b2BProductListsPage = new B2BProductListsPage();
        b2BProductListsPage.selectCategory("Baby Changing");
        b2BSearchPage = new B2BSearchPage();
        b2BSearchPage.clickOnProductTitle(productName);
    }

    @When("^User click on \"([^\"]*)\" link from PDP$")
    public void User_click_on_link_from_PDP(String favouriteLink) throws Throwable {
        b2bProductDetailPage.clickOnLink(favouriteLink);
    }

    @Then("^verify validation message of adding favourite list on page$")
    public void verify_validation_message_of_adding_favourite_list_on_page() throws Throwable {
        assertThat(b2bProductDetailPage.verifyFavouritesConfirmErrorMsgDisplayed()).isTrue();
    }

    @Then("^verify added confirmation message displayed on page$")
    public void verify_added_confirmation_message_displayed_on_page() throws Throwable {
        assertThat(b2bProductDetailPage.verifyFavouritesConfirmMsgDisplayed()).isTrue();
    }

    @And("^add new favourite as \"([^\"]*)\" with random data$")
    public void add_new_favourite_as_with_random_data(String favourite) throws Throwable {
        b2bProductDetailPage.createNewFavouriteRandomData(favourite);
    }

    @Then("^The user navigates to \"([^\"]*)\" tab$")
    public void The_user_navigates_to_tab(String reviews) throws Throwable {
        assertThat(b2bProductDetailPage.verifyReviewsTextDisplayed(reviews)).isTrue();
    }

    @And("^The user verify below sections$")
    public void The_user_verify_below_sections(List<String> ratingSections) throws Throwable {
        assertThat(b2bProductDetailPage.getExpectedRatingSectons()).isEqualTo(ratingSections);
    }

    @Then("^verify correct number of reviews sections displayed or not$")
    public void verify_correct_number_of_reviews_sections_displayed_or_not() throws Throwable {
        assertThat(b2bProductDetailPage.verifyNoOfReviewsSections()).isTrue();
    }

    @When("^User clicked on \"([^\"]*)\" link from PDP$")
    public void User_clicked_on_link_from_PDP(String reviews) throws Throwable {
        b2bProductDetailPage.clickOnReviewsLink(reviews);
    }

    @When("^The user update the product quantity to \"([^\"]*)\"$")
    public void The_user_update_the_product_quantity_to(String quantity) throws Throwable {
        b2bProductDetailPage.updateProductQuantity(quantity);
    }

    @Then("^The user should navigated to \"([^\"]*)\" page$")
    public void The_user_should_navigated_to_page(String pageName) throws Throwable {
        assertThat(b2bProductDetailPage.canAccessTargetPage(pageName)).isTrue();
    }

    @When("^The user clicks on \"([^\"]*)\" from PDP$")
    public void The_user_clicks_on_from_PDP(String arg1) throws Throwable {
        b2bProductDetailPage.onClickAAHMiniCart();
    }
}
