package com.salmon.test.stepdefinitions.b2b.profilelanding;

import java.util.List;
import java.util.Map;

import com.salmon.test.pageobjects.b2b.profilelanding.B2BOrderReplyPage;
import static org.assertj.core.api.Assertions.assertThat;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class B2BOrderReplySteps {

	private B2BOrderReplyPage b2BOrderReplyPage;
	private List<String> replyCodes;
	
	public B2BOrderReplySteps(B2BOrderReplyPage orderReplyPage){
		b2BOrderReplyPage=orderReplyPage;
	}
	
	@When("^User view order reply from order details page$")
	public void user_view_order_reply_from_order_details_page()throws Throwable{
		b2BOrderReplyPage.viewOrderReplyFromOrderDetails();
	}
	
	@Then("^Check the order data table is displayed correctly$")
	public void check_the_order_lines_table_displayed_correctly(List<String>dataTableHeaders)throws Throwable{
		assertThat(b2BOrderReplyPage.getTableHeaders()).hasSameElementsAs(dataTableHeaders);
	}
	
	@Then("^Check order details label should be displayed in correct format$")
	public void check_order_details_label_should_be_displayed_in_correct_format(List<String>detailsLabel) throws Throwable{
	    assertThat(b2BOrderReplyPage.getOrderDetailsLabels()).hasSameElementsAs(detailsLabel);
	}
	
	@Then("^Check the order summary should be displayed in correct format$")
	public void check_the_order_summary_should_be_displayed_in_correct_format(List<String>orderSummary) throws Throwable{
		assertThat(b2BOrderReplyPage.getOrderSummaryLabels()).hasSameElementsAs(orderSummary);
	}
	
	@When("^Click on view exceptions only checkbox$")
	public void click_on_view_exceptions_only_checkbox() throws Throwable{
		b2BOrderReplyPage.clickShowExceptionsOnly();
	}
	
	@Then("^Check order reply page header \"(.*?)\" for default order is displayed$")
	public void check_order_reply_page_header_is_displayed(String expectedHeader) throws Throwable{
	    assertThat(b2BOrderReplyPage.getPageHeader().startsWith(expectedHeader)).isTrue();
	}
	
	@Then("^Check all reply codes greater than zero shall be shown$")
    public void check_all_reply_codes_greater_than_zero_shall_be_shown() throws Throwable {
        if (!this.replyCodes.isEmpty()) {
            assertThat(b2BOrderReplyPage.getOrderReplyCodeData().isEmpty()).isTrue();
        } else
            assertThat(b2BOrderReplyPage.getOrderReplyCodeData().isEmpty()).isFalse();
    }
	
	@Then("^Check all data in order reply table is not empty and get reply codes$")
	public void check_all_data_in_order_reply_table_is_not_empty() throws Throwable{
		List<List<String>>tableContent=b2BOrderReplyPage.getAllOrderReplyData();
		for(List<String>item:tableContent){
			assertThat(item.isEmpty()).isFalse();
		}
		this.replyCodes=b2BOrderReplyPage.getOrderReplyCodeData();
	}
}
