package com.salmon.test.pageobjects.b2c.storelocator;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LPStoreLocatorDetailsPage extends PageObject {
    private static Logger LOG = LoggerFactory.getLogger(LPStoreLocatorDetailsPage.class);
    private By storeLocatorLink = By.cssSelector("i.iLocator");
    private By storeDetailsBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(4)");
    private By postOrTownSearch = By.cssSelector("#postCodeInput");
    private By viewDetailsLink = By.cssSelector(".storeLocatorResults .storeTableContent div:nth-child(1) ul li a");
    private By storeDetailsMap = By.cssSelector(".storeDetails #googleMap");
    private By storeLocatorLabelList = By.cssSelector(".storeDetails > div:nth-child(1) label");
    private By storeLocatorDetailsList = By.cssSelector(".storeDetails ul");
    private By storeDetailServicesList = By.cssSelector(".storeDetails ul.services li");
    //TODO:Need to change the selectors below once we got the proper identifier
    private By postFindPharmacy = By.cssSelector("fieldset>input[value='Find Pharmacy']");
    private By signOut = By.linkText("Sign Out");

    public void goToStoreLocatorDetailsPage() {
        LOG.info("Navigating to Lloyds Pharmacy Store locator details page \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        waitForExpectedElement(storeLocatorLink).click();
        waitForExpectedElement(postOrTownSearch).sendKeys(LloydsPharmacyConstants.POST_TOWN_SEARCH);
        getWebDriver().findElement(postFindPharmacy).click();
        waitForExpectedElement(viewDetailsLink).click();
    }

    public boolean isAppropriateMapDisplayed() {
        return getWebDriver().findElement(storeDetailsMap).isDisplayed();
    }

    /**
     * This is to make sure we check display of 3 blocks(Lloyds pharmacy, services and opening times)
     *
     * @return size of the list
     */
    public int getStoreLocatorDetails() {
        return (getWebDriver().findElements(storeLocatorDetailsList)).size();
    }

    public List<String> getStoreDetailServices(String service) {
        List<String> storesDetailServices = null;
        if (LloydsPharmacyConstants.STORES_LABELS.equals(service)) {
            storesDetailServices = getStoreDetailLabels(storeLocatorLabelList);
        } else if (LloydsPharmacyConstants.STORES_DETAILS.equals(service)) {
            storesDetailServices = getStoreDetailLabels(storeDetailServicesList);
        }
        return storesDetailServices;
    }

    public List<String> getStoreDetailLabels(By identifier) {
        return getWebDriver().findElements(identifier).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getRelevantStoreDetailsBreadCrumb() {
        return waitForExpectedElement(storeDetailsBreadCrumb).getText();
    }
}
