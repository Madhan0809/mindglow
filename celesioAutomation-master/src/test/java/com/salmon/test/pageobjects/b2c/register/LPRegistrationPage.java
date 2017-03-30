package com.salmon.test.pageobjects.b2c.register;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.b2c.Registration;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;
import static org.assertj.core.api.Assertions.assertThat;

public class LPRegistrationPage extends PageObject {
    private static Logger LOG = LoggerFactory.getLogger(LPRegistrationPage.class);
    private By userName = By.id("username");
    private By password = By.id("password");
    private By email = By.name("email1");
    private By showPassword = By.id("showPassword");
    private By title = By.name("personTitle");
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By postcode = By.name("zipCode");
    private By address1 = By.name("address1");
    private By address2 = By.id("address2");
    private By city = By.id("city");
    private By state = By.id("state");
    private By country = By.id("country");
    private By preferredContact = By.id("preferredContact");
    private By phone = By.id("phone");
    private By birthDay = By.id("birthDay");
    private By birthMonth = By.id("birthMonth");
    private By birthYear = By.id("birthYear");
    private By gender = By.name("gender");
    private By preferMail = By.id("preferMail");
    private By preferPhone = By.id("preferPhone");
    private By preferEmail = By.id("preferEmail");
    private By preferSMS = By.id("preferSMS");
    private By terms = By.id("terms");
    private By createAccount = By.cssSelector("input[value='Create Account']");
    private By findAddress = By.cssSelector("button.btnPrimary");
    private By manualAddress = By.id("manualAddress");
    private By errorMsgs = By.cssSelector("div:not(.hide).errorMsg");
    private By labels = By.cssSelector(".registerForm label");
    private By errorMessage = By.cssSelector(".clientValidation .errorMsg");
    private By registerAddress = By.cssSelector("#registerAddress");
    private By findPostCodeButton = By.cssSelector(".btnPrimary.findAddressBtn");
    private By selectAddressFromDropDown = By.cssSelector(".addressSelect");
    private By prefText=By.cssSelector("h3~p:nth-of-type(1)");
    
    private String userNameData;
    private String emailIdData;
    private String passwordData;

    public Registration createUserRegData(Registration userDetails) {
        if (userDetails.isExistingUser()) {
            userNameData = getProp("b2c.username");
            emailIdData = getProp("b2c.email");
            passwordData = getProp("b2c.password");
        } else {
            userNameData = "Auto" + RandomGenerator.random(5, PermittedCharacters.ALPHABETS);
            emailIdData = "Auto" + RandomGenerator.randomEmailAddress(6);
            passwordData = "password1";
        }
        if (userDetails.getAddressType() == null) {
            userDetails.setAddressType("home");
        }
        String addressType = userDetails.getAddressType();
        userDetails.setUsername(userNameData);
        userDetails.setEmail(passwordData);
        userDetails.setPassword(passwordData);
        userDetails.setTitle(getB2cProp("address.title." + addressType));
        userDetails.setFirstName(getB2cProp("address.firstName." + addressType));
        userDetails.setLastName(getB2cProp("address.lastName." + addressType));
        userDetails.setPostcode(getB2cProp("address.postCode." + addressType));
        userDetails.setStreetAddress1(getB2cProp("address.add1." + addressType));
        userDetails.setStreetAddress2(getB2cProp("address.add2." + addressType));
        userDetails.setTownOrCity(getB2cProp("address.city." + addressType));
        userDetails.setCounty(getB2cProp("address.county." + addressType));
        userDetails.setCountry(getB2cProp("address.country." + addressType));
        userDetails.setPreferredContact("address.preferredContact." + addressType);
        userDetails.setContactNumber("01234567458");
        userDetails.setDateOfBirth("26-08-1982");
        userDetails.setGender("Male");
        userDetails.setShowPassword("Yes");
        userDetails.setPreferEmail("Yes");
        userDetails.setPreferSMS("Yes");
        userDetails.setShowPassword("Yes");
        userDetails.setShowPassword("Yes");
        userDetails.setPreferMail("Yes");
        userDetails.setPreferPhone("Yes");
        userDetails.setTermsAccept("Yes");
        return userDetails;
    }

    public Registration enterDetailsOnTheRegistrationPage(Registration userDetails) {
        if (userDetails.isGenerateRandomData()) {
            userDetails = createUserRegData(userDetails);
        }
        enterBasicRegistrationDetails(userDetails);
        if ((userDetails.getPostCodeFinder() != null)) {
            enterAddressUsingFinder(userDetails);
            verifyFoundAddress(userDetails);
        } else {
            enterAddressManually(userDetails);
        }

        enterPreferences(userDetails);
        return userDetails;
    }



