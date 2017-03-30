package com.salmon.test.stepdefinitions.b2c.myAccount;
import com.salmon.test.models.b2c.AccountData;
import com.salmon.test.models.b2c.Basket;
import com.salmon.test.pageobjects.b2c.myAccount.LPReOrderPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class LPReOrderSteps {
    private AccountData accountData;
    private LPReOrderPage lpReOrderPage;

    public LPReOrderSteps(LPReOrderPage lpReOrderPage, AccountData accountData) {
        this.lpReOrderPage = lpReOrderPage;
        this.accountData = accountData;
    }

    @Then("^the Order History Page Item Details consists of$")
    public void the_Order_History_Page_Item_Details_consists_of(List<Basket> basketList) throws Throwable {
        lpReOrderPage.verifyItemDetailsToOrder(basketList.get(0));
    }

    @When("^I select the the item to Order on ReOrder Page$")
    public void I_select_the_the_item_to_Order_on_ReOrder_Page() throws Throwable {
        lpReOrderPage.selectItemToReOrder();
    }

    @When("^I click on Add to basket on ReOrder Page$")
    public void I_click_on_Add_to_basket_on_ReOrder_Page() throws Throwable {
        lpReOrderPage.addToBasket();
    }

    @When("^I click  \"([^\"]*)\" for the current order on My Accounts page$")
    public void I_click_for_the_current_order_on_My_Accounts_page(String reorderORDetails) throws Throwable {
        if (reorderORDetails.equalsIgnoreCase("reorder")) {
            lpReOrderPage.clickOnOrderNo(accountData.getOrderNo());
        }
    }

    @Given("^I update the reorder quantity to \"([^\"]*)\"$")
    public void I_update_the_reorder_quantity_to(String quantity) throws Throwable {
        lpReOrderPage.updateQuantity(quantity);
    }
}
