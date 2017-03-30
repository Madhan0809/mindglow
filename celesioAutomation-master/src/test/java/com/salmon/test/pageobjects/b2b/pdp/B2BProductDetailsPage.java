package com.salmon.test.pageobjects.b2b.pdp;
import com.salmon.test.framework.PageObject;
import com.salmon.test.pageobjects.b2b.B2BPageLandingModule;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.salmon.test.enums.PermittedCharacters.ALPHABETS;
import static com.salmon.test.framework.helpers.utils.RandomGenerator.random;

public class B2BProductDetailsPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BProductDetailsPage.class);
    public static String newRandomList;
    public B2BPageLandingModule b2BPageLandingModule;
    private By productDetailForm = By.cssSelector(".productDetails");
    private By departmentElements = By.cssSelector("ul #categoriesColumn li a span");
    private By categoryElements = By.cssSelector(".categoryContentPanel div:nth-of-type(1) ul li");
    private By subcategoryLink = By.cssSelector(".categoryContentPanel div:nth-of-type(1) ul li:nth-of-type(1) span");
    private By breadCrumb = By.cssSelector(".current");
    private By writeReviewLink=By.cssSelector(".bv-details-bar a[class*='write-review-label']");
    private By shopByCategory = By.id("categoryNavLink");
    private By productImage = By.cssSelector(".product_image>a");
    private By productTitle = By.cssSelector(".product_name>a");
    private By productList = By.cssSelector(".product_listing_container li .product.row");
    private By reviewRating = By.cssSelector("span[itemprop='ratingValue']");
    private By offerDetail = By.cssSelector(".offer");
    private By detailsTab = By.cssSelector("#pdp_tab1>div");
    private By additionalDetailsTab = By.cssSelector("#pdp_tab2>div");
    private By reviewsTab = By.cssSelector("#pdp_tab3>div");
    private By pdpImage = By.cssSelector("#productImage");
    
    private By reviewProductHeader=By.cssSelector(".bv-mbox-breadcrumb");
    private By reviewTitle=By.cssSelector("#bv-text-field-title");
    private By reviewText=By.cssSelector("#bv-textarea-field-reviewtext");
    private By recommendIcon=By.id("bv-radio-isrecommended-true-label");
    private By promotingScore=By.cssSelector("span[class*='netpromoterscore-wrapper'] #bv-radio-netpromoterscore-10-label");
    private By postReviewBtn=By.cssSelector("button[class*='actions-submit']");
    private By userLocationText=By.id("bv-text-field-userlocation");
    private By userEmailText=By.id("bv-email-field-useremail");
    private By reviewSelectors=By.id("select[id^='bv-select-field']");
    private By closePopupIcon=By.cssSelector("#bv-mbox-lightbox-list>button");
    private By ratingField=By.cssSelector("fieldset[class*='fieldset'] span[class*='bv-submission-star-rating-control']");
    private By priceDetailsItem=By.cssSelector(".priceGuide.row dfn");
    private By priceQtyItem=By.cssSelector(".priceGuide.row dd");
    
    private By recmededRetailPriceValue = By.cssSelector(".priceGuide.col-8.m-col-12 dd:nth-of-type(1)");
    private By splSellingPriceValue = By.cssSelector(".priceGuide.col-8.m-col-12 dd:nth-of-type(2)");
    private By outerPackUnitsVal = By.cssSelector(".price>li>ul>li:first-child");
    private By youSave = By.cssSelector(".price>li>ul>li:nth-child(2)");
    private By noOfQuantity = By.cssSelector("#quantity_13952");
    private By singlepackLabel = By.cssSelector("div.trow:nth-child(2) div.cell:nth-child(1) p:first-child");
    private By outerPackLabel = By.cssSelector("div.trow:nth-child(3) div.cell:nth-child(1) p:first-child");
    private By rcmdRetailPriceLabel = By.cssSelector(".priceGuide.row dt:nth-of-type(1)>dfn");
    private By specialSellingPriceLabel = By.cssSelector(".priceGuide.row dt:nth-of-type(2)>dfn");
    private By addToFavouritesLabel = By.cssSelector(".addToFavModal");
    private By productQty = By.cssSelector("input[id^='quantity_']");
    private By sizeList = By.cssSelector(".selectListIcons.size li");
    private By colourList = By.cssSelector(".selectListIcons.Colour li");
    private By addToBasket = By.cssSelector(".btn.btnSecondary");
    private List<String> multiVariantList = new ArrayList();
    private By addToFavPopupMsg = By.cssSelector(".modal-body>h2");
    private By newFavListName = By.cssSelector("#newFavListName");
    private By createNewFavListOpt = By.cssSelector("#favlist>fieldset>label>h2");
    private By getAddToFavButton = By.xpath(".//*[@id='favlist']/button");
    private By favouritesAddConfirmationMsg = By.cssSelector("div[class='message'] #ErrorMessageText");
    private By reviewsLabel = By.cssSelector("span[itemprop='reviewCount']>a");
    private By reviewsTabText = By.cssSelector(".bv-action-bar-header");
    private By reviewRatingSections = By.cssSelector(".bv-section-summary-inline .bv-content-title");
    private By noOfReviews = By.cssSelector(".bv-content-item-avatar-offset.bv-content-item-avatar-offset-on");
    private By addToFavourListPopupErrMsg = By.cssSelector(".errorMsg");
    private By productWithSelectorInPLP = By.xpath(".//select/ancestor::div[@class='productDetails']");
    private By miniAAHMiniCart = By.cssSelector(".btn.btnPrimary.widget_minishopcart[data-baskettype='AAH']");


    public void landingToPDPFromAAHLogin(B2BPageLandingModule pageLandingModule, String landingPageName) throws Throwable {
//    	b2BPageLandingModule=new B2BPageLandingModule(b2BPageLandingModule);
//    	b2BPageLandingModule=pageLandingModule;
        pageLandingModule.goToTargetLandingPageFromAAHLogin(landingPageName);
    }

    /**
     * It lets us navigate to appropriate product details page.
     */
    public void goToProductDetailsPage(String product) {
        LOG.info("Navigating to AAH PDP page \n");
        //  getWebDriver().manage().deleteAllCookies();
        // UrlBuilder.startAtHomePage();
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        //select first department form the list
        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        if (!departmentElementsList.isEmpty()) {
            action.moveToElement(departmentElementsList.get(0)).perform();
        }
        //select first category form the list
        List<WebElement> categoryElementsList = visibilityOfAllElementsLocatedBy(categoryElements);
        if (!categoryElementsList.isEmpty()) {
            action.moveToElement(categoryElementsList.get(0)).click().perform();
            waitForExpectedElement(subcategoryLink).click();
        }
        clickOnProductImage(getB2bProp(product));
    }

    private void clickOnProductImage(String product) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement compareElement : compareElementsList) {
            LOG.info("\n Before execution this step :::" + compareElement.findElement(productTitle).getText());
            if (compareElement.findElement(productTitle).getText().contains(product)) {
                LOG.info("\n After execution this step :::" + compareElement.findElement(productTitle).getText());
                compareElement.findElement(productImage).click();
                break;
            }
        }
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList, 30);
    }

    public boolean verifyProductImageDisplayed(String productInfo) {
        boolean imageFlag = false;
        if (productInfo.contains("image")) {
            if (waitForExpectedElement(pdpImage).isDisplayed()) {
                imageFlag = true;
            }
        } else if (productInfo.contains("offer")) {
            if (waitForExpectedElement(offerDetail).isDisplayed()) {
                imageFlag = true;
            }
        }
        return imageFlag;
    }

    public boolean verifyProductNameDisplayed(String product) {
        boolean prodNameFlag = false;
        LOG.info("\n Product Name :: "+waitForExpectedElement(productTitle).getText());
        if (waitForExpectedElement(productTitle).getText().contains(product)) {
            prodNameFlag = true;
        }
        return prodNameFlag;
    }

    public boolean verifyReviewRating(int reviewStarRating) {
        boolean ratingFlag = false;
        String noOfReviews = waitForExpectedElement(reviewRating).getText();
        double d = Double.parseDouble(noOfReviews);
        int ratingValue = (int) d;
        LOG.info("\n No of Reviews :: "+noOfReviews);
        LOG.info("\n Rating Value :: " + ratingValue);
        if (ratingValue == reviewStarRating) {
            ratingFlag = true;
        }
        return ratingFlag;
    }

    public boolean infoTabsDisplayedAndClickable(List<String> tabsList) {
        LOG.info("Info Tabs List: " + tabsList.toString());
        boolean tabsFlag = false;
        for (String tabName : tabsList) {
            if (tabName.equalsIgnoreCase(getB2bProp("details"))) {
                waitForElementDisplayedAndClickable(detailsTab);
                waitForExpectedElement(detailsTab).click();
                tabsFlag = true;
            } else if (tabName.equalsIgnoreCase(getB2bProp("additionalDetails"))) {
                waitForElementDisplayedAndClickable(additionalDetailsTab);
                waitForExpectedElement(additionalDetailsTab).click();
                tabsFlag = true;
            } else if (tabName.equalsIgnoreCase(getB2bProp("reviews"))) {
                waitForExpectedElement(reviewsTab).click();
                tabsFlag = true;
            } else {
                LOG.info(" \n No such " + tabName + " tab exists.");
            }
        }
        return tabsFlag;
    }

    public boolean verifyBreadcrumbDisplayed() {
        boolean breadCrumbFlag = false;
        if (waitForElementAvailableAndVisible(breadCrumb).isDisplayed()) {
            LOG.info("\n ::: Breadcrumb Value ::: -->  " + waitForExpectedElement(breadCrumb).getText());
            breadCrumbFlag = true;
        }
        return breadCrumbFlag;
    }

    public String verifySkuDetailsDisplayed(String skuDetails) {
        String pdpPageLabels = null;
        if (skuDetails.contains(getB2bProp("recommended.retailPrice.label"))) {
            pdpPageLabels = waitForExpectedElement(rcmdRetailPriceLabel).getText().trim();
        } else if (skuDetails.contains(getB2bProp("special.sellingPrice.label"))) {
            pdpPageLabels = waitForExpectedElement(specialSellingPriceLabel).getText().trim();
        } else if (skuDetails.contains(getB2bProp("addTo.favourites.label"))) {
            pdpPageLabels = waitForExpectedElement(addToFavouritesLabel).getText().trim();
        } else if (skuDetails.contains(getB2bProp("singlePack.label"))) {
            pdpPageLabels = waitForExpectedElement(singlepackLabel).getText().trim();
        } else if (skuDetails.contains(getB2bProp("outerPack.label"))) {
            pdpPageLabels = waitForExpectedElement(outerPackLabel).getText().trim();
            LOG.info("\n outer pack label::-->" + pdpPageLabels);
        } else if (skuDetails.contains(getB2bProp("reviews.text"))) {
            if(!isElementPresent(reviewsLabel)){
               LOG.info("It's the first reviewer of the current product. Now write one piece of product review."); 
               waitForExpectedElement(writeReviewLink).click();
               submitOnePieceOfProductReview();
               waitForExpectedElement(closePopupIcon).click();
            }          
            String str = waitForExpectedElement(reviewsLabel).getText().trim();
            pdpPageLabels = str.replaceAll("[^a-zA-Z]", "");
            LOG.info("\n reviews label::-->" + pdpPageLabels);
        }
        return pdpPageLabels;
    }

    private void submitOnePieceOfProductReview(){
        if(!isElementPresent(reviewProductHeader)){
            LOG.error("The reviewing product popup is not coming.");
            return;
        }
        for(WebElement starRating: this.visibilityOfAllElementsLocatedBy(ratingField)){
            LOG.info("Review all star rating field as 'good'.");
            starRating.findElement(By.cssSelector("a[id*='4']")).click();
        }
        getWebDriver().findElement(reviewTitle).sendKeys(getB2bProp("review.title"));
        getWebDriver().findElement(reviewText).sendKeys(getB2bProp("reviews.content"));
        getWebDriver().findElement(userLocationText).sendKeys(getB2bProp("address.country.work"));
        getWebDriver().findElement(userEmailText).sendKeys(getB2bProp("valid.email"));
        getWebDriver().findElement(recommendIcon).click();
        for(WebElement selector: this.visibilityOfAllElementsLocatedBy(reviewSelectors)){
            Select s=new Select(selector);
            s.selectByIndex(1);
        }
        getWebDriver().findElement(promotingScore).click();
        LOG.info("Submit the review for the product.");
        getWebDriver().findElement(postReviewBtn).click();
    }
    
    public Map<String, String> getProductDetailsOnRHS() {
        Map<String, String> productDetailsMap = new HashMap<>();    
        for(int i=0;i<visibilityOfAllElementsLocatedBy(priceDetailsItem).size();i++){
        productDetailsMap.put(getWebDriver().findElements(priceDetailsItem).get(i).getText(), getWebDriver().findElements(priceQtyItem).get(i).getText());
        } 
        return productDetailsMap;
    }

    public void updateProductQuantity(String quantity) {
        waitForExpectedElement(productQty).clear();
        waitForExpectedElement(productQty).sendKeys(quantity);

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

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click()", element);
    }

    public void addToBasket(String basketName) {
        for (WebElement row : this.visibilityOfAllElementsLocatedBy(productWithSelectorInPLP)) {
            jsClick(row.findElement(By.cssSelector("a[wairole='button'][href]")));
            break;
        }
    }


    public void onClickOfView(String view) {
        if (view.equalsIgnoreCase(getB2bProp("add.to.basket"))) {
            List<WebElement> compareElementsList = getListOfProducts();
            if (compareElementsList != null && !compareElementsList.isEmpty()) {
                compareElementsList.get(0).findElement(addToBasket).click();
            } else {
                LOG.info(" \n No such " + view + " parameter exists.");
            }
        }
    }

    public void clickOnLink(String favouriteLink) {
        List<WebElement> addToFavLinks = visibilityOfAllElementsLocatedBy(addToFavouritesLabel, 20);
        LOG.info("\n Action Buttons Size :: " + addToFavLinks.size());
        for (WebElement addToFavLink : addToFavLinks) {
            LOG.info("\n  Text Value :: " + addToFavLink.getText());
            if (addToFavLink.getText().trim().contains(favouriteLink)) {
                addToFavLink.click();
                break;
            }
            break;
        }
    }

    public boolean verifyAddToFavouritesMsgDisplayed(String favouriteMsg) {
        boolean flag = false;
        if (waitForElementAvailableAndVisible(addToFavPopupMsg).getText().toLowerCase().trim().contains(favouriteMsg.toLowerCase().trim())) {
            flag = true;
        }
        return flag;
    }

    public boolean verifyFavouritesConfirmMsgDisplayed() {
        if (isElementPresent(favouritesAddConfirmationMsg)) {
            LOG.info(" \n TEST confirmation ::: " + getB2bProp("add.favourites.msg"));
            return waitForExpectedElement(favouritesAddConfirmationMsg).getText().contains(getB2bProp("add.favourites.msg"));
        } 
        return false;
    }

    public void selectFavourite(String favouriteOpt) {
        if (waitForExpectedElement(createNewFavListOpt).getText().equalsIgnoreCase(favouriteOpt)) {
            waitForExpectedElement(By.cssSelector("#createNewFavList")).click();
        } else {
            List<WebElement> favouriteList = visibilityOfAllElementsLocatedBy(By.cssSelector("#favlist label"));
            for (WebElement ele : favouriteList) {
                if (ele.getText().equalsIgnoreCase(favouriteOpt)) {
                    LOG.info("\n Now select the favourite ::" + ele.getText());
                    ele.click();
                    break;
                }
            }
        }
    }

    public void createNewFavouriteRandomData(String favourite) {
    	newRandomList=favourite + random(5, ALPHABETS);
        waitForExpectedElement(newFavListName).sendKeys(newRandomList);
    }

    public void createNewFavourite(String favourite) {
        waitForExpectedElement(newFavListName).sendKeys(favourite + random(5, ALPHABETS));
    }

    public void clickOnAddToFavButton(String buttonName) {
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(getAddToFavButton));
        waitForExpectedElement(getAddToFavButton).click();
    }

    public boolean verifyFavouritesConfirmErrorMsgDisplayed() {
        boolean flag = false;
        if (isElementPresent(addToFavourListPopupErrMsg) && waitForExpectedElement(addToFavourListPopupErrMsg).getText().contains(getB2bProp("add.favourites.popup.errMsg"))) {
            LOG.info("\n TEST validation message ::: The choosen favourite list is not owned by current user.");
            flag = true;
        } else if (isElementPresent(addToFavourListPopupErrMsg) && waitForExpectedElement(addToFavourListPopupErrMsg).getText().contains(getB2bProp("add.favourite.exist.errorMsg"))) {
            LOG.info("\n TEST validation message ::: Item has been already added in to the favourite list.");
            flag = true;
        } else if (isElementPresent(favouritesAddConfirmationMsg)&& waitForElementAvailableAndVisible(favouritesAddConfirmationMsg).getText().contains(getB2bProp("add.favourites.msg"))) {
            LOG.info("\n TEST validation message ::: Add to favourite successfully.");
            flag = true;
        } else
            LOG.error("\n Not found valid message above. The actual error message is :"+ waitForExpectedElement(addToFavourListPopupErrMsg).getText());
        return flag;
    }

    public boolean verifyReviewsTextDisplayed(String reviews) {
        boolean flag = false;
        if (waitForExpectedElement(reviewsTabText).getText().equalsIgnoreCase(reviews)) {
            flag = true;
        }
        return flag;
    }

    public List<String> getExpectedRatingSectons() {
        List<String> reviewRatingLabelList = new ArrayList<String>();
        List<WebElement> webElements = getWebDriver().findElements(reviewRatingSections);
        for (WebElement label : webElements) {
            reviewRatingLabelList.add(label.getText().trim());
        }
        LOG.info("\n reviewRatingSections :: " + reviewRatingLabelList);
        return reviewRatingLabelList;
    }

    public boolean verifyNoOfReviewsSections() {
        boolean flag = false;
        String str = waitForExpectedElement(reviewsLabel).getText().trim();
        String pdpReviews = str.replaceAll("[^0-9]", "");
        LOG.info("\n number of reviews::-->" + pdpReviews);
        List<WebElement> availableReviews = visibilityOfAllElementsLocatedBy(noOfReviews);
        LOG.info("\n Available Reviews::-->" + availableReviews.size());
        if (Integer.parseInt(pdpReviews) == availableReviews.size()) {
            flag = true;
        }
        return flag;
    }

    public void clickOnReviewsLink(String reviews) {
        waitForExpectedElement(reviewsLabel).click();
    }

    public boolean isProductImageAvailable() {
        return isElementPresent(pdpImage);
    }

    public boolean canAccessTargetPage(String pageTitle) {
        LOG.info("\n page title :: " + pageTitle);
        return checkPageTitleContains(pageTitle);
    }

    public void onClickAAHMiniCart() {
        waitForExpectedElement(miniAAHMiniCart).click();
    }

}