package com.salmon.test.stepdefinitions.b2b.register;
import com.salmon.test.framework.helpers.DatabaseHelper;
import com.salmon.test.pageobjects.b2b.home.B2BHomePageHeader;
import com.salmon.test.pageobjects.b2b.register.B2BLoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.assertj.core.api.Assertions.assertThat;

public class B2BLoginSteps {
    private static final Logger LOG = LoggerFactory.getLogger(B2BLoginPage.class);
    public B2BLoginPage b2BLoginPage;
    private B2BHomePageHeader b2BHomePageHeader;

    public B2BLoginSteps(B2BLoginPage loginPage, B2BHomePageHeader aAHHomePageHeader) {
        this.b2BLoginPage = loginPage;
        this.b2BHomePageHeader=aAHHomePageHeader;
    }

    @Given("^User login with default multi account$")
    public void user_login_with_default_multi_account() throws Throwable{
        b2BHomePageHeader.gotoHomePage();
        LOG.info("User login with default multi account: "+b2BLoginPage.getProp("b2b.multiAccount.username"));
        b2BHomePageHeader.provideMultiAccountLoginDetails();
        b2BHomePageHeader.clickOnSignInButton();      
        b2BLoginPage.choosenAccountToEnterHomepage();
        b2BLoginPage.dismissAllNotification();
        b2BLoginPage.closeCookiePolicyBar();      
    }
    
    @Given("^User login with user name and password$")
    public void user_login_with_username_and_password() throws Throwable {
        LOG.info("User login with default user account: "+b2BLoginPage.getProp("b2b.username"));
        b2BLoginPage.loginWithDefaultUser();
        b2BLoginPage.dismissAllNotification();
        b2BLoginPage.closeCookiePolicyBar();
    }
    
    @When("^The user is on landing page of entry point$")
    public void the_user_is_on_landing_page_of_entry_point() throws Throwable {
    	b2BLoginPage.goToEntryPointLandingPage();
    }   
    
    @When("^The user clicks on \"(.*?)\" button in login page$")
    public void the_user_click_on_button_in_homepage(String btnName) throws Throwable {
        b2BLoginPage.clickButtonFromAAHHompage(btnName);
    }

    @And("^User login with userId \"(.*?)\" and password \"(.*?)\" from AAH home page$")
    public void user_enters_loginId_and_password_from_homepage(String userName, String password) throws Throwable {
        if(userName.startsWith("b2b.")){
        	LOG.info("\n Try to login with user who has b2b default account user: "+b2BLoginPage.getProp(userName));
        	b2BLoginPage.loginWithUserCredentials(b2BLoginPage.getProp(userName), b2BLoginPage.getProp(password));
        }
        else
        	b2BLoginPage.loginWithUserCredentials(userName, password);
    }
    
    @When("^User input current password \"(.*?)\" new password \"(.*?)\" and \"(.*?)\" in password expired form$")
    public void user_input_password_info_in_expired_password_form(String oldPswd, String newPswd, String verifyPswd) throws Throwable{
    	b2BLoginPage.inputExpriedPasswordFormAndSubmit(oldPswd, newPswd, verifyPswd);
    }
    
    @Then("^Header is displayed for \"(.*?)\" user$")
    public void check_whether_the_logo_in_header_can_be_shown(String userType) throws Throwable{
    	assertThat(b2BLoginPage.canShowAAHHeader(userType)).isTrue();
    }

    @Then("^Login home page will be displayed$")
    public void login_homepage_will_be_displayed() throws Throwable {
        assertThat(b2BLoginPage.canEnterLoginHomePage()).isTrue();
    }

    @Then("^User can see the validation message \"(.*?)\" from \"(.*?)\"")
    public void check_user_can_see_validation_message_from_AAH_Homepage(String validationErrMsg, String pageName) throws Throwable {
		if (pageName.contains("AAH")) {
			assertThat(b2BLoginPage.canViewValidationMessageFromAAH(validationErrMsg)).isTrue();
		} else if (pageName.contains("username")) {
			assertThat(b2BLoginPage.canViewValidationMesssageFromForgotCredentials(validationErrMsg)).isTrue();
		} else if (pageName.contains("password")) {
			assertThat(b2BLoginPage.canViewValidationMesssageFromValidationCode(validationErrMsg)).isTrue();
		} else {
			LOG.error("The page is not supported for now: " + pageName);
		}
    }

    @Then("^The \"(.*?)\" page is displayed$")
    public void check_the_target_page_can_be_displayed(String pageName) throws Throwable {
       assertThat(b2BLoginPage.canAccessTargetPage(pageName)).isTrue();      
    }
    
    @Then("^Password sent to user page content is displayed correctly$")
    public void password_sent_to_user_page_content_is_displayed_correctly() throws Throwable{
        assertThat(b2BLoginPage.canShowPasswordPageContent()).isTrue();
    }
    
