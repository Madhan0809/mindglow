package com.salmon.test.models.b2c;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Registration {
    private String username;
    private String email;
    private String password;
    private String showPassword;
    private String title;
    private String firstName;
    private String lastName;
    private String postcode;
    private String streetAddress1;
    private String streetAddress2;
    private String townOrCity;
    private String county;
    private String country;
    private String preferredContact;
    private String contactNumber;
    private String dateOfBirth;
    private String gender;
    private String preferMail;
    private String preferPhone;
    private String preferEmail;
    private String preferSMS;
    private String termsAccept;
    private boolean generateRandomData;
    private boolean existingUser;
    private String postCodeFinder;
    private String addressType;
}



