package com.salmon.test.pageobjects.b2c.myAccount;
import com.salmon.test.enums.PermittedCharacters;
//import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LPMyAccountPage extends PageObject {
    private By lhsMenuLinks = By.cssSelector(".myAccountSidebar dt");
    private By lhsSubMenuLinks = By.cssSelector(".myAccountSidebar dd a");
    private By email = By.name("email1");
    private By showPassword = By.id("showPassword");
    private By title = By.name("personTitle");
    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By postcode = By.cssSelector("div[class*='findAddress']>input");
    private By address1 = By.name("address1");
    private By address2 = By.name("address2");
    private By city = By.name("city");
    private By state = By.name("state");
    private By country = By.name("country");
    private By preferredContact = By.name("phone1");
    private By alternativeContact = By.name("phone2");
    private By gender = By.name("gender");
    private By birthDay = By.name("dobday");
    private By birthMonth = By.name("dobmonth");
    private By birthYear = By.name("dobyear");
    private By preferMail = By.id("mailconsent");
    private By preferPhone = By.id("phoneconsent");
    private By preferEmail = By.id("emailconsent");
    private By preferSMS = By.id("smsconsent");
    private By preferredAddress = By.cssSelector(".preferredAddress> ul li");
    private By editPreferredAddress = By.cssSelector(".preferredAddress .btnPrimary");
    private By deletePreferredAddress = By.cssSelector(".preferredAddress btnDarkGrey");
    private By addNewAddress = By.cssSelector(".addressHeader a.btnAction");
    private By wishListSelect = By.cssSelector("#wishlistSelect>select");
    private By wishListProductTitle = By.cssSelector(".productTitle");
    private By wishListEmptyMessage = By.cssSelector(".emptyWishlist p");
    private By removeFromWishList = By.cssSelector("a.removeFromWishlist");
    private By wishListResults = By.cssSelector(".wishResults");
    private By nickName = By.name("nickName");
    private By zipCode = By.name("zipCode");
    private By guestBillingAddressPostCode = By.name("bill_to_address_postal_code");
    private By county = By.name("state");
    private By findAddressButton = By.cssSelector(".btnPrimary.findAddressBtn");
    private By saveAddress = By.cssSelector(".btnAction[value='Add Address']");
    private By cookiesConfirmBtn=By.cssSelector(".btnAction.firefinder-match");
