package com.salmon.test.stepdefinitions.b2b.plp;
import com.salmon.test.models.b2b.PLPProductItems;
import com.salmon.test.pageobjects.b2b.megamenu.B2BMegaMenu;
import com.salmon.test.pageobjects.b2b.plp.B2BProductListsPage;
import com.salmon.test.pageobjects.b2b.register.B2BRegisterPage;
import com.salmon.test.pageobjects.b2b.search.B2BSearchPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import org.seleniumhq.jetty9.util.log.Log;

import static org.assertj.core.api.Assertions.assertThat;


public class B2BProductListPageListSteps {

    private B2BProductListsPage b2BProductListsPage;
    B2BMegaMenu b2BMegaMenu;
    B2BSearchPage b2BSearchPage;

    public B2BProductListPageListSteps(B2BProductListsPage b2BProductListsPage, B2BMegaMenu b2BMegaMenu) {
        this.b2BProductListsPage = b2BProductListsPage;
        this.b2BMegaMenu=b2BMegaMenu;
    }

    @Given("^User enters PLP by selecting default category$")
    public void user_enters_PLP_by_selecting_default_category() throws Throwable{        
        b2BMegaMenu.onHoverOfMainMenu(b2BMegaMenu.getB2bProp("departmentPage.defaultMainMenu"));
        b2BMegaMenu.onHoverOfDepartment(b2BMegaMenu.getB2bProp("category.test"));
        String defaultSubCategory=b2BProductListsPage.getB2bProp("category.babyChanging");
        b2BProductListsPage.selectCategory(defaultSubCategory);
        assertThat(b2BProductListsPage.onSubCategoryScreen(defaultSubCategory).trim().equalsIgnoreCase(b2BProductListsPage.getB2bProp("babyChanging.title"))).isTrue();
    }
    
    @When("^The user Select \"([^\"]*)\" Category$")
    public void The_user_Select_Category(String subCategory) throws Throwable {
        b2BProductListsPage.selectCategory(subCategory);
    }

    @Then("^The user should be navigated to \"([^\"]*)\" Sub Category screen$")
    public void The_user_should_be_navigated_to_Sub_Category_screen(String subCatPageTitle) throws Throwable {
        if(subCatPageTitle.startsWith("category.")){
            subCatPageTitle=b2BProductListsPage.getB2bProp(subCatPageTitle);
        }
        if (subCatPageTitle.equalsIgnoreCase(b2BProductListsPage.getB2bProp("category.halfPrice").trim())) {
            assertThat(b2BProductListsPage.onSubCategoryScreen(subCatPageTitle).equalsIgnoreCase(b2BProductListsPage.getB2bProp("halfPricePage.title"))).isTrue();
        } else if (subCatPageTitle.equalsIgnoreCase(b2BProductListsPage.getB2bProp("category.greatSavings").trim())) {
            assertThat(b2BProductListsPage.onSubCategoryScreen(subCatPageTitle).equalsIgnoreCase(b2BProductListsPage.getB2bProp("greatSavings.title"))).isTrue();
        } else if (subCatPageTitle.trim().equalsIgnoreCase(b2BProductListsPage.getB2bProp("category.sub").trim())) {
            assertThat(b2BProductListsPage.onSubCategoryScreen(subCatPageTitle).trim().equalsIgnoreCase(b2BProductListsPage.getB2bProp("subCategory.title").trim())).isTrue();
        } else if(subCatPageTitle.equalsIgnoreCase(b2BProductListsPage.getB2bProp("category.babyChanging").trim())){
        	assertThat(b2BProductListsPage.onSubCategoryScreen(subCatPageTitle).trim().equalsIgnoreCase(b2BProductListsPage.getB2bProp("babyChanging.title"))).isTrue();
        }
    }

    @Then("^Check the \"([^\"]*)\" text label$")
    public void Check_the_text_label(String filterByText) throws Throwable {
       // filterByText = b2BProductListsPage.getB2bProp("filterBy.text");
       assertThat(b2BProductListsPage.checkFilterByText().equalsIgnoreCase(filterByText)).isTrue();
    }

    @And("^User can select \"([^\"]*)\" sign and Inspect the \"([^\"]*)\" sign$")
    public void User_can_select_sign_and_Inspect_the_sign(String arg1, String arg2) throws Throwable {
        b2BProductListsPage.inspectTheFilterSign();
    }

