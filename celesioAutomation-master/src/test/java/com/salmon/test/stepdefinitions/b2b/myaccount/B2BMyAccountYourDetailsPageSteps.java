package com.salmon.test.stepdefinitions.b2b.myaccount;
import com.salmon.test.pageobjects.b2b.myaccount.B2BMyAccountYourDetailsPage;

import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aettukullapati on 11/01/2016.
 */
public class B2BMyAccountYourDetailsPageSteps {

    private static final Logger LOG = LoggerFactory.getLogger(B2BMyAccountYourDetailsPageSteps.class);

        private B2BMyAccountYourDetailsPage b2bMyAccountYourDetailsPage;

    public B2BMyAccountYourDetailsPageSteps(B2BMyAccountYourDetailsPage b2bMyAccountYourDetailsPage) {
        this.b2bMyAccountYourDetailsPage = b2bMyAccountYourDetailsPage;
    }
	
	@When("^User enter Your Details page after login$")
	public void user_enter_your_details_page_after_login() throws Throwable{
		b2bMyAccountYourDetailsPage.clickYourDetailsPageView();
	}
    
    @Then("^The header \"(.*?)\" is shown on Your Details page$")
    public void the_header_is_shown_on_Your_Details_page(String headerText) throws Throwable {
		 assertThat(b2bMyAccountYourDetailsPage.getHeaderText()).isEqualTo(headerText);
    }
    
    @Then("^Mandatory lable is displayed on Your Details page$")
    public void Mandatory_lable_is_displayed_on_Your_Details_page() throws Throwable {
		 assertThat(b2bMyAccountYourDetailsPage.isDisplayMandatory()).isTrue();
    }

    @Then("^Section lables are displayed on Your Details page$")
    public void section_labels_are_displayed_on_Your_Details_page(List<String> sectionList) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getLabelList()).isEqualTo(sectionList);
    }
    
    @Then("^Information labels are displayed on Your Details page$")
    public void information_labels_are_displayed_on_Your_Details_page(List<String> labelList) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getLabelList1()).isEqualTo(labelList);
    }
    
    @Then("^Change password labels are displayed on Your Details page$")
    public void change_password_labels_are_displayed_on_Your_Details_page(List<String> pwdLabelList) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getLabelList2()).isEqualTo(pwdLabelList);
    }
    
    @Then("^\"(.*?)\" label is displayed on Your Details page$")
    public void label_is_displayed_on_Your_Details_page(String permissions) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getPermissionsLabel()).isEqualTo(permissions);
    }
    
    @Then("^First Name is shown as valid value on Your Details page$")
    public void First_Name_is_on_Your_Details_page() throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getFirstName()).isNotEmpty();
    }

    @Then("^Last Name is shown as valid value on Your Details page$")
    public void Last_Name_is_on_Your_Details_page() throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getLastName()).isNotEmpty();
    }

    @Then("^Email is shown as valid value on Your Details page$")
    public void Email_is_on_Your_Details_page() throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getEmail()).isNotEmpty();
    }

    @Then("^Primary contact number is shown as valid value on Your Details page$")
    public void Primary_contact_number_is_on_Your_Details_page() throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getPrimaryNumber()).isNotEmpty();
    }
    
    @Then("^\"(.*?)\" title is displayed on Your Details page$")
    public void title_is_displayed_on_Your_Details_page(String marketingPreferTitle) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getMarketingPreferTitle()).isEqualTo(marketingPreferTitle);
    }

    @Then("^Preferences Options are is displayed on Your Details page$")
    public void Preferences_Options_are_is_displayed_on_Your_Details_page(List<String> optionList) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getOptionList()).isEqualTo(optionList);
    }

	@Then("^Account Table Titles are displayed on Your Details page$")
	public void account_Table_Titles_are_displayed_on_Your_Details_page(List<String> accountTableTitleList) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getAccountTableTitleList()).isEqualTo(accountTableTitleList);
	}
	
	@When("^User click \"(.*?)\" on Your Details page$")
	public void user_click_on_Your_Details_page(String buttonName) throws Throwable {
		b2bMyAccountYourDetailsPage.clickButtonOnYourDetails(buttonName);
	}
	
	@When("^Update First Name as \"(.*?)\" on Your Details page$")
	public void update_First_Name_as_on_Your_Details_page(String firstName) throws Throwable {
		b2bMyAccountYourDetailsPage.updateFirstName(firstName);
	}

	@When("^Update Last Name as \"(.*?)\" on Your Details page$")
	public void update_Last_Name_as_on_Your_Details_page(String lastName) throws Throwable {
		b2bMyAccountYourDetailsPage.updateLastName(lastName);
	}

	@When("^Update Email as \"(.*?)\" on Your Details page$")
	public void update_Email_as_on_Your_Details_page(String email) throws Throwable {
		b2bMyAccountYourDetailsPage.updateEmail(email);
	}

	@When("^Update Primary contact number as \"(.*?)\" on Your Details page$")
	public void update_Primary_contact_number_as_on_Your_Details_page(String contactNo) throws Throwable {
		b2bMyAccountYourDetailsPage.updateContactNo(contactNo);
	}
    
	@Then("^Error messages are displayed on Your Details page$")
	public void error_messages_are_displayed_on_Your_Details_page(List<String> errorMsg) throws Throwable {
    	assertThat(b2bMyAccountYourDetailsPage.getErrorMsgList()).isEqualTo(errorMsg);
	}
	
	@Then("^Clear First Name on Your Details page$")
	public void clear_First_Namer_on_Your_Details_page() throws Throwable {
		b2bMyAccountYourDetailsPage.clearFirstName();
	}
	
	@Then("^Clear Last Name on Your Details page$")
	public void clear_Last_Namer_on_Your_Details_page() throws Throwable {
		b2bMyAccountYourDetailsPage.clearLastName();
	}
	
	@Then("^Clear Email on Your Details page$")
	public void clear_Email_on_Your_Details_page() throws Throwable {
		b2bMyAccountYourDetailsPage.clearEmail();
	}
	
	@Then("^Clear Primary contact number on Your Details page$")
	public void clear_Primary_contact_number_on_Your_Details_page() throws Throwable {
		b2bMyAccountYourDetailsPage.clearContactNumber();
	}
	
	@Then("^Clear EmployeeID on Your Details page$")
	public void clear_EmployeeID_on_Your_Details_page() throws Throwable {
		b2bMyAccountYourDetailsPage.clearEmployeeID();
	}
	
	@Then("^Check the Permissions table information on Your Details page$")
	public void check_the_Permissions_table_information_on_Your_Details_page(@Transpose DataTable tableDetails) throws Throwable {
		List<String> expectedTableInfo=tableDetails.asList(String.class);
		assertThat(b2bMyAccountYourDetailsPage.getPermissionTableHeader()).isEqualTo(expectedTableInfo);		
	}

}