//    private By saveAddress=By.xpath(".//input[@value='Add Address']");
    private By addANewAddressLink = By.cssSelector(".myAccount .btnAction");
    private By addressInputText=By.cssSelector("form#formAddress input[type='text']:not([readonly])");
    private By additionalAddress = By.cssSelector(".addressBottom ul>li");
    private By updateAddress = By.cssSelector(".btnAction[value=Update]");
    private By errorMessage = By.cssSelector("#ErrorMessageText");

    public List<String> getLhsMenuLinks() {
        return visibilityOfAllElementsLocatedBy(lhsMenuLinks).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getLhsSubMenuLinks() {
        return visibilityOfAllElementsLocatedBy(lhsSubMenuLinks).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickLink(String linkName) {
        jsClick(By.linkText(linkName));
    }

    public void verifyPersonalInformationDetails(String userName) {
        assertThat(waitForExpectedElement(email).getAttribute("value")).isEqualToIgnoringCase(getProp("b2c.email." + userName));
        assertThat(new Select(getWebDriver().findElement(title)).getFirstSelectedOption().getText()).containsIgnoringCase(getB2cProp("address.title." + userName));
        assertThat(getWebDriver().findElement(firstName).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.firstName." + userName));
        assertThat(getWebDriver().findElement(lastName).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.lastName." + userName));
        assertThat(getWebDriver().findElement(postcode).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.postCode." + userName));
        assertThat(getWebDriver().findElement(address1).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.add1." + userName));
        assertThat(getWebDriver().findElement(address2).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.add2." + userName));
        assertThat(getWebDriver().findElement(preferredContact).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.preferredContact." + userName));
        assertThat(getWebDriver().findElement(alternativeContact).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.contactAlternative." + userName));
        assertThat(getWebDriver().findElement(city).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.city." + userName));
        assertThat(getWebDriver().findElement(state).getAttribute("value")).isEqualToIgnoringCase(getB2cProp("address.county." + userName));
        assertThat(new Select(getWebDriver().findElement(country)).getFirstSelectedOption().getText()).isEqualToIgnoringCase(getB2cProp("address.country." + userName));
        assertThat(new Select(getWebDriver().findElement(gender)).getFirstSelectedOption().getText()).isEqualToIgnoringCase(getB2cProp("address.gender." + userName));
        assertThat(new Select(getWebDriver().findElement(birthDay)).getFirstSelectedOption().getText()).isEqualToIgnoringCase(getB2cProp("address.birthDay." + userName));
        assertThat(new Select(getWebDriver().findElement(birthMonth)).getFirstSelectedOption().getText()).isEqualToIgnoringCase(getB2cProp("address.birthMonth." + userName));
        assertThat(new Select(getWebDriver().findElement(birthYear)).getFirstSelectedOption().getText()).isEqualToIgnoringCase(getB2cProp("address.birthYear." + userName));
        assertThat(getWebDriver().findElement(preferMail).isSelected()).isEqualTo(Boolean.parseBoolean(getB2cProp("address.preferMail." + userName)));
        assertThat(getWebDriver().findElement(preferPhone).isSelected()).isEqualTo(Boolean.parseBoolean(getB2cProp("address.preferPhone." + userName)));
        assertThat(getWebDriver().findElement(preferEmail).isSelected()).isEqualTo(Boolean.parseBoolean(getB2cProp("address.preferEmail." + userName)));
        assertThat(getWebDriver().findElement(preferSMS).isSelected()).isEqualTo(Boolean.parseBoolean(getB2cProp("address.preferSMS." + userName)));
    }

    public void verifyPersonalAddressCollectionPreferences(String userName) {
        String preferredAddressText = visibilityOfAllElementsLocatedBy(preferredAddress).stream().map(WebElement::getText).collect(Collectors.joining(" "));
        assertThat(preferredAddressText).containsIgnoringCase(getB2cProp("address.saved." + userName));
    }

    public void selectWishListByName(String wishListName) {
        new Select(waitForExpectedElement(wishListSelect)).selectByVisibleText(wishListName);
    }

    public String checkIfProductIsPresentInWishList(String productName) {
        return getWishListProduct(productName).findElement(wishListProductTitle).getText();
    }

    public void removeProductFromWishList(String productName) {
        getWishListProduct(productName).findElement(removeFromWishList).click();

    }

    public String getEmptyWishListMessage() {
        return waitForExpectedElement(wishListEmptyMessage).getText();
    }

    private WebElement getWishListProduct(String productName) {
        waitForExpectedElement(wishListSelect);
        List<WebElement> productsInWishList = getWebDriver().findElements(wishListResults);
        for (WebElement webElement : productsInWishList) {
            if (webElement.findElement(wishListProductTitle).getText().equalsIgnoreCase(getB2cProp(productName))) {
                return webElement;
            }
        }
        return null;
    }

    public void editOrDeletePreferredAddress(String addtressType) {
        this.jsClick(editPreferredAddress);
        clearInputFields();
        enterNewBillingAddress(getB2cProp("address.nick." + addtressType));
    	((JavascriptExecutor)  getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(updateAddress));
    	waitForExpectedElement(updateAddress).click();
    }

    private void clearInputFields() {
        List<WebElement> inputFields = visibilityOfAllElementsLocatedBy(addressInputText);
        for (WebElement inputField : inputFields) {
            inputField.clear();
        }
    }

    private void enterNewBillingAddress(String addressName) {
//        waitForExpectedElement(nickName).sendKeys(addressName);
        waitForExpectedElement(firstName).sendKeys(getB2cProp("address.firstName." + addressName));
        waitForExpectedElement(lastName).sendKeys(getB2cProp("address.lastName." + addressName));
        waitForExpectedElement(zipCode).sendKeys(getB2cProp("address.postCode." + addressName));
        waitForExpectedElement(address1).sendKeys(getB2cProp("address.add1." + addressName));
        waitForExpectedElement(address2).sendKeys(getB2cProp("address.add2." + addressName));
        waitForExpectedElement(city).sendKeys(getB2cProp("address.city." + addressName));
        waitForExpectedElement(county).sendKeys(getB2cProp("address.county." + addressName));
        new Select(waitForExpectedElement(country)).selectByVisibleText(getB2cProp("address.country." + addressName));
    }

    public void addressAction(String addressActionType, String addressName) {
        if (addressActionType.equalsIgnoreCase("Add New")) {
            waitForExpectedElement(addANewAddressLink).click();
            waitForExpectedElement(nickName).sendKeys(getB2cProp("address.nick." + addressName));
            enterNewBillingAddress(getB2cProp("address.nick." + addressName));
            jsClick(saveAddress);
//            waitForExpectedElement(saveAddress).click();
        }
    }
    
    private void jsClick(By by){
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()",waitForExpectedElement(by));
	}


    public void verifyAdditonalAddress(String userName) {
    	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, 300)", "");
        String additionalAddress = this.visibilityOfAllElementsLocatedBy(this.additionalAddress).stream().map(WebElement::getText).collect(Collectors.joining(" "));
        assertThat(additionalAddress).containsIgnoringCase(getB2cProp("address.saved." + userName));
    }

    public String getErrorMessage() {
        return waitForExpectedElement(errorMessage).getText();
    }
}

