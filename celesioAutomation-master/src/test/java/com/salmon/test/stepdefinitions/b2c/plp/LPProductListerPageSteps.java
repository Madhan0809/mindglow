package com.salmon.test.stepdefinitions.b2c.plp;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.pageobjects.b2c.plp.LPProductListerPage;
import com.salmon.test.pageobjects.b2c.plp.ProductItemPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LPProductListerPageSteps {
    private LPProductListerPage lpProductListerPage;

    public LPProductListerPageSteps(LPProductListerPage lpProductListerPage) {
        this.lpProductListerPage = lpProductListerPage;
    }

    @Given("^The user is on \"(.*?)\" PLP$")
    public void the_user_is_on_PLP(String category) throws Throwable {
        lpProductListerPage.goToTargetProductListerPage(category);
    }

    @Given("^The user is on \"(.*?)\" PLP page$")
    public void the_user_is_on_PLP_page(String navigationPage) throws Throwable {
        lpProductListerPage.goToFacetNavigationProductListerPage(navigationPage);
    }

    @Then("^Breadcrumb \"(.*?)\" is displayed in PLP$")
    public void breadcrumb_is_displayed_in_PLP(String category) throws Throwable {
        assertThat(lpProductListerPage.getRelevantBreadCrumb()).isEqualTo(category);
    }

    @When("^The user click on the product image$")
    public void the_user_click_on_the_product_image() throws Throwable {
        lpProductListerPage.onClickOfProductImage();
    }

    @When("^The user clicks Add to basket for \"([^\"]*)\" on the PLP$")
    public void The_user_clicks_Add_to_basket_for_on_the_PLP(String productNameValue) throws Throwable {
        lpProductListerPage.onClickOfAddToBasket(productNameValue);
    }

    @When("^The user clicks More Info for \"([^\"]*)\" on the PLP$")
    public void The_user_clicks_More_Info_for_on_the_PLP(String productNameValue) throws Throwable {
        lpProductListerPage.onClickOfMoreInfo(productNameValue);
    }
    @Then("^The user should be navigated to product details page\\(PDP\\)$")
    public void the_user_should_be_navigated_to_product_details_page_PDP() throws Throwable {
        assertThat(lpProductListerPage.isRelevantProductDetailsPage()).isTrue();
    }

    @When("^The user click on the product text$")
    public void the_user_click_on_the_product_text() throws Throwable {
        lpProductListerPage.onClickOfProductName();
    }

    @Then("^The compare text should be changed to \"(.*?)\"$")
    public void the_compare_text_should_be_changed_to(String expCompareTxt) throws Throwable {
        assertThat(lpProductListerPage.isCompareTextChanged(expCompareTxt)).isTrue();
    }

    /*@Then("^The text \"(.*?)\" should be visible before compare button$")
    public void the_text_should_be_visible_before_compare_button(String addedText) throws Throwable {
        assertThat(lpProductListerPage.isAddedTextVisibleWhenTwoOrMoreProductsAddedToCompare(addedText)).isTrue();
    }*/

    @When("^the user clicks on compare button$")
    public void the_user_clicks_on_compare_button() throws Throwable {
        lpProductListerPage.onClickOfCompareButton();
    }

    @Then("^the user should be navigated to \"(.*?)\" compare page$")
    public void the_user_should_be_navigated_to_compare_page(String comparePage) throws Throwable {
        assertThat(lpProductListerPage.getComparePageTitle()).isEqualToIgnoringCase(comparePage);
    }

    @When("^The user clicks on \"(.*?)\"$")
    public void the_user_clicks_on(String view) throws Throwable {
        lpProductListerPage.onClickOfView(view);
    }

    @Then("^The user should be presented with \"(.*?)\" of products$")
    public void the_user_should_be_presented_with_of_products(String view) throws Throwable {
        assertThat(lpProductListerPage.isCorrectViewDisplayed(view)).isTrue();
    }

    @When("^The user selects \"(.*?)\" from sort by list$")
    public void the_user_selects_from_sort_by_list(String sortName) throws Throwable {
        lpProductListerPage.onSelectOfSortBy(sortName);
    }

    @Then("^The products should be displayed in sorted by \"(.*?)\"$")
    public void the_products_should_be_displayed_in_sorted_by(String sortName) throws Throwable {
        List<ProductItemPage> productItems = lpProductListerPage.getProductList();
        switch (sortName) {
            case LloydsPharmacyConstants.NAME_ASCENDING:
                assertThat(productItems).isSortedAccordingTo(
                        (p1, p2) -> p1.getProductName().compareTo(p2.getProductName()));
                break;
            case LloydsPharmacyConstants.NAME_DESCENDING:
                assertThat(productItems).isSortedAccordingTo(
                        (p1, p2) -> p2.getProductName().compareTo(p1.getProductName()));
                break;
            case LloydsPharmacyConstants.PRICE_LOW_TO_HIGH:
            	productItems = lpProductListerPage.getAllProductListWithPrice();
            	assertThat(productItems).isSortedAccordingTo(
                        (p1, p2) -> (p1.getProductPrice()<p2.getProductPrice()) ? -1 : 0);
//                assertThat(productItems).isSortedAccordingTo(
//                        (p1, p2) -> (p1.getProductPrice() < p2.getProductPrice()) ? -1 : (p1.getProductPrice() > p2.getProductPrice()) ? 1 : 0);
                break;
            case LloydsPharmacyConstants.PRICE_HIGH_TO_LOW:
            	productItems = lpProductListerPage.getAllProductListWithPrice();
                assertThat(productItems).isSortedAccordingTo(
                        (p1, p2) -> (p2.getProductPrice() < p1.getProductPrice()) ? -1 : (p2.getProductPrice() > p1.getProductPrice()) ? 1 : 0);
                break;
            default:
                throw new IllegalArgumentException("No such " + sortName + " sorting parameter exists.");
        }
    }

    @Then("^the products are displayed with From_Price (\\d+) and To_Price (\\d+)$")
    public void the_products_are_displayed_with_From_Price_and_To_Price(int fromPrice, int toPrice) throws Throwable {
        List<ProductItemPage> productItems = lpProductListerPage.getProductList();
        for (ProductItemPage productItem : productItems) {
                Double productPrice = productItem.getProductPrice();
                assertThat(productPrice).isGreaterThanOrEqualTo(fromPrice).isLessThanOrEqualTo(toPrice);
            }
    }

    @When("^The user selects on \"(.*?)\" from per page drop down$")
    public void the_user_selects_on_from_per_page_drop_down(String pageSize) throws Throwable {
        lpProductListerPage.onSelectOfPageSize(pageSize);
    }

    @Then("^The user should be presented with \"(.*?)\" products$")
    public void the_user_should_be_presented_with_products(String pageSize) throws Throwable {
        assertThat(lpProductListerPage.verifyPageSize(Integer.valueOf(pageSize))).isTrue();
    }

    //
    @When("^The user selects (\\d+) star rating from facet menu$")
    public void the_user_selects_star_rating_from_facet_menu(int starProduct) throws Throwable {
        lpProductListerPage.onSelectOfReviewStar(starProduct);
    }

    @Then("^The user should be presented with (\\d+) star products only$")
    public void the_user_should_be_presented_with_star_products_only(int starProduct) throws Throwable {
        assertThat(lpProductListerPage.canGetReviewProducts(starProduct)).isTrue();
    }

    @Then("^Clear all link should appear on facet menu$")
    public void clear_all_link_should_appear_on_facet_menu() throws Throwable {
        assertThat(lpProductListerPage.verifyIfClearAllIsDisplayed()).isTrue();
    }

    //
    @Then("^Facet filter should be applied$")
    public void facet_filter_should_be_applied() throws Throwable {
        assertThat(lpProductListerPage.verifyIfFacetFilterIsApplied()).isTrue();
    }

    //
    @When("^The user selects product type \"(.*?)\" from facet menu$")
    public void the_user_selects_product_type_from_facet_menu(String type) throws Throwable {
        lpProductListerPage.onSelectOfProductType(type);
    }

    //
    @When("The user selects brand type \"(.*?)\" from facet menu")
    public void the_user_selects_brand_type_from_facet_menu(String brandName) throws Throwable {
        lpProductListerPage.onSelectOfBrand(brandName);
    }

    @When("^The user selects choose filter item \"(.*?)\" from \"(.*?)\" filter$")
    public void the_user_selects_filter_from_facet_navigation_menu(String filterItem, String filterName) throws Throwable {
        if(filterItem.startsWith("b2c.")){
            filterItem=lpProductListerPage.getB2cProp(filterItem);
        }
        switch(filterName){
          case "size":
        	lpProductListerPage.onSelectOfSizeType(filterItem);
        	break;
          case "product":
        	  lpProductListerPage.onSelectOfProductType(filterItem);
        	  break;
          case "star":	
        	  lpProductListerPage.onSelectOfReviewStar(Integer.parseInt(filterItem));
        	  break;
          case "price":
        	  lpProductListerPage.onSelectOfPriceRangeType(filterItem);
        	  break;
          case "card":
        	  lpProductListerPage.onSelectCardEnabled(filterItem);
        	  break;
          case "special offer":	
        	  lpProductListerPage.onSelectSpecialOffer(filterItem);
        	  break;
          case "brand":
        	  lpProductListerPage.onSelectOfBrand(filterItem);
        	  break;
        }
    }


    @Then("The user should be presented (\\d+) products only")
    public void the_user_should_be_presented_products_only(int expectedProdNumber) throws Throwable {
        assertThat(lpProductListerPage.getSingleActiveFilterProdNumber()).isEqualTo(expectedProdNumber);
    }

    //
    @When("^The user selects price type \"(.*?)\" from facet menu$")
    public void the_user_selects_price_type_from_facet_menu(String priceRangeType) throws Throwable {
        lpProductListerPage.onSelectOfPriceRangeType(priceRangeType);
    }

    @Then("^The user should be presented \"(.*?)\" product types only$")
    public void the_user_should_be_presented_product_types_only(String type) throws Throwable {
        if(type.startsWith("b2c.")){
            type=lpProductListerPage.getB2cProp(type);
        }
        assertThat(lpProductListerPage.canFilterCorrectTypes(type)).isTrue();
    }

    @When("The user enter price type from facet menu")
    public void The_user_enter_price_type_from_facet_menu(@Transpose Map<String, String> userOwnPriceRange) throws Throwable {
        lpProductListerPage.inputProductsInRange(userOwnPriceRange);
    }


    @Then("^The user should be navigated to checkout basket page$")
    public void the_user_should_be_navigated_to_checkout_basket_page() throws Throwable {
        assertThat(lpProductListerPage.isNavigatedToCheckoutPage()).isTrue();
    }

    @Then("^\"(.*?)\" (?:should be added to|are in) mini basket$")
    public void should_be_added_to_mini_basket(String quantity) throws Throwable {
//        assertThat(lpProductListerPage.getNumberOfProductAddedToBasket()).isEqualTo(quantity);
    	assertThat(lpProductListerPage.canShowCorrectQuantityInfoInBasket(quantity)).isTrue();
    }

    @Then("there are (\\d+) facet filters displays from facet menu")
    public void check_elements_from_facet_menu(int elementCount) throws Throwable {
        assertThat(lpProductListerPage.getReviewElementsCount()).isEqualTo(elementCount);
    }

    @Then("there are (\\d+) rows displays from \"(.*?)\" from facet menu")
    public void check_the_rows_within_one_facet_menu(int expectedRowsValue, String facetMenuName) throws Throwable {
        int actualRowValue = lpProductListerPage.getRowOfSingleFacetMenu(facetMenuName);
        assertThat(actualRowValue).isEqualTo(expectedRowsValue);
    }

    @When("The user click on \"(.*?)\" link from \"(.*?)\" facet menu")
    public void the_user_click_on_show_more_or_less_link_from_facet_menu(String linkName, String facetMenuName) throws Throwable {
        lpProductListerPage.clickOnLinkFromFacetMenu(linkName, facetMenuName);
    }

    @Then("the footer and header should be displayed and clickable")
    public void check_footer_and_header_should_be_displayed_and_clickable() throws Throwable {
        assertThat(lpProductListerPage.canGetHeaderAndFooterInfo()).isTrue();
    }

    @When("^The user update product quantity to \"([^\"]*)\" in PLP$")
    public void The_user_update_product_quantity_to_in_PLP(String quantity) throws Throwable {
        lpProductListerPage.updateProductQuantity(quantity);
    }

    @When("^The user selects compare check boxes of \"([^\"]*)\"$")
    public void The_user_selects_compare_check_boxes_of(int noOfProducts) throws Throwable {
        lpProductListerPage.selectCompareCheckBoxesOfProducts(noOfProducts);
    }

    @Then("^Labels/messages \"([^\"]*)\" for relevant \"([^\"]*)\" should be displayed$")
    public void Labels_messages_for_relevant_should_be_displayed(String message, int noOfProducts) throws Throwable {
        assertThat(lpProductListerPage.getMessagesForCompareActions(noOfProducts)).isEqualToIgnoringCase(message);
    }

    @Then("There are totally (\\d+) products in PLP")
    public void check_the_totally_products_count_in_PLP(int expectedCount) throws Throwable {
        assertThat(lpProductListerPage.getAllProductCount()).isEqualTo(expectedCount);
    }

    @When("Clear \"([^\"]*)\" filter from facet menu")
    public void clear_the_specific_filter_from_facet_menu(String filterName) throws Throwable {
        lpProductListerPage.clearActiveFacetFilter(filterName);
    }

    @When("The user clear all filters")
    public void the_user_clear_all_filters() throws Throwable {
        lpProductListerPage.clearAllAppliedFilters();
    }

    @Then("All filters should be cleared")
    public void all_filters_should_be_cleared() throws Throwable {
        assertThat(lpProductListerPage.getActivedFilterAmount()).isEqualTo(0);
    }

    @Then("^\"([^\"]*)\" in product detail on PLP is displayed \"([^\"]*)\"$")
    public void in_product_detail_on_PLP_is_displayed(String detailType, boolean isDisplayed) throws Throwable {
        assertThat(lpProductListerPage.checkIfElementPresent(detailType)).isEqualTo(isDisplayed);
    }
}
