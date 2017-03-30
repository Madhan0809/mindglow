package com.salmon.test.stepdefinitions.b2b.profilelanding;
import com.salmon.test.pageobjects.b2b.checkout.B2BCheckOutBasketPage;
import com.salmon.test.pageobjects.b2b.home.B2BHomePageHeader;
import com.salmon.test.pageobjects.b2b.megamenu.B2BMegaMenu;
import com.salmon.test.pageobjects.b2b.plp.B2BProductListsPage;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BOrderWaitToApprovePage;
import com.salmon.test.pageobjects.b2b.register.B2BLoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BOrderWaitToApproveSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BOrderWaitToApproveSteps.class);

	B2BOrderWaitToApprovePage b2BOrderWaitToApprovePage;
	B2BCheckOutBasketPage b2bCheckOutBasketPage;
	B2BHomePageHeader aAHHomePageHeader;
	B2BMegaMenu b2BMegaMenu;
	B2BLoginPage b2BLoginPage;
	B2BProductListsPage b2BProductListsPage;

	public B2BOrderWaitToApproveSteps(B2BLoginPage loginPage,B2BOrderWaitToApprovePage ordersToApprovedPage, B2BCheckOutBasketPage checkOutBasketPage, B2BHomePageHeader homepageHeader, B2BMegaMenu megaMenu) {
		b2BOrderWaitToApprovePage=ordersToApprovedPage;
		b2bCheckOutBasketPage = checkOutBasketPage;
		aAHHomePageHeader=homepageHeader;
		b2BLoginPage=loginPage;
		b2BMegaMenu=megaMenu;
	}
	
	@When("^User click on \"(.*?)\" tab from profile landing page$")
	public void user_click_on_tab_from_profile_landing_page(String tabName) throws Throwable{
		b2BOrderWaitToApprovePage.clickOnOrderWaitingForApproval();
	}
	
	@Then("^Check faux table of order list displays in correct format$")
	public void check_faux_table_of_order_list_displays_in_correct_format(List<String> tableTemplate) throws Throwable{
		assertThat(b2BOrderWaitToApprovePage.getTableHeaderList()).isEqualTo(tableTemplate);
	}
	
	@Given("^The orders number summary is displayed in orders to approved page$")
	public void the_orders_number_summary_is_displayed_in_orders_to_approved_page() throws Throwable{
		
	}
	
	@Then("^Check the order number is keep consistent with the amount in order list$")
	public void check_the_order_number_is_keep_consistent_with_the_amount_in_order_list() throws Throwable{
		
	}
	
	@Given("^User click on \"(.*?)\" in orders to approved page$")
	public void user_click_on_link_in_orders_to_approved_page(String linkName) throws Throwable{
		b2BOrderWaitToApprovePage.clickOnOrder(linkName);
	}

	@And("^User enter a comment \"(.*?)\" of this buyer$")
	public void user_enter_a_comment_of_this_buyer(String comment) throws Throwable{
		b2BOrderWaitToApprovePage.enterComments(comment);
	}

	@And("^User input comments to continue \"(.*?)\" after selecting on action button$")
    public void user_input_comments_to_continue_order_approve_process_if_presented(String operation) throws Throwable{
	    if(operation.startsWith("Approve")){
	        LOG.info("The page shall be shown a confirmation of 'order approved'. No need to input comments.");
	    } else if(operation.startsWith("Reject")){
	        b2BOrderWaitToApprovePage.enterComments("Reject this order.");
	        b2BOrderWaitToApprovePage.clickButtons("Reject");
	    }else{
	        LOG.error("Cannot find proper action: "+operation);
	    }
	}
	
	@Given("^User select \"([^\"]*)\" option in the action menu of order \"([^\"]*)\" from orders waiting for approval page$")
	public void User_select_option_in_the_action_menu_of_order_from_orders_waiting_for_approval_page(String option, String orderLinkName) throws Throwable {
		b2BOrderWaitToApprovePage.selectOptionFromActions(option, orderLinkName);
	}

	@And("^verify the \"([^\"]*)\" message displayed$")
	public void verify_the_message_displayed(String orderMessage) throws Throwable {
		assertThat(b2BOrderWaitToApprovePage.verifyOrderApprovalReqMessage(orderMessage)).isTrue();
	}

	@And("^Add one product to \"([^\"]*)\" basket from PLP$")
	public void Add_one_product_to_basket_from_PLP(String basketName) throws Throwable {
	    if(basketName.startsWith("b2b.")){
	        basketName=b2bCheckOutBasketPage.getProp(basketName);
	        LOG.info("\n basket name ::" + basketName);
        }    
		b2BMegaMenu = new B2BMegaMenu();
		b2BProductListsPage = new B2BProductListsPage();
		b2BMegaMenu.onHoverOfMainMenu("Shop By Category");
		b2BMegaMenu.onHoverOfDepartment("Baby & Children");
		b2BProductListsPage.selectCategory("Baby Changing");
		if (basketName.startsWith("basket.")) {		
			basketName = b2bCheckOutBasketPage.getB2bProp(basketName);
		}
		b2bCheckOutBasketPage.addOneProductIntoTargetBasket(basketName);
		b2bCheckOutBasketPage.closeMiniBasketPopup();
		b2bCheckOutBasketPage.onClickAAHMiniCart();
	}

	@Then("^The user click the AAH mini cart button and verify \"([^\"]*)\" page is displayed$")
	public void The_user_click_the_AAH_mini_cart_button_and_verify_page_is_displayed(String pageName) throws Throwable {
		b2bCheckOutBasketPage.onClickAAHMiniCart();
		assertThat(b2BOrderWaitToApprovePage.canAccessTargetPage(pageName)).isTrue();
	}

	@And("^click on \"([^\"]*)\" button and send for review$")
	public void click_on_button_and_send_for_review(String requestReview) throws Throwable {
//		String productName = b2bCheckOutBasketPage.getProductName();
//		if (productName.contains(b2bCheckOutBasketPage.getB2bProp("huggies.productName"))) {
//			b2bCheckOutBasketPage.updateProductQuantity(b2bCheckOutBasketPage.getB2bProp("huggies.productName"), 10);
//		} else if (productName.contains(b2bCheckOutBasketPage.getB2bProp("yardley.productName"))) {
//			b2bCheckOutBasketPage.updateProductQuantity(b2bCheckOutBasketPage.getB2bProp("yardley.productName"), 10);
//		} else if (productName.contains(b2bCheckOutBasketPage.getB2bProp("rimmel.ProductName"))) {
//			b2bCheckOutBasketPage.updateProductQuantity(b2bCheckOutBasketPage.getB2bProp("rimmel.ProductName"), 10);
//		}
		b2BOrderWaitToApprovePage.clickOnRequestReviewButton(requestReview);
	}

	@Then("^buyer user has to sign-out and sign-in as Approver user$")
	public void buyer_user_has_to_sign_out_and_sign_in_as_Admin_user() throws Throwable {
        aAHHomePageHeader.dismissNotificationArea();
		aAHHomePageHeader.clickOnSignOutButton();
		aAHHomePageHeader.gotoHomePage();
		b2BLoginPage.loginWithUserCredentials(b2BLoginPage.getProp("b2b.approver.username"), b2BLoginPage.getProp("b2b.approver.password"));
	}

	@And("^User go to \"([^\"]*)\" from main menu and click on \"([^\"]*)\" link from profile landing page$")
	public void User_go_to_from_main_menu_and_click_on_link_from_profile_landing_page(String yourProfile, String linkName) throws Throwable {
		b2BOrderWaitToApprovePage.clickLinkFromMainNavigation(yourProfile);
		b2BOrderWaitToApprovePage.clickOnOrderWaitingForApproval();
	}

	@When("^User can choose \"([^\"]*)\" account from accounts dropdown and proceed to home page$")
	public void User_can_choose_account_from_accounts_dropdown_and_proceed_to_home_page(String selectedAccount) throws Throwable {
		if (selectedAccount.equalsIgnoreCase("first")) {
			aAHHomePageHeader.selectAccountDropDownValue(new Integer(1));
		} else if (selectedAccount.equalsIgnoreCase("second")) {
			aAHHomePageHeader.selectAccountDropDownValue(new Integer(2));
		}
		aAHHomePageHeader.clickOnShopCompleteButton(selectedAccount);
	}

	@And("^verify \"([^\"]*)\" confirmation is displayed$")
	public void verify_confirmation_is_displayed(String confirmationMessage) throws Throwable {
		assertThat(b2BOrderWaitToApprovePage.getOrderConfirmationMessage(confirmationMessage)).isTrue();
	}
}
