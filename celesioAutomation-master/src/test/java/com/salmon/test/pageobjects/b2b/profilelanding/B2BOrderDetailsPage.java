/**
 * 
 */
/**
 * @author tzhao
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

public class B2BOrderDetailsPage extends PageObject{
    private static final Logger LOG = LoggerFactory.getLogger(B2BOrderWaitToApprovePage.class);
    private By orderPanelLabels = By.cssSelector("section dt");
    private By orderTableHeaders = By.cssSelector(".row.thead>p");
    private By rejectButton = By.cssSelector("input[value='Reject']");
    private By approveButton = By.cssSelector("input[value='Approve']");

    public List<String> getAAHPanel() {
        List<String> panelLabelList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(orderPanelLabels);
        for (WebElement label : webElements) {
            panelLabelList.add(label.getText().trim());
        }
        LOG.info("\n Page Items :: " + panelLabelList);
        return panelLabelList;
    }

    public List<String> getTableHeaders() {
        List<String> orderTableHeaderList = new ArrayList<>();
        List<WebElement> webElements = getWebDriver().findElements(orderTableHeaders);
        for (WebElement tableHeader : webElements) {
            orderTableHeaderList.add(tableHeader.getText().trim());
        }
        LOG.info("\n Table Headers :: " + orderTableHeaderList);
        return orderTableHeaderList;
    }

    public void clickOnButton(String button) {
        if (button.equalsIgnoreCase("Reject"))
            waitForExpectedElement(rejectButton).click();
        else if (button.equalsIgnoreCase("Approve")) {
            waitForExpectedElement(approveButton).click();
        }
    }
}