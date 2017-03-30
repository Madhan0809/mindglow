package com.salmon.test.models.b2b;
import lombok.Getter;

/**
 * Created by aettukullapati on 08/12/2015.
 */
@Getter
public class MyBasketDetails {

        private String basketName;
        private String dateSent;
        private String deliveryPoint;
        private String orderNumber;
        private String orderReference;
        private String account;
        private String deliveryDate;
        private String deliveryDetails;
        private String dateCreated;
        private String totalOrderLines;
        private String sendOrder;
        //For orders to approved page
        private String suplierLocalCode;
        private String orderValue;


}
