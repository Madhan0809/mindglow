package com.salmon.test.pageobjects.b2c.home;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LPFooterPage extends PageObject {
	private By footerHeaders=By.cssSelector(".fLinksWrap .collapsible.mobile");
	private By footerMenuContent=By.cssSelector(".row.collapsibleContainer ul>li");
    private By subscribeHeader = By.cssSelector(".newsletter p strong");
    private By subscribeContent = By.cssSelector(".newsletter p+p");
    private By subscribeEmail = By.cssSelector(".newsletter input[type='email']");
    private By subscribeButton = By.cssSelector(".newsletter input[type='submit']");
    private By socialMediaFacebook = By.cssSelector(".footerSocial li:nth-child(1) a");
    private By socialMediaTwitter = By.cssSelector(".footerSocial li:nth-child(2) a");
    private By socialMediaGooglePlus = By.cssSelector(".footerSocial li:nth-child(3) a");
    private By socialMediaPinterest = By.cssSelector(".footerSocial li:nth-child(4) a");
    private By socialMediaYoutube = By.cssSelector(".footerSocial li:nth-child(5) a");
    private By socialMediaOurBlog = By.cssSelector(".footerSocial li:nth-child(6) a");
    private By pharmacyImg=By.cssSelector("img[alt='Registered Pharmacy']");
    
    public String getSubscribePart(String subscribePart) {
        String subscribe = "";
        switch (subscribePart) {
            case LloydsPharmacyConstants.SUBSCRIBE_HEADER_TEXT:
                subscribe = waitForExpectedElement(subscribeHeader).getText();
                break;
            case LloydsPharmacyConstants.SUBSCRIBE_CONTENT_TEXT:
                subscribe = getWebDriver().findElement(subscribeContent).getText();
                break;
            case LloydsPharmacyConstants.SUBSCRIBE_EMAIL:
                subscribe = getWebDriver().findElement(subscribeEmail).getAttribute("placeholder");
                break;
            case LloydsPharmacyConstants.SUBSCRIBE_BUTTON:
                subscribe = getWebDriver().findElement(subscribeButton).getAttribute("value");
                break;
        }
        return subscribe;
    }

    public List<String> getFooterHeaderList() {
    	return visibilityOfAllElementsLocatedBy(footerHeaders).stream().map(e->e.getText()).collect(Collectors.toList());
    }

    public List<String> getFooterMenuList() {
    	return this.visibilityOfAllElementsLocatedBy(footerMenuContent).stream().map(e->e.getText()).collect(Collectors.toList());
    }

    public List<String> getSocialMediaList() {
        List<String> socialMediaList = new ArrayList<>();
        socialMediaList.add(getWebDriver().findElement(socialMediaFacebook).getAttribute("tooltip"));
        socialMediaList.add(getWebDriver().findElement(socialMediaTwitter).getAttribute("tooltip"));
        socialMediaList.add(getWebDriver().findElement(socialMediaGooglePlus).getAttribute("tooltip"));
        socialMediaList.add(getWebDriver().findElement(socialMediaPinterest).getAttribute("tooltip"));
        socialMediaList.add(getWebDriver().findElement(socialMediaYoutube).getAttribute("tooltip"));
        socialMediaList.add(getWebDriver().findElement(socialMediaOurBlog).getAttribute("tooltip"));
        return socialMediaList;
    }

    public void onClickOfsubscribePart(String subscribePart) {
        switch (subscribePart) {
            case LloydsPharmacyConstants.SUBSCRIBE_EMAIL:
                getWebDriver().findElement(subscribeEmail).click();
                break;
            case LloydsPharmacyConstants.SUBSCRIBE_BUTTON:
                getWebDriver().findElement(subscribeButton).click();
                break;
            case LloydsPharmacyConstants.PHARMACY_LOGO:
                getWebDriver().findElement(pharmacyImg).click();
        }
    }

    public void inputOfemailAddress(String emailAddress) {
        getWebDriver().findElement(subscribeEmail).sendKeys(emailAddress);
    }

    public String getTypeofEmail() {
        return getWebDriver().findElement(subscribeEmail).getAttribute("type");
    }
    
    public boolean isPharmacyLogoDisplayed(){
        return waitForExpectedElement(pharmacyImg).isDisplayed();
    }
    
    public boolean isRedirectedToPharmacyPage(String pageTitle){
        String origWindow=getWebDriver().getWindowHandle();
        for(String currentWindow: getWebDriver().getWindowHandles()){
            getWebDriver().switchTo().window(currentWindow);
            if(!currentWindow.equals(origWindow)){
                return this.checkPageTitleContains(pageTitle);
            }
        }
        return false;
    }
}
