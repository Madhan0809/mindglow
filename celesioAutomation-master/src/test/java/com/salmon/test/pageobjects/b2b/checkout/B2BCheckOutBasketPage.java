package com.salmon.test.pageobjects.b2b.checkout;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.DatabaseHelper;
import com.salmon.test.pageobjects.b2b.B2BPageLandingModule;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class B2BCheckOutBasketPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BCheckOutBasketPage.class);
	
	DatabaseHelper dbHelper;
	Actions action;

	private Select selector;
	private By productRowInPLP = By.cssSelector(".product_listing_container div.product.row");

	private By accountDropDown = By.id("accountDropdown");
	private By miniBasketRow = By.cssSelector(".basketOverview");
	private By miniBasket = By.id("miniBasket");
	private By brandNameInMiniBasket = By.cssSelector("div[class^='col-']>img");
	private By quanlityFromMiniBasket = By.cssSelector(".quantity");
	private By miniBasketSelector = By.cssSelector("select[data-toggle='modal']");
	private By createBasketBtn = By.cssSelector("*[value='Create Basket']");
	private By miniBasketWidgetBtn = By.cssSelector("a.btn.btnPrimary.widget_minishopcart");
	private By SendOrderBtn = By.cssSelector(".btnSecondary[value='Send Order']");
	private By RequestReviewBtn = By.cssSelector(".btnSecondary[value='Request Review']");
	private By newBasketDialog=By.cssSelector("div#NewBasketDialog div[class^='modal-content']");
	
	private By miniShopCartPopup = By.id("widget_minishopcart_popup");
	private By singleProdName = By.cssSelector("a[id^='MiniShopCartProdName']");
	private By accountInfoFromCurrentOrder = By.cssSelector(".accountInfo");
	private By deliveryPointSelector = By.cssSelector(".accountInfo select");
	private By orderDetails = By.cssSelector("#order_details");
	private By productRow = By.cssSelector("#order_details>div[class$='row']");
	private By productHeader=By.cssSelector("div.row.thead p");
	private By quantityInputField = By.cssSelector("input[id^='qty_']");
	private By updateLink = By.cssSelector("a[onclick*='setCurrentId']");
	private By continueShoppingBtn = By.cssSelector("nav#orderNav>a.btn");
	private By accountInfoHeader=By.cssSelector(".mobileHide>thead>tr>th");
	private By submitOrderBtn = By.cssSelector("input.btn.btnSecondary");
	private By removeLink = By.cssSelector("a[class^='remove_address_link']");
	private By productImg = By.cssSelector("a[id^='catalogEntry_img']>img");
	private By popupMsgInCurrentOrder = By.cssSelector("#ErrorMessageText");

	private By firstAccountSelector = By.cssSelector(".row:nth-child(2) select[name='AccountName']");
	private By firstDeliveryPointSelector=By.cssSelector("#deliveryPoint_1");
    private By firstCuttOffTimeSelector=By.cssSelector("#cutOffTime_1");
	private By createBasketDialog=By.cssSelector("#NewBasketDialog");
	private By singleProductTab = By.cssSelector(".product.row");
	private By productName = By.cssSelector("div.product_name>a");
	private By basketIdSelector = By.cssSelector(".basketId");
	private By addBtn = By.cssSelector(".btn.btnSecondary");
	private By doneBtn=By.cssSelector("input[value='Done']");
	private By closeMiniBtn=By.cssSelector("#MiniShopCartCloseButton_1");
	
	private By availableSingleRowInPLP=By.xpath(".//a[contains(@class,'hasBasket')]/ancestor::div[@class='productDetails']//div[@class='row']");
	private By miniBasketBtn=By.cssSelector("a[data-baskettype]");	
	private By miniAAHQuantity = By.cssSelector("a[data-baskettype='AAH']>span.quantity");
	private By miniEnterpriseQuantity = By.cssSelector("a[data-baskettype='ENTERPRISE']>span.quantity");
	private By miniAAHBasketSelectorOptions = By.cssSelector(".basketId[data-baskettype='AAH'] option");
	private By miniEnterpriseBasketSelector = By.cssSelector(".basketId[data-baskettype='ENTERPRISE'],.basketId[data-baskettype*='Enterprise']");
		
	private By productInCurrentOrder=By.cssSelector("#CheckoutForm .productTitle>a");
	private By miniAAHMiniCart = By.cssSelector(".btn.btnPrimary.widget_minishopcart[data-baskettype='AAH']");
	private By miniEnterpriseMiniCart = By.cssSelector(".widget_minishopcart[data-baskettype='ENTERPRISE'],.widget_minishopcart[data-baskettype='Enterprise']");
	private By multipleDeliveryPointBtn = By.cssSelector(".accountInfo>a");
	private By cancelBtn = By.cssSelector(".btnTertiary");
	private By createNewBtn = By.cssSelector("#widget_minishopcart_popup .btn.btnTertiary");
	private By quantity = By.cssSelector(".quantity");
	private By orderConfirmTitle = By.cssSelector(".breadcrumb_current");
	private By printConfirmText = By.cssSelector("#WC_OrderShippingBillingConfirmationPage_Print_Link>.button_text");
	private By continueBtn = By.cssSelector("div.continue_shopping .button_text");
	private By multiDeliveryTitle = By.cssSelector(".main_content>h1");
	private By columnHeaderList = By.cssSelector(".row.thead>p");
	private By accountHeaderList = By.cssSelector("thead>tr>th");
	private By accountDetailsList = By.cssSelector("tbody>tr>td");
    private By refernceInputField=By.id("reference");
	private By bundlesLink = By.cssSelector("a[id^='CatalogEntryViewDetailsLink']");
	private By current = By.cssSelector(".current");
	private By bundleName = By.cssSelector(".product_name>h3");
	private By panelMessage = By.cssSelector(".panel.greyBack>h2");
	private By bundleProductList = By.cssSelector(".pdpRangeItem.spacerRow div>h3>a");
	private By bundleProductTableTitle = By.cssSelector(".bundleDetails:nth-of-type(3)>.tblHeader>.row>div");
	private By dropdownAAH = By.cssSelector(".accountDropdownBuy_AAH");
	private By dropdownENTERPRISE = By.cssSelector(".accountDropdownBuy_ENTERPRISE");
	private By addToCartBtn = By.id("addToCartBtn");
	private By basketproductlist = By.cssSelector(".productTitle>a");
	private By orderTotal =  By.cssSelector(".totalBreakdown>div>div:nth-of-type(3)>p");
	private By productTitle = By.cssSelector(".productTitle>a");
	private By basketDropdownTitleList = By.cssSelector(".row.spacerRow.bottom .dropdownTitles");
	private By targetAdd = By.cssSelector("a[wairole='button'][href]");
	private By checkboxAAH = By.cssSelector(".centeredText>input[data-brand='AAH']:not([checked])");
	private By viewOrderReplyLink=By.cssSelector(".textLink");		
	
	public void landingToCheckoutBasketFromAAHLogin(B2BPageLandingModule pageLandingModule) throws Throwable {
		pageLandingModule.goToTargetLandingPageFromAAHLogin(getB2bProp("landing.checkoutbasket"));
	}

	public void selectAccountsFromDropDownHeader(String accountToSelect) {
		if (isElementPresent(accountDropDown)) {
			WebElement accountDropDownList = waitForExpectedElement(accountDropDown);
			selectItemFromList(accountDropDownList, getB2bProp(accountToSelect));
		} else
			LOG.info("There is no account selection from header. Use the default one.");
	}
	
    private void jsClick(WebElement element){
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", element);
	}
	
	public void addOneProductIntoTargetBasket(String basketName){
		for(WebElement row: this.visibilityOfAllElementsLocatedBy(availableSingleRowInPLP)){
		    WebElement basketSelector=row.findElement(By.cssSelector("select"));
		        if(isItemExistedInSelector(basketSelector,basketName)){
	                LOG.info("Add one product into basket: "+basketName);
		            selectItemFromList(basketSelector,basketName);
	                jsClick(row.findElement(targetAdd));
	                break;
	            }
		}
	}
	
	public void addProductIntoTargetBasket(String productName,String basketName){
		String targetProductXpath="//a[contains(text(),'"+productName+"')]/ancestor::div[@class='product row']";
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(By.xpath(targetProductXpath)));
		WebElement productRow=waitForExpectedElement(By.xpath(targetProductXpath));
		for(WebElement selector: productRow.findElements(By.cssSelector("select"))){
			if(isItemExistedInSelector(selector,basketName)){
				LOG.info("Now add product into basket: "+basketName);
				selectItemFromList(selector,basketName);
				String actionModelArea=".//select[@title='"+basketName+"']/ancestor::div[@class='col-3 mobileRight']";
				WebElement targetRow = productRow.findElement(By.xpath(actionModelArea));
				jsClick(targetRow.findElement(targetAdd));
				return;
			}
		}
		LOG.warn("The basket '"+basketName+"' is not found for the product: "+productName+"Just add to one basket on the dropdown list.");
		jsClick(productRow.findElement(targetAdd));
	}
	
	public List<String> getAccountHeaderInfo(){
		return this.visibilityOfAllElementsLocatedBy(accountInfoHeader).stream().map(WebElement::getText).filter(item->(!item.isEmpty())).collect(Collectors.toList());
	}
	
	
	public boolean canDisplayAllMiniBasketFromHeader(){
		WebElement miniBasketForm=waitForExpectedElement(miniBasket);
		List<WebElement> basketOverviews=miniBasketForm.findElements(miniBasketRow);
		for(WebElement bskt: basketOverviews){
			if(!bskt.isDisplayed()){
				LOG.error("One or more basket is not displayed from header.");
				return false;
			}
		}
		return true;
	}
	
	public boolean canDisplayMiniBasketPopupFromHeader(){
		if(!isElementPresent(miniShopCartPopup)){
			LOG.error("The mini shop cart basket is not displayed in header.");
			return false;
		}
		return true;
	}	
	
	public void enterDefaultAAHBasketOrderFromHeader(){
		LOG.info("Enter current order from default AAH basket from the enterprise header.");
		selectBasketFromMiniBasket(getB2bProp("b2b.aah.currentBasket"), "AAH");
		selectBasketFromMiniBasket(getB2bProp("b2b.enterprise.currentBasket"), "Enterprise");
		for(WebElement row: visibilityOfAllElementsLocatedBy(miniBasketRow)){
			if(row.findElement(brandNameInMiniBasket).getAttribute("alt").equals("AAH")){
				row.findElement(miniBasketWidgetBtn).click();
				break;
			}
		}
	}
	
	public void clickOnTargetMiniBasket(String basketType){
		for(WebElement basketBtn: this.visibilityOfAllElementsLocatedBy(miniBasketBtn)){
			if(basketBtn.getAttribute("data-baskettype").equalsIgnoreCase(basketType)){
				basketBtn.click();
				break;
			}
		}
	}	
	
	public boolean isRedirectedToTargetPage(String pageTitle){
		LOG.info("The current page title is ::::: ->"+getCurrentPageTitle().trim());
		return pageTitle.equals(getCurrentPageTitle().trim());
	}
	
	public void selectBasketFromMiniBasket(String basketName, String minibasketRowName) {
		for (WebElement singleBasket : visibilityOfAllElementsLocatedBy(miniBasketRow,20)) {
			((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", singleBasket);
			if (singleBasket.findElement(brandNameInMiniBasket).getAttribute("alt").equalsIgnoreCase(minibasketRowName)) {
				WebElement selector = singleBasket.findElement(miniBasketSelector);
				LOG.info("Now select basket: " + basketName);
				selectItemFromList(selector, basketName);
				if (!getWebDriver().findElement(createBasketDialog).isDisplayed()) {
					selector.click();
				}
				break;
			}
		}
		if(basketName.equalsIgnoreCase("Create a new basket")&&!isElementPresent(newBasketDialog)){
			LOG.warn("/n Basket row is not found: " + minibasketRowName+" start create basket from scratch.");
			selectItemFromList(waitForExpectedElement(miniBasketSelector), basketName);
		}
	}
	
	public int getProductItemsQuantityFromMiniBasket(String basketName) {		
		for (WebElement singleBasket : visibilityOfAllElementsLocatedBy(miniBasketRow)) {
			if (basketName.contains(singleBasket.findElement(brandNameInMiniBasket).getAttribute("alt"))) {
				return Integer.parseInt(singleBasket.findElement(quanlityFromMiniBasket).getText().trim());
			}
		}
		LOG.error("Fail to find any item from the basket of brand ::::: ->" + basketName);
		return 0;
	}
	
	public boolean isProductFoundInMiniShopCart(String productName){
		WebElement miniBasketPopup=waitForExpectedElement(miniShopCartPopup);
		for(WebElement prod: miniBasketPopup.findElements(singleProdName)){
			if(prod.getText().trim().equals(productName)){
				return true;
			}
		}
		LOG.error("The product is not found in mini shop cart popup::::: -> "+productName);
		return false;
	}
	
	
	public void addProductIntoBasket(String productName){
		if(productName.startsWith("productType.")){
			productName=getB2bProp(productName);
		}
		List<WebElement>products=visibilityOfAllElementsLocatedBy(productRowInPLP);
		for(WebElement pdt: products){
			if(pdt.findElement(By.cssSelector("a[id^='CatalogEntryViewDetailsLink']")).getText().trim().equals(productName)){
				pdt.findElement(addBtn).click();
				break;
			}
		}
	}
	
    public void createDefaultBasket(String basketType) {
        selectItemFromList(waitForExpectedElement(basketIdSelector, 20), "Create a new basket");
        if (!getWebDriver().findElement(createBasketDialog).isDisplayed()) {
            waitForExpectedElement(basketIdSelector).click();
        }
        if (basketType.contains("AAH") && isElementPresent(By.name("AccountName"))) {
            LOG.info("The default contract for AAH type is "+getProp("b2b.defaultContract"));
            selectItemFromList(waitForExpectedElement(By.name("AccountName")), getProp("b2b.defaultContract"));
        } else if (((basketType.contains("ENTERPRISE"))||basketType.contains("Enterprise"))&& isElementPresent(By.name("AccountName"))) {
            selectItemFromList(waitForExpectedElement(By.name("AccountName")),getProp("b2b.default.enterprise.Contract"));
        } else {
            LOG.info("Use the default select account to create a default basket.");
        }
        waitForExpectedElement(createBasketBtn).click();
    }
	
	
	public void createNewBasket(Map<String,String> basketCreateInfo){
		if(isElementPresent(By.name("AccountName"))){
		  selectItemFromList(waitForExpectedElement(By.name("AccountName")),basketCreateInfo.get("Account"));
		}
		if(isElementPresent(By.name("DeliveryPoint"))){
		  selectItemFromList(waitForExpectedElement(By.name("DeliveryPoint")),basketCreateInfo.get("DeliveryPoint"));
		}
		waitForExpectedElement(By.cssSelector("#rDate2")).click();
		waitForExpectedElement(By.cssSelector("input[id$='SubmissionDate']")).clear();
		waitForExpectedElement(By.cssSelector("input[id$='SubmissionDate']")).sendKeys(basketCreateInfo.get("SubmissionDate"));		
		waitForExpectedElement(By.cssSelector(".fieldset>div>img")).click();
		waitForExpectedElement(createBasketBtn).click();
	}

	public void createBasketFromMiniBasket(String basketName, String minibasketRowName) {
		for (WebElement singleBasket : visibilityOfAllElementsLocatedBy(miniBasketRow)) {
			if (singleBasket.findElement(brandNameInMiniBasket).getAttribute("alt")
					.equalsIgnoreCase(minibasketRowName)) {
				WebElement selector = singleBasket.findElement(miniBasketSelector);
				LOG.info("Now select basket: " + basketName);
				selectItemFromList(selector, basketName);
				selector.click();
				break;
			} else {
				WebElement selector = singleBasket.findElement(miniBasketSelector);
				LOG.info("Now select basket: " + basketName);
				selectItemFromList(selector, basketName);
				selector.click();
				break;
			}
		}
	}
	
    public void addDefaultMultipleDeliveryPoint() {
        selector = new Select(waitForExpectedElement(firstAccountSelector));
        selector.selectByVisibleText(this.getProp("b2b.duplicatedAccount"));
        if (isElementPresent(firstDeliveryPointSelector)) {
            selector = new Select(waitForExpectedElement(firstDeliveryPointSelector));
            selector.selectByIndex(1);
        }
        if (isElementPresent(firstCuttOffTimeSelector)) {
            selector = new Select(waitForExpectedElement(firstCuttOffTimeSelector));
            selector.selectByIndex(1);
        }
    }
	
    public void addCustomizedMultiDeliveryPoint(Map<String,String>userDuplicateDetails){
        HashMap<String,String>dupDetails=new HashMap<String,String>();
        dupDetails.putAll(userDuplicateDetails);
        for(String k:dupDetails.keySet()){
            if(dupDetails.get(k).startsWith("b2b.")){
                dupDetails.replace(k, this.getProp(dupDetails.get(k)));
            }
        }     
        selectItemFromList(waitForExpectedElement(firstAccountSelector),dupDetails.get("Account"));
        if (isElementPresent(firstDeliveryPointSelector)) {
            selectItemFromList(waitForExpectedElement(firstDeliveryPointSelector),dupDetails.get("Delivery Point"));
        }
        if (isElementPresent(firstCuttOffTimeSelector)) {
            selectItemFromList(waitForExpectedElement(firstCuttOffTimeSelector),dupDetails.get("Submission Date / Current Basket"));
        }  
    }
	
	public boolean isOrderAccountInfoDislayCorrectly(Map<String, String> accountSummaryInfo){
		WebElement accountInfoTab=waitForExpectedElement(accountInfoFromCurrentOrder);
		for(WebElement singleItem: accountInfoTab.findElements(By.cssSelector("ul>li"))){
			if(!accountSummaryInfo.containsValue(singleItem.getText().trim())){
				if(!isDeliveryPointFound(accountSummaryInfo.get("Delivery Point")))
					return false;
			}
		}
		return true;
	}
	
	public void addProductIntoBasket(Map<String, String> basketInfo){
		for(WebElement productRow: visibilityOfAllElementsLocatedBy(singleProductTab)){
			if(productRow.findElement(productName).getText().trim().equals(basketInfo.get("Product"))){
			  WebElement basketRow=productRow.findElement(By.xpath("//div[contains(@class,'"+basketInfo.get("Brand")+"')]/ancestor::div[@class='trow']"));
			  if(!basketInfo.get("Basket name").isEmpty()){
			  selectItemFromList(basketRow.findElement(basketIdSelector), basketInfo.get("Basket name"));
              }
			  basketRow.findElement(addBtn).click();
			  break;
			}
		}
	}	
	
	private List<Map<String,String>> getProductsInfo(){
		List<Map<String, String>> products=new ArrayList<Map<String,String>>();	
		List<WebElement> singleRows=visibilityOfAllElementsLocatedBy(productRow);
		for(int i=0;i<singleRows.size();i++){
			WebElement row=visibilityOfAllElementsLocatedBy(productRow).get(i);
			String productName=getProductName(row);
			products.add(getSingleProductInfo(productName));
		}		
		return products;
	}
	
	private Map<String,String> getSingleProductInfo(String productName){
		Map<String,String>singleProductInfo=new HashMap<String,String>();
		List<String>columns=this.visibilityOfAllElementsLocatedBy(productHeader).stream().map(WebElement::getText).collect(Collectors.toList());
		singleProductInfo.put(columns.get(0), productName);
		singleProductInfo.put(columns.get(1), getAvailabilityOfProduct(getProductRow(productName)));
		singleProductInfo.put(columns.get(2), Integer.toString(getQuantityOfProduct(productName)));
		singleProductInfo.put(columns.get(3), formatPrice(getEachPriceOfProduct(productName)));
		singleProductInfo.put(columns.get(4), formatPrice(getTotalPriceFromProductRow(productName)));
    	LOG.info("Get product info: "+singleProductInfo.toString());
		return singleProductInfo;
	}

	public void clickOnTargetProduct(String productName) {
		WebElement productRow = getProductRow(productName);
		productRow.findElement(By.cssSelector("p.productTitle>a")).click();
	}

	public boolean canProductFoundInOrderDetails(Map<String, String> productInfo) {
		for (Map<String, String> singleProduct : getProductsInfo()) {
			if (singleProduct.equals(productInfo)) {
				return true;
			}
		}
		LOG.error("The product is not found in order details: " + productInfo.get("Product"));
		return false;
	}

    public double getTotalPriceFromProductRow(String productName) {
        WebElement targetProductRow = getProductRow(productName);
        String totalPrice = targetProductRow.findElement(By.xpath(".//p[text()='Total' and @class='mLabel']/parent::div/p[2]")).getText().trim();
        return Double.parseDouble(totalPrice.replace("£", ""));
    }

	private String formatPrice(double priceToFormat) {
		DecimalFormat format = new DecimalFormat("0.00");
		return "£" + format.format(priceToFormat);
	}

	public double getEachPriceOfProduct(String productName) {
		WebElement targetProductRow = getProductRow(productName);
		String eachPrice = targetProductRow
				.findElement(By.xpath("//p[text()='Each' and @class='mLabel']/parent::div/p[2]")).getText().trim();
		return Double.parseDouble(eachPrice.replace("£", ""));
	}

	private WebElement getProductRow(String productName) {
		for (int i = 0; i < visibilityOfAllElementsLocatedBy(productRow).size(); i++) {
			WebElement row = visibilityOfAllElementsLocatedBy(productRow).get(i);
			if (row.findElement(By.cssSelector("p.productTitle>a")).getText().trim().equals(productName)) {
				return row;
			}
		}
		return null;
	}

	public int getQuantityOfProduct(String productName) {
		WebElement targetProductRow = getProductRow(productName);
		String quantity = targetProductRow.findElement(quantityInputField).getAttribute("value");
		return Integer.parseInt(quantity);
	}

	private String getProductName(WebElement singleProductRow) {
		return singleProductRow.findElement(By.cssSelector("p.productTitle>a")).getText().trim();
	}

	private String getAvailabilityOfProduct(WebElement singleProductRow) {
		return singleProductRow.findElement(By.cssSelector("#order_details>div.row>div:nth-of-type(3)")).getText().trim();
	}

	public boolean canShowAllProductImgs() {
		for (WebElement img : this.visibilityOfAllElementsLocatedBy(productImg)) {
			if (!img.isDisplayed()) {
				LOG.error("One or more product image is not displayed.");
				return false;
			}
		}
		return true;
	}

	public boolean canShowAllProductSKU() {
		List<WebElement> productRows = visibilityOfAllElementsLocatedBy(orderDetails);
		for (WebElement row : productRows) {
			if (!row.findElement(By.xpath("//p[starts-with(text(),'SKU')]")).isDisplayed()) {
				LOG.error("One or more product SKU is not displayed.");
				return false;
			}
		}
		return true;
	}

	public void updateProductQuantity(String productName, int quantity) {
		WebElement targetProductRow = getProductRow(productName);
		inputFromInputField(targetProductRow.findElement(quantityInputField), Integer.toString(quantity));	
		targetProductRow.findElement(updateLink).click();
		waitForElementAvailableAndVisible(By.cssSelector("#ShopCartPagingDisplay input[value='" + Integer.toString(quantity) + "']"));
	}
	
	private void inputFromInputField(WebElement inputField, String text) {
		inputField.clear();
		inputField.sendKeys(text);
	}

	private boolean isDeliveryPointFound(String deliveryPointName) {
		Select selector = new Select(waitForExpectedElement(deliveryPointSelector));
		for (WebElement s : selector.getAllSelectedOptions()) {
			if (s.getText().trim().equals(deliveryPointName)) {
				return true;
			}
		}
		return false;
	}

	public void cleanAllContentFromBasket(){
		while(isElementPresent(removeLink)){
		    visibilityOfAllElementsLocatedBy(removeLink).get(0).click();
		}
	}
	
	public void removeProductFromCurrentOrder(String productName) {
		WebElement productToDelete = getProductRow(productName);
		productToDelete.findElement(removeLink).click();
	}

	public void inputReference(String refData){
	    waitForExpectedElement(refernceInputField).clear();
	    waitForExpectedElement(refernceInputField).sendKeys(refData);
	}
	
    public boolean canGetCorrectPopupFromCurrentOrder(String expectedMsg) {
        if (!waitForExpectedElement(popupMsgInCurrentOrder).isDisplayed()) {
            this.waitForElementToDisappear(popupMsgInCurrentOrder);
            if (this.getQuantity().equals("0")) {
                LOG.warn("This is the last product to remove.");
                return true;
            } else
                return false;
        } else
            return waitForExpectedElement(popupMsgInCurrentOrder).getText().equals(expectedMsg);
    }

	public void clickOnButtonFromCurrentOrder(String buttonName) {
		if (buttonName.equals("Continue Shopping")) {
		    scrollToElement(waitForExpectedElement(continueShoppingBtn));
			waitForExpectedElement(continueShoppingBtn).click();
		} else if (buttonName.equals("Submit Order")) {
			waitForExpectedElement(submitOrderBtn).click();
		} else if (buttonName.equals(getB2bProp("multipleDeliveryPoint"))) {
			waitForExpectedElement(multipleDeliveryPointBtn).click();
		} else if(buttonName.equals("Done")){
			waitForExpectedElement(doneBtn).click();
		} else
			LOG.error("Cannot find the target button in current page: " + buttonName);
	}

	public String getCurrentTimeStamp() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String msg = df.format(new Date().getTime());
		return msg;
	}

	public boolean isBasketFoundInHeader(String basketName) {
		for (WebElement s : this.visibilityOfAllElementsLocatedBy(miniBasketSelector,20)) {
			if (isItemExistedInSelector(s, basketName)) {
			    LOG.info("Basket is found in mini basket header: "+basketName);
				return true;
			}
		}
		LOG.warn("Basket is not found in mini basket header: "+basketName);
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

	private boolean isItemExistedInSelector(WebElement selector, String itemName) {
		Select select=new Select(selector);
		for (WebElement opt : select.getOptions()) {
			if (opt.getText().trim().equals(itemName))
				return true;
		}
		return false;
	}

    public String getBasketMessage() {
        return waitForExpectedElement(By.id("WC_EmptyShopCartDisplayf_div_1")).getText();
    }

	public String getQuantity() {
        return waitForExpectedElement(quantity).getText();
	}

	public void onClickAAHMiniCart() {
		waitForExpectedElement(miniAAHMiniCart).click();
	}
	
	public void onClickENTERPRISEMiniCart() {
		waitForExpectedElement(miniEnterpriseMiniCart).click();
	}

	public void onClickRemove() {
		waitForExpectedElement(By.id("WC_OrderItemDetailsf_links_2_1")).click();
	}

	public void onClickSendOrder() {
		waitForExpectedElement(SendOrderBtn).click();		
	}

	
	public String getAAHQuantity() {
        return waitForExpectedElement(miniAAHQuantity).getText();
	}
	
	public String getENTERPRISEQuantity() {;
        return waitForExpectedElement(miniEnterpriseQuantity).getText();
	}
	
    public void closeMiniBasketPopup() {
        if (isElementPresent(closeMiniBtn)) {
            waitForExpectedElement(closeMiniBtn).click();
        }
    }
	
	public boolean isBasketFoundInENTERPRISEHeader(String basketName) {
		for (WebElement s : this.visibilityOfAllElementsLocatedBy(miniEnterpriseBasketSelector)) {
			if (isItemExistedInSelector(s, basketName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBasketFoundInAAHHeader(String basketName) {
		boolean flag = false;
		List<WebElement> elements = visibilityOfAllElementsLocatedBy(miniAAHBasketSelectorOptions, 10);
		for (WebElement ele : elements) {
		    LOG.info(ele.getText().trim());
			if (ele.getText().trim().equalsIgnoreCase(basketName)) {
				flag = true;
				break;
			} else {
				flag = false;
			}
		}
		return flag;
	}


    public void onHoverOfMiniCart(String basketType) {
        String strJavaScript = "var element = arguments[0];"
                + "var mouseEventObj = document.createEvent('MouseEvents');"
                + "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
        action = new Actions(getWebDriver());
        if (basketType.equalsIgnoreCase("AAH")) {
            ((JavascriptExecutor) getWebDriver()).executeScript(strJavaScript, waitForExpectedElement(miniAAHMiniCart));
            waitForExpectedElement(miniShopCartPopup);
        } else if (basketType.equalsIgnoreCase("ENTERPRISE")) {
            WebElement enterpriseMiniCart = waitForExpectedElement(miniEnterpriseMiniCart);
            ((JavascriptExecutor) getWebDriver()).executeScript(strJavaScript, enterpriseMiniCart);
            waitForExpectedElement(miniShopCartPopup);
        } else {
            LOG.error("Basket type is not found: " + basketType);
        }
    }

	public void onClickOfCreatOnMiniCart() {
		waitForExpectedElement(createNewBtn).click();
	}

	public void onClickMultiDeliveryLink() {
		waitForExpectedElement(multipleDeliveryPointBtn).click();
	}

	public String getMultiDeliveryTitle() {
        return waitForExpectedElement(multiDeliveryTitle).getText();
	}

	public List<String> getColumnHeaderList() {
    	return getWebDriver().findElements(columnHeaderList).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public List<String> getAccountHeaderList() {
    	return getWebDriver().findElements(accountHeaderList).stream().map(WebElement::getText).filter(e->!e.isEmpty()).collect(Collectors.toList());
	}

	public List<String> getAccountDetailsList() {
		return getWebDriver().findElements(accountDetailsList).stream().filter(WebElement::isDisplayed).map(WebElement::getText).filter(e->!e.isEmpty()).collect(Collectors.toList());
	}

	public boolean isDisplayMultiOrderLink() {
		return waitForExpectedElement(multipleDeliveryPointBtn).getText().equals(getB2bProp("multipleDeliveryPoint"));
	}

	public void onClickOfCancelBtn() {
		waitForExpectedElement(cancelBtn).click();
	}

	public void onClickOfEditBtn() {
		waitForExpectedElement(multipleDeliveryPointBtn).click();	
	}

	public String getOrderConfirmTitle() {
        return waitForExpectedElement(orderConfirmTitle).getText();
	}

	public String getPrintConfirmButtonText() {
        return waitForExpectedElement(printConfirmText).getText();
	}

	public void onClickOfContinueBtn() {
		waitForExpectedElement(continueBtn).click();		
	}

	public void onClickRequestReview() {
		waitForExpectedElement(RequestReviewBtn).click();	
	}

	public void onClickOfBundleLink(String bundleName) {
		for(WebElement link: visibilityOfAllElementsLocatedBy(bundlesLink)){
			if(link.getText().trim().equals(bundleName)){
				link.click();
				break;
			}	
		}		
	}

	public void clickViewOrderReplyLink(){
	    waitForExpectedElement(viewOrderReplyLink).click();
	}
	
	public List<String> getAllProductFromCurrentOrder(){
	    return visibilityOfAllElementsLocatedBy(productInCurrentOrder).stream().map(e->e.getText()).collect(Collectors.toList());
	}
	
	public String getBundlePDPName() {
        return waitForExpectedElement(current).getText();
	}

	public String getBundleName() {
        return waitForExpectedElement(bundleName).getText();
	}

	public String getPanelMessage() {
        return waitForExpectedElement(panelMessage).getText();
	}

	public List<String> getBundleProductList() {
		return getWebDriver().findElements(bundleProductList).stream().map(WebElement::getText).sorted().collect(Collectors.toList());
	}

	public List<String> getTableTitleList() {
		LOG.info("Table title list:" + visibilityOfAllElementsLocatedBy(bundleProductTableTitle).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(bundleProductTableTitle).stream().map(WebElement::getText).collect(Collectors.toList());
	}

    public void selectBasketFromGreyPanel(String basketName, String basketType) {
        if (basketType.equals("AAH")) {
            selectItemFromList(waitForExpectedElement(dropdownAAH), basketName);
        } else if (basketType.equals("ENTERPRISE")) {
            selectItemFromList(waitForExpectedElement(dropdownENTERPRISE), basketName);
        } else {
            LOG.error("Fail to find the basket type: " + basketType);
        }
    }

    public void onClickOfAddToCartBtn(String addSelectedBtn) {
        String buttonText = waitForExpectedElement(addToCartBtn).getText().trim();
        LOG.info("Button Text:" + buttonText);
        if (addSelectedBtn.equals(buttonText)) {
            LOG.info("Scroll into the top of the page and click add to basket.");
//            ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -650)", "");
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(addToCartBtn));
            ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", "");
            Actions a = new Actions(getWebDriver());
            Dimension d = waitForExpectedElement(addToCartBtn).getSize();
            a.moveByOffset(d.getWidth(), d.getHeight()).build().perform();
            waitForExpectedElement(addToCartBtn).click();
        }
    }

	public List<String> getBasketProductList() {
		LOG.info("Basket product list:" + visibilityOfAllElementsLocatedBy(basketproductlist).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(basketproductlist).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public String getOrderTotal() {
        return waitForExpectedElement(orderTotal).getText();
	}

	public String getProductName() {
		LOG.info("\n Product Name :: " + waitForExpectedElement(productTitle).getText());
		return waitForExpectedElement(productTitle).getText();
	}

	public List<String> getBasketDropdownTitleList() {
		LOG.info("Basket dropdown list:" + getWebDriver().findElements(basketDropdownTitleList).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(basketDropdownTitleList).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public void oncheckAllProductsForAAH() {
		if (getWebDriver().findElements(checkboxAAH).size() == 0) {
			return;
		}
		for (WebElement checkbox : getWebDriver().findElements(checkboxAAH)) {
			LOG.info("Find 1 unchecked AAH product!");
			checkbox.click();
		}
	}

}
