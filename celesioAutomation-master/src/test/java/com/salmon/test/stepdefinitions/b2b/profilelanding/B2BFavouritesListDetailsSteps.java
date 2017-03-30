/**
 * 
 */
package com.salmon.test.stepdefinitions.b2b.profilelanding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BFavouriteListDetailsPage;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BFavouriteListPage;

/**
 * @author tzhao
 *
 */
public class B2BFavouritesListDetailsSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BFavouritesListDetailsSteps.class);
	
	private B2BFavouriteListDetailsPage b2BFavouriteListDetailsPage;
	private B2BFavouriteListPage b2BFavouriteListPage;
	private String newDynamicFavList;
	
	public B2BFavouritesListDetailsSteps(B2BFavouriteListPage favouriteList,B2BFavouriteListDetailsPage favouriteListDetails){
		b2BFavouriteListDetailsPage=favouriteListDetails;
		b2BFavouriteListPage=favouriteList;
	}
	
	@Given("^User clear all product content in the favourite details table$")
	public void user_clear_all_product_content_in_the_favourite_details_table() throws Throwable{
		if(!b2BFavouriteListDetailsPage.isEmptyTableInFavouriteDetailsTable()){
			LOG.info("It's not an empty table. Now remove all products to clean test data.");
			b2BFavouriteListDetailsPage.removeAllProductInTable();
		}else{
		    LOG.info("It's already an empty table. Skip the step of cleanning favourite list.");
		}
	}
	
	
	@Then("^Favourite details header of \"(.*?)\" is displayed correctly$")
	public void favourite_details_info_is_displayed_correctly(String favListName) throws Throwable{
		if(favListName.startsWith("favourite.")){
			favListName=b2BFavouriteListDetailsPage.getB2bProp(favListName);
		}
		assertThat(b2BFavouriteListDetailsPage.getFavNameInHeader()).isEqualTo(favListName.toUpperCase());
	}
	
	@When("^User enter product code \"(.*?)\" and quantity (\\d+) to add into favourite list$")
	public void user_enter_product_code_and_quantity_to_add_into_favourite_list(String prodCode, int qty) throws Throwable{
		if(prodCode.startsWith("quick.order.")){
			prodCode=b2BFavouriteListDetailsPage.getProp(prodCode);
		}
		b2BFavouriteListDetailsPage.addBySku(prodCode, qty);	
	}
	
	@When("^User click on add to current order button$")
	public void user_click_on_add_to_current_order_button() throws Throwable{
	    b2BFavouriteListDetailsPage.clickAddToCurrentOrderButton();
	}
	
	@When("^User update the quantity of product \"(.*?)\" to (\\d+) and apply change$")
	public void user_update_the_quantity_of_product_to_value_and_apply_change(String prodDescription, int qty) throws Throwable{
	    if(prodDescription.startsWith("b2b.")){
            prodDescription=b2BFavouriteListDetailsPage.getProp(prodDescription);
        }
	    b2BFavouriteListDetailsPage.updateQuantityForProduct(prodDescription, qty);
	}
	
	@Then("^Check the value of quantity for \"(.*?)\" is (\\d+)$")
	public void check_the_value_of_quantity_for_product_is(String prodDescription, int qty) throws Throwable{
	    if(prodDescription.startsWith("b2b.")){
            prodDescription=b2BFavouriteListDetailsPage.getProp(prodDescription);
        }
	    assertThat(b2BFavouriteListDetailsPage.getQuantityForProduct(prodDescription)).isEqualTo(qty);
	}
	
	@Then("^Check product \"(.*?)\" is added into favourite details table$")
	public void check_favourite_details_info_can_be_found_in_the_table(String prodDescription) throws Throwable{
		if(prodDescription.startsWith("b2b.")){
		    prodDescription=b2BFavouriteListDetailsPage.getProp(prodDescription);
		}
	    assertThat(b2BFavouriteListDetailsPage.getProductsDescription()).contains(prodDescription);
	} 
	
	@Then("^The creating message info \"(.*?)\" is displayed in header$")
	public void the_creating_message_info_is_displayed_in_header(String msgInfo) throws Throwable{
		assertThat(b2BFavouriteListDetailsPage.getMsgPopupInHeader()).isEqualTo(msgInfo);
	}
	
	@Then("^Check favourite details table content header is displayed as expected$")
	public void check_table_content_header_is_displayed_as_expected(List<String> tableColumns) throws Throwable{
		assertThat(b2BFavouriteListDetailsPage.getTableHeaders()).isEqualTo(tableColumns);
	}
	
	@When("^User update favourite list as dynamic name \"(.*?)\" and apply the change$")
	public void user_update_favourite_list_name_as_and_apply_the_change(String newFavName) throws Throwable{
		this.newDynamicFavList=newFavName+RandomGenerator.random(5, PermittedCharacters.NUMERIC);
		b2BFavouriteListDetailsPage.updateFavListName(newDynamicFavList);
	}
	
	@Then("^Check updated favourite list is existed in favourite list page$")
	public void check_updated_favourite_list_is_existed_in_favourite_list_page() throws Throwable{
		assertThat(b2BFavouriteListPage.isFavoriteListExistInTable(this.newDynamicFavList)).isTrue();
	}
	
	@And("^Data cleaning to delete the new updated favourite list$")
	public void data_cleaning_to_delete_the_new_updated_favourite_list() throws Throwable{
		b2BFavouriteListPage.clickActionMenuForFavList("Delete list", this.newDynamicFavList);
		assertThat(b2BFavouriteListPage.isFavoriteListExistInTable(newDynamicFavList)).isFalse();
	}
	
	@When("^User click on breadCrumb link \"(.*?)\" in favourite details page$")
	public void user_click_on_breadCrumb_link_in_favourite_details_page(String linkName) throws Throwable{
		b2BFavouriteListDetailsPage.clickOnBreadCrumb(linkName);
	}
	
	@When("^Add selected item with product description \"(.*?)\" into current basket$")
	public void add_item_with_product_code_into_current_basket(String prodDescription) throws Throwable{
		b2BFavouriteListDetailsPage.addSelectedToCurrentOrder(prodDescription);
		b2BFavouriteListPage.dismissAllNotification();
	}
	
	@And("^Click Go to favourites list in the popup header$")
	public void click_go_to_favourite_list_in_the_popup_header() throws Throwable{
		b2BFavouriteListDetailsPage.clickGoToCurrentOrderButton();
	}
	
	@When("^User make the favourite list with \"(.*?)\" status$")
	public void user_share_favourite_list_with_public_organization(String status) throws Throwable{
		b2BFavouriteListDetailsPage.clickOnStatusLink(status);
	}
	
}
