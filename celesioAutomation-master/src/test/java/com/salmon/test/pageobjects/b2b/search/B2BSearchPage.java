package com.salmon.test.pageobjects.b2b.search;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class B2BSearchPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(B2BSearchPage.class);

    private By searchButton = By.cssSelector("a#searchSubmit");
    private By searchBreadCrumb = By.cssSelector("#widget_breadcrumb>ul li:nth-child(2)");
    private By pageHeaderLogo  =  By.cssSelector(".col-4.m-col-12.panel>a>img");
    private By footerHeaders = By.cssSelector(".contentRecommendationWidget .row:nth-of-type(1)  ul>li:nth-child(1)");
    private By footerMenus = By.cssSelector(".content.collapsibleTarget.active>li");
    private By socialMediaLinks = By.cssSelector("#social li a");
    private By searchResultHeadingText = By.cssSelector(".widget_title_container h1");
    private By noResultSuggestionSection = By.cssSelector(".noResultsSearchFormMessage");
    private By searchTermNoResultInputText = By.cssSelector("#simpleSearchForm_SearchTerm_NoResults");
    private By searchAgainButton = By.xpath(".//*[@id='noResultsSearchBox']/input[13]");
    private By noResultHeadingText = By.cssSelector(".results_description>h2");
    private By srpProductImage = By.cssSelector(".product_image.col-2.m-col-12 img");
    private By srpProductName = By.cssSelector(".product_name>a");
    private By productList = By.cssSelector(".product_listing_container li .product.row");
    private By productListInfo = By.cssSelector(".product_listing_container >ul li .product_info");
    private By productNameList = By.cssSelector("");
    private By leftCategoryTitle = By.cssSelector(".collapsibleContainer h3");
    private By searchMessage = By.cssSelector(".searchSummaryWidget .widget_title_container h1");
    private By sideBar = By.cssSelector(".widget_left_nav.facetWidget.collapsible.collapsibleContainer .facetSelect li a");
    private By searchBox = By.cssSelector(".icon-search");




  public void clickOnSearchButton(){
//      Actions action = new Actions(getWebDriver());
//      action.click(waitForExpectedElement(searchButton)).build().perform();
      waitForExpectedElement(searchButton).click();
      // When Search Button click is not working
//      waitForExpectedElement(searchBox).sendKeys(Keys.ENTER);


  }

    public boolean isFooterShown() {
        return isElementPresent(footerHeaders) &&
                isElementPresent(footerMenus) &&
                isElementPresent(socialMediaLinks);
    }

    public boolean isHeaderLogoShown() {
        return isElementPresent(pageHeaderLogo);
    }

    public String getSearchBreadcrumb() {

        return waitForExpectedElement(searchBreadCrumb).getText();
    }

    public boolean getSearchResultHeadingText(){
        return isElementPresent(searchResultHeadingText);
    }

    public boolean isNoResultShown(String noResult) {
        String noResultText = waitForExpectedElement(noResultHeadingText).getText();
          String trimNoResult = noResultText.replaceAll("\\s*[\\r\\n]+\\s*", "").trim();
        LOG.info("\n cleaned ::->"+trimNoResult);
        return trimNoResult.toLowerCase().contains(noResult.toLowerCase().trim());
    }

    public boolean isSearchTermShowedInTextBox(String noResult) {
        return waitForExpectedElement(searchTermNoResultInputText).getAttribute("value").contains(noResult);

    }

    public boolean isSearchButtonDisplayed(){
        return isElementPresent(searchAgainButton);
    }

    public boolean isSuggestionSectionDisplayed(){
        return isElementPresent(noResultSuggestionSection);
    }

	public void clickOnProductImage(String product) {
		List<WebElement> compareElementsList = getListOfProducts();
		for (WebElement compareElement : compareElementsList) {
			if (product.equals(compareElement.findElement(srpProductName).getText())) {
				compareElement.findElement(srpProductImage).click();
				break;
			}
		}
	}

    public void clickOnProductTitle(String product) {
        //List<WebElement> compareElementsList = getListOfProducts();
        List<WebElement> compareElementsList = visibilityOfAllElementsLocatedBy(productListInfo, 20);
        for (WebElement compareElement : compareElementsList) {
            WebElement productTitleElement = compareElement.findElement(srpProductName);
            if (productTitleElement.getText().equalsIgnoreCase(product)) {
                LOG.info("\n Product Name : : --> "+productTitleElement.getText() );
                productTitleElement.click();
                break;
            }
        }
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList, 30);
    }

    public boolean isRelevantMessageShown() {
        boolean flag=false;
        LOG.info("\n message::::" + waitForExpectedElement(searchMessage).getText().replaceAll("\\s*[\\r\\n]+\\s*", "").trim());
        if(!waitForExpectedElement(searchMessage).getText().trim().isEmpty()){
            flag=true;
        }
        return flag;
    }
    public String getLeftCategoryTitle() {
        return waitForExpectedElement(leftCategoryTitle).getText();
    }

    public List<String> getSideBarList() {
        return getWebDriver().findElements(sideBar).stream().map(leftPanel -> leftPanel.getText().replaceAll("[^A-Za-z]", "")).collect(Collectors.toList());
    }

}
