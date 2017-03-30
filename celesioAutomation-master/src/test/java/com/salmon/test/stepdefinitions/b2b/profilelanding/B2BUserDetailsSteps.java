package com.salmon.test.stepdefinitions.b2b.profilelanding;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.pageobjects.b2b.myaccount.B2BMyAccountYourDetailsPage;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BAccountDetailsPage;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BUserDetailsPage;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class B2BUserDetailsSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BUserDetailsSteps.class);
	private B2BUserDetailsPage b2BUserDetailsPage;
	private static String dynamicPassword;
	
	public B2BUserDetailsSteps(B2BUserDetailsPage userDetailsPage,B2BAccountDetailsPage accountDetailsPage) {
		b2BUserDetailsPage=userDetailsPage;
	}

	@Then("^User details page content should be displayed in correct format$")
	public void user_details_page_content_should_be_displayed_in_correct_format() throws Throwable{
		LOG.info("Verify the header, permission and role assign table in user details page");
		assertThat(b2BUserDetailsPage.canShowPageHeader()).isTrue();
		assertThat(b2BUserDetailsPage.canShowPermission()).isTrue();
		assertThat(b2BUserDetailsPage.canShowRoleAssignmentTable()).isTrue();
	}
	
	@And("^User click link \"(.*?)\" from breadcrumb$")
	public void user_click_link_from_breadcrumb(String linkName) throws Throwable{
		b2BUserDetailsPage.clickBreadCrumb(linkName);
	}
	
	@When("^User click reset password in user details page$")
	public void user_click_reset_password_in_user_details_page() throws Throwable{
		b2BUserDetailsPage.clickResetPasswordLink();
	}
	
	@When("^User input reset password information in reset password page$")
	public void user_input_reset_password_information_in_reset_password_page(Map<String,String>passwordResetInfo) throws Throwable{
		b2BUserDetailsPage.inputPasswordResetInfo(passwordResetInfo);
		b2BUserDetailsPage.clickResetPasswordBtn();
	} 
	
	@And("^User input a new random password in reset password page$")
	public void user_input_a_new_random_password_in_reset_password_page()throws Throwable{
		dynamicPassword="temp"+RandomGenerator.random(5, PermittedCharacters.NUMERIC);
		Map<String,String> passwordInfo = new HashMap<String,String>();	
		passwordInfo.put("Create a Password", dynamicPassword);
		passwordInfo.put("Verify Password",dynamicPassword);
		b2BUserDetailsPage.inputPasswordResetInfo(passwordInfo);
		b2BUserDetailsPage.clickResetPasswordBtn();
	}
	
	@Then("^Check reset successful message should display$")
	public void check_reset_successful_message_should_display() throws Throwable{
		assertThat(b2BUserDetailsPage.canShowPopupOfResetPassword()).isTrue();
	}
	
	@And("^Check validation message in reset password page$")
	public void check_validation_message_in_reset_password_page(List<String>errorMsg)throws Throwable{
		assertThat(b2BUserDetailsPage.getErrorMessage()).isEqualTo(errorMsg);
	}
	
	@Then("^Check reset password header of \"(.*?)\" display in correct format$")
	public void check_reset_password_header_display_in_correct_format(String expectedHeader) throws Throwable{
		assertThat(b2BUserDetailsPage.getResetPasswordPageHeader()).startsWith(expectedHeader.toUpperCase());
	}
	
	
	@Then("^User details info should display as expected format$")
	public void user_details_info_should_display_as_expected(List<String>userDetailsInfo) throws Throwable{
		assertThat(b2BUserDetailsPage.getUserDetails().keySet()).hasSameElementsAs(userDetailsInfo);
	}
	
	@Then("^Check empty table should display in search result$")
	public void check_empty_table_should_display_in_search_result() throws Throwable{
		assertThat(b2BUserDetailsPage.getTableFooterText()).isEqualTo(b2BUserDetailsPage.getB2bProp("empty.table.text"));
	}	
	
	@When("^User save changes in user details page$")
	public void user_save_changes_in_user_details_page() throws Throwable{
		b2BUserDetailsPage.clickSaveChangesButton();
	}
	
	
}
