package com.salmon.test.stepdefinitions.b2c.department;
import com.salmon.test.pageobjects.b2c.department.LPDepartmentPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPDepartmentPageSteps {
    private LPDepartmentPage lpDepartmentPage;

    public LPDepartmentPageSteps(LPDepartmentPage lpDepartmentPage) {
        this.lpDepartmentPage = lpDepartmentPage;
    }

    @Given("^The user is on \"(.*?)\" department page$")
    public void the_user_is_on_department_page(String department) throws Throwable {
        lpDepartmentPage.goToDepartmentPage();
    }

    @Then("^appropriate breadcrumb \"(.*?)\" is displayed$")
    public void appropriate_breadcrumb_is_displayed(String department) throws Throwable {
        assertThat(department).isEqualTo(lpDepartmentPage.getRelevantBreadCrumb());
    }

    @Then("^The category menu is displayed as expected on LHS$")
    public void the_category_menu_is_displayed_on_LHS() throws Throwable {
        List<String> subCategoriesFromMega=lpDepartmentPage.getDefaultDepSubCategories();    
        assertThat(lpDepartmentPage.getDepartmentCategoryListFromLHS()).hasSameElementsAs(subCategoriesFromMega);
    }

    @When("^The user clicks on category item \"(.*?)\"$")
    public void the_user_clicks_on_category_item(String category) throws Throwable {
        lpDepartmentPage.onClickOfCategory(category);
    }

    @Then("^The user should be navigated to \"(.*?)\" PLP page$")
    public void the_user_should_be_navigated_to_PLP_page(String plpPage) throws Throwable {
        assertThat(lpDepartmentPage.getRelevantPLPPage()).isEqualToIgnoringCase(plpPage);
    }

    @When("^Click on \"(.*?)\" indicator of category menu$")
    public void click_on_indicator_of_category_menu(String indicator) throws Throwable {
        lpDepartmentPage.clickOnCategoryMenuIndicator(indicator);
    }

    @Then("^The category menu should be \"(.*?)\"\\.$")
    public void the_category_menu_should_be(String visibility) throws Throwable {
        assertThat(lpDepartmentPage.getCategoryMenuVisibility()).isEqualTo(visibility);
    }
}
