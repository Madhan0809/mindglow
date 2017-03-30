package com.salmon.test.pageobjects.b2c.plp;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.JavascriptExecutor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LPProductListerPage extends PageObject {
    private static Logger LOG = LoggerFactory.getLogger(LPProductListerPage.class);
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By plpCategoryBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(3)");
    private By gridViewIcon = By.cssSelector(".gridView");
    private By listViewIcon = By.cssSelector(".listView");
    private By productList = By.cssSelector("#plpContent .productList .product");
    private By sortBy = By.cssSelector(".orderBy");
    private By pageSize = By.cssSelector(".pageSize");
    private By showMoreLink=By.cssSelector("li.showMoreButton.moreFacets>a");
    private By showLessLink=By.cssSelector("li.showLessButton.moreFacets>a");
    
    private By productImage = By.cssSelector(".productImage a");
    private By productName = By.cssSelector(".productContent a");
    private By productReviewDetail = By.cssSelector(".reviews");
    private By productPrice = By.cssSelector(".priceDisplay>input");
    private By reviewStarsList = By.cssSelector("fieldset[data-facetid='AMOUNT_REVIEW_STARS'] li[class*='facetType']");
    private By clearAll = By.cssSelector(".removeFiltersLink");
    private By facetActiveFilterList = By.cssSelector(".activeFilters li>a");
    private By facetTypeElement = By.cssSelector("li[class*='facetType']>h4[title]");
    private By productTypes = By.cssSelector("fieldset[data-facetid='Product Type'] ul li[id*='facet']");
    private By compareTargetList = By.cssSelector(".productList .product .compare");
    private By sizeTypes = By.cssSelector("fieldset[data-facetid='size'] li.facetType ul li[id*='facet']");
    private By priceTypes = By.cssSelector("fieldset[data-facetid='Price'] li.facetType ul li[id*='facet']");
    private By brandTypes = By.cssSelector("fieldset[data-facetid='Brand'] li.facetType ul li[id*='facet']");
    private By cardEnableTypes = By.cssSelector("fieldset[data-facetid='CandCEnabled'] li.facetType ul li[id*='facet']");
    private By specialOfferTypes = By.cssSelector("fieldset[data-facetid='SpecialOffer'] li.facetType ul li[id*='facet']");
    private By userOwnPriceRange = By.cssSelector("li.price_range_input");
    private By compareCheckBox = By.cssSelector("input.comparebox");
    private By compareLabel = By.cssSelector(".compareLabel");
    private By compareTargetLink = By.cssSelector("a");
    private By pdpPage = By.id("pdp");
    private By addToBasket = By.cssSelector(".productFooter a");
    private By productMoreInfoBtn = By.cssSelector("a.btnContinue.multiSKU");
    private By checkOut = By.cssSelector("#MiniShoppingCart .footer a");
    private By basketCount = By.cssSelector("#miniBasket #minishopcart_total");
    private By checkOutPage = By.linkText("Checkout Securely");
    private By headerIcon = By.cssSelector("div.row.brandSearch>div>a");
    private By footerIcon = By.cssSelector(".row.footerSocial");
    private By pageNumber = By.cssSelector("li.pageNumbers>a");
    private By productQuantity = By.cssSelector(".productFooter input[type='number']");
    private By compareErrorMsg = By.cssSelector("#msgpopup_content_wrapper #ErrorMessageText");
    private By signOut = By.linkText("Sign Out");

    private By cookiePolicyBar=By.cssSelector(".cookiePolicy");
    private By cookiePolicyBtn=By.cssSelector(".cookiePolicy button");
    
    
    public void goToTargetProductListerPage(String category) {   
        getWebDriver().manage().deleteAllCookies();       
        UrlBuilder.startAtHomePage();
        if (isElementPresent(signOut)) {
            getWebDriver().findElement(signOut).click();
        }
        if(isPresent(cookiePolicyBar)){
        	waitForExpectedElement(cookiePolicyBtn).click();
        }
        String[] categories = category.split(">");
        String mainCategory = categories[0].trim();
        String subCategory = categories[1].trim();
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        WebElement element = waitForExpectedElement(By.xpath(".//a[@class='mainCategoryLink' and contains(text(),'" + mainCategory + "')]"));
        action.moveToElement(element).perform();
        WebElement targetCatagory = element.findElement(By.xpath("..//a[@class='subCategoryLink' and contains(text(),'" + subCategory + "')]"));
        action.moveToElement(targetCatagory).click().perform();
    }

    public void goToFacetNavigationProductListerPage(String navigationPage) {
        if (LloydsPharmacyConstants.REVIEW_FACET_NAVIGATION.equals(navigationPage)) {
            onSelectOfReviewStar(5);
        }
    }

    public String getRelevantBreadCrumb() {
        return waitForExpectedElement(plpCategoryBreadCrumb).getText();
    }

    public void onClickOfProductImage() {
        List<WebElement> compareElementsList = getListOfProducts();
        if (compareElementsList != null && !compareElementsList.isEmpty()) {
            compareElementsList.get(0).findElement(productImage).click();
        }
    }

    public void onClickOfProductName() {
        List<WebElement> compareElementsList = getListOfProducts();
        if (compareElementsList != null && !compareElementsList.isEmpty()) {
            compareElementsList.get(0).findElement(productName).click();
        }
    }

    public void onClickOfAddToBasket(String productNameValue) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement product : compareElementsList) {
            if (product.findElement(productName).getText().equalsIgnoreCase(productNameValue)) {
                product.findElement(addToBasket).click();
                break;
            }
        }
    }

    public void onClickOfMoreInfo(String productNameValue) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement product : compareElementsList) {
            if (product.findElement(productName).getText().equalsIgnoreCase(productNameValue)) {
                product.findElement(productMoreInfoBtn).click();
                break;
            }
        }
    }




    public boolean isRelevantProductDetailsPage() {
        boolean isRelevantDetailPage = false;
        if (waitForExpectedElement(pdpPage).isDisplayed()) {
            isRelevantDetailPage = true;
        }
        return isRelevantDetailPage;
    }

    public boolean isCompareTextChanged(String expCompareText) {
        String actualCompareText = getProductListToCompare().get(0).findElement(compareLabel).getText();
        return expCompareText.equals(actualCompareText);
    }

    public String getMessagesForCompareActions(int noOfProducts) {
        String message;
        switch (noOfProducts) {
            case 1:
                message = getProductListToCompare().get(0).findElement(compareLabel).getText();
                break;
            case 4:
                message = getProductListToCompare().get(3).findElement(compareLabel).getText();
                break;
            case 5:
                message = waitForExpectedElement(compareErrorMsg).getText();
                break;
            default:
                throw new IllegalArgumentException("No such parameter exists.");
        }
        return message;
    }

    public void selectCompareCheckBoxesOfProducts(int noOfProducts) {
        List<WebElement> compareElementsList = getProductListToCompare();
        if (compareElementsList != null && !compareElementsList.isEmpty()) {
            for (int i = 0; i < noOfProducts; i++) {
//            	compareElementsList.get(i).findElement(compareCheckBox).click();
            	click(compareElementsList.get(i).findElement(compareCheckBox));
            }
        }
    }
    
	private void click(WebElement webElement) {
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].click()", webElement);

	}

    private List<WebElement> getProductListToCompare() {
        return this.visibilityOfAllElementsLocatedBy(compareTargetList);
    }

    public void onClickOfCompareButton() {
        List<WebElement> compareElementsList = getProductListToCompare();
        if (compareElementsList != null && !compareElementsList.isEmpty()) {
//            compareElementsList.get(1).findElement(compareTargetLink).click();
        	click(compareElementsList.get(1).findElement(compareTargetLink));
        }
    }

    public String getComparePageTitle() {
        return getCurrentPageTitle();
    }

    public void onClickOfView(String view) {
        switch (view) {
            case LloydsPharmacyConstants.GRID_VIEW:
                waitForExpectedElement(gridViewIcon).click();
                break;
            case LloydsPharmacyConstants.LIST_VIEW:
                waitForExpectedElement(listViewIcon).click();
                break;
            case LloydsPharmacyConstants.ADD_TO_BASKET:
                List<WebElement> compareElementsList = getListOfProducts();
                if (compareElementsList != null && !compareElementsList.isEmpty()) {
                    compareElementsList.get(0).findElement(addToBasket).click();
                }
                break;
            case LloydsPharmacyConstants.CHECKOUT:
                waitForExpectedElement(checkOut).click();
                break;
            default:
                throw new IllegalArgumentException("No such" + view + "parameter exists.");
        }
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList);
    }

    public boolean isCorrectViewDisplayed(String view) {
        boolean isRightView = false;
        if (LloydsPharmacyConstants.GRID_VIEW.equals(view)) {
            if (waitForExpectedElement(By.cssSelector(".gridView.active")).isDisplayed()) {
                isRightView = true;
            }
        } else if (LloydsPharmacyConstants.LIST_VIEW.equals(view)) {
            if (waitForExpectedElement(By.cssSelector(".listView.active")).isDisplayed()) {
                isRightView = true;
            }
        }
        return isRightView;
    }

    public List<ProductItemPage> getProductList() {
        List<WebElement> productElementList = getWebDriver().findElements(productList);
        List<ProductItemPage> productItemList = new ArrayList<>();
        for (WebElement product : productElementList) {
            productItemList.add(new ProductItemPage(product));
        }
        return productItemList;
    }

    public List<ProductItemPage> getAllProductListWithPrice(){
		List<ProductItemPage> productItemList = new ArrayList<>();
		List<ProductItemPage> originalList = getProductList();
		for (int i=0;i<originalList.size();i++) {
		    ProductItemPage item=originalList.get(i);
			String prodName = item.getProductName();
			String targetFormatPriceXpath = ".//a[text()='" + prodName
					+ "']/ancestor::div[@class='product']//*[@class='priceDisplay']/input[@type='hidden']";
			if (isPresent(By.xpath(targetFormatPriceXpath))) {
				productItemList.add(item);
			}
		}
		return productItemList;
    }
    
    public int getAllProductCount() {
        int productCount = 0;
        List<WebElement> pageNumbers = getWebDriver().findElements(pageNumber);
        if (pageNumbers.size() <= 1) {
            return getProductList().size();
        } else {
            for (int i = 0; i < pageNumbers.size(); i++) {
                visibilityOfAllElementsLocatedBy(pageNumber).get(i).click();
                productCount = productCount + getProductList().size();
            }
        }
        return productCount;
    }

    public void onSelectOfSortBy(String sortName) {
        Select select = new Select(waitForExpectedElement(sortBy));
        switch (sortName) {
            case LloydsPharmacyConstants.NAME_ASCENDING:
                select.selectByVisibleText(LloydsPharmacyConstants.NAME_ASCENDING);
                break;
            case LloydsPharmacyConstants.NAME_DESCENDING:
                select.selectByVisibleText(LloydsPharmacyConstants.NAME_DESCENDING);
                break;
            case LloydsPharmacyConstants.PRICE_LOW_TO_HIGH:
                select.selectByVisibleText(LloydsPharmacyConstants.PRICE_LOW_TO_HIGH);
                break;
            case LloydsPharmacyConstants.PRICE_HIGH_TO_LOW:
                select.selectByVisibleText(LloydsPharmacyConstants.PRICE_HIGH_TO_LOW);
                break;
            default:
                throw new IllegalArgumentException("No such " + sortName + " sorting parameter exists.");
        }
    }

    public void onSelectOfPageSize(String count) {
        Select select = new Select(waitForExpectedElement(pageSize));
        switch (count) {
            case LloydsPharmacyConstants.PER_PAGE_SIX:
                select.selectByValue(LloydsPharmacyConstants.PER_PAGE_SIX);
                break;
            case LloydsPharmacyConstants.PER_PAGE_TWELVE:
                select.selectByValue(LloydsPharmacyConstants.PER_PAGE_TWELVE);
                break;
            case LloydsPharmacyConstants.PER_PAGE_EIGHTEEN:
                select.selectByValue(LloydsPharmacyConstants.PER_PAGE_EIGHTEEN);
                break;
            default:
                throw new IllegalArgumentException("No such " + count + " page size parameter exists.");
        }
    }

    public boolean verifyPageSize(int pageSize) {
        //TODO: Temporary wait..Need to remove this.
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int size = visibilityOfAllElementsLocatedBy(productList, 10).size();
        return size == pageSize;
    }

    public void onSelectOfReviewStar(int starProduct) {
        this.waitForExpectedElement(reviewStarsList);
        WebElement starsList = getWebDriver().findElement(reviewStarsList);
        List<WebElement> starsRows = starsList.findElements(By.xpath("./ul/li[@id and not(@class='hide')]"));
        for (WebElement starRow : starsRows) {
            int rowStarCount = starRow.findElements(By.cssSelector("i.iStarYellow")).size();
            if (rowStarCount == starProduct) {
                starRow.findElement(By.cssSelector("input[type='checkbox']")).click();
                break;
            }
        }
    }

    public boolean canGetReviewProducts(int starProduct) {
//        List<WebElement> reviewStarsList = getWebDriver().findElements(reviewStars);
//        String count = reviewStarsList.get(starProduct-1).getText();
        WebElement starsList = getWebDriver().findElement(reviewStarsList);
        String count = starsList.findElement(By.xpath("./ul//span[text()=" + starProduct + "]/ancestor::li/label//span[2]")).getText();
        List<WebElement> productCount = getWebDriver().findElements(productList);
//       int isProdSize=productCount.size();
//       int valueCount=Integer.valueOf(count);
        return productCount.size() == Integer.valueOf(count);
    }

    public int getReviewElementsCount() {
        List<WebElement> reviewElements = visibilityOfAllElementsLocatedBy(facetTypeElement);
        return reviewElements.size();
    }

    public void clearActiveFacetFilter(String filterName) {
        List<WebElement> activeFacetFilters = getWebDriver().findElements(facetActiveFilterList);
        for (WebElement filter : activeFacetFilters) {
            String activeFilterName = filter.getText().trim();
            if (activeFilterName.contains(filterName)) {
                LOG.debug("Now start to clear filter: " + activeFilterName);
                filter.click();
                break;
            } else if (filterName.contains("star")) {
                LOG.debug("Now start to clear filter: " + activeFilterName);
                filter.click();
                break;
            }
        }
    }

    public int getSingleActiveFilterProdNumber() {
        String productNumber = getWebDriver().findElement(facetActiveFilterList).findElement(By.xpath("./span/span[2]")).getText();
        return Integer.parseInt(productNumber);
    }

    public boolean canGetHeaderAndFooterInfo() {
        if (!this.isElementPresent(headerIcon)) {
            LOG.error("Fail to display the header icon.");
            return false;
        } else if (!isElementPresent(footerIcon)) {
            LOG.error("Fail to display the footer icon.");
            return false;
        }
        return true;
    }

    public boolean verifyIfClearAllIsDisplayed() {
        return isElementPresent(clearAll);
//       return waitForExpectedElement(clearAll).isDisplayed();
    }

    public boolean verifyIfFacetFilterIsApplied() {
        List<WebElement> reviewStarsList = visibilityOfAllElementsLocatedBy(facetActiveFilterList);
        return !reviewStarsList.isEmpty() && reviewStarsList.get(0) != null;
    }

    public int getActivedFilterAmount() {
        List<WebElement> reviewStarsList = getWebDriver().findElements(facetActiveFilterList);
        return reviewStarsList.size();
    }

    public void onSelectOfProductType(String productType) {
        if (isElementPresent(productTypes)) {
            List<WebElement> productTypeList = getWebDriver().findElements(productTypes);
            for (WebElement product : productTypeList) {
                if (productType.equals(product.findElement(By.xpath("./label/span/*[not(@class)]")).getText())) {
                    product.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        }
        LOG.error("Fail to find the target type in facet navigatino: " + productType);
    }

    public void onSelectOfSizeType(String sizeType) {
        if (isElementPresent(sizeTypes)) {
            List<WebElement> sizeTypeList = getWebDriver().findElements(sizeTypes);
            for (WebElement size : sizeTypeList) {
                if (sizeType.equals(size.findElement(By.xpath("./label/span/*[not(@class)]")).getText().trim())) {
                    size.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        } else
            LOG.error("Fail to find the target type in facet navigatino: " + sizeType);
    }

    public void onSelectOfPriceRangeType(String priceRangeType) {
        if (this.isElementPresent(priceTypes)) {
            List<WebElement> priceRangeList = getWebDriver().findElements(priceTypes);
            for (WebElement price : priceRangeList) {
                if (priceRangeType.equals(price.findElement(By.xpath("./label/span/*[not(@class)]")).getText().trim())) {
                    price.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        } else
            LOG.error("Fail to find the target type in facet navigatino: " + priceRangeType);
    }

    public void onSelectOfBrand(String brandName) {
        if (this.isElementPresent(brandTypes)) {
            List<WebElement> brandList = getWebDriver().findElements(brandTypes);
            for (WebElement brand : brandList) {
                if (brandName.equals(brand.findElement(By.xpath("./label/span/*[not(@class)]")).getText().trim())) {
                    brand.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        } else
            LOG.error("Fail to find the target type in facet navigatino: " + brandName);
    }

    public void onSelectCardEnabled(String cardNameOption) {
        if (this.isElementPresent(cardEnableTypes)) {
            List<WebElement> cardCList = getWebDriver().findElements(cardEnableTypes);
            for (WebElement cardCItem : cardCList) {
                if (cardNameOption.equals(cardCItem.findElement(By.xpath("./label/span/*[not(@class)]")).getText().trim())) {
                    cardCItem.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        } else
            LOG.error("Fail to find the target type in facet navigatino: " + cardNameOption);
    }

    public void onSelectSpecialOffer(String offerName) {
        if (isElementPresent(specialOfferTypes)) {
            List<WebElement> specialOfferList = getWebDriver().findElements(specialOfferTypes);
            for (WebElement offer : specialOfferList) {
                if (offerName.equals(offer.findElement(By.xpath("./label/span/*[not(@class)]")).getText().trim())) {
                    offer.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        } else {
            LOG.error("Fail to find the target type in facet navigation: " + offerName);
        }
    }

    public boolean canFilterCorrectTypes(String productType) {
        String count = "";
        List<WebElement> productTypeList = getWebDriver().findElements(facetActiveFilterList);
        for (WebElement product : productTypeList) {
            List<String> counts = new ArrayList<>();
            if (productType.equals(product.findElement(By.cssSelector("span>span")).getText().trim())) {
                count = product.findElement(By.cssSelector("span>span.count")).getText();
                counts.add(count);
            } else if (productType.contains("star")) {
                count = product.findElement(By.cssSelector("span>span.count")).getText().trim();
                counts.add(count);
            }
            count = Collections.min(counts);
        }
        if (Integer.parseInt(count) == getAllProductCount()) {
            LOG.debug("Now here are " + count + "product(s) for the product type: " + productType);
            return true;
        } else {
            LOG.error("The product count is not updated as expected for the product type: " + productType);
            return false;
        }
    }

    public void inputProductsInRange(Map<String, String> priceRange) {
        this.waitForExpectedElement(userOwnPriceRange);
        WebElement priceRangeTab = getWebDriver().findElement(userOwnPriceRange);
        priceRangeTab.findElement(By.cssSelector("input.fromPrice")).sendKeys(priceRange.get("From"));
        priceRangeTab.findElement(By.cssSelector("input.toPrice")).sendKeys(priceRange.get("To"));
        priceRangeTab.findElement(By.cssSelector(".btnPrimary[type='submit']")).click();
    }

    public boolean isProductPricesInRangeOf(int priceFrom, int priceTo) {
        List<WebElement> pageNumbers = getWebDriver().findElements(pageNumber);
        if (pageNumbers.size() <= 1) {
            return isPricesInRange(priceFrom, priceTo, getAllProductPriceInPage());
        } else {
            for (WebElement page : pageNumbers) {
                page.click();
                if (!isPricesInRange(priceFrom, priceTo, getAllProductPriceInPage())) {
                    LOG.error("Found the page with product price that is not within range.");
                    return false;
                }
            }
            return true;
        }
    }

    private boolean isPricesInRange(int priceFrom, int priceTo, List<String> allPrices) {
        for (String price : allPrices) {
            Double singlePrice = Double.parseDouble(price);
            if (singlePrice > (double) priceTo || singlePrice < (double) priceFrom) {
                LOG.error("Here is a price out of range: " + price);
                return false;
            }
        }
        return true;
    }

    public List<String> getAllProductPriceInPage() {
        List<WebElement> allPricesInPage = getWebDriver().findElements(productPrice);
        List<String> prices = new ArrayList<>();
        for (WebElement price : allPricesInPage) {
            prices.add(price.getAttribute("value").replace("£", ""));
        }
        return prices;
    }

    public String getNumberOfProductAddedToBasket() {
        return waitForExpectedElement(basketCount).getText().replaceAll("[:]+", "");
    }
    
    public boolean canShowCorrectQuantityInfoInBasket(String expectedQty){
    	String qtyXpath=".//span[@id='minishopcart_total' and contains(text(),'"+expectedQty.replaceAll("[^\\d]", "")+"')]";
    	return isElementPresent(By.xpath(qtyXpath));
    }

    public int getRowOfSingleFacetMenu(String facetMenuName) {
        int rowCount = 0;
        List<WebElement> facetMenuTitles = visibilityOfAllElementsLocatedBy(facetTypeElement);
        for (WebElement titleTab : facetMenuTitles) {
            if (titleTab.getAttribute("title").contains(facetMenuName)) {
                WebElement facetType = titleTab.findElement(By.xpath("./parent::li"));
                List<WebElement> totalSize = facetType.findElements(By.cssSelector("li[id^='facet']:not([class='hide'])"));
                for (WebElement e : totalSize) {
                    if (e.isDisplayed()) {
                        rowCount++;
                    }
                }
                return rowCount;
            }
        }
        LOG.error("Fail to find the facet menu of : " + facetMenuName);
        return 0;
    }

    public void clickOnLinkFromFacetMenu(String linkName, String facetMenuName) {
        WebElement targetFacetMenu = getWebDriver().findElement(By.xpath("//h4[@title='" + facetMenuName + "']/parent::li[contains(@class,'facetType')]"));
        if (linkName.contains("more")) {
            LOG.info("Click on show more link of facet: " + facetMenuName);
            if (isElementPresent(By.cssSelector("li.showMoreButton.moreFacets"))) {
//                targetFacetMenu.findElement(showMoreLink).click();
                click(targetFacetMenu.findElement(showMoreLink));
            }
        } else if (linkName.contains("less")) {
//            targetFacetMenu.findElement(showLessLink).click();
        	  click(targetFacetMenu.findElement(showLessLink));
        } else {
            LOG.error("Fail to find link in facet menu: " + linkName);
        }
    }

    public boolean isNavigatedToCheckoutPage() {
        return waitForExpectedElement(checkOutPage).isDisplayed();
    }

    public void updateProductQuantity(String quantity) {
        waitForExpectedElement(productQuantity).clear();
        getWebDriver().findElement(productQuantity).sendKeys(quantity);
    }

    public void clearAllAppliedFilters() {
    	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -650)", "");
        this.waitForExpectedElement(clearAll);
        getWebDriver().findElement(clearAll).click();
    }

    public boolean checkIfElementPresent(String detailType) {
        return isElementPresent(productReviewDetail);
    }
}
