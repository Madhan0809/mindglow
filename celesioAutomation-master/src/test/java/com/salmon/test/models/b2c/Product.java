package com.salmon.test.models.b2c;
import lombok.Data;

@Data
public class Product {
    String productName;
    String userRating;
    String price;
    String wasPrice;
    String savePrice;
    String stock;
    String quantity;
    boolean productImage;
    String priceWithVATRelief;
    String variant;
    boolean select;
}
