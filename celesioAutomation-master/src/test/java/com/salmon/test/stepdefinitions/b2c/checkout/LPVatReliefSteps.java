package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.pageobjects.b2c.checkout.LPVatReliefPage;
import cucumber.api.java.en.Then;

public class LPVatReliefSteps {
    private LPVatReliefPage lpVatReliefPage;

    public LPVatReliefSteps(LPVatReliefPage lpVatReliefPage) {
        this.lpVatReliefPage = lpVatReliefPage;
    }

    @Then("^the VatRelief option is selected \"([^\"]*)\"$")
    public void the_VatRelief_option_is_selected(boolean vatReliefFlag) throws Throwable {
        lpVatReliefPage.selectVatReliefAndChooseARandomReason(vatReliefFlag);
    }

    @Then("^the VatRelief option is selected \"([^\"]*)\" for option \"([^\"]*)\"$")
    public void the_VatRelief_option_is_selected_for_option(boolean vatReliefFlag, String vatReilefFor) throws Throwable {
        if (!vatReilefFor.equalsIgnoreCase("NA")) {
            lpVatReliefPage.selectVatReliefAndChooseARandomReason(vatReliefFlag, vatReilefFor);
        }

    }
}

