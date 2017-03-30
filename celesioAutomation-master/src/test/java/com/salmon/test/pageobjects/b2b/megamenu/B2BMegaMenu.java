package com.salmon.test.pageobjects.b2b.megamenu;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class B2BMegaMenu extends PageObject {

    public static final int FASTENER_INDEX = 0;
    private static final Logger LOG = LoggerFactory.getLogger(B2BMegaMenu.class);
    Actions action;
    private By shopByCategory = By.cssSelector("#categoryNavLink>a>span");
    private By departmentElements = By.cssSelector("ul #categoriesColumn li a span");
    private By categoryElements = By.cssSelector(".categoryContentPanel div:nth-of-type(1) ul li span");
    private  By subCategoryElements = By.cssSelector(".categoryContentPanel div:nth-of-type(1) ul li span");
    private By departmentViewLink = By.cssSelector(".categoryContent.equaliseColumn.show .viewAll>span");

    private By selectedHoverSubCategories=By.cssSelector(".categoryContentPanel .navContent.equaliseColumn.show li");

    public void goToB2BHomePage() {
        LOG.info("Navigating to AAH Enterprise HomePage \n");
        getWebDriver().manage().deleteAllCookies();
        UrlBuilder.startB2BHomePage();

    }

    public void onHoverOfMainMenu(String menuItem) {
        action = new Actions(getWebDriver());
        LOG.info("\n onHoverOfMainMenu :: " + getWebDriver().findElement(shopByCategory).getText());
        if (menuItem.toLowerCase().trim().equalsIgnoreCase(getWebDriver().findElement(shopByCategory).getText().toLowerCase().trim())) {
            action.moveToElement(waitForExpectedElement(shopByCategory)).build().perform();
        }

    }

    public List<String> getDepartmentListForShopByCategoryMenu() {
        return visibilityOfAllElementsLocatedBy(departmentElements, 20).parallelStream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void onHoverOfDepartment(String department) {
        action = new Actions(getWebDriver());

        List<WebElement> departmentElementsList =  visibilityOfAllElementsLocatedBy(departmentElements , 20);
          if(department.equalsIgnoreCase("Test Category") ){
              department="Great Offers";
          }
        for (WebElement element : departmentElementsList) {
            if (element.getText().equalsIgnoreCase(department)) {
                action.moveToElement(element).build().perform();
                break;
            }/*else if(element.getText().equalsIgnoreCase(department)){
                action.moveToElement(element).build().perform();
                break;
            }*/
        }
       /* if(!departmentElementsList.isEmpty()) {
            action.moveToElement(departmentElementsList.get(LloydsPharmacyConstants.DEPARTMENT_INDEX)).build().perform();
        }*/
    }


    public void onClickOfDepartment() {
        action = new Actions(getWebDriver());
        List<WebElement> deptElementsList = visibilityOfAllElementsLocatedBy(departmentElements);
        if (!deptElementsList.isEmpty()) {
            action.moveToElement(deptElementsList.get(FASTENER_INDEX)).click().perform();
        }
    }


    public List<String> getCategoryList(String departmentCatgeroy) {
        List<String> categoryList = new ArrayList<String>();
        if(departmentCatgeroy.equalsIgnoreCase("Great Offers")) {
            categoryList = visibilityOfAllElementsLocatedBy(categoryElements,20).stream().map(WebElement::getText).collect(Collectors.toList());
        }else if(departmentCatgeroy.equalsIgnoreCase("Test Category")){
            categoryList = visibilityOfAllElementsLocatedBy(subCategoryElements,20).stream().map(WebElement::getText).collect(Collectors.toList());
        }else if(departmentCatgeroy.equalsIgnoreCase("Baby & Children")){
            categoryList = this.visibilityOfAllElementsLocatedBy(selectedHoverSubCategories).stream().map(WebElement::getText).collect(Collectors.toList());
        }
        return categoryList;
    }

    public String getViewAllLinkForCategoryContent() {
        return getWebDriver().findElement(departmentViewLink).getText();
    }


    public boolean isRespectiveDepartmentPage(String departmentPageTitle) {
        LOG.info("window title :::: --> " + getCurrentPageTitle());
        return checkPageTitleContains(departmentPageTitle);
    }

    public void onClickOfViewAllForCategory() {
        getWebDriver().findElement(departmentViewLink).click();
    }

 /*   *//**
     * demo purpose only :
     * steps executes in slow motion by calling this method in each step.
     * * *//*
    private void sleep(){
    try {
          Thread.sleep(3000L);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }*/
}