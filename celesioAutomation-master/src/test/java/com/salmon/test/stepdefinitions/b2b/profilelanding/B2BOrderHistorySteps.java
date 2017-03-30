package com.salmon.test.stepdefinitions.b2b.profilelanding;

import java.util.List;
import java.util.Map;

import com.salmon.test.pageobjects.b2b.profilelanding.B2BOrderHistoryPage;
import com.salmon.test.stepdefinitions.b2b.checkout.B2BCheckOutBasketSteps;

import static org.assertj.core.api.Assertions.assertThat;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class B2BOrderHistorySteps {
	
	private B2BOrderHistoryPage b2bOrderHistoryPage;
	
	public B2BOrderHistorySteps(B2BOrderHistoryPage orderHistoryPage){
		b2bOrderHistoryPage=orderHistoryPage;
	}
	

	@Then("^Check the title of \"(.*?)\" can be displayed correctly$")
    public void check_the_title_of_sent_orders_can_be_displayed_correctly(String expectedTitle) throws Throwable{
		assertThat(b2bOrderHistoryPage.getPageTitle()).isEqualTo(expectedTitle);
	}
	
	@Then("^Check the order history records is displayed accordingly in table$")
	public void check_the_order_history_records_is_displayed_accordingly_in_table() throws Throwable{
		assertThat(b2bOrderHistoryPage.getOrderHistoryTableContent().size()>0).isTrue();
	} 
	
	@Then("^Check the new order reference is found in table$")
	public void check_order_reference_is_found_in_table() throws Throwable{
	    List<String>customRef=b2bOrderHistoryPage.getColumnsOfTable("Customer Reference");
	    assertThat(customRef).contains(B2BCheckOutBasketSteps.referenceData);
	}
	
	@Then("^Check the order list table display in correct format$")
	public void check_the_order_list_table_display_in_correct_format(List<String> orderListTemplate) throws Throwable{
		assertThat(b2bOrderHistoryPage.getOrderTableHeader()).isEqualTo(orderListTemplate);
	}
	
	@When("^Input search order info and search for order$")
	public void input_search_order_info_and_search_for_order(Map<String,String> searchOrderInfo) throws Throwable{
		b2bOrderHistoryPage.searchUserAccount(searchOrderInfo);
	}
	
	@When("^User click link \"(.*?)\" for order \"(.*?)\" in sent order page$")
	public void user_click_link_for_order_in_sent_order_page(String linkName, String orderNumber) throws Throwable{
		b2bOrderHistoryPage.clickLinkInTable(linkName, orderNumber);
	}
	
	@When("^User clicks a status link of \"(.*?)\" order in sent order table$")
	public void user_clicks_a_link_of_failure_order_in_sent_order_table(String status) throws Throwable{
	    b2bOrderHistoryPage.openStatusLinkForFailure(status);
	}
	
	@When("^User resend a order in current order history table$")
	public void user_resend_a_order_in_current_order_history_table() throws Throwable{
		b2bOrderHistoryPage.resendAnOrderInCurrentTable();
	}
	
	@When("^User select \"(.*?)\" for order in sent order page$")
	public void user_click_link_for_order_in_sent_order_page (String optionName) throws Throwable{
		b2bOrderHistoryPage.selectDropdownListInOrderHistory(optionName);
	}
	
	@And("^Check the \"(.*?)\" message popup comes in sent order page$")
	public void check_the_message_popup_comes_in_sent_order_page(String message) throws Throwable{
		assertThat(b2bOrderHistoryPage.getStatusFromPopupOverlay()).contains(message);
	}
	
	@When("^User click order \"(.*?)\" in sent order page$")
	public void user_click_order_in_sent_order_page(String orderNumber) throws Throwable{
		if(orderNumber.startsWith("b2b.")){
		   orderNumber=b2bOrderHistoryPage.getProp(orderNumber);
		}	    
	    b2bOrderHistoryPage.clickOrderInTable(orderNumber);
	}
	
	@When("^User click a order link in sent order data table$")
	public void user_click_a_order_link_in_sent_order_data_table() throws Throwable{
	    b2bOrderHistoryPage.clickOrderInTable();
	}
	
	@When("^User selects \"(.*?)\" from selector in sent order page$")
	public void user_selects_option_from_selector_in_sent_order_page(String optionName) throws Throwable{
		throw new PendingException();
	}
	
	@Then("^The date of all orders in current list is equals to today$")
	public void the_date_of_all_orders_in_current_list_is_equal_to_today() throws Throwable{
		List<String> sentDates=b2bOrderHistoryPage.getSentDateFromCurrentTable();
		for(String date: sentDates){
			String formatedDateInTable=date.substring(0, date.indexOf(" "));
			assertThat(b2bOrderHistoryPage.getTodaysDate().equals(formatedDateInTable)).isTrue();
		}
	}
	
	
	

}
