package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LPCheckOutBasketPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPCheckOutBasketPage.class);
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By signOut = By.linkText("Sign Out");
    private By departmentElements = By.cssSelector("#mainCategoriesList .mainCategoryLink");
    private By categoryElements = By.cssSelector("#mainCategoriesList>li:nth-of-type("
            + LloydsPharmacyConstants.CATEGORY_NUMBER + ") .subCategoriesList li a");
    private By productList = By.cssSelector(".productList .product");
    private By productTitle = By.cssSelector(".productContent a");
    //    private By plpAddToBasket = By.cssSelector(".product_listing_container ul:nth-child(1) li:nth-child(1) .product .add-to-basket a");
    private By basketHeaderTextLabels = By.cssSelector("#basketTable #basketTableHeader div");
    private By addToBasket = By.cssSelector(".productFooter a");
    private By headerCheckOut = By.cssSelector("#basketHeader a.btnAction");
    private By basketItemCount = By.cssSelector("#basketHeader h1 .itemsCount");
    private By basketItemPrice = By.cssSelector("#basketHeader h1 .price");
    private By freeDeliveryBanner = By.cssSelector("#freeDeliveryBanner");
    private By basketItemList = By.cssSelector("#basketTable .basketListItem");
    private By eachItemPrice = By.cssSelector(".basketListItem>div:nth-child(3)");
    private By totalPriceBeforeDiscount = By.cssSelector(".basketListItem>div:nth-child(4)");
    private By deliveryOptions = By.cssSelector(".basketListItem .deliveryOptions strong");
    private By itemName = By.cssSelector(".itemDesc .itemTitle");
    private By itemQuantity = By.cssSelector(".qtyInput");
    private By updateQuantity = By.cssSelector(".updateItemBtn");
    private By removeItem = By.cssSelector(".removeItemLink");
    private By orderSubTotalPrice = By.cssSelector("#basketTotals #order_total span:nth-child(1) .total_figures");
    private By vatRelief = By.cssSelector("#basketTotals #order_total span:nth-child(2) .value");
    private By orderTotalPrice = By.cssSelector("#basketTotals #order_total .total .breadcrumb_current");
    private By discount = By.cssSelector("#order_total span:nth-last-child(2) .total_figures");
    private By actionCheckOut = By.cssSelector("#basketActions .btnAction");
    private By continueShopping = By.cssSelector("#basketActions #continueShoppingButton a");
    private By applyPromoCode = By.cssSelector("a[id^='WC_PromotionCodeDisplay']");
    private By promotionBtn = By.cssSelector(".promotionButton a.btnSecondary");
    private By promoCode = By.cssSelector("#promoCode");
    private By miniShoppingCart = By.id("MiniShoppingCart");
    private By miniShoppingCartTotal = By.cssSelector("#miniBasket a #minishopcart_total");
    private By miniShoppingCartDropDown = By.cssSelector("div#cartDropdown");
    private By checkOut = By.cssSelector("#MiniShoppingCart .miniBasketContents .footer a");
    private By miniCartCloseBtn = By.cssSelector("#cartDropdown a.closeButton");
    private By closePopupBtn=By.cssSelector(".iClose");
    private By emptyBasketMsg = By.cssSelector("#shopCartDisplay div:nth-child(2) div");
    private By checkOutSignIn = By.name("signIn");
    private By homePage = By.id("homepage");
    private By msgPopup = By.cssSelector("#msgpopup_content_wrapper #ErrorMessageText");
    private By closePopup = By.cssSelector("#msgpopup_content_wrapper a.closeLink .iClose");
    private By popUpMsgArea = By.cssSelector("#MessageArea");
    private By emptyPromoCodeMsg = By.cssSelector("#msgpopup_content_wrapper #ErrorMessageText");
    private By signIn = By.linkText("Sign In");
    private By loginIDInput = By.cssSelector("#pageLoginForm [name=logonId]");
    private By passwordInput = By.cssSelector("#pageLoginForm [name=logonPassword]");
    private By loginButton = By.cssSelector("#pageLoginForm [type=submit]");
    private By itemDescription = By.cssSelector(".itemDesc .row");
    private By cookiePolicyBar=By.cssSelector(".cookiePolicy");
    private By cookiePolicyBtn=By.cssSelector(".cookiePolicy button");
    
    public void goToCheckOutBasketPage() {
        LOG.info("Navigating to Lloyds Pharmacy Checkout basket page \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        if(isPresent(cookiePolicyBar)){
        	waitForExpectedElement(cookiePolicyBtn).click();
        }
        browseToCheckOutBasket();
    }

    public void goToCheckOutSignInPageAsARegisteredUser() {
        LOG.info("Navigating to Lloyds Pharmacy Checkout basket page as a registered user \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        if(isPresent(cookiePolicyBar)){
            waitForExpectedElement(cookiePolicyBtn).click();
        }
        waitForExpectedElement(signIn).click();
        waitForExpectedElement(loginIDInput).sendKeys(getProp("b2c.username"));
        getWebDriver().findElement(passwordInput).sendKeys(getProp("b2c.password"));
        getWebDriver().findElement(loginButton).click();
        browseToCheckOutBasket();
    }

    private void browseToCheckOutBasket() {
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        //select first department form the list
        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        if (!departmentElementsList.isEmpty()) {
            action.moveToElement(departmentElementsList.get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).build().perform();
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
        action.moveToElement(getWebDriver().findElement(miniShoppingCart)).perform();
        waitForElementDisplayedAndClickable(checkOut).click();
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList);
    }

    public Map<String, String> getBasketHeaderDetails() {
        Map<String, String> basketHeaderMap = new HashMap();
        basketHeaderMap.put(LloydsPharmacyConstants.BASKET_ITEM_QUANTITY, waitForExpectedElement(basketItemCount,30).getText());
        basketHeaderMap.put(LloydsPharmacyConstants.BASKET_ITEM_PRICE, getWebDriver().findElement(basketItemPrice).getText());
        basketHeaderMap.put(LloydsPharmacyConstants.MINI_BASKET_TOTAL, getWebDriver().findElement(miniShoppingCartTotal).getText().replaceAll("[:]+", ""));
        return basketHeaderMap;
    }

    public Map<String, String> getBasketItemDetails() {
        Map<String, String> basketDetailsMap = new HashMap();
        basketDetailsMap.put(LloydsPharmacyConstants.ITEM_NAME, getBasketItemList().get(0).findElement(itemName).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.ITEM_QUANTITY, getBasketItemList().get(0).findElement(itemQuantity).getAttribute("value"));
        basketDetailsMap.put(LloydsPharmacyConstants.ITEM_PRICE, getBasketItemList().get(0).findElement(eachItemPrice).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.TOTAL_PRICE_BEFORE_DISCOUNT, getBasketItemList().get(0).findElement(totalPriceBeforeDiscount).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.DELIVERY_OPTIONS, getDeliveryOptions());
        basketDetailsMap.put(LloydsPharmacyConstants.ORDER_SUB_TOTAL_PRICE, getWebDriver().findElement(orderSubTotalPrice).getText());
//        basketDetailsMap.put(LloydsPharmacyConstants.VAT_RELIEF, getWebDriver().findElement(vatRelief).getText());
//        basketDetailsMap.put(LloydsPharmacyConstants.DISCOUNT, getWebDriver().findElement(discount).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.ORDER_TOTAL_PRICE, getWebDriver().findElement(orderTotalPrice).getText());
        return basketDetailsMap;
    }

    public String getDeliveryOptions() {
        String delivOptions = "";
        List<WebElement> delivElements = getBasketItemList().get(0).findElements(deliveryOptions);
        for (WebElement element : delivElements) {
            delivOptions = delivOptions + element.getText() + ", ";
        }
//        delivElements.stream().map(WebElement::getText).map(delOptions -> delOptions+", ");
        return delivOptions.substring(0, delivOptions.trim().length() - 1);
    }

    public List<WebElement> getBasketItemList() {
        return visibilityOfAllElementsLocatedBy(basketItemList);
    }

    public boolean isFreeDeliveryBannerVisible() {
        return getWebDriver().findElement(freeDeliveryBanner).isDisplayed();
    }

    public void updateItemQuantity(String quantity) {
        waitForExpectedElement(itemQuantity).clear();
        waitForExpectedElement(itemQuantity).sendKeys(quantity);
        waitForExpectedElement(updateQuantity).click();
    }

    public Map<String, String> getBasketItemDetailsAfterUpdate() {
        Map<String, String> basketDetailsMap = new HashMap();
        basketDetailsMap.put(LloydsPharmacyConstants.ITEM_QUANTITY, getBasketItemList().get(0).findElement(itemQuantity).getAttribute("value"));
        basketDetailsMap.put(LloydsPharmacyConstants.TOTAL_PRICE_BEFORE_DISCOUNT, getBasketItemList().get(0).findElement(totalPriceBeforeDiscount).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.ORDER_SUB_TOTAL_PRICE, getWebDriver().findElement(orderSubTotalPrice).getText());
        //basketDetailsMap.put(LloydsPharmacyConstants.DISCOUNT, getWebDriver().findElement(discount).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.ORDER_TOTAL_PRICE, getWebDriver().findElement(orderTotalPrice).getText());
        basketDetailsMap.put(LloydsPharmacyConstants.MINI_BASKET_TOTAL, getWebDriver().findElement(miniShoppingCartTotal).getText().replaceAll("[:]+", ""));
        return basketDetailsMap;
    }

    public void removeItemFromBasket(String item) {
        if (LloydsPharmacyConstants.REMOVE_BASKET_ITEM.equalsIgnoreCase(item)) {
            getBasketItemList().get(1).findElement(removeItem).click();
        } else if (LloydsPharmacyConstants.REMOVE_LAST_BASKET_ITEM.equalsIgnoreCase(item)) {
        	if(!isElementPresent(removeItem)){
        		LOG.error("remove link is not presented now.");
        	}
        	for(int i=0;i<getWebDriver().findElements(basketItemList).size();i++){
        		waitForElementDisplayedAndClickable(removeItem).click();
        	}   
        }
    }

    public void addItemToBasket() {
        waitForExpectedElement(continueShopping,20).click();
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
            if (LloydsPharmacyConstants.PRODUCT_TO_ADD_ON_PLP_2.equalsIgnoreCase(compareElement.findElement(productTitle).getText())) {
                compareElement.findElement(addToBasket).click();
                break;
            }
        }
        action.moveToElement(waitForExpectedElement(miniShoppingCart)).perform();
        waitForExpectedElement(checkOut).click();
    }

    public String getEmptyBasketMessage() {
        return waitForExpectedElement(emptyBasketMsg).getText();
    }
    
    private void jsClick(By by){
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", waitForExpectedElement(by));
	}


    public void onClickOfBasketPageActions(String button) {
        switch (button) {
            case LloydsPharmacyConstants.HEADER_CHECKOUT:
            	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(headerCheckOut));    	
            	jsClick(headerCheckOut);
                break;
            case LloydsPharmacyConstants.BASKET_ACTIONS_CHECKOUT:
                waitForExpectedElement(actionCheckOut).click();
                break;
            case LloydsPharmacyConstants.CONTINUE_SHOPPING:
                waitForExpectedElement(continueShopping).click();
                break;
            case LloydsPharmacyConstants.PROMO_CODE_APPLY:
//            	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(applyPromoCode));    	
                waitForExpectedElement(applyPromoCode).click();
                break;
            default:
                throw new IllegalArgumentException("No such " + button + " parameter exists.");
        }
    }

    public void enterPromotionalCode(String code) {
        waitForExpectedElement(promoCode).sendKeys(code);
    }

    public boolean isNavigatedToRelevantCheckOutSignInPage() {
        boolean isCheckOutSignIn = false;
        if (waitForExpectedElement(checkOutSignIn).isDisplayed()) {
            isCheckOutSignIn = true;
        }
        getWebDriver().navigate().back();
        return isCheckOutSignIn;
    }

    public boolean isNavigatedToRelevantHomePage() {
        return waitForExpectedElement(homePage).isDisplayed();
    }

    public List<String> getBasketHeaderTextLabels() {
        List<WebElement> textLabels = visibilityOfAllElementsLocatedBy(basketHeaderTextLabels);
        return textLabels.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getPopupMessage() {
    	if(!isElementPresent(msgPopup)){
    		LOG.warn("The popup message is not displayed for the moment.");		
    	}
        return getWebDriver().findElement(msgPopup).getText();
    }
    
    public void closePopup(){
    	if(isElementPresent(closePopupBtn)){
    		getWebDriver().findElement(closePopupBtn).click();
    	}   	
    	getWebDriver().switchTo().defaultContent();
    }

	public void onClickOfClosePopupPanel() {
		if (isElementPresent(closePopup)) {
			LOG.info("Click on close icon in popup.");
			getWebDriver().findElement(closePopup).click();
		}
	}

    public boolean isPopUpPanelClosed() {
        return waitForElementToDisappear(popUpMsgArea);
    }

    public String getEmptyPromoCodeMessage() {
        return waitForExpectedElement(emptyPromoCodeMsg).getText();
    }

    public void verifyItemDescription(String itemValue) {
        String actualValue = this.visibilityOfAllElementsLocatedBy(itemDescription).stream().map(WebElement::getText).collect(Collectors.joining(" "));
        assertThat(actualValue).containsIgnoringCase(getB2cProp(itemValue));
    }
}
