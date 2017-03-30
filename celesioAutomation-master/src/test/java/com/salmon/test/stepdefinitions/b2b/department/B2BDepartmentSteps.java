package com.salmon.test.stepdefinitions.b2b.department;

import com.salmon.test.pageobjects.b2b.department.B2BDepartmentPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BDepartmentSteps {
    private static final Logger LOG = LoggerFactory.getLogger(B2BDepartmentPage.class);
    private B2BDepartmentPage b2bDepartment;

    //	@Then("^Check \"(.*?)\" can be shown in department page$")
//	public void check_webelement_item_or_content_in_department_page(String itemName) throws Throwable{
//		if(itemName.equalsIgnoreCase("promotional content")){
//			
//		}
//		else if(itemName.equalsIgnoreCase("espot")){
//			
//		}
//	}
    public B2BDepartmentSteps(B2BDepartmentPage b2bDepartment) {
        this.b2bDepartment = b2bDepartment;
    }

    @When("^The user click on target department \"(.*?)\" from mega menu$")
    public void the_user_hover_on_target_department(String departmentName) throws Throwable {
        b2bDepartment.onClickOfTargetDepartment(departmentName);
    }

    @Then("^The target department page \"([^\"]*)\" is displayed correctly$")
    public void the_target_department_page_is_displayed_correctly(String departmentTitle) throws Throwable {
        b2bDepartment.isDepartmentPageDisplayedCorrectly(departmentTitle);
    }

    @Then("^Check best promotions is shown in department page$")
    public void check_best_promotions_can_be_shown_in_department_page() throws Throwable {
        assertThat(b2bDepartment.isBestPromotionsDisplayed()).isTrue();
    }

    @Then("^Check eSpots is shown in department page$")
    public void check_eSpot_can_be_shown_in_department_page() throws Throwable {
        assertThat(b2bDepartment.isEspotDisplayed()).isTrue();
    }
    
    @Then("^Check the panel of Carousel is displayed from department page$")
    public void check_the_panel_of_carousel_is_displayed_from_department_page() throws Throwable{
    	assertThat(b2bDepartment.isCarouselDisplayed()).isTrue();
    }

    @Then("^All images in current view in subcategory can be displayed in department page$")
    public void check_images_in_subcategory_can_be_displayed_in_department_page() throws Throwable {
        assertThat(b2bDepartment.isAllImagesInSubCategoryDisplayed()).isTrue();
    }

    @When("^User click on \"([^\"]*)\" arrow navigation from best sellers$")
    public void click_on_arrow_navigation_from_best_sellers(String arrowNav) throws Throwable {
        b2bDepartment.clickArrowsFromBestSellers(arrowNav);
    }

    @When("^User click on product item \"([^\"]*)\" in panel \"([^\"]*)\" from department page$")
    public void user_click_on_product_item_from_department_page(String productName, String panelName) throws Throwable {
        if (panelName.equalsIgnoreCase("Carousel")) {
            b2bDepartment.clickOnProductFromCarousel(productName);
        } else if (panelName.equalsIgnoreCase("Best Seller")) {
            b2bDepartment.clickOnProductFromBestSellers(productName);
        } else
            LOG.error("Cannot find the panel: " + panelName);
    }

    @When("^User select the \"([^\"]*)\" sign on the category section$")
    public void user_select_sign_on_the_category_section(String toggleOption) throws Throwable {
        b2bDepartment.clickOnToggleIcon(toggleOption);
    }

    @Then("^Check that all categories in facet navigation are in \"([^\"]*)\" status$")
    public void check_all_catagories_in_facet_navigation_status(String displayStatus) throws Throwable {
        if (displayStatus.contains("Hidden")) {
            assertThat(b2bDepartment.canDisplayAllCatagoriesInFacetNavigation()).isFalse();
        } else if (displayStatus.contains("Visual")) {
            assertThat(b2bDepartment.canDisplayAllCatagoriesInFacetNavigation()).isTrue();
        }
    }

    @Then("^Check there should be (\\d+) sub categories under facet navigation$")
    public void check_the_sub_categories_count_under_facet_navigation(int expectedCount) throws Throwable {
        assertThat(b2bDepartment.getSubCategoryCountFromFacet()).isEqualTo(expectedCount);
    }

    @Given("^The user Select \"([^\"]*)\" Category from Facet navigation$")
    public void the_user_select_subCategory_from_facet_navigation(String subCategoryName) throws Throwable {
        b2bDepartment.selectSubCategoryFromFacet(subCategoryName);
    }
}
