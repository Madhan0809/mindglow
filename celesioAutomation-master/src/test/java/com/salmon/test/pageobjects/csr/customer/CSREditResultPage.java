package com.salmon.test.pageobjects.csr.customer;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CSREditResultPage extends PageObject {
    private By submitButton = By.cssSelector("input[type=submit]");

    public WebElement getResultTab(String tabName) {
        return getWebDriver().findElement(By.cssSelector(".tabs li[data-name=" + tabName + " ]>a"));
    }

    public WebElement getElementByFieldName(String elementName) {
        return getWebDriver().findElement(By.cssSelector("input[name=" + elementName + "]"));
    }

    public Select selectElementByFieldName(String elementName) {
        return new Select(getWebDriver().findElement(By.cssSelector("select[name=" + elementName + "]")));
    }

    public WebElement submitButton() {
        return getWebDriver().findElement(submitButton);
    }
}
