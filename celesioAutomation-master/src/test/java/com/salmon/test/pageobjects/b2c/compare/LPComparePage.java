package com.salmon.test.pageobjects.b2c.compare;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LPComparePage extends PageObject {
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By departmentElements = By.cssSelector("#mainCategoriesList .mainCategoryLink");
    private By categoryElements = By.cssSelector("#mainCategoriesList>li:nth-of-type("
            + LloydsPharmacyConstants.CATEGORY_NUMBER + ") .subCategoriesList li a");
    private By compareButton = By.cssSelector(".btnCompare");
    private By headerLogo = By.cssSelector(".brandSearch a");
    private By compareBreadcrumb = By.cssSelector(".current");
    private By footerHeaderCustomerService = By.cssSelector(".collapsibleContainer div:nth-child(1) .fLinksWrap");
    private By footerHeaderShopWithUs = By.cssSelector(".collapsibleContainer div:nth-child(2) .fLinksWrap");
    private By footerHeaderOurServices = By.cssSelector(".collapsibleContainer div:nth-child(3) .fLinksWrap");
    private By backToAccessories = By.cssSelector(".backLink");
    private By compareUp4Product = By.cssSelector(".headingGrey");
    private By compareHeaderTitle = By.cssSelector(".compareWrapper.rowContainer>div:nth-child(2)>div h1");
    private By pdpHeader = By.cssSelector(".productName>h1");
    private By accessoriesCategory = By.cssSelector("#widget_breadcrumb>ul>li:nth-child(3)>a");
    private By recentPanelImg = By.cssSelector("a>img[id^='productThumbNailImage']");
    private By miniBasketTotal = By.cssSelector("#minishopcart_total");
    private By compareProducts = By.cssSelector("#compareProducts .productItem");
    private By addToBasket = By.cssSelector(".btnPrimary");
    private By remove = By.cssSelector(".btnRemove");
    private By productImage = By.cssSelector("a img");
    private By productQuantity = By.cssSelector(".qtyBox input[name='quantity']");
    private By productList = By.cssSelector("#plpContent .productList .product");
    private By productName = By.cssSelector(".productContent a");
    private By compareBox = By.cssSelector(".compare input[type='checkbox']");
    private By attribute = By.cssSelector(".productAttribute strong");
    private By emptyMessage = By.cssSelector(".errorLabel");
    private By compareLabel=By.cssSelector("label.compareLabel");
    
    public void onSelectionOfProductsToCompare(List<String> products) { 
    	((JavascriptExecutor)getWebDriver()).executeScript("window.scrollBy(0, 650)", ""); 
 	List<WebElement> productElementList = this.visibilityOfAllElementsLocatedBy(productList,3);   
        for(int i=0;i<productElementList.size();i++){
        	WebElement currentProduct=getWebDriver().findElements(productList).get(i); 	
        	if(products.contains(currentProduct.findElement(productName).getText())){
//        		currentProduct.click();
        		currentProduct.findElement(compareBox).click();
//        		waitForExpectedElement(compareLabel).click();
        	}
        }
//    	List<WebElement> productElementList = visibilityOfAllElementsLocatedBy(productList);     
//        for (WebElement productElement : productElementList) {
//            if (products.contains(productElement.findElement(productName).getText())) {
//                productElement.findElement(compareBox).click();
//            }
//        }
    }

    public boolean isCompareShown() {
        return isElementPresent(compareBreadcrumb);
    }

    public boolean isHeaderLogoShown() {
        return isElementPresent(headerLogo);
    }

    public boolean isFooterShown() {
        return isElementPresent(footerHeaderCustomerService) &&
                isElementPresent(footerHeaderShopWithUs) &&
                isElementPresent(footerHeaderOurServices);
    }

    public String gettextLabel(String textLabel) {
        String label = "";
        switch (textLabel) {
            case LloydsPharmacyConstants.BACK_TO_ACCESSORIES:
                label = getWebDriver().findElement(backToAccessories).getText();
                break;
            case LloydsPharmacyConstants.COMPARE_UP_4_PRODUCTS:
                label = getWebDriver().findElement(compareUp4Product).getText();
                break;
            case LloydsPharmacyConstants.COMPARE_PRODUCTS:
                label = getWebDriver().findElement(compareHeaderTitle).getText();
                break;
        }
        return label;
    }

    public List<String> gettextLabels() {
        List<String> labels = new ArrayList();
        labels.add(getWebDriver().findElement(compareHeaderTitle).getText());
        labels.add(waitForExpectedElement(backToAccessories).getText());
        labels.add(getWebDriver().findElement(compareUp4Product).getText());
        return labels;
    }

    public void onClickOftextLabel(String textLabel) {
        switch (textLabel) {
            case LloydsPharmacyConstants.BACK_TO_ACCESSORIES:
                getWebDriver().findElement(backToAccessories).click();
                break;
        }
    }

    public void onClickOfProduct(String product) {
        List<WebElement> compareElementList = visibilityOfAllElementsLocatedBy(compareProducts);
        for (WebElement productElement : compareElementList) {
            if (product.equalsIgnoreCase(productElement.findElement(productImage).getAttribute("alt"))) {
                productElement.findElement(productImage).click();
                break;
            }
        }
    }

    public void onClickOfCompareButton() {
//        visibilityOfAllElementsLocatedBy(productList).get(0).findElement(compareButton).click();
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", getWebDriver().findElements(compareButton).get(0));
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", visibilityOfAllElementsLocatedBy(productList).get(0).findElement(compareButton));
    }

    public String verifyPDPHeader() {
        waitForExpectedElement(pdpHeader);
        return getWebDriver().findElement(pdpHeader).getText();
    }

    public void onClickOfSubCategory(String category) {
        Actions action = new Actions(getWebDriver());
        switch (category) {
            case LloydsPharmacyConstants.ACCESSORIES_CATEGORY:
                action.moveToElement(getWebDriver().findElement(accessoriesCategory)).click().perform();
                break;
        }
    }

    public boolean isRecentViewPanelDisplayed() {
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(recentPanelImg,25));
        return isElementPresent(recentPanelImg);
    }

    public void onClickOfAccessories() {
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        List<WebElement> departmentElementsList = getWebDriver().findElements(departmentElements);
        if (!departmentElementsList.isEmpty()) {
            action.moveToElement(departmentElementsList.get(0)).perform();
        }
        List<WebElement> categoryElementsList = getWebDriver().findElements(categoryElements);
        if (!categoryElementsList.isEmpty()) {
            action.moveToElement(categoryElementsList.get(0)).click().perform();
        }
    }

    public void onClickOfAddToBasket() {
        getWebDriver().findElements(compareProducts).get(0).findElement(addToBasket).click();
    }

    public String getMiniBasket() {
        return getWebDriver().findElement(miniBasketTotal).getText();
    }

    public void onClickOfRemove(String product) {
        List<WebElement> compareElementList = visibilityOfAllElementsLocatedBy(compareProducts);
        for (WebElement productElement : compareElementList) {
            if (product.equalsIgnoreCase(productElement.findElement(productImage).getAttribute("alt"))) {
                productElement.findElement(remove).click();
                break;
            }
        }
    }

    public List<String> getAttributeList() {
        return getWebDriver().findElements(attribute).stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void updateProductQuantity(String quantity) {
        //Need to investigate whether there is any other way to clear the quantity box
        visibilityOfAllElementsLocatedBy(compareProducts).get(0).findElement(productQuantity).sendKeys(Keys.ENTER, Keys.BACK_SPACE);
        getWebDriver().findElements(compareProducts).get(0)
                .findElement(productQuantity).sendKeys(quantity);
    }

    public boolean isProductRemoved(String product) {
        boolean isProductRemoved = true;
        List<WebElement> compareElementList = visibilityOfAllElementsLocatedBy(compareProducts);
        for (WebElement productElement : compareElementList) {
            if (product.equalsIgnoreCase(productElement.findElement(productImage).getAttribute("alt"))) {
                isProductRemoved = false;
            }
        }
        return isProductRemoved;
    }

    public void removeAllTheProducts() {
        visibilityOfAllElementsLocatedBy(compareProducts).get(2).findElement(remove).click();
        visibilityOfAllElementsLocatedBy(compareProducts).get(1).findElement(remove).click();
        visibilityOfAllElementsLocatedBy(compareProducts).get(0).findElement(remove).click();
    }

    public String getEmptyCompareMessage() {
        return waitForExpectedElement(emptyMessage).getText();
    }
}
