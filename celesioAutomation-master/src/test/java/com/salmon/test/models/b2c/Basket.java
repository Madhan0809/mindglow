package com.salmon.test.models.b2c;
import lombok.Getter;

@Getter
public class Basket {
    private String noOfBasketItems;
    private String totalBasketPrice;
    private String itemName;
    private String itemQuantity;
    private String eachItemPrice;
    private String totalPrice;
    private String deliveryOptions;
    private String orderSubTotal;
    //    private String vatRelief;
    private String discount;
    private String orderTotal;
    private String miniBasket;
    private String inStock;
}
