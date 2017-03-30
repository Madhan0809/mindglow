package com.salmon.test.pageobjects.b2b.myaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;

public class B2BManageUsersPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BManageUsersPage.class);
	
	private By accountTable = By.cssSelector("table.responsiveTable");
	private By sectionTabs = By.cssSelector(".row a>h3");
	private By dismissAllNtfButton=By.cssSelector(".close_text.dismissAll"); 
	private By backBtn = By.cssSelector("a.btn.btnTertiary");
	private By addUserBtn = By.partialLinkText("Add User");
	private By searchExpander = By.cssSelector("abbr[title='expand section']");
    private By searchField = By.cssSelector("fieldset.row .field,fieldset.row .dropdown");
    private By searchBtn = By.cssSelector(".searchActions button.btn.btnPrimary");
    private By tableHeader = By.cssSelector("thead>tr>th");
    private By tableMultiRow = By.cssSelector("table:not([class='multi'])>tbody>tr");
    private By nextPageBtn = By.cssSelector("#dataTableContainer_page_next:not([disabled])");
    private By tableRows = By.cssSelector("table.responsiveTable>tbody>tr");
    private By accountNameInTable=By.cssSelector("tr[class^='table']>td:nth-of-type(1)");
    private By userNameInTable = By.cssSelector("tr[class^='table']>td:nth-of-type(2)");
    private By userStatusCol=By.cssSelector("td:nth-of-type(4)");
    private By actionMenu = By.cssSelector(".actions");
    private By accountLinkInTable = By.cssSelector("a.textLink");
    private By addUserRequiredInputField = By.cssSelector(".field.row");
    private By assignRolecheckbox = By.cssSelector("td .checkboxField>label");
    private By saveBtn = By.cssSelector("input[value='Save']");
    
    private AccountSimpleTable simpleTable;
    
	public void clickSectionFromMyAccount(String sectionName) {
		for (WebElement section : this.visibilityOfAllElementsLocatedBy(sectionTabs,25)) {
			if (section.getText().trim().equalsIgnoreCase(sectionName)) {
				section.click();
				break;
			}
		}
	}

    public void searchUserAccount(Map<String, String> searchInfo) {
        visibilityOfAllElementsLocatedBy(tableRows);
        LOG.info("Scroll to top of the page and search user account info.");
        ((JavascriptExecutor)getWebDriver()).executeScript("window.scrollTo(0, -(document.body.scrollHeight))");
        ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", waitForExpectedElement(searchExpander));
        for (WebElement field : visibilityOfAllElementsLocatedBy(searchField)) {
            String fieldText = field.findElement(By.cssSelector("label:first-child")).getText().trim();
            if (searchInfo.get(fieldText) != null) {
                AssignValueForInputFieldItem(field.findElement(By.cssSelector("*:last-child")),
                        searchInfo.get(fieldText));
            }
        }
        waitForExpectedElement(searchBtn).click();
    }
	
	private List<Map<String, String>> getCurrentAccountTableInfo() {
		WebElement simpleTable = waitForExpectedElement(accountTable);
		List<Map<String, String>> tableDetailInfo = new ArrayList<Map<String, String>>();
		if (simpleTable.findElements(tableMultiRow).size() == 0) {
			return null;
		}
		for (int i = 1; i <= simpleTable.findElements(tableMultiRow).size(); i++) {
		    ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", simpleTable.findElements(tableMultiRow).get(i-1));
			tableDetailInfo.add(getTableMultiRowInfo(waitForExpectedElement(accountTable), i));
		}
		LOG.info("The current table account information are"+tableDetailInfo.toString());
		return tableDetailInfo;
	}
	
	private Map<String, String> getTableMultiRowInfo(WebElement simpleTable, int row) {
        Map<String, String> rowInfo = new HashMap<String, String>();
        List<WebElement> headers = simpleTable.findElements(tableHeader);
        for (int dataCol = 0, headCol = 0; dataCol < headers.size(); dataCol++, headCol++) {
            String colName = headers.get(dataCol).getText().trim();
            if (colName.isEmpty()) {
                continue;
            } else if (isPresent(By.xpath(".//tr[starts-with(@class,'table-row-')][" + row + "]/td["+ (dataCol + 1) + "]//table//td[" + (dataCol-headCol+1) + "]"))) {
                LOG.warn("This is a table with multiple unit sections.");
                 
                String multiRow1Text = getWebDriver().findElements(By.xpath("//tr[starts-with(@class,'table-row-')]/td[" + (dataCol + 1) + "]//table//td[1]"))
                        .stream().map(e -> e.getText()).collect(Collectors.toList()).get(row).toString().replaceAll("\\[|\\]", "");
                String multiRow2Text = getWebDriver().findElements(By.xpath("//tr[starts-with(@class,'table-row-')]/td[" + (dataCol + 1) + "]//table//td[2]"))
                        .stream().map(e -> e.getText()).collect(Collectors.toList()).get(row).toString().replaceAll("\\[|\\]", "");
                rowInfo.put(colName, multiRow1Text.replaceAll("[\r\n]+", ""));
                rowInfo.put(headers.get(dataCol + 1).getText().trim(), multiRow2Text.replaceAll("[\r\n]+", ""));
                dataCol = headCol + 1;
            } else
                rowInfo.put(colName,simpleTable.findElements(By.cssSelector("tr[class^='table-row-']:nth-of-type(" + row + ")>td"))
                                .get(headCol).getText().trim().replaceAll("[\r\n]+", ""));
        }
        return rowInfo;
    }
	
	private void AssignValueForInputFieldItem(WebElement ipt, String value) {
		if (ipt.getTagName().equals("input")) {
			ipt.clear();
			ipt.sendKeys(value);
		} else if (ipt.getTagName().equals("select")) {
			Select selector = new Select(ipt);
			selector.selectByVisibleText(value);
		} else {
			LOG.error("The current input type is not found: " + ipt.getTagName());
		}
	}	 
	 
	public List<Map<String, String>> getAccountTableInfo() {
		List<Map<String, String>> allTableDetailInfo = new ArrayList<Map<String, String>>();
		allTableDetailInfo=getCurrentAccountTableInfo();
		if (allTableDetailInfo == null) {
			LOG.info("This is an empty table in user management page. The table has no content.");
			return null;
		}
		while (isElementPresent(nextPageBtn)) {
			waitForExpectedElement(nextPageBtn).click();
			allTableDetailInfo.addAll(getCurrentAccountTableInfo());
		}
		LOG.info("Permission table: " + allTableDetailInfo);
		return allTableDetailInfo;
	}
	 
	public void selectDropDownMenuByAccountName(String action, String userName) {
		while (!isAccountFoundInCurrentPageView(userName) && isPresent(nextPageBtn)) {
			waitForExpectedElement(nextPageBtn).click();
		}
		for (WebElement row : this.visibilityOfAllElementsLocatedBy(tableRows)) {
			if (row.findElement(userNameInTable).getText().trim().equals(userName)) {
				Select selector = new Select(row.findElement(actionMenu));
				LOG.info("Select from the dropdown list under user table: "+action);
				selector.selectByVisibleText(action);
				String updatedRowXpath=".//td[text()='"+userName+"']/parent::tr//td[4][text()='"+action+"']";
				if(!isElementPresent(By.xpath(updatedRowXpath))){
				    LOG.error("Operation of '"+action+"' is failed. Please recheck.");
				}
				break;
			}
		}
	}
	
	public void selectDropDownMenuForAnAccount(String action){
	   WebElement row=visibilityOfAllElementsLocatedBy(tableRows).get(0);
	   row.findElement(actionMenu).click();
	   Select selector = new Select(row.findElement(actionMenu));
       selector.selectByVisibleText(action);
	}
	 
	public boolean isUserFoundInTable(String userAccountName) {
		while(!isAccountFoundInCurrentPageView(userAccountName)&&isPresent(nextPageBtn)){
			waitForExpectedElement(nextPageBtn).click();
		}
		return isAccountFoundInCurrentPageView(userAccountName);
	}
	
    public boolean isNameFoundInTable(String name){
        while(!isFirstLastNameFoundInCurrentPageVIew(name)&&isPresent(nextPageBtn)){
            waitForExpectedElement(nextPageBtn).click();
        }
        return isFirstLastNameFoundInCurrentPageVIew(name);
    }
	
	private boolean isFirstLastNameFoundInCurrentPageVIew(String name){
	    for (WebElement link : visibilityOfAllElementsLocatedBy(accountNameInTable)) {
            if (link.getText().trim().equals(name)) {
                LOG.info("Now find the account with first+last name in table: " + name);
                return true;
            }
        }
        return false; 
	}
	
	private boolean isAccountFoundInCurrentPageView(String userAccountName){
		for (WebElement link : visibilityOfAllElementsLocatedBy(userNameInTable)) {
			if (link.getText().trim().equals(userAccountName)) {
				LOG.info("Now find the account in table: " + userAccountName);
				return true;
			}
		}
		return false;
	}
	
	public void createUserFromUserManagement(Map<String,String>userInfo){
		List<String> requiredInputFields=visibilityOfAllElementsLocatedBy(addUserRequiredInputField).stream().map(element->element.findElement(By.cssSelector("input")).getAttribute("id")).collect(Collectors.toList());
		for (String ipt : userInfo.keySet()) {
			if (requiredInputFields.contains(ipt)) {
				WebElement inputField = waitForExpectedElement(By.cssSelector("#" + ipt));
				inputField.clear();
				inputField.sendKeys(userInfo.get(ipt));
			}
		}
		getWebDriver().findElements(assignRolecheckbox).get(0).click();
		waitForExpectedElement(saveBtn).click();
	}
	

	public void clickOnAccountFromTable(String accountName) {
	    while (!clickAccountFromCurrentTable(accountName) && isPresent(nextPageBtn)) {
			waitForExpectedElement(nextPageBtn).click();
		}
	}
	
	public String getAnRandomAccountFromTable(){
	    Random randomInt=new Random();
	    WebElement randomAccount=visibilityOfAllElementsLocatedBy(accountLinkInTable).get(randomInt.nextInt(visibilityOfAllElementsLocatedBy(accountLinkInTable).size()));
	    LOG.info("The account name in format of ['firstname lastname'] is "+randomAccount.getText().trim());
	    return randomAccount.getText().trim();
	}
	
	private boolean clickAccountFromCurrentTable(String accountName){
		for (WebElement link : visibilityOfAllElementsLocatedBy(accountLinkInTable)) {
		    if (link.getText().trim().equals(accountName)) {
		        LOG.info("Now click the account: " + accountName);
		        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", link);		        
		        ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", link);
				return true;
			}
		}
		return false;
	}
	
	public List<String> getAccountTableHeaders(){
    	simpleTable=new AccountSimpleTable(waitForExpectedElement(accountTable));
    	return simpleTable.getTableHeaderColoums();
    }
	 
	public void dismissAllNotification(){
		if(isPresent(dismissAllNtfButton)){
			getWebDriver().findElement(dismissAllNtfButton).click();
		}
	}
	
	public void clickOnAddUser(){
    	waitForExpectedElement(addUserBtn).click();
    }
    
    public void clickBackButton(){
    	waitForExpectedElement(backBtn).click();
    }
}
