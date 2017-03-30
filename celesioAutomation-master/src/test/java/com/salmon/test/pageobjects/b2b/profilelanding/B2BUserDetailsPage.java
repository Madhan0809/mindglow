package com.salmon.test.pageobjects.b2b.profilelanding;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class B2BUserDetailsPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BUserDetailsPage.class);

	private By breadCrumbLink=By.cssSelector("a[id^='WC_BreadCrumb_Link']");
	private By userDetailHeader=By.cssSelector("#userDetailsRoles>h1");
	private By permissionHeader=By.cssSelector("#userDetailsRoles>section>h2");
	private By permissionContext=By.cssSelector(".shadedPanel.row");
	private By roleAssignTable=By.cssSelector("table.responsiveTable");
	private By resetPswdLink=By.partialLinkText("Reset User Password");
	private By userDetailsLabel=By.cssSelector("form#userDetailsRoles dl:first-child>dt");
	private By userDetailsText=By.cssSelector("form#userDetailsRoles dl:first-child>dd");
	private By saveChangesBtn=By.cssSelector("input[value='Save Changes']");
	private By tableFooterText=By.cssSelector("#dataTableContainer_showing");
	private By nextBtn=By.cssSelector("#dataTableContainer_page_next:not([disabled])");
	private By resetPasswordHeader=By.cssSelector(".accountUserPasswordReset h1>span");
	private By createPswdField=By.id("password");
	private By verifyPswdFiedl=By.id("verifyPassword");
	private By resetPasswordBtn=By.cssSelector("form input[value=\"Reset User's Password\"]");
	private By resetPopupMsg=By.xpath(".//span[contains(text(),'You have successfully reset this users password.')]");
	private By errorMsg=By.cssSelector(".errorMsg");
	
	public boolean canShowPageHeader(){
		return waitForExpectedElement(userDetailHeader).getText().trim().equals("USER DETAILS");
	}
	
	public String getResetPasswordPageHeader(){
		return waitForExpectedElement(resetPasswordHeader).getText().trim();
	}
	
	public void clickBreadCrumb(String linkName){
		for(WebElement link: visibilityOfAllElementsLocatedBy(breadCrumbLink)){
			if(link.getText().equals(linkName)){
				link.click();
				break;
			}
		}
	}
	
	public void inputPasswordResetInfo(Map<String,String>pswdResetInfo){
		waitForExpectedElement(createPswdField).clear();
		waitForExpectedElement(createPswdField).sendKeys(pswdResetInfo.get("Create a Password"));
		waitForExpectedElement(verifyPswdFiedl).clear();
		waitForExpectedElement(verifyPswdFiedl).sendKeys(pswdResetInfo.get("Verify Password"));
	}
	
	public void clickResetPasswordBtn(){
		waitForExpectedElement(resetPasswordBtn).click();
	}	 
	
	public boolean canShowPopupOfResetPassword(){
		return waitForExpectedElement(resetPopupMsg).isDisplayed();
	}
	
	
	public boolean canShowPermission(){
		if(!waitForExpectedElement(permissionHeader).getText().trim().equals("PERMISSIONS")){
			LOG.error("The header is not shown as expected.");
			return false;
		}
		else if(!isElementPresent(permissionContext)){
			LOG.error("The permission explanatory text is not show.");
			return false;
		}
		return true;
	}
	
	public boolean canShowRoleAssignmentTable(){
		return isElementPresent(roleAssignTable);
	}
	
	public void clickResetPasswordLink(){
		LOG.info("Click on reset password link in user details page.");
		waitForExpectedElement(resetPswdLink).click();
	}
	
	public Map<String,String> getUserDetails(){
		Map<String,String>userDetails=new HashMap<String,String>();
		for(int i=0;i<visibilityOfAllElementsLocatedBy(userDetailsLabel).size();i++){
			String label=getWebDriver().findElements(userDetailsLabel).get(i).getText().trim();
			String text=getWebDriver().findElements(userDetailsText).get(i).getText().trim();
			userDetails.put(label,text);
		}
		return userDetails;
	}
	
	public String getTableFooterText(){
		String footerText=waitForExpectedElement(tableFooterText).getText().trim();
		return footerText;
	}
	
	public void clickSaveChangesButton(){
		LOG.info("Click on save chagnes button in user details page.");
		waitForExpectedElement(saveChangesBtn).click();
	}
	
	public List<String> getErrorMessage(){
		return visibilityOfAllElementsLocatedBy(errorMsg).stream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	
}
