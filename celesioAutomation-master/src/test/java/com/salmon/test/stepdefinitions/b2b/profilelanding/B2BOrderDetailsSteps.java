package com.salmon.test.stepdefinitions.b2b.profilelanding;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BOrderDetailsPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BOrderDetailsSteps {
	
	private B2BOrderDetailsPage b2bOrderDetailsPage;
	
	public B2BOrderDetailsSteps(B2BOrderDetailsPage orderDetailsPage){
		b2bOrderDetailsPage=orderDetailsPage;
	}
	

	@When("^User click \"(.*?)\" tab from order details page$")
	public void user_click_on_tab_from_order_details_page(String tabName) throws Throwable{
		
	}
	
	@When("^User click on \"(.*?)\" in order details page$")
	public void user_click_on_button_or_link_in_order_details_page(String button) throws Throwable {
		b2bOrderDetailsPage.clickOnButton(button);
	}
	
	@Then("^the order detail summary is display in correct format$")
	public void check_the_shade_panel_is_displayed_in_correct_format(List<String> expectedPanelFormat) throws Throwable{
		assertThat(b2bOrderDetailsPage.getAAHPanel()).hasSameElementsAs(expectedPanelFormat);
	}
	
	@Then("^Check the faux table and the content is displayed in correct format$")
	public void check_the_faux_table_content_displayed_in_correct_format(List<String> expectedTableFormat) throws Throwable{
		assertThat(b2bOrderDetailsPage.getTableHeaders()).hasSameElementsAs(expectedTableFormat);
	}
}