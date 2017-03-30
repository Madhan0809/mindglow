package com.salmon.test.pageobjects.b2c.department;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * It contains all the b2c elements in department pages and various actions that are being
 * performed on b2c elements
 */
public class LPDepartmentPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPDepartmentPage.class);
    private By shopByCategory = By.id("shopByCategoryMenuLink");
    private By signOut = By.linkText("Sign Out");
    private By brandImg=By.cssSelector("div[class^='brand']>a");
    private By mainNavDepartment=By.cssSelector("#mainCategoriesList>li");
    private By subCategories=By.cssSelector("div .subCategoriesList>li");
    private By departmentElements = By.cssSelector("#mainCategoriesList .mainCategoryLink");
    private By categoryElements = By.cssSelector(".sidebar.panel div.collapsibleContainer ul li a");
    private By categoryBreadCrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(2)");
    private By categoryUpInd = By.cssSelector(".collapsibleContainer h3 a i.iIndicatorUp");
    private By categoryDownInd = By.cssSelector(".collapsibleContainer h3 a i.iIndicatorDown");
    private By categoryFacetList = By.cssSelector(".collapsibleContainer ul.collapsibleTarget.active");

    public void goToDepartmentPage() {
        LOG.info(" Navigating to Lloyds Pharmacy department page \n");
        if (isElementPresent(signOut)) {
            waitForExpectedElement(signOut).click();
        }
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startAtHomePage();
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        List<WebElement> departmentElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        if (!departmentElementsList.isEmpty()) {
            action.moveToElement(departmentElementsList.get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).click().perform();
        }
    }

    public List<String> getDefaultDepSubCategories(){
        Actions action = new Actions(getWebDriver());
        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        action.moveToElement(visibilityOfAllElementsLocatedBy(departmentElements).get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).perform();
        WebElement mainNavgator=visibilityOfAllElementsLocatedBy(mainNavDepartment).get(LloydsPharmacyConstants.DEPARTMENT_INDEX);
        List<String> subCategoriesList=mainNavgator.findElements(subCategories).stream().map(e->e.getText()).collect(Collectors.toList());
        action.moveToElement(waitForExpectedElement(brandImg)).build().perform();
        return subCategoriesList;
    }
    
    /**
     * It gives relevant bread crumb in the department page
     *
     * @return relevant bread crumb text
     */
    public String getRelevantBreadCrumb() {
        return waitForExpectedElement(categoryBreadCrumb).getText();
    }


    public List<String> getDepartmentCategoryListFromLHS() {
//        Actions action = new Actions(getWebDriver());
//        action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
//        action.moveToElement(visibilityOfAllElementsLocatedBy(categoryElements).get(0)).build().perform();
        return getWebDriver().findElements(categoryElements).stream().map(cat -> cat.getText().split("\\s+\\(")[0])
                .collect(Collectors.toList());
    }

    /**
     * To simulate click functionality on each category with in each department.
     */
    public void onClickOfCategory(String category) {
        List<WebElement> categoryElementsList = visibilityOfAllElementsLocatedBy(categoryElements);
        for (WebElement categoryElement : categoryElementsList) {
            if (category.equalsIgnoreCase(categoryElement.getText().replaceAll("[^A-Za-z]", ""))) {
                categoryElement.click();
                break;
            }
        }
    }

    public String getRelevantPLPPage() {
        return getCurrentPageTitle();
    }

    public void clickOnCategoryMenuIndicator(String indicator) {
        if (LloydsPharmacyConstants.CATEGORY_COLLAPSIBLE_UP_ARROW.equals(indicator)) {
            getWebDriver().findElement(categoryUpInd).click();
        } else if (LloydsPharmacyConstants.CATEGORY_COLLAPSIBLE_DOWN_ARROW.equals(indicator)) {
            getWebDriver().findElement(categoryDownInd).click();
        }
    }

    public String getCategoryMenuVisibility() {
        return isElementPresent(categoryFacetList) ?
                LloydsPharmacyConstants.CATEGORY_COLLAPSIBLE_VISIBLE :
                LloydsPharmacyConstants.CATEGORY_COLLAPSIBLE_HIDDEN;
    }
}
