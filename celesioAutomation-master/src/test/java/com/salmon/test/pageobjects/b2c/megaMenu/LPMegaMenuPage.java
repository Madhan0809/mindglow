package com.salmon.test.pageobjects.b2c.megaMenu;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * It contains all the b2c elements in Mega menu pages and various actions that are being
 * performed on b2c elements
 */
public class LPMegaMenuPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPMegaMenuPage.class);
    private By signOut = By.cssSelector("a[href*='Logoff']");
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By departmentElements = By.cssSelector("#mainCategoriesList .mainCategoryLink");
    private By departmentViewLink = By.cssSelector("#mainCategoriesList>li:nth-of-type("
            + LloydsPharmacyConstants.CATEGORY_NUMBER + ") .categoryContent .viewAll");
//    private By categoryElements = By.cssSelector("#mainCategoriesList>li:nth-of-type("
//            + LloydsPharmacyConstants.CATEGORY_NUMBER + ") .subCategoriesList li a");
    private By cookiePolicyBar=By.cssSelector(".cookiePolicy");
    private By cookiePolicyBtn=By.cssSelector(".cookiePolicy button");
    
    public void gotoLloydsPharmacyHomePage() {
    	getWebDriver().manage().deleteAllCookies();
    	UrlBuilder.startAtHomePage();
        if (isPresent(signOut)) {
        	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(signOut));
            waitForExpectedElement(signOut).click();
        }               
        if(isPresent(cookiePolicyBar)){
        	waitForExpectedElement(cookiePolicyBtn).click();
        }
    }

    /**
     * To simulate 'onHover' functionality on Mega menu departments.
     *
     * @param menu The department that we want to hover on
     */
    public void onHoverOfMainMenu(String menu) {
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
    }

    /**
     * To simulate 'onHover' functionality on Mega menu departments.
     */
    public void onHoverOfDepartment(String department) {
        Actions action = new Actions(getWebDriver());
        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        for(WebElement menu: departmentElementsList){
        	if(menu.getText().trim().equals(department)){
        		 action.moveToElement(menu).build().perform();
        		 break;
            }
        }  
//         else if(!departmentElementsList.isEmpty()) {
//        	LOG.warn("Target department menu is not found. Now hover on default index:6. ");
//            action.moveToElement(departmentElementsList.get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).build().perform();
//        } else{
//        	LOG.error("Department is not found. Category list is empty.");
//        }      
    }

    /**
     * To simulate 'onClick' functionality of Mega menu departments.
     */
    public void onClickOfDepartment(String department) {
    	Actions action = new Actions(getWebDriver());
        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        for(WebElement menu: departmentElementsList){
        	if(menu.getText().trim().equals(department)){
        		 action.moveToElement(menu).click().perform();
        		 break;
            }
        }  
    	
//        Actions action = new Actions(getWebDriver());
//        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
//        if (!departmentElementsList.isEmpty()) {
//            action.moveToElement(departmentElementsList.get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).click().perform();
//        }
    }

    /**
     * To get department list for 'Shop By Category' Mega menu
     *
     * @return List of departments
     */
    public List<String> getDepartmentListForShopByCategoryMenu() {
        return visibilityOfAllElementsLocatedBy(departmentElements).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * To get category list of each department listed in 'Shop By Category' Mega menu
     *
     * @return List of categories with in a given department
     */
    public List<String> getCategoryList(String department) {
		String targetCategoriesXpath = ".//a[@class='mainCategoryLink' and contains(text(),'" + department
				+ "')]/parent::li//ul[@class='subCategoriesList']/li";
		return visibilityOfAllElementsLocatedBy(By.xpath(targetCategoriesXpath)).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    /**
     * To get 'viewAll' links for each department in a Mega menu
     *
     * @return viewAll link
     */
    public String getViewAllLinkForCategoryContent() {
        return getWebDriver().findElement(departmentViewLink).getText();
    }

    /**
     * To verify if we are navigated to appropriate department page onClick of
     * each category in Mega menu
     *
     * @param departmentPageTitle The title of the target department page.
     * @return flag
     */
    public boolean isRelevantDepartmentPage(String departmentPageTitle) {
        return departmentPageTitle.equals(getCurrentPageTitle());
    }
}
