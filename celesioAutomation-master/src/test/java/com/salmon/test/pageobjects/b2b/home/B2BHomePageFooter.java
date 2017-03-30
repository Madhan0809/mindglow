package com.salmon.test.pageobjects.b2b.home;
import com.salmon.test.constants.b2b.B2bConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.DatabaseHelper;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class B2BHomePageFooter extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BHomePageFooter.class);
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    DatabaseHelper dbHelper;
    private String randomEmail = null;
    private Pattern pattern;
    private Matcher matcher;
    private By subscribeHeader = By.cssSelector("#newsletter p strong");
    private By subscribeContent = By.cssSelector("#newsletter p:nth-of-type(2)");
    private By subscribeEmail = By.cssSelector("#newsletter input[type=email]");
    private By subscribeButton = By.cssSelector("#newsletter input[type=submit]");
    private By footerHeaders = By.cssSelector(".contentRecommendationWidget .row:nth-of-type(1)  ul>li:nth-child(1)");
    private By footerMenus = By.cssSelector(".content.collapsibleTarget.active>li");
    private By thankYouMsg = By.cssSelector("#confirmationMessage");
    private By socialMediaLinks = By.cssSelector("#social li a");
    private By cookiePolicyButton = By.cssSelector(".cc_btn.cc_btn_accept_all");

    public void goToHomePage() {
        LOG.info(" Navigating to AAH Enterprise HomePage \n");
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startB2BHomePage();
        if (isPresent(cookiePolicyButton)) {
            LOG.info("\n ****** cookies button has been closed ******");
            waitForExpectedElement(cookiePolicyButton).click();
        }
    }

    public String getSubscribePart(String subscribePart) {
        String subscribe = "";
        switch (subscribePart) {
            case B2bConstants.SUBSCRIBE_HEADER_TEXT:
                subscribe = waitForExpectedElement(subscribeHeader).getText();
                break;
            case B2bConstants.SUBSCRIBE_CONTENT_TEXT:
                subscribe = waitForExpectedElement(subscribeContent).getText();
                break;
            case B2bConstants.SUBSCRIBE_EMAIL:
                subscribe = waitForExpectedElement(subscribeEmail).getAttribute("placeholder");
                break;
            case B2bConstants.SUBSCRIBE_BUTTON:
                subscribe = waitForExpectedElement(subscribeButton).getAttribute("value");
                break;
        }
        return subscribe;
    }

    public List<String> getFooterHeaderList() {
        List<String> footerHeaderList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(footerHeaders);
        for (WebElement footerHeaders : webElements) {
            footerHeaderList.add(footerHeaders.getText());
        }
        return footerHeaderList;
    }

    public List<String> getFooterMenuList() {
        List<String> footerMenuList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(footerMenus);
        for (WebElement footerMenus : webElements) {
            footerMenuList.add(footerMenus.getText());
        }
        return footerMenuList;
    }

    public List<String> getSocialMediaList() {
        List<String> socialMediaList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(socialMediaLinks);
        //LOG.info(" Social Media Links AAH Enterprise HomePage \n"+socialMediaLinks.getAttribute("href"));
        //LOG.info(" Social Media Links Tag Name AAH Enterprise HomePage \n"+);
        socialMediaList.addAll(webElements.stream().map(socialMediaLinks -> socialMediaLinks.getAttribute("href")).collect(Collectors.toList()));
        return socialMediaList;
    }

    public void inputOfEmailAddress(String emailAddress) {
        randomEmail = getRandomEmail(emailAddress);
        waitForExpectedElement(subscribeEmail).sendKeys(randomEmail);
    }

    public void onClickOfSubscribePart(String subscribePart) {
        switch (subscribePart) {
            case B2bConstants.SUBSCRIBE_EMAIL:
                waitForExpectedElement(subscribeEmail).click();
                break;
            case B2bConstants.SUBSCRIBE_BUTTON:
                waitForExpectedElement(subscribeButton).click();
                break;
        }
    }

    public void onClickOfSubscribeEmailPart(String subscribePart) {
        LOG.info("subscribePart::::::" + subscribePart);
        waitForExpectedElement(subscribeButton).click();
    }

    public String getTypeofEmail() {
        return waitForExpectedElement(subscribeEmail).getAttribute("type");
    }

    public WebElement getThankYouMessage() {
        return waitForExpectedElement(thankYouMsg);
    }

    public boolean getDataBaseValues(String dbTable, String colName, String emailAddress) {
        boolean emailSaved;
        dbHelper = new DatabaseHelper();
        if (emailAddress.equalsIgnoreCase(getB2bProp("valid.email"))) {
            emailAddress = randomEmail;
        }
        emailSaved = dbHelper.selectRecordsFromTable(dbTable, colName, randomEmail, "b2B").equalsIgnoreCase(emailAddress);
        return emailSaved;
    }

    private String getRandomEmail(String email) {
        Random rand = new Random();
        int start = email.indexOf("@");
        int randomNum = rand.nextInt((1000 - 1) + 1) + 1;
        String firstPart = email.substring(0, start);
        String emailValue = firstPart + randomNum;
        StringBuilder sbEmail = new StringBuilder(email);
        return sbEmail.replace(0, start, emailValue).toString();
    }

    public void inputOfInvalidEmailAddress(String invalidEmailAddress) {
        waitForExpectedElement(subscribeEmail).sendKeys(invalidEmailAddress);
    }

    public boolean getSubscribeEmailValidation(String invalidEmailEntry) throws Exception {
        return validate(invalidEmailEntry);
    }

  /*  *//*   *//**//**
     * demo purpose only :
     * steps executes in slow motion by calling this method in each step.
     * * *//*
    private void sleep(){
    try {
          Thread.sleep(3000L);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

*/

    /**
     * Validate hex with regular expression
     *
     * @param emailEntry hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String emailEntry) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailEntry);
        return matcher.matches();
    }
}







