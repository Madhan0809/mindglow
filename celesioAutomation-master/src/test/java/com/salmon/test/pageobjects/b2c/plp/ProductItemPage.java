package com.salmon.test.pageobjects.b2c.plp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductItemPage {
    private By productName = By.cssSelector(".productList .product .productContent .productTitle");
    private By productPrice = By.cssSelector(".productList .product .productContent .priceDisplay input[type='hidden']");
    private WebElement product;

    public ProductItemPage(WebElement product) {
        this.product = product;
    }

    public String getProductName() {
        return product.findElement(productName).getText();
    }

    public Double getProductPrice() {
        String price = product.findElement(productPrice).getAttribute("value");
        return Double.valueOf(parseCurrency(price.trim()));
    }

    private String parseCurrency(String currency) {
        return currency.replaceAll("[^\\d.]+", "");
    }
}
