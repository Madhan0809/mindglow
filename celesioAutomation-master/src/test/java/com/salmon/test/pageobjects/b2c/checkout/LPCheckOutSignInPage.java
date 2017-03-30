package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LPCheckOutSignInPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPCheckOutSignInPage.class);
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By signOut = By.linkText("Sign Out");
    private By logo=By.cssSelector("div.row.brandSearch a");
    private By departmentElements = By.cssSelector("#mainCategoriesList .mainCategoryLink");
    private By categoryElements = By.cssSelector("#mainCategoriesList>li:nth-of-type("
            + LloydsPharmacyConstants.CATEGORY_NUMBER + ") .subCategoriesList li a");
    private By addToBasketButton = By.cssSelector("a[title='Add to basket']");
    private By miniShopcart = By.cssSelector("#widget_minishopcart");
    private By subtotalMessage = By.cssSelector("#minishopcart_total");
    //	private By gotoCart = By.cssSelector("#GotoCartButton1>span");
//	private By gotoCart= By.cssSelector(".right.btn.btnPrimary");
    private By shoppingCartBox = By.cssSelector(".shopping_cart_box");
    //	private By secureCheckout = By.cssSelector("#basketActions .btnAction");
    private By checkOutSignIn = By.cssSelector(".signInPanel h1");
    private By checkOutLoginID = By.cssSelector("#pageLoginForm [name=logonId]");
    private By checkOutPassword = By.cssSelector("#pageLoginForm [name=logonPassword]");
    private By signInButton = By.cssSelector("#pageLoginForm [type=submit]");
    private By registerButton = By.cssSelector("[name=registerLink]");
    private By registerPageTitle = By.cssSelector(".registerForm .formContainer>h1");
    private By errorMessage = By.cssSelector(".errorMsg");
    private By deliveryTitle = By.cssSelector("#deliveryOptionsPage h1");
    private By addToBasket = By.cssSelector(".productFooter a");
    private By miniShoppingCart = By.id("MiniShoppingCart");
    private By checkOut = By.cssSelector("#MiniShoppingCart .footer a");
    private By productList = By.cssSelector(".productList .product");
    private By actionSecureCheckOut = By.cssSelector("#basketActions .btnAction");
    private By productTitle = By.cssSelector(".productContent a");
    private By guestCheckOut = By.linkText("Checkout Now");
    private By cookiePolicyBar=By.cssSelector(".cookiePolicy");
    private By cookiePolicyBtn=By.cssSelector(".cookiePolicy button");
    
    public void goToCheckOutSignInPage() {
        LOG.info("Navigating to Lloyds Pharmacy Checkout Sign-in page \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        if(isPresent(cookiePolicyBar)){
        	waitForExpectedElement(cookiePolicyBtn).click();
        }
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        //select first department form the list
        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        if (!departmentElementsList.isEmpty()) {
            action.moveToElement(departmentElementsList.get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).perform();
        }
        //select first category form the list
        List<WebElement> categoryElementsList = visibilityOfAllElementsLocatedBy(categoryElements);
        if (!categoryElementsList.isEmpty()) {
            action.moveToElement(categoryElementsList.get(LloydsPharmacyConstants.CATEGORY_INDEX)).click().perform();
        }
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement compareElement : compareElementsList) {
            if (LloydsPharmacyConstants.PRODUCT_TO_ADD_ON_PLP.equalsIgnoreCase(compareElement.findElement(productTitle).getText())) {
                compareElement.findElement(addToBasket).click();
                break;
            }
        }
        action.moveToElement(waitForExpectedElement(miniShoppingCart,20)).perform();
        waitForExpectedElement(checkOut).click();
        waitForExpectedElement(actionSecureCheckOut,20).click();
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList);
    }

    public void onClickOfAddToBasket() {
        waitForExpectedElement(addToBasketButton,20).click();
    }

    public void onClickOfMiniShopcart() {
        WebElement miniShopCartWidget=waitForExpectedElement(miniShopcart,20);
    	Actions action = new Actions(getWebDriver());
        action.moveToElement(miniShopCartWidget).click().build().perform();
//        waitForExpectedElement(miniShopcart,20).click();
    }

    public String getSubtotal() {
        return waitForExpectedElement(subtotalMessage).getText();
    }

	/*public void onClickOfGoToCart() {
        waitForExpectedElement(gotoCart);
		getWebDriver().findElement(gotoCart).click();	
	}*/

    public boolean isShopCartPage() {
        return isElementPresent(shoppingCartBox);
    }

    public void onClickOfSecureCheckout() {	
		jsClick(waitForExpectedElement(actionSecureCheckOut,20));
    }
    
    private void jsClick(WebElement e){
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click()", e);
    }

    public String getSignInHeader() {
    	if(!isElementPresent(checkOutSignIn)){
    		LOG.error("The checkout signIn button is not presented.");
    	}
        return getWebDriver().findElement(checkOutSignIn).getText();
    }

    public void inputLoginIDPWD(String loginID, String password) {
//		waitForExpectedElement(checkOutLoginID);
        waitForExpectedElement(checkOutLoginID).clear();
        getWebDriver().findElement(checkOutLoginID).sendKeys(loginID);
        getWebDriver().findElement(checkOutPassword).clear();
        getWebDriver().findElement(checkOutPassword).sendKeys(password);
    }

    public void onClickOfSignInButton() {
        waitForExpectedElement(signInButton).click();
    }

    public void onClickOfButton(String button) {
        switch (button) {
            case LloydsPharmacyConstants.SIGN_IN:
            	jsClick(waitForExpectedElement(signInButton));
//                waitForExpectedElement(signInButton).click();
                break;
            case LloydsPharmacyConstants.REGISTER:
                waitForExpectedElement(registerButton).click();
                break;
            case LloydsPharmacyConstants.CHECKOUT_NOW:
                waitForExpectedElement(guestCheckOut).click();
                break;
        }
    }

    public boolean isRegisterOpen() {
        return waitForExpectedElement(registerPageTitle).getText().equals("Register with the LloydsPharmacy Group");
    }

    public String getErrorMessage() {
        return waitForExpectedElement(errorMessage).getText();
    }

    public String getDeliveryPage() {
        return waitForExpectedElement(deliveryTitle,20).getText();
    }
}
