package com.salmon.test.pageobjects.b2b.myaccount;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aettukullapati on 19/01/2016.
 */
public class B2BOTCOrderLandingPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BOTCOrderLandingPage.class);
    private By yourProfileHeaders = By.cssSelector(".col-4.m-col-12.borderedPanel h2");
    private By yourProfileLinks = By.cssSelector(".col-4.m-col-12.borderedPanel>ul>li>a>h3");
    private By userProfilesToApprove = By.xpath(".//*[@id='content']/div/ul/li[3]/ul/li[2]/a/h3");
    private By ordersForApprove = By.xpath(".//*[@id='content']/div/ul/li[3]/ul/li[3]/a/h3");

    public List<String> getYourProfileHeaderList() {
        List<String> profileHeaderList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(yourProfileHeaders);
        for (WebElement profileHeaders : webElements) {
            profileHeaderList.add(profileHeaders.getText().trim());
        }
        return profileHeaderList;
    }

    public List<String> getYourProfileLinksList() {
        List<String> profileLinksList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(yourProfileLinks);
        for (WebElement profileLinks : webElements) {
            profileLinksList.add(profileLinks.getText());
        }
        return profileLinksList;
    }

    public void clickOnSection(String sectionName) {
        if (sectionName.equalsIgnoreCase(getB2bProp("user.profiles.approve"))) {
            waitForExpectedElement(userProfilesToApprove).click();
        } else if (sectionName.equalsIgnoreCase(getB2bProp("orders.for.approval"))) {
            waitForExpectedElement(ordersForApprove).click();
        }
    }
}
