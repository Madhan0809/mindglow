package com.salmon.test.stepdefinitions.b2b.myaccount;
import com.salmon.test.pageobjects.b2b.myaccount.B2BManageUsersPage;
import com.salmon.test.pageobjects.b2b.myaccount.B2BMyAccountYourDetailsPage;
import com.salmon.test.stepdefinitions.b2b.profilelanding.B2BAccountDetailsSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class B2BManageUsersPageSteps {

    private static final Logger LOG = LoggerFactory.getLogger(B2BManageUsersPageSteps.class);

        private B2BManageUsersPage b2BManageUsersPage;

    public B2BManageUsersPageSteps(B2BManageUsersPage b2BManageUsersPage) {
        this.b2BManageUsersPage = b2BManageUsersPage;
    }
    
    @When("^User click \"(.*?)\" button in user management$")
    public void user_click_button_in_user_management(String linkOrButtonName) throws Throwable{
    	if(linkOrButtonName.equalsIgnoreCase("Add User")){
    		b2BManageUsersPage.clickOnAddUser();
    	}
    	else if(linkOrButtonName.equalsIgnoreCase("Back")){
    		b2BManageUsersPage.clickBackButton();
    	}
    	else 
    		LOG.error("link or button name is not found: "+linkOrButtonName);
    }
    
    @When("^User click on \"(.*?)\" section from My Account page$")
    public void user_click_on_manage_users_link_from_my_account_page(String sectionName) throws Throwable{
    	b2BManageUsersPage.clickSectionFromMyAccount(sectionName);
    	b2BManageUsersPage.dismissAllNotification();
    }
    
    @When("^Search for a new creating user in manage user table$")
    public void search_for_a_new_creating_user_in_manage_user_table() throws Throwable{
        Map<String,String> searchInfo=new HashMap<String,String>(){
            {put("Username",B2BAccountDetailsSteps.newAddedUserName);}
        };
        b2BManageUsersPage.searchUserAccount(searchInfo);
    }
    
    @When("^Input account information to search in manage user page$")
    public void input_account_information_to_search_in_mange_user_page(Map<String,String> searchInfo) throws Throwable{
    	b2BManageUsersPage.searchUserAccount(searchInfo);
    }
    
    @Then("^Check the account info should be found in manage user table$")
    public void check_the_account_info_should_be_should_in_search_result(Map<String,String> accountInfo) throws Throwable{   	
        List<Map<String,String>>tableWithoutAccountInfo=new ArrayList();
        HashMap<String,String> updatedAccountInfo= new HashMap<String,String>();
        updatedAccountInfo.putAll(accountInfo);
        for(Map<String,String>singleRowInfo:b2BManageUsersPage.getAccountTableInfo()){
            LOG.info("Ignore the account name difference during comparison. Actual account name is "+singleRowInfo.get("Account"));
            singleRowInfo.remove("Account");          
            tableWithoutAccountInfo.add(singleRowInfo);
        }    
        if(accountInfo.get("Username").startsWith("random")){         
            updatedAccountInfo.computeIfPresent("Username", (k,v)->B2BAccountDetailsSteps.newAddedUserName);
        }
        assertThat(tableWithoutAccountInfo.contains(updatedAccountInfo)).isTrue();
    }
    
    @Then("^Check account \"(.*?)\" can be searched out in manager user table$")
    public void check_account_can_be_searched_out_in_manage_user_table(String account) throws Throwable{
        assertThat(b2BManageUsersPage.isNameFoundInTable(account)).isEqualTo(true);
    }
    
    @And("^Check empty table content should display in manage user page$")
    public void check_empty_table_content_should_display_in_manage_user_page() throws Throwable{
    	assertThat(b2BManageUsersPage.getAccountTableInfo()).isEqualTo(null);
    }
    
    @When("^User select \"(.*?)\" from action menu for user \"(.*?)\" in manage user page$")
    public void user_select_from_action_menu_for_user_in_manage_user_page(String action, String userName)
            throws Throwable {
        if (userName.startsWith("b2b.")) {
            userName = b2BManageUsersPage.getB2bProp(userName);
        } else if(userName.startsWith("random_NewUserName")){
            userName=B2BAccountDetailsSteps.newAddedUserName;
        }
        if (userName.startsWith("random")&&!userName.startsWith("random_NewUserName")) {
            b2BManageUsersPage.selectDropDownMenuForAnAccount(action);
        } 
            b2BManageUsersPage.selectDropDownMenuByAccountName(action, userName);
    }
    
    @When("^User click account name \"(.*?)\" from account table in user management$")
    public void user_click_account_name_from_account_table_in_user_management(String accountName) throws Throwable{
    	if(accountName.startsWith("b2b.")){
    		accountName=b2BManageUsersPage.getB2bProp(accountName);
    	}
    	b2BManageUsersPage.clickOnAccountFromTable(accountName);
    }
    
    @And("^User click a random account in user management$")
    public void user_click_a_random_account_in_user_management() throws Throwable{
        String randomUserName=b2BManageUsersPage.getAnRandomAccountFromTable();
    	b2BManageUsersPage.clickOnAccountFromTable(randomUserName);
    }
    
    @Then("^Check the account table is in correct format$")
    public void check_the_account_table_is_in_correct_format(List<String> tableHeader) throws Throwable{
    	assertThat(b2BManageUsersPage.getAccountTableHeaders()).isEqualTo(tableHeader);
    }
    
    @Given("^User setup a default account account for reset password testing$")
    public void User_setup_a_default_account_account_for_reset_password_testing(Map<String,String> defaultUserInfo) throws Throwable{
    	String defaultUser=b2BManageUsersPage.getProp("b2b.reset.defaultUser");	
    	if(b2BManageUsersPage.isUserFoundInTable(defaultUser)){
    		LOG.info("There is an default account existed already.");
    	}
    	else{ 		
    		b2BManageUsersPage.clickOnAddUser();
    		b2BManageUsersPage.createUserFromUserManagement(defaultUserInfo);
    	}
    }
    
}
