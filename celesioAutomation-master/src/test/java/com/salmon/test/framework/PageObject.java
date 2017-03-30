package com.salmon.test.framework;

import com.salmon.test.framework.helpers.LoadProperties;
import com.salmon.test.framework.helpers.WebDriverHelper;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public abstract class PageObject {
    private static final long DRIVER_WAIT_TIME = 10;
    private static final Logger LOG = LoggerFactory.getLogger(PageObject.class);
    private final ResourceBundle csrProps;
    private final ResourceBundle b2CProps;
    private final ResourceBundle b2BProps;
    private final Properties props;
    @Getter
    protected WebDriverWait wait;
    @Getter
    protected WebDriver webDriver;


    public PageObject() {
        this.webDriver = WebDriverHelper.getWebDriver();
        this.wait = new WebDriverWait(webDriver, DRIVER_WAIT_TIME);
        csrProps = ResourceBundle.getBundle("properties/csr/messages");
        b2CProps = ResourceBundle.getBundle("properties/b2c/messages");
        b2BProps = ResourceBundle.getBundle("properties/b2b/messages");
        props = LoadProperties.getProps();
    }

    /**
     * Returns the current Url from page
     **/
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    /**
     * Returns the current page title from page
     */
    public String getCurrentPageTitle() {
        return getWebDriver().getTitle();
    }

    /**
     * An expectation for checking that the title contains a case-sensitive
     * substring
     *
     * @param title the fragment of title expected
     * @return true when the title matches, false otherwise
     */
    public boolean checkPageTitleContains(String title) {
        return new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(ExpectedConditions.titleContains(title));
    }
    public WebElement waitForExpectedElement(final By by, long waitTimeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), waitTimeInSeconds);
            return wait.until(visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            LOG.info(e.getMessage());
            return null;
        } catch (TimeoutException e) {
            LOG.info(e.getMessage());
            return null;
        }
    }

    protected ExpectedCondition<WebElement> visibilityOfElementLocated(final By by) throws NoSuchElementException {
        return new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage());
                }
                WebElement element = getWebDriver().findElement(by);
                return element.isDisplayed() ? element : null;
            }
        };
    }

    public WebElement waitForExpectedElement(final By by) {
        return wait.until(visibilityOfElementLocated(by));
    }

    protected List<WebElement> visibilityOfAllElementsLocatedBy(final By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> visibilityOfAllElementsLocatedBy(final By by, long waitTimeInSeconds) {
        return (new WebDriverWait(getWebDriver(), waitTimeInSeconds)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    //With wait
    protected boolean isElementPresent(By by) {
        try {
            new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME).until(visibilityOfElementLocated(by));
        } catch (TimeoutException exception) {
            LOG.info(exception.getMessage());
            return false;
        }
        return true;
    }

    //Without wait
    protected boolean isPresent(By by) {
        try {
            getWebDriver().findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    protected WebElement waitForElementAvailableAndVisible(By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(visibilityOfElementLocated(by));
    }

    public WebElement waitForElementDisplayedAndClickable(By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(visibilityOfElementLocated(by));
    }

    protected boolean waitForElementToDisappear(By by) {
        return (new WebDriverWait(getWebDriver(), DRIVER_WAIT_TIME)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebDriver getBrowserByPageTitle(String pageTitle) {
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver = webDriver.switchTo().window(windowHandle);
            if (pageTitle.equalsIgnoreCase(webDriver.getTitle())) {
                return webDriver;
            }
        }
        return null;
    }

    public void navigateToPreviousPageUsingBrowserBackButton() {
        webDriver.navigate().back();
    }

    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }

    public String getElementByTagNameWithJSExecutor(String tagName) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.getElementsByTagName('" + tagName + "')").toString();
    }

    public String getElementByQueryJSExecutor(String cssSelector) {
        return ((JavascriptExecutor) webDriver).executeScript("return window.getComputedStyle(document.querySelector('" + cssSelector + "')").toString();
    }

    /**
     * Gets the key from csr/messages.properties for CSR
     *
     * @param key
     **/
    public String getCsrProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return csrProps.getString(key);
        }
    }

    /**
     * Gets the key from b2b/messages.properties for B2B
     *
     * @param key
     **/
    public String getB2bProp(String key) {

        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return b2BProps.getString(key);

        }
    }

    /**
     * Gets the key from b2c/messages.properties for B2C
     *
     * @param key
     **/
    public String getB2cProp(String key) {

        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return b2CProps.getString(key);

        }
    }

    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return props.getProperty(key);

        }
    }

/*    public void clickWithinElementWithXYCoordinates(WebElement webElement, int x, int y) {
        Actions builder = new Actions(webDriver);
        builder.moveToElement(webElement, x, y);
        builder.click();
        builder.perform();
    }*/

    public void scrollAndClick(WebElement webElement) {
        Point hoverItem = webElement.getLocation();
        clickWithinElementWithXYCoordinates(webElement, hoverItem.getX(), hoverItem.getY());
        webElement.click();
    }

    public void scrollToElement(WebElement webElement) {
        Point hoverItem = webElement.getLocation();
        clickWithinElementWithXYCoordinates(webElement, hoverItem.getX(), hoverItem.getY());
    }

    public void scrollAndClick(By by) {
        Point hoverItem = waitForExpectedElement(by).getLocation();
        clickWithinElementWithXYCoordinates(waitForExpectedElement(by), hoverItem.getX(), hoverItem.getY());
        waitForExpectedElement(by).click();
    }

}