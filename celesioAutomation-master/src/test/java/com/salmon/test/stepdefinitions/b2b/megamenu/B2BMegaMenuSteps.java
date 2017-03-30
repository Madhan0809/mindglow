package com.salmon.test.stepdefinitions.b2b.megamenu;
import com.salmon.test.pageobjects.b2b.megamenu.B2BMegaMenu;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BMegaMenuSteps {
    private B2BMegaMenu b2BMegaMenu;

    public B2BMegaMenuSteps(B2BMegaMenu b2BMegaMenu) {
        this.b2BMegaMenu = b2BMegaMenu;
    }

    @Given("^The user is on B2B home page$")
    public void The_user_is_on_B_B_home_page() throws Throwable {
        b2BMegaMenu.goToB2BHomePage();
    }

    @When("^The user hover on \"([^\"]*)\" menu$")
    public void The_user_hover_on_menu(String menuItem) throws Throwable {
        b2BMegaMenu.onHoverOfMainMenu(menuItem);
    }

    @Then("^The list of values is displayed with full menu$")
    public void The_list_of_values_is_displayed_with_full_menu() throws Throwable {
        assertThat(b2BMegaMenu.getDepartmentListForShopByCategoryMenu().size()>0).isTrue();
    }

    @When("^The user hover on \"([^\"]*)\" department$")
    public void The_user_hover_on_department(String department) throws Throwable {
        b2BMegaMenu.onHoverOfDepartment(department);
    }

    @Then("^The \"([^\"]*)\" related category menu is displayed$")
    public void The_related_category_menu_is_displayed(String departmentCatgeroy) throws Throwable {
        assertThat(b2BMegaMenu.getCategoryList(departmentCatgeroy).size()>0).isTrue();
    }

    @And("^Respective view all link \"([^\"]*)\" should be displayed$")
    public void Respective_view_all_link_should_be_displayed(String viewAllLink) throws Throwable {
        assertThat(b2BMegaMenu.getViewAllLinkForCategoryContent()).isEqualTo(viewAllLink);
    }

    @When("^The user selects \"([^\"]*)\" department and clicks$")
    public void The_user_selects_department_and_clicks(String deptCategory) throws Throwable {
        if (deptCategory.equalsIgnoreCase(b2BMegaMenu.getB2bProp("category.test"))) {
            b2BMegaMenu.onClickOfDepartment();
        }
    }

    @Then("^The user should be navigated to \"([^\"]*)\" department screen$")
    public void The_user_should_be_navigated_to_department_screen(String departmentPageTitle) throws Throwable {
        assertThat(b2BMegaMenu.isRespectiveDepartmentPage(departmentPageTitle.trim())).isTrue();
    }

    @When("^The user clicked on  \"([^\"]*)\" link$")
    public void The_user_clicked_on_link(String viewAllCategoryDeptLink) throws Throwable {
        b2BMegaMenu.onClickOfViewAllForCategory();
    }

    @And("^The no results message \"([^\"]*)\"No results for \"([^\"]*)\"\" is showed on search result page$")
    public void The_no_results_message_No_results_for_is_showed_on_search_result_page(String arg1, String arg2) throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
}
