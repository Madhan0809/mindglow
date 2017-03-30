package com.salmon.test.pageobjects.csr.find;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindReturnsPage extends PageObject {
    private By findFormHeader = By.cssSelector((".modal-content>h3"));
    private By closeForm = By.cssSelector(".modal-content a.close");
    private By orderNo = By.cssSelector("input[name='orderId']");
    private By logonID = By.cssSelector("input[name='loginId']");
    private By returnID = By.cssSelector("input[name='returnId']");
    private By findButton = By.cssSelector("input[type=submit]");
    private By searchResultsTable = By.cssSelector(".table.paginated>tbody>tr");

    public void enterSearchQuery(Map<String, String> fieldValueMap) {
        waitForExpectedElement(returnID).sendKeys(fieldValueMap.get("returnID"));
        getWebDriver().findElement(logonID).sendKeys(fieldValueMap.get("logonID"));
        getWebDriver().findElement(orderNo).sendKeys(fieldValueMap.get("orderNo"));
        getWebDriver().findElement(findButton).click();
    }

    public void clearSearchFields() {
        waitForExpectedElement(returnID).clear();
        getWebDriver().findElement(orderNo).clear();
        getWebDriver().findElement(logonID).clear();
    }

    public boolean verifySearchResults(Map<String, String> searchResultsMap) {
        boolean searchResult = false;
        waitForExpectedElement(searchResultsTable);
        List<WebElement> resultsList = getWebDriver().findElements(searchResultsTable);
        for (WebElement rows : resultsList) {
            List<WebElement> rowCells = rows.findElements(By.cssSelector("td"));
            if (rowCells.get(1).getText().equalsIgnoreCase(searchResultsMap.get("returnID"))) {
                verySearchResult(rowCells, searchResultsMap);
                searchResult = true;
                break;
            }
        }
        return searchResult;
    }

    private void verySearchResult(List<WebElement> rowCells, Map<String, String> searchResultsMap) {
        assertThat(rowCells.get(1).getText()).isEqualToIgnoringCase(searchResultsMap.get("returnID"));
        assertThat(rowCells.get(2).getText()).isEqualToIgnoringCase(searchResultsMap.get("orderNo"));
        assertThat(rowCells.get(3).getText()).isEqualToIgnoringCase(searchResultsMap.get("logonID"));
        assertThat(rowCells.get(4).getText()).containsIgnoringCase(searchResultsMap.get("returnStatus"));
        assertThat(rowCells.get(5).getText()).containsIgnoringCase(searchResultsMap.get("refundTotal"));
    }
}
