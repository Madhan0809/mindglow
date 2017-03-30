/**
 * 
 */
package com.salmon.test.pageobjects.b2b.profilelanding;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
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
public class B2BFavouriteListPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BFavouriteListPage.class);
	private By pageHeader=By.cssSelector("#favouritesList h1");
	private By yourFavTable=By.cssSelector("section#favourites_tab1Widget table[class^='favListPageTable']");
	private By orgFavTable=By.cssSelector("section#favourites_tab2Widget table[class^='favListPageTable']");
	private By favouriteTab=By.cssSelector("div[id^='favourites_tab']");
	private By addFavListButton=By.cssSelector(".btn.btnSecondary.addToFavModal");
	private By inputNewFavField=By.cssSelector("input#name");
	private By saveBtn=By.cssSelector("input[value='Save']");
	private By favouriteNameColumn=By.cssSelector("td:first-child>a");
	private By dismissAllNtfButton=By.cssSelector(".close_text.dismissAll");
	
	private By favPanel=By.cssSelector(".row.whitePanel");
	private By favListTable=By.cssSelector("table[class^='favListPageTable']");
	
	private By actionMenu=By.cssSelector(".actionMenu");
	private By detailsBtn=By.cssSelector("tbody td:last-child>a");
	private By nameLinkInTable=By.cssSelector("tbody>tr>td:first-child>a");
	private By messageCloseIcon=By.cssSelector("#MessageArea div.close_text");
	private By infoMessagePopup=By.cssSelector("#MessageArea #successIcon~div.message_text");
	
	public String getFavouriteListPageHeader(){
		return waitForExpectedElement(pageHeader).getText().trim();
	}
	
	public List<String> getYourFavTableHeader(String tableName){	
		if (tableName.equalsIgnoreCase("Your Favourites")) {
			AccountSimpleTable simpleTable = new AccountSimpleTable(waitForExpectedElement(yourFavTable));
			return simpleTable.getTableHeaderColoums();
		} else if (tableName.equalsIgnoreCase("Organisation Favourites")) {
			AccountSimpleTable simpleTable = new AccountSimpleTable(waitForExpectedElement(orgFavTable));
			return simpleTable.getTableHeaderColoums();
		} else
			return null;
	}
	
	public void clickOnTab(String tabName){
		for(WebElement tab: this.visibilityOfAllElementsLocatedBy(favouriteTab)){
			if(tab.getText().contains(tabName)){
				LOG.info("/n Now tab on :"+tabName+" in favourite list page.");
				tab.click();
				break;
			}
		}
	}
	
	public void dismissAllNotification(){
		if(isElementPresent(dismissAllNtfButton)){
			waitForExpectedElement(dismissAllNtfButton).click();
		}
	}
	
	public void createNewFavouriteList(String newFavListName){
		waitForExpectedElement(addFavListButton).click();
		waitForExpectedElement(inputNewFavField).clear();
		getWebDriver().findElement(inputNewFavField).sendKeys(newFavListName);
		getWebDriver().findElement(saveBtn).click();
		if(isElementPresent(messageCloseIcon)){
			waitForExpectedElement(messageCloseIcon).click();
		}
	}
	
	public boolean isFavoriteListExistInTable(String favListName){
		WebElement currentTable=getCurrentTableView();
		if(currentTable.findElements(favouriteNameColumn).size()==0){
			LOG.error("The favourite is an empty list.");
			return false;
		}	
		for(WebElement fav: currentTable.findElements(favouriteNameColumn)){
			if(fav.getText().equals(favListName)){
				return true;
			}
		}
		return false;
	}
	
	private WebElement getCurrentTableView(){
		WebElement panel=waitForExpectedElement(favPanel);
		for(WebElement table: panel.findElements(favListTable)){
			if(table.isDisplayed()){
				return table;
			}
		}
		return null;
	}
	
	public boolean isDuplicateListIsFound(String oriListName){
		String dupListName="Copy_of_"+oriListName;
		for(WebElement fav: getWebDriver().findElements(favouriteNameColumn)){
			if(fav.getText().startsWith(dupListName)){
				return true;
			}
		}
		return false;
	}
	
	public void removeDuplicateList(String listToRemove){
		String dupListXpath=".//a[starts-with(text(),'"+listToRemove+"')]/ancestor::tr";
		for(WebElement row: getWebDriver().findElements(By.xpath(dupListXpath))){
			String favName=row.findElement(nameLinkInTable).getText();
			clickActionMenuForFavList("Delete list",favName);
			assertThat(this.isFavoriteListExistInTable(favName)).isFalse();
		}
	}
	
	public String getPopupInfo(){
		return waitForExpectedElement(infoMessagePopup).getText().trim();
	}
	
	private WebElement getFavouriteListRow(String favListName){
		if(!isFavoriteListExistInTable(favListName)){
			LOG.error("The favourite list is not found in table: "+favListName);
			return null;
		}
		String rowXpath=".//a[text()='"+favListName+"']/ancestor::tr";
		return getWebDriver().findElement(By.xpath(rowXpath));
	}
	
	public void clickActionMenuForFavList(String action, String favListName){
		WebElement dropDownMenu=getFavouriteListRow(favListName).findElement(actionMenu);
		Select selector=new Select(dropDownMenu);
//		dropDownMenu.findElement(By.xpath(".//option[text()='"+action+"']")).click();
		selector.selectByVisibleText(action);
	}
	
	public void clickDetailsButtonForFavList(String favListName){
		getFavouriteListRow(favListName).findElement(detailsBtn).click();
	}
	
	public void clickNameForFavList(String favListName){
		getFavouriteListRow(favListName).findElement(nameLinkInTable).click();
	}
	
	
}
