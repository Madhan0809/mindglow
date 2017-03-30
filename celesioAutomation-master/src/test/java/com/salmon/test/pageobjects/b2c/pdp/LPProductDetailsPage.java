package com.salmon.test.pageobjects.b2c.pdp;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.b2c.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class LPProductDetailsPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPProductDetailsPage.class);
    private By productItemList = By.cssSelector(".pdpRangeItem");
    private By addSelectedToBasketButton = By.cssSelector(".btnAction.addSelected");
    private By variantList = By.cssSelector("tbody tr");
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By departmentElements = By.cssSelector("#mainCategoriesList .mainCategoryLink");
    private By categoryElements = By.cssSelector("#mainCategoriesList>li:nth-of-type("
            + LloydsPharmacyConstants.CATEGORY_NUMBER + ") .subCategoriesList li a");
    private By breadCrumb = By.cssSelector("#widget_breadcrumb ul li.current");
    private By cookiePolicyBar=By.cssSelector(".cookiePolicy");
    private By cookiePolicyBtn=By.cssSelector(".cookiePolicy button");
    private By addToBasket=By.cssSelector("*[title='Add to basket']");
    private By subscriptionPeriod = By.cssSelector(".productPrice");
    //    private By productQuantity = By.cssSelector("input.productQty");
    private By descriptionTab = By.cssSelector("#productPageTabs .tabHeading ul li:nth-child(1) a");
    private By howToUseTab = By.cssSelector("#productPageTabs .tabHeading ul li:nth-child(2) a");
    private By ingredientsTab = By.cssSelector("#productPageTabs .tabHeading ul li:nth-child(3) a");
    private By reviewsTab = By.cssSelector("#productPageTabs .tabHeading ul li:nth-child(4) a");
    private By delivReturnsTab = By.cssSelector("#productPageTabs .tabHeading ul li:nth-child(5) a");
    private By productList = By.cssSelector(".productList .product");
    private By productImage = By.cssSelector(".productImage a");
    private By productHeaderTitle = By.cssSelector(".productName h1");
    private By userRating = By.cssSelector(".bv-write-review.bv-focusable.bv-submission-button");
    private By productPrice = By.cssSelector(".productPrice input[type='hidden']");
    private By savePrice = By.cssSelector(".productPrice .savePrice");
    private By wasPrice = By.cssSelector(".productPrice .wasPrice");
    private By inStock = By.cssSelector(".inStock");
    private By productQty = By.cssSelector(".productQty");
    private By productDetailImage = By.cssSelector(".galleryImage");
    private By productTitle = By.cssSelector(".productContent a");
    private By colourList = By.cssSelector(".selectListIcons.Colour li");
    private By sizeList = By.cssSelector(".selectListIcons.Size li");
    private By basketItemVariants = By.cssSelector(".basketListItem .definingAttributes li");
    private By addToWishList = By.cssSelector(".btnGuestWishList");
    private By registeredWishList = By.cssSelector(".registeredWishlist .btnWishList");
    private By wishListNamesInDropDown = By.cssSelector(".wishlistName");
    private By wishListSuccessMessage = By.cssSelector(".wishlistSuccess");
    private By newWishList = By.cssSelector(".createList");
    private By wishListModalText = By.cssSelector(".wishlistModal input[type='text']");
    private By wishListModalSave = By.cssSelector(".wishlistModal .btnPrimary");
    private By wishListModalContinueShopping = By.cssSelector(".wishlistModal .btnAction.continueShopping");
    private By signInOrRegLink = By.cssSelector(".guestWishlist .guestList.active a");
    private By signInOrRegText = By.cssSelector(".guestWishlist .guestList.active p");
    private By signOut = By.linkText("Sign Out");
    private By errorMessagePopUp = By.cssSelector("#ErrorMessageText");
    private List<String> multiVariantList = new ArrayList<>();

    public void goToProductDetailsPage(String product, boolean deleteCookies) {
        if (deleteCookies) {
            getWebDriver().manage().deleteAllCookies();
            if (isElementPresent(signOut)) {
            	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(signOut));
                waitForExpectedElement(signOut).click();
            }
        }
        UrlBuilder.startAtHomePage();
        if(isElementPresent(cookiePolicyBar)){
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
        clickOnProductImage(getB2cProp(product));
    }

    public void goToProductDetailsPage(String product) {
        goToProductDetailsPage(product, true);
    }

    private void clickOnProductImage(String product) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement compareElement : compareElementsList) {
            if (product.equalsIgnoreCase(compareElement.findElement(productTitle).getText())) {
                compareElement.findElement(productImage).click();
                break;
            }
        }
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList);
    }

    public boolean isRelevantBreadCrumb(String pdpBreadCrumb) {
        String test=waitForExpectedElement(breadCrumb).getText();
        return pdpBreadCrumb.contains(waitForExpectedElement(breadCrumb).getText());
    }

    public void getSubscriptionPeriod() {
        Select select = new Select(getWebDriver().findElement(subscriptionPeriod));
        select.selectByValue("1");
    }

    public void infoTabsDisplayedAndClickable(List<String> tabsList) {
        LOG.info("Info Tabs List: " + tabsList.toString());
        for (String tabName : tabsList) {
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(descriptionTab));
            ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", "");
            waitForExpectedElement(By.cssSelector("div.tabHeading")).click();
            switch (tabName) {
            case LloydsPharmacyConstants.DESCRIPTION_TAB:
                jsClick(descriptionTab);
                continue;
            case LloydsPharmacyConstants.HOW_TO_USE_TAB:
                jsClick(howToUseTab);
                continue;
            case LloydsPharmacyConstants.INGREDIENTS_TAB:
                jsClick(ingredientsTab);
//                waitForExpectedElement(ingredientsTab).click();
                continue;
            case LloydsPharmacyConstants.REVIEWS_TAB:
                jsClick(reviewsTab);
//                waitForExpectedElement(reviewsTab).click();
                continue;
            case LloydsPharmacyConstants.DELIV_RETURNS_TAB:
                jsClick(delivReturnsTab);
//                waitForExpectedElement(delivReturnsTab).click();
                continue;
            default:
                throw new IllegalArgumentException("No such " + tabName + " tab exists.");
            }
        }
    }

    public Product getProductDetailsOnRHS() {
        Product product = new Product();
        product.setProductName(waitForExpectedElement(productHeaderTitle).getText());
        product.setPrice(getWebDriver().findElement(productPrice).getAttribute("value"));
        product.setWasPrice(getWebDriver().findElement(wasPrice).getText().substring(0, 9));
        product.setSavePrice(getWebDriver().findElement(savePrice).getText());
        product.setStock(getWebDriver().findElement(inStock).getText());
        product.setQuantity(getWebDriver().findElement(productQty).getAttribute("value"));
        return product;
    }

    public Product getBundleProductDetailsRHS() {
        Product product = new Product();
        product.setProductName(waitForExpectedElement(productHeaderTitle,20).getText());
        //product.setUserRating(Boolean.toString(isPresent(userRating)));
        product.setPrice(Boolean.toString(isPresent(productPrice)));
        product.setStock(Boolean.toString(isPresent(inStock)));
        product.setQuantity(Boolean.toString(isPresent(productQty)));
        return product;
    }

    public boolean isProductImageDisplayedInPDP() {
        return getWebDriver().findElement(productDetailImage).isDisplayed();
    }

    public void clickButton(String button) {
        switch (button) {
            case "Add to basket":
                waitForExpectedElement(addToBasket).click();
                break;
            case "Wish List":
                waitForExpectedElement(addToWishList).click();
                break;
            case "Registered User Wish List":
                waitForExpectedElement(registeredWishList).click();
                break;
            default:
                throw new IllegalArgumentException("No such " + button + " parameter exists.");
        }
    }

    public void updateProductQuantity(String quantity) {
        waitForExpectedElement(productQty).clear();
        getWebDriver().findElement(productQty).sendKeys(quantity);
    }

    public void selectProductColour(String colour) {
        List<WebElement> colourElements = visibilityOfAllElementsLocatedBy(colourList);
        for (WebElement colourElement : colourElements) {
            if (colour.equalsIgnoreCase(colourElement.getAttribute("data-value"))) {
                multiVariantList.add(colour);
                colourElement.click();
                break;
            }
        }
    }

    public void selectProductSize(String size) {
        List<WebElement> sizeElements = getWebDriver().findElements(sizeList);
        for (WebElement sizeElement : sizeElements) {
            if (size.equalsIgnoreCase(sizeElement.getAttribute("data-value"))) {
                multiVariantList.add(size);
                sizeElement.click();
                break;
            }
        }
    }

    public boolean verifyListOfVariantsFromCheckOutBasketAreSameAsOnPDP() {
        boolean areAttributesSame = false;
        List<WebElement> basketVariantElements = visibilityOfAllElementsLocatedBy(basketItemVariants);
        List<String> variantsOnCheckoutPage = basketVariantElements.stream().map(basketVariant -> basketVariant.getText().split(":")[1].trim()).collect(Collectors.toList());
        if (multiVariantList.containsAll(variantsOnCheckoutPage)) {
            areAttributesSame = true;
            multiVariantList.clear();
        }
        return areAttributesSame;
    }

    public String getSignInAndRegisterText() {
        return waitForExpectedElement(signInOrRegText).getText();
    }

    public void clickSignInAndRegisterLink() {
        waitForExpectedElement(signInOrRegLink).click();
    }

    public void selectWishListByNameFromDropDown(String wishListName) {
    	if(isElementPresent(cookiePolicyBar)){
        	waitForExpectedElement(cookiePolicyBtn).click();
        }
    	if(!isElementPresent(newWishList)){
    		LOG.info("Open the wish list dropdown menue.");
    		jsClick(By.cssSelector(".iTriangleDown"));
    	}
        List<WebElement> wishListNameElements = getWebDriver().findElements(wishListNamesInDropDown);
        for (WebElement wishListNameElement : wishListNameElements) {
            if (wishListNameElement.getText().equalsIgnoreCase(wishListName)) {
                wishListNameElement.click();
                break;
            }
        }
    }

    public String getWishListSuccessMessage() {
        return waitForExpectedElement(wishListSuccessMessage).getText();
    }

    public void createNewWishList(String wishListName) {
    	if(isElementPresent(cookiePolicyBar)){
        	waitForExpectedElement(cookiePolicyBtn).click();
        }
    	if(!isElementPresent(newWishList)){
    		LOG.info("Open the wish list dropdown menue.");
    		jsClick(By.cssSelector(".iTriangleDown"));
    	}
        waitForExpectedElement(newWishList).click();
        waitForExpectedElement(wishListModalText).sendKeys(wishListName);
        getWebDriver().findElement(wishListModalSave).click();
        waitForExpectedElement(wishListModalContinueShopping).click();
    }

    public String getPopUpErrorMessage() {
        return waitForExpectedElement(errorMessagePopUp).getText();
    }

    public boolean selectAndUpdateQuantityForAProductItemVariantFromBundle(Product product) {
        List<WebElement> productItems = this.visibilityOfAllElementsLocatedBy(productItemList,3);
        for (WebElement productItem : productItems) {
            if (productItem.findElement(By.cssSelector("h3>a")).getText().equalsIgnoreCase(product.getProductName())) {
                return selectVariantAndUpdateQuantity(productItem, product);
            }
        }
        return false;
    }

    private void scrollIntoView(WebElement e){
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", e);

    }
    
    private boolean selectVariantAndUpdateQuantity(WebElement productItem, Product product) {
        List<WebElement> multiVariants = productItem.findElements(variantList);
        for (WebElement variantRow : multiVariants) {
            if (variantRow.findElement(By.cssSelector("td:nth-child(1)")).getText().equalsIgnoreCase(product.getVariant())) {
                assertThat(variantRow.findElement(By.cssSelector("td:nth-child(2)")).getText()).isEqualToIgnoringCase(product.getPrice());
                assertThat(variantRow.findElement(By.cssSelector("td:nth-child(3)")).getText()).isEqualToIgnoringCase(product.getPriceWithVATRelief());
                assertThat(variantRow.findElement(By.cssSelector("td:nth-child(4)")).getText()).isEqualToIgnoringCase(product.getStock());
                WebElement quantity = variantRow.findElement(By.cssSelector("td:nth-child(5) input"));
                scrollIntoView(quantity);
                ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", quantity);
                quantity.clear();
                quantity.sendKeys(product.getQuantity());
                if (product.isSelect()) {
                	((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", variantRow.findElement(By.cssSelector("input[type=radio]")));
//                    variantRow.findElement(By.cssSelector("input[type=radio]")).click();
                }
                return true;
            }
        }
        return false;
    }

    private void jsClick(By by){
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", getWebDriver().findElement(by));
	}

    
    public void addSelectedToBasket() {
    	jsClick(addSelectedToBasketButton);
//        waitForExpectedElement(addSelectedToBasketButton).click();
    }
}
