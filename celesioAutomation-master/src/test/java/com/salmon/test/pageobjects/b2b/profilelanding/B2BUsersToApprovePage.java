package com.salmon.test.pageobjects.b2b.profilelanding;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class B2BUsersToApprovePage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BUsersToApprovePage.class);
	
	private By usersApproveHeader=By.cssSelector("#content>h1");
	private By userNamesTab=By.cssSelector("tbody>tr>td:nth-child(1)");
	private By usersTable=By.cssSelector(".responsiveTable");
	private By searchForm=By.cssSelector(".accordionContent.forceHasLayout>form");
	private By searchExpander=By.cssSelector(".expandableToggle>abbr");
	private By searchField=By.cssSelector("form#filterForm .field");
	private By searchBtn=By.cssSelector(".searchActions>li:first-child>button");
	private By viewDetailsBtn=By.cssSelector("table>tbody a");
	private By namesColumnData=By.cssSelector("tr[class^='table']>td:nth-of-type(1)");
	private By nextBtn=By.cssSelector("#dataTableContainer_page_next:not([disabled])>a");
	private By approvedDetailsHeader=By.cssSelector("#content>h1>span");
	private By usersFormLabel=By.cssSelector("dl:not([class='shadedPanel row'])>dt:nth-of-type(n)");
	private By rejectBtn=By.cssSelector(".actions input[value='Reject']");
	private By approvedBtn=By.cssSelector(".actions input[value='Approve']");
	private By commentsField=By.id("comment");
	
	public boolean canDisplayUsersToApproveHeader(){
		if(!isElementPresent(usersApproveHeader)){
			LOG.error("The header is not presented in usersToApproved page.");
			return false;
		}
		return true;
	}
	
    public void viewDetailsForUser(String userFirstLastName) {
        String targetButtonXpath = "//td[text()='" + userFirstLastName + "']/parent::tr/td/a";
        if (isElementPresent(By.xpath(targetButtonXpath))) {
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);",
                    this.visibilityOfAllElementsLocatedBy(By.xpath(targetButtonXpath)).get(0));
            ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -100)", ""); 
            getWebDriver().findElements(By.xpath(targetButtonXpath)).get(0).click();
        } else {
            LOG.error("Fail to find user name as: " + userFirstLastName + " in table. Now select first user by default.");
            visibilityOfAllElementsLocatedBy(viewDetailsBtn).get(0).click();
        }
    }
	
	public List<String> getTableHeaders(){
		AccountSimpleTable simpleTable=new AccountSimpleTable(waitForExpectedElement(usersTable));
		return simpleTable.getTableHeaderColoums();
	}
	
	public List<String> getAllNamesInTable(){
	    List<String> NamesOnCurrentPage=this.visibilityOfAllElementsLocatedBy(namesColumnData).stream().map(name->name.getText()).collect(Collectors.toList());
	    while(isElementPresent(nextBtn)){      
            waitForExpectedElement(nextBtn).click();   
            NamesOnCurrentPage.addAll(getAllNamesInTable());
        }
	    return NamesOnCurrentPage;
	}
	
	
	public List<Map<String,String>> getAllApprovedUserTableInfo(){	
		AccountSimpleTable simpleTable=new AccountSimpleTable(waitForExpectedElement(usersTable));
		List<Map<String,String>>allUserTableInfo=simpleTable.getAllTableContent();
		while(isElementPresent(nextBtn)){		
			waitForExpectedElement(nextBtn).click();
			simpleTable=new AccountSimpleTable(waitForExpectedElement(usersTable));
			allUserTableInfo.addAll(simpleTable.getAllTableContent());
		}
		return allUserTableInfo;
	}
	
	public String getOrdersToApprovedDetailsHeader(){
		return waitForExpectedElement(approvedDetailsHeader).getText();
	}
	
    public void inputUserDetailsAndSearch(Map<String, String> userSearchInfo) {
        if (!isElementPresent(searchForm)) {
            if (isElementPresent(searchExpander)) {
                LOG.info("Click the search expander to open search form in 'Users to approve page'.");
                getWebDriver().findElement(searchExpander).click();
            } else {
                LOG.info("The search field is not existed.");
                return;
            }
        }
        for (WebElement field : this.visibilityOfAllElementsLocatedBy(searchField)) {
            String label = field.findElement(By.cssSelector("label")).getText().trim();
            if (userSearchInfo.containsKey(label)) {
                WebElement currentInput = field.findElement(By.cssSelector("input"));
                currentInput.clear();
                currentInput.sendKeys(userSearchInfo.get(label));
            }
        }
        waitForExpectedElement(searchBtn).click();
    }
	
	public List<String> getUsersFormContent(){
	    return visibilityOfAllElementsLocatedBy(usersFormLabel).stream().map(label->label.getText().replaceAll("[\r\n]+", "")).collect(Collectors.toList());
	}
	
	public boolean isUserFoundInTable(String userAccountName){	
		while(isElementPresent(nextBtn)&&!isUserFoundInCurrentTableView(userAccountName)){
			waitForExpectedElement(nextBtn).click();
		}
		return isUserFoundInCurrentTableView(userAccountName);
	}
	
	private boolean isUserFoundInCurrentTableView(String userAccountName){
		for(WebElement user: getWebDriver().findElements(userNamesTab)){
			if(user.getText().trim().equals(userAccountName)){
				LOG.info("User is found in users to approved table: "+userAccountName);
				return true;
			}
		}
		return false;
	}
	
	public void addCommentsForUser(String comments){
		waitForExpectedElement(commentsField).sendKeys(comments);
	}
	
	public void clickApprovedButton(){
		waitForExpectedElement(approvedBtn).click();
	}
	
	public void clickRejectButton(){
		waitForExpectedElement(rejectBtn).click();
	}
	
}