    @And("^User can Click the \"([^\"]*)\" sign and inspect the \"([^\"]*)\"$")
    public void User_can_Click_the_sign_and_inspect_the(String arg1, String arg2) throws Throwable {
        b2BProductListsPage.verifyTheFilterSign();
    }

    @And("^Verify the heading text for the displayed attributes$")
    public void Verify_the_heading_text_for_the_displayed_attributes() throws Throwable {
        assertThat(b2BProductListsPage.verifyPageHeading()).isTrue();
    }

    @And("^user can verify the price range attribute$")
    public void user_can_verify_the_price_range_attribute() throws Throwable {
        assertThat(b2BProductListsPage.verifyPriceRangeText()).isTrue();
    }

    @Then("^Check the text displays Number of Products on page$")
    public void Check_the_text_displays_Number_of_Products_on_page() throws Throwable {
        assertThat(b2BProductListsPage.verifyProductsLabel().toLowerCase().startsWith(b2BProductListsPage.getB2bProp("products.prefix").toLowerCase())).isTrue();
    }

    @And("^Total number of products is displayed accordingly$")
    public void Total_Number_of_products() throws Throwable {
        assertThat(b2BProductListsPage.verifyNumberOfProducts()).isTrue();
    }

    @When("^The user selects \"([^\"]*)\" option from sort by list$")
    public void The_user_selects_option_from_sort_by_list(String sortName) throws Throwable {
        b2BProductListsPage.onSelectOfSortBy(sortName);
    }

