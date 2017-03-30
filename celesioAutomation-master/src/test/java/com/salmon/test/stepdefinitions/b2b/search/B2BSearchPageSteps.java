package com.salmon.test.stepdefinitions.b2b.search;
import com.salmon.test.pageobjects.b2b.pdp.B2BProductDetailsPage;
import com.salmon.test.pageobjects.b2b.search.B2BSearchPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aettukullapati on 02/10/2015.
 */
public class B2BSearchPageSteps {

    private B2BSearchPage b2BSearchPage;
    private B2BProductDetailsPage b2bProductDetailPage;

    public B2BSearchPageSteps(B2BSearchPage b2BSearchPage) {
        this.b2BSearchPage = b2BSearchPage;
    }

    @And("^The user clicks on the Search button$")
    public void The_user_clicks_on_the_Search_button() throws Throwable {
      b2BSearchPage.clickOnSearchButton();
    }

    @Then("^The search page breadcrumb \"([^\"]*)\" is displayed on search result page$")
    public void The_search_page_breadcrumb_is_displayed_on_search_result_page(String searchBreadcrumb) throws Throwable {
        assertThat(b2BSearchPage.getSearchBreadcrumb()).isEqualTo(searchBreadcrumb);
    }

    @And("^The page header logo is displayed on search result page$")
    public void The_page_header_logo_is_displayed_on_search_result_page() throws Throwable {
        assertThat(b2BSearchPage.isHeaderLogoShown()).isTrue();
    }

    @And("^The page Footer is displayed on search result page$")
    public void The_page_Footer_is_displayed_on_search_result_page() throws Throwable {
        assertThat(b2BSearchPage.isFooterShown()).isTrue();
    }

    @And("^The no results message \"([^\"]*) is showed on search result page$")
    public void The_no_results_message_NonExist_is_showed_on_search_result_page(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @And("^The message of found seach result is showed on search result page$")
    public void The_message_Vype_is_showed_on_search_result_page() throws Throwable {
        assertThat(b2BSearchPage.isRelevantMessageShown()).isTrue();
    }

    @And("^The \"([^\"]*)\" is showed on Left hand navigation on search result page$")
    public void The_is_showed_on_Left_hand_navigation_on_search_result_page(String category) throws Throwable {
        assertThat(b2BSearchPage.getLeftCategoryTitle()).isEqualTo(category);
    }

    @And("^The side bar panel is displayed on Left hand side navigation on search result page$")
    public void The_side_bar_panel_is_displayed_on_Left_hand_side_navigation_on_search_result_page(List<String> sideBarList) throws Throwable {
        assertThat(b2BSearchPage.getSideBarList()).isEqualTo(sideBarList);
    }

    @And("^The dropdown lists is displayed on search result page$")
    public void The_dropdown_lists_is_displayed_on_search_result_page() throws Throwable {
        }

    @When("^The user clicks on \"([^\"]*)\" section$")
    public void The_user_clicks_on_section(String arg1) throws Throwable {
       }

    @Then("^The user should be presented with the \"([^\"]*)\" of products$")
    public void The_user_should_be_presented_with_the_of_products(String arg1) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }


    @And("^The Search Result text is displayed on search result page$")
    public void The_Search_Result_text_is_displayed_on_search_result_page() throws Throwable {
      assertThat(b2BSearchPage.getSearchResultHeadingText()).isTrue();
    }

    @And("^The input box of Search term displays value \"([^\"]*)\"$")
    public void The_input_box_of_Search_term_displays_value(String searchTerm) throws Throwable {
        assertThat(b2BSearchPage.isSearchTermShowedInTextBox(searchTerm)).isTrue();
    }

    @And("^verify that Search again button is displayed$")
    public void verify_that_Search_again_button_is_displayed() throws Throwable {
        assertThat(b2BSearchPage.isSearchButtonDisplayed()).isTrue();
    }

    @And("^verify that Suggestions section is displayed$")
    public void verify_that_Suggestions_section_is_displayed() throws Throwable {
        assertThat(b2BSearchPage.isSuggestionSectionDisplayed()).isTrue();
    }

    @Then("^The no results message \"(.*?)\" is showed on search result page$")
    public void The_no_results_message_NonExist_is_showed_on_search_result_page(String noResult) throws Throwable {
        assertThat(b2BSearchPage.isNoResultShown(noResult)).isTrue();
    }

    @When("^The user clicks on the product image \"([^\"]*)\" on SRP$")
    public void The_user_clicks_on_the_product_image_on_SRP(String productName) throws Throwable {
        b2BSearchPage.clickOnProductImage(productName);
    }

    @When("^The user clicks on the product name of product \"([^\"]*)\"$")
    public void The_user_clicks_on_the_product_name_of_product(String productName) throws Throwable {
        b2BSearchPage.clickOnProductTitle(productName);
    }

    @Then("^Check the product name \"([^\"]*)\" and description displayed in PDP$")
    public void Check_the_product_name_and_description_displayed_in_PDP(String productName) throws Throwable {
        b2bProductDetailPage = new B2BProductDetailsPage();
        assertThat(b2bProductDetailPage.verifyProductNameDisplayed(productName)).isTrue();
    }
}
