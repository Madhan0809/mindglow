package com.salmon.test.pageobjects.b2b.register;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.LoadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class B2BLoginPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BLoginPage.class);

    private By accessWebsiteBtn=By.cssSelector("div.row>a[href*='LoginAccessWebsitesView']");
    private By registerInHeader=By.cssSelector("div.row>a[href*='Registration']");
    private By aahHeaderLogo=By.cssSelector("#logoTools a>img");
    private By signOutBtn=By.cssSelector("div>a#signOut");
    private By dismissAllNtfButton=By.cssSelector("#NotificationMessageArea .close_text,.close_text.dismissAll");
    private By passwordPageHeader = By.cssSelector("#WC_PasswordResetForm_div_2>h1");
    private By passwordContainer=By.cssSelector(".forgot_password_container div[id^='WC_PasswordResetForm']>div:only-child");
    private By cookiePolicyBar=By.cssSelector(".cc_banner.cc_container.cc_container--open");
    private By acceptCookieButton=By.cssSelector(".cc_btn.cc_btn_accept_all");
    private By continueBtn=By.cssSelector("input[value='Continue']");
    
    private By registerBtn = By.cssSelector("#WC_AccountDisplay_links_3");
    private By signInBtn = By.cssSelector("*[value='Sign In']");
    private By logonIdInput = By.cssSelector("input[name='logonId']");
    private By pswdInput = By.cssSelector("input[name='logonPassword']");
    private By forgotPswdArea=By.cssSelector("div.forgotPassword");
    private By forgotPswdLink = By.cssSelector(".forgotPassword>a");
    private By forgotUserNameLink = By.cssSelector(".forgotUsername>a");
    private By continueToLoginBtn=By.cssSelector(".forgot_password_content a");
    private By loginHomePageWrapper = By.cssSelector("#contentWrapper div.homePageWrapper.rowContainer");
    private By validationMsgFromAAH = By.cssSelector("#logonErrorMessage");
    private By mainNavigationMenu=By.cssSelector("#mainNavigationTopLevel>li>a");
    
    private By forgotPasswordForm = By.cssSelector("form#ResetPasswordForm");
    private By sendValidateCodeBtn = By.cssSelector("input[value='Send me my password']");
    private By changePasswordBtn=By.cssSelector("input[value='Change password']");
    private By validationMsgFromForgotCredential = By.cssSelector(".errorMsg,#error_msg");
    private By errorPanel=By.cssSelector(".errorPanel");
    private By oldLogonPasswordForExpired=By.cssSelector("input[name='logonPasswordOld']");
    private By newLogonPasswordForExpired=By.cssSelector("input[name='logonPassword']");
    private By verifyPasswordInputForExpried=By.cssSelector("input[name='logonPasswordVerify']");

    private By accountChoosenPage=By.cssSelector("body#AccountDisplay");
    private By accountSelector=By.cssSelector("select[name='account']");
    private By continueToBtn=By.cssSelector("input.btn.btnSecondary.validateSuccess");
    
    private By sendReminderBtn = By.cssSelector("input[value='Send Reminder']");
    private By registerLink = By.partialLinkText("register");
    private By validationCodeInput=By.cssSelector("input[id*='validationCode_In_ResetPasswordForm']");
    private By newPasswordInput=By.cssSelector("input[id*='logonPassword_In_ResetPasswordForm']");
    private By verifyPasswordInput=By.cssSelector("input[id*='logonPasswordVerify_In_ResetPasswordForm']");
    private By validationMsgFromValidateCode=By.cssSelector(".errorMsg,#error_msg");
    
    public void clickButtonFromAAHHompage(String btnName) {
        if (getB2bProp("login.register").equalsIgnoreCase(btnName)) {
            LOG.info("Click on register button from AAH homepage.");
            waitForExpectedElement(registerBtn).click();
        } else if (getB2bProp("login.signIn").equalsIgnoreCase(btnName)) {
            LOG.info("Click on sign in button from AAH homepage to login");
            waitForExpectedElement(signInBtn).click();
        } else if (getB2bProp("login.forgotpassword").equalsIgnoreCase(btnName)) {
            LOG.info("Click on forgot password link from AAH homepage.");
            waitForExpectedElement(forgotPswdArea).click();
            waitForExpectedElement(forgotPswdLink).click();
        } else if (getB2bProp("login.forgotusername").equalsIgnoreCase(btnName)) {
            LOG.info("Click on forgot username link from AAH homepage.");
            waitForExpectedElement(forgotPswdArea).click();
            waitForExpectedElement(forgotUserNameLink).click();
        } else if(getB2bProp("login.accessWebsite").equalsIgnoreCase(btnName)){
        	LOG.info("Click on access website link from AAH homepage.");
        	waitForExpectedElement(accessWebsiteBtn).click();
        } else {
            LOG.error("No button name exist: " + btnName);
        }
    }

	public void loginWithUserCredentials(String userName, String password) {
		if (userName.contains("b2b.username") || password.contains("b2b.password")) {
			loginWithDefaultUser();
		} else {
			WebElement logonIdField = waitForExpectedElement(logonIdInput);
			logonIdField.clear();
			logonIdField.sendKeys(userName);
			WebElement passwordField = waitForExpectedElement(pswdInput);
			passwordField.clear();
			passwordField.sendKeys(password);
			waitForExpectedElement(signInBtn).click();
		}
	}

	public void loginWithDefaultUser() {
		WebElement logonIdField = waitForExpectedElement(logonIdInput);
		logonIdField.clear();
		logonIdField.sendKeys(getProp("b2b.username"));
		WebElement passwordField = waitForExpectedElement(pswdInput);
		passwordField.clear();
		passwordField.sendKeys(getProp("b2b.password"));
		waitForExpectedElement(signInBtn).click();
	}
    
	public void dismissAllNotification(){
		if(isElementPresent(dismissAllNtfButton)){
			LOG.info("Dismiss for the all notification bar.");
			waitForExpectedElement(dismissAllNtfButton).click();
        } 
    }
	
	public void closeCookiePolicyBar(){
		if(isPresent(cookiePolicyBar)){
			LOG.info("Accept cookie bar in the middle of the page.");
			waitForExpectedElement(acceptCookieButton).click();
		}
	}
    
    public void goToEntryPointLandingPage() {
        LOG.info(" Navigating to entry point landing page. \n");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().to(LoadProperties.getProps().getProperty("b2b.landing.url"));
    }

    public void choosenAccountToEnterHomepage(){
    	if(isElementPresent(accountChoosenPage)){
    		Select accountSelect=new Select(waitForExpectedElement(accountSelector,20));
    		LOG.info("Now select the first account in list by default.\n");
    		accountSelect.selectByIndex(1);
    		waitForExpectedElement(continueToBtn).click();
    	}
    }
    
    public void inputExpriedPasswordFormAndSubmit(String oldPswd, String newPswd, String verifyPswd){
    	waitForExpectedElement(oldLogonPasswordForExpired).clear();
        waitForExpectedElement(oldLogonPasswordForExpired).sendKeys(oldPswd);
        waitForExpectedElement(newLogonPasswordForExpired).sendKeys(newPswd);
        waitForExpectedElement(verifyPasswordInputForExpried).sendKeys(verifyPswd);
        waitForExpectedElement(changePasswordBtn).click();
    }

    public boolean canEnterLoginHomePage() {
        return isElementPresent(loginHomePageWrapper);
    }

    public boolean canShowAAHHeader(String userType){
    	if(userType.contains("unlogged-in")){
    		LOG.info("Check header displaying for a unlogged-in user.");
    		if(!isElementPresent(aahHeaderLogo)){
    			return false;
    		}
    		else if(!isElementPresent(accessWebsiteBtn)||!isElementPresent(registerInHeader)){
    			return false;
    		}
    		return true;
    	}else{
    		LOG.info("Check header displaying for a logged-in user.");
            return !(!isElementPresent(signOutBtn) || !isElementPresent(aahHeaderLogo));
        }
    }
    
	public boolean canShowPasswordExpiredHeader() {
		if (!isElementPresent(aahHeaderLogo)) {
			return false;
		} else if (this.visibilityOfAllElementsLocatedBy(mainNavigationMenu).size() != 6) {
			return false;
		}
		return true;
	}
    
    public boolean canViewValidationMessageFromAAH(String validateMsg) {
        if (isElementPresent(validationMsgFromAAH)) {
        	if (waitForExpectedElement(validationMsgFromAAH).getText().trim().equals(validateMsg)) {
                return true;
            }
        } else if(isElementPresent(validationMsgFromForgotCredential)){
            if (waitForExpectedElement(validationMsgFromForgotCredential).getText().trim().equals(validateMsg)) {
               return true;
            }
        }
		return false;
    }
    
    public void inputAndSubmitValidationCodeInfo(String validationCode, String newPassword, String verifyPassword){
    	waitForExpectedElement(validationCodeInput).sendKeys(validationCode);
    	waitForExpectedElement(newPasswordInput).sendKeys(newPassword);
    	waitForExpectedElement(verifyPasswordInput).sendKeys(verifyPassword);
    	waitForExpectedElement(changePasswordBtn).click();
    }
    

    public boolean canViewValidationMesssageFromForgotCredentials(String validateText) {
		if (!isElementPresent(validationMsgFromForgotCredential)) {
			if (!isElementPresent(errorPanel)) {
				LOG.error("There is no validation message in forgot password page.");
				return false;
			}
			return true;
		} else {
			WebElement validationMsg = waitForExpectedElement(validationMsgFromForgotCredential);
			if (!validationMsg.getText().trim().equals(validateText)) {
				LOG.error("The validation message is not correct. Actual message is: " + validationMsg.getText());
				return false;
			}
			return true;
		}
    }
    
    public boolean canViewValidationMesssageFromValidationCode(String expectedErrorMsg){
    	List<WebElement> errorElementsList = getWebDriver().findElements(validationMsgFromValidateCode); 
    	return errorElementsList.stream().filter(errorMsg -> errorMsg.isDisplayed()&&errorMsg.getText().equals(expectedErrorMsg)).count() != 0;
    }
    
    public boolean canShowPasswordPageContent(){
        if(!isElementPresent(passwordPageHeader)){
           LOG.error("Password page header is not shown.");
           return false;
        } else if(!isElementPresent(passwordContainer)){
            LOG.error("Password page content is not shown.");
            return false; 
        }
        return true;
    }
    
    public void continueToLoginPage(){
        waitForExpectedElement(continueToLoginBtn).click();
    }
    
    public boolean canAccessTargetPage(String pageName) {
        LOG.info("Current page title is "+getWebDriver().getTitle());
    	if(!checkPageTitleContains(pageName)){
    		LOG.error("The page title does not contain: "+pageName);
    		return false;
    	}
    	return getCurrentPageTitle().trim().equals(pageName);
    }

    public void inputLogonIdInForgotPasswordForm(String userLogonId) {
        WebElement forgotPswdForm = waitForExpectedElement(forgotPasswordForm);
        LOG.info("Now input logon id for getting back password.");
        forgotPswdForm.findElement(logonIdInput).clear();
        forgotPswdForm.findElement(logonIdInput).sendKeys(userLogonId);
    }

    public void sendValidationCode() {
        LOG.info("Click on send validation code from forgot password page.");
        waitForExpectedElement(sendValidateCodeBtn).click();
    }

    public void clickOnRegisterLinkFromForgotPasswordPage() {
        LOG.info("Click on send validation code from forgot password page.");
        waitForExpectedElement(registerLink).click();
    }

    public void inputEmailAddressInForgotUserNameForm(String emailAddr) {
        waitForExpectedElement(logonIdInput).clear();
        waitForExpectedElement(logonIdInput).sendKeys(emailAddr);
    }

    public void sendReminderInForgotUserNameForm() {
        waitForExpectedElement(sendReminderBtn).click();
    }

    public void clickOnRegisterLinkFromForgotUsernamePage() {
        LOG.info("Click on send reminder from forgot username page.");
        waitForExpectedElement(registerLink).click();
    }
    
    public void clickLinkFromMainNavigation(String linkName){
    	List<WebElement> mainMenuLinks = visibilityOfAllElementsLocatedBy(mainNavigationMenu);
        for(WebElement link: mainMenuLinks){
        	if(link.getText().trim().equals(linkName)){
        		link.click();
        		break;
        	}
        }
    }
    
	public void clickContinueForImportantMsg() {
		if (isElementPresent(continueBtn)) {
			getWebDriver().findElement(continueBtn).click();
		}
	}
    
}
