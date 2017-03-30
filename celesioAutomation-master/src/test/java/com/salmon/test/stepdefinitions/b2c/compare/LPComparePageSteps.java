package com.salmon.test.stepdefinitions.b2c.compare;
import com.salmon.test.pageobjects.b2c.compare.LPComparePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPComparePageSteps {
    private LPComparePage lpComparePage;

    public LPComparePageSteps(LPComparePage lpComparePage) {
        this.lpComparePage = lpComparePage;
    }

    @Then("^The compare page opens$")
    public void the_compare_page_opens() throws Throwable {
        assertThat(lpComparePage.isCompareShown()).isTrue();
    }

    @Then("^The header logo is displayed on compare page$")
    public void the_header_logo_is_displayed_on_compare_page() throws Throwable {
        assertThat(lpComparePage.isHeaderLogoShown()).isTrue();
    }

    @Then("^The Footer is displayed on compare page$")
    public void the_Footer_is_displayed_on_compare_page() throws Throwable {
        assertThat(lpComparePage.isFooterShown()).isTrue();
    }

    @Then("^The \"(.*?)\" text label is displayed on compare page$")
    public void the_text_label_is_displayed_on_compare_page(String textLabel) throws Throwable {
        assertThat(lpComparePage.gettextLabel(textLabel)).isEqualTo(textLabel);
    }

    @When("^The user clicks on \"(.*?)\" text label$")
    public void the_user_clicks_on_text_label(String textLabel) throws Throwable {
        lpComparePage.onClickOftextLabel(textLabel);
    }

    @Then("^The PDP of \"(.*?)\" opens$")
    public void the_PDP_of_opens(String product) throws Throwable {
        assertThat(lpComparePage.verifyPDPHeader()).isEqualTo(product);
    }

    @Then("^The user clicks on \"(.*?)\" to go back$")
    public void the_user_clicks_on_to_go_back(String category) throws Throwable {
        lpComparePage.onClickOfSubCategory(category);
    }

    @Then("^The Recently Viewed panel is displayed on compare page$")
    public void the_panel_is_displayed_on_compare_page() throws Throwable {
        assertThat(lpComparePage.isRecentViewPanelDisplayed()).isEqualTo(true);
    }

    @Then("^The user opens Accessories PLP page$")
    public void the_user_opens_Accessories_PLP_page() throws Throwable {
        lpComparePage.onClickOfAccessories();
    }

    @Then("^The mini basket has \"(.*?)\" added$")
    public void the_mini_basket_has_added(String items) throws Throwable {
        assertThat(lpComparePage.getMiniBasket()).isEqualTo(items);
    }

    @When("^The user removes the \"(.*?)\" product on compare page$")
    public void the_user_removes_the_product_on_compare_page(String num) throws Throwable {
        lpComparePage.onClickOfRemove(num);
    }

    @Then("^The product attributes are displayed$")
    public void The_product_attributes_are_displayed(List<String> attributeList) throws Throwable {
        assertThat(lpComparePage.getAttributeList()).hasSameElementsAs(attributeList);
    }

    @And("^Appropriate text labels are displayed on compare page$")
    public void Appropriate_text_labels_are_displayed_on_compare_page(List<String> labels) throws Throwable {
        assertThat(lpComparePage.gettextLabels()).isEqualTo(labels);
    }

    @And("^The user clicks on \"([^\"]*)\" in compare page$")
    public void The_user_clicks_on_in_compare_page(String addToBasket) throws Throwable {
        lpComparePage.onClickOfAddToBasket();
    }

    @When("^The user update product quantity to \"([^\"]*)\" in compare page$")
    public void The_user_update_product_quantity_to_in_compare_page(String quantity) throws Throwable {
        lpComparePage.updateProductQuantity(quantity);
    }

    @When("^The user selects the products to compare$")
    public void The_user_selects_the_products_to_compare(List<String> products) throws Throwable {
        lpComparePage.onSelectionOfProductsToCompare(products);
    }

    @When("^The user clicks on \"([^\"]*)\" product on the compare page$")
    public void The_user_clicks_on_product_on_the_compare_page(String product) throws Throwable {
        lpComparePage.onClickOfProduct(product);
    }

    @And("^The user clicks on compare button$")
    public void The_user_clicks_on_compare_button() throws Throwable {
        lpComparePage.onClickOfCompareButton();
    }

    @Then("^The \"([^\"]*)\" product should be removed$")
    public void The_product_should_be_removed(String product) throws Throwable {
        assertThat(lpComparePage.isProductRemoved(product)).isTrue();
    }

    @When("^The user removes all the products on compare page$")
    public void The_user_removes_all_the_products_on_compare_page() throws Throwable {
        lpComparePage.removeAllTheProducts();
    }

    @Then("^The empty message on the compare page shall be displayed$")
    public void The_empty_message_on_the_compare_page_shall_be_displayed() throws Throwable {
        assertThat(lpComparePage.getEmptyCompareMessage()).isEqualTo(lpComparePage.getB2cProp("checkout.emptyCompare"));
    }
}
