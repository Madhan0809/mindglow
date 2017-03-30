package com.salmon.test.pageobjects.csr.find;
import com.salmon.test.framework.PageObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindCommonPage extends PageObject {
    private By findFormHeader = By.cssSelector((".modal-content>h3"));
    private By closeForm = By.cssSelector(".modal-content a.close");
    private By clearResultsButton = By.cssSelector(".form-controls button.clear");
    private By noSearchResultsTable = By.cssSelector(".empty>td");
    private By formValidationsList = By.cssSelector(".error-list>li>p");
    private By matchingCustomersCount = By.cssSelector(".matches .count");
    private By matchingCustomersLabel = By.cssSelector(".matches h3");
    private By noOfPages = By.cssSelector(".paginator-buttons li[data-page]");
    @Getter
    private By nextPage = By.cssSelector(".next>a");
    @Getter
    private By previousPage = By.cssSelector(".prev>a");

    public String getFindFormHeader() {
        return waitForExpectedElement(findFormHeader).getText();
    }

    public void clearSearchResultsTable() {
        waitForExpectedElement(clearResultsButton).click();
    }

    public String checkSearchResults() {
        return waitForExpectedElement(noSearchResultsTable).getText();
    }

    public Integer getMatchCount() {
        return Integer.parseInt(waitForExpectedElement(matchingCustomersCount).getText());
    }

    public String matchLabel() {
        return waitForExpectedElement(matchingCustomersLabel).getText();
    }

    public List<WebElement> noOfPageElements() {
        return getWebDriver().findElements(noOfPages);
    }

    public boolean isPageActiveForNextPageNavigation() {
        boolean isActive = false;
        for (int i = 0; i < noOfPageElements().size(); i++) {
            assertThat(noOfPageElements().get(i).getAttribute("class")).isEqualToIgnoringCase("active");
            if (i < noOfPageElements().size() - 1) {
                waitForExpectedElement(nextPage).click();
            }
            isActive = true;
        }
        return isActive;
    }

    public boolean isPageActiveForPreviousPageNavigation() {
        boolean isActive = false;
        for (int i = noOfPageElements().size(); i > 0; i--) {
            assertThat(noOfPageElements().get(i - 1).getAttribute("class")).isEqualToIgnoringCase("active");
            if (i > 1) {
                waitForExpectedElement(previousPage).click();
            }
            isActive = true;
        }
        return isActive;
    }

    public Integer calculateExpectedPageCount(Double paginateBy) {
        Integer count = getMatchCount();
        return (int) Math.ceil(count / paginateBy);
    }

    public List<WebElement> checkValidations() {
        return visibilityOfAllElementsLocatedBy(formValidationsList);
    }
}