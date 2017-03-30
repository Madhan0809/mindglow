package com.salmon.test.stepdefinitions.b2b.register;
import com.salmon.test.pageobjects.b2b.register.B2BRegisterPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class B2BRegisterSteps {
    private static final Logger LOG = LoggerFactory.getLogger(B2BRegisterSteps.class);
    public B2BRegisterPage b2BRegisterPage;

    public B2BRegisterSteps(B2BRegisterPage registerPage) {
        this.b2BRegisterPage = registerPage;
    }
//	@When("The user clicks on \"(.*?)\" button in homepage")
//	public void the_user_click_on_button_in_homepage(String btnName) throws Throwable{
//		b2BRegisterPage.clickButtonFromHompage(btnName);
//	}
//	@Then("The register page should be displayed")
//	public void the_register_page_should_be_displayed() throws Throwable {
//		 assertThat(b2BRegisterPage.canAccessRegisterPage()).isTrue();
//	}
//	
//	@And("Input user information in register page")
//	public void input_user_information_in_register_page(@Transpose Map<String, String>addressForm) throws Throwable{
//		//TODO
//	}
//	@When("When User click \"(.*?)\" button in register page")
//	public void user_click_button_in_register_page(String buttonName) throws Throwable{
//		//TODO
//	}
}
