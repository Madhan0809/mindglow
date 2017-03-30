package com.salmon.test.pageobjects.b2b.myaccount;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class B2BMyAccountYourDetailsPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(B2BMyAccountYourDetailsPage.class);
    
    private By accountTable = By.cssSelector("table.responsiveTable");
    private By updateBtn = By.cssSelector(".btn.btnPrimary[value='Update']");
    private By backBtn = By.cssSelector("a.btn.btnTertiary");
    private By yourDetailsLink = By.cssSelector("a[href*='AccountYourDetailsView']>h3");
    private By headerText = By.cssSelector(".headingContainer>h1");
    private By mandatoryLabel = By.cssSelector(".key");
    private By labelList = By.cssSelector("#Register>fieldset>h2");
    private By labelList1 = By.cssSelector("#Register>fieldset:nth-of-type(1)>.field.row>label");
    private By labelList2 = By.cssSelector("#Register>fieldset:nth-of-type(2)>.field.row>label");
    private By permissions = By.cssSelector("#Register>h2");
    private By firstName = By.id("WC_Your Details_NameEntryForm_FormInput_firstName_1");
    private By lastName = By.id("WC_Your Details_NameEntryForm_FormInput_lastName_1");
    private By email = By.id("WC_UserRegistrationAddForm_FormInput_email1_In_Register_1");
    private By primaryNumber = By.id("WC_UserRegistrationAddForm_FormInput__mobile_In_Register_1");
    private By marketingPreferOption = By.xpath(".//*[@id='Register']/fieldset[3]/div/div/div/label");
    private By marketingPrefer = By.xpath(".//*[@id='Register']/fieldset[3]/div/div/p");
    private By helpLink = By.cssSelector(".userGuideLink");
    private By accountLandingView = By.cssSelector("a[href*='AccountLandingView']>span");
    private By accountTableTitleList = By.cssSelector(".responsiveTable>thead>tr>th");
    private By errorMsgList = By.cssSelector(".errorMsg");
    private By employeeID = By.cssSelector("#WC_UserRegistrationAddForm_FormInput_phoneNum_In_Register_1[name='employeeId']");
    
    
    public List<String> getPermissionTableHeader(){
    	AccountSimpleTable simpleTable=new AccountSimpleTable(waitForExpectedElement(accountTable));
    	return simpleTable.getTableHeaderColoums();
    }


	public void clickYourDetailsPageView() {
        waitForExpectedElement(accountLandingView).click();
        waitForExpectedElement(yourDetailsLink).click();
    }

	public String getHeaderText() {
		return waitForExpectedElement(headerText).getText();
	}

	public boolean isDisplayMandatory() {
		String mandatory = waitForExpectedElement(mandatoryLabel).getText();
		LOG.info("Mandatory Label:" + mandatory);
		if (mandatory.contains("Mandatory") && mandatory.contains("*")){
			return true;
		}
		else
			return false;
		}
	
	public List<String> getLabelList() {
		LOG.info("Section Label List:" + getWebDriver().findElements(labelList).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(labelList).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public List<String> getLabelList1() {
		LOG.info("Information Label List:" + getWebDriver().findElements(labelList1).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(labelList1).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public List<String> getLabelList2() {
		LOG.info("Change pwd Label List:" + getWebDriver().findElements(labelList2).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(labelList2).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public String getPermissionsLabel() {
    	return waitForExpectedElement(permissions).getText();
	}

	public String getFirstName() {
    	return waitForExpectedElement(firstName).getAttribute("value");
	}

	public String getLastName() {
    	return waitForExpectedElement(lastName).getAttribute("value");
	}

	public String getEmail() {
    	return waitForExpectedElement(email).getAttribute("value");
	}

	public String getPrimaryNumber() {
    	return waitForExpectedElement(primaryNumber).getAttribute("value");
	}

	public String getMarketingPreferTitle() {
    	return waitForExpectedElement(marketingPrefer).getText();
	}

	public List<String> getOptionList() {
		LOG.info("Marketing Prefer Option List:" + getWebDriver().findElements(marketingPreferOption).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(marketingPreferOption).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public List<String> getAccountTableTitleList() {
		LOG.info("Account Table Title List:" + getWebDriver().findElements(accountTableTitleList).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(accountTableTitleList).stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public void clickButtonOnYourDetails(String btnName) {
		if(btnName.equalsIgnoreCase("Update")){
			waitForExpectedElement(updateBtn).click();
		}
		else if(btnName.equalsIgnoreCase("Back")){
			waitForExpectedElement(backBtn).click();
		}
		else
			LOG.error("The button name is not supported: "+btnName);
	}

	public void updateFirstName(String firstNameValue) {
		waitForExpectedElement(firstName).clear();
		waitForExpectedElement(firstName).sendKeys(firstNameValue);		
	}

	public void updateLastName(String lastNameValue) {
		waitForExpectedElement(lastName).clear();
		waitForExpectedElement(lastName).sendKeys(lastNameValue);		
	}

	public void updateEmail(String emailValue) {
		waitForExpectedElement(email).clear();
		waitForExpectedElement(email).sendKeys(emailValue);	
	}

	public void updateContactNo(String contactNo) {
		waitForExpectedElement(primaryNumber).clear();
		waitForExpectedElement(primaryNumber).sendKeys(contactNo);	
	}

	public List<String> getErrorMsgList() {
		LOG.info("Error Message List:" + getWebDriver().findElements(errorMsgList).stream().map(WebElement::getText).collect(Collectors.toList()));
    	return getWebDriver().findElements(errorMsgList).stream().map(WebElement::getText).collect(Collectors.toList());

	}

	public void clearContactNumber() {
		waitForExpectedElement(primaryNumber).clear();
	}
	
	public void clearEmployeeID() {
		waitForExpectedElement(employeeID).clear();
	}


	public void clearFirstName() {
		waitForExpectedElement(firstName).clear();
	}


	public void clearLastName() {
		waitForExpectedElement(lastName).clear();
	}


	public void clearEmail() {
		waitForExpectedElement(email).clear();
	}
	
}
