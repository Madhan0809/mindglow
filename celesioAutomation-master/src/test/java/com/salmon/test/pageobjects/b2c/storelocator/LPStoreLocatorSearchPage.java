package com.salmon.test.pageobjects.b2c.storelocator;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LPStoreLocatorSearchPage extends PageObject {
    private static Logger LOG = LoggerFactory.getLogger(LPStoreLocatorSearchPage.class);
    private By storeLocatorLink = By.cssSelector("i.iLocator");
    private By searchBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(2)");
    private By resultsBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(3)");
    private By openingTimesList = By.cssSelector("#locateStore ul.servicesList.openingTimes li a.check");
    private By lloydsServicesList = By.cssSelector("#locateStore ul.servicesList.allServices li a.check");
    private By postOrTownSearch = By.cssSelector("#postCodeInput");
    //TODO:Need to change the selectors below once we got the proper identifier
    private By postFindPharmacy = By.cssSelector("fieldset>input[value='Find Pharmacy']");
    private By servicesFindPharmacy = By.cssSelector(".btnSubmit");
    private By searchAgainForm=By.cssSelector("#locateStore>.row");
    private By signOut = By.linkText("Sign Out");


    public void goToStoreLocatorSearchPage() {
        LOG.info("Navigating to Lloyds Pharmacy Store locator results page \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        waitForExpectedElement(storeLocatorLink).click();
    }

    public String getStoreSearchBreadCrumb() {
        return waitForExpectedElement(searchBreadCrumb).getText();
    }

    public String getStoreResultsBreadCrumb() {
        return waitForExpectedElement(resultsBreadCrumb).getText();
    }

    public List<String> getServicesList(String serviceName) {
        List<WebElement> openingTimesWebElements = new ArrayList<>();
        if (LloydsPharmacyConstants.OPENING_TIMES_AND_COLLECTION.equals(serviceName)) {
            openingTimesWebElements = getWebDriver().findElements(openingTimesList);
        } else if (LloydsPharmacyConstants.LLOYDS_PHARMACY.equals(serviceName)) {
            openingTimesWebElements = getWebDriver().findElements(lloydsServicesList);
        }
        return openingTimesWebElements.stream().filter(WebElement::isDisplayed).map(WebElement::getText).collect(Collectors.toList());
    }

    public void populateTownOrPostCode(String search) {
    	waitForExpectedElement(searchAgainForm);
    	if(isElementPresent(postOrTownSearch)){
    		waitForElementDisplayedAndClickable(postOrTownSearch).clear();
            waitForExpectedElement(postOrTownSearch).sendKeys(search);
    	} 
    	else
    		LOG.error("The post search area is not available.");
    }

    public void onClickOfPostFindPharmacy() {
        waitForExpectedElement(postFindPharmacy).click();
    }
}
