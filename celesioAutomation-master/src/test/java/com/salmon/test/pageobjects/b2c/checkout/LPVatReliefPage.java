package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LPVatReliefPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPVatReliefPage.class);
    
    private By vatReliefYes = By.cssSelector("#vatReliefClaim-Yes");
    private By vatReliefNo = By.cssSelector("#vatReliefClaim-No");
    private By continueVatRelief = By.cssSelector("#submitPart1");
    private By vatReliefForMe = By.cssSelector("input[value='Me']");
    private By vatReliefForSomeOneElse = By.cssSelector("input[value='SomeoneElse']");
    private By vatReliefForUkCharity = By.cssSelector("input[value='UKRegisteredCharity']");
    private By reasonForClaim = By.name("reasonForClaim");
    private By declareLabel=By.cssSelector(".declaration");
    private By declarationCheckBox = By.cssSelector("#declaration-checkbox");
    private By submit = By.cssSelector(".submit .btnPrimary");
    private By relationshipToClaimant = By.name("relationshipToClaimant");
    private By claimantTitle = By.name("claimantTitle");
    private By claimantFirstName = By.name("claimantFirstName");
    private By claimantLastName = By.name("claimantLastName");
    private By claimantPostcode = By.name("claimantPostcode");
    private By claimantAddress = By.name("claimantAddress");
    private By claimantAddress2 = By.name("claimantAddress2");
    private By claimantTown = By.name("claimantTown");
    private By claimantCounty = By.name("claimantCounty");
    private By claimantCountry = By.name("claimantCountry");
    private By yourTitle = By.name("yourTitle");
    private By yourFirstName = By.name("yourFirstName");
    private By yourLastName = By.name("yourLastName");
    private By yourPosition = By.name("yourPosition");
    private By charityName = By.name("charityName");
    private By charityNumber = By.name("charityNumber");
    private By charityPostcode = By.name("charityPostcode");
    private By charityAddress = By.name("charityAddress");
    private By charityTown = By.name("charityTown");
    private By charityCounty = By.name("charityCounty");
    private By charityCountry = By.name("charityCountry");

    public void selectVatReliefAndChooseARandomReason(boolean vatRelief) {
        selectVatReliefAndChooseARandomReason(vatRelief, "Me");
    }

    public void selectVatReliefAndChooseARandomReason(boolean vatRelief, String vatReliefFor) {
        if (vatRelief) {
            Actions builder = new Actions(webDriver);
            builder.moveToElement(waitForExpectedElement(vatReliefYes)).perform();
            waitForExpectedElement(vatReliefYes).click();
            getWebDriver().findElement(continueVatRelief).click();
            if (vatReliefFor.equalsIgnoreCase("Me")) {
                LOG.info("Selection claim of vat relief for me.");
                waitForExpectedElement(vatReliefForMe).click();         
                new Select(getWebDriver().findElement(reasonForClaim)).selectByVisibleText("Arthritis");
                builder.moveToElement(waitForExpectedElement(declareLabel)).perform();
                waitForExpectedElement(declareLabel).click();
            } else if (vatReliefFor.equalsIgnoreCase("SomeoneElse")) {
                LOG.info("Input information for vat relief for someone else.");
                vatReliefForSomeoneElse();
                waitForExpectedElement(declareLabel).click();
//                ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(declarationCheckBox));
            } else if (vatReliefFor.equalsIgnoreCase("UkRegisteredCharity")) {
                LOG.info("Input information for UK register charity.");
                vatReliefForUKCharity();
            }         
            builder.moveToElement(waitForExpectedElement(declarationCheckBox)).perform();
//            ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", waitForExpectedElement(declarationCheckBox));
            if(!waitForExpectedElement(declarationCheckBox).isSelected()){
                LOG.info("Check the checkbox of declarition.");
                waitForExpectedElement(declarationCheckBox).click();
            }
            waitForExpectedElement(submit).click();
        } else {
            waitForExpectedElement(vatReliefNo).click();
            waitForExpectedElement(continueVatRelief).click();
        }
    }

    private void vatReliefForSomeoneElse() {
        waitForExpectedElement(vatReliefForSomeOneElse).click();
        new Select(waitForExpectedElement(relationshipToClaimant)).selectByVisibleText("Spouse");
        new Select(getWebDriver().findElement(claimantTitle)).selectByVisibleText("Mr");
        getWebDriver().findElement(claimantFirstName).sendKeys("ClaimFirst");
        getWebDriver().findElement(claimantLastName).sendKeys("ClaimLast");
        getWebDriver().findElement(claimantPostcode).sendKeys("SL1 3TE");
        getWebDriver().findElement(claimantAddress).sendKeys("16 Stranraer");
        getWebDriver().findElement(claimantAddress2).sendKeys("Gardens");
        getWebDriver().findElement(claimantTown).sendKeys("Slough");
        getWebDriver().findElement(claimantCounty).sendKeys("Berkshire");
        new Select(getWebDriver().findElement(claimantCountry)).selectByVisibleText("United Kingdom");
        new Select(getWebDriver().findElement(reasonForClaim)).selectByVisibleText("Arthritis");
    }

    private void vatReliefForUKCharity() {
        waitForExpectedElement(vatReliefForUkCharity).click();
        getWebDriver().findElement(yourTitle).sendKeys("Mr");
        getWebDriver().findElement(yourFirstName).sendKeys("YourFirst");
        getWebDriver().findElement(yourLastName).sendKeys("YourLast");
        getWebDriver().findElement(yourPosition).sendKeys("Manager");
        getWebDriver().findElement(charityName).sendKeys("OXFAM");
        getWebDriver().findElement(charityNumber).sendKeys("123456");
        getWebDriver().findElement(charityPostcode).sendKeys("SL1 3TE");
        getWebDriver().findElement(charityAddress).sendKeys("16 Stranraer Gardens");
        getWebDriver().findElement(charityTown).sendKeys("Slough");
        getWebDriver().findElement(charityCounty).sendKeys("Berkshire");
        new Select(getWebDriver().findElement(charityCountry)).selectByVisibleText("United Kingdom");
    }
}
