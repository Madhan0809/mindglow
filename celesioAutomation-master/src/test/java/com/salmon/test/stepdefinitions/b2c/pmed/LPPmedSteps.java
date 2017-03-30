package com.salmon.test.stepdefinitions.b2c.pmed;
import com.salmon.test.pageobjects.b2c.pmed.LPPmedPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LPPmedSteps {
    private LPPmedPage lpPmedPage;

    public LPPmedSteps(LPPmedPage lpPmedPage) {
        this.lpPmedPage = lpPmedPage;
    }

    @When("^The user clicks Add to basket on PMED page$")
    public void the_user_clicks_Add_to_basket_on_PMED_page() throws Throwable {
        lpPmedPage.onClickOfAddToBasket();
    }

    @Then("^The WWHAM Page opens$")
    public void the_WWHAM_Page_opens() throws Throwable {
        assertThat(lpPmedPage.isWWHAMShown()).isTrue();
    }

    @Then("^The Page Tabs are displayed on PMED page$")
    public void the_Page_Tabs_are_displayed_on_PMED_page(List<String> pageTabsList) throws Throwable {
        assertThat(lpPmedPage.getPageTabsList()).isEqualTo(pageTabsList);
    }

    @Then("^The delivery options are displayed on PMED page$")
    public void the_delivery_options_are_displayed_on_PMED_page(List<String> deliveryOptionsList) throws Throwable {
        assertThat(lpPmedPage.getDeliveryOptionsList()).isEqualTo(deliveryOptionsList);
    }

    @Then("^The delivery texts are displayed on PMED page$")
    public void the_delivery_texts_are_displayed_on_PMED_page(List<String> deliveryTextList) throws Throwable {
        assertThat(lpPmedPage.getDeliveryTextList()).isEqualTo(deliveryTextList);
    }

    @Then("^The product name \"(.*?)\" is displayed on PMED page$")
    public void the_product_name_is_displayed_on_PMED_page(String productName) throws Throwable {
        assertThat(lpPmedPage.getProductName()).isEqualTo(productName);
    }

    @Then("^The short description \"(.*?)\" is displayed on PMED page$")
    public void the_short_description_is_displayed_on_PMED_page(String shortDescription) throws Throwable {
        assertThat(lpPmedPage.getShortDescription()).isEqualTo(shortDescription);
    }

    @Then("^The Offer Price \"(.*?)\" is displayed on PMED page$")
    public void the_Offer_Price_is_displayed_on_PMED_page(String offerPrice) throws Throwable {
        assertThat(lpPmedPage.getOfferPrice()).isEqualTo(offerPrice);
    }

    @Then("^The Was Save Price \"(.*?)\" is displayed on PMED page$")
    public void the_Was_Save_Price_is_displayed_on_PMED_page(String was_Save_Price) throws Throwable {
        assertThat(lpPmedPage.getWasSavePrice()).isEqualTo(was_Save_Price);
    }

    @Then("^The question title \"(.*?)\" is displayed on WWHAM page$")
    public void the_question_header_is_displayed_on_WWHAM_page(String title) throws Throwable {
        assertThat(lpPmedPage.getQuestionHeader()).isEqualTo(title);
    }

    @Then("^The Headers are displayed on WWHAM page$")
    public void the_Headers_are_displayed_on_WWHAM_page(List<String> headerList) throws Throwable {
        assertThat(lpPmedPage.getHeaderList()).isEqualTo(headerList);
    }

    @When("^The user clicks Back button on WWHAM page$")
    public void the_user_clicks_Back_button_on_WWHAM_page() throws Throwable {
        lpPmedPage.onClickOfBack();
    }

    @Then("^The questions are displayed on WWHAM page$")
    public void the_questions_are_displayed_on_WWHAM_page(List<String> questionList) throws Throwable {
        assertThat(lpPmedPage.getQuestionList()).isEqualTo(questionList);
    }

    @Given("^User enters all mandatory input fields and dropdown list from WWHAM page$")
    public void user_enters_all_mandatory_input_fields_and_dropdown_list_from_WWHAM_page() throws Throwable{
    	lpPmedPage.fulfillAllMandatoryFields();
    }
    
    
    @When("^The user selects \"(.*?)\" from the first dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_first_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion1(option);
    }

    @When("^The user selects \"(.*?)\" Years and \"(.*?)\" Month from the dropdown list on WWHAM Page$")
    public void the_user_selects_Years_and_Month_from_the_dropdown_list_on_WWHAM_Page(String year, String month) throws Throwable {
        lpPmedPage.onClickYear(year);
        lpPmedPage.onClickMonth(month);
    }

    @When("^The user selects \"(.*?)\" from the fourth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_fourth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion4(option);
    }

    @When("^The user selects \"(.*?)\" from the sixth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_sixth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion6(option);
    }

    @When("^The user checks on the last checkbox on WWHAM Page$")
    public void the_user_checks_on_the_last_checkbox_on_WWHAM_Page() throws Throwable {
        lpPmedPage.onClickCheckBox();
    }

    @When("^The user clicks Continue button on WWHAM Page$")
    public void the_user_clicks_Continue_button_on_WWHAM_Page() throws Throwable {
        lpPmedPage.onClickContinue();
    }

    @When("^The user selects \"(.*?)\" Day and \"(.*?)\" Month and \"(.*?)\" Year from the dropdown list on WWHAM Page$")
    public void the_user_selects_Day_and_Month_and_Year_from_the_dropdown_list_on_WWHAM_Page(String day, String month, String year) throws Throwable {
        lpPmedPage.onSelectDay(day);
        lpPmedPage.onSelectMonth(month);
        lpPmedPage.onSelectYear(year);
    }

    @When("^The user selects \"(.*?)\" from the eighth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_eighth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion8(option);
    }

    @When("^The user inputs the daytime contact number \"(.*?)\"$")
    public void the_user_inputs_the_daytime_contact_number(String number) throws Throwable {
        lpPmedPage.inputContactNumber(number);
    }

    @When("^User input height and weight for how long of sympotoms on WWHAM page$")
    public void user_input_years_and_month_for_how_long_of_the_sympotoms_on_WWHAM_page(Map<String,String>usingAliInfo) throws Throwable{
        lpPmedPage.inputPersonalInfoOfSymptom(usingAliInfo);
    }
    
    @When("^The user selects \"(.*?)\" Years and \"(.*?)\" Month for how long of the symptoms on WWHAM Page$")
    public void the_user_selects_Years_and_Month_for_how_long_of_the_symptoms_on_WWHAM_Page(String year, String month) throws Throwable {
        lpPmedPage.onSelectofYearMonthofSymptom(year, month);
    }

    @When("^The user selects \"(.*?)\" Years and \"(.*?)\" Month for the youngest intended user on WWHAM Page$")
    public void the_user_selects_Years_and_Month_for_the_youngest_intended_user_on_WWHAM_Page(String year, String month) throws Throwable {
        lpPmedPage.onSelectofYearMonthofYoungestUser(year, month);
    }

    @When("^The user selects \"(.*?)\" nails are affected on WWHAM Page$")
    public void the_user_selects_nails_are_affected_on_WWHAM_Page(String number) throws Throwable {
        lpPmedPage.onSelectofNails(number);
    }

    @When("^The user selects \"(.*?)\" that has been recommended by a doctor on WWHAM Page$")
    public void the_user_selects_that_has_been_recommended_by_a_doctor_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofRecommended(option);
    }

    @When("^The user selects \"(.*?)\" that it is a repeat purchase on WWHAM Page$")
    public void the_user_selects_that_it_is_a_repeat_purchase_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofRepeatPurchase(option);
    }

    @When("^The user selects \"(.*?)\" that the treatment is working on WWHAM Page$")
    public void the_user_selects_that_the_treatment_is_working_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofTreatment(option);
    }

    @When("^The user selects \"(.*?)\" that is the most like your nail on WWHAM Page$")
    public void the_user_selects_that_is_the_most_like_your_nail_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofMostLikeNail(option);
    }

    @When("^The user selects \"(.*?)\" that are affected on WWHAM Page$")
    public void the_user_selects_that_are_affected_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofPartAffected(option);
    }

    @When("^The user inputs \"(.*?)\" for medical conditions on WWHAM Page$")
    public void the_user_inputs_for_medical_conditions_on_WWHAM_Page(String information) throws Throwable {
        lpPmedPage.inputMedicalConditions(information);
    }

    @When("^The user selects \"(.*?)\" of gender from the dropdown list on WWHAM Page$")
    public void the_user_selects_of_gender_from_the_dropdown_list_on_WWHAM_Page(String gender) throws Throwable {
        lpPmedPage.onSelectGender(gender);
    }

    @When("^The user selects \"(.*?)\" of the shaved scalp from the dropdown list on WWHAM Page$")
    public void the_user_selects_of_the_shaved_scalp_from_the_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectIfShavedScalp(option);
    }

    @When("^The user inputs \"(.*?)\" for symptoms on WWHAM Page$")
    public void the_user_inputs_for_symptoms_on_WWHAM_Page(String symptoms) throws Throwable {
        lpPmedPage.inputSymptoms(symptoms);
    }

    @When("^The user selects \"(.*?)\" for taking any of the Anti-coagulants medication on WWHAM Page$")
    public void the_user_selects_for_taking_any_of_the_Anti_coagulants_medication_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofAntiCoagulants(option);
    }

    @When("^The user selects \"(.*?)\" for how many episodes the intended user suffers from on WWHAM Page$")
    public void the_user_selects_for_how_many_episodes_the_intended_user_suffers_from_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofEpisodeNumber(option);
    }

    @When("^The user selects \"(.*?)\" for how these symptoms affect your daily life on WWHAM Page$")
    public void the_user_selects_for_how_these_symptoms_affect_your_daily_life_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofHowSymptomsAffect(option);
    }

    @When("^The user selects \"(.*?)\" for whether the user suffers from unintentional weight loss on WWHAM Page$")
    public void the_user_selects_for_whether_the_user_suffers_from_unintentional_weight_loss_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onSelectofWeightLoss(option);
    }

    @When("^The user selects \"(.*?)\" from the nineth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_nineth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion9(option);
    }

    @When("^The user inputs \"(.*?)\" for other medical conditions on WWHAM Page$")
    public void the_user_inputs_for_other_medical_conditions_on_WWHAM_Page(String conditions) throws Throwable {
        lpPmedPage.inputOtherConditions(conditions);
    }

    @Then("^The error messages are listed on WWHAM Page$")
    public void the_error_messages_are_listed_on_WWHAM_Page(List<String> errorList) throws Throwable {
        assertThat(lpPmedPage.getErrorList()).isEqualTo(errorList);
    }

    @When("^The user inputs \"(.*?)\" for travel destination on WWHAM Page$")
    public void the_user_inputs_for_travel_destination_on_WWHAM_Page(String travelDestination) throws Throwable {
        lpPmedPage.inputTravelDestination(travelDestination);
    }

    @When("^The user selects \"(.*?)\" Day \"(.*?)\" Month \"(.*?)\" Year for travel date on WWHAM Page$")
    public void the_user_selects_Day_Month_Year_for_travel_date_on_WWHAM_Page(String day, String month, String year) throws Throwable {
        lpPmedPage.onSelectofTravelDate(day, month, year);
    }

    @When("^The user selects \"(.*?)\" Years \"(.*?)\" Months and \"(.*?)\" Days for travel duration on WWHAM Page$")
    public void the_user_selects_Years_Months_and_Days_for_travel_duration_on_WWHAM_Page(String year, String month, String day) throws Throwable {
        lpPmedPage.onSelectofDuration(year, month, day);
    }

    @When("^The user selects \"(.*?)\" from the seventh dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_seventh_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion7(option);
    }

    @When("^The user selects \"(.*?)\" from the tenth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_tenth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion10(option);
    }

    @When("^The user selects \"(.*?)\" from the twelveth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_twelveth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion12(option);
    }

    @When("^The user selects \"(.*?)\" from the thirteenth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_thirteenth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion13(option);
    }

    @When("^The user inputs \"(.*?)\" metre and \"(.*?)\" centimeter as height on WWHAM Page$")
    public void the_user_inputs_metre_and_centimeter_as_height_on_WWHAM_Page(String metre, String centimeter) throws Throwable {
        lpPmedPage.inputHeight(metre, centimeter);
    }

    @When("^The user inputs \"(.*?)\" kilogram as weight on WWHAM Page$")
    public void the_user_inputs_kilogram_as_weight_on_WWHAM_Page(String kilogram) throws Throwable {
        lpPmedPage.inputWeight(kilogram);
    }

    @When("^The user selects \"(.*?)\" Day \"(.*?)\" Month \"(.*?)\" Year for the birthday of youngest user on WWHAM Page$")
    public void the_user_selects_Day_Month_Year_for_the_birthday_of_youngest_user_on_WWHAM_Page(String day, String month, String year) throws Throwable {
        lpPmedPage.onSelectofYoungestBirthday(day, month, year);
    }

    @When("^The user selects \"(.*?)\" from the third dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_third_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion3(option);
    }

    @Then("^The user selects \"(.*?)\" from the second dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_second_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion2(option);
    }

    @Then("^The user selects \"(.*?)\" from the fifth dropdown list on WWHAM Page$")
    public void the_user_selects_from_the_fifth_dropdown_list_on_WWHAM_Page(String option) throws Throwable {
        lpPmedPage.onClickQustion5(option);
    }

    @Then("^The user selects \"(.*?)\" Day \"(.*?)\" Month \"(.*?)\" Year for the birthday on WWHAM Page$")
    public void the_user_selects_Day_Month_Year_for_the_birthday_on_WWHAM_Page(String day, String month, String year) throws Throwable {
        lpPmedPage.onSelectofBirthday(day, month, year);
    }

    @Then("^The user ticks the checkbox of the second question on WWHAM Page$")
    public void the_user_ticks_the_checkbox_of_the_second_question_on_WWHAM_Page() throws Throwable {
        lpPmedPage.onCheckQuestion2();
    }

    @When("^The user inputs \"(.*?)\" in the second question on WWHAM Page$")
    public void the_user_inputs_in_the_second_question_on_WWHAM_Page(String answers) throws Throwable {
        lpPmedPage.inputQuestion2(answers);
    }
}


