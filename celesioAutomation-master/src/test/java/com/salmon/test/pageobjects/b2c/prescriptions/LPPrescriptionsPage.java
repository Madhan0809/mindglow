package com.salmon.test.pageobjects.b2c.prescriptions;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LPPrescriptionsPage extends PageObject {
    private By oneOffPrescription = By.cssSelector(".prescriptionListType[value='oneoff']");
    private By repeatPrescription = By.cssSelector(".prescriptionListType[value='repeat']");
    private By paidSelect = By.cssSelector("[data-type='NHS'] .btnPrimary");
    private By freeSelect = By.cssSelector("[data-type='NHS_EXEMPT'] .btnPrimary");
    private By privateSelect = By.cssSelector("[data-type='PRIVATE'] .btnPrimary");
    private By prescriptionForText = By.name("whoFor");
    private By continuePaymentExempt = By.cssSelector("#exemptionForm .btnAction");
    private By confirmSignedPrescription = By.name("termsConfirm");
    private By confirmTerms = By.name("agreeTerms");
    private By submitOrder = By.cssSelector("#paymentForm .btnAction");
    private By freePostMessage = By.cssSelector(".prescriptionInfo>p>strong");
    private By freePostAddress = By.cssSelector(".prescriptionTerms .address>li");

    private By prescriptionFrequency = By.name("prescriptionFrequency_1");
    private By prescriptionStartDate = By.name("prescriptionStartDate_1");
    private By continueBtn=By.cssSelector("#exemptionForm .btnAction");

    private By patientNameDropDown = By.name("patientId");

    public void selectPrescriptionType(String prescriptionType) {
        if (prescriptionType.equalsIgnoreCase("oneoff")) {
            WebElement oneOffRadio = waitForExpectedElement(oneOffPrescription,20);
            oneOffRadio.click();
            assertThat(oneOffRadio.getAttribute("checked")).isEqualTo("true");
            getWebDriver().findElement(prescriptionForText).sendKeys(RandomGenerator.random(5, PermittedCharacters.ALPHABETS));
        } else {
            WebElement repeatRadio = waitForExpectedElement(repeatPrescription);
            repeatRadio.click();
            assertThat(repeatRadio.getAttribute("checked")).isEqualTo("true");
            new Select(getWebDriver().findElement(prescriptionFrequency)).selectByValue("28");

            DateTime now = new DateTime();
            String pattern = "dd/MM/yyyy";
            DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
            String formatted = formatter.print(now);


            getWebDriver().findElement(prescriptionStartDate).sendKeys(formatted);


        }
    }

    public void selectNHSPrescriptionPayType(String prescriptionPayType) {
        if (prescriptionPayType.equalsIgnoreCase("paid")) {
            getWebDriver().findElement(paidSelect).click();
        } else if (prescriptionPayType.equalsIgnoreCase("free")) {
            getWebDriver().findElement(freeSelect).click();
        } else if (prescriptionPayType.equalsIgnoreCase("private")) {
            getWebDriver().findElement(privateSelect).click();
        }
    }

    public void selectPaymentExemptionReason(String exemptReason) {
        ((JavascriptExecutor)getWebDriver()).executeScript("window.scrollTo(0, -(document.body.scrollHeight))");
        Actions action=new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(By.cssSelector("input[value='" + getB2cProp(exemptReason) + "']"))).build().perform();
        ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", waitForExpectedElement(By.cssSelector("input[value='" + getB2cProp(exemptReason) + "']")));
//        waitForExpectedElement(By.cssSelector("input[value='" + getB2cProp(exemptReason) + "']")).click();
//        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",  waitForExpectedElement(continueBtn));
        action.moveToElement(waitForExpectedElement(continueBtn)).build().perform();
        waitForExpectedElement(continueBtn).click();
    }

    public void confirmSignedPrescriptionAndTerms() {
//        confirmSignedPrescription();
        getWebDriver().findElement(confirmTerms).click();
        getWebDriver().findElement(submitOrder).click();
    }


    public void confirmSignedPrescription() {
        waitForExpectedElement(confirmSignedPrescription).click();
        assertThat(getWebDriver().findElement(freePostMessage).getText()).containsIgnoringCase(getB2cProp("freepost.message"));
        String freeAddress = getWebDriver().findElements(freePostAddress).stream().map(WebElement::getText).collect(Collectors.joining(" "));
        assertThat(freeAddress).containsIgnoringCase(getB2cProp("freepost.address"));
    }

    public void selectPatientName(String patientNameType){
        new Select(waitForExpectedElement(patientNameDropDown)).selectByVisibleText(patientNameType);
    }

}