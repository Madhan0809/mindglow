package com.salmon.test.models.b2b;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PLPProductItems {
    private By productName = By.cssSelector(".product_listing_container >ul li .product_info .product_name");
    private By productPrice = By.cssSelector("dl.priceGuide.row dd:nth-of-type(2)");
    private By priceRow=By.cssSelector(".priceGuide.row");
    private WebElement product;

    public PLPProductItems(WebElement product) {
        this.product = product;
    }

    public String getProductName() {
        return product.findElement(productName).getText();
    }

    public Double getProductPrice() {
        if(product.findElement(priceRow).getText().isEmpty()){
            return Double.valueOf("0");
        }
        String price = product.findElement(productPrice).getText();
        if (price == null || price.equals("")) {
            return Double.valueOf("0");
        }
        return Double.valueOf(parseCurrency(price.trim()));
    }

    private String parseCurrency(String currency) {
        return currency.replaceAll("[^\\d.]+", "");
    }
}
