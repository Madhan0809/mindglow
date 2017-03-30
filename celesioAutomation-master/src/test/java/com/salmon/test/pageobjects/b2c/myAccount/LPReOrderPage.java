package com.salmon.test.pageobjects.b2c.myAccount;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2c.Basket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPReOrderPage extends PageObject {
    private By reOrderSelect = By.cssSelector(".reorderSelect");
    private By itemName = By.cssSelector(".itemDesc .itemspecs a");
    private By inStock = By.cssSelector(".inStock .left_espot");
    private By itemQuantity = By.cssSelector(".qtyInput");
    private By eachItemPrice = By.cssSelector(".itemUnitPrice>strong:not(.m-show)");
    private By totalPrice = By.cssSelector(".totalItemPrice>strong");
    private By orderSubTotal = By.cssSelector(".totalOrderPrice");
    private By reOrderButton = By.cssSelector("#reOrder");

    public void verifyItemDetailsToOrder(Basket orderItemDetails) {
        assertThat(waitForExpectedElement(itemName).getText()).isEqualToIgnoringCase(orderItemDetails.getItemName());
        assertThat(getWebDriver().findElement(inStock).getText()).isEqualToIgnoringCase(orderItemDetails.getInStock());
        assertThat(getWebDriver().findElement(itemQuantity).getAttribute("value")).isEqualTo(orderItemDetails.getItemQuantity());
        assertThat(getWebDriver().findElement(eachItemPrice).getText()).isEqualToIgnoringCase(orderItemDetails.getEachItemPrice());
        assertThat(getWebDriver().findElement(totalPrice).getText()).isEqualToIgnoringCase(orderItemDetails.getTotalPrice());
        assertThat(getWebDriver().findElement(orderSubTotal).getText()).isEqualToIgnoringCase(orderItemDetails.getOrderSubTotal());
    }

    public void selectItemToReOrder() {
        waitForExpectedElement(reOrderSelect).click();
    }

    public void addToBasket() {
        waitForExpectedElement(reOrderButton).click();
    }

    public void updateQuantity(String quantity) {
        getWebDriver().findElement(itemQuantity).clear();
        getWebDriver().findElement(itemQuantity).sendKeys(quantity);
    }

    public void clickOnOrderNo(String orderNo) {
        List<WebElement> orderTableRows = visibilityOfAllElementsLocatedBy(By.cssSelector("td a.btnAction"));
        for (WebElement orderTableRow : orderTableRows) {
            if ((orderTableRow.getAttribute("href").contains("orderId=" + orderNo))) ;
            orderTableRow.click();
            break;
        }
    }
}