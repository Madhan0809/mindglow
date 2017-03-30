package com.salmon.test.pageobjects.b2b.profilelanding;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.models.b2b.AccountSimpleTable;
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

public class B2BAccountDetailsPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BMyBasketPage.class);

	private By accountDetailsView = By.cssSelector("a[href*='AccountsListView']>h3");
	private By accountName = By.cssSelector("tr[class^='table-row']>td p");
	private By accountLogo = By.cssSelector("tr[class^='table']>td>img");
	private By userInUsersTab = By.cssSelector(".textLink");
    private By accountInputField=By.id("accountName");
	private By accountSearchBtn=By.cssSelector(".accountSearch button.btn.btnPrimary");
    private By breadCrumbs = By.cssSelector("ul[aria-label^='breadcrumb'] li");
	private By accountTable = By.cssSelector("table.responsiveTable");
	private By accountTableHeader = By.cssSelector("thead tr");
	private By tabContainer = By.cssSelector(".tab_container");
	private By preferenceForm = By.cssSelector("form.borderedPanel");
	private By preferenceDisabledForm = By.cssSelector(".shadedPanel.disabled");
	private By perferenceCheckbox = By.cssSelector(".checkboxField>label");
	private By prefStatus = By.xpath(".//*[@class='checkboxField']/input[@checked]/preceding-sibling::label/span");
	private By viewDetailsBtn=By.cssSelector("#dataTableContainer_body a.btn.btnSecondary");
	
	private By accountHeaderTitle = By.cssSelector("#content>h1");
	private By addressDetails = By.cssSelector(".accountContactInfo>dl>dt");
	private By appvCheckBox=By.cssSelector(".checkboxFieldNulled>input");
	private By approvalsCheck = By.cssSelector("label[for='orderApprovals']");
	private By amountInputField = By.cssSelector(".shadedPanel input");
	private By poundInputField = By.cssSelector("#approvalMinimumAmountPounds");
	private By penceInputField = By.cssSelector("#approvalMinimumAmountPence");
	private By saveChangesBtn = By.cssSelector("input[value='Save Changes']");
	private By deliveryPointTable = By.cssSelector(".deliveryPointsTab table.responsiveTable");
	private By accountNamesCol = By.cssSelector("a.textLink");

	private By addUserBtn = By.cssSelector("#OrganizationUsersList_toolbar_addUser");
	private By addUserRequiredInputField = By.cssSelector(".field.row");
	private By addUserFieldName = By.cssSelector("form#addUserForm>fieldset>div>label[class$='m-col-12']");
	private By saveBtn = By.cssSelector("input[value='Save']");
	private By backBtn = By.cssSelector("a.btn.btnTertiary");
	private By assignRolesTitle = By.cssSelector("fieldset:last-child>h2");
	private By assignRolesPanel = By.cssSelector(".shadedPanel.row");
	private By roleCheckbox = By.cssSelector("td>div.checkboxField>label");
	private By accountRow = By.cssSelector("tr[class^='table-row-']");
	private By singleAccountName = By.cssSelector(".responsiveTable td>h3:not([class='accessText'])");
	private By nextPageBtn = By.cssSelector("#dataTableContainer_page_next:not([disabled])>a");

	private By accountSearchForm = By.cssSelector(".accountSearch");
	private By searchAccountField = By.cssSelector("#accountName");
	private By searchBtnAddUser = By.cssSelector("#submit");
	private By searchBtnAccountList = By.cssSelector("form#filterForm .btn.btnPrimary");
	private By searchBtnUsersTab = By.cssSelector("input[value='Search']");
	private By clearResultsBtn = By.cssSelector("*[type='reset']");
	private By errorMsg = By.cssSelector(".errorMsg");

	private By usersTable = By.cssSelector("#dataTableContainer table");
	private By searchExpander = By.cssSelector(".expandableToggle>abbr");
	private By searchField = By.cssSelector("form#filterForm .field, form#filterForm .dropdown");
	
	
	public void clickAccountDetailsFromMyAccount(){
		waitForExpectedElement(accountDetailsView).click();
	}
	
	public List<String> getAllAccountNamesFromList(){  
	    List<String>allNames=visibilityOfAllElementsLocatedBy(accountName).stream().map(WebElement::getText).collect(Collectors.toList());
	    while(!visibilityOfAllElementsLocatedBy(accountName).isEmpty()&&isPresent(nextPageBtn)){
            LOG.info("The table has more than one page layout. Click next page to get next table.");
            waitForExpectedElement(nextPageBtn).click();
            allNames.addAll(visibilityOfAllElementsLocatedBy(accountName).stream().map(WebElement::getText).collect(Collectors.toList()));
        }
		return allNames;
	}
	
	public List<String> getUsersNameInUserTab(){
		return visibilityOfAllElementsLocatedBy(userInUsersTab).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public void clickViewDetailsForAccount(String account){
		String viewDetailsXpath=".//p[text()='"+account+"']/ancestor::tr//a[@class='btn btnSecondary']";
		while(!isElementPresent(By.xpath(viewDetailsXpath))&&isPresent(nextPageBtn)){
		    LOG.info("The account list contains more thant 1 page, now click 'next' page button.");
		    ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(nextPageBtn));
		    ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", ""); 
		    waitForExpectedElement(nextPageBtn).click();
		}		
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(By.xpath(viewDetailsXpath)));
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", "");
		waitForExpectedElement(By.xpath(viewDetailsXpath)).click();
	}
	
	public void viewDetailsForAnyAccount(){
	    this.visibilityOfAllElementsLocatedBy(viewDetailsBtn).get(0).click();
	}
	
	public boolean canShowAllAccountLogo(){
		for(WebElement logo: getWebDriver().findElements(accountLogo)){
			if(!logo.isDisplayed()){
				return false;
			}
		}
		return true;
	}
	
	public boolean isAccessToAccountOverviewPage(String pageBodyName){
		return waitForExpectedElement(By.cssSelector("body")).getAttribute("id").equals(pageBodyName);
	}
	
	public boolean canShowAccountTable(){
		if(!isElementPresent(accountTable)){
			return false;
		}
		else if(!waitForExpectedElement(accountTableHeader).getText().equals("Account")){
			return false;
		}
		return true;
	}
	
	public List<Map<String,String>> getAccountListTableInfo(){
		AccountSimpleTable userTable=new AccountSimpleTable(waitForExpectedElement(accountTable));
		return userTable.getAllTableContent();
	}
	
	public String getAccountDetailsHeader(){
		return waitForExpectedElement(accountHeaderTitle).getText().trim();
	}
	
	public List<String> getAddressDetails(){
		return this.visibilityOfAllElementsLocatedBy(addressDetails).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public void userInputOrderApprovals(Map<String,String> amountDetails){
		waitForExpectedElement(approvalsCheck).click();			
		waitForExpectedElement(poundInputField).clear();
		waitForExpectedElement(poundInputField).sendKeys(amountDetails.get("pounds"));
		waitForExpectedElement(penceInputField).clear();
		waitForExpectedElement(penceInputField).sendKeys(amountDetails.get("pence"));
	}
	
	public List<String> getApprovalsAmount(){
		return visibilityOfAllElementsLocatedBy(amountInputField).stream().map(e->e.getAttribute("value")).collect(Collectors.toList());
	}
	
	public void clickButtonInAccountDetails(String btnName){
		if(btnName.equalsIgnoreCase("Add User")){
			waitForExpectedElement(addUserBtn).click();
		}
		else if(btnName.equalsIgnoreCase("Save")){
			waitForExpectedElement(saveBtn).click();
		}
		else if(btnName.equalsIgnoreCase("Back")){
			waitForExpectedElement(backBtn).click();
		}
		else
			LOG.error("The button name is not supported: "+btnName);
	}
	
	public void saveChangesForApprovals(){
		waitForExpectedElement(saveChangesBtn).click();
	}
	
	public void clickOnTab(String tabName){
		for(WebElement tab: this.visibilityOfAllElementsLocatedBy(tabContainer)){
			if(tab.getText().trim().equals(tabName)){
				tab.click();
				break;
			}
		}
	}
	
    public void inputAddUserDetails(Map<String,String> userInfo){
    	List<String> requiredInputFields=visibilityOfAllElementsLocatedBy(addUserRequiredInputField).stream().map(element->element.findElement(By.cssSelector("input")).getAttribute("id")).collect(Collectors.toList());
		for (String ipt : userInfo.keySet()) {
			if (requiredInputFields.contains(ipt)) {
				WebElement inputField = waitForExpectedElement(By.cssSelector("#" + ipt));
				inputField.clear();
				inputField.sendKeys(userInfo.get(ipt));
			}
		}
    }
    
    public void unCheckOrderApproval(){
        if(waitForExpectedElement(appvCheckBox).isSelected()){
            LOG.info("Checkbox is in 'selected' status. Now uncheck it.");
            getWebDriver().findElement(approvalsCheck).click();
        }
    }
    
    public void assignRoleForNewUser(String accountName, String role){
    	 WebElement targetRow=getAccountRow(accountName);
    	 for(WebElement checkbox: targetRow.findElements(roleCheckbox)){
    		 if(checkbox.getText().trim().contains(role)){
//    		     &isPresent(By.xpath(selectCheckBoxXpath))
    			 LOG.info("Now assign role for new user: "+role);
    			 checkbox.click();
    		 }
    	 }
    }
	
    public void assignRoleForAnyAccount(String role){
        for(WebElement checkbox: this.visibilityOfAllElementsLocatedBy(roleCheckbox)){
            if(checkbox.getText().trim().contains(role)){      
//                ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(searchAccountField));              
                LOG.info("Now assign role for new user: "+role);
                checkbox.click();
                break;
            }
        }
    }
    
	public List<String> getDeliveryTableHeaders(){
		AccountSimpleTable userTable=new AccountSimpleTable(waitForExpectedElement(deliveryPointTable));
		return userTable.getTableHeaderColoums();	
	}
	
	private WebElement getAccountRow(String accountName){
		for(WebElement act: this.visibilityOfAllElementsLocatedBy(accountRow)){
			if(act.findElement(By.cssSelector("h3")).getText().contains(accountName)){
				return act;
			}
		}
		return null;
	}
	
	public List<String> getUserTableHeader(){
		AccountSimpleTable userTable=new AccountSimpleTable(waitForExpectedElement(usersTable));
		return userTable.getTableHeaderColoums();
	}
	
	public boolean clickAccountFromUsersTab(String accountName){
		while(!clickUsersFromCurrentTable(accountName)&&isPresent(nextPageBtn)){
		   waitForExpectedElement(nextPageBtn).click();
		}
		return true;
	}
	
    private boolean clickUsersFromCurrentTable(String accountName){
    	for (WebElement account : visibilityOfAllElementsLocatedBy(accountNamesCol)) {
			if (account.getText().equals(accountName)) {
				account.click();
			    return true;
			}
		}
    	return false;
    }

    public void clickAnRandomUserFromCurrentTable(){
        List<WebElement>usersInTable=visibilityOfAllElementsLocatedBy(accountNamesCol);
        usersInTable.get(0).click();
    }
    
    public boolean isUserFoundInUsersTable(Map<String, String> singleUserInfo){
    	AccountSimpleTable userTable=new AccountSimpleTable(waitForExpectedElement(usersTable));
    	while(isElementPresent(nextPageBtn)&&!userTable.getAllTableContent().contains(singleUserInfo)){
			LOG.info("The table has more than one page layout. Click next page to get next table.");
			waitForExpectedElement(nextPageBtn).click();
		}
    	return true;
    }

    
	public List<Map<String,String>>getUsersTableContent(){
		AccountSimpleTable userTable=new AccountSimpleTable(waitForExpectedElement(usersTable));
		List<Map<String,String>> tableContent=userTable.getAllTableContent();
		while(isElementPresent(nextPageBtn)){
			LOG.info("The table has more than one page layout. Click next page to get next table.");
			waitForExpectedElement(nextPageBtn).click();
			tableContent.addAll(userTable.getAllTableContent());
		}
		return tableContent;
	}

	public void inputUserDetailsToSearch(Map<String,String>userSearchInfo){
		if(waitForExpectedElement(searchExpander).getText().contains("+")){
		  waitForExpectedElement(searchExpander).click();
		}
		List<WebElement> searchFields = this.visibilityOfAllElementsLocatedBy(searchField);
		for (WebElement field : searchFields) {
		    String fieldText = field.findElement(By.cssSelector("label:first-child")).getText();
			LOG.info("input search info of: "+fieldText); 
			if (userSearchInfo.get(fieldText) != null) {
				AssignValueForInputFieldItem(field.findElement(By.cssSelector(".field>*:last-child")),
						userSearchInfo.get(fieldText));
			}
		}
		waitForExpectedElement(searchBtnUsersTab).click();
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

	
	public void searchAccountInAddUsers(String searchText){
		waitForExpectedElement(searchAccountField).clear();
		waitForExpectedElement(searchAccountField).sendKeys(searchText);
		waitForExpectedElement(searchBtnAddUser).click();
	}
	
	public List<String> getAccountNamesForAddUsers(){
		return visibilityOfAllElementsLocatedBy(singleAccountName).stream().map(user->user.getText().trim()).collect(Collectors.toList());
	}
	
	public void clearSearchResults(){
		((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(clearResultsBtn));
		((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", "");
		waitForExpectedElement(clearResultsBtn).click();
	}

	public List<String> getErrorMsgsFromAddUser(){
		return visibilityOfAllElementsLocatedBy(errorMsg).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public void searchAccountInAccountList(String searchText){
		waitForExpectedElement(searchAccountField).clear();
		waitForExpectedElement(searchAccountField).sendKeys(searchText);
		waitForExpectedElement(searchBtnAccountList).click();
	}
	
	public List<String> getAddUserFormContent(){
		List<String> userFields=new ArrayList<String>();
		for(WebElement field: this.visibilityOfAllElementsLocatedBy(addUserFieldName)){
			userFields.add(field.getText().replaceAll("[\r\n]+", ""));
		}
		return userFields;
	}
	
	public boolean canDisplayAssignRolesPanel(){
		if(!waitForExpectedElement(assignRolesTitle).getText().equalsIgnoreCase(getB2bProp("assign.role.text"))){
			return false;
		}
		else if(!isElementPresent(assignRolesPanel)){
			return false;
		}
		return true;
	}
	
	public boolean isPreferencesEnabled(){	
		if(isElementPresent(preferenceForm)){
			LOG.info("The preference area is in enabled status.");
			return true;
		}else if(isElementPresent(preferenceDisabledForm)){
			LOG.info("The preference area is grey out. It's in disabled status.");
			return false;
		}
		return false;
	}
	
	public boolean isSearchAreaDisplayed(){
		return isElementPresent(accountSearchForm);
	}
	
	public void selectPreferences(String option){
		for(WebElement opt: visibilityOfAllElementsLocatedBy(perferenceCheckbox)){
			if(opt.getText().contains(option)){
			opt.click();
			break;
			}
		}
	}
	
	public String getSelectedPreferenceOption(){
		return waitForExpectedElement(prefStatus).getText();
	}
	
	public List<String> getBreadCrumbsContext(){
		return visibilityOfAllElementsLocatedBy(breadCrumbs).stream().map(e->e.getText().replaceAll("[\r\n]+", "")).collect(Collectors.toList());
	}
	
	public boolean clickBreadCrumb(String targetLink){
		for(WebElement breadCrumbLink: visibilityOfAllElementsLocatedBy(breadCrumbs)){
			if(breadCrumbLink.getText().contains(targetLink)){
				breadCrumbLink.click();
				return true;
			}
		}
		return false;
	}
	
}
