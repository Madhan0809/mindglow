package com.salmon.test.stepdefinitions.b2c.megaMenu;
import com.salmon.test.pageobjects.b2c.megaMenu.LPMegaMenuPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LPMegaMenuSteps {
    private LPMegaMenuPage lpMegaMenuPage;

    public LPMegaMenuSteps(LPMegaMenuPage lpMegaMenuPage) {
        this.lpMegaMenuPage = lpMegaMenuPage;
    }

    @Given("^The user is on Lloyds Pharmacy home page$")
    public void the_user_is_on_Lloyds_Pharmacy_home_page() throws Throwable {
        lpMegaMenuPage.gotoLloydsPharmacyHomePage();
    }

    @When("^The user hover over \"(.*?)\" menu$")
    public void the_user_hover_over_menu(String menu) throws Throwable {
        lpMegaMenuPage.onHoverOfMainMenu(menu);
    }

    @When("^The user hover over \"(.*?)\" department$")
    public void the_user_hover_over_department(String department) throws Throwable {
        lpMegaMenuPage.onHoverOfDepartment(department);
    }

    @Then("^The list is displayed with its full menu$")
    public void the_list_is_displayed_with_its_full_menu(List<String> categoryList) throws Throwable {
    	assertThat(lpMegaMenuPage.getDepartmentListForShopByCategoryMenu()).isEqualTo(categoryList);
    }

    @Then("^The \"(.*?)\" category menu is displayed$")
    public void the_category_menu_is_displayed(String department, List<String> categoryList) throws Throwable {
        assertThat(lpMegaMenuPage.getCategoryList(department)).isEqualTo(categoryList);
    }

    @Then("^Appropriate view all link \"(.*?)\" should be displayed$")
    public void appropriate_view_all_link_should_be_displayed(String category) throws Throwable {
        assertThat(lpMegaMenuPage.getViewAllLinkForCategoryContent()).isEqualTo(category);
    }

    @When("^The user clicks on \"(.*?)\" department$")
    public void the_user_clicks_on_department(String department) throws Throwable {
        lpMegaMenuPage.onClickOfDepartment(department);
    }

    @Then("^The user should be navigated to \"(.*?)\" department page$")
    public void the_user_should_be_navigated_to_department_page(String departmentPageTitle) throws Throwable {
        assertThat(lpMegaMenuPage.isRelevantDepartmentPage(departmentPageTitle.trim())).isTrue();
    }
}
