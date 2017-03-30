package com.salmon.test.stepdefinitions.csr;
import com.salmon.test.pageobjects.csr.CSRHomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CSRHomePageSteps {
    private CSRHomePage csrHomePage;

    public CSRHomePageSteps(CSRHomePage csrHomePage) {
        this.csrHomePage = csrHomePage;
    }

    @Given("^The user is on CSR Signin page$")
    public void The_user_is_on_CSR_Home_page() throws Throwable {
        csrHomePage.gotoCSRSigninPage();
    }

    @When("^I click on \"([^\"]*)\" link from CSR LHS Menu$")
    public void I_click_on_link_from_CSR_LHS_Menu(String lhsMenuLinkName) throws Throwable {
        csrHomePage.menuByLHSHeader(lhsMenuLinkName).click();
    }

    @When("^I click on Find  \"([^\"]*)\" link from CSR LHS Menu$")
    public void I_click_on_Find_link_from_CSR_LHS_Menu(String lhsMenuLinkName) throws Throwable {
        csrHomePage.findMenuByLHSHeader(lhsMenuLinkName).click();
    }

    @And("^the CSR menu is displayed is on the LHS$")
    public void the_CSR_menu_is_displayed_is_on_the_LHS(List<String> lhsMenu) throws Throwable {
        List<String> categoryList = csrHomePage.getLHSMenuLabelList().stream().map(WebElement::getText).collect(Collectors.toList());
        assertThat(categoryList).isEqualTo(lhsMenu);
    }

    @Then("^The user is signed in and navigated to CSR Home Page$")
    public void The_user_is_signed_in_and_navigated_to_CSR_Home_Page() throws Throwable {
        assertThat(csrHomePage.getCsrHeader().getText()).isEqualToIgnoringCase(csrHomePage.getCsrProp("homepage.header"));
    }

    @When("^I choose channel \"([^\"]*)\" and store brand \"([^\"]*)\"$")
    public void I_choose_channel_and_store_brand(String channel, String store) throws Throwable {
        csrHomePage.chooseChannelAndStore(channel, store);
    }

    @Then("^the relevant store brand go to \"([^\"]*)\" is displayed$")
    public void the_relevant_store_brand_go_to_is_displayed(String storeBrand) throws Throwable {
        if (storeBrand.equalsIgnoreCase("LloydsPharmacy")) {
            assertThat(csrHomePage.gotoLink().getText()).containsIgnoringCase(csrHomePage.getCsrProp("homepage.goToLloydsPharmacyLink"));
        }
    }

    @When("^I click on the relevant store brand got to \"([^\"]*)\"$")
    public void I_click_on_the_relevant_store_brand_got_to(String storeBrand) throws Throwable {
        csrHomePage.gotoLink().click();
    }

    @And("^the \"([^\"]*)\" link from CSR LHS Menu contains the selected result \"([^\"]*)\"$")
    public void the_link_from_CSR_LHS_Menu_contains_the_selected_result(String lhsMenuLinkName, String searchResult) throws Throwable {
        WebElement foundSearchResult = csrHomePage.getFoundSearchResult(searchResult, lhsMenuLinkName);
        assertThat(searchResult.contains(foundSearchResult.getText()));
    }

    @When("^I click on  \"([^\"]*)\" link from CSR LHS Menu which contains the result \"([^\"]*)\"$")
    public void I_click_on_link_from_CSR_LHS_Menu_which_contains_the_result(String lhsMenuLinkName, String searchResult) throws Throwable {
        WebElement foundSearchResult = csrHomePage.getFoundSearchResult(searchResult, lhsMenuLinkName);
        foundSearchResult.click();
    }
}
