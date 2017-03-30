package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.models.b2c.Basket;
import com.salmon.test.pageobjects.b2c.checkout.LPCheckOutBasketPage;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LPCheckOutBasketPageSteps {
    private LPCheckOutBasketPage lpCheckOutBasketPage;

    public LPCheckOutBasketPageSteps(LPCheckOutBasketPage lpCheckOutBasketPage) {
        this.lpCheckOutBasketPage = lpCheckOutBasketPage;
    }

    @Given("^The user is on basket checkout page$")
    public void the_user_is_on_basket_checkout_page() throws Throwable {
        lpCheckOutBasketPage.goToCheckOutBasketPage();
    }

    @Then("^Appropriate basket header details should be displayed$")
    public void appropriate_basket_header_details_should_be_displayed(@Transpose List<Basket> checkOutDetails) throws Throwable {
        Basket basket = checkOutDetails.get(0);
        Map<String, String> basketDetailsMap = lpCheckOutBasketPage.getBasketHeaderDetails();
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.BASKET_ITEM_QUANTITY)).isEqualTo(basket.getNoOfBasketItems());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.BASKET_ITEM_PRICE)).isEqualTo(basket.getTotalBasketPrice());
        //Check if delivery banner is visible on check out basket page
        assertThat(lpCheckOutBasketPage.isFreeDeliveryBannerVisible()).isTrue();
    }

    @Then("^Appropriate basket items header text labels should be displayed$")
    public void appropriate_basket_items_header_text_labels_should_be_displayed(List<String> headerTextLabels) throws Throwable {
        assertThat(lpCheckOutBasketPage.getBasketHeaderTextLabels()).isEqualTo(headerTextLabels);
    }

    @Then("^Appropriate basket items should be displayed$")
    public void appropriate_basket_items_should_be_displayed(@Transpose List<Basket> checkOutDetails) throws Throwable {
        Basket basket = checkOutDetails.get(0);
        Map<String, String> basketDetailsMap = lpCheckOutBasketPage.getBasketItemDetails();
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ITEM_NAME)).isEqualTo(basket.getItemName());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ITEM_QUANTITY)).isEqualTo(basket.getItemQuantity());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ITEM_PRICE)).isEqualTo(basket.getEachItemPrice());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.TOTAL_PRICE_BEFORE_DISCOUNT)).isEqualTo(basket.getTotalPrice());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.DELIVERY_OPTIONS)).isEqualTo(basket.getDeliveryOptions());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ORDER_SUB_TOTAL_PRICE)).isEqualTo(basket.getOrderSubTotal());
        //TODO:Waiting for dev to finish
//        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.VAT_RELIEF)).isEqualTo(basket.getVatRelief());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.DISCOUNT)).isEqualTo(basket.getDiscount());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ORDER_TOTAL_PRICE)).isEqualTo(basket.getOrderTotal());
    }

    @When("^The user updates item quantity to \"(.*?)\"$")
    public void the_user_updates_item_quantity_to(String quantity) throws Throwable {
        lpCheckOutBasketPage.updateItemQuantity(quantity);
    }

    @Then("^Order details should be updated across the page$")
    public void Order_details_should_be_updated_across_the_page(@Transpose List<Basket> checkOutDetails) throws Throwable {
        Basket basket = checkOutDetails.get(0);
        Map<String, String> basketDetailsMap = lpCheckOutBasketPage.getBasketItemDetailsAfterUpdate();
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ITEM_QUANTITY)).isEqualTo(basket.getItemQuantity());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.TOTAL_PRICE_BEFORE_DISCOUNT)).isEqualTo(basket.getTotalPrice());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ORDER_SUB_TOTAL_PRICE)).isEqualTo(basket.getOrderSubTotal());
        //TODO:Waiting for dev to finish
