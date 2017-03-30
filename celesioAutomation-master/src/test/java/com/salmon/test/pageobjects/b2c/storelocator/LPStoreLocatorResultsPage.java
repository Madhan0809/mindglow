package com.salmon.test.pageobjects.b2c.storelocator;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LPStoreLocatorResultsPage extends PageObject {
    private static Logger LOG = LoggerFactory.getLogger(LPStoreLocatorResultsPage.class);
    private By storeLocatorLink = By.cssSelector("i.iLocator");
    private By resultsBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(3)");
    private By detailsBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(4)");
    private By storesLocation = By.cssSelector(".storeResultsPanel h1");
    private By storesLocatorMap = By.cssSelector("#storeLocatorMap");
    private By storeTableHeading = By.cssSelector(".storeLocatorResults .storeTableHeading ");
    private By storeLocatorContents = By.cssSelector(".storeLocatorResults .storeTableContent");
    private By postOrTownSearch = By.cssSelector("#postCodeInput");
    private By viewDetailsLink = By.cssSelector(".storeLocatorResults .storeTableContent div:nth-child(1) ul li a");
    //TODO:Need to change the selectors below once we got the proper identifier
    private By storeFindFieldset=By.cssSelector("#locateStore fieldset");
    private By postFindPharmacy = By.cssSelector("fieldset>input[value='Find Pharmacy']");
    private By errorMsg = By.cssSelector(".inputBalance .errorMsg");
    private By signOut = By.linkText("Sign Out");

    public void goToStoreLocatorResultsPage() {
        LOG.info("Navigating to Lloyds Pharmacy Store locator results page \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        waitForExpectedElement(storeLocatorLink).click();
        waitForExpectedElement(postOrTownSearch).sendKeys("Wimbledon");
        waitForExpectedElement(postFindPharmacy).click();
    }

    public String getRelevantStoreLocationDisplayed() {
        return waitForExpectedElement(storesLocation,20).getText();
    }

    public boolean isAppropriateMapDisplayed() {
        return getWebDriver().findElement(storesLocatorMap).isDisplayed();
    }

    public boolean isRelevantStoreLocatorResultsDisplayed() {
        return getWebDriver().findElement(storeTableHeading).isDisplayed() &&
                getWebDriver().findElement(storeLocatorContents).isDisplayed();
    }

    public void OnClickOfViewDetailsLink() {
        waitForExpectedElement(viewDetailsLink).click();
    }

    public void populateTownOrPostCode(String search) {
//        if(!isElementPresent(storeFindFieldset)){
//            LOG.error("Find store input field set is not found.");
//        }
        waitForExpectedElement(postOrTownSearch,30).clear();
        getWebDriver().findElement(postOrTownSearch).sendKeys(search);
    }

    public String getRelevantDetailsPage() {
        return waitForExpectedElement(detailsBreadCrumb).getText();
    }

    public String getRelevantBreadCrumb() {
        return waitForExpectedElement(resultsBreadCrumb).getText();
    }

    public String getNoStoresErrorMsg() {
        return waitForExpectedElement(errorMsg).getText();
    }
}
