/**
 * @author tzhao
 *
 */
package com.salmon.test.pageobjects.b2b.profilelanding;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.OrderDetailsSummaryForm;
import com.salmon.test.pageobjects.b2b.B2BPageLandingModule;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class B2BMyBasketPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BMyBasketPage.class);
    private By accountLandingView = By.cssSelector("a[href*='AccountLandingView']>span");
    private By myBasketView = By.cssSelector("a[href*='MyBasketsView']>h3");
    private By myBasketSummary = By.cssSelector(".row.itemDetails");
    private By basketNames = By.cssSelector(".tableBasketsName>a");
    private By removeButton = By.cssSelector("#WC_OrderItemDetailsf_links_2_1");
    private By removeOrderConfirmation = By.cssSelector(".message_text #ErrorMessageText");
    private By popupMessageSection = By.cssSelector("#MessageArea>div");
    private By basketName=By.cssSelector("a[data-label='Basket Name']");
    private By deleteConfirmationBtn = By.cssSelector("#DeleteBasketDialog .btn.btnPrimary");
    private By createNewBasketBtn=By.cssSelector(".btn.btnSecondary[href*='CreateNewBasket']");
    private By basketHeader=By.cssSelector(".row.thead>p:not([class*='accessText'])");
    private By basketRow=By.cssSelector(".row.itemDetails");
    private By actionDropdown=By.cssSelector(".myBasketActions");
    private By totalPriceLabel=By.cssSelector("p[data-label='Total']");
    private By miniBasketSelector = By.cssSelector("select[data-toggle='modal']");
