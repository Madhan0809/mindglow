package com.salmon.test.pageobjects.b2c.prescriptions;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPRepeatPrescriptionsPage extends PageObject {
    private By drName = By.name("doctorsName");
    private By drPostcode = By.name("drPostcode");
    private By drAddress1 = By.name("drAddress1");
    private By drCity = By.name("drCity");
    private By drPhone = By.name("drPhone");
    private By repeatsFromLloyds = By.name("repeatsFromLloyds");
    private By otherMeds = By.name("otherMeds");
    private By patientRadio = By.cssSelector("#patientSelector[value=patient]");
    private By representativeRadio = By.cssSelector("#representativeSelector[value=representative]");
    private By personTitleDropdown = By.name("personTitle");
    private By repName = By.name("repName");
    private By repRelationship = By.name("repRelationship");
    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By postCode = By.name("postcode");
    private By address1 = By.name("address1");
    private By address2 = By.name("address2");
    private By city = By.name("city");
    private By state = By.name("state");
    private By mainPhone = By.name("mainPhone");
    private By altPhoneNo = By.name("altPhoneNo");
    private By gender = By.name("gender");
    private By email = By.name("email");
    private By birthDay = By.name("birthDay");
    private By birthMonth = By.name("birthMonth");
    private By birthYear = By.name("birthYear");
    private By nhsNumber = By.name("nhsNumber");
    private By continueButton = By.cssSelector("#drfindaddressbtn");
    private By requiredConsents = By.cssSelector(".requiredConsents input[type='checkbox']:not([disabled='disabled'])");
    private By optionalConsents = By.cssSelector(".optionalConsents input[type='checkbox']:not([disabled='disabled'])");
    private By multiTextBox = By.cssSelector(".patientDetailSection input[type='text']:not([disabled='disabled']):not([name='repRelationship']) , input[type='tel']");
    private By agreePatient = By.cssSelector(".agreePatient  [name=agreePatient]");
    private By agreeRep = By.cssSelector(".agreeRep [name=agreePatient]");

    public void enterDrDetails() {
        waitForExpectedElement(drName,30).sendKeys("Dr Name");
        getWebDriver().findElement(drPostcode).sendKeys("UB10 9DW");
        getWebDriver().findElement(drAddress1).sendKeys("11 Midhurst Gardens");
        getWebDriver().findElement(drCity).sendKeys("Uxbridge");
        getWebDriver().findElement(drPhone).sendKeys("1234567891");
        new Select(getWebDriver().findElement(repeatsFromLloyds)).selectByVisibleText("Yes");
        getWebDriver().findElement(otherMeds).sendKeys("No other meds");
    }

    public void selectPatientOrRepresentative(String patitenOrRepresentative) {
        if (patitenOrRepresentative.equalsIgnoreCase("Patient")) {
        	jsClick(waitForExpectedElement(patientRadio));
        	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, 850)", ""); 
            waitForExpectedElement(agreePatient).click();
        } else {   	
        	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, 950)", "");
        	jsClick(waitForExpectedElement(representativeRadio));
        	waitForExpectedElement(agreeRep).click();
//        	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(agreeRep));         
            assertThat(getWebDriver().findElement(repName).getAttribute("value")).isNotEmpty();
            assertThat(getWebDriver().findElement(repName).getAttribute("disabled")).isEqualToIgnoringCase("true");          
            waitForElementDisplayedAndClickable(repRelationship).sendKeys("Brother");
        	
        }
    }

    public void enterPatientORRepresentativeDetails() {
        List<WebElement> textElements = getWebDriver().findElements(multiTextBox);
        for (WebElement textElement : textElements) textElement.clear();
        new Select(getWebDriver().findElement(personTitleDropdown)).selectByVisibleText(getB2cProp("address.title.work"));
        getWebDriver().findElement(firstName).sendKeys(getB2cProp("address.firstName.work"));
        getWebDriver().findElement(lastName).sendKeys(getB2cProp("address.lastName.work"));
        getWebDriver().findElement(postCode).sendKeys(getB2cProp("address.postCode.work"));
        getWebDriver().findElement(address1).sendKeys(getB2cProp("address.add1.work"));
        getWebDriver().findElement(address2).sendKeys(getB2cProp("address.add2.work"));
        getWebDriver().findElement(city).sendKeys(getB2cProp("address.city.work"));
        getWebDriver().findElement(state).sendKeys(getB2cProp("address.county.work"));
        getWebDriver().findElement(mainPhone).sendKeys(getB2cProp("address.mainPhone.work"));
        getWebDriver().findElement(altPhoneNo).sendKeys(getB2cProp("address.altPhone.work"));
        new Select(getWebDriver().findElement(gender)).selectByVisibleText("Male");
        getWebDriver().findElement(email).sendKeys(getB2cProp("address.email.work"));
        new Select(getWebDriver().findElement(birthDay)).selectByVisibleText("25");
        new Select(getWebDriver().findElement(birthMonth)).selectByVisibleText("11");
        new Select(getWebDriver().findElement(birthYear)).selectByVisibleText("1982");
        getWebDriver().findElement(nhsNumber).sendKeys(RandomGenerator.random(10, PermittedCharacters.ALPHANUMERIC));
    }
    
    private void scrollIntoCurrentView(WebElement e){
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
    }

    private void jsClick(WebElement e){
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", e);
	}

    
    public void selectCheckBoxesAndClickContinue() {
        List<WebElement> requiredCheckBoxElements = this.visibilityOfAllElementsLocatedBy(requiredConsents,10);
        for (WebElement multiCheckBoxElement : requiredCheckBoxElements){
//        	scrollIntoCurrentView(multiCheckBoxElement);
        	jsClick(multiCheckBoxElement);
//            multiCheckBoxElement.click();
        }
        List<WebElement> optionalConsentsElements = getWebDriver().findElements(optionalConsents);
        for (WebElement multiCheckBoxElement : optionalConsentsElements) {
//        	scrollIntoCurrentView(multiCheckBoxElement);
        	jsClick(multiCheckBoxElement);
//            multiCheckBoxElement.click();
        }
        getWebDriver().findElement(continueButton).submit();
    }
}


