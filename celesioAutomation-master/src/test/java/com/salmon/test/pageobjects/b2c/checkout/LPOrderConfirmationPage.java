package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LPOrderConfirmationPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPOrderConfirmationPage.class);
    
    private By thankYouConfirmation = By.cssSelector(".summaryHeader>h1");
    private By orderNo = By.cssSelector(".orderNumber>strong");
    private By shippingAddressDetails = By.cssSelector("[data-test=addr]");
    private By productName = By.cssSelector(".productDetails");
    private By shippingMethod = By.cssSelector("[data-test='shipping']");
    private By subTotal = By.cssSelector(".totalProductPrice");
    private By shippingCharge = By.cssSelector(".totalShippingCharge");
    private By orderTotal = By.cssSelector(".row.total strong");

    public String getShippingMethod() {
        return getWebDriver().findElement(shippingMethod).getText().trim();
    }

    public String getProductName() {
        return getWebDriver().findElement(productName).getText();
    }

    public String getDeliveryAddress() {
        List<WebElement> addressDetailsElements = getWebDriver().findElements(shippingAddressDetails);
        StringBuilder addressDetails = new StringBuilder();
        for (WebElement addressDetailsElement : addressDetailsElements) {
            addressDetails.append(addressDetailsElement.getText() + " ");
        }
        return addressDetails.toString().replaceAll("(\\s+)", " ").trim();
    }

    public String getSubTotal() {
        return getWebDriver().findElement(subTotal).getText();
    }

    public String getOrderTotal() {
        return getWebDriver().findElement(orderTotal).getText();
    }

    public String getShippingCharge() {
        return getWebDriver().findElement(shippingCharge).getText();
    }

    public String getOrderConfirmationMessage() {
        LOG.info("Get order confirmation message.");
//    	return this.waitForElementDisplayedAndClickable(thankYouConfirmation).getText();
        return waitForExpectedElement(thankYouConfirmation, 60).getText();
    }

    public String getTheOrderNumber() {
        LOG.info("Now get the order number in confirmation page.");
        return waitForExpectedElement(orderNo).getText();
    }
}
