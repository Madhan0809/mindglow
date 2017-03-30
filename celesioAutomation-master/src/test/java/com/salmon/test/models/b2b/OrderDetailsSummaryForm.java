package com.salmon.test.models.b2b;
import lombok.Getter;

@Getter
public class OrderDetailsSummaryForm {
	 private String supplier;
	 private String orderMethod;
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
	 private String basketName;
	 private String sendOrderOn;
	 private String total;
}
