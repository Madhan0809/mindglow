package com.salmon.test.pageobjects.b2b.plp;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.PLPProductItems;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class B2BProductListsPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BProductListsPage.class);
    private By filterByMinusSign = By.cssSelector(".toggle.collapsible");
    private By subCategories=By.cssSelector("ul.subColumn a>span");
    private By filterSection = By.cssSelector(".facetWidget.collapsibleContainer");
    private By sortBy = By.name("orderBy");
    private By productsHeadingText = By.cssSelector(".row.header_bar>div:first-child");
    private By productList = By.cssSelector(".product_listing_container >ul li .product_info");
    private By productListSize = By.cssSelector(".product_listing_container .product.row");
    private By numberOfProducts = By.cssSelector(".row.header_bar>div:first-child>span");
    private By pageSize = By.cssSelector(".pageSize");
    private By itemsPerPageText = By.cssSelector(".sorting_controls.m-col-8.col-10 span:nth-of-type(2)>label");
    private By viewControllerBar=By.cssSelector(".sorting_view_controls_container.row");
    private By gridViewIcon = By.cssSelector(".icon-th");
    private By listViewIcon = By.cssSelector("i[title='Switch to list view']");
    private By filterText=By.cssSelector("div[id^='section_button_']");
    private By allFacetFilters=By.cssSelector("form#productsFacets>fieldset");
    private By productTypes = By.cssSelector("fieldset[id='SINGLE_OUTER'] .facetSelectContainer .facetSelect li[id*='facet']");
    private By reviewStarsList = By.cssSelector("fieldset[id*='STAR']  ul.facetSelect>li");
    private By priceTypes = By.cssSelector("fieldset[id='PRICE'] .facetSelectContainer .facetSelect li[id*='facet']");
    private By cardEnableTypes = By.cssSelector("");
    private By facetActiveFilterList = By.cssSelector(".facetSelectedCont li");
    private By pageNumber = By.cssSelector(".pageControl.number>a");
    private By clearAll = By.cssSelector(".clearAll");
    private By userOwnPriceRange = By.cssSelector(".priceFacet");
    private By nextButton = By.cssSelector("#WC_SearchBasedNavigationResults_pagination_link_right_categoryResults");
    private By previousButton = By.cssSelector("#WC_SearchBasedNavigationResults_pagination_link_left_categoryResults");
    private By activePageNumber = By.cssSelector(".active.selected");
    private By inactivePageNumber = By.cssSelector(".hoverover");
    private By filterBySign = By.cssSelector(".icon-plus.active");
    private By pageHeading = By.cssSelector(".pageHeading>h1");
    private By priceRangeText = By.cssSelector(".priceFacet>div>h4");
    private By productImage = By.cssSelector(".product_image .image>a>img");
    private By prodDetailPageSection = By.id("productInformation");
    private By productName = By.cssSelector(".product_name>a");
    private By colourList = By.cssSelector(".selectListIcons.Colour li");
    private By sizeList = By.cssSelector(".selectListIcons.size li");
    private By basketCountAAH = By.cssSelector(".btn.btnPrimary.widget_minishopcart[data-baskettype='AAH']>.quantity");
    private By basketCountEnterprise = By.cssSelector(".btn.btnPrimary.widget_minishopcart[data-baskettype='ENTERPRISE']>.quantity");
    private By checkOutPage = By.linkText("Checkout Securely");
    private By basketItemVariants = By.cssSelector(".basketListItem .definingAttributes li");
    private By closeMiniBtn=By.cssSelector("#MiniShopCartCloseButton_1");
    private By addToBasketBtn = By.xpath("//select[contains(@class,'basketId')]/ancestor::div[@class='productDetails']//a[@style='display: block;']");
    private By targetAdd = By.cssSelector("a[wairole='button'][href]");
    private By qtyInputField=By.cssSelector("input[id^='quantity']");
    private List<String> multiVariantList = new ArrayList();
    private By addToFavouritesLabel = By.cssSelector(".addToFavModal");
    private By listOfSelectedFilters = By.cssSelector(".filter_option .facetRowClose");
    private By gridViewActivated = By.cssSelector(".icon-th.active");
    private By listViewActivated = By.cssSelector(".icon-th-list.active");
    private By logOnId = By.cssSelector("input[name='logonId']");
    private By password = By.cssSelector("input[name='logonPassword']");
    private By productWithSelectorInPLP = By.xpath(".//select/ancestor::div[@class='productDetails']");
    private By miniAAHMiniCart = By.cssSelector(".btn.btnPrimary.widget_minishopcart[data-baskettype='AAH']");
    private By goToCurrentOrderLink=By.xpath(".//h3[text()='Items in your current order:']/parent::div//a[@class='basicButton']/span");

    
    private void mouseOverToCornerOfPLP(){
        visibilityOfAllElementsLocatedBy(productList, 20);
        Actions a=new Actions(getWebDriver());
        Dimension d=getWebDriver().manage().window().getSize();
        a.moveByOffset(d.getWidth()/2, d.getHeight()/2).build().perform();
    }

    public void selectCategory(String subCategory) {
        if (subCategory.startsWith("category.")) {
            subCategory = getB2bProp(subCategory);
        } else {
            for (WebElement sub : getWebDriver().findElements(subCategories)) {
                if (sub.getText().equals(subCategory)) {
                    sub.click();
                    break;
                }
            }
        }
    }

    public String onSubCategoryScreen(String subCatTitle) {
        String screwPageTitle = null;
        if (!((JavascriptExecutor) getWebDriver()).executeScript("return document.readyState").equals("complete")) {
            LOG.warn("Page loading may not fully completed");
        }
        screwPageTitle = getCurrentPageTitle();
        LOG.info(" \n subcategory page title ::::" + screwPageTitle);
        return screwPageTitle;
    }

    public String checkFilterByText() {
        String filterText = waitForExpectedElement(filterByMinusSign).getText();
        return filterText;
    }

    public String verifyProductsLabel() {
        String productsLabel = waitForExpectedElement(productsHeadingText).getText();
        LOG.info(" \n Product Text :  " + productsLabel);
        return productsLabel;
    }

    public boolean verifyNumberOfProducts() {
        String test = waitForExpectedElement(numberOfProducts).getText();
        LOG.info("\n No of Products :" + test);
        return waitForExpectedElement(numberOfProducts).isDisplayed();
    }

    public boolean verifyPerPageText(String textFromPage) {
        String perPageText = waitForExpectedElement(itemsPerPageText).getText();
        LOG.info("\n Per Page Text :" + perPageText);
        return perPageText.toLowerCase().contains(textFromPage.toLowerCase());
    }

    public void onSelectOfSortBy(String sortName) {
        Select select = new Select(waitForExpectedElement(sortBy));
        if (sortName.equalsIgnoreCase(getB2bProp("sortBy.Name"))) {
            select.selectByVisibleText(sortName);
        } else if (sortName.equalsIgnoreCase(getB2bProp("sortBy.Brand"))) {
            select.selectByVisibleText(sortName);
        } else if (sortName.equalsIgnoreCase(getB2bProp("sortBy.priceLowToHigh"))) {
            select.selectByVisibleText(sortName);
        } else if (sortName.equalsIgnoreCase(getB2bProp("sortBy.priceHighToLow"))) {
            select.selectByVisibleText(sortName);
        } else {
            LOG.info(" \n  No such " + sortName + "sorting parameter exists.");
        }
    }

    public List<PLPProductItems> getProductList() {
        List<WebElement> productElementList = visibilityOfAllElementsLocatedBy(productList, 20);
        List<PLPProductItems> productItemList = new ArrayList<>();
        for (WebElement product : productElementList) {
            productItemList.add(new PLPProductItems(product));
        }
        return productItemList;
    }

    public void onSelectOfPageSize(String count) {
        Select select = new Select(waitForExpectedElement(pageSize));
        if (count.equalsIgnoreCase(getB2bProp("page.perSix"))) {
            select.selectByVisibleText(count);
        } else if (count.equalsIgnoreCase(getB2bProp("page.perTwelve"))) {
            select.selectByVisibleText(count);
        } else if (count.equalsIgnoreCase(getB2bProp("page.perEighteen"))) {
            select.selectByVisibleText(count);
        } else {
            LOG.info(" \n  No such " + count + " page size parameter exists.");
        }
    }
    
    public void closeMiniBasketPopup(){
		if(isElementPresent(closeMiniBtn)){
			waitForExpectedElement(closeMiniBtn).click();
		}
	}

    public boolean verifyPageSize(int pageSize) {
        int retryCount=0;
        while(!isElementPresent(productListSize)&&retryCount<3){
            LOG.warn("The PLP is under loading.");
            retryCount++;
        }
        int size = visibilityOfAllElementsLocatedBy(productListSize, 20).size();
        LOG.info("\n No of Products Per page ::  " + size);
        return size == pageSize;
    }

    public void onClickOfView(String view) {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(viewControllerBar));
        ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", ""); 
        if (view.equalsIgnoreCase(getB2bProp("list.view"))) {
            waitForExpectedElement(listViewIcon).click();
        } else if (view.equalsIgnoreCase(getB2bProp("grid.view"))) {
            waitForExpectedElement(gridViewIcon).click();
        } else if (view.equalsIgnoreCase(getB2bProp("add.to.basket"))) {
            List<WebElement> compareElementsList = getListOfProducts();
            if (compareElementsList != null && !compareElementsList.isEmpty()) {
                LOG.info("Now add one item from PLP into basket.");
                visibilityOfAllElementsLocatedBy(addToBasketBtn).get(0).click();
            }
            waitForExpectedElement(gridViewIcon).click();
        } else {
            LOG.info(" \n No such " + view + " parameter exists.");
        }
    }

    public boolean isCorrectViewDisplayed(String view) {
        boolean isRightView = false;
        LOG.info("\n Selected View :: " + view);
        if (view.equalsIgnoreCase(getB2bProp("grid.view"))) {
            LOG.info("\n Grid View Selected - displayed :: " + waitForExpectedElement(gridViewActivated).isDisplayed());
            LOG.info("\n Grid View Selected - enabled :: " + getWebDriver().findElement(gridViewActivated).isEnabled());
            if (getWebDriver().findElement(gridViewActivated).isDisplayed()) {
                isRightView = true;
            }
        } else if (view.equalsIgnoreCase(getB2bProp("list.view"))) {
            LOG.info("\n List View Selected - displayed :: " + waitForExpectedElement(listViewActivated).isDisplayed());
            LOG.info("\n List View Selected - enabled :: " + getWebDriver().findElement(listViewActivated).isEnabled());
            if (getWebDriver().findElement(listViewActivated).isDisplayed()) {
                isRightView = true;
            }
        }
        return isRightView;
    }

    public boolean isDefaultViewDisplayed() {
        boolean isRightView = false;
        waitTime();
        if (waitForExpectedElement(By.xpath(".//*[@class='icon-th active']"), 10).isDisplayed()) {
            isRightView = true;
        }
        return isRightView;
    }

    public void selectOneItemFromFilterMenu(String filterName) {
        for (WebElement filter : this.visibilityOfAllElementsLocatedBy(allFacetFilters)) {
            if (filter.findElement(filterText).getText().contains(filterName.toUpperCase())) {
                WebElement targetFilterOption = filter.findElements(By.cssSelector("a>label")).get(0);
                jsClick(targetFilterOption);
                mouseOverToCornerOfPLP();
                return;
            }
        }
        LOG.error("Cannot find target filter type: " + filterName);
    }

    public void onSelectOfProductType() {
        if (isElementPresent(productTypes)) {
            mouseOverToCornerOfPLP();
            getWebDriver().findElements(productTypes).get(0).click();
        } else {
            LOG.error("Fail to find the product type filter in facet navigation.");
        }
    }

    public void onSelectOfReviewStar(int starProduct) {
        this.waitForExpectedElement(reviewStarsList);
        WebElement starsList = waitForExpectedElement(reviewStarsList, 20);
        List<WebElement> starsRows = starsList.findElements(By.xpath("./ul/li[@id and not(@class='hide')]"));
        for (WebElement starRow : starsRows) {
            int rowStarCount = starRow.findElements(By.cssSelector("i.iStarYellow")).size();
            if (rowStarCount == starProduct) {
                mouseOverToCornerOfPLP();
                starRow.findElement(By.cssSelector("input[type='checkbox']")).click();
                break;
            }
        }
    }

    public void onSelectOfPriceRangeType(String priceRangeType) {
        if (this.isElementPresent(priceTypes)) {
            List<WebElement> priceRangeList = this.visibilityOfAllElementsLocatedBy(priceTypes);
            for (WebElement price : priceRangeList) {
                if (priceRangeType.equals(price.findElement(By.xpath(".//a/span/span[not(@class)]")).getText().trim())) {
                    mouseOverToCornerOfPLP();
                    price.findElement(By.cssSelector("fieldset[id='PRICE'] .fauxCheckbox")).click();
                    break;
                }
            }
        } else
            LOG.error("Fail to find the target type in facet navigation: " + priceRangeType);
    }

    public void onSelectCardEnabled(String cardNameOption) {
        if (this.isElementPresent(cardEnableTypes)) {
            List<WebElement> cardCList = getWebDriver().findElements(cardEnableTypes);
            for (WebElement cardCItem : cardCList) {
                if (cardNameOption.equals(cardCItem.findElement(By.xpath("./label/span/*[not(@class)]")).getText().trim())) {
                    mouseOverToCornerOfPLP();
                    cardCItem.findElement(By.cssSelector("input[type='checkbox']")).click();
                    break;
                }
            }
        } else
            LOG.error("Fail to find the target type in facet navigatino: " + cardNameOption);
    }

    public boolean getProductTypes(String productType) {
        mouseOverToCornerOfPLP();
        boolean productTypeFlag = false;
        List<WebElement> productTypeList = visibilityOfAllElementsLocatedBy(facetActiveFilterList);
        for (WebElement product : productTypeList) {
            if (productType.equals(product.findElement(By.xpath(".//*[@class='facetRowClose']/span[1]")).getText().trim())) {
                productTypeFlag = true;
            } else if (productType.contains("GSL")) {
                productTypeFlag = true;
            }
        }
        return productTypeFlag;
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

    public boolean verifyIfClearAllIsDisplayed() {
        return isElementPresent(clearAll);
    }

    public boolean verifyIfFacetFilterIsApplied() {
        List<WebElement> reviewStarsList = visibilityOfAllElementsLocatedBy(facetActiveFilterList,20);
        return !reviewStarsList.isEmpty() && reviewStarsList.get(0) != null;
    }

    public int getActiveFilterAmount() {
    	if(!isElementPresent(facetActiveFilterList)){
    		LOG.info(" \n No active filter elements.");
    		return 0;
    	} 	
        List<WebElement> reviewStarsList = visibilityOfAllElementsLocatedBy(facetActiveFilterList,20);
        LOG.info(" \n number of active filter elements :: " + reviewStarsList.size());
        LOG.info(" \n Any active filter elements is present :: " + reviewStarsList.isEmpty());
        return reviewStarsList.size();
    }

    public void clearAllAppliedFilters() {
        waitForExpectedElement(clearAll,20);
        List<WebElement> reviewStarsList = visibilityOfAllElementsLocatedBy(facetActiveFilterList);
        List<WebElement> testingFilterList  = getWebDriver().findElements(listOfSelectedFilters);
        LOG.info("\n Before Clear All ::" + reviewStarsList.size());
        LOG.info("\n testingFilterList  Before Clear All ::" + testingFilterList.size());
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(clearAll));
    	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -100)", "");
    	jsClick(waitForExpectedElement(clearAll));
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

    public void inputProductsInRange(Map<String, String> priceRange) {
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(userOwnPriceRange));
    	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -100)", "");
        WebElement priceRangeTab = waitForExpectedElement(userOwnPriceRange);
        priceRangeTab.findElement(By.cssSelector("#low_price_input")).sendKeys(priceRange.get("From"));
        priceRangeTab.findElement(By.cssSelector("#high_price_input")).sendKeys(priceRange.get("To"));
        priceRangeTab.findElement(By.cssSelector("#price_range_go")).click();
    }

    public void selectedOnNavigationButton(String navButton) {
        if (navButton.equalsIgnoreCase(getB2bProp("next.button"))) {
        	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(nextButton,30));
        	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -100)", "");          
        	waitForExpectedElement(nextButton).click();
        } else if (navButton.equalsIgnoreCase(getB2bProp("prev.button"))) {
        	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(previousButton,30));
        	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -100)", "");
        	waitForExpectedElement(previousButton).click();       	
        }
    }

    public boolean verifyNavigatedToNextPage() {
        boolean flag = false;
        String pageNumber = waitForExpectedElement(activePageNumber).getText();
        LOG.info(" active Page number" + Integer.parseInt(pageNumber));
        List<WebElement> restPageNumbers = getWebDriver().findElements(inactivePageNumber);
        for (WebElement element : restPageNumbers) {
            LOG.info(" Inactive Page number in IF" + Integer.parseInt(element.getText()));
            if (Integer.parseInt(pageNumber) < Integer.parseInt(element.getText())) {
                flag = waitForExpectedElement(activePageNumber).isDisplayed();
            } else if (Integer.parseInt(pageNumber) > Integer.parseInt(element.getText())) {
                LOG.info(" Inactive Page number else IF" + Integer.parseInt(element.getText()));
                flag = waitForExpectedElement(activePageNumber).isDisplayed();
            }
        }
        return flag;
    }

    public void refreshThePage() {
        getWebDriver().navigate().refresh();
        /*Actions actions = new Actions(getWebDriver());
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();*/
    }

    public boolean inspectTheFilterSign() {
        boolean verifySignFlag = false;
        waitForElementAvailableAndVisible(filterBySign).click();
        Dimension dimensions = waitForExpectedElement(filterSection).getSize();
        if (Integer.parseInt(getB2bProp("filterByDim.width")) == dimensions.width) {
            if (Integer.parseInt(getB2bProp("filterByDim.height")) == dimensions.width) {
                verifySignFlag = true;
            }
        }
        return verifySignFlag;
    }

    public boolean verifyTheFilterSign() {
        boolean verifySignFlag = false;
        waitForExpectedElement(filterBySign).click();
        Dimension dimensions = waitForExpectedElement(filterSection).getSize();
        if (Integer.parseInt(getB2bProp("filterByDim.width")) == dimensions.width) {
            if (Integer.parseInt(getB2bProp("filterByDim.height")) < dimensions.width) {
                verifySignFlag = true;
            }
        }
        return verifySignFlag;
    }

    public boolean verifyPageHeading() {
        boolean pageHeadingFlag = false;
        String pageHeadText = waitForExpectedElement(pageHeading).getText();
        if (pageHeadText.equalsIgnoreCase(getB2bProp("category.babyChanging"))) {
            pageHeadingFlag = true;
        }
        return pageHeadingFlag;
    }

    public boolean verifyPriceRangeText() {
        boolean priceRangeFlag = false;
        String pageHeadText = waitForExpectedElement(priceRangeText).getText();
        if (pageHeadText.equalsIgnoreCase(getB2bProp("price.rangeText"))) {
            priceRangeFlag = true;
        }
        return priceRangeFlag;
    }

    public void onClickOfProductImage() {
   /* List<WebElement> prodImages = getWebDriver().findElements(productImage);
      WebElement element = prodImages.get(0);
    waitForElementDisplayedAndClickable(productImage).click();*/
        List<WebElement> compareElementsList = getListOfProducts();
        if (compareElementsList != null && !compareElementsList.isEmpty()) {
            compareElementsList.get(0).findElement(productImage).click();
        }
    }

    public void onClickOfProductName() {
        List<WebElement> prodNames = getWebDriver().findElements(productName);
        prodNames.get(0).click();
/*
            List<WebElement> compareElementsList = getProductList();
            if (compareElementsList != null && !compareElementsList.isEmpty()) {
                compareElementsList.get(0).findElement(productImage).click();
            }
*/
    }

    public boolean isNavigatedToProductDetailsPage() {
        boolean isRelevantDetailPage = false;
        if (waitForExpectedElement(prodDetailPageSection, 20).isDisplayed()) {
            isRelevantDetailPage = true;
        }
        getWebDriver().navigate().back();
        //  boolean pdpFlag  = waitForElementAvailableAndVisible(prodDetailPageSection).isDisplayed();
        return isRelevantDetailPage;
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productListSize, 20);
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

    private boolean isItemExistedInSelector(WebElement selector, String itemName) {
		Select select=new Select(selector);
		for (WebElement opt : select.getOptions()) {
			if (opt.getText().trim().equals(itemName))
				return true;
		}
		return false;
	}
    
    private void selectItemFromList(WebElement selector, String text) {
		Select select = new Select(selector);
		if (isItemExistedInSelector(selector, text)) {
			select.selectByVisibleText(text);
		} else {
			LOG.warn("The option item " + text + " is not found. Now just select first option by default.");
			select.selectByIndex(0);
		}
	}
    
    public void addProductToBasketInPLP(String productName, String qty, String basketName) {
    	String targetProductXpath="//a[contains(text(),'"+productName+"')]/ancestor::div[@class='product row']";
		LOG.info("product row xpath: "+targetProductXpath+" /n Now scroll the product into current view.");
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(By.xpath(targetProductXpath)));
		WebElement productRow=waitForExpectedElement(By.xpath(targetProductXpath));	
		for(WebElement selector: productRow.findElements(By.cssSelector("select"))){
			if(isItemExistedInSelector(selector,basketName)){
				selectItemFromList(selector,basketName);
				String actionModelArea=".//select[@title='"+basketName+"']/ancestor::div[@class='col-3 mobileRight']";
				LOG.info("Now update quantity to "+qty+" for basket: "+basketName);
				WebElement targetRow = productRow.findElement(By.xpath(actionModelArea));
				targetRow.findElement(qtyInputField).clear();
				targetRow.findElement(qtyInputField).sendKeys(qty);
				jsClick(targetRow.findElement(targetAdd));
				break;
			}
		}
    }

   
    public String getNumberOfProductAddedToBasket(String basketType) {
    	String noOfItems = null;
        if (basketType.equalsIgnoreCase("AAH")) {
            noOfItems = waitForExpectedElement(basketCountAAH).getText();
        } else if (basketType.equalsIgnoreCase("Enterprise")) {
            noOfItems = waitForExpectedElement(basketCountEnterprise).getText();
        }
        return noOfItems;
    }

    public boolean isNavigatedToCheckoutPage() {
        return waitForExpectedElement(checkOutPage).isDisplayed();
    }

    public boolean verifyListOfVariantsFromCheckOutBasketAreSameAsOnPDP() {
        boolean areAttributesSame = false;
        List<WebElement> basketVariantElements = visibilityOfAllElementsLocatedBy(basketItemVariants);
        List<String> variantsOnCheckoutPage = new ArrayList();
        for (WebElement basketVariant : basketVariantElements) {
            variantsOnCheckoutPage.add(basketVariant.getText().split(":")[1].trim());
        }
        if (multiVariantList.containsAll(variantsOnCheckoutPage)) {
            areAttributesSame = true;
            multiVariantList.clear();
        }
        return areAttributesSame;
    }

    public void waitTime() {
        try {
            Thread.sleep(10000);
        } catch (
                InterruptedException e
                ) {
            e.printStackTrace();
        }
    }

    public String verifySkuDetailsDisplayed(String selectedProduct, String skuDetails) {
        String plpPageLabels = null;
        List<WebElement> compareElementsList = visibilityOfAllElementsLocatedBy(productList, 20);
        for (WebElement compareElement : compareElementsList) {
            WebElement productTitleElement = compareElement.findElement(productName);
            if (productTitleElement.getText().contains(selectedProduct)) {
                LOG.info("\n Product Name : : --> " + productTitleElement.getText());
                LOG.info("\n Product Name : : --> " + compareElement.findElement(addToFavouritesLabel).getText().trim());
                if (skuDetails.contains(getB2bProp("addTo.favourites.label"))) {
                    plpPageLabels = compareElement.findElement(addToFavouritesLabel).getText().trim();
                }
                break;
            }
        }
        return plpPageLabels;
    }

    public void clickOnLink(String favouriteLink) {
        List<WebElement> productElementList = visibilityOfAllElementsLocatedBy(productList, 20);
        for (WebElement compareElement : productElementList) {
            WebElement productTitleElement = compareElement.findElement(productName);
            if (productTitleElement.getText().contains(getB2bProp("huggies.productName"))) {
                LOG.info("\n Product Name : : --> " + productTitleElement.getText());
                visibilityOfAllElementsLocatedBy(addToFavouritesLabel, 20).get(0).click();
                break;
            }
        }
    }

    public void goTOSelectedProduct(String product) {
        List<WebElement> productElementList = visibilityOfAllElementsLocatedBy(productList, 20);
        for (WebElement compareElement : productElementList) {
            WebElement productTitleElement = compareElement.findElement(productName);
            if (productTitleElement.getText().contains(product)) {
                LOG.info("\n Product Name : : --> " + productTitleElement.getText());
                productTitleElement.click();
                break;
            }
        }
    }

    public void provideAutoUser2LoginDetails() {
        waitForExpectedElement(logOnId, 20).sendKeys(getProp("b2b.username"));
        waitForExpectedElement(password, 10).sendKeys(getProp("b2b.password"));
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
    
    public void goToCurrentOrderFromMiniBasketPanel(){
//    	waitForExpectedElement(miniCartPanel).click();
    	waitForExpectedElement(goToCurrentOrderLink).click();
    }

    public void onClickAAHMiniCart() {
        waitForExpectedElement(miniAAHMiniCart).click();
    }
}
