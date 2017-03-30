package com.salmon.test.pageobjects.b2b.home;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class B2BHomePageHeader extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BHomePageHeader.class);
    private By homePageIcon = By.cssSelector(".col-4.m-col-12.panel>a>img");
    private By searchBox = By.cssSelector("#SimpleSearchForm_SearchTerm");
    private By autoSugesstedProducts = By.cssSelector("#suggestedProductsResults .list_section ul a");
    private By notificationCloseBtn = By.cssSelector("#NotificationMessageArea .close_text");
    private By logOnId = By.cssSelector("input[name='logonId']");
    private By password = By.cssSelector("input[name='logonPassword']");
    private By signInButton = By.cssSelector("*[value='Sign In']");
    private By homePage = By.id("page");
    private By signOutButton = By.cssSelector("#signOut");
    private By shopCompleteButton = By.cssSelector("input[value='Shop Now']");
    private By cookiePolicyButton = By.cssSelector(".cc_btn.cc_btn_accept_all");
    private By locationDropDown=By.id("accountDropdown");
    private By accountDropDown=By.cssSelector("select[name='account']");

    public void gotoHomePage() {
        LOG.info(" Navigating to AAH Enterprise HomePage \n");
//        getWebDriver().quit();
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startB2BHomePage();
        if(isPresent(cookiePolicyButton)){
            LOG.info("\n ****** cookies button has been closed ******");
          waitForExpectedElement(cookiePolicyButton).click();
        }
    }
     
    public void clickOnHomePageAAHIcon() {
        waitForExpectedElement(homePageIcon).click();
    }

    public boolean verifyHomePage() {
        return waitForExpectedElement(homePage).isDisplayed();
    }

    public void inputOfSearchString(String searchString) {
        waitForExpectedElement(searchBox, 20).sendKeys(searchString);
    }

    public List<String> getAutoSuggestProducts() {
        return visibilityOfAllElementsLocatedBy(autoSugesstedProducts).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void provideDefaultLoginDetails() {
        waitForExpectedElement(logOnId, 20).sendKeys(getProp("b2b.username"));
        waitForExpectedElement(password, 20).sendKeys(getProp("b2b.password"));
    }

    public void clickOnSignInButton() {
        waitForExpectedElement(signInButton).click();
    }

    public Boolean verifyUserOnHomePage() {
        return waitForExpectedElement(homePage,20).isDisplayed();
    }

    public void selectFromLocationDropdown(String location){
        Select accountSelector=new Select(waitForExpectedElement(locationDropDown));
        accountSelector.selectByVisibleText(location.replaceAll("(.*?)( )(-)( )", ""));
    }

    public boolean selectAccountDropDownValue(int value) {
        boolean flag =false;
        WebElement element = waitForExpectedElement(accountDropDown);
        Select dropDown = new Select(element);
        LOG.info(" \n Value ::" + value);
        dropDown.selectByIndex(value);
        flag = true;
        return flag;
    }

    public void dismissNotificationArea(){
        if(isPresent(notificationCloseBtn)){
            waitForExpectedElement(notificationCloseBtn).click();
        }
    }
    
    public void clickOnSignOutButton() {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(signOutButton));
        waitForExpectedElement(signOutButton).click();
    }

    public void provideMultiAccountLoginDetails() {
        waitForExpectedElement(logOnId).sendKeys(getProp("b2b.multiAccount.username"));
        waitForExpectedElement(password).sendKeys(getProp("b2b.multiAccount.password"));
    }

    public void clickOnShopCompleteButton(String shopComplete){
        waitForExpectedElement(shopCompleteButton).click();
    }
}
