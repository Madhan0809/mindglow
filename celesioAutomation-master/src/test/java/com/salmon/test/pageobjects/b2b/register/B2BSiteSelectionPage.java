/**
 * 
 */
package com.salmon.test.pageobjects.b2b.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;

/**
 * @author tzhao
 *
 */
public class B2BSiteSelectionPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BSiteSelectionPage.class);
	
	private By chooseWebsiteBtn=By.cssSelector("li>a.btn.btnSecondary");
	private By buttonInHeader=By.cssSelector("div.row>a");
	private By pageHeader=By.cssSelector("#content>h1");
	private By aahLogoImage=By.cssSelector("#contentImage_1_AccessWebsites_AAH_Espot");
	private By enterpriseLogoImage=By.cssSelector("#contentImage_1_AccessWebsites_OTC_Espot");
	
	public void chooseWebSiteFromEntryPoint(String website){
    	for(WebElement btn:visibilityOfAllElementsLocatedBy(chooseWebsiteBtn)){
    		if(btn.getText().trim().contains(website)){
    			LOG.info("Now access to the site of : "+website);
    			btn.click();
    			break;
    		}
    	}
    }
	
	public boolean isLogoImageDisplayed() {
		if (!isElementPresent(aahLogoImage)) {
			LOG.error("The AAH logo image is not presented.");
			return false;
		} else if (!isElementPresent(enterpriseLogoImage)) {
			LOG.error("The Enterprise logo image is not presented.");
			return false;
		} else
			return true;
	}
	
	
	public String getSiteSelectionPageHeader(){
		return waitForExpectedElement(pageHeader).getText().trim();
	}
	
	public void clickButtonInHeader(String buttonName){
		for(WebElement btn: this.visibilityOfAllElementsLocatedBy(buttonInHeader)){
			if(btn.getText().trim().equals(buttonName)){
				btn.click();
				break;
			}
		}
	}
	
}
