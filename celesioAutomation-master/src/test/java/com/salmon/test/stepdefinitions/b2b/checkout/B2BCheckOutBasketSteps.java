package com.salmon.test.stepdefinitions.b2b.checkout;
import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.pageobjects.b2b.B2BPageLandingModule;
import com.salmon.test.pageobjects.b2b.checkout.B2BCheckOutBasketPage;
import cucumber.api.DataTable;
import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class B2BCheckOutBasketSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BCheckOutBasketSteps.class);
	
	 private B2BCheckOutBasketPage b2bCheckOutBasketPage;
	 public static String referenceData;
	 private B2BPageLandingModule b2bPageLandingModule;
	 
	 public B2BCheckOutBasketSteps(B2BCheckOutBasketPage checkOutBasketPage,B2BPageLandingModule pageLandingModule){
		 b2bCheckOutBasketPage=checkOutBasketPage;
		 b2bPageLandingModule=pageLandingModule;
	 }
	 
	 @Given("^The user is on basket checkout page in AAH$")
	 public void the_user_is_on_basket_checkout_page_in_OTC() throws Throwable{
		 b2bCheckOutBasketPage.landingToCheckoutBasketFromAAHLogin(b2bPageLandingModule);
	 }
	 
	 @When("^The user click on \"(.*?)\" button in current order$")
	 public void the_user_click_on_button_in_current_order(String buttonName) throws Throwable{
		 b2bCheckOutBasketPage.clickOnButtonFromCurrentOrder(buttonName);
	 }
	 
	 @When("^The user click on product \"(.*?)\" in current order page$")
	 public void the_user_click_on_product_in_current_order_page(String productName) throws Throwable{
	     if(productName.startsWith("b2b.")){
             productName=b2bCheckOutBasketPage.getProp(productName);
         }
	     b2bCheckOutBasketPage.clickOnTargetProduct(productName);
	 }
	 
	 @When("^User select account \"(.*?)\" from header$")
	 public void user_select_account_from_header(String accountContractName) throws Throwable{
		 b2bCheckOutBasketPage.selectAccountsFromDropDownHeader(accountContractName);
	 }
	 
	 @And("^User clear the all content in default basket \"(.*?)\"$")
	 public void user_clear_the_all_content_in_default_basket(String basketName) throws Throwable{
	   String defaultEnterpriseBasket=b2bCheckOutBasketPage.getProp("b2b.enterprise.currentBasket");
	   String defaultAAHBasket=b2bCheckOutBasketPage.getProp("b2b.aah.currentBasket");
	   if(!b2bCheckOutBasketPage.isBasketFoundInHeader(b2bCheckOutBasketPage.getProp(basketName))){
		   LOG.info("Default basket is not existed in current user. Now create a default basket firstly: "+basketName);
		   b2bCheckOutBasketPage.createDefaultBasket(b2bCheckOutBasketPage.getProp(basketName));
	   }
		b2bCheckOutBasketPage.selectBasketFromMiniBasket(defaultEnterpriseBasket,"ENTERPRISE");
	    b2bCheckOutBasketPage.selectBasketFromMiniBasket(defaultAAHBasket,"AAH");
        if (b2bCheckOutBasketPage.getProductItemsQuantityFromMiniBasket(b2bCheckOutBasketPage.getProp(basketName)) == 0) {
			LOG.info("\n The default basket is already an empty basket: " + basketName+". So skip the clear basket process.");
		} else {
			if (basketName.equals("b2b.enterprise.currentBasket")) {	
				b2bCheckOutBasketPage.clickOnTargetMiniBasket("ENTERPRISE");
			} else if (basketName.equals("b2b.aah.currentBasket")) {				
				b2bCheckOutBasketPage.clickOnTargetMiniBasket("AAH");
			} else
				LOG.error("Basket is not found or it's not a default basket: " + basketName);
			b2bCheckOutBasketPage.cleanAllContentFromBasket();
		}
        LOG.info("Currently we have "+b2bCheckOutBasketPage.getProductItemsQuantityFromMiniBasket(b2bCheckOutBasketPage.getProp(basketName))+" item in basket "+basketName);
	}
	 
	 @When("^The user clear all products from basket \"(.*?)\" in \"(.*?)\" row$")
	 public void the_user_clear_all_products_from_basket_in_row(String basketName, String miniBasketRow) throws Throwable {
	     if(basketName.startsWith("b2b.")){
	         basketName=b2bCheckOutBasketPage.getProp(basketName);
         }	     
	     b2bCheckOutBasketPage.selectBasketFromMiniBasket(basketName, miniBasketRow);
		 if (miniBasketRow.equals("ENTERPRISE")) {				
			b2bCheckOutBasketPage.clickOnTargetMiniBasket("ENTERPRISE");
		} else if (miniBasketRow.equals("AAH")) {				
			b2bCheckOutBasketPage.clickOnTargetMiniBasket("AAH");
		} 
		b2bCheckOutBasketPage.cleanAllContentFromBasket();
		assertThat(b2bCheckOutBasketPage.getProductItemsQuantityFromMiniBasket(basketName)).isEqualTo(0);
	 }
	 
	 @When("^Add one product into \"(.*?)\" basket in PLP$")
	 public void add_one_product_into_target_basket_in_PLP(String basketName) throws Throwable{
		 if(basketName.startsWith("b2b.")){
			 basketName=b2bCheckOutBasketPage.getProp(basketName);
		 }
		 b2bCheckOutBasketPage.addOneProductIntoTargetBasket(basketName);
	 }
	 
	 @When("^Add product \"(.*?)\" into target basket \"(.*?)\"$")
	 public void add_product_into_target_basket(String productName, String basketName) throws Throwable{
		 if(basketName.startsWith("b2b.")){
			 basketName=b2bCheckOutBasketPage.getProp(basketName);
		 }
		 if(productName.startsWith("b2b.")){
		     productName=b2bCheckOutBasketPage.getProp(productName);
		 }
		 b2bCheckOutBasketPage.addProductIntoTargetBasket(productName,basketName);
	 }
	 
	 
	 @When("^The user select \"(.*?)\" from mini basket in \"(.*?)\" row$")
    public void the_user_select_item_from_mini_basket(String basketName, String miniBasketRow) throws Throwable {
        if (basketName.startsWith("b2b.")) {
            b2bCheckOutBasketPage.selectBasketFromMiniBasket(b2bCheckOutBasketPage.getProp(basketName), miniBasketRow);
        } else
            b2bCheckOutBasketPage.selectBasketFromMiniBasket(basketName, miniBasketRow);
    }
	 
	 @When("^User input information from multiple delivery points$")
	 public void user_input_information_from_multiple_delivery_points() throws Throwable{
		 b2bCheckOutBasketPage.addDefaultMultipleDeliveryPoint();
	 }
	 
	 @When("^User input information for duplicate basket$")
	 public void user_input_information_for_duplicate_basket(Map<String,String>dupDetails) throws Throwable{
	     b2bCheckOutBasketPage.addCustomizedMultiDeliveryPoint(dupDetails);
	 }
	 
	 @When("^User add product with basket detail information into basket from PLP$")
	 public void user_add_product_with_basket_detail_information_into_basekt_from_PLP(@Transpose DataTable basketInfo) throws Throwable{
		List<Map<String,String>>basketDetailInfo= basketInfo.asMaps(String.class, String.class);
		for(Map<String, String> singleBasketInfo: basketDetailInfo){
			b2bCheckOutBasketPage.addProductIntoBasket(singleBasketInfo);
		}
	 }
	 
	 @Then("^The mini shop cart is displayed in header$")
	 public void check_the_mini_shop_cart_is_displayed_in_header() throws Throwable{
		 assertThat(b2bCheckOutBasketPage.canDisplayMiniBasketPopupFromHeader()).isTrue();
	 }
	 
	 @And("^check product \"(.*?)\" is found in mini shop cart$")
	 public void check_product_is_found_in_mini_shop_cart(String productName) throws Throwable{
	     if(productName.startsWith("b2b.")){
             productName=b2bCheckOutBasketPage.getProp(productName);
         }
		 assertThat(b2bCheckOutBasketPage.isProductFoundInMiniShopCart(productName)).isTrue();
	 }
	 
	 
	 @And("^The user input basket detail information to create$")
	 public void the_user_input_basket_detail_information_to_create(Map<String,String> basketDetailInfo) throws Throwable{
         HashMap<String,String> updatedBasketInfo= new HashMap<String,String>();
         updatedBasketInfo.putAll(basketDetailInfo);
         for(String key:updatedBasketInfo.keySet()){
             if(updatedBasketInfo.get(key).startsWith("b2b.")){
                 updatedBasketInfo.replace(key, b2bCheckOutBasketPage.getProp(updatedBasketInfo.get(key)));
             }
         }
		 b2bCheckOutBasketPage.createNewBasket(updatedBasketInfo);
	 }	  
	 
	 @Then("^The new basket is created with the message \"(.*?)\"$")
	 public void the_new_basket_is_created_with_the_message(String message) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getBasketMessage()).isEqualTo(message);
	 }
	 
	 @Then("^The new basket is created with name as \"(.*?)\"$")
	 public void the_new_basket_is_created_with_name_as(String basketName) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.isBasketFoundInHeader(basketName)).isTrue();
	 } 
 
	 @When("^The user click Send Order button$")
	 public void the_user_click_Send_Order_button() throws Throwable {
		 b2bCheckOutBasketPage.onClickSendOrder();
	 }
	 
	 @Then("^The user click Request Review button$")
	 public void the_user_click_Request_Review_button() throws Throwable {
		 b2bCheckOutBasketPage.onClickRequestReview();
	 }	 

	 @Then("^The AAH basket \"(.*?)\" is removed from mini basket row$")
    public void the_basket_is_removed_from_mini_basket_row(String basketName) throws Throwable {
        if (basketName.startsWith("b2b.")) {
            basketName = b2bCheckOutBasketPage.getProp(basketName);
        }
        assertThat(b2bCheckOutBasketPage.isBasketFoundInAAHHeader(basketName)).isFalse();
    }
	 
	 @Then("^The ENTERPRISE basket \"(.*?)\" is removed from mini basket row$")
	 public void the_ENTERPRISE_basket_is_removed_from_mini_basket_row(String basketName) throws Throwable {
	     if(basketName.startsWith("b2b.")){
             basketName=b2bCheckOutBasketPage.getProp(basketName);   
          }
	     assertThat(b2bCheckOutBasketPage.isBasketFoundInENTERPRISEHeader(basketName)).isFalse();
	 }
	 
	 @When("^The user click the AAH mini cart button$")
	 public void the_user_click_the_AAH_mini_cart_button() throws Throwable {
		 b2bCheckOutBasketPage.onClickAAHMiniCart();
	 }
	 
	 @When("^The user click the ENTERPRISE mini cart button$")
     public void the_user_click_the_ENTERPRISE_mini_cart_button() throws Throwable {
         b2bCheckOutBasketPage.onClickENTERPRISEMiniCart();
     }
	 
	 @And("^User input reference data \"(.*?)\" in current basket$")
	 public void user_input_reference_data_in_current_basket(String refData) throws Throwable{     
	     referenceData=refData+RandomGenerator.random(5, PermittedCharacters.ALPHANUMERIC);
	     LOG.info("Input user reference for the basket: "+referenceData);
	     b2bCheckOutBasketPage.inputReference(referenceData);
	 }
	 
	 @Then("^Check order summary header displayed in correct format$")
	 public void check_order_summary_header_displayed_in_correct_format(List<String>orderSummary) throws Throwable{
		 assertThat(b2bCheckOutBasketPage.getAccountHeaderInfo()).isEqualTo(orderSummary);
	 }
	 
	 @Then("^The new ENTERPRISE basket is created with name as \"(.*?)\"$")
	 public void the_new_ENTERPRISE_basket_is_created_with_name_as(String basketName) throws Throwable {
		 if(basketName.startsWith("b2b.")){
		    basketName=b2bCheckOutBasketPage.getProp(basketName);   
		 }
	     assertThat(b2bCheckOutBasketPage.isBasketFoundInENTERPRISEHeader(basketName)).isTrue();
	 }
	 
	 
	 @Then("^The new AAH basket is created with name as \"(.*?)\"$")
    public void the_new_AAH_basket_is_created_with_name_as(String basketName) throws Throwable {
        if (basketName.startsWith("b2b.")) {
            basketName=b2bCheckOutBasketPage.getProp(basketName);
        }
        assertThat(b2bCheckOutBasketPage.isBasketFoundInAAHHeader(basketName)).isTrue();
    }
	 
 
	 @When("^Remove the product from the basket on the basekt page$")
	 public void remove_the_product_from_the_basket_on_the_basekt_page() throws Throwable {
		 b2bCheckOutBasketPage.onClickRemove();
	 }
	 
	 @When("^The empty basket message \"(.*?)\" is shown on the basket page$")
	 public void the_empty_basket_message_is_shown_on_the_basket_page(String message) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getBasketMessage()).isEqualTo(message);
	 }
	 
	 @Then("^The \"(.*?)\" page is displayed in checkout basket process")
	 public void check_the_target_page_is_displayed_in_checkout_basket_process(String pageName) throws Throwable{
		assertThat(b2bCheckOutBasketPage.isRedirectedToTargetPage(pageName)).isTrue(); 
	 }

	 @Then("^Check create basket from header is displayed$")
	 public void check_create_basket_from_header_is_displayed() throws Throwable{
		 assertThat(b2bCheckOutBasketPage.canDisplayAllMiniBasketFromHeader()).isTrue();
	 }
	 
	 @Then("^Check \"(.*?)\" summary information should displayed$")
	 public void check_AAH_order_summary_infomration_display(String orderPageName, Map<String, String> orderSummaryInfo) throws Throwable{
		 if(orderPageName.equals("OTC order")){
		   assertThat(b2bCheckOutBasketPage.isOrderAccountInfoDislayCorrectly(orderSummaryInfo)).isTrue();
		 }
	 }
	 
	 @Then("^The product name \"(.*?)\" is found in current order$")
	 public void the_product_name_in_basket_result_is_found_in_current_order(String product) throws Throwable{
	     if(product.startsWith("b2b.")){
	         product=b2bCheckOutBasketPage.getProp(product);
         }
	     assertThat(b2bCheckOutBasketPage.getAllProductFromCurrentOrder()).contains(product);
	 }
	 
	 
	 @Then("^The product information in basket result is found in \"(.*?)\" page$")
     public void the_product_information_in_basket_result_is_displayed_correctly(String orderPageName,Map<String, String> productInfo) throws Throwable {
	     HashMap<String,String>updatedInfo=new HashMap<String,String>();
	     updatedInfo.putAll(productInfo);
	     for (String k : updatedInfo.keySet()) {
            if (updatedInfo.get(k).startsWith("b2b.")) {
                updatedInfo.replace(k, b2bCheckOutBasketPage.getProp(updatedInfo.get(k)));
            }
        }
        if (orderPageName.equals("OTC order")) {
            assertThat(b2bCheckOutBasketPage.canProductFoundInOrderDetails(updatedInfo)).isTrue();
        }
    }
	 	 
	 @When("^The user update the amount for product \"(.*?)\" set quanlity as (\\d+)$")
	 public void the_user_update_amount_for_product_quanlity(String productName, int updatedValue) throws Throwable{
	     if(productName.startsWith("b2b.")){
             productName=b2bCheckOutBasketPage.getProp(productName);
         }
	     b2bCheckOutBasketPage.updateProductQuantity(productName,updatedValue);
	 }

	 @Then("^Check Total price for product \"(.*?)\" should be updated accordingly$")
	 public void check_total_price_for_target_product_should_be_updated_accordingly(String productName) throws Throwable{
		double eachProductCount=b2bCheckOutBasketPage.getEachPriceOfProduct(productName);
		int quanlityOfProduct=b2bCheckOutBasketPage.getQuantityOfProduct(productName);
		assertThat(eachProductCount*quanlityOfProduct).isEqualTo(b2bCheckOutBasketPage.getTotalPriceFromProductRow(productName));
	 }
	 

	@Then("^The link Replicate order for multiple accounts/delivery points should be \"(.*?)\"$")
	public void the_link_Replicate_order_for_multiple_accounts_delivery_points_should_be(String isDisplay) throws Throwable {
		 if(isDisplay.equalsIgnoreCase("Visible")){
			 assertThat(b2bCheckOutBasketPage.isDisplayMultiOrderLink()).isTrue();
		 }
		 else if (isDisplay.equalsIgnoreCase("Hidden")){
			 assertThat(b2bCheckOutBasketPage.isDisplayMultiOrderLink()).isFalse();
		 }
	}
	 
	 @And("^The user removes \"(.*?)\" from current order$")
	 public void the_user_remove_product_from_current_order(String productName) throws Throwable{
	     if(productName.startsWith("b2b.")){
             productName=b2bCheckOutBasketPage.getProp(productName);
         }
	     b2bCheckOutBasketPage.removeProductFromCurrentOrder(productName);
	 }
	 
	 @Then("^Popup should be displayed with the relevant message \"(.*?)\" in b2b checkOutBasket process$")
	 public void check_popup_displaying_in_b2b_checkout_basket_process(String expectedMsg) throws Throwable{
		 assertThat(b2bCheckOutBasketPage.canGetCorrectPopupFromCurrentOrder(expectedMsg)).isTrue();
	 }
	 
	 @Then("^Check the image for all products can be displayed$")
	 public void check_the_image_for_all_product_can_be_displayed() throws Throwable{
		assertThat(b2bCheckOutBasketPage.canShowAllProductImgs()).isTrue(); 
	 }
	 
	 @Then("^Check there are \"(.*?)\" items in mini shop cart in \"(.*?)\" row$")
	 public void check_the_items_quantity_in_mini_shop_cart(int quantity, String brandName) throws Throwable{
		 assertThat(b2bCheckOutBasketPage.getProductItemsQuantityFromMiniBasket(brandName)).isEqualTo(quantity);
	 }
	 
	 @Then("^Check Sku description for all products can be displayed$")
	 public void check_sku_description_for_all_products_can_be_displayed() throws Throwable{
		 assertThat(b2bCheckOutBasketPage.canShowAllProductSKU()).isTrue(); 
	 }
	 
	 @When("^The user adds product \"(.*?)\" into current order$")
	 public void The_user_adds_default_product_into_current_order(String productName) throws Throwable{
		 b2bCheckOutBasketPage.addProductIntoBasket(productName);
	 } 
	 
	 
	 @And("^The user enter current order from header$")
	 public void the_user_enter_current_order_from_header() throws Throwable{
		 b2bCheckOutBasketPage.enterDefaultAAHBasketOrderFromHeader();
	 }

	 
	 @Then("^The quantity of AAH mini cart is updated to \"(.*?)\"$")
	 public void the_quantity_of_AAH_mini_cart_is_updated_to(String quantity) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getAAHQuantity()).isEqualTo(quantity);
	 }
	 
	 @Then("^The quantity of ENTERPRISE mini cart is updated to \"(.*?)\"$")
	 public void the_quantity_of_ENTERPRISE_mini_cart_is_updated_to(String quantity) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getENTERPRISEQuantity()).isEqualTo(quantity);		 
	 }
	 
	 @When("^The user click Multiple Delivery Point link on basket page$")
	 public void the_user_click_Multiple_Delivery_Point_link_on_basket_page() throws Throwable {
		 b2bCheckOutBasketPage.onClickMultiDeliveryLink();
	 }

	 @Then("^The user is navigate to \"(.*?)\" page$")
	 public void the_user_is_navigate_to_page(String title) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getMultiDeliveryTitle()).isEqualTo(title);	
	 }
	 
	 
	 @When("^User close the mini basket popup$")
	 public void user_close_the_mini_basket_popup() throws Throwable{
		 b2bCheckOutBasketPage.closeMiniBasketPopup();
	 }
	 
	 @When("^User hover on \"(.*?)\" mini cart button$")
	 public void user_hover_on_mini_cart_button(String baskettype) throws Throwable {
		 b2bCheckOutBasketPage.onHoverOfMiniCart(baskettype);
	 }

	 @When("^User click Create New Basket button on mini cart$")
	 public void user_click_Create_New_Basket_button_on_mini_cart() throws Throwable {
		 b2bCheckOutBasketPage.onClickOfCreatOnMiniCart();
	 }
	 
	 @Then("^The column headers are as design$")
	 public void the_column_headers_are_as_design(List<String> columnheaderList) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getColumnHeaderList()).isEqualTo(columnheaderList);
	 }
	 
	 @Then("^The checkout account headers are displayed as per design$")
	 public void the_Account_hearders_are_as_design(List<String> accountheaderList) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getAccountHeaderList()).isEqualTo(accountheaderList);
	 }

	 @Then("^The account details are displayed with (\\d+) field$")
	 public void the_Account_details_are_correct(int accountDetailsSize) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getAccountDetailsList().size()).isEqualTo(accountDetailsSize);
	 }

	 @When("^The user click Cancel button on Multiple Delivery Point Selection page$")
	 public void the_user_click_Cancel_button_on_Multiple_Delivery_Point_Selection_page() throws Throwable {
		 b2bCheckOutBasketPage.onClickOfCancelBtn();
	 }
	 
	 @Then("^The user click on Edit button on basket view page$")
	 public void the_user_click_on_Edit_button_on_basket_view_page() throws Throwable {
		 b2bCheckOutBasketPage.onClickOfEditBtn();
	 }	 	 	 
	 
	 @Then("^The \"(.*?)\" title is displayed on Order Confirm Page$")
	 public void the_title_is_displayed_on_Order_Confirm_Page(String orderConfirmTitle) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getOrderConfirmTitle()).isEqualTo(orderConfirmTitle);
	 }
	 
	 @And("^User click view order reply on order confirm page$")
	 public void suer_click_view_order_reply_on_order_confirm_page() throws Throwable{
	     b2bCheckOutBasketPage.clickViewOrderReplyLink();
	 }

	 @Then("^The \"(.*?)\" button is displayed on Order Confirm Page$")
	 public void the_button_is_displayed_on_Order_Confirm_Page(String printConfirmText) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getPrintConfirmButtonText()).isEqualTo(printConfirmText);
	 }
	 
	 @When("^The click Continue button on Order Confirm Page$")
	 public void the_click_Continue_button_on_Order_Confirm_Page() throws Throwable {
		 b2bCheckOutBasketPage.onClickOfContinueBtn();
	 }
	 
	 @Then("^The product information in basket result is found in Order Confirm page$")
	 public void the_product_information_in_basket_result_is_found_in_Order_Confirm_page(Map<String, String> productInfo) throws Throwable {
		   assertThat(b2bCheckOutBasketPage.canProductFoundInOrderDetails(productInfo)).isTrue();
	 }
	 
	 @When("^The user clicks \"(.*?)\" bundle link$")
	 public void the_user_clicks_bundle_link(String bundleName) throws Throwable {
	     if(bundleName.startsWith("b2b.")){
	         bundleName=b2bCheckOutBasketPage.getProp(bundleName);
	     }
		 b2bCheckOutBasketPage.onClickOfBundleLink(bundleName);
	 }

	 @Then("^The user is on \"(.*?)\" bundle PDP$")
    public void the_user_is_on_bundle_PDP(String bundleName) throws Throwable {
        if (bundleName.startsWith("b2b.")) {
            bundleName = b2bCheckOutBasketPage.getProp(bundleName);
        }
        assertThat(b2bCheckOutBasketPage.getBundlePDPName()).isEqualTo(bundleName);
    }
	 
	 @Then("^The bundle name \"(.*?)\" is shown correctly$")
    public void the_bundle_name_is_shown_correctly(String bundleName) throws Throwable {
        if (bundleName.startsWith("b2b.")) {
            bundleName = b2bCheckOutBasketPage.getProp(bundleName);
        }
        assertThat(b2bCheckOutBasketPage.getBundleName()).isEqualTo(bundleName.toUpperCase());
    }

	 @Then("^The message \"(.*?)\" is shown on the grey panel$")
	 public void the_message_is_shown_on_the_grey_panel(String message) throws Throwable {
		   assertThat(b2bCheckOutBasketPage.getPanelMessage()).isEqualTo(message);
	 }

	 @Then("^The products in this bundle are listed correctly$")
	 public void the_products_in_this_bundle_are_listed_correctly(List<String> productList) throws Throwable {
//		 List<String> sortedList=productList.stream().sorted().collect(Collectors.toList());
		 assertThat(b2bCheckOutBasketPage.getBundleProductList()).hasSameElementsAs(productList);
	 }
	 
	 @Then("^The product detail table titles are listed correctly$")
	 public void the_product_detail_table_titles_are_listed_correctly(List<String> tableTitleList) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getTableTitleList()).isEqualTo(tableTitleList);
	 }
	 
	 @When("^The user select \"(.*?)\" from the \"(.*?)\" basket dropdown on the grey panel$")
	 public void the_user_select_from_the_basket_dropdown_on_the_grey_panel(String basketName, String basketType) throws Throwable {
		 if(basketName.startsWith("b2b.")){
			 b2bCheckOutBasketPage.selectBasketFromGreyPanel(b2bCheckOutBasketPage.getProp(basketName), basketType);
		 }
		 else
		 b2bCheckOutBasketPage.selectBasketFromGreyPanel(basketName, basketType);	 
	 }

	 @When("^The user clicks \"(.*?)\" on the grey panel$")
	 public void the_user_clicks_on_the_grey_panel(String addSelectedBtn) throws Throwable {
		 b2bCheckOutBasketPage.onClickOfAddToCartBtn(addSelectedBtn);
	 }
	 
	 @Then("^The products in this basket are listed correctly$")
	 public void the_products_in_this_basket_are_listed_correctly(List<String> productList) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getBasketProductList()).hasSameElementsAs(productList);
	 }

	 @Then("^The Order total is \"(.*?)\"$")
	 public void the_Order_total_is(String orderTotal) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getOrderTotal()).isEqualTo(orderTotal);
	 }
	 
	 @Then("^The basket dropdown titles are shown correctly on the grey panel$")
	 public void the_basket_dropdown_titles_are_shown_correctly_on_the_grey_panel(List<String> basketDropdownTitleList) throws Throwable {
		 assertThat(b2bCheckOutBasketPage.getBasketDropdownTitleList()).isEqualTo(basketDropdownTitleList);
	 }
	 
	 @Then("^The user checks on all products for AAH$")
	 public void the_user_checks_on_all_products_for_AAH() throws Throwable {
		 b2bCheckOutBasketPage.oncheckAllProductsForAAH();
	 }
	 
}
