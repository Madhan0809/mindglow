package com.salmon.test.models.b2b;
import com.salmon.test.models.b2c.Registration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class B2bAccountData {
    private String orderNo;
    private String wishList;
    private RegistrationDetails registration;
}
