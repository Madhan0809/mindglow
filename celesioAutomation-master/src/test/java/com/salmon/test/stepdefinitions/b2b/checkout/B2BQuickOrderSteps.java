package com.salmon.test.stepdefinitions.b2b.checkout;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.salmon.test.pageobjects.b2b.checkout.B2BQuickOrderPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class B2BQuickOrderSteps {
	private B2BQuickOrderPage b2bQuickOrderPage;
	 
	public B2BQuickOrderSteps(B2BQuickOrderPage quickOrderPage){
		b2bQuickOrderPage=quickOrderPage;
	 }
	
	 @When("^User select \"(.*?)\" from main navigation$")
	 public void user_select_menu_from_header(String linkName) throws Throwable{
		b2bQuickOrderPage.selectLinkFromMainNav(linkName);	 
	 } 	
	
	 @Then("^Check whether can display correct page content in quick order$")
	 public void check_basket_instruction_and_espot_should_display_in_quick_order_page() throws Throwable{
		 assertThat(b2bQuickOrderPage.canDisplayDefTextAndeSpotFromQuickOrder()).isTrue();
	 }
	 
	 @Then("^Check the form header is displayed as expected$")
	 public void check_the_form_header_is_displayed_as_expected(List<String> header) throws Throwable{
		 assertThat(b2bQuickOrderPage.getQuickOrderFormHeader()).isEqualTo(header);
	 }
	 
	 @And("^User click on upload list in quick order page$")
     public void user_click_on_upload_list_in_quick_order_page() throws Throwable{
		b2bQuickOrderPage.uploadCSVFile();
	 }
	
	 @When("^The user enters SKU codes \"(.*?)\" and quantities \"(.*?)\" in quick order and add to basket$")
	 public void the_user_enters_sku_codes_and_quantities_in_quick_order(String skuNumber, String quantity) throws Throwable{
		 if(skuNumber.startsWith("quick.order.sku")){
			 skuNumber=b2bQuickOrderPage.getProp(skuNumber);
		 }
		 b2bQuickOrderPage.addQuickOrderInfoIntoBasket(skuNumber, quantity);
	 }
	 
	 @Then("^Check popup messages in checkout basket process \"(.*?)\"$")
	 public void check_popup_messages_in_checkout_basket_process(String validationMsg) throws Throwable{
		 assertThat(b2bQuickOrderPage.getPopupMessageFromQuickOrder()).isEqualTo(validationMsg);
	 }
}
