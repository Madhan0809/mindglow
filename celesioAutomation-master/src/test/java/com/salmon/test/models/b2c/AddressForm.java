/**
 *
 */
package com.salmon.test.models.b2c;
import lombok.Getter;

@Getter
public class AddressForm {
    private String title;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String chooseAddress;
    private String preferredDeliveryMethod;
    private String deliveryInstructions;
    private String postCode;
    //For edit delivery address
    private String nickName;
    private String zipCode;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;

    private String birthDay;
    private String birthMonth;
    private String birthYear;

    private String mainPhoneNo;
    private String AltPhoneNo;

}