    @Then("^The products on the page should be displayed in sorted by \"([^\"]*)\"$")
    public void The_products_on_the_page_should_be_displayed_in_sorted_by(String sortName) throws Throwable {
        List<PLPProductItems> productItems = b2BProductListsPage.getProductList();
        if (sortName.equalsIgnoreCase(b2BProductListsPage.getB2bProp("sortBy.Name"))) {
            assertThat(productItems).isSortedAccordingTo(
                    (p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
        } else if (sortName.equalsIgnoreCase(b2BProductListsPage.getB2bProp("sortBy.Brand"))) {
            assertThat(productItems).isSortedAccordingTo(
                    (p1, p2) -> p2.getProductName().compareTo(p1.getProductName()));
        } else if (sortName.equalsIgnoreCase(b2BProductListsPage.getB2bProp("sortBy.priceLowToHigh"))) {
            assertThat(productItems).isSortedAccordingTo(
                    (p1, p2) -> (p1.getProductPrice() < p2.getProductPrice()) ? -1 : (p1.getProductPrice() > p2.getProductPrice()) ? 1 : 0);
        } else if (sortName.equalsIgnoreCase(b2BProductListsPage.getB2bProp("sortBy.priceHighToLow"))) {
            assertThat(productItems).isSortedAccordingTo(
                    (p1, p2) -> (p2.getProductPrice() < p1.getProductPrice()) ? -1 : (p2.getProductPrice() > p1.getProductPrice()) ? 1 : 0);
        }
    }

    @Then("^Check the text displays \"([^\"]*)\" on page$")
    public void Check_the_text_displays_on_page(String perPageText) throws Throwable {
        assertThat(b2BProductListsPage.verifyPerPageText(perPageText)).isTrue();
    }

    @When("^The user selects on \"([^\"]*)\" from Items per page drop down$")
    public void The_user_selects_on_from_Items_per_page_drop_down(String pageSize) throws Throwable {
        b2BProductListsPage.onSelectOfPageSize(pageSize);
    }

    @Then("^The user should be presented with \"([^\"]*)\" products page$")
    public void The_user_should_be_presented_with_products_page(String pageSize) throws Throwable {
        assertThat(b2BProductListsPage.verifyPageSize(Integer.valueOf(pageSize))).isTrue();
    }

    @When("^The user clicks on \"([^\"]*)\" option$")
    public void The_user_clicks_on_option(String view) throws Throwable {
        b2BProductListsPage.onClickOfView(view);
    }

    @Then("^The page should be presented with \"([^\"]*)\" of products$")
    public void The_page_should_be_presented_with_of_products(String view) throws Throwable {
        assertThat(b2BProductListsPage.isCorrectViewDisplayed(view)).isTrue();
    }

    @Then("^\"(.*?)\" items should be added into \"(.*?)\" basket$")
    public void items_should_be_added_into_basket(String quantity, String basketType) throws Throwable {
      assertThat(b2BProductListsPage.getNumberOfProductAddedToBasket(basketType)).isEqualTo(quantity);
      b2BProductListsPage.closeMiniBasketPopup();
    }
    
    @And("^The user selects the colour \"([^\"]*)\"$")
    public void The_user_selects_the_colour(String colour) throws Throwable {
        b2BProductListsPage.selectProductColour(colour);
    }

    @And("^The user selects the size \"([^\"]*)\"$")
    public void The_user_selects_the_size(String size) throws Throwable {
        b2BProductListsPage.selectProductSize(size);

    }

    @And("^The user add product \"(.*?)\" with quantity (\\d+) into basket \"(.*?)\" in PLP$")
    public void The_user_updates_product_quantity_to_in_PLP(String product, int qty, String basketName) throws Throwable {
        if(basketName.startsWith("b2b.")){
        	basketName=b2BProductListsPage.getProp(basketName);
        }
        if(product.startsWith("b2b.")){
            product=b2BProductListsPage.getProp(product);
        }
    	b2BProductListsPage.addProductToBasketInPLP(product,Integer.toString(qty),basketName);
    }

    
    @Then("^The user should be navigated to the checkout basket page$")
    public void The_user_should_be_navigated_to_the_checkout_basket_page() throws Throwable {
        assertThat(b2BProductListsPage.isNavigatedToCheckoutPage()).isTrue();
    }

    @And("^The attributes should match with the ones selected in PDP$")
    public void The_attributes_should_match_with_the_ones_selected_in_PDP() throws Throwable {
        assertThat(b2BProductListsPage.verifyListOfVariantsFromCheckOutBasketAreSameAsOnPDP()).isTrue();
    }

    @When("^User refresh the page$")
    public void User_refresh_the_page() throws Throwable {
       b2BProductListsPage.refreshThePage();
    }

    @Then("^The view returns to the default list view$")
    public void The_view_returns_to_the_default_list_view() throws Throwable {
        assertThat(b2BProductListsPage.isDefaultViewDisplayed()).isTrue();
    }

    @When("^The user clicks on the product image$")
    public void The_user_clicks_on_the_product_image() throws Throwable {
        b2BProductListsPage.onClickOfProductImage();
    }

    @When("^The user clicks on the product text$")
    public void The_user_clicks_on_the_product_text() throws Throwable {
        b2BProductListsPage.onClickOfProductName();
    }

    @And("^The user selects clear all filters$")
    public void The_user_selects_clear_all_filters() throws Throwable {
        b2BProductListsPage.clearAllAppliedFilters();
    }

    @Then("^All filters must be cleared$")
    public void All_filters_must_be_cleared() throws Throwable {
        assertThat(b2BProductListsPage.getActiveFilterAmount()).isEqualTo(0);
    }

    @Then("^The user should be navigated to the product details page$")
    public void The_user_should_be_navigated_to_the_product_details_page() throws Throwable {
        assertThat(b2BProductListsPage.isNavigatedToProductDetailsPage()).isTrue();
    }
    
    @When("^The user selects the filter item \"([^\"]*)\" from \"([^\"]*)\" filter$")
    public void The_user_selects_the_filter_item_from_filter(String filterItem, String filterName) throws Throwable { 
       b2BProductListsPage.selectOneItemFromFilterMenu(filterName);
    }

    @Then("^The user should be presented with \"([^\"]*)\" product types only$")
    public void The_user_should_be_presented_with_product_types_only(String type) throws Throwable {
        assertThat(b2BProductListsPage.getProductTypes(type)).isTrue();
    }

    @And("^Clear all link should display on facet menu$")
    public void Clear_all_link_should_display_on_facet_menu() throws Throwable {
        assertThat(b2BProductListsPage.verifyIfClearAllIsDisplayed()).isTrue();
    }

    @And("^Verify that Facet filter should be applied$")
    public void Verify_that_Facet_filter_should_be_applied() throws Throwable {
        assertThat(b2BProductListsPage.verifyIfFacetFilterIsApplied()).isTrue();
    }

    @When("^The user enter price range type from facet menu$")
    public void The_user_enter_price_range_type_from_facet_menu(@Transpose Map<String, String> userOwnPriceRange) throws Throwable {
        b2BProductListsPage.inputProductsInRange(userOwnPriceRange);
    }

    @Then("^There is totally (\\d+) products in PLP$")
    public void There_is_totally_products_in_PLP(int expectedCount) throws Throwable {
        assertThat(b2BProductListsPage.getAllProductCount()).isEqualTo(expectedCount);
    }

    @When("^The user selects clear all filters option$")
    public void The_user_selects_clear_all_filters_option() throws Throwable {
          b2BProductListsPage.clearAllAppliedFilters();
    }

    @When("^Clear the \"([^\"]*)\" filter from facet menu$")
    public void Clear_the_filter_from_facet_menu(String filterName) throws Throwable {
        b2BProductListsPage.clearActiveFacetFilter(filterName);
    }

    @Then("^There are totally (\\d+) products shows in PLP$")
    public void There_are_totally_products_shows_in_PLP(int expectedCount) throws Throwable {
        assertThat(b2BProductListsPage.getAllProductCount()).isEqualTo(expectedCount);
    }

    @Then("^All Selected filters should be cleared$")
    public void All_Selected_filters_should_be_cleared() throws Throwable {
        assertThat(b2BProductListsPage.getActiveFilterAmount()).isEqualTo(0);
    }

    @When("^The user clicks on \"([^\"]*)\" page selection$")
    public void The_user_clicks_on_page_selection(String navButton) throws Throwable {
        b2BProductListsPage.selectedOnNavigationButton(navButton);
    }

    @Then("^The user navigate to respective page$")
    public void The_user_navigate_to_respective_page() throws Throwable {
        assertThat(b2BProductListsPage.verifyNavigatedToNextPage()).isTrue();
    }

    @Then("^The view returns to the default grid view$")
    public void The_view_returns_to_the_default_grid_view() throws Throwable {
        assertThat(b2BProductListsPage.isDefaultViewDisplayed()).isTrue();
    }

    @Given("^The user is navigating to PLP go to \"([^\"]*)\" product Name$")
    public void The_user_is_navigating_to_PLP_go_to_product_Name(String productName) throws Throwable {
        b2BMegaMenu = new B2BMegaMenu();
        b2BMegaMenu.onHoverOfMainMenu("Shop By Category");
        b2BMegaMenu.onHoverOfDepartment("Baby & Children");
        b2BProductListsPage = new B2BProductListsPage();
        b2BProductListsPage.selectCategory("Baby Changing");
        b2BSearchPage = new B2BSearchPage();
        b2BSearchPage.clickOnProductTitle(productName);
    }

    @Given("^The user is navigating to PLP$")
    public void The_user_is_navigating_to_PLP() throws Throwable {
        b2BMegaMenu = new B2BMegaMenu();
        b2BMegaMenu.onHoverOfMainMenu("Shop By Category");
        b2BMegaMenu.onHoverOfDepartment("Baby & Children");
        b2BProductListsPage = new B2BProductListsPage();
        b2BProductListsPage.selectCategory("Baby Changing");
    }

    @And("^go to \"([^\"]*)\" product and verify \"([^\"]*)\" display correctly$")
    public void go_to_product_and_verify_display_correctly(String productTitle, String skuDetails) throws Throwable {
        if(productTitle.startsWith("b2b.")){
            productTitle=b2BProductListsPage.getProp(productTitle);
        }
        assertThat(b2BProductListsPage.verifySkuDetailsDisplayed(productTitle, skuDetails).equalsIgnoreCase(skuDetails.trim())).isTrue();
    }

    @When("^User click on \"([^\"]*)\" link from PLP$")
    public void User_click_on_link_from_PLP(String favouriteLink) throws Throwable {
        b2BProductListsPage.clickOnLink(favouriteLink);
    }

    @Then("^user Navigate to \"([^\"]*)\" page$")
    public void user_Navigate_to_page(String pageTitle) throws Throwable {
        B2BRegisterPage b2BRegisterPage = new B2BRegisterPage();
        assertThat(b2BRegisterPage.verifyRegistrationLandingPage(pageTitle)).isTrue();
    }

    @And("^The AutoUser(\\d+) provide username and password$")
    public void The_AutoUser_provide_username_and_password(int arg1) throws Throwable {
       b2BProductListsPage.provideAutoUser2LoginDetails();
    }

    @And("^The user clicks on \"([^\"]*)\" option from PLP$")
    public void The_user_clicks_on_option_from_PLP(String addToBasket) throws Throwable {
        b2BProductListsPage.addToBasket(addToBasket);
    }

    @When("^The user clicks on \"([^\"]*)\" from PLP$")
    public void The_user_clicks_on_from_PLP(String linkName) throws Throwable {
    	if(linkName.equalsIgnoreCase("Current Order")){
    		b2BProductListsPage.goToCurrentOrderFromMiniBasketPanel();
    	}
        b2BProductListsPage.onClickAAHMiniCart();
    }
}
