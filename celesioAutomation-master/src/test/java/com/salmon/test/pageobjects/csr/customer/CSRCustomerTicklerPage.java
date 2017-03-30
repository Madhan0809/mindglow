package com.salmon.test.pageobjects.csr.customer;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CSRCustomerTicklerPage extends PageObject {
    private By addNewTicklerButton = By.cssSelector(".form-intro button");

    public WebElement addNewTicklerButton() {
        return getWebDriver().findElement(addNewTicklerButton);
    }
}
