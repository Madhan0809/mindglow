package com.salmon.test.pageobjects.csr;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CSRHomePage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(CSRHomePage.class);
    private By csrHeader = By.cssSelector("#content h2");
    private By lhsMenuLabelList = By.cssSelector("#menu h5.menu-header");
    private By channelList = By.id("channel-list");
    private By storeList = By.id("store-list");
    private By gotoLink = By.id("home-link");

    public void gotoCSRSigninPage() {
        LOG.info(" Navigating to CSR  HomePage \n");
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startCSRHomePage();
    }

    public WebElement menuByLHSHeader(String menuHeader) {
        return waitForExpectedElement(By.xpath(".//*[text()='" + menuHeader + "']"));
    }

    public WebElement findMenuByLHSHeader(String menuHeader) {
        return waitForExpectedElement(By.xpath(".//*[text()='" + menuHeader + "']/following-sibling::a"));
    }

    private List<WebElement> listOfFoundSearchResultsInLhsMenu(String menuHeader) {
        return getWebDriver().findElements(By.xpath(".//*[text()='" + menuHeader + "']/following-sibling::div//li/a"));
    }

    public List<WebElement> getLHSMenuLabelList() {
        return getWebDriver().findElements(lhsMenuLabelList);
    }

    public WebElement getFoundSearchResult(String searchResult, String menuHeader) {
        List<WebElement> listOfFoundSearchResultsInLhsMenu = listOfFoundSearchResultsInLhsMenu(menuHeader);
        for (WebElement searchResultUnderLHSMenu : listOfFoundSearchResultsInLhsMenu) {
            if ((searchResultUnderLHSMenu.getText().contains(getCsrProp(searchResult)))) {
                return searchResultUnderLHSMenu;
            }
        }
        return null;
    }

    public WebElement getCsrHeader() {
        return waitForExpectedElement(csrHeader);
    }

    public void chooseChannelAndStore(String channel, String store) {
        new Select(waitForExpectedElement(channelList)).selectByVisibleText(channel);
        new Select(waitForExpectedElement(storeList)).selectByVisibleText(store);
    }

    public WebElement gotoLink() {
        return waitForExpectedElement(gotoLink);
    }
}