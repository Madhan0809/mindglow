package com.salmon.test.stepdefinitions.b2c.checkout;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.models.b2c.AddressForm;
import com.salmon.test.pageobjects.b2c.checkout.LPDeliveryOptionPage;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LPDeliveryOptionSteps {
    private static final Logger LOG = LoggerFactory.getLogger(LPDeliveryOptionPage.class);
    private LPDeliveryOptionPage lpDeliveryOptionPage;
    private Map<String, String> orderSummaryInfo = new HashMap<>();
    private List<String> storeDetailPopupInfo = new ArrayList<>();

    public LPDeliveryOptionSteps(LPDeliveryOptionPage lpDeliveryStep) throws Throwable {
        this.lpDeliveryOptionPage = lpDeliveryStep;
    }

    @Given("The user selects \"(.*?)\" delivery radio button")
    public void the_user_select_delivery_option(String deliveryMethod) throws Throwable {
        lpDeliveryOptionPage.selectDeliveryOptions(deliveryMethod);
    }

    @When("Check user enter store locator lister page")
    public void check_user_enter_store_locator_lister_page_from_delivery_page() throws Throwable {
        assertThat(lpDeliveryOptionPage.isStoreLodcatorPageDisplayed()).isTrue();
    }

    @When("User click store \"(.*?)\" from locator map")
    public void user_click_store_from_locator_map(String storeNumber) throws Throwable {
        lpDeliveryOptionPage.clickStoreFromLocatorMap(storeNumber);
    }

    @When("User select store \"(.*?)\" from google map")
    public void user_select_store_from_locator_map(String storeNumber) throws Throwable {
        lpDeliveryOptionPage.selectStoreFromLocatorMap(storeNumber);
    }

    @When("User select store \"(.*?)\" from store result list")
    public void user_select_store_from_store_result_list(String storeNumber) throws Throwable {
        lpDeliveryOptionPage.selectStoreFromResultList(storeNumber);
    }
    
    @When("User select store from store details page")
    public void user_select_store_from_store_details_page() throws Throwable{
        lpDeliveryOptionPage.selectStoreFromStoreDetailsPage();
    }

    @Then("The store collection point is \"(.*?)\" in C&C from delivery page")
    public void check_store_is_selected_in_delivery_page(String shouldDisplay) {
        if (shouldDisplay.contains("not")) {
            assertThat(lpDeliveryOptionPage.isCollectionPointFoundInDeliveryPage()).isEqualTo(false);
        } else
            assertThat(lpDeliveryOptionPage.isCollectionPointFoundInDeliveryPage()).isEqualTo(true);
    }

    @Then("The store detail information is displayed in popup from google map")
    public void check_store_detail_info_display_from_map() throws Throwable {
        assertThat(lpDeliveryOptionPage.isStoreDetailPopupDisplayed()).isTrue();
    }

    @Then("User change the collection point in delivery page")
    public void user_change_collection_point_in_delivery_page() throws Throwable {
        lpDeliveryOptionPage.changeCollectionPoint();
    }

    @And("User get the store detail information from google map")
    public void user_get_store_detail_information_from_google_map() throws Throwable {
        this.storeDetailPopupInfo = lpDeliveryOptionPage.getStoreDetailInfoFromPopup();
    }

    @Then("User can get the matching store detail information of store \"(.*?)\" from store list")
    public void user_get_matching_store_detail_from_store_list(String storeNumber) {
        List<String> actualDetailsFromList = lpDeliveryOptionPage.getStoreDetailInfoFromStoreList(storeNumber);
        assertThat(lpDeliveryOptionPage.isStorePopupInfoMatch(storeDetailPopupInfo, actualDetailsFromList)).isTrue();
    }

    @When("User search store \"(.*?)\" from store locator list page")
    public void user_search_store_from_store_locator_page(String searchText) throws Throwable {
        lpDeliveryOptionPage.searchStoreInStoreLocator(searchText);
    }

    @When("User views the store details of \"(.*?)\"")
    public void user_views_the_store_details(String storeNumber) throws Throwable {
        lpDeliveryOptionPage.clickOnViewStoreDetails(storeNumber);
    }

    @Then("Store detail information can be shown")
    public void store_detail_information_can_be_shown() throws Throwable {
        assertThat(lpDeliveryOptionPage.isStoreDetailsPageDisplayed()).isTrue();
    }
    
    @Then("Check Store lister information can be shown")
    public void check_store_lister_information_can_be_shown() throws Throwable{
        assertThat(lpDeliveryOptionPage.isStoreListerInfomationDisplayed()).isTrue();
    }
    
//    @Then("^The delivery option default page is displayed$")
//    public void the_delivery_option_default_page_is_displayed() throws Throwable{
//    	
//    }

    @Then("Check the \"(.*?)\" text table and form in should display")
    public void check_the_international_table_display(String deliveryMethod) throws Throwable {
        assertThat(lpDeliveryOptionPage.isFormTableDisplayed(deliveryMethod)).isTrue();
    }

    @And("^Click on \"(.*?)\" and create or edit delivery address$")
    public void click_on_create_or_edit_delivery_address(String linkName, @Transpose List<AddressForm> deliveryEditInfo) throws Throwable {
        lpDeliveryOptionPage.editDeliveryAddressByClickLink(linkName);
        if (linkName.contains("Edit")) {
            lpDeliveryOptionPage.editDeliveryAddressInfo(deliveryEditInfo.get(0), "Edit");
        } else
            lpDeliveryOptionPage.editDeliveryAddressInfo(deliveryEditInfo.get(0), "Add");
    }

    @And("^Click on \"(.*?)\" in the delivery address$")
    public void click_on_back_or_save_in_edit_delivery_address(String buttonName) throws Throwable {
        lpDeliveryOptionPage.clickOnButtonInDeliveryAddressEditMode(buttonName);
    }

    @And("^Input \"(.*?)\" click and collect information for delivery$")
    public void Input_valid_click_and_collect_information_for_delivery(String userType, @Transpose List<AddressForm> addressForm) throws Throwable {
        lpDeliveryOptionPage.inputClickCollectInformation(addressForm.get(0));
    }

    @And("^Click on Find Address button in \"(.*?)\" delivery option$")
    public void click_on_find_address_button(String deliveryOptionName) throws Throwable {
        LOG.debug("Check 'find address' function in delivery option page works well.");
        lpDeliveryOptionPage.clickFindAddressInDeliveryOption(deliveryOptionName);
    }

    @And("^Input \"(.*?)\" detail information for delivery$")
    public void input_detail_information(String userType, @Transpose List<AddressForm> addressForm) throws Throwable {
        lpDeliveryOptionPage.inputDetailInformation(addressForm.get(0));
        if (userType.equalsIgnoreCase(LloydsPharmacyConstants.GUEST_USER)) {
            lpDeliveryOptionPage.inputMobilePreferredInfo(addressForm.get(0));
        }
    }
//    @And("^Input \"(.*?)\" Mobile preferred Information for delivery$")
//    public void input_mobile_preferred_information(String userType, @Transpose List<AddressForm>addressForm) throws Throwable{
//    	lpDeliveryOptionPage.inputMobilePreferredInfo(addressForm.get(0));
//    }
//    @And("^Input invalid detail information for delivery$")
//    public void input_invalid_information_for_delivery(@Transpose List<AddressForm>addressForm) throws Throwable{
//    	LOG.info("Input invalid detail information and valid delivery address and method to test.");
//    	lpDeliveryOptionPage.inputDetailInformation(addressForm.get(0));
//    }

    @And("^select on valid delivery address$")
    public void choose_delivery_address_for_international_table(@Transpose List<AddressForm> addressForm) throws Throwable {
        lpDeliveryOptionPage.chooseDeliveryAddress(addressForm.get(0));
    }

    @And("^select on preferred delivery method$")
    public void choose_preferred_delivery_method_for_international_table(@Transpose List<AddressForm> addressForm) throws Throwable {
        lpDeliveryOptionPage.choosePreferredDeliveryMethod(addressForm.get(0));
    }

    @And("^input delivery instructions$")
    public void input_delivery_instructions_for_international_table(@Transpose List<AddressForm> addressForm) throws Throwable {
        lpDeliveryOptionPage.inputDeliveryInstructions(addressForm.get(0));
    }

    @When("^I click on Continue to payment button$")
    public void click_continue_to_payment_button() throws Throwable {
        lpDeliveryOptionPage.clickOnContinueToPayment();
    }

    @Then("^the Payment page opens$")
    public void check_the_payment_page_opens() throws Throwable {
        if(lpDeliveryOptionPage.getWebDriver().getTitle().contains("Problem loading page")){
            lpDeliveryOptionPage.getWebDriver().navigate().refresh();
        }
        assertThat(lpDeliveryOptionPage.isPaymentPageDisplays()).isTrue();
    }

    @Then("^error message should comes in delivery page$")
    public void error_message_should_comes_in_delivery_page() throws Throwable {
        LOG.info("Check that error message should come when input invalid information and it shouldn't go to 'Payment' page.");
        assertThat(lpDeliveryOptionPage.isErrorMessageComes()).isTrue();
        assertThat(lpDeliveryOptionPage.isPaymentPageDisplays()).isFalse();
    }

    @Then("^Validate error message details in delivery option page$")
    public void validate_error_message_details_in_delivery_option_page(List<String> expectedErrorMsg) throws Throwable {
        assertThat(lpDeliveryOptionPage.canDisplayAllErrorMessages(expectedErrorMsg)).isTrue();
    }

    @Then("^error message should comes in delivery address page$")
    public void error_message_should_comes_in_delivery_address_page() {
        LOG.info("Check that error message should come in 'Address' edit mode.");
// TBD
//    	assertThat(lpDeliveryOptionPage.isErrorMessageComes()).isTrue();
    }

    @Given("^Get the information of order summary$")
    public void get_the_information_of_order_summary() throws Throwable {
        assertThat(lpDeliveryOptionPage.isSummaryInfoPanelDisplayed()).isTrue();
        this.orderSummaryInfo = lpDeliveryOptionPage.getOrderSummaryInfo();
    }

    @And("^Get the information of order summary for mixed basket$")
    public void get_the_information_of_order_summary_for_mixed_basket() throws Throwable {
        LOG.info("Check whether the order list summary information is displayed.");
        assertThat(lpDeliveryOptionPage.isSummaryInfoPanelDisplayed()).isTrue();
        LOG.info("Get the actual order summary information.");
        this.orderSummaryInfo = lpDeliveryOptionPage.getOrderSummaryInfo();
    }

    @Then("^Check the result of order summary for mixed basket is correct$")
    public void check_the_result_of_order_summary_for_mixed_bakset_is_correct(Map<String, String> expectedInfo) throws Throwable {
        check_the_result_of_order_summary_is_correct(expectedInfo);
    }

    @Then("^Check the result of order summary is correct$")
    public void check_the_result_of_order_summary_is_correct(Map<String, String> expectedInfo) throws Throwable {
        LOG.info("Check whether the actual order list summary information is correct as expected.");
        //Some error happens in background steps
        assertThat(lpDeliveryOptionPage.isOrderSummaryInfoSame(this.orderSummaryInfo, expectedInfo)).isTrue();
    }

    @Then("^Check the result of order detail information is correct$")
    public void check_the_result_of_order_detail_information_is_correct(@Transpose DataTable detailOrderInfo) throws Throwable {
        LOG.info("Check whether the actual order detail information is correct as expected.");
        List<List<String>> productDetailInfo = detailOrderInfo.cells(0);
        assertThat(lpDeliveryOptionPage.isProductDetailInfoFound(productDetailInfo)).isTrue();
    }

    @Then("^The header information of delivery page is displayed$")
    public void check_whether_the_header_information_is_displayed() throws Throwable {
        LOG.info("Check whether header information display is correct.");
        assertThat(lpDeliveryOptionPage.isHeaderInfoExist()).isTrue();
    }

    @Then("^The footer information of delivery page is displayed$")
    public void check_whether_the_footer_information_is_displayed() throws Throwable {
        LOG.info("Check whether footer information display is correct.");
        assertThat(lpDeliveryOptionPage.isFooterInfoExist()).isTrue();
    }

    @Given("^The user enter delivery option page with guest user$")
    public void enter_delivery_option_page_with_guest_user() throws Throwable {
        lpDeliveryOptionPage.signInWithGuestuser();
    }

    @Then("^The Click and Collect option is not available$")
    public void the_click_and_collect_is_not_available() throws Throwable {
        assertThat(lpDeliveryOptionPage.isOptionAvailable("Click and Collect")).isFalse();
    }

    @Then("^The \"(.*?)\" option is not available in delivery option page$")
    public void the_delivery_to_UK_is_not_availabe(String optionName) throws Throwable {
        if (optionName.equalsIgnoreCase("Click and Collect")) {
            assertThat(lpDeliveryOptionPage.isOptionAvailable("Click and Collect")).isFalse();
        } else if (optionName.equalsIgnoreCase("Deliver to UK address")) {
            assertThat(lpDeliveryOptionPage.isOptionAvailable("Deliver to UK address not available")).isFalse();
        } else {
            Assert.assertTrue(false, "Cannot find the target option: " + optionName);
        }
    }

    @Then("^The \"(.*?)\" option is available in delivery option page$")
    public void the_option_is_available_in_delivery_option_page(String optionName) throws Throwable {
        assertThat(lpDeliveryOptionPage.isOptionAvailable(optionName)).isTrue();
    }

    @Given("^The user is on Lloyds Pharmacy home page without delete cookies$")
    public void start_from_homepage_without_delete_cookies() throws Throwable {
        lpDeliveryOptionPage.startFromHomePageWithCookies();
    }

    @Then("^Check the \"(.*?)\" exception table should display$")
    public void check_the_exception_table_should_display(String exceptionTableName) throws Throwable {
        LOG.info("Check whether the exception table in the target option exists: " + exceptionTableName);
        assertThat(lpDeliveryOptionPage.canViewExceptionTable(exceptionTableName)).isTrue();
    }

    @Then("^Check error message for exception table in \"(.*?)\" form is correct$")
    public void check_the_error_message_for_exception_table_is_correct(String exceptionTableName, List<String> errorMsgContent) throws Throwable {
        LOG.info("Check the error message for exception table is correct in the form: " + exceptionTableName);
        assertThat(lpDeliveryOptionPage.canViewCorectExceptionTableMessage(exceptionTableName, errorMsgContent));
    }

    @When("^Click on remove and continue button in \"(.*?)\" form in delivery option page$")
    public void click_on_remove_and_continue_button_in_delivery_option_page(String exceptionTableName) throws Throwable {
        lpDeliveryOptionPage.clickOnRemoveAndContinue(exceptionTableName);
    }
}
