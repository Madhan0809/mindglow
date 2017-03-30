package com.salmon.test.pageobjects.b2c.home;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LPHomePage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPHomePage.class);
    private By homeLink = By.cssSelector("i.iHome");
    private By locatorLink = By.cssSelector("i.iLocator");
    private By myAccountLink = By.cssSelector("i.iAccount");
    private By signInLink = By.cssSelector("i.iLock");
    private By signOut = By.linkText("Sign Out");
    private By onlineDoctorLink = By.cssSelector("i.iOnlineDoctor");
    private By blogLink = By.cssSelector("i.iBlog");
    private By contactLink = By.cssSelector("i.iContact");
    private By brandLogoLink = By.cssSelector("div.row.brandSearch div:nth-child(1) a");
    private By homePage = By.cssSelector("#homepage");
    private By storeLocatorPage = By.cssSelector("#widget_breadcrumb ul li:nth-child(2)");
    private String parentWindow;

    public void gotoHomePage() {
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
    }

    public void clickOnLloydsPharmacyHomeLink() {
        waitForExpectedElement(homeLink).click();
    }

    public void clickOnStoreLocatorLink() {
        waitForExpectedElement(locatorLink).click();
    }

    public void clickOnSignInLink() {
        waitForExpectedElement(signInLink).click();
    }

    public void clickOnOnlineDoctorLink() {
        if(!isElementPresent(onlineDoctorLink)){
            LOG.error("Online doctor icon is not presented. Please check.");
        }
        getParentWindow();
        waitForExpectedElement(onlineDoctorLink).click();
    }

    public void clickOnBlogLink() {
        getParentWindow();
        waitForExpectedElement(blogLink).click();
    }

    public void clickOnContactLink() {
        waitForExpectedElement(contactLink).click();
    }

    public void clickOnBrandLogoLink() {
        waitForExpectedElement(brandLogoLink).click();
    }

    private void getParentWindow() {
        LOG.info("Get current window handle as parent window: "+getWebDriver().getWindowHandle());
        parentWindow = getWebDriver().getWindowHandle();
    }

    public String verifyTheResultantTabOnHeaderClick(String pageTitle) throws InterruptedException {
        String title = "";
        for(int retryCount=0;getWebDriver().getWindowHandles().size()<1&&retryCount<10;retryCount++){
            LOG.warn("Currently there are "+getWebDriver().getWindowHandles().size()+" windows opened. The new window may not opened.");             
        }
        for(String windowHandle : getWebDriver().getWindowHandles()){
            LOG.info("Inside Window handles loop---------->"+getWebDriver().getWindowHandle());
            getWebDriver().switchTo().window(windowHandle);
            if(this.getCurrentPageTitle().equals(pageTitle)){            
                title = getWebDriver().getTitle();
                getWebDriver().close();
                break;
            }
        }     
//        for (String windowHandle : getWebDriver().getWindowHandles()) {
//            LOG.info("Inside Window handles loop---------->");
//            if (!windowHandle.equals(parentWindow)) {
//                getWebDriver().switchTo().window(windowHandle);
//                LOG.info("Inside (!parent)Window ---------->");
//                title = getWebDriver().getTitle();
//                getWebDriver().close();
//                break;
//            }
//        }
        getWebDriver().switchTo().window(parentWindow);
        return title;
    }

    public boolean verifyTheResultantTabOnSignInClick() {
        return isElementPresent(By.linkText("Register"));
    }

    public boolean verifyHomePage() {
        return waitForExpectedElement(homePage).isDisplayed();
    }

    public boolean isRelevantStoreLocatorPage() {
        return "Store Locator".equals(waitForExpectedElement(storeLocatorPage).getText());
    }

    public void clickOnSignOutLink() {
        waitForExpectedElement(signOut).click();
    }

    public boolean isUserSignedOut() {
        return isElementPresent(signOut);
    }

    public boolean isUserSignedIn() {
        return isElementPresent(signInLink);
    }

    public void clickOnMyAccountLink() {
    	getWebDriver().switchTo().defaultContent();
        waitForExpectedElement(myAccountLink).click();
    }
}
