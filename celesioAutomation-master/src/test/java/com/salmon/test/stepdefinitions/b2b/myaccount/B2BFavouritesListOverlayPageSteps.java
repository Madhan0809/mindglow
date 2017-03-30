package com.salmon.test.stepdefinitions.b2b.myaccount;
import com.salmon.test.pageobjects.b2b.myaccount.B2BFavouritesListOverlayPage;

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
 * Created by April on 16/03/2016.
 */
public class B2BFavouritesListOverlayPageSteps {

    private static final Logger LOG = LoggerFactory.getLogger(B2BFavouritesListOverlayPageSteps.class);

    private B2BFavouritesListOverlayPage b2bFavouritesListOverlayPage;
            
    public B2BFavouritesListOverlayPageSteps(B2BFavouritesListOverlayPage b2bFavouritesListOverlayPage) {
        this.b2bFavouritesListOverlayPage = b2bFavouritesListOverlayPage;
    }
	    
    @Then("^The page title \"(.*?)\" is displayed on Favourites List Overlay page$")
    public void the_page_title_is_displayed_on_Favourites_List_Overlay_page(String pageTitle) throws Throwable {
		 assertThat(b2bFavouritesListOverlayPage.getPageTitle()).isEqualTo(pageTitle);
    }

    @Then("^The text label \"(.*?)\" is displayed on Favourites List Overlay page$")
    public void the_text_label_is_displayed_on_Favourites_List_Overlay_page(String textLabel) throws Throwable {
		 assertThat(b2bFavouritesListOverlayPage.getTextLabel()).isEqualTo(textLabel);
    }

    @Then("^The text \"(.*?)\" is displayed on Favourites List Overlay page$")
    public void the_text_is_displayed_on_Favourites_List_Overlay_page(String text) throws Throwable {
		 assertThat(b2bFavouritesListOverlayPage.getText()).isEqualTo(text);
    }

    @Then("^The button text \"(.*?)\" is displayed on Favourites List Overlay page$")
    public void the_button_text_is_displayed_on_Favourites_List_Overlay_page(String buttonText) throws Throwable {
		 assertThat(b2bFavouritesListOverlayPage.getButtonText()).isEqualTo(buttonText);
    }
     
}
