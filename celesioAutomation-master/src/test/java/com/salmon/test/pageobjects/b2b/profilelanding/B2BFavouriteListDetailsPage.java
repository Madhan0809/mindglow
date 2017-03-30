/**
 * 
 */
package com.salmon.test.pageobjects.b2b.profilelanding;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;

/**
 * @author tzhao
 *
 */
public class B2BFavouriteListDetailsPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BFavouriteListDetailsPage.class);
	
	private By breadCrumbLink=By.cssSelector("a[id^='WC_BreadCrumb']");
	private By favNameField=By.id("listName");
	private By updateNameBtn=By.cssSelector("#listName~button");
	private By favouriteListName=By.cssSelector(".listName");
	private By productCodeInputField=By.cssSelector("#skuCode_0");
	private By skuQtyInputField=By.cssSelector("#skuQty_0");
	private By addButton=By.cssSelector("#favouritesListDetailsToolbar button");
	private By prodDescription=By.cssSelector("p[data-label='Product description']");
	private By messageInHeader=By.cssSelector(".message>.message_text");
	private By selectCheckBox=By.cssSelector("div[data-label='Select']");
	private By addSelectedItemsBtn=By.cssSelector("a[class*='addItems right']");
	private By removeItemsBtn=By.cssSelector("a[class*='removeItems']");
	private By updatingMessage=By.cssSelector("p.modal-message");
	private By productQtyInputField=By.cssSelector("input[name='qty']");
	private By productUpdateLink=By.cssSelector("a[class*='updateItems']");
	private By addToCurrentOrderBtn=By.cssSelector(".btn.btnSecondary.addItems");
	private By goToOrderPageBtn=By.cssSelector(".modal-body>.btn.btnPrimary");
	private By stayCurrentPageBtn=By.cssSelector(".modal-body>.btn.btnTertiary");
	private By emptyTableRow=By.cssSelector("#favouritesListDetailsTable>.row-empty");
	private By rowTheadColumn=By.cssSelector(".row.thead>p");
	private By sharePublicLink=By.cssSelector(".listStatus.favListDetLink");
	private By makePrivateLink=By.cssSelector(".listStatus");
	
	public void addBySku(String productCode, int qty){
		waitForExpectedElement(productCodeInputField).clear();
		getWebDriver().findElement(productCodeInputField).sendKeys(productCode);
		getWebDriver().findElement(skuQtyInputField).clear();
		getWebDriver().findElement(skuQtyInputField).sendKeys(String.valueOf(qty));
		getWebDriver().findElement(addButton).click();
	}
	
	public void clickOnBreadCrumb(String linkName){
		for(WebElement link: this.visibilityOfAllElementsLocatedBy(breadCrumbLink)){
			if(link.getText().equals(linkName)){
				link.click();
				break;
			}
		}
	}
	
	public String getFavNameInHeader(){
		return waitForExpectedElement(favouriteListName).getText();
	}
	
	private WebElement getProductRow(String prodDescription){
		String productXpath=".//p[text()='"+prodDescription+"']/ancestor::div[@class='row']";
		return waitForExpectedElement(By.xpath(productXpath));
	}
	
	public List<String> getProductsDescription(){
		return visibilityOfAllElementsLocatedBy(prodDescription).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public String getMsgPopupInHeader(){
		return waitForExpectedElement(messageInHeader).getText();
	}
	
	public void removeAllProductInTable(){
		for(WebElement checkBox: visibilityOfAllElementsLocatedBy(selectCheckBox)){
			checkBox.click();
		}
		getWebDriver().findElement(removeItemsBtn).click();
		if(isElementPresent(updatingMessage)){
			waitForExpectedElement(stayCurrentPageBtn).click();
		}
	}
	
	public boolean isEmptyTableInFavouriteDetailsTable(){
		return isElementPresent(emptyTableRow);
	}
	
	public List<String> getTableHeaders(){
		return visibilityOfAllElementsLocatedBy(rowTheadColumn).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public void updateQuantityForProduct(String prodDescription, int qty){
		WebElement targetProductRow=getProductRow(prodDescription);
		targetProductRow.findElement(productQtyInputField).clear();
		targetProductRow.findElement(productQtyInputField).sendKeys(String.valueOf(qty));
		targetProductRow.findElement(productUpdateLink).click();
		if(isElementPresent(updatingMessage)){
			waitForExpectedElement(stayCurrentPageBtn).click();
		}
	}
	
	public int getQuantityForProduct(String prodDescription){
		String prodQty=getProductRow(prodDescription).findElement(productQtyInputField).getAttribute("value");
		return Integer.parseInt(prodQty);
	}
	
	public void updateFavListName(String newFavName){
		waitForExpectedElement(favNameField).clear();
		getWebDriver().findElement(favNameField).sendKeys(newFavName);
		getWebDriver().findElement(updateNameBtn).click();
	}
	
	public void addSelectedToCurrentOrder(String prodDescription){
		WebElement targetRow=getProductRow(prodDescription);
		targetRow.findElement(selectCheckBox).click();
		getWebDriver().findElement(addSelectedItemsBtn).click();
		if(isElementPresent(updatingMessage)){
			waitForExpectedElement(stayCurrentPageBtn).click();
		}
	}
	
	public void clickAddToCurrentOrderButton(){
	    waitForExpectedElement(addToCurrentOrderBtn).click();
	}
	
	public void clickGoToCurrentOrderButton(){
		waitForExpectedElement(goToOrderPageBtn).click();
	}
	
	public void clickOnStatusLink(String status){
		if(status.contains("public")){
			waitForExpectedElement(sharePublicLink).click();
		}
		else if(status.contains("private")){
			waitForExpectedElement(makePrivateLink).click();
		}
		else
			LOG.error("The status is not found: "+status);
	}
}
