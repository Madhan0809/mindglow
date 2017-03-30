package com.salmon.test.pageobjects.b2c.home;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LPLoginPage extends PageObject {
    private By signIn = By.linkText("Sign In");
    private By signOut = By.linkText("Sign Out");
    private By loginForm = By.cssSelector("[name='pageLoginForm']");
    private By logonLabel = By.cssSelector("[name='pageLoginForm'] label");
    private By loginButton = By.cssSelector("[name='pageLoginForm'] input[name='signIn']");
    private By forgotPassword = By.cssSelector("[name='pageLoginForm'] a[name='forgotPass']");
    private By noAccount = By.cssSelector(".register p");
    private By register = By.cssSelector("a[name='registerLink']");
    private By loginIDInput = By.cssSelector("[name='pageLoginForm'] input[name='logonId']");
    private By passwordInput = By.cssSelector("[name='pageLoginForm'] input[name='logonPassword']");
    private By errorMessage = By.cssSelector(".errorMsg");
    private By loginDropdown = By.cssSelector("#Header_GlobalLogin_signInDropdown");
    private By productTitle = By.cssSelector(".productContent a");
    private By productList = By.cssSelector(".productList .product");
    private By registrationPage = By.cssSelector(".registerForm .formContainer>h1");
    private By addToBasketButton = By.cssSelector("a.btnPrimary");

    public void onClickOfSignIn() {
        waitForExpectedElement(signIn).click();
    }

    public boolean isSignInShown() {
        return isElementPresent(loginForm);
    }

    public List<String> getLoginPageLabelDetails() {
        List<String> overlayDetailsList = new ArrayList();
        overlayDetailsList.add(getWebDriver().findElements(logonLabel).get(0).getText());
        overlayDetailsList.add(getWebDriver().findElements(logonLabel).get(1).getText());
        overlayDetailsList.add(getWebDriver().findElement(loginButton).getAttribute("value"));
        overlayDetailsList.add(getWebDriver().findElement(forgotPassword).getText());
        //overlayDetailsList.add(getWebDriver().findElement(noAccount).getText());
        overlayDetailsList.add(getWebDriver().findElement(register).getText());
        return overlayDetailsList;
    }

    public void onClickOfButton(String button) {
        switch (button) {
            case LloydsPharmacyConstants.SIGN_IN:
                waitForExpectedElement(loginButton).click();
                break;
            case LloydsPharmacyConstants.REGISTER:
                waitForExpectedElement(register).click();
                break;
        }
    }

    public boolean isRegisterOpen() {
        return waitForExpectedElement(registrationPage).getText().equals("Register with LloydsPharmacy");
    }

    public void inputLoginIDPWD(String loginID, String password) {
        waitForExpectedElement(loginIDInput).sendKeys(loginID);
        getWebDriver().findElement(passwordInput).sendKeys(password);
    }

    public String getTypeofLogonID() {
        return getWebDriver().findElement(loginIDInput).getAttribute("type");
    }

    public boolean isSignIn() {
        return waitForExpectedElement(signOut).getText().equals("Sign Out");
    }

    public String getErrorMessage() {
        return waitForExpectedElement(errorMessage).getText();
    }

    public boolean isSignInDisappear() {
        return waitForElementToDisappear(loginDropdown);
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList,20);
    }

    public void clickOnProductTitle(String productName) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement compareElement : compareElementsList) {
            if (productName.equalsIgnoreCase(compareElement.findElement(productTitle).getText())) {
                compareElement.findElement(productTitle).click();
                break;
            }
        }
    }
}