//    private By moveToFavListBtn=By.cssSelector("#favlist button");
    private By currentOrderTitle=By.cssSelector("div.main_content h1");
    private By basketLogoImg=By.cssSelector("#newBasketContainer .fieldset>div>img");
    private By createBtn=By.cssSelector("#newBasketContainer input[value='Create Basket']");
    private By accountNameSelector=By.cssSelector("#WC_CreateBasketForm_FormInput_Account_CreateBasketPage");
    private By deliveryPointSelector=By.cssSelector("#WC_CreateBasketForm_FormInput_DeliveryPoint_CreateBasketPage");
    private By dateInputField=By.cssSelector("#newBasketContainer input[name='SubmissionDate']");
    private By submissionDateOpt=By.cssSelector("#rDate21");
    private By paginationNextBtn=By.xpath(".//div[@id='Pagination']/a[contains(text(),'Next Page')]");
    private By favorListInputField=By.cssSelector("#newFavListName");
    private By favorListOption=By.cssSelector("#favlist label");
    private By addToFavoriteListBtn=By.cssSelector("form#favlist button");
    private By messagePopup=By.cssSelector("#MessageArea div.message");
    private By paginationLink=By.xpath(".//a[contains(text(),'Next Page')]");
    
    public void enterLoginLandingPage(B2BPageLandingModule pageLandingModule) throws Throwable {
        pageLandingModule.goToTargetLandingPageFromAAHLogin(getB2bProp("landing.loginHomePageName"));
    }

    public void clickMyBasketPageView() {
        waitForExpectedElement(accountLandingView).click();
        waitForExpectedElement(myBasketView).click();
    }
    
    public List<String> getAllProductName(){
    	return this.visibilityOfAllElementsLocatedBy(basketName).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void inputCreateBasketInfoAndCreate(Map<String,String>basketCreateInfo){
    	if(isElementPresent(accountNameSelector)){
  		  selectItemFromList(waitForExpectedElement(accountNameSelector),basketCreateInfo.get("Account"));
  		}
  		if(isElementPresent(deliveryPointSelector)){
  		  selectItemFromList(waitForExpectedElement(deliveryPointSelector),basketCreateInfo.get("DeliveryPoint"));
  		}
  		if(!basketCreateInfo.get("SubmissionDate").isEmpty()){
  		waitForExpectedElement(submissionDateOpt).click();
  		waitForExpectedElement(dateInputField).sendKeys(basketCreateInfo.get("SubmissionDate"));	
  		}
  		waitForExpectedElement(basketLogoImg).click();
  		waitForExpectedElement(createBtn).click();  	
    }
    
    private void selectItemFromList(WebElement selector, String text) {
		Select select = new Select(selector);
		for(WebElement opt: select.getOptions()){
			if(opt.getText().equals(text)){
				select.selectByVisibleText(text);
				break;
			}
		}
	}
    
    public List<String> getMyBasketTableHeader(){
    	List<String>test=visibilityOfAllElementsLocatedBy(basketHeader).stream().map(WebElement::getText).filter(e->!e.isEmpty()).collect(Collectors.toList());
    	return test;
    }
    
    public List<List<String>> getMyBasketAllContent(){
    	List<List<String>>detailInfo=new ArrayList<>();
    	detailInfo.addAll(getBasketContentInCurrentPage());
    	while(isPresent(paginationNextBtn)){
            waitForExpectedElement(paginationNextBtn).click();
            detailInfo.addAll(getBasketContentInCurrentPage());
    	}
    	return detailInfo;
    }
    
    private List<List<String>> getBasketContentInCurrentPage(){
    	List<List<String>>detailInfoInPage=new ArrayList<>();
    	for(WebElement row: visibilityOfAllElementsLocatedBy(basketRow)){
    		detailInfoInPage.add(getCurrentBasketInfo(row));
    	}  	
    	return detailInfoInPage;
    }
    
    public boolean isProductDetailInfoFound(List<OrderDetailsSummaryForm> myBasketDetails) {
        List<WebElement> myBasketPanel = getWebDriver().findElements(myBasketSummary);
        LOG.info("\n myBasketPanel size : " + myBasketPanel.size());       
        List<String> expdBasketInfo = null;
        boolean flag = false;
        for (int i = 0; myBasketPanel.size() > i; i++) {
            List<String> myBasketInfo=getCurrentBasketInfo(myBasketPanel.get(i));
            expdBasketInfo = new ArrayList<String>();
            expdBasketInfo.add(myBasketDetails.get(i).getBasketName().trim());
            expdBasketInfo.add(myBasketDetails.get(i).getAccount().trim());
            expdBasketInfo.add(myBasketDetails.get(i).getDeliveryPoint().trim());
            expdBasketInfo.add(myBasketDetails.get(i).getSendOrderOn().trim());
            expdBasketInfo.add(myBasketDetails.get(i).getTotal().trim());
            LOG.info("\n Actual basket info :: " + i + ": " + myBasketInfo);
            LOG.info("\n Expected basket info :: " + i + ": " + expdBasketInfo);
            flag = compareLists(expdBasketInfo, myBasketInfo);
            LOG.info("\n The compare result for current basket is :: " + i + ": " + flag);
            if (flag == false) {
                break;
            }
        }
        return flag;
    }
    
    public boolean selectOptionInFavoirteDialog(String optName){
    	for(WebElement opt:this.visibilityOfAllElementsLocatedBy(favorListOption)){
    		if(opt.getText().trim().equalsIgnoreCase(optName)){
    			opt.findElement(By.cssSelector("input")).click();
    			return true;
    		}
    	}
    	return false;
    }
    
    public void inputNewFavoriteList(String favoriteList){
    	waitForExpectedElement(favorListInputField).clear();
    	waitForExpectedElement(favorListInputField).sendKeys(favoriteList);
    }
    
    public boolean canShowAddToFavoriteListMessage(String messageExpected){
    	if(!waitForExpectedElement(messagePopup).isDisplayed()){
    		return false;
        } else if (!waitForExpectedElement(messagePopup).getText().equals(messageExpected)) {
            LOG.info("Get message from fav list: "+waitForExpectedElement(messagePopup).getText());
            return false;
    	}
    	return true;
    }
    
    public List<String> getSpecificBasketInfo(String basketName){
    	for(String bskt: getAllProductName()){
    		if(bskt.equals(basketName)){
    			String panelXpath=".//a[contains(text(),'"+basketName+"')]/ancestor::div[@class='row itemDetails']";
    			return getCurrentBasketInfo(waitForExpectedElement(By.xpath(panelXpath)));
    		}
    	}
    	return null;
    }
    
    private List<String> getCurrentBasketInfo(WebElement myBasketPanel){
    	List<String> myBasketInfo = new ArrayList<String>();
        String basketName = myBasketPanel.findElement(By.xpath(".//*[a[@data-label='Basket Name']]")).getText().trim();
        myBasketInfo.add(basketName);
        String account = myBasketPanel.findElement(By.xpath(".//*[p[@data-label='Account']]")).getText().replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
        myBasketInfo.add(account);
        String deliveryPoint = myBasketPanel.findElement(By.xpath(".//*[p[@data-label='Delivery Point']]")).getText().trim();
        myBasketInfo.add(deliveryPoint);
        String sendOrderOn = myBasketPanel.findElement(By.xpath(".//*[p[@data-label='Send Order On']]")).getText().trim();
        myBasketInfo.add(sendOrderOn);
        String total = myBasketPanel.findElement(By.xpath(".//*[p[@data-label='Total']]")).getText().trim();
        myBasketInfo.add(total);
        return myBasketInfo;
    }

    public boolean compareLists(List<String> expectedString, List<String> actualString) {
        boolean listFlag = false;
        if (expectedString != null && actualString != null && (expectedString.size() == actualString.size())) {
        	expectedString.removeAll(actualString);
            if (expectedString.isEmpty()) {
                listFlag = true;
                LOG.info("\n Both list are same.");
            }
        }
        return listFlag;
    }

    private WebElement getBasketRow(String basketName){
    	List<WebElement> myBasketPanel = visibilityOfAllElementsLocatedBy(myBasketSummary);
    	for(WebElement basket: myBasketPanel){
    		if(basket.findElement(By.xpath(".//*[a[@data-label='Basket Name']]")).getText().trim().equals(basketName)){
    			return basket;
    		}
    	}
    	return null;
    }
    
    public void clickOnBasket(String basketName){
    	while(!clickBasketInCurrentPage(basketName)&&isElementPresent(paginationNextBtn)){
    		waitForExpectedElement(paginationNextBtn).click();
    	}
	}
    
    private boolean clickBasketInCurrentPage(String basketName){
    	for (WebElement baskt : visibilityOfAllElementsLocatedBy(basketNames)) {
			if (baskt.getText().equals(basketName)) {
			    LOG.info("Click on basket link in my basket page: "+basketName);
				baskt.click();
				return true;
			}
		}
    	return false;
    }
    
    public void clickOnDeleteConfirmPopup(String buttonOption){
        waitForExpectedElement(deleteConfirmationBtn).click();
    }
    
    public void selectActionFromRandomBasket(String actionOpt){
        Select dropDown=new Select(this.visibilityOfAllElementsLocatedBy(actionDropdown).get(0));
        dropDown.selectByVisibleText(actionOpt);
    }
    
    
	public void selectActionDropDown(String actionOpt, String basketName) {		
		if(!isBasketFoundInMyBakset(basketName))
			return;
		WebElement basketPanel = getBasketRow(basketName);
		Select dropDown=new Select(basketPanel.findElement(By.cssSelector("select")));
		dropDown.selectByVisibleText(actionOpt);
	}

	public void acceptPopupAlert(){
		if (getWebDriver().switchTo().alert() != null) {
			LOG.info("Accept the alert popup in my basket page.");
			getWebDriver().switchTo().alert().accept();
		}
		else
			LOG.info("There is no alert popup.");
	}
	
    public boolean verifyCurrentOrderPage() {
        boolean flag = false;
        if(!isElementPresent(currentOrderTitle)){
        	LOG.error("The basket title is not displayed.");
        	return false;
        }
        String pageTitle = getCurrentPageTitle();
        LOG.info(" \n Page Title :: " + pageTitle);
        if (pageTitle.equalsIgnoreCase(getB2bProp("current.order"))) {
            flag = true;
        } else {
            LOG.info(" \n Page Title :: " + pageTitle);
        }
        return flag;
    }

    public void clickOnBasketName() {
        List<WebElement> basketRows = getWebDriver().findElements(basketNames);
        for (WebElement ele : basketRows) {
            if (ele.getText().trim().equalsIgnoreCase(getB2bProp("basket.name"))) {
                LOG.info(" \n Basket Name  :: " + ele.getText());
                ele.click();
                break;
            }
            LOG.error("The b2b default basket is not found. Please check.");
        }
    }

    public boolean isBasketFoundInHeader(String basketName) {
        for (WebElement s : this.visibilityOfAllElementsLocatedBy(miniBasketSelector,20)) {
            if (isItemExistedInSelector(s, basketName)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isItemExistedInSelector(WebElement selector, String itemName) {
        Select select=new Select(selector);
        for (WebElement opt : select.getOptions()) {
            if (opt.getText().trim().equals(itemName))
                return true;
        }
        return false;
    }
    
	public boolean isBasketFoundInMyBakset(String basketName) {
		if (getAllProductName().contains(basketName)) {
			return true;
		}
		while (isElementPresent(paginationLink)){
			((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, 650)", ""); 
			waitForExpectedElement(paginationLink).click();
			if(getAllProductName().contains(basketName)){
				return true;
			}
		}
		return false;
	}
	
	public String getTotalPriceForProduct(String basketName){
        if (isBasketFoundInMyBakset(basketName)) {
            if (getAllProductName().contains(basketName)) {
                WebElement targetRow = getBasketRow(basketName);
                return targetRow.findElement(totalPriceLabel).getText().trim();
            }
        }
        return null;
    }
    
    
    public boolean verifyEmptyCurrentOrder() {
        boolean flag = false;
        String confirmationMsg = waitForExpectedElement(removeOrderConfirmation).getText();
        if (confirmationMsg.equalsIgnoreCase(getB2bProp("itemRemoved.confirmationMessage")) &&
                waitForElementAvailableAndVisible(popupMessageSection).isDisplayed()) {
            flag = true;
        }
        return flag;
    }
    
    public void clickOnAddFavoriteListButton(){
        LOG.info("Now click on add to favourite list button.");
    	waitForExpectedElement(addToFavoriteListBtn).click();
    }
    
    public void clickOnCreateBasketButton(){
        LOG.info("Now click on create new basket button.");
    	waitForExpectedElement(createNewBasketBtn).click();
    }

    public void clickOnRemoveButton() {
        LOG.info("Now click on remove button.");
        waitForExpectedElement(removeButton).click();
    }
}