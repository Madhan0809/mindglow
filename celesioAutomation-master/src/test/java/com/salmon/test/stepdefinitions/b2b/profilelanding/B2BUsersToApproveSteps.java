package com.salmon.test.stepdefinitions.b2b.profilelanding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.pageobjects.b2b.profilelanding.B2BUsersToApprovePage;
import com.salmon.test.pageobjects.b2b.register.B2BRegisterPage;
import com.salmon.test.stepdefinitions.b2b.myaccount.B2BManageUsersPageSteps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class B2BUsersToApproveSteps {

	private static final Logger LOG = LoggerFactory.getLogger(B2BManageUsersPageSteps.class);
	private B2BUsersToApprovePage b2bUsersToApprovePage;
    private B2BRegisterPage b2BRegisterPage;
	
	public B2BUsersToApproveSteps(B2BUsersToApprovePage b2bUsersToApprovePage, B2BRegisterPage registerPage) {
		this.b2bUsersToApprovePage = b2bUsersToApprovePage;
		this.b2BRegisterPage = registerPage;
	}
	
	@Then("^Check header should be displayed in users to approve page$")
	public void check_header_should_be_displayed_in_users_to_approve_page() throws Throwable {
        assertThat(b2bUsersToApprovePage.canDisplayUsersToApproveHeader()).isEqualTo(true);
	}
	
	@When("^User click view details for \"(.*?)\"$")
	public void user_click_view_details_for(String firstLastName) throws Throwable{
		if (b2bUsersToApprovePage.isUserFoundInTable(firstLastName)) {
			b2bUsersToApprovePage.viewDetailsForUser(firstLastName);
		} else
			throw new Exception("User is not found: " + firstLastName);
	}
	
	@When("^User view details for user \"(.*?)\" in search result$")
	public void user_view_details_for_user_in_search_result(String userName){
		b2bUsersToApprovePage.viewDetailsForUser(userName);
	}
	
	@Then("^Check the header columns of user table displays in correct format$")
	public void check_the_header_columns_of_user_table_displays_in_correct_format(List<String> headers) throws Throwable{
		assertThat(b2bUsersToApprovePage.getTableHeaders()).isEqualTo(headers);
	}
	
	@Then("^Check user info is found in user to approved table$")
	public void check_user_info_is_found_in_user_table(Map<String,String>userInfo)throws Throwable{
		assertThat(b2bUsersToApprovePage.getAllApprovedUserTableInfo().contains(userInfo)).isEqualTo(true);
	}
	
	@Then("^Check name \"(.*?)\" is found in user table$")
	public void check_name_is_found_in_user_table(String name) throws Throwable{
	    assertThat(b2bUsersToApprovePage.getAllNamesInTable()).contains(name);
	}
	
	@When("^User input users waiting for approval info to search$")
	public void user_input_users_waiting_for_approval_info_to_search(Map<String,String> searchInfo) throws Throwable{
		b2bUsersToApprovePage.inputUserDetailsAndSearch(searchInfo);
	}
	
	@And("^Check an empty user table should be displayed$")
	public void check_an_empty_user_table_should_be_displayed() throws Throwable{
		assertThat(b2bUsersToApprovePage.getAllApprovedUserTableInfo()).isEqualTo(null);
	}
	
	@Then("^Check header should be displayed for user \"(.*?)\" in users to approve details page$")
	public void check_header_should_be_displayed_for_user_in_users_to_approve_page(String userName) throws Throwable{
		String expectedTitle="Approval Request for "+userName;
		assertThat(b2bUsersToApprovePage.getOrdersToApprovedDetailsHeader()).isEqualTo(expectedTitle.toUpperCase());
	}
	
	@Then("^Check the content displaying in users to approved details page$")
	public void check_the_content_displaying_in_users_to_approved_details_page(List<String>userInfo) throws Throwable{
		assertThat(b2bUsersToApprovePage.getUsersFormContent()).isEqualTo(userInfo);
	}
	
	@When("^User click on \"(.*?)\" from users to approved details page$")
	public void user_click_on_btn_or_link_from_users_to_approved_details_page(String btnOrLink) throws Throwable {
		if (btnOrLink.equalsIgnoreCase("Approve")) {
			b2bUsersToApprovePage.clickApprovedButton();
		} else if (btnOrLink.equalsIgnoreCase("Reject")) {
			b2bUsersToApprovePage.clickRejectButton();
		} else
			LOG.error("Not found the button or link within the current page: " + btnOrLink);
	}
	
	@When("^Add comments \"(.*?)\" in users approved details page$")
	public void add_comments_in_users_approved_details_page(String comments) throws Throwable{
		b2bUsersToApprovePage.addCommentsForUser(comments);
	}
	
	@Then("^The user \"(.*?)\" cannot be found in users to approved details page$")
	public void the_user_cannot_be_found_in_users_to_approved_details_page(String userAccountName) throws Throwable{
		assertThat(b2bUsersToApprovePage.isUserFoundInTable(userAccountName)).isFalse();
	}
	
}
