package com.salmon.test.stepdefinitions.b2c.prescriptions;
import com.salmon.test.pageobjects.b2c.prescriptions.LPRepeatPrescriptionsPage;
import cucumber.api.java.en.And;

public class LPPrescriptionRegistrationSteps {
    private LPRepeatPrescriptionsPage lpRepeatPrescriptionsPage;

    public LPPrescriptionRegistrationSteps(LPRepeatPrescriptionsPage lpRepeatPrescriptionsPage) {
        this.lpRepeatPrescriptionsPage = lpRepeatPrescriptionsPage;
    }

    @And("^the user enters new prescription details for \"([^\"]*)\"$")
    public void the_user_enters_new_prescription_details_for(String representativeORPatient) throws Throwable {
        lpRepeatPrescriptionsPage.enterDrDetails();
        lpRepeatPrescriptionsPage.selectPatientOrRepresentative(representativeORPatient);
        lpRepeatPrescriptionsPage.enterPatientORRepresentativeDetails();
        lpRepeatPrescriptionsPage.selectCheckBoxesAndClickContinue();
    }
}
