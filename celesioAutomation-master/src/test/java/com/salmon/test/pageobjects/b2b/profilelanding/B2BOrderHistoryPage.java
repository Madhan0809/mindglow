
/**
 * @author tzhao
 *
 */
package com.salmon.test.pageobjects.b2b.profilelanding;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class B2BOrderHistoryPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BOrderHistoryPage.class);
	private By titleInHeader=By.cssSelector("#content>h1");
	private By orderHistoryTable=By.cssSelector(".responsiveTable");
	private By orderViewSelector=By.cssSelector("#ordersView");
	private By linkInTable=By.cssSelector(".textLink");
	private By orderLink=By.cssSelector(".textLink[href*='OrderDetailsView']");
	private By searchExpander=By.cssSelector(".expandableToggle>abbr");
	private By searchField=By.cssSelector("#filterForm .field:not([style='display:none;'])");
	private By searchLabel=By.cssSelector(".field:not([style='display:none;'])>label");
	private By searchArea=By.cssSelector(".field>*:last-child");
	private By searchBtn=By.cssSelector(".searchActions input[value='Search']");
	private By sentDateText=By.cssSelector("tbody>tr>td:nth-of-type(6)");
	private By resendOrderLink=By.linkText("Resend Order");
	private By popupBody=By.cssSelector("#orderStatusDialog .modal-body");
	private By failureStatusOverlay=By.cssSelector(".modal-body>dl>dd:nth-of-type(1)");
	private By nextBtn=By.cssSelector("#dataTableContainer_page_next:not([disabled])>a");
	
	public String getPageTitle(){
		return waitForExpectedElement(titleInHeader).getText().trim();
	}

	public List<String> getOrderTableHeader(){
		AccountSimpleTable orderTable=new AccountSimpleTable(waitForExpectedElement(orderHistoryTable));
		return orderTable.getTableHeaderColoums();
	}
	
	public void selectDropdownListInOrderHistory(String option){
		Select orderSelector=new Select(waitForExpectedElement(orderViewSelector));
		orderSelector.selectByVisibleText(option);
	}
	
	public String getStatusFromPopupOverlay() {
		this.waitForExpectedElement(popupBody);
		for (WebElement status : getWebDriver().findElements(failureStatusOverlay)) {
			if (status.isDisplayed()) {
				return status.getText();
			}
		}
		LOG.error("There is no status overlay display in popup");
		return null;
	}
	
	public List<Map<String,String>> getOrderHistoryTableContent(){
		AccountSimpleTable orderTable=new AccountSimpleTable(waitForExpectedElement(orderHistoryTable));
		return orderTable.getAllTableContent();
	}
	
    public List<String> getColumnsOfTable(String columnName) {
        List<String> targetColumsData=new ArrayList<String>();
        AccountSimpleTable orderTable = new AccountSimpleTable(waitForExpectedElement(orderHistoryTable));
        targetColumsData.addAll(orderTable.getColumnData(columnName));
        while (isPresent(nextBtn)) {
            waitForExpectedElement(nextBtn).click();
            orderTable = new AccountSimpleTable(waitForExpectedElement(orderHistoryTable));
            targetColumsData.addAll(orderTable.getColumnData(columnName));
        }
        return targetColumsData;
    }
	
    public void searchUserAccount(Map<String, String> searchInfo) {
        if (waitForExpectedElement(searchExpander).getText().contains("+")) {
            waitForExpectedElement(searchExpander).click();
        }
        for (WebElement field : visibilityOfAllElementsLocatedBy(searchField)) {
            String fieldText = field.findElement(searchLabel).getText().replaceAll("[\r\n]+", "");
            if (searchInfo.get(fieldText) != null) {
                AssignValueForInputFieldItem(field.findElement(searchArea), searchInfo.get(fieldText));
            }
        }
        waitForExpectedElement(searchBtn).click();
    }
	
	 private void AssignValueForInputFieldItem(WebElement ipt, String value){
			if(ipt.getTagName().equals("input")){
				ipt.clear();
				ipt.sendKeys(value);
			}
			else if(ipt.getTagName().equals("select")){
				Select selector=new Select(ipt);
				selector.selectByVisibleText(value);
			}
			else{
				LOG.error("The current input type is not found: "+ipt.getTagName());
			}
		}
	
	 public void openStatusLinkForFailure(String status){
	     String targetLinksXpath=".//a[contains(@class,'textLink') and contains(text(),'"+status+"')]";
	     visibilityOfAllElementsLocatedBy(By.xpath(targetLinksXpath)).get(0).click();
	 }
	 
	
	public void clickLinkInTable(String linkName, String orderNumber){
		String targetRowXpath=".//td[3]/a[text()='"+orderNumber+"']/ancestor::tr";
		WebElement orderRow=waitForExpectedElement(By.xpath(targetRowXpath));
		for(WebElement textLink: orderRow.findElements(linkInTable)){
			if(textLink.getText().equals(linkName)){
				LOG.info("\n Link is found in order history table. Now click the link: "+linkName);
				textLink.click();
				break;
			}
		}
	}
	
	public void clickOrderInTable(){
	    LOG.info("Just click the first order link in data table.");
	    visibilityOfAllElementsLocatedBy(orderLink).get(0).click();
	}
	
	public void clickOrderInTable(String orderNumber){
		for(WebElement link:visibilityOfAllElementsLocatedBy(orderLink)){
			if(link.getText().equals(orderNumber)){
				LOG.info("\n Order is found in order history table. Now click the order of: "+orderNumber);
				link.click();
				break;
			}
		}
	}
	
	public void resendAnOrderInCurrentTable(){
		visibilityOfAllElementsLocatedBy(resendOrderLink).get(0).click();
	}
	
	
	public List<String> getSentDateFromCurrentTable(){
		return visibilityOfAllElementsLocatedBy(sentDateText).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public String getTodaysDate(){
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	    Date today = new Date();
	    return sdfDate.format(today);
	}
	
}