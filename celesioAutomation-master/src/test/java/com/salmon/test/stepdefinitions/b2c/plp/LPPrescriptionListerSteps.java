package com.salmon.test.stepdefinitions.b2c.plp;
import com.salmon.test.pageobjects.b2c.plp.LPPrescriptionsListerPage;
import cucumber.api.java.en.When;

public class LPPrescriptionListerSteps {
    private LPPrescriptionsListerPage lpPrescriptionsListerPage;

    public LPPrescriptionListerSteps(LPPrescriptionsListerPage lpPrescriptionsListerPage) {
        this.lpPrescriptionsListerPage = lpPrescriptionsListerPage;
    }

    @When("^The user clicks \"([^\"]*)\" for \"([^\"]*)\" on the Prescriptions PLP$")
    public void The_user_clicks_for_on_the_Prescriptions_PLP(String addButtonType, String productName
    ) throws Throwable {
        if (addButtonType.equalsIgnoreCase("add")) {
            lpPrescriptionsListerPage.addPrescriptionItemToBasket(productName);
        } else {
            lpPrescriptionsListerPage.addToExistingPrescription(productName);
        }
    }


}