    private void enterPreferences(Registration userDetails) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(showPassword));
        ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", ""); 
        if (needToCheckTheBox(userDetails.getShowPassword())) {
//            jsClick(waitForExpectedElement(showPassword));
            waitForExpectedElement(showPassword).click();
        }
        if (needToCheckTheBox(userDetails.getPreferMail())) {
            jsClick(waitForExpectedElement(preferMail));
        }
        if (needToCheckTheBox(userDetails.getPreferPhone())) {
            jsClick(waitForExpectedElement(preferPhone));
        }
        if (needToCheckTheBox(userDetails.getPreferEmail())) {
            jsClick(waitForExpectedElement(preferEmail));
        }
        if (needToCheckTheBox(userDetails.getPreferSMS())) {
            jsClick(waitForExpectedElement(preferSMS));
        }
        if (needToCheckTheBox(userDetails.getTermsAccept())) {
            jsClick(waitForExpectedElement(terms));
        }
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click()", element);
    }
    
    private void enterBasicRegistrationDetails(Registration userDetails) {
        waitForExpectedElement(userName).sendKeys(userNameData);
        waitForExpectedElement(email).sendKeys(emailIdData);
        waitForExpectedElement(password).sendKeys(passwordData);
        waitForExpectedElement(title).sendKeys(userDetails.getTitle().trim());
        waitForExpectedElement(firstName).sendKeys(userDetails.getFirstName() + random(4, ALPHABETS));
        waitForExpectedElement(lastName).sendKeys(userDetails.getLastName() + random(4, ALPHABETS));
        waitForExpectedElement(postcode).sendKeys(userDetails.getPostcode());
        waitForExpectedElement(preferredContact).sendKeys(userDetails.getPreferredContact());
        waitForExpectedElement(phone).sendKeys(userDetails.getContactNumber());
        waitForExpectedElement(gender).sendKeys(userDetails.getGender());
        try {
            populateDateOfBirth(userDetails.getDateOfBirth());
        } catch (ParseException e) {
            LOG.info(e.getMessage());
        }
    }

    private void enterAddressManually(Registration userDetails) {
        waitForExpectedElement(manualAddress).click();
        waitForExpectedElement(address1).sendKeys(userDetails.getStreetAddress1());
        waitForExpectedElement(address2).sendKeys(userDetails.getStreetAddress2());
        waitForExpectedElement(city).sendKeys(userDetails.getTownOrCity());
        waitForExpectedElement(state).sendKeys(userDetails.getCounty());
        waitForExpectedElement(country).sendKeys(userDetails.getCountry());
    }

    private void enterAddressUsingFinder(Registration userDetails) {
        waitForExpectedElement(findPostCodeButton).click();
        waitForElementToDisappear(By.cssSelector(".wishLoader"));
        new Select(waitForExpectedElement(selectAddressFromDropDown)).selectByVisibleText(getB2cProp("address.saved." + userDetails.getPostCodeFinder()));

    }

    public void enterPostCode(String postcodeKey) {
        waitForExpectedElement(postcode).sendKeys(getB2cProp(postcodeKey));
        waitForExpectedElement(findPostCodeButton).click();
        waitForElementToDisappear(By.cssSelector(".wishLoader"));
    }

    private void verifyFoundAddress(Registration userDetails) {
        assertThat(waitForExpectedElement(address1).getAttribute("value")).isEqualToIgnoringCase(userDetails.getStreetAddress1());
        assertThat(waitForExpectedElement(address2).getAttribute("value")).isEqualToIgnoringCase(userDetails.getStreetAddress2());
        assertThat(waitForExpectedElement(city).getAttribute("value")).isEqualToIgnoringCase(userDetails.getTownOrCity());
        assertThat(waitForExpectedElement(state).getAttribute("value")).isEqualToIgnoringCase("");
        assertThat(new Select(waitForExpectedElement(country)).getFirstSelectedOption().getText()).isEqualToIgnoringCase(userDetails.getCountry());
    }


    public void onClickOfAButton(String name) {
        switch (name) {
            case "Create Account":
                jsClick(waitForExpectedElement(createAccount));
                break;
            case "Find Address":
            	jsClick(waitForExpectedElement(findAddress));
                break;
            case "Enter address manually":
                jsClick(waitForExpectedElement(manualAddress));
                break;
            default:
                throw new IllegalArgumentException("Invalid parameter" + name);
        }
    }

    private void populateDateOfBirth(String dateOfBirth) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        calendar.setTime(simpleDateFormat.parse(dateOfBirth));
        waitForExpectedElement(birthDay).sendKeys(String.valueOf(calendar.get(Calendar.DATE)));
        //Work around to get the correct numeric value for specified month
        waitForExpectedElement(birthMonth).sendKeys(String.valueOf(calendar.get(Calendar.MONTH) + 1));
        waitForExpectedElement(birthYear).sendKeys(String.valueOf(calendar.get(Calendar.YEAR)));
    }

    private boolean needToCheckTheBox(String value) {
        return "Yes".equalsIgnoreCase(value);
    }

    public List<String> getAllErrorMessages() {
        return visibilityOfAllElementsLocatedBy(errorMsgs).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getTextLabels() {
        waitForExpectedElement(manualAddress).click();
        return getWebDriver().findElements(labels).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getAppropriateErrorMessage() {
        return waitForExpectedElement(errorMessage).getText();

    }

    public boolean isManualAddressVisible() {
        return isElementPresent(registerAddress);
    }
}
