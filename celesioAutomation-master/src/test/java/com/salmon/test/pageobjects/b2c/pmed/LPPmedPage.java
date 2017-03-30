package com.salmon.test.pageobjects.b2c.pmed;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LPPmedPage extends PageObject {
	private static final Logger LOG = LoggerFactory.getLogger(LPPmedPage.class);
	
    private By addToBasket = By.cssSelector(".btnAction.wwhamForm");
    private By wwhamTitle = By.cssSelector("#questions h1");
    private By pageTabs = By.cssSelector("#productPageTabs .contentRecommendationWidget div");
//    private By deliveryOptions = By.cssSelector(".deliveryOptions.panel>ul>li>span>strong");
    private By deliveryOptions=By.cssSelector("div[data-slot-id]>div[class$='deliveryOptions panel'] strong");
    private By deliveryText=By.cssSelector("div[data-slot-id]>div[class$='deliveryOptions panel'] .deliveryMoreText");
//    private By deliveryText = By.cssSelector(".deliveryMoreText");
    private By header = By.cssSelector(".patientHeader h2");
    private By productName = By.cssSelector(".productName>h1");
    private By shortDescription = By.cssSelector(".product_text>p");
    private By offer_Price = By.cssSelector(".productPrice>input");
    private By was_Save_Price = By.cssSelector(".wasPrice");
    private By questionHeader = By.cssSelector(".questionHeader h1");
    //    private By back = By.cssSelector(".btnQuaternary");
    private By back = By.cssSelector(".btnGrey");
    private By questions = By.cssSelector("#questions>div>div>label");
    private By answer_1 = By.cssSelector("#answer1");
    private By answer_1_1 = By.cssSelector("#answer1-1");
    private By answer_1_2 = By.cssSelector("#answer1-2");
    private By answer_1_3 = By.cssSelector("#answer1-3");
    private By answer_2 = By.cssSelector("#answer2");
    private By answer_2_1 = By.cssSelector("#answer2-1");
    private By answer_2_2 = By.cssSelector("#answer2-2");
    private By answer_2_3 = By.cssSelector("#answer2-3");
    private By answer_3 = By.cssSelector("#answer3");
    private By answer_3_1 = By.cssSelector("#answer3-1");
    private By answer_3_2 = By.cssSelector("#answer3-2");
    private By answer_3_3 = By.cssSelector("#answer3-3");
    private By answer_4 = By.cssSelector("#answer4");
    private By answer_4_1 = By.cssSelector("#answer4-1");
    private By answer_4_2 = By.cssSelector("#answer4-2");
    private By answer_5 = By.cssSelector("#answer5");
    private By answer_5_1 = By.cssSelector("#answer5-1");
    private By answer_5_2 = By.cssSelector("#answer5-2");
    private By answer_5_3 = By.cssSelector("#answer5-3");
    private By answer_6 = By.cssSelector("#answer6");
    private By answer_6_1 = By.cssSelector("#answer6-1");
    private By answer_6_2 = By.cssSelector("#answer6-2");
    private By answer_6_3 = By.cssSelector("#answer6-3");
    private By answer_7 = By.cssSelector("#answer7");
    private By answer_8 = By.cssSelector("#answer8");
    private By answer_8_1 = By.cssSelector("#answer8-1");
    private By answer_8_2 = By.cssSelector("#answer8-2");
    private By answer_9 = By.cssSelector("#answer9");
    private By answer_10 = By.cssSelector("#answer10");
    private By checkbox = By.cssSelector("#answerLast");
    private By answer_11 = By.cssSelector("#answer11");
    private By answer_12 = By.cssSelector("#answer12");
    private By answer_13 = By.cssSelector("#answer13");
    private By answer_15 = By.cssSelector("#answer15");
    private By optionAnswers=By.cssSelector("input[id^='answer14-']");
    private By mostlikeNail_A = By.cssSelector("#answer14-1");
    private By mostlikeNail_B = By.cssSelector("#answer14-2");
    private By mostlikeNail_C = By.cssSelector("#answer14-3");
    private By mostlikeNail_D = By.cssSelector("#answer14-4");
    private By continues = By.cssSelector("#questionSubmit");
    private By errors = By.cssSelector(".error");

    private By questionsSection=By.cssSelector("section#questions");
    private By allMandatoryInputFields=By.cssSelector("select[id^='answer'],textarea[id^='answer']");
    
    public void onClickOfAddToBasket() {
    	waitForExpectedElement(addToBasket).click();
    }

    public boolean isWWHAMShown() {
        waitForExpectedElement(wwhamTitle);
        return getWebDriver().findElement(wwhamTitle).getText().equals("Questions from your Pharmacist");
    }

    public List<String> getPageTabsList() {
        return getWebDriver().findElements(pageTabs).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getDeliveryOptionsList() {
    	int size=getWebDriver().findElements(deliveryOptions).size();
        return getWebDriver().findElements(deliveryOptions).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getDeliveryTextList() {
        return getWebDriver().findElements(deliveryText).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> getHeaderList() {
    	return getWebDriver().findElements(header).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public String getProductName() {
        return getWebDriver().findElement(productName).getText();
    }

    public String getShortDescription() {
        return getWebDriver().findElement(shortDescription).getText();
    }

    public String getOfferPrice() {
        return getWebDriver().findElement(offer_Price).getAttribute("value");
    }

    public String getWasSavePrice() {
        return getWebDriver().findElement(was_Save_Price).getText();
    }

    public String getQuestionHeader() {
        return getWebDriver().findElement(questionHeader).getText();
    }

    public void onClickOfBack() {
        getWebDriver().findElement(back).click();
    }

    public List<String> getQuestionList() {
        List<WebElement> questionElementsList = getWebDriver().findElements(questions);
        return questionElementsList.stream().filter(pmed -> pmed.isDisplayed()).map(WebElement::getText).collect(Collectors.toList());
    }

    public void fulfillAllMandatoryFields(){
        waitForExpectedElement(questionsSection);
    	List<WebElement> inputFields=getWebDriver().findElements(allMandatoryInputFields).stream().filter(e->e.isDisplayed()).collect(Collectors.toList());
    	for(WebElement ipt: inputFields){
    		if(ipt.getTagName().equals("select")){
    			Select selector=new Select(ipt);  			
    			selector.selectByIndex(2);
    		} else if(ipt.getTagName().equals("textarea")){
    			ipt.clear();
    			ipt.sendKeys(getB2cProp("wwham.conditions.text"));
    		} else{
    			LOG.error("The input field is not found.");
    		}
    	}
    }
    
    
    public void onClickQustion1(String option) {
        Select answer1 = new Select(getWebDriver().findElement(answer_1));
        switch (option) {
            case "Yes":
                answer1.selectByVisibleText("Yes");
                break;
            case "No: Self and others":
                answer1.selectByVisibleText("No: Self and others");
                break;
        }
    }

    public void onClickYear(String year) {
        Select years = new Select(getWebDriver().findElement(answer_3_1));
        switch (year) {
            case "0":
                years.selectByVisibleText("0");
                break;
            case "10":
                years.selectByVisibleText("10");
                break;
            case "18":
                years.selectByVisibleText("18");
                break;
            case "28":
                years.selectByVisibleText("28");
                break;
            case "70":
                years.selectByVisibleText("70");
                break;
        }
    }

    public void onClickMonth(String month) {
        Select months = new Select(getWebDriver().findElement(answer_3_2));
        switch (month) {
            case "0":
                months.selectByVisibleText("0");
                break;
            case "6":
                months.selectByVisibleText("6");
                break;
        }
    }

    public void onClickQustion4(String option) {
        Select answer4 = new Select(getWebDriver().findElement(answer_4));
        switch (option) {
            case "Yes":
                answer4.selectByVisibleText("Yes");
                break;
            case "No":
                answer4.selectByVisibleText("No");
                break;
        }
    }

    public void onClickQustion6(String option) {
        Select answer6 = new Select(getWebDriver().findElement(answer_6));
        switch (option) {
            case "Yes":
                answer6.selectByVisibleText("Yes");
                break;
            case "No":
                answer6.selectByVisibleText("No");
                break;
        }
    }
    
    public void onClickCheckBox() {
        jsClick(checkbox);
    }

    public void onClickContinue() {
//        this.waitForElementDisplayedAndClickable(continues).click();
    	jsClick(continues);
    }

    private void jsClick(By by){
    	((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(by));
		((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].click()", waitForExpectedElement(by));
	}

    
    public void onSelectDay(String day) {
        Select days = new Select(getWebDriver().findElement(answer_3_1));
        switch (day) {
            case "1":
                days.selectByVisibleText("1");
                break;
            case "2":
                days.selectByVisibleText("2");
                break;
        }
    }

    public void onSelectMonth(String month) {
        Select months = new Select(getWebDriver().findElement(answer_3_2));
        switch (month) {
            case "1":
                months.selectByVisibleText("1");
                break;
            case "2":
                months.selectByVisibleText("2");
                break;
        }
    }

    public void onSelectYear(String year) {
        Select years = new Select(getWebDriver().findElement(answer_3_3));
        switch (year) {
            case "1999":
                years.selectByVisibleText("1999");
                break;
            case "1980":
                years.selectByVisibleText("1980");
                break;
        }
    }

    public void onClickQustion8(String option) {
        Select answer8 = new Select(getWebDriver().findElement(answer_8));
        switch (option) {
            case "Yes":
                answer8.selectByVisibleText("Yes");
                break;
            case "No":
                answer8.selectByVisibleText("No");
                break;
        }
    }

    public void inputContactNumber(String number) {
        getWebDriver().findElement(answer_11).clear();
        getWebDriver().findElement(answer_11).sendKeys(number);
    }

    public void inputPersonalInfoOfSymptom(Map<String, String> personalInfo) {
        waitForExpectedElement(answer_4_1).clear();
        getWebDriver().findElement(answer_4_1).sendKeys(personalInfo.get("Meter"));
        getWebDriver().findElement(answer_4_2).clear();
        getWebDriver().findElement(answer_4_2).sendKeys(personalInfo.get("CentiMeter"));
        getWebDriver().findElement(answer_5_1).clear();
        getWebDriver().findElement(answer_5_1).sendKeys(personalInfo.get("Kg"));
    }
    
    public void onSelectofYearMonthofSymptom(String year, String month) {
        Select years = new Select(getWebDriver().findElement(answer_4_1));
        Select months = new Select(getWebDriver().findElement(answer_4_2));
        switch (year) {
            case "0":
                years.selectByVisibleText("0");
                break;
            case "1":
                years.selectByVisibleText("1");
                break;
            case "2":
                years.selectByVisibleText("2");
                break;
        }
        switch (month) {
            case "1":
                months.selectByVisibleText("1");
                break;
            case "0":
                months.selectByVisibleText("0");
                break;
        }
    }

    public void onSelectofYearMonthofYoungestUser(String year, String month) {
        Select years = new Select(getWebDriver().findElement(answer_5_1));
        Select months = new Select(getWebDriver().findElement(answer_5_2));
        switch (year) {
            case "0":
                years.selectByVisibleText("0");
                break;
            case "8":
                years.selectByVisibleText("8");
                break;
            case "18":
                years.selectByVisibleText("18");
                break;
            case "28":
                years.selectByVisibleText("28");
                break;
        }
        switch (month) {
            case "0":
                months.selectByVisibleText("0");
                break;
            case "1":
                months.selectByVisibleText("1");
                break;
            case "6":
                months.selectByVisibleText("6");
                break;
        }
    }

    public void onSelectofNails(String number) {
        Select nails = new Select(getWebDriver().findElement(answer_10));
        switch (number) {
            case "1":
                nails.selectByVisibleText("1");
                break;
            case "2":
                nails.selectByVisibleText("2");
                break;
            case "3":
                nails.selectByVisibleText("3");
                break;
            case "4":
                nails.selectByVisibleText("4");
                break;
            case "5":
                nails.selectByVisibleText("5");
                break;
            case "6":
                nails.selectByVisibleText("6");
                break;
            case "7":
                nails.selectByVisibleText("7");
                break;
            case "8":
                nails.selectByVisibleText("8");
                break;
            case "9":
                nails.selectByVisibleText("9");
                break;
            case "10":
                nails.selectByVisibleText("10");
                break;
        }
    }

    public void onSelectofRecommended(String option) {
        Select recommended = new Select(getWebDriver().findElement(answer_11));
        switch (option) {
            case "Yes":
                recommended.selectByVisibleText("Yes");
                break;
            case "No":
                recommended.selectByVisibleText("No");
                break;
        }
    }

    public void onSelectofRepeatPurchase(String option) {
        Select repeatPurchase = new Select(getWebDriver().findElement(answer_12));
        switch (option) {
            case "Yes":
                repeatPurchase.selectByVisibleText("Yes");
                break;
            case "No":
                repeatPurchase.selectByVisibleText("No");
                break;
        }
    }

    public void onSelectofTreatment(String option) {
        Select treatment = new Select(getWebDriver().findElement(answer_13));
        switch (option) {
            case "Yes":
                treatment.selectByVisibleText("Yes");
                break;
            case "No":
                treatment.selectByVisibleText("No");
                break;
        }
    }

    public void onSelectofPartAffected(String option) {
        Select partAffected = new Select(getWebDriver().findElement(answer_15));
        switch (option) {
            case "the entire nail":
                partAffected.selectByVisibleText("the entire nail");
                break;
            case "the top of the nail":
                partAffected.selectByVisibleText("the top of the nail");
                break;
            case "the left side of the nail":
                partAffected.selectByVisibleText("the left side of the nail");
                break;
            case "the right side of the nail":
                partAffected.selectByVisibleText("the right side of the nail");
                break;
            case "the base of the nail":
                partAffected.selectByVisibleText("the base of the nail");
                break;
        }
    }

    public void onSelectofMostLikeNail(String option) {
    	((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(optionAnswers));
    	((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -100)", ""); 
    	waitForExpectedElement(continues);
        switch (option) {
            case "A":
                waitForExpectedElement(mostlikeNail_A).click();
                break;
            case "B":
            	waitForExpectedElement(mostlikeNail_B).click();
                break;
            case "C":
            	waitForExpectedElement(mostlikeNail_C).click();
                break;
            case "D":
            	waitForExpectedElement(mostlikeNail_D).click();
                break;
        }
    }

    public void inputMedicalConditions(String information) {
        getWebDriver().findElement(answer_3).sendKeys(information);
    }

    public void onSelectGender(String gender) {
        Select gend = new Select(getWebDriver().findElement(answer_4));
        switch (gender) {
            case "Male":
                gend.selectByVisibleText("Male");
                break;
            case "Female":
                gend.selectByVisibleText("Female");
                break;
        }
    }

    public void onSelectIfShavedScalp(String option) {
        Select shavedScalp = new Select(getWebDriver().findElement(answer_5));
        switch (option) {
            case "Yes":
                shavedScalp.selectByVisibleText("Yes");
                break;
            case "No":
                shavedScalp.selectByVisibleText("No");
                break;
        }
    }

    public void inputSymptoms(String symptoms) {
        getWebDriver().findElement(answer_4).sendKeys(symptoms);
    }

    public void onSelectofAntiCoagulants(String option) {
        switch (option) {
            case "Yes":
                getWebDriver().findElement(answer_5_1).click();
                break;
            case "No":
                getWebDriver().findElement(answer_5_2).click();
                break;
        }
    }

    public void onSelectofEpisodeNumber(String option) {
        Select episodeNumber = new Select(getWebDriver().findElement(answer_6));
        switch (option) {
            case "None":
                episodeNumber.selectByVisibleText("None");
                break;
            case "1":
                episodeNumber.selectByVisibleText("1");
                break;
            case "2 or more":
                episodeNumber.selectByVisibleText("2 or more");
                break;
        }
    }

    public void onSelectofHowSymptomsAffect(String option) {
        Select symptomsAffect = new Select(getWebDriver().findElement(answer_7));
        switch (option) {
            case "Rarely":
                symptomsAffect.selectByVisibleText("Rarely");
                break;
            case "Occassionally":
                symptomsAffect.selectByVisibleText("Occassionally");
                break;
            case "Constantly":
                symptomsAffect.selectByVisibleText("Constantly");
                break;
            case "Often":
                symptomsAffect.selectByVisibleText("Often");
                break;
        }
    }

    public void onSelectofWeightLoss(String option) {
        switch (option) {
            case "Yes":
                getWebDriver().findElement(answer_8_1).click();
                break;
            case "No":
                getWebDriver().findElement(answer_8_2).click();
                break;
        }
    }

    public void onClickQustion9(String option) {
        Select answer9 = new Select(getWebDriver().findElement(answer_9));
        switch (option) {
            case "Yes":
                answer9.selectByVisibleText("Yes");
                break;
            case "No":
                answer9.selectByVisibleText("No");
                break;
        }
    }

    public void inputOtherConditions(String conditions) {
        getWebDriver().findElement(answer_11).sendKeys(conditions);
    }

    public List<String> getErrorList() {
        List<WebElement> errorElementsList = getWebDriver().findElements(errors);
        List<String> errors=errorElementsList.stream().filter(pmed -> pmed.isDisplayed()&&pmed.getCssValue("color").equals(getB2cProp("validationMsg.color"))).map(WebElement::getText).collect(Collectors.toList());
        return errorElementsList.stream().filter(pmed -> pmed.isDisplayed()&&pmed.getCssValue("color").equals(getB2cProp("validationMsg.color"))).map(WebElement::getText).collect(Collectors.toList());
    }

    public void inputTravelDestination(String travelDestination) {
        getWebDriver().findElement(answer_3).sendKeys(travelDestination);
    }

    public void onSelectofDuration(String year, String month, String day) {
        Select years = new Select(getWebDriver().findElement(answer_6_1));
        Select months = new Select(getWebDriver().findElement(answer_6_2));
        Select days = new Select(getWebDriver().findElement(answer_6_3));
        switch (year) {
            case "0":
                years.selectByVisibleText("0");
                break;
            case "1":
                years.selectByVisibleText("1");
                break;
            case "2":
                years.selectByVisibleText("2");
                break;
            case "8":
                years.selectByVisibleText("8");
                break;
            case "18":
                years.selectByVisibleText("18");
                break;
            case "28":
                years.selectByVisibleText("28");
                break;
        }
        switch (month) {
            case "0":
                months.selectByVisibleText("0");
                break;
            case "1":
                months.selectByVisibleText("1");
                break;
            case "2":
                months.selectByVisibleText("2");
                break;
            case "3":
                months.selectByVisibleText("3");
                break;
            case "4":
                months.selectByVisibleText("4");
                break;
            case "5":
                months.selectByVisibleText("5");
                break;
            case "6":
                months.selectByVisibleText("6");
                break;
            case "7":
                months.selectByVisibleText("7");
                break;
            case "8":
                months.selectByVisibleText("8");
                break;
            case "9":
                months.selectByVisibleText("9");
                break;
            case "10":
                months.selectByVisibleText("10");
                break;
            case "11":
                months.selectByVisibleText("11");
                break;
            case "12":
                months.selectByVisibleText("12");
                break;
        }
        switch (day) {
            case "0":
                days.selectByVisibleText("0");
                break;
            case "1":
                days.selectByVisibleText("1");
                break;
            case "2":
                days.selectByVisibleText("2");
                break;
            case "3":
                days.selectByVisibleText("3");
                break;
            case "4":
                days.selectByVisibleText("4");
                break;
            case "5":
                days.selectByVisibleText("5");
                break;
            case "6":
                days.selectByVisibleText("6");
                break;
        }
    }

    public void onClickQustion7(String option) {
        Select answer7 = new Select(getWebDriver().findElement(answer_7));
        switch (option) {
            case "Yes":
                answer7.selectByVisibleText("Yes");
                break;
            case "No":
                answer7.selectByVisibleText("No");
                break;
        }
    }

    public void onClickQustion10(String option) {
        Select answer10 = new Select(getWebDriver().findElement(answer_10));
        switch (option) {
            case "Yes":
                answer10.selectByVisibleText("Yes");
                break;
            case "No":
                answer10.selectByVisibleText("No");
                break;
        }
    }

    public void onClickQustion12(String option) {
        Select answer12 = new Select(getWebDriver().findElement(answer_12));
        switch (option) {
            case "Yes":
                answer12.selectByVisibleText("Yes");
                break;
            case "No":
                answer12.selectByVisibleText("No");
                break;
        }
    }

    public void onClickQustion13(String option) {
        Select answer13 = new Select(getWebDriver().findElement(answer_13));
        switch (option) {
            case "Yes":
                answer13.selectByVisibleText("Yes");
                break;
            case "No":
                answer13.selectByVisibleText("No");
                break;
        }
    }

    public void inputHeight(String metre, String centimeter) {
        getWebDriver().findElement(answer_4_1).sendKeys(metre);
        getWebDriver().findElement(answer_4_2).sendKeys(centimeter);
    }

    public void inputWeight(String kilogram) {
        getWebDriver().findElement(answer_5_1).sendKeys(kilogram);
    }

    public void onSelectofTravelDate(String day, String month, String year) {
        Select days = new Select(getWebDriver().findElement(answer_5_1));
        Select months = new Select(getWebDriver().findElement(answer_5_2));
        Select years = new Select(getWebDriver().findElement(answer_5_3));
        switch (day) {
            case "1":
                days.selectByVisibleText("1");
                break;
            case "2":
                days.selectByVisibleText("2");
                break;
            case "8":
                days.selectByVisibleText("8");
                break;
            case "18":
                days.selectByVisibleText("18");
                break;
            case "28":
                days.selectByVisibleText("28");
                break;
        }
        switch (month) {
            case "1":
                months.selectByVisibleText("1");
                break;
            case "2":
                months.selectByVisibleText("2");
                break;
            case "3":
                months.selectByVisibleText("3");
                break;
            case "4":
                months.selectByVisibleText("4");
                break;
            case "5":
                months.selectByVisibleText("5");
                break;
            case "6":
                months.selectByVisibleText("6");
                break;
            case "7":
                months.selectByVisibleText("7");
                break;
            case "8":
                months.selectByVisibleText("8");
                break;
            case "9":
                months.selectByVisibleText("9");
                break;
            case "10":
                months.selectByVisibleText("10");
                break;
            case "11":
                months.selectByVisibleText("11");
                break;
            case "12":
                months.selectByVisibleText("12");
                break;
        }
        switch (year) {
            case "2014":
                years.selectByVisibleText("2014");
                break;
            case "2015":
                years.selectByVisibleText("2015");
                break;
        }
    }

    public void onSelectofYoungestBirthday(String day, String month, String year) {
        Select days = new Select(getWebDriver().findElement(answer_2_1));
        Select months = new Select(getWebDriver().findElement(answer_2_2));
        Select years = new Select(getWebDriver().findElement(answer_2_3));
        switch (day) {
            case "1":
                days.selectByVisibleText("1");
                break;
            case "2":
                days.selectByVisibleText("2");
                break;
            case "8":
                days.selectByVisibleText("8");
                break;
            case "18":
                days.selectByVisibleText("18");
                break;
            case "28":
                days.selectByVisibleText("28");
                break;
        }
        switch (month) {
            case "1":
                months.selectByVisibleText("1");
                break;
            case "2":
                months.selectByVisibleText("2");
                break;
            case "3":
                months.selectByVisibleText("3");
                break;
            case "4":
                months.selectByVisibleText("4");
                break;
            case "5":
                months.selectByVisibleText("5");
                break;
            case "6":
                months.selectByVisibleText("6");
                break;
            case "7":
                months.selectByVisibleText("7");
                break;
            case "8":
                months.selectByVisibleText("8");
                break;
            case "9":
                months.selectByVisibleText("9");
                break;
            case "10":
                months.selectByVisibleText("10");
                break;
            case "11":
                months.selectByVisibleText("11");
                break;
            case "12":
                months.selectByVisibleText("12");
                break;
        }
        switch (year) {
            case "1980":
                years.selectByVisibleText("1980");
                break;
            case "2015":
                years.selectByVisibleText("2015");
                break;
        }
    }

    public void onClickQustion3(String option) {
        Select answer3 = new Select(getWebDriver().findElement(answer_3));
        switch (option) {
            case "Yes":
                answer3.selectByVisibleText("Yes");
                break;
            case "No":
                answer3.selectByVisibleText("No");
                break;
        }
    }

    public void onClickQustion2(String option) {
        Select answer2 = new Select(getWebDriver().findElement(answer_2));
        switch (option) {
            case "Yes":
                answer2.selectByVisibleText("Yes");
                break;
            case "No":
                answer2.selectByVisibleText("No");
                break;
        }
    }

    public void onClickQustion5(String option) {
        Select answer5 = new Select(getWebDriver().findElement(answer_5));
        switch (option) {
            case "Yes":
                answer5.selectByVisibleText("Yes");
                break;
            case "No":
                answer5.selectByVisibleText("No");
                break;
        }
    }

    public void onSelectofBirthday(String day, String month, String year) {
        Select days = new Select(getWebDriver().findElement(answer_1_1));
        Select months = new Select(getWebDriver().findElement(answer_1_2));
        Select years = new Select(getWebDriver().findElement(answer_1_3));
        switch (day) {
            case "1":
                days.selectByVisibleText("1");
                break;
            case "2":
                days.selectByVisibleText("2");
                break;
            case "8":
                days.selectByVisibleText("8");
                break;
            case "18":
                days.selectByVisibleText("18");
                break;
            case "28":
                days.selectByVisibleText("28");
                break;
        }
        switch (month) {
            case "1":
                months.selectByVisibleText("1");
                break;
            case "2":
                months.selectByVisibleText("2");
                break;
            case "3":
                months.selectByVisibleText("3");
                break;
            case "4":
                months.selectByVisibleText("4");
                break;
            case "5":
                months.selectByVisibleText("5");
                break;
            case "6":
                months.selectByVisibleText("6");
                break;
            case "7":
                months.selectByVisibleText("7");
                break;
            case "8":
                months.selectByVisibleText("8");
                break;
            case "9":
                months.selectByVisibleText("9");
                break;
            case "10":
                months.selectByVisibleText("10");
                break;
            case "11":
                months.selectByVisibleText("11");
                break;
            case "12":
                months.selectByVisibleText("12");
                break;
        }
        switch (year) {
            case "2000":
                years.selectByVisibleText("2000");
                break;
            case "2015":
                years.selectByVisibleText("2015");
                break;
        }
    }

    public void onCheckQuestion2() {
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(answer_2));
        ((JavascriptExecutor) getWebDriver()).executeScript("window.scrollBy(0, -150)", "");
        waitForExpectedElement(answer_2).click();
    }

    public void inputQuestion2(String answers) {
        getWebDriver().findElement(answer_2).sendKeys(answers);
    }
}
