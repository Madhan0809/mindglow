package com.salmon.test.stepdefinitions.b2b.profilelanding;

import java.util.List;
import java.util.Map;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.b2b.OrderDetailsSummaryForm;
import com.salmon.test.pageobjects.b2b.B2BPageLandingModule;
import com.salmon.test.pageobjects.b2b.home.B2BHomePageHeader;
import com.salmon.test.pageobjects.b2b.megamenu.B2BMegaMenu;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BMyBasketPage;
import com.salmon.test.pageobjects.b2b.register.B2BLoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BMyBasketSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BMyBasketPage.class);
	private B2BMyBasketPage b2bMyBasketPage;
	private B2BPageLandingModule b2bPageLandingModule;
	private B2BHomePageHeader aAHHomePageHeader;
	private B2BLoginPage b2BLogin;

	public B2BMyBasketSteps(B2BMyBasketPage myBasketPage,B2BLoginPage b2bLogin,B2BMegaMenu b2bMegaMenu,B2BPageLandingModule pageLandingModule){
		b2bMyBasketPage=myBasketPage;
		b2bPageLandingModule=pageLandingModule;
		b2BLogin=b2bLogin;
	}
	
	@Given("^The user enter \"(.*?)\" from B2B landing page$")
	public void the_user_enter_target_page_from_order_landing_page(String pageName) throws Throwable{
		b2bMyBasketPage.enterLoginLandingPage(b2bPageLandingModule);
		b2bMyBasketPage.clickMyBasketPageView();
	}
	
	@Then("^Check basket \"(.*?)\" is found in my basket page$")
	public void check_target_basket_is_found_in_my_basket_page(String basketName) throws Throwable{
		if(basketName.startsWith("b2b.")){
			basketName=b2bMyBasketPage.getProp(basketName);
		}
		assertThat(b2bMyBasketPage.isBasketFoundInMyBakset(basketName)).isTrue();
	}
	
	@Then("^Check \"(.*?)\" basket has product added in my basket page$")
	public void check_basket_has_product_added_in_my_basket_page(String basketName) throws Throwable{
	    assertThat(b2bMyBasketPage.getTotalPriceForProduct(b2bMyBasketPage.getProp(basketName)).compareTo("0")>0).isTrue();
	}
	
	@Then("The basket \"(.*?)\" cannot be found in my basket page")
	public void check_basket_cannot_be_found_in_my_basket_page(String basketName) throws Throwable{
		if(basketName.startsWith("b2b")){
			basketName=b2bMyBasketPage.getProp(basketName);
		}
		assertThat(b2bMyBasketPage.isBasketFoundInMyBakset(basketName)).isFalse();
	}
	
	@When("^User enter my basket page after login$")
	public void user_enter_my_basket_page_after_login() throws Throwable{
		b2bMyBasketPage.clickMyBasketPageView();
	}
	
	@Then("^Check basket detail information is found in my basket$")
	public void check_basket_detail_information_is_found_in_my_basket(List<String> basketInfo) throws Throwable{
		assertThat(b2bMyBasketPage.getMyBasketAllContent().contains(basketInfo)).isTrue();
	}
	
	@And("^Check basket table header is displayed$")
	public void check_total_count_number_should_keep_consistent_with_basket_details(List<String> tableHeader) throws Throwable{
		assertThat(b2bMyBasketPage.getMyBasketTableHeader()).isEqualTo(tableHeader);
	}
	
	@When("^The user click on \"(.*?)\" for basket \"(.*?)\" from my basket page$")
	public void the_user_click_on_button_or_links_for_basket_from_my_basket_page(String btnOrLinkName, String basketName) throws Throwable{
		b2bMyBasketPage.selectActionDropDown(btnOrLinkName,basketName);
	}
	
	@When("^The user click on \"(.*?)\" from my basket process$")
	public void the_user_click_on_button_or_link_from_my_basket_page(String btnOrLinkName) throws Throwable{
		if(btnOrLinkName.startsWith("b2b.")){
			b2bMyBasketPage.clickOnBasket(b2bMyBasketPage.getProp(btnOrLinkName));
		}
		else if(btnOrLinkName.equals("Create basket")){
			b2bMyBasketPage.clickOnCreateBasketButton();
		}
		else
			LOG.error("\n Not found a button or link with such name: "+btnOrLinkName);
	}
	
	@And("^User cleanup the basket \"(.*?)\" in my basket page$")
	public void user_cleanup_the_basket_in_my_basket_page(String basketToClean) throws Throwable {
		if(basketToClean.startsWith("b2b.")){
		    basketToClean=b2bMyBasketPage.getProp(basketToClean);
		}
	    if (!b2bMyBasketPage.isBasketFoundInHeader(basketToClean)) {
			LOG.info("The basket: " + basketToClean + " is not existed anymore. Skip the step of data-cleaning.");
		} else {
			b2bMyBasketPage.selectActionDropDown("Delete basket", basketToClean);
			b2bMyBasketPage.clickOnDeleteConfirmPopup("Ok");
		}
	}
	
	
	@When("^User select \"(.*?)\" in popup of favourites$")
	public void user_select_in_popup_of_favourites(String optionName) throws Throwable{
		b2bMyBasketPage.selectOptionInFavoirteDialog(optionName);
	}
	
	@And("^User click move to favourite list in popup$")
	public void user_click_move_to_favourite_list_in_popup() throws Throwable{
		b2bMyBasketPage.clickOnAddFavoriteListButton();
	}
	
	@And("^User input favourite list name \"(.*?)\" and click on add to favourites$")
	public void user_input_favorite_list_name_and_click_on_add_to_favourites(String favouritesListName) throws Throwable{
		if(favouritesListName.startsWith("favourite.")){
			favouritesListName=b2bMyBasketPage.getB2bProp(favouritesListName);
		}
		if(!b2bMyBasketPage.selectOptionInFavoirteDialog(favouritesListName)){
		    String newFavouritesListName=favouritesListName+RandomGenerator.random(5, PermittedCharacters.ALPHABETS);
		    LOG.info("Don't find a favourite name on the list. Now create a new list with name: "+newFavouritesListName);
		    b2bMyBasketPage.inputNewFavoriteList(newFavouritesListName); 
		}
		b2bMyBasketPage.clickOnAddFavoriteListButton();
	}
	
	@Then("^Check add to favourite list popup message \"(.*?)\" comes in header$")
	public void check_add_to_favourite_list_popup_message_comes_in_header(String msgText) throws Throwable{
		assertThat(b2bMyBasketPage.canShowAddToFavoriteListMessage(msgText)).isTrue();
	}

	@Then("^Check the basket content \"(.*?)\" is found in my basket$")
	public void verify_the_basket_details_form(String basketName, List<String> singleBasketInfo) throws Throwable {
		LOG.info("Check whether the basket details is equal to the content in my basket.");
	//	List<List<String>> productDetailInfo = productDetailForm.cells(0);
		b2bMyBasketPage.getSpecificBasketInfo(basketName);
		assertThat(b2bMyBasketPage.getSpecificBasketInfo(basketName)).isEqualTo(singleBasketInfo);
	}
	
	@When("^The user input basket detail information and create basket from my basket$")
	public void the_user_input_basket_detail_information_and_create_basket_from_my_basket(Map<String,String>basketInfo){
		b2bMyBasketPage.inputCreateBasketInfoAndCreate(basketInfo);
	}
	
	@Then("^verify the my basket details form$")
	public void verify_the_mybasket_details_form( List<OrderDetailsSummaryForm> myBasketDetails) throws Throwable {
		assertThat(b2bMyBasketPage.isProductDetailInfoFound(myBasketDetails)).isTrue();
	}

	@Then("^user navigated to current order page and checkout basket process is displayed$")
	public void user_navigated_to_current_order_page_and_checkout_basket_process_is_displayed() throws Throwable {
		assertThat(b2bMyBasketPage.verifyCurrentOrderPage()).isTrue();
	}

	@When("^user select \"([^\"]*)\" from action menu from a random basket$")
	public void user_select_from_action_menu_from_a_random_basket(String action) throws Throwable{
	    b2bMyBasketPage.selectActionFromRandomBasket(action);
	}
	
	@When("^user select \"([^\"]*)\" from action menu for \"([^\"]*)\" from my basket page$")
	public void user_select_from_action_menu_for_from_my_basket_page(String actionValue, String basketName) throws Throwable {
		if (basketName.startsWith("b2b.")) {
			basketName = b2bMyBasketPage.getProp(basketName);
		}	
		b2bMyBasketPage.selectActionDropDown(actionValue, basketName);
	}
	
	@Then("^Click \"([^\"]*)\" on delete basket confirmation popup$")
	public void click_on_delete_basket_confirmation_popup(String buttonOption) throws Throwable{
		b2bMyBasketPage.clickOnDeleteConfirmPopup(buttonOption);
//		b2bMyBasketPage.acceptPopupAlert();
	}
	
	@When("^User click on one basket from my basket page$")
	public void User_click_on_from_my_basket_page() throws Throwable {
		b2bMyBasketPage.clickOnBasketName();
	}

	@Then("^user click on remove button from order page$")
	public void user_click_on_remove_button_from_order_page() throws Throwable {
        b2bMyBasketPage.clickOnRemoveButton();
	}

	@And("^verify emptyOrder message$")
	public void verify_emptyOrder_message() throws Throwable {
		assertThat(b2bMyBasketPage.verifyEmptyCurrentOrder()).isTrue();
	}
}
