package com.salmon.test.pageobjects.csr.find;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRFindCustomersPage extends PageObject {
    private By findFormHeader = By.cssSelector((".modal-content>h3"));
    private By closeForm = By.cssSelector(".modal-content a.close");
    private By logonID = By.cssSelector("input[name='logonId']");
    private By firstName = By.cssSelector("input[name='firstName']");
    private By lastName = By.cssSelector("input[name='lastName']");
    private By emailAddress = By.cssSelector("input[name='email']");
    private By postCode = By.cssSelector("input[name='zipCode']");
    private By findButton = By.cssSelector("input[type=submit]");
    private By searchResultsTable = By.cssSelector(".table>tbody>tr");
    private By noSearchResultsTable = By.cssSelector(".empty>td");
    private By allFormTextBox = By.cssSelector(".container>form .input-row input");

    public void enterSearchQuery(Map<String, String> fieldValueMap) {
        waitForExpectedElement(logonID).sendKeys(getCsrProp(fieldValueMap.get("logonID")));
        getWebDriver().findElement(firstName).sendKeys(getCsrProp(fieldValueMap.get("firstName")));
        getWebDriver().findElement(lastName).sendKeys(getCsrProp(fieldValueMap.get("lastName")));
        getWebDriver().findElement(emailAddress).sendKeys(getCsrProp(fieldValueMap.get("email")));
        getWebDriver().findElement(postCode).sendKeys(getCsrProp(fieldValueMap.get("postCode")));
        getWebDriver().findElement(findButton).click();
    }

    public void clearSearchFields() {
        List<WebElement> listOfFields = getWebDriver().findElements(allFormTextBox);
        listOfFields.forEach(WebElement::clear);
    }

    public boolean verifySearchResults(Map<String, String> searchResultsMap) {
        boolean searchResult = false;
        waitForElementToDisappear(noSearchResultsTable);
        List<WebElement> resultsList = visibilityOfAllElementsLocatedBy(searchResultsTable);
        for (WebElement rows : resultsList) {
            List<WebElement> rowCells = rows.findElements(By.cssSelector("td"));
            if (rowCells.get(1).getText().equalsIgnoreCase(getCsrProp(searchResultsMap.get("logonID")))) {
                verySearchResult(rowCells, searchResultsMap);
                searchResult = true;
                if (searchResultsMap.get("select").equalsIgnoreCase("true")) {
                    rowCells.get(5).findElement(By.tagName("button")).click();
                }
                break;
            }
        }
        return searchResult;
    }

    private void verySearchResult(List<WebElement> rowCells, Map<String, String> searchResultsMap) {
        assertThat(rowCells.get(0).getText()).isEqualToIgnoringCase(getCsrProp(searchResultsMap.get("name")));
        assertThat(rowCells.get(2).getText()).isEqualToIgnoringCase(getCsrProp(searchResultsMap.get("email")));
        assertThat(rowCells.get(3).getText()).isEqualToIgnoringCase(getCsrProp(searchResultsMap.get("telephone")));
        assertThat(rowCells.get(4).getText()).containsIgnoringCase(getCsrProp(searchResultsMap.get("address")));
    }
}