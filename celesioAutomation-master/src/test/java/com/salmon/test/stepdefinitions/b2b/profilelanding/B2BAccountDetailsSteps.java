package com.salmon.test.stepdefinitions.b2b.profilelanding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.pageobjects.b2b.myaccount.B2BManageUsersPage;
import com.salmon.test.pageobjects.b2b.myaccount.B2BMyAccountYourDetailsPage;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BAccountDetailsPage;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

public class B2BAccountDetailsSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BAccountDetailsSteps.class);
	private B2BAccountDetailsPage accountDetails;
	private B2BManageUsersPage b2BManageUsersPage;
	public static String newAddedUserName;
	
	public B2BAccountDetailsSteps(B2BAccountDetailsPage accountDetail, B2BManageUsersPage manageUsersPage){
		accountDetails=accountDetail;
		b2BManageUsersPage=manageUsersPage;
	}
	
	@Then("^Check the account name \"(.*?)\" should display in above of the account list$")
	public void check_account_name_should_display_in_above_of_account_list(String accountName) throws Throwable {
		if(accountName.isEmpty()){
			assertThat(accountDetails.getAccountListTableInfo()).isEqualTo(null);
		} else if (accountName.startsWith("b2b.")) {
			assertThat(accountDetails.getAllAccountNamesFromList().contains(accountDetails.getProp(accountName))).isTrue();
		} else
			assertThat(accountDetails.getAllAccountNamesFromList().contains(accountName)).isTrue();
	}
	
	@Then("^Check the account name \"(.*?)\" should display in users table$")
	public void check_the_account_name_should_display_in_users_table(String user) throws Throwable{
		if(user.startsWith("b2b.")){
			user=accountDetails.getB2bProp(user);
		}
		assertThat(accountDetails.getUsersNameInUserTab().contains(user)).isTrue();
	}
	
	@And("^Account logo and content should display in account list$")
	public void check_account_logo_and_content_should_display_in_account_list() throws Throwable{
		assertThat(accountDetails.canShowAllAccountLogo()).isTrue();
		assertThat(accountDetails.canShowAccountTable()).isTrue();
	}
	
	@Given("^Check the option is set to \"(.*?)\" in account preferences$")
	public void Check_the_preference_option_in_account_details(String optName) throws Throwable{
		assertThat(accountDetails.getSelectedPreferenceOption()).isEqualTo(optName);
	}
	
	@When("^User select \"(.*?)\" in account preferences and save changes$")
	public void user_select_option_in_account_preferences_and_save_changes(String preferenceName) throws Throwable{
		accountDetails.selectPreferences(preferenceName);
		accountDetails.saveChangesForApprovals();
	}
	
	@When("^User input \"(.*?)\" and search account in account details page$")
	public void user_input_search_content_and_search_account_in_account_details_page(String searchText) throws Throwable{
		if(searchText.startsWith("b2b.")){
			searchText=accountDetails.getProp(searchText);
		}
		accountDetails.searchAccountInAccountList(searchText);
	}
	
	@When("^User clicks a user from user table$")
	public void user_clicks_a_user_from_user_table() throws Throwable{
	    accountDetails.clickAnRandomUserFromCurrentTable();
	}
	
	@When("^User click user \"(.*?)\" from user table$")
	public void User_click_one_user_from_user_table(String accountName) throws Throwable{
		if(accountName.contains("b2b.multiAccount.accountname")){
			accountName=accountDetails.getB2bProp("b2b.multiAccount.accountname");
		}
		assertThat(accountDetails.clickAccountFromUsersTab(accountName)).isTrue();
	}
	
	@When("^Input account information to search$")
    public void input_account_information_to_search(Map<String, String> searchInfo) throws Throwable {
	    HashMap<String,String> updatedSearchInfo= new HashMap<String,String>();
	    updatedSearchInfo.putAll(searchInfo);
        if (searchInfo.get("Username").startsWith("random")) {
            updatedSearchInfo.replace("Username", newAddedUserName);
        }
        accountDetails.inputUserDetailsToSearch(updatedSearchInfo);
    }
	
	@When("^User clear results in search form$")
	public void user_clear_results_in_search_form() throws Throwable{
		accountDetails.clearSearchResults();
	}
	
	@Then("^Check the account list should display as valid value$")
	public void check_the_account_list_should_display_as_expected() throws Throwable{
		assertThat(accountDetails.getAllAccountNamesFromList()).isNotEmpty();
	}
	
	@Then("^Check empty table content should display$")
	public void check_empty_table_content_should_display() throws Throwable{
		assertThat(accountDetails.getUsersTableContent()).isEqualTo(null);
	}
	
	@Then("^Check if access to \"(.*?)\" page$")
	public void is_target_page_is_shown(String pageBodyName) throws Throwable{
		assertThat(accountDetails.isAccessToAccountOverviewPage(pageBodyName)).isTrue();
	}
	
	@Then("^Check error message in account details page$")
	public void check_error_message_in_account_details_page(List<String>validationMsg) throws Throwable{
		assertThat(accountDetails.getErrorMsgsFromAddUser()).isEqualTo(validationMsg);
	}
	
	@When("^User click view details for account \"(.*?)\"$")
	public void user_click_view_details_for_account(String accountName) throws Throwable{
		if(accountName.startsWith("b2b.")){
			accountName=accountDetails.getProp(accountName);
		}
		accountDetails.clickViewDetailsForAccount(accountName);
	}
	
	@When("^User click view details for any account in account list$")
	public void user_click_view_details_for_any_account_in_account_list() throws Throwable{
	    accountDetails.viewDetailsForAnyAccount();
	}
	
	@Then("^Check a new user is created$")
	public void check_a_new_user_is_created() throws Throwable{
		String newUser=this.newAddedUserName;
		Map<String, String> searchInfo = new HashMap<String, String>() {
			{
				put("Username", newUser);
			}
		};
		b2BManageUsersPage.searchUserAccount(searchInfo);
		assertThat(b2BManageUsersPage.getAccountTableInfo().size()).isEqualTo(1);
	}
	
	@Then("^Check the account name \"(.*?)\" displays in account tab header$")
	public void check_the_account_name_and_header_displays_in_account_tab(String accountName) throws Throwable{
		if(accountName.startsWith("b2b.")){
			accountName=accountDetails.getProp(accountName);
		}
		assertThat(accountDetails.getAccountDetailsHeader()).startsWith(accountName.toUpperCase());
	}
	
	@And("^Account address details is displayed in account tab$")
	public void check_address_details_is_displayed_in_account_tab(List<String> addressDetail) throws Throwable{
		assertThat(accountDetails.getAddressDetails()).isEqualTo(addressDetail);
	}
	
	@When("^User click on Account Details link from My Account page$")
	public void user_click_on_account_details_link_from_my_account_page() throws Throwable{
		accountDetails.clickAccountDetailsFromMyAccount();
	}
	
	@When("^User click on Order approvals with minimum amount$")
	public void user_click_on_order_approvals_with_minimum_amount(Map<String,String> amountDetails) throws Throwable{
		accountDetails.userInputOrderApprovals(amountDetails);
	}
	
	@Then("^The approved required for all orders should be over$")
	public void the_approved_required_for_all_orders_should_be_over(List<String> amountDetails) throws Throwable{
		assertThat(accountDetails.getApprovalsAmount()).isEqualTo(amountDetails);
	}
	
	@And("^User de-select on order approval checkbox$")
	public void user_de_select_on_order_approval_checkbox() throws Throwable{
	    accountDetails.unCheckOrderApproval();
	}
	
	@And("^User save changes with order approvals$")
	public void user_save_changes_with_order_approvals() throws Throwable{
		accountDetails.saveChangesForApprovals();
	}
	
	@And("^User click on \"(.*?)\" tab in account details$")
	public void user_click_on_sub_tab_in_account_details(String tabName) throws Throwable{
		accountDetails.clickOnTab(tabName);
	}
	
	@Then("^Delivery point info is found in table$")
	public void check_delivery_point_table_is_shown(@Transpose DataTable tableHeaders) throws Throwable{
		List<String> expectedTableInfo=tableHeaders.asList(String.class);
		assertThat(accountDetails.getDeliveryTableHeaders()).isEqualTo(expectedTableInfo);     	
	}
	
	@Then("^Check user table content is shown correctly$")
	public void check_user_table_content_is_shown_correctly(@Transpose DataTable tableDetails)throws Throwable{
		List<Map<String,String>> expectedTableInfo=tableDetails.asMaps(String.class, String.class);
		assertThat(accountDetails.getUsersTableContent()).isEqualTo(expectedTableInfo);
	}
	
	@Then("^Check user information is included in table$")
	public void check_user_information_is_included_in_table(@Transpose DataTable tableDetails)throws Throwable{
		List<Map<String, String>> expectedTableInfo = tableDetails.asMaps(String.class, String.class);
		HashMap<String,String> updatedSearchInfo= new HashMap<String,String>();
		for(Map<String,String>singleInfo:expectedTableInfo){
		    updatedSearchInfo.putAll(singleInfo);
		    if(updatedSearchInfo.get("Username").startsWith("random")){
		        updatedSearchInfo.replace("Username", newAddedUserName);
		    }
		}
		assertThat(accountDetails.getUsersTableContent().contains(updatedSearchInfo)).isEqualTo(true);
	}
	
	@Then("^Check user table header columns show correctly$")
	public void check_user_table_header_columns_show_correctly(List<String>tableColumns) throws Throwable{
		assertThat(accountDetails.getUserTableHeader()).isEqualTo(tableColumns);
	}
	
	@When("^User click \"(.*?)\" button in account details$")
	public void user_click_button_in_account_details(String buttonName) throws Throwable{
		accountDetails.clickButtonInAccountDetails(buttonName);
	}
	
	@When("^Assign role of \"(.*?)\" for account \"(.*?)\" to add user$")
	public void assign_role_for_adding_user(String role, String accountName) throws Throwable{
		if(accountName.startsWith("b2b.")){
			accountName=accountDetails.getProp(accountName);
		}
		accountDetails.assignRoleForNewUser(accountName, role);
	}
	
	@When("^Assign role of \"(.*?)\" for any account to add user$")
	public void assign_role_for_any_account_to_add_user(String role) throws Throwable{
	    accountDetails.assignRoleForAnyAccount(role);
	}
	
	@When("^User inputs user details with random username to add$")
    public void user_input_user_details_to_add(Map<String, String> userDetails) throws Throwable {
        HashMap<String, String> convertedDetails = new HashMap<String, String>(userDetails);
        if (userDetails.get("username").startsWith("b2b.")) {
            LOG.info("Creating a user with the default user name: " + accountDetails.getProp(userDetails.get("username")));
            convertedDetails.replace("username", accountDetails.getProp(userDetails.get("username")));
        } else if(userDetails.get("username").isEmpty()){
            LOG.warn("Input user information with an empty user name.");
        } else {          
            newAddedUserName = userDetails.get("username") + RandomGenerator.random(8, PermittedCharacters.ALPHABETS);
            convertedDetails.replace("username", newAddedUserName);
            LOG.info("Creating a user with a random username."+newAddedUserName);
        } 
        accountDetails.inputAddUserDetails(convertedDetails);
    }
	
	@When("^User inputs user details to add$")
	public void user_inputs_user_details_to_add(Map<String, String> userDetails) throws Throwable{
	    accountDetails.inputAddUserDetails(userDetails);
	}
	
	@Then("^Check preferences should be displayed with \"(.*?)\" status$")
	public void check_preferences_should_be_displayed_with_status(String status) throws Throwable{
		if(status.equalsIgnoreCase("GREY")){
			LOG.info("Check whether preferences display in 'grey' status.");
			assertThat(accountDetails.isPreferencesEnabled()).isFalse();
		}
		else if(status.equalsIgnoreCase("ENABLED")){
			LOG.info("Check whether preferences display in 'enabled' status.");
			assertThat(accountDetails.isPreferencesEnabled()).isTrue();
		}
	}

	@When("^User input \"(.*?)\" and search account in add user page$")
	public void user_input_content_and_search_account_in_add_user_page(String searchText) throws Throwable{
		if(searchText.startsWith("b2b.")){
			searchText=accountDetails.getProp(searchText);
		}
		accountDetails.searchAccountInAddUsers(searchText);
	}
	
	@Then("^Account \"(.*?)\" should be displayed in search result$")
	public void account_should_be_displayed_in_search_result(String accountName) throws Throwable{
		if(accountName.startsWith("b2b.")){
			accountName=accountDetails.getProp(accountName);
		}
		assertThat(accountDetails.getAccountNamesForAddUsers()).contains(accountName);
	}
	
	
	@Then("^Check search area displaying status is \"(.*?)\" in account list$")
	public void check_search_area_displaying_status_in_account_list(Boolean shouldDisplay)throws Throwable{
		if(shouldDisplay){
			assertThat(accountDetails.isSearchAreaDisplayed()).isTrue();
		}
		else
		assertThat(accountDetails.isSearchAreaDisplayed()).isFalse();
	}
	
	@Then("^Check breadcrumb within account details$")
	public void check_breadcrumb_within_account_details(List<String> expectedBreadCrumb) throws Throwable{
	    assertThat(accountDetails.getBreadCrumbsContext()).containsAll(expectedBreadCrumb);
	}
	
	@Then("^Check breadcrumb should be displayed correctly$")
	public void check_breadcrumb_should_be_displayed_correctly(List<String> expectedBreadCrumb) throws Throwable{
		assertThat(accountDetails.getBreadCrumbsContext()).isEqualTo(expectedBreadCrumb);
	}
	
	@When("^Click link \"(.*?)\" on breadcrumb$")
	public void click_link_on_breadcroumb(String breadCrumbLink) throws Throwable{
		assertThat(accountDetails.clickBreadCrumb(breadCrumbLink)).isTrue();
	}
	
	
	@Then("^Check add user form content displays in correct format$")
	public void check_add_user_form_content_displays_in_correct_format(List<String> addUserForm) throws Throwable{
		assertThat(accountDetails.getAddUserFormContent()).isEqualTo(addUserForm);
	}
	
	@And("^Check role assignment panel is displayed$")
	public void check_role_assignment_panel_is_displayed() throws Throwable{
		assertThat(accountDetails.canDisplayAssignRolesPanel()).isTrue();
	}
	
}
