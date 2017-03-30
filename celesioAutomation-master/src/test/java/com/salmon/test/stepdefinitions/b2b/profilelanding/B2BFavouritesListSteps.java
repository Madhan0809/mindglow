/**
 * 
 */
package com.salmon.test.stepdefinitions.b2b.profilelanding;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import com.salmon.test.enums.PermittedCharacters;
import com.salmon.test.framework.helpers.utils.RandomGenerator;
import com.salmon.test.pageobjects.b2b.pdp.B2BProductDetailsPage;
import com.salmon.test.pageobjects.b2b.profilelanding.B2BFavouriteListPage;

/**
 * @author tzhao
 *
 */
public class B2BFavouritesListSteps {
	private static final Logger LOG = LoggerFactory.getLogger(B2BFavouritesListSteps.class);
	
	private B2BFavouriteListPage b2BFavouriteListPage;
	private static String newDynamicFavList;
	private B2BProductDetailsPage b2BProductDetailsPage;
	
	public B2BFavouritesListSteps(B2BFavouriteListPage favouriteListPage,B2BProductDetailsPage productDetailsPage){
		b2BFavouriteListPage=favouriteListPage;
		b2BProductDetailsPage=productDetailsPage;
	}
	
	@Given("^The page header of \"(.*?)\" display in correct format$")
	public void the_page_header_display_in_correct_format(String header) throws Throwable{
		assertThat(b2BFavouriteListPage.getFavouriteListPageHeader()).isEqualTo(header.toUpperCase());
	}
	
	@Then("^Check \"(.*?)\" table is displayed in correct format$")
	public void Check_favourite_list_table_is_displayed_in_correct_format(String tableName, List<String> tableHeader) throws Throwable{
		assertThat(b2BFavouriteListPage.getYourFavTableHeader(tableName)).isEqualTo(tableHeader);
	}
	
	@When("^User click on tab \"(.*?)\" in favourite list page$")
	public void user_click_on_tab_in_favourite_list_page(String tabName) throws Throwable{
		b2BFavouriteListPage.clickOnTab(tabName);
	}
	
	@When("^User create a new dynamic favourite list \"(.*?)\"$")
	public void user_create_a_new_favourite_list(String favListName) throws Throwable{
		if(favListName.startsWith("favourite.list")){
			favListName=b2BFavouriteListPage.getB2bProp(favListName);
		}
		newDynamicFavList=favListName+RandomGenerator.random(5, PermittedCharacters.NUMERIC);
//		b2BFavouriteListPage.dismissAllNotification();
		b2BFavouriteListPage.createNewFavouriteList(newDynamicFavList);
	}
	
	@Given("^User setup a default favourite list \"(.*?)\"$")
	public void user_setup_a_default_favourite_list(String defaultList) throws Throwable{
		if(defaultList.startsWith("favourite.default")){
			defaultList=b2BFavouriteListPage.getB2bProp("favourite.default.list");
		}else if(defaultList.startsWith("favourite.")){
		    defaultList=b2BFavouriteListPage.getB2bProp(defaultList);
		}
		if(b2BFavouriteListPage.isFavoriteListExistInTable(defaultList)){
			LOG.info("The default favourite list is already setup: "+defaultList);
		} else{
			LOG.info("Now create a default favourite list: "+defaultList);
			b2BFavouriteListPage.createNewFavouriteList(defaultList);
		}
	}	 
	
	@Then("^Check a new favourite list should be created$")
	public void check_a_new_favourite_list_should_be_created() throws Throwable{
		assertThat(b2BFavouriteListPage.isFavoriteListExistInTable(newDynamicFavList)).isTrue();
	}
	
	@When("^User click on \"(.*?)\" under action menu for favourite list \"(.*?)\"$")
	public void user_click_on_under_action_menu_for_favourite_list(String action, String favList) throws Throwable{
		if(favList.startsWith("favourite.list")){
			favList=newDynamicFavList;
		}else if(favList.startsWith("plp.random.")){
			favList=b2BProductDetailsPage.newRandomList;
		}
		b2BFavouriteListPage.clickActionMenuForFavList(action, favList);
	}
	
	@Then("^The popup of order has been updated successfully display in header$")
	public void the_popup_of_order_has_ben_updated_display_in_header() throws Throwable{
		assertThat(b2BFavouriteListPage.getPopupInfo()).isEqualTo(b2BFavouriteListPage.getB2bProp("favourite.update.order.infoMsg"));
	}
	
	
	@Then("^Check a duplicate list for \"(.*?)\" is created$")
	public void check_a_duplicate_list_is_created(String originalList) throws Throwable{
		assertThat(b2BFavouriteListPage.isDuplicateListIsFound(originalList)).isTrue();
	}
	
	@And("^Data cleaning for a newly duplicating basket \"(.*?)\"$")
	public void data_cleaning_for_a_newly_duplicating_basket(String listToRemove) throws Throwable{
		b2BFavouriteListPage.removeDuplicateList(listToRemove);	
	}
	
	@Then("^Check favourite list \"(.*?)\" is existed in current table$")
	public void check_favourite_list_is_exited_in_current_table(String favListName) throws Throwable{
		if(favListName.startsWith("favourite.list")){
			favListName=newDynamicFavList;
		}
		assertThat(b2BFavouriteListPage.isFavoriteListExistInTable(favListName)).isTrue();
	}
	
	@And("^Check the favourite list \"(.*?)\" is not found in current table$")
	public void check_the_favourite_list_is_not_found_in_current_table(String favListName) throws Throwable{
		if(favListName.startsWith("favourite.list")){
			favListName=newDynamicFavList;
		}
		assertThat(b2BFavouriteListPage.isFavoriteListExistInTable(favListName)).isFalse();
	}
	
	@When("^User click on details for favourite list \"(.*?)\" in favourite list$")
	public void user_click_on_details_for_favourite_list_in_favourite_list(String favListName) throws Throwable{
		b2BFavouriteListPage.clickDetailsButtonForFavList(favListName);	
	}
	
	@When("^User click on name of \"(.*?)\" in favourite list page$")
	public void user_click_on_name_in_favourite_list(String favListName) throws Throwable{
		if(favListName.startsWith("favourite.")){
			favListName=b2BFavouriteListPage.getB2bProp(favListName);
		}
		else if(favListName.startsWith("dynamic.favourite")){
			favListName=newDynamicFavList;
		}	
		b2BFavouriteListPage.dismissAllNotification();
		b2BFavouriteListPage.clickNameForFavList(favListName);
	}
	
	
}
