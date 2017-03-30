package com.salmon.test.pageobjects.b2b.register;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.LoadProperties;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.b2b.RegistrationDetails;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
public class B2BRegisterPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BRegisterPage.class);
    @Getter
    private static Properties runProps;
    private By registerBtn = By.cssSelector(".registerLinkContainer .btn.btnPrimary");
    private By signInBtn = By.cssSelector("a#WC_AccountDisplay_links_2");
    private By registrationLink = By.cssSelector(".row.margin-true div:nth-of-type(2) .textLink");
    private By userName = By.cssSelector("input[name='logonId']");
    private By password = By.cssSelector("input[name='logonPassword']");
    private By verifyPassword = By.cssSelector("input[name='logonPasswordVerify']");
    private By email = By.cssSelector("input[name='email1']");
    private By accountId=By.cssSelector("input[name='accountId']");
    private By firstName = By.cssSelector("input[name='firstName']");
    private By lastName = By.cssSelector("input[name='lastName']");
    private By preferredContact = By.id("primaryTelType");
    private By primaryContactNum=By.name("phone1");
    private By phone = By.cssSelector("input[name='phone1']");
    // private By preferEmail = By.cssSelector("#WC_UserRegistrationAddForm_receiveEmail_div_9_1>div>span");
    private By preferEmail = By.xpath("//label[text()=\"Send me e-mails about store specials\"]");
    private By preferSMSPromotions = By.xpath("//label[text()=\"Send SMS notifications to mobile phone\"]");
    //private By preferSMSPromotions = By.cssSelector("#WC_UserRegistrationAddForm_sendMeSMSPreference_div_12_1>div>span");
    private By preferSMSNotifications = By.xpath("//label[text()=\"Send SMS promotions to mobile phone\"]");
    //private By preferSMSNotifications = By.cssSelector("#WC_UserRegistrationAddForm_sendMeSMSNotification_div_9_1>div>span");
    private By terms = By.cssSelector("label[for='terms']>abbr");
    //    private By terms = By.cssSelector("fieldset>.field.checkboxField>span");
    private By submitRequest = By.cssSelector("input[value='Submit Request']");
    private By labels = By.cssSelector("*[id^='WC_UserRegistrationAddForm'] label:not([class='access'])");
    private By errorMsgs = By.cssSelector(".errorMsg");
    private By userRegErrorMessage  = By.cssSelector("#UserRegistrationErrorMessage");

    private By accessWebsitesLink = By.partialLinkText("Sign In");
    private By registerLink = By.cssSelector("#logoTools a:nth-of-type(2)");
    private By otcOnlineButton = By.xpath(".//*[@id='content']/ul/li[2]/a");
    private By verifySiteAccessPage = By.cssSelector("#content>h1");
    private By requestedSubmitted = By.cssSelector(".title.headingContainer>h1");
  /*private By address1 = By.name("address1");
    private By address2 = By.id("address2");
    private By city = By.id("city");
    private By state = By.id("state");
    private By country = By.id("country");
    private By showPassword = By.id("showPassword");
    private By title = By.name("personTitle");
    private By postcode = By.name("zipCode");
    private By birthDay = By.id("birthDay");
    private By birthMonth = By.id("birthMonth");
    private By birthYear = By.id("birthYear");
    private By gender = By.name("gender");
    private By preferMail = By.id("preferMail");
    private By preferPhone = By.id("preferPhone");
    private By createAccount = By.cssSelector("input[value='Create Account']");
    private By findAddress = By.cssSelector("button.btnPrimary");
    private By manualAddress = By.id("manualAddress");
    private By findPostCodeButton = By.cssSelector(".btnPrimary.findAddressBtn");
    private By selectAddressFromDropDown = By.cssSelector(".addressSelect");*/

    private String userNameData;
    private String emailIdData;
    private String passwordData;

    public void clickButtonFromSignInPage(String btnName) {
        if (getB2bProp("login.register").equalsIgnoreCase(btnName)) {
            LOG.info("Click on register button from homepage.");
            waitForExpectedElement(registerBtn).click();
        } else if (getB2bProp("login.signIn").equalsIgnoreCase(btnName)) {
            LOG.info("Click on sign in button to login");
            waitForExpectedElement(signInBtn).click();
        } else {
            LOG.error("No button name exist: " + btnName);
        }
    }

    public boolean canAccessRegisterPage() {
        LOG.info(" \n Register Page Title ::"+getWebDriver().getTitle());
        if (!getWebDriver().getTitle().equals("Register")) {
            LOG.info(" \n Register Page Title ::"+getWebDriver().getTitle());
            return false;
        }
            return true;
    }

    public boolean verifyRegistrationLandingPage(String pageTitle){
        return checkPageTitleContains(pageTitle);
    }

     public void clickOnRegistrationLink(String regLink){
         if(waitForExpectedElement(registrationLink).getText().equalsIgnoreCase(regLink)) {
             waitForExpectedElement(registrationLink).click();
         }
    }
    public RegistrationDetails enterDetailsOnTheRegistrationPage(RegistrationDetails userDetails) {
        if (userDetails.isGenerateRandomData()) {
            userDetails = createUserRegData(userDetails);
        }
        enterBasicRegistrationDetails(userDetails);
       /* if ((userDetails.getPostCodeFinder() != null)) {
            enterAddressUsingFinder(userDetails);
            verifyFoundAddress(userDetails);
        } else {
            enterAddressManually(userDetails);
        }*/

        enterPreferences(userDetails);
        return userDetails;
    }

    public void enterUserRegisteredInfo(RegistrationDetails userDetails){
    	waitForExpectedElement(userName).sendKeys(userDetails.getUsername()+RandomGenerator.random(5, PermittedCharacters.ALPHABETS));       
        waitForExpectedElement(password).sendKeys(userDetails.getPassword());
        waitForExpectedElement(verifyPassword).sendKeys(userDetails.getVerifyPassword());
        waitForExpectedElement(accountId).sendKeys(getProp("b2b.register.accountId"));
        waitForExpectedElement(firstName).sendKeys(userDetails.getFirstName());
        waitForExpectedElement(lastName).sendKeys(userDetails.getLastName());
        waitForExpectedElement(email).sendKeys(RandomGenerator.randomEmailAddress(6));
        waitForExpectedElement(preferredContact).sendKeys(userDetails.getPreferredContact());
        waitForExpectedElement(primaryContactNum).sendKeys(userDetails.getContactNumber());   
    	enterPreferences(userDetails);
    }
    
    
    public RegistrationDetails createUserRegData(RegistrationDetails userDetails) {
        if (userDetails.isExistingUser()) {
            userNameData = getProp("b2b.username");
            emailIdData = getProp("b2b.email");
            passwordData = getProp("b2b.password");
        } else {
            userNameData = "Auto" + RandomGenerator.random(5, PermittedCharacters.ALPHABETS);
            emailIdData = "Auto" + RandomGenerator.randomEmailAddress(6);
            passwordData = "password1";
        }
        if (userDetails.getAddressType() == null) {
            userDetails.setAddressType("home");
        }
        String addressType = userDetails.getAddressType();
        userDetails.setUsername(userNameData);
        userDetails.setEmail(passwordData);
        userDetails.setPassword(passwordData);
        userDetails.setVerifyPassword(passwordData);
        userDetails.setFirstName(getB2bProp("address.firstName." + addressType));
        userDetails.setLastName(getB2bProp("address.lastName." + addressType));
        userDetails.setOrgAccount(getB2bProp("address.orgAccount."+ addressType));
        userDetails.setPreferredContact("address.preferredContact." + addressType);
        userDetails.setContactNumber("01234567458");
        userDetails.setPreferEmail("Yes");
        userDetails.setPreferSMSNotifications("Yes");
        userDetails.setPreferSMSPromotions("Yes");
        userDetails.setPreferPhone("Yes");
        userDetails.setTermsAccept("Yes");
        return userDetails;
    }

    private void enterBasicRegistrationDetails(RegistrationDetails userDetails) {
        waitForExpectedElement(userName).sendKeys(userNameData);
        waitForExpectedElement(email).sendKeys(emailIdData);
        waitForExpectedElement(password).sendKeys(passwordData);
        waitForExpectedElement(verifyPassword).sendKeys(passwordData);
        waitForExpectedElement(firstName).sendKeys(userDetails.getFirstName() + random(4, ALPHABETS));
        waitForExpectedElement(lastName).sendKeys(userDetails.getLastName() + random(4, ALPHABETS));
        waitForExpectedElement(accountId).sendKeys(userDetails.getOrgAccount());
        waitForExpectedElement(preferredContact).sendKeys(userDetails.getPreferredContact());
        waitForExpectedElement(phone).sendKeys(userDetails.getContactNumber());
      }

    private void enterPreferences(RegistrationDetails userDetails) {
        if (needToCheckTheBox(userDetails.getPreferEmail())) {
            waitForExpectedElement(preferEmail).click();
        }
         if (needToCheckTheBox(userDetails.getPreferSMSNotifications())) {
             waitForExpectedElement(preferSMSNotifications).click();
        }
       if (needToCheckTheBox(userDetails.getPreferSMSPromotions())) {
           waitForExpectedElement(preferSMSPromotions).click();
        }
        if (needToCheckTheBox(userDetails.getTermsAccept())) {
            waitForExpectedElement(terms).click();
        }
    }

    private boolean needToCheckTheBox(String value) {
        return "Yes".equalsIgnoreCase(value);
    }
    
    public void clickOnSubmitRequest(){
       waitForExpectedElement(submitRequest).click();
    }

    public boolean verifyRegistrationConfirmation(String requestSubmitMsg){
        boolean flag = false;
        String confirmation = waitForElementAvailableAndVisible(requestedSubmitted).getText();
        if(confirmation.toLowerCase().contains(requestSubmitMsg.toLowerCase())){
            flag = true;
        }
        return flag;
    }

    public List<String> getTextLabels() {
         List<WebElement> test = visibilityOfAllElementsLocatedBy(labels);
         List<String> labelsList = new ArrayList<String>();
         for(WebElement ele : test){
             labelsList.add(ele.getText());
               }
        labelsList.removeAll(Arrays.asList(null,""));
        LOG.info("\n Labels ::  " + labelsList);
        return labelsList;
    }

    public List<String> getAllErrorMessages() {
        List<WebElement> test = getWebDriver().findElements(errorMsgs);
        List<String> errormsgs = new ArrayList<String>();
        for(WebElement ele : test){
            errormsgs.add(ele.getText());
        }
       System.out.println("\n errormsgs :::"+errormsgs);
        return visibilityOfAllElementsLocatedBy(errorMsgs).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getAppropriateErrorMessage() {
        LOG.info("\n  Register Error Message" + waitForExpectedElement(userRegErrorMessage).getText());
        return waitForExpectedElement(userRegErrorMessage).getText();
    }

    public void gotoAAHPointPage(){
        LOG.info(" Navigating to AAH Enterprise HomePage \n");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().navigate().to(LoadProperties.getProps().getProperty("b2b.url.main"));
    }

    public void clickOnAAHPointLinks(String accessLink){
     if(accessLink.equalsIgnoreCase("Sign In")){
         LOG.info("\n : --- AAHPointLinks Sign button  --- : "+accessLink);
         waitForExpectedElement(accessWebsitesLink).click();
     }
     if(accessLink.equalsIgnoreCase("Register")){
         LOG.info("\n : ---Register button  --- : ");
         waitForExpectedElement(registerLink).click();
     }
    }

    public void selectOTCOnlineButton(){
     waitForExpectedElement(otcOnlineButton).click();
    }

    public boolean verifySiteAccessPage(){
        LOG.info("\n : ---Verify Navigate to Site Access --- : ");
          return waitForExpectedElement(verifySiteAccessPage).isDisplayed();
    }

    public boolean verifySignInPage(String signIn){
        LOG.info( "\n window title :::"+getWebDriver().getTitle());
          return  checkPageTitleContains(signIn);
    }
}
