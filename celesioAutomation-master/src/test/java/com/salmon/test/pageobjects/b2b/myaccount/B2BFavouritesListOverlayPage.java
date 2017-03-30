package com.salmon.test.pageobjects.b2b.myaccount;
import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class B2BFavouritesListOverlayPage extends PageObject {

    private static final Logger LOG = LoggerFactory.getLogger(B2BFavouritesListOverlayPage.class);
    
    private By pageTitle = By.cssSelector(".modal-body>h2");
    private By textLabel = By.cssSelector("#favlist>fieldset>label:nth-of-type(1)");
    private By text = By.cssSelector("#favlist>fieldset>label>h2");
    private By buttonText = By.cssSelector("#favlist>button");

	public String getPageTitle() {
		LOG.info("Page Title: " + waitForExpectedElement(pageTitle).getText());
        return waitForExpectedElement(pageTitle).getText();
	}

	public String getTextLabel() {
		LOG.info("Text Label: " + waitForExpectedElement(textLabel).getText());
        return waitForExpectedElement(textLabel).getText();
	}

	public String getText() {
		LOG.info("Text: " + waitForExpectedElement(text).getText());
        return waitForExpectedElement(text).getText();
	}

	public String getButtonText() {
		LOG.info("Button Text: " + waitForExpectedElement(buttonText).getText());
        return waitForExpectedElement(buttonText).getText();
	}
	
}
