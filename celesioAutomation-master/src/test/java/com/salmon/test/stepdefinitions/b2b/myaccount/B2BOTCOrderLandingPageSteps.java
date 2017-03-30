package com.salmon.test.stepdefinitions.b2b.myaccount;
        import com.salmon.test.pageobjects.b2b.myaccount.B2BOTCOrderLandingPage;
        import cucumber.api.PendingException;
        import cucumber.api.java.en.And;

        import java.util.List;

        import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by aettukullapati on 19/01/2016.
 */
public class B2BOTCOrderLandingPageSteps {

    private B2BOTCOrderLandingPage b2BOTCOrderLandingPage;

    public B2BOTCOrderLandingPageSteps(B2BOTCOrderLandingPage b2BOTCOrderLandingPage) {
        this.b2BOTCOrderLandingPage = b2BOTCOrderLandingPage;
    }

    @And("^verify the Your Profile headers are displayed$")
    public void verify_the_Your_Profile_headers_are_displayed(List<String> profileHeaderList) throws Throwable {
        assertThat(b2BOTCOrderLandingPage.getYourProfileHeaderList()).isEqualTo(profileHeaderList);
    }

    @And("^verify profile links are displayed$")
    public void verify_profile_links_are_displayed(List<String> profileLinksList) throws Throwable {
        assertThat(b2BOTCOrderLandingPage.getYourProfileLinksList()).isEqualTo(profileLinksList);
    }

    @And("^User clicks on \"([^\"]*)\" selection$")
    public void User_clicks_on_selection(String sectionName) throws Throwable {
        b2BOTCOrderLandingPage.clickOnSection(sectionName);
    }
}
