package com.salmon.test.stepdefinitions.b2c.prescriptions;
import com.salmon.test.pageobjects.b2c.prescriptions.LPPrescriptionsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LpPrescriptionSteps {
    private final LPPrescriptionsPage lpPrescriptionsPage;

    public LpPrescriptionSteps(LPPrescriptionsPage lpPrescriptionsPage) {
        this.lpPrescriptionsPage = lpPrescriptionsPage;
    }

    @And("^The user selects prescription frequency \"([^\"]*)\"$")
    public void The_user_selects_prescription_frequency(String frequency) throws Throwable {
        lpPrescriptionsPage.selectPrescriptionType(frequency);
    }

    @And("^The user selects prescription payment Type \"([^\"]*)\"$")
    public void The_user_selects_prescription_payment_Type(String paymentType) throws Throwable {
        lpPrescriptionsPage.selectNHSPrescriptionPayType(paymentType);
    }

    @And("^The user selects payment exempt reason \"([^\"]*)\"$")
    public void The_user_selects_payment_exempt_reason(String exemptReason) throws Throwable {
        lpPrescriptionsPage.selectPaymentExemptionReason(exemptReason);
    }

    @Then("^I confirm signed prescription terms and submit$")
    public void I_confirm_signed_prescription_terms_and_submit() throws Throwable {
        lpPrescriptionsPage.confirmSignedPrescriptionAndTerms();
    }

    @Then("^I confirm signed prescription$")
    public void I_confirm_signed_prescription() throws Throwable {
        lpPrescriptionsPage.confirmSignedPrescriptionAndTerms();
//        lpPrescriptionsPage.confirmSignedPrescription();
    }

    @When("^The user selects for repeat prescription patient name as \"([^\"]*)\"$")
    public void The_user_selects_for_repeat_prescription_patient_name_as(String patientNameType) throws Throwable {
        lpPrescriptionsPage.selectPatientName(patientNameType);
    }
}
