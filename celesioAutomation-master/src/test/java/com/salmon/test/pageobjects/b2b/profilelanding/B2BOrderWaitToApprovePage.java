/**
 *
 */
package com.salmon.test.pageobjects.b2b.profilelanding;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class B2BOrderWaitToApprovePage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BOrderWaitToApprovePage.class);
    private By orderWaitingForApproval = By.cssSelector("#accountAdminPanel ul li:nth-of-type(3)>a>h3");
    private By tableHeaders = By.cssSelector(".responsiveTable>thead>tr>th>span:nth-of-type(1)");
    private By orderApprovalMessage = By.cssSelector("#content>h1");
    private By actionsButtons = By.cssSelector(".actions");
    private By actionDropDownValues = By.cssSelector("option");
    private By submitBtn = By.cssSelector(".actions input[type='submit']");
    
    private By aahOrderLinks=By.xpath(".//img[@alt='aah']/ancestor::tr//a[@class='textLink']");
    private By enterpriseOrderLinks=By.xpath(".//img[@alt='ENTERPRISE']/ancestor::tr//a[@class='textLink']");
    private By logOnId = By.cssSelector("#WC_AccountDisplay_FormInput_logonId_In_Logon_1");
    private By password = By.cssSelector("#WC_AccountDisplay_FormInput_logonPassword_In_Logon_1");
    private By requestReviewBtn = By.cssSelector("input[value='Request Review']");
    private By mainNavigationMenu = By.cssSelector("#mainNavigationTopLevel>li>a");
    private By commentInputBox = By.cssSelector("#comment");
    private By orderConfirmationMessage = By.cssSelector("#content>div>h3");

    public void clickOnOrderWaitingForApproval() {
        waitForExpectedElement(orderWaitingForApproval).click();
    }

    public List<String> getTableHeaderList() {
        List<String> footerHeaderList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(tableHeaders);
        for (WebElement footerHeaders : webElements) {
            footerHeaderList.add(footerHeaders.getText().trim());
        }
        return footerHeaderList;
    }

    public void selectOptionFromActions(String option, String orderLinkName) {
        List<WebElement> actionButtons = visibilityOfAllElementsLocatedBy(actionsButtons, 20);
        LOG.info("\n Action Buttons Size :: " + actionButtons.size());
        for (WebElement actionButton : actionButtons) {
            LOG.info("\n  Text Value :: " + actionButton.getText());
            if (orderLinkName.equalsIgnoreCase("Actions")) {
                actionButton.click();       
                List<WebElement> options=actionButton.findElements(actionDropDownValues);
                for(WebElement compareElement : options){
                	if (compareElement.getText().equalsIgnoreCase(option)) {
                        LOG.info("\n Action Name After : : --> " + compareElement.getText());
                        compareElement.click();
                        break;
                    }
                }   
            }
            break;
        }
    }

    public boolean verifyOrderApprovalReqMessage(String orderMessage) {
        return waitForExpectedElement(orderApprovalMessage).getText().toLowerCase().contains(orderMessage.toLowerCase());
    }

    public void clickOnOrder(String linkName) {
        if (linkName.equalsIgnoreCase("AAH")) {
            LOG.info("Now click an order with AAH type.");
            visibilityOfAllElementsLocatedBy(aahOrderLinks).get(0).click();
        } else if (linkName.equalsIgnoreCase("Enterprise")) {
            LOG.info("Now click an order with Enterprise type.");
            this.visibilityOfAllElementsLocatedBy(enterpriseOrderLinks).get(0).click();
        } else {
            LOG.error("The link name is not found: " + linkName);
        }
    }

    public void clickOnRequestReviewButton(String reviewButton) {
        waitForExpectedElement(requestReviewBtn).submit();
        LOG.info("\n clicked  button:: "+reviewButton);
    }

    public boolean canAccessTargetPage(String pageTitle) {
        LOG.info("\n page title :: " + pageTitle);

        return checkPageTitleContains(pageTitle);
    }

    public void clickLinkFromMainNavigation(String linkName) {
        List<WebElement> mainMenuLinks = visibilityOfAllElementsLocatedBy(mainNavigationMenu);
        for (WebElement link : mainMenuLinks) {
            if (link.getText().trim().equals(linkName)) {
                link.click();
                break;
            }
        }
    }

    public void enterComments(String comment) {
        waitForExpectedElement(commentInputBox).sendKeys(comment);
    }
    
    public void clickButtons(String btnName){
        for(WebElement btn: this.visibilityOfAllElementsLocatedBy(submitBtn)){
            if(btn.getAttribute("value").trim().equalsIgnoreCase(btnName)){
                btn.click();
                return;
            }
        }
        LOG.error("Cannot find button with name: "+btnName);
    }

    public boolean getOrderConfirmationMessage(String confirmationMsg) {
        boolean flag = false;
        LOG.info("\n Confirmation message ::" + waitForExpectedElement(orderConfirmationMessage).getText());
        if (waitForExpectedElement(orderConfirmationMessage, 20).getText().contains(confirmationMsg.toUpperCase())) {
            flag = true;
        }
        return flag;
    }

}