    @And("^Continue to login page from password sent page$")
    public void continue_to_login_page_from_password_sent_page() throws Throwable{
        b2BLoginPage.continueToLoginPage();
    }
    
    @Then("^Data cleaning in DB to make account \"(.*?)\" unlocked$")
    public void execute_in_DB_to_make_account_unlocked(String account) throws Throwable{
    	if(account.contains("negative.testuser"))
    		account=b2BLoginPage.getProp("negative.testuser");
    	LOG.info("Data cleaning on user: "+account);
		String sql = "update userreg set status=1 where users_id = (select users_id from userreg where logonid='"+account+"')";
		DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.executeQuery(b2BLoginPage.getProp("db.schedma"), sql, "b2B");
    }
    
    @And("^Set expired password for user \"(.*?)\" in DB$")
    public void set_expired_password_for_user_in_DB(String userName) throws Throwable{
    	LOG.info("Set user account as password expired for user: "+userName);
    	String sql="Update USERREG set PASSWORDEXPIRED = 1 where LOGONID='"+userName+"'";
    	DatabaseHelper dbHelper = new DatabaseHelper();
		dbHelper.executeQuery(b2BLoginPage.getProp("db.schedma"), sql, "b2B");
    }
    
    @When("^User click on \"(.*?)\" from main navigation menu$")
    public void user_click_on_link_from_main_navigation_menu(String linkName) throws Throwable{
    	b2BLoginPage.clickLinkFromMainNavigation(linkName);
    }

    @And("^User input \"(.*?)\" in forgot password page$")
	public void user_input_logon_id_in_forgot_password_page(String userLogonId) throws Throwable {
		if (userLogonId.startsWith("b2b.")) {
			b2BLoginPage.inputLogonIdInForgotPasswordForm(b2BLoginPage.getProp("b2b.username"));
		} else if (userLogonId.startsWith("negative.")) {
			b2BLoginPage.inputLogonIdInForgotPasswordForm(b2BLoginPage.getProp("negative.testuser"));
		} else
			b2BLoginPage.inputLogonIdInForgotPasswordForm(userLogonId);
	}

    @When("^User click on \"(.*?)\" link in forgot password page$")
	public void user_click_on_link_in_forgot_password_page(String linkName) throws Throwable {
		if (linkName.contains("Send me my reset code")) {
			b2BLoginPage.sendValidationCode();
		} else if (linkName.contains("register")) {
			b2BLoginPage.clickOnRegisterLinkFromForgotPasswordPage();
		} else {
			LOG.error("Can't find the target link: " + linkName);
		}
	}
    
    @When("^User enter validation code \"(.*?)\" password \"(.*?)\" verify password \"(.*?)\" and submit$")
    public void user_enter_validation_code_info_in_forgot_password_field(String validationCode, String newPassword, String verifyPassword) throws Throwable{
    	b2BLoginPage.inputAndSubmitValidationCodeInfo(validationCode, newPassword, verifyPassword);
    }
     
    
    @And("^User input \"(.*?)\" in forgot username page$")
    public void user_input_email_address_in_forgot_username_page(String emailAddress) throws Throwable {
        b2BLoginPage.inputEmailAddressInForgotUserNameForm(emailAddress);
    }

    @When("^User click on \"(.*?)\" link in forgot username page")
    public void user_click_on_link_in_forgot_username_page(String linkName) throws Throwable {
        if (linkName.contains("Reminder")) {
            b2BLoginPage.sendReminderInForgotUserNameForm();
        } else if (linkName.contains("Register")) {
            b2BLoginPage.clickOnRegisterLinkFromForgotUsernamePage();
        } else {
            LOG.error("Can't find the target link: " + linkName);
        }
    }

	@When("^User click on continue in important messages page$")
	public void user_click_button_in_important_message_page() throws Throwable {
		b2BLoginPage.clickContinueForImportantMsg();
	}
    
    @When("^The user is on AAH home page with choosing account$")
    public void the_user_is_on_AAH_home_page_with_choosing_account() throws Throwable{
    	b2BLoginPage.choosenAccountToEnterHomepage();
    	user_dismiss_all_notification_in_header();
    	user_close_the_cookie_policy_bar_on_the_page();
    } 
    
    @When("^User dismiss all notification in header$")
    public void user_dismiss_all_notification_in_header() throws Throwable{
    	b2BLoginPage.dismissAllNotification();
    }
    
    @When("^User close the cookie policy bar on the page$")
    public void user_close_the_cookie_policy_bar_on_the_page(){
    	b2BLoginPage.closeCookiePolicyBar();
    }
    
    @Then("^Header for password expired page is displayed$")
    public void check_header_for_password_expired_page_is_displayed() throws Throwable{
    	LOG.error("The header is not implemented yet.");
    	assertThat(b2BLoginPage.canShowPasswordExpiredHeader()).isTrue();
    }
    
}
