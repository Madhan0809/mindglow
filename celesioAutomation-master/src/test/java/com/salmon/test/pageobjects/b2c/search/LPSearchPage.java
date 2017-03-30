package com.salmon.test.pageobjects.b2c.search;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class LPSearchPage extends PageObject {
    private By productList = By.cssSelector(".productList .product");
    private By productImage = By.cssSelector(".productImage a");
    private By searchTextBox = By.cssSelector("input[name='searchTerm']");
    private By searchButton = By.cssSelector(".searchSubmitButton.iSearch");
    private By searchBreadcrumb = By.cssSelector("#widget_breadcrumb ul li:nth-child(2)");
    private By noResulMessage = By.cssSelector(".widget_search_results .results_description h1");
    private By searchMessage = By.cssSelector(".searchSummaryWidget .widget_title_container h1");
    private By nonExistName = By.cssSelector(".darker");
    private By headerLogo = By.cssSelector(".brandSearch>div>a");
    private By footerHeaderCustomerService = By.cssSelector(".collapsibleContainer>div:nth-child(1) .fLinksWrap>h5");
    private By footerHeaderShopWithUs = By.cssSelector(".collapsibleContainer>div:nth-child(2) .fLinksWrap>h5");
    private By footerHeaderOurServices = By.cssSelector(".collapsibleContainer>div:nth-child(3) .fLinksWrap>h5");
    private By productName = By.cssSelector(".main_header");
    private By productPrice = By.cssSelector("div.productPrice span");
    private By leftCategoryTitle = By.cssSelector(".collapsibleContainer h3");
//    private By sideBar = By.cssSelector(".sidebar.panel ul>li>a");
    private By sideBar=By.cssSelector(".facetCountContainer");
    private By dropdown = By.cssSelector("#plpContent .productListSort label");
    private By productTitle = By.cssSelector(".listItem .productTitle");
    private By filterTitle = By.cssSelector("#filterTitle");
    private By facetTitletList = By.cssSelector(".title");
    private By pmed = By.cssSelector("#productsFacets fieldset:nth-of-type(1) li:nth-of-type(2)>label");
    private By prodcutsSearchTabLabel = By.cssSelector("#plpContent .productCount");
    private By prescriptionSearchTabLabel = By.cssSelector("#plpPrescription .productCount");
    private By helpSearchTabLabel = By.cssSelector("#healthAndAdviceContent .productCount");
    private By searchTabs = By.cssSelector(".tab_container .left_espot");

    public void inputSearchText(String searchText) {
        WebElement searchTextBoxElement = waitForExpectedElement(searchTextBox,30);
        searchTextBoxElement.clear();
        searchTextBoxElement.sendKeys(searchText);
    }

    public void onClickofSearchButton() {
        getWebDriver().findElement(searchTextBox).click();
        getWebDriver().findElement(searchTextBox).sendKeys(Keys.ENTER);
    }

    public String getSearchBreadcrumb() {
        return waitForExpectedElement(searchBreadcrumb).getText();
    }

    public boolean isNoResultShown(String noResult) {
        return getWebDriver().findElement(noResulMessage).getText().contains(noResult);
    }

    public boolean isRelevantMessageShown(String message) {
        return waitForExpectedElement(searchMessage).getText().trim().matches(message);
    }

    public String getProductName() {
        return getWebDriver().findElement(productName).getText();
    }

    public String getProductPrice() {
        return getWebDriver().findElement(productPrice).getText();
    }

    public String getLeftCategoryTitle() {
        return getWebDriver().findElement(leftCategoryTitle).getText();
    }

    public List<String> getSideBarList() {
        return getWebDriver().findElements(sideBar).stream().filter(element->element.isDisplayed()).map(leftPanel -> leftPanel.getText().replaceAll("[^A-Za-z]", "")).collect(Collectors.toList());
    }

    public List<String> getDropDownList() {
        return getWebDriver().findElements(dropdown).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean isHeaderLogoShown() {
        return isElementPresent(headerLogo);
    }

    public boolean isFooterShown() {
        return isElementPresent(footerHeaderCustomerService) &&
                isElementPresent(footerHeaderShopWithUs) &&
                isElementPresent(footerHeaderOurServices);
    }

    public void clickOnProductImage(String product) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement compareElement : compareElementsList) {
            if (product.equalsIgnoreCase(compareElement.findElement(productTitle).getText())) {
                compareElement.findElement(productImage).click();
                break;
            }
        }
    }

    public void clickOnProductTitle(String product) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement compareElement : compareElementsList) {
            WebElement productTitleElement = compareElement.findElement(productTitle);
            if (product.equalsIgnoreCase(productTitleElement.getText())) {
                productTitleElement.click();
                break;
            }
        }
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList);
    }


    public List<String> getProductList() {
        return getWebDriver().findElements(productTitle).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getFilterTitle() {
        return getWebDriver().findElement(filterTitle).getText();
    }

    public List<String> getFacetTitle() {
        return getWebDriver().findElements(facetTitletList).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void onClickofFacet(String facet) {
        switch (facet) {
            case "PMED":
                getWebDriver().findElement(pmed).click();
                break;
        }
    }

    public void onClickoFTab(String tab) {
        List<WebElement> searchTabsElement = getWebDriver().findElements(searchTabs);
        for (WebElement searchTabElement : searchTabsElement) {
            if (searchTabElement.getText().contains(tab)) {
                searchTabElement.click();
                break;
            }
        }
    }

    public String getSearchResultLabel(String searchType) {
        String labelValue = "";
        switch (searchType) {
            case "Products":
                labelValue = waitForExpectedElement(prodcutsSearchTabLabel).getText();
                break;
            case "Prescriptions":
                labelValue = waitForExpectedElement(prescriptionSearchTabLabel).getText();
                break;
            case "Articles":
                labelValue = waitForExpectedElement(helpSearchTabLabel).getText();
                break;
        }
        return labelValue;
    }
}
