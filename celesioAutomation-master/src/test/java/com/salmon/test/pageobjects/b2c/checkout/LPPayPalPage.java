package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LPPayPalPage extends PageObject {
	private static final Logger LOG = LoggerFactory.getLogger(LPPayPalPage.class);
	private By loginSection=By.cssSelector("section#login");
    private By payPalUserName = By.id("email");
    private By payPalPassword = By.id("password");
    private By payPalSubmit = By.cssSelector("button#btnLogin");
    private By continueWithPayPalBalance = By.name("continue");
    private By paypalInner=By.cssSelector(".span14.trayInner");
    private By paypalName=By.cssSelector(".given-name.ng-binding");
    private By paypalDeliveryAdd=By.cssSelector(".confidential");
    private By payNowConfirmBtn=By.cssSelector("input#confirmButtonTop");
    
    public void enterPayPalLoginDetails(String payPalUserNameValue, String payPalPasswordValue) {
        LOG.info("Explict wait for page loading of paypal login page.");
        int retry=0;
        while(!isElementPresent(paypalInner)&&retry<5){
            LOG.warn("The paypal payment login page may under loading.");
            retry++;
        }
        waitForExpectedElement(paypalInner,85);
//        waitForExpectedElement(loginSection,85).click();
        for (WebElement frame : getWebDriver().findElements(By.cssSelector("iframe"))) {
            getWebDriver().switchTo().frame(frame);
            if (isElementPresent(payPalUserName)) {
                LOG.info("Input paypal credentials and submit it to process the payment.");
                waitForExpectedElement(payPalUserName).clear();
                getWebDriver().findElement(payPalPassword).clear();
                getWebDriver().findElement(payPalUserName).sendKeys(getProp(payPalUserNameValue));
                getWebDriver().findElement(payPalPassword).sendKeys(getProp(payPalPasswordValue));
                waitForExpectedElement(payPalSubmit).click();
                break;
            } else {
                getWebDriver().switchTo().defaultContent();
            }
        }
        getWebDriver().switchTo().defaultContent();
    }

	public void continueWithPayPalBalance() {
		if (isElementPresent(payNowConfirmBtn)) {
		    LOG.info("Click the pay now confirm button.");
			waitForExpectedElement(payNowConfirmBtn).click();
		} else if (isElementPresent(continueWithPayPalBalance)) {
		    LOG.info("Click the continue with paypal banlance button.");
			waitForExpectedElement(continueWithPayPalBalance).click();
		} else
			LOG.error("The continue to paypal payment button is not found.");
	}

	public String getPayPalNameInfo(){
	    return waitForExpectedElement(paypalName,30).getText().trim();
	}
	
    public String getPayPalDeliveryAddress() {
        List<WebElement> deliveryAddressDetails = visibilityOfAllElementsLocatedBy(paypalDeliveryAdd,30);
        return deliveryAddressDetails.stream().map(webElement -> webElement.getText().trim().replace("\n", " ").trim()).collect(Collectors.joining());
    }
}
