package com.salmon.test.pageobjects.b2b.profilelanding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.salmon.test.framework.PageObject;
import com.salmon.test.models.b2b.AccountSimpleTable;

public class B2BOrderReplyPage extends PageObject{
   private static final Logger LOG = LoggerFactory.getLogger(B2BOrderReplyPage.class);
    
   private By viewOrderReplyLink=By.cssSelector("section#sentDetailsTab a.textLink");
   private By orderDetailsTab=By.cssSelector("#orderDetailsTabs>div");
   private By orderReplyHeader=By.cssSelector("#contentWrapper h1");
   private By sentDetailsLabel=By.cssSelector("section dt");
   private By orderReplies=By.cssSelector("#ordReplies>tbody>tr>td:nth-of-type(2)");
   private By orderReplyDataTable=By.cssSelector(".responsiveTable");
   private By orderSummaryLabel=By.cssSelector("section#sentDetailsTab dl>dt");
   private By viewExceptionsCheckBox=By.cssSelector(".checkboxField>label");
   private By exceptionsDataColumn=By.cssSelector("table#ordReplies td");
   private By tableRows=By.cssSelector("table.responsiveTable>tbody>tr");
   
   public void viewOrderReplyFromOrderDetails(){
	   waitForExpectedElement(viewOrderReplyLink).click();
   }
   
   public void clickOnTab(String tabName){
	   this.visibilityOfAllElementsLocatedBy(orderDetailsTab).stream().filter(e->e.getText().contains(tabName)).forEach(e->e.click());
   }
   
   public List<String> getTableHeaders(){
	   AccountSimpleTable orderReplyTable=new AccountSimpleTable(waitForExpectedElement(orderReplyDataTable));
	   return orderReplyTable.getTableHeaderColoums();
   }
   
   public List<List<String>>getAllOrderReplyData(){
	   List<List<String>>tableData=new ArrayList<>();
	   List<WebElement> rows=this.visibilityOfAllElementsLocatedBy(tableRows);
	   for(WebElement row: rows){
		   tableData.add(row.findElements(By.cssSelector("td")).stream().map(e->e.getText()).collect(Collectors.toList()));
	   }
	   return tableData;
   }
   
   public List<String> getOrderDetailsLabels(){
       return this.visibilityOfAllElementsLocatedBy(sentDetailsLabel).stream().map(lb->lb.getText().trim()).collect(Collectors.toList());
   }
   
   public List<String> getOrderSummaryLabels(){
	   return visibilityOfAllElementsLocatedBy(orderSummaryLabel).stream().map(lb->lb.getText().trim()).collect(Collectors.toList());
   }
	
   public List<String>getOrderReplyCodeData(){
       if(!isElementPresent(orderReplies)){
           LOG.info("This is an empty table.");
           return Collections.emptyList();
       }else{
           return visibilityOfAllElementsLocatedBy(orderReplies).stream().map(code->code.getText()).collect(Collectors.toList());
       }
   }
   
   public void clickShowExceptionsOnly(){
	   waitForExpectedElement(viewExceptionsCheckBox).click();
   }  
   
   public String getPageHeader(){
	   return waitForExpectedElement(orderReplyHeader).getText().trim();
   }
   
}