//        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.VAT_RELIEF)).isEqualTo(basket.getVatRelief());
//        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.DISCOUNT)).isEqualTo(basket.getDiscount());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.ORDER_TOTAL_PRICE)).isEqualTo(basket.getOrderTotal());
        assertThat(basketDetailsMap.get(LloydsPharmacyConstants.MINI_BASKET_TOTAL)).isEqualTo(basket.getMiniBasket());
    }

    @Then("^Popup should be displayed with the relevant message$")
    public void popup_should_be_displayed_with_the_relevant_message() throws Throwable {
        assertThat(lpCheckOutBasketPage.getPopupMessage()).isEqualTo(lpCheckOutBasketPage.getB2cProp("checkout.messagePopup"));
        lpCheckOutBasketPage.closePopup();
    }

    @When("^The user add an item to the basket$")
    public void the_user_add_an_item_to_the_basket() throws Throwable {
        lpCheckOutBasketPage.addItemToBasket();
    }

    @When("^The user removes \"(.*?)\" from basket$")
    public void the_user_removes_from_basket(String item) throws Throwable {
        lpCheckOutBasketPage.removeItemFromBasket(item);
    }

    @Then("^The user should see empty shopping cart message$")
    public void the_user_should_see_empty_shopping_cart_message() throws Throwable {
        assertThat(lpCheckOutBasketPage.getEmptyBasketMessage()).isEqualToIgnoringCase(
                lpCheckOutBasketPage.getB2cProp("checkout.basketEmptyMsg"));
    }

    @When("^The user enters \"(.*?)\" promotional code$")
    public void the_user_enters_promotional_code(String promoCode) throws Throwable {
        lpCheckOutBasketPage.enterPromotionalCode(promoCode);
    }

    @When("^The user clicks on \"(.*?)\" button$")
    public void the_user_clicks_on__button(String checkout) throws Throwable {
        lpCheckOutBasketPage.onClickOfBasketPageActions(checkout);
    }

    @Then("^Appropriate empty promo code message should be displayed$")
    public void appropriate_empty_promo_code_message_should_be_displayed() throws Throwable {
        assertThat(lpCheckOutBasketPage.getEmptyPromoCodeMessage()).isEqualTo(lpCheckOutBasketPage.getB2cProp("checkout.emptyPromoCodeMsg"));
    }

    @Then("^Popup should be displayed with the relevant error message$")
    public void popup_should_be_displayed_with_the_relevant_error_message() throws Throwable {
        assertThat(lpCheckOutBasketPage.getPopupMessage()).isEqualTo(lpCheckOutBasketPage.getB2cProp("checkout.promotionError"));
    }

    @Then("^The user should be navigated to \"(.*?)\" page$")
    public void the_user_should_be_navigated_to_page(String page) throws Throwable {
        if ("Checkout signIn".equals(page)) {
            assertThat(lpCheckOutBasketPage.isNavigatedToRelevantCheckOutSignInPage()).isTrue();
        } else if ("Home".equals(page)) {
            assertThat(lpCheckOutBasketPage.isNavigatedToRelevantHomePage()).isTrue();
        }
    }

    @When("^The user close a popup panel$")
    public void the_user_clicks_on_in_a_popup_panel() throws Throwable {
        lpCheckOutBasketPage.onClickOfClosePopupPanel();
//        lpCheckOutBasketPage.closePopup();
    }

    @Then("^The popup panel should be closed$")
    public void the_popup_panel_should_be_closed() throws Throwable {
        assertThat(lpCheckOutBasketPage.isPopUpPanelClosed()).isTrue();
    }

    @Given("^The user is on basket checkout page as a registered user$")
    public void The_user_is_on_basket_checkout_page_as_a_registered_user() throws Throwable {
        lpCheckOutBasketPage.goToCheckOutSignInPageAsARegisteredUser();
    }

    @And("^Check Item Description on basket checkout page \"([^\"]*)\"$")
    public void Check_Item_Description_on_basket_checkout_page(String itemDescription) throws Throwable {
        lpCheckOutBasketPage.verifyItemDescription(itemDescription);
    }
}
