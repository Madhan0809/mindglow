package com.salmon.test.stepdefinitions.b2c.search;
import com.salmon.test.pageobjects.b2c.search.LPSearchPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPSearchPageSteps {
    private LPSearchPage lpSearchPage;

    public LPSearchPageSteps(LPSearchPage lpSearchPage) {
        this.lpSearchPage = lpSearchPage;
    }

    @When("^The user inputs \"(.*?)\" in the Search text box$")
    public void the_user_inputs_in_the_Search_text_box(String searchText) throws Throwable {
        lpSearchPage.inputSearchText(searchText);
    }

    @When("^The user clicks the Search button$")
    public void the_user_clicks_the_Search_button() throws Throwable {
        lpSearchPage.onClickofSearchButton();
    }

    @Then("^The search breadcrumb \"(.*?)\" is displayed on search result page$")
    public void the_search_breadcrumb_is_displayed_on_search_result_page(String searchBreadcrumb) throws Throwable {
        assertThat(lpSearchPage.getSearchBreadcrumb()).isEqualTo(searchBreadcrumb);
    }

    @Then("^The no results message \"(.*?)\" is displayed on search result page$")
    public void the_no_results_message_NonExist_is_displayed_on_search_result_page(String noResult) throws Throwable {
        assertThat(lpSearchPage.isNoResultShown(noResult)).isTrue();
    }

    @Then("^The message \"(.*?)\" is displayed on search result page$")
    public void the_message_is_displayed_on_search_result_page(String message) throws Throwable {
        assertThat(lpSearchPage.isRelevantMessageShown(message)).isTrue();
    }

    @Then("^The product name \"(.*?)\" is displayed on search result page$")
    public void the_product_name_is_displayed_on_search_result_page(String name) throws Throwable {
        assertThat(lpSearchPage.getProductName()).isEqualTo(name);
    }

    @Then("^The product price \"(.*?)\" is displayed on search result page$")
    public void the_product_price_is_displayed_on_search_result_page(String price) throws Throwable {
        assertThat(lpSearchPage.getProductPrice()).isEqualTo(price);
    }

    @Then("^The \"(.*?)\" is displayed on Left hand navigation on search result page$")
    public void the_is_displayed_on_Left_hand_navigation_on_search_result_page(String category) throws Throwable {
        assertThat(lpSearchPage.getLeftCategoryTitle()).isEqualTo(category);
    }

    @Then("^The side bar panel is displayed on Left hand navigation on search result page$")
    public void the_side_bar_panel_is_displayed_on_Left_hand_navigation_on_search_result_page(List<String> sideBarList) throws Throwable {
        assertThat(lpSearchPage.getSideBarList()).isEqualTo(sideBarList);
    }

    @Then("^The dropdown list is displayed on search result page$")
    public void the_dropdown_list_is_displayed_on_search_result_page(List<String> dropdownList) throws Throwable {
        assertThat(lpSearchPage.getDropDownList()).isEqualTo(dropdownList);
    }

    @Then("^The header logo is displayed on search result page$")
    public void the_header_logo_is_displayed_on_search_result_page() throws Throwable {
        assertThat(lpSearchPage.isHeaderLogoShown()).isTrue();
    }

    @Then("^The Footer is displayed on search result page$")
    public void the_Footer_is_displayed_on_search_result_page() throws Throwable {
        assertThat(lpSearchPage.isFooterShown()).isTrue();
    }

    @When("^The user clicks the image of prodcut \"(.*?)\"$")
    public void the_user_clicks_the_image_of_prodcut(String productName) throws Throwable {
        lpSearchPage.clickOnProductImage(productName);
    }

    @When("^The user clicks the name of product \"(.*?)\"$")
    public void the_user_clicks_the_name_of_product(String productName) throws Throwable {
        lpSearchPage.clickOnProductTitle(productName);
    }

    @Then("^The products are re-ordered accordingly on search result page$")
    public void the_products_are_re_ordered_accordingly_on_search_result_page(List<String> productList) throws Throwable {
        assertThat(lpSearchPage.getProductList()).isEqualTo(productList);
    }
    
   /* @Then("^The pagination message \"(.*?)\" is displayed on search result page$")
    public void the_pagination_message_is_displayed_on_search_result_page(String pageMessage) throws Throwable {
    	assertThat(lpSearchPage.getPageMessage()).isEqualTo(pageMessage);
    }
    */

    @Then("^The \"(.*?)\" filter title is displayed on search result page$")
    public void the_filter_title_is_displayed_on_search_result_page(String filter) throws Throwable {
        assertThat(lpSearchPage.getFilterTitle()).isEqualTo(filter);
    }

    @Then("^The Facet titles are displayed on search result page$")
    public void the_Facet_titles_are_displayed_on_search_result_page(List<String> facetTitle) throws Throwable {
        assertThat(lpSearchPage.getFacetTitle()).isEqualTo(facetTitle);
    }

    @When("^The user checks on facet \"(.*?)\" on search result page$")
    public void the_user_checks_on_facet_on_search_result_page(String facet) throws Throwable {
        lpSearchPage.onClickofFacet(facet);
    }

    @When("^The user clicks \"(.*?)\" tab on search result page$")
    public void the_user_clicks_tab_on_search_result_page(String tab) throws Throwable {
        lpSearchPage.onClickoFTab(tab);
    }

    @Then("^\"([^\"]*)\" are shown on search result page$")
    public void are_shown_on_search_result_page(String searchType) throws Throwable {
        assertThat(lpSearchPage.getSearchResultLabel(searchType)).contains(searchType);
    }
}
