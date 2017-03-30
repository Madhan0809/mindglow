package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2c.AddressForm;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LPPaymentPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPPaymentPage.class);
    
    private By payPalPaymentOption = By.cssSelector("#Paypal");
    private By paymentCardOption = By.cssSelector("[name='paymentGroup']");
    private By agreeTerms = By.name("agreeTerms");
    private By submitOrder = By.cssSelector(".btnAction[type='submit']");
    private By cardType = By.cssSelector("#cardSelect");
    private By cardNumber = By.cssSelector("[name='card_number']");
    private By cardName = By.cssSelector("[name='name']");
    private By cardMonth = By.cssSelector("[name='cardmonth']");
    private By cardYear = By.cssSelector("[name='cardyear']");
    private By cardSecurity = By.cssSelector("[name='card_cvn']");
    private By addANewAddressLink = By.linkText("Add a new address");
    private By editAddressLink = By.linkText("Edit Address");
    private By chooseAddress = By.name("addressId");
    private By nickName = By.name("nickName");
    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By zipCode = By.name("zipCode");
    private By guestBillingAddressPostCode = By.name("bill_to_address_postal_code");
    private By address1 = By.name("address1");
    private By address2 = By.name("address2");
    private By city = By.name("city");
    private By county = By.name("state");
    private By country = By.name("country");
    private By findAddressButton = By.cssSelector(".btnPrimary.findAddressBtn");
    private By saveAddress = By.cssSelector(".btnSaveAddress");
    private By addressDetails = By.cssSelector("li[data-test='addr']");
    private By addressPersonalDetails = By.cssSelector("li[data-test='name']");
    private By sameAsDeliveryCheckBox = By.name("sameAsDelivery");
    private By billingAddressForm = By.cssSelector(".guestPaymentBilling .newAddressDetails.active");
    private By manualAddress1Input = By.cssSelector(".groupManualAddress input[name='bill_to_address_line1']");
    private By enterManualAddressLink = By.cssSelector("a.manualAddressLink");
    private By billingAddressFormInputForGuest = By.cssSelector(".newAddressDetails.active  input");
    private By billingAddressNameSameDetails = By.cssSelector(".guestPaymentBilling strong");
    private By billingAddressSameDetails = By.cssSelector(".guestPaymentBilling strong br");
    private By visa3DPageBody=By.cssSelector("body[id='3DPage']");
    private By password3DSecure = By.cssSelector("form input[type='password']");
    private By submit3DSecure = By.cssSelector("form input[type='submit']");

    private By shippingAddressLHS = By.cssSelector(".rowShippingDetails li:not(:first-child)");
    private By  shippingCharge = By.cssSelector(".shippingCharge");
    private By  deliveryType = By.cssSelector(".deliveryType");


    private void enterNewBillingAddress(String addressName) {
    	jsClick(addANewAddressLink);
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(nickName));
        waitForElementDisplayedAndClickable(nickName).sendKeys(addressName);
        getWebDriver().findElement(firstName).sendKeys(getB2cProp("address.firstName." + addressName));
        getWebDriver().findElement(lastName).sendKeys(getB2cProp("address.lastName." + addressName));
        getWebDriver().findElement(zipCode).sendKeys(getB2cProp("address.postCode." + addressName));
        getWebDriver().findElement(address1).sendKeys(getB2cProp("address.add1." + addressName));
        getWebDriver().findElement(address2).sendKeys(getB2cProp("address.add2." + addressName));
        getWebDriver().findElement(city).sendKeys(getB2cProp("address.city." + addressName));
        getWebDriver().findElement(county).sendKeys(getB2cProp("address.county." + addressName));
        new Select(getWebDriver().findElement(country)).selectByVisibleText(getB2cProp("address.country." + addressName));
        getWebDriver().findElement(saveAddress).click();
    }

    private void jsClick(By by){
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(by));
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click()", waitForExpectedElement(by));
	}

    public void addressAction(String addressActionType, String addressName) {
        if (addressActionType.equalsIgnoreCase("Add New")) {
            enterNewBillingAddress(getB2cProp("address.nick." + addressName));
        }
    }

    public String verifyBillingAddress(String addressName) {
        List<WebElement> addressDetailsElements = this.visibilityOfAllElementsLocatedBy(addressDetails);
        StringBuilder addressDetails = new StringBuilder();
        for (WebElement addressDetailsElement : addressDetailsElements) {
            addressDetails.append(addressDetailsElement.getText() + " ");
        }
        return addressDetails.toString();
    }



    public void submitOrderUsingPayPal() {
        LOG.info("Click on paypal payment option and submit order.");
        waitForExpectedElement(payPalPaymentOption).click();
        getWebDriver().findElement(agreeTerms).click();
        getWebDriver().findElement(submitOrder).click();
    }

    public void enterCardDetails(String typeOfcard) {
        waitForExpectedElement(paymentCardOption).click();
        new Select(waitForExpectedElement(cardType)).selectByVisibleText(getB2cProp(typeOfcard + ".cardType"));
        getWebDriver().findElement(cardName).sendKeys(getB2cProp("card.name"));
        getWebDriver().findElement(cardNumber).sendKeys(getB2cProp(typeOfcard + ".cardNumber"));
        new Select(waitForExpectedElement(cardMonth)).selectByVisibleText(getB2cProp("card.expiry.month"));
        new Select(waitForExpectedElement(cardYear)).selectByVisibleText(getB2cProp("card.expiry.year"));
        getWebDriver().findElement(cardSecurity).sendKeys(getB2cProp("card.securityNumber"));
    }

    public void submitOrderAndAgreeTerms() {
//    	jsClick(agreeTerms);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(agreeTerms));
        waitForExpectedElement(agreeTerms).click();
        waitForExpectedElement(submitOrder,30).click();
    }

    public void chooseAndEnterBillingAddressForGuest(String addressName, AddressForm addressForm) {
        if (addressName.equalsIgnoreCase("SAME")) {
            assertThat(waitForExpectedElement(billingAddressNameSameDetails).getText()).containsIgnoringCase(getB2cProp("address.sameBilling.guest"));
            assertThat(getWebDriver().findElement(sameAsDeliveryCheckBox).isSelected()).isTrue();
        } else {
            getWebDriver().findElement(sameAsDeliveryCheckBox).click();
            assertThat(getWebDriver().findElement(sameAsDeliveryCheckBox).isSelected()).isFalse();
            enterGuestBillingAddress(addressForm);
        }
    }

    public void enterGuestBillingAddress(AddressForm addressForm) {
        WebElement billingAddressFormGuest = waitForExpectedElement(billingAddressForm);
        billingAddressFormGuest.findElement(By.name("bill_to_address_postal_code")).sendKeys(addressForm.getPostCode());
        waitForExpectedElement(enterManualAddressLink).click();
        waitForExpectedElement(manualAddress1Input);
        getDetailEditFieldByAttributeName("bill_to_forename").sendKeys(addressForm.getFirstName());
        getDetailEditFieldByAttributeName("bill_to_surname").sendKeys(addressForm.getLastName());
        getDetailEditFieldByAttributeName("bill_to_address_line1").sendKeys(addressForm.getAddress1());
        getDetailEditFieldByAttributeName("address2").sendKeys(addressForm.getAddress2());
        getDetailEditFieldByAttributeName("bill_to_address_city").sendKeys(addressForm.getCity());
        getDetailEditFieldByAttributeName("bill_to_address_state").sendKeys(addressForm.getState());
    }

    private WebElement getDetailEditFieldByAttributeName(String fieldName) {
        WebElement deliveryForm = getWebDriver().findElement(billingAddressForm);
        String fieldNameCss = ".guestPaymentBilling .newAddressDetails.active" + " [name =  '" + fieldName + "']";
        if (!isElementPresent(By.cssSelector(fieldNameCss))) {
            return null;
        }
        return deliveryForm.findElement(By.name(fieldName));
    }

    public void selectPaymentOption(String paymentOption) {
        if(paymentOption.equalsIgnoreCase("paypal")){
            waitForExpectedElement(payPalPaymentOption).click();
        }

    }

    public void complete3DVerification() {
    	waitForExpectedElement(visa3DPageBody,45);
    	LOG.info("Input visa3D default crendentials and submit to complete verification.");
        getWebDriver().switchTo().defaultContent();
        getWebDriver().switchTo().frame(0);
//        getWebDriver().switchTo().frame(waitForExpectedElement(By.cssSelector("iframe"),30));
        getWebDriver().switchTo().frame("authWindow");
        waitForExpectedElement(password3DSecure).sendKeys("1234");
        getWebDriver().findElement(submit3DSecure).click();
        getWebDriver().switchTo().defaultContent();
    }

    public String getShippingAddress() {
        return getWebDriver().findElements(shippingAddressLHS).stream().map(WebElement::getText).collect(Collectors.joining(" "));
    }

    public String getShippingCharge(){
        return getWebDriver().findElement(shippingCharge).getText();
    }

    public String getShippingType(){
        return getWebDriver().findElement(deliveryType).getText();
    }


}
