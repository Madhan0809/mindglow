package com.salmon.test.pageobjects.csr.customer;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CSRAddNewTicklerPage extends PageObject {
    private By addNewTicklerButton = By.cssSelector(".form-intro button");

    public WebElement addNewTicklerButton() {
        return getWebDriver().findElement(addNewTicklerButton);
    }

    public Select selectElementByFieldName(String elementName) {
        return new Select(getWebDriver().findElement(By.cssSelector("select[name=" + elementName + "]")));
    }
}
