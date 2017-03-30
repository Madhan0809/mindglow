package com.salmon.test.stepdefinitions.b2b.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.pageobjects.b2b.register.B2BLoginPage;
import com.salmon.test.pageobjects.b2b.register.B2BSiteSelectionPage;
import static org.assertj.core.api.Assertions.assertThat;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class B2BSiteSelectionSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BLoginPage.class);
	
	public B2BSiteSelectionPage b2BSiteSelectionPage;

    public B2BSiteSelectionSteps(B2BSiteSelectionPage siteSelectionPage) {
        b2BSiteSelectionPage = siteSelectionPage;
    }
	
	@When("^User select entry point of \"(.*?)\"$")
    public void user_select_entry_point_from_login_landing_page(String website) throws Throwable{
		b2BSiteSelectionPage.chooseWebSiteFromEntryPoint(website);    
    }
	
	@When("^User click on \"(.*?)\" button in header in site selection page$")
	public void user_click_on_button_in_site_selection_page(String buttonName) throws Throwable{
		b2BSiteSelectionPage.clickButtonInHeader(buttonName);
	}
	
	@Then("^Page header of \"(.*?)\" is displayed$")
	public void check_page_header_is_displayed(String header) throws Throwable{
		assertThat(b2BSiteSelectionPage.getSiteSelectionPageHeader()).isEqualTo(header);
	}
	
	@Then("^Check if the logo image for AAH and Enterprise is displayed$")
	public void check_if_the_logo_image_for_aah_and_enterprise_is_displayed() throws Throwable{
	    assertThat(b2BSiteSelectionPage.isLogoImageDisplayed()).isTrue();
	}
	
}
