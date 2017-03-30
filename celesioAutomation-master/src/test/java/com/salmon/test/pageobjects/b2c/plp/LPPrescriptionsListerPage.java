package com.salmon.test.pageobjects.b2c.plp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LPPrescriptionsListerPage extends LPProductListerPage {
    private By productName = By.cssSelector(".productContent a");
    private By add = By.cssSelector(".btnEmptyPrice");
    private By addToExistingPrescription = By.cssSelector(".btnAction.addToExistingPrescription");
    private By productList = By.cssSelector(".productList .product");

    public void addPrescriptionItemToBasket(String productNameValue) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement product : compareElementsList) {
            if (product.findElement(productName).getText().equalsIgnoreCase(productNameValue)) {
                product.findElement(add).click();
                break;
            }
        }
    }

    public void addToExistingPrescription(String productNameValue) {
        List<WebElement> compareElementsList = getListOfProducts();
        for (WebElement product : compareElementsList) {
            if (product.findElement(productName).getText().equalsIgnoreCase(productNameValue)) {
                product.findElement(addToExistingPrescription).click();
                break;
            }
        }
    }

    private List<WebElement> getListOfProducts() {
        return visibilityOfAllElementsLocatedBy(productList);
    }
}
