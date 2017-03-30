package com.salmon.test.pageobjects.b2b.checkout;
import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class B2BQuickOrderPage extends PageObject{
	private static final Logger LOG = LoggerFactory.getLogger(B2BQuickOrderPage.class);
	
	private By quickOrderLink = By.cssSelector("li>a[href*='QuickOrder']>span");
	private By quickOrderForm = By.id("MQuickOrderForm");
	private By addToBasketBtn = By.cssSelector("div.button_text");
	private By explanTextFiled = By.cssSelector("div[id^='WC_QuickOrderForm']>#quick_order>.col-12");
	private By quickOrderUploadBtn=By.cssSelector("button[class^='quickOrderUpload']");
	private By browseBtn=By.cssSelector("label[for='UpLoadedFile']>div");
	private By browseInboxBtn=By.cssSelector("#qoFileNameBox");
	private By uploadBtn=By.cssSelector("button#UploadListForm_Save");
	private By msgPopupFromQuickOrder = By.cssSelector("div.rowContainer.belowHeaderTools span#ErrorMessageText");
	private By alertPopup = By.cssSelector(".dijitTooltipContainer.dijitTooltipContents");
	private By partNumberField=By.cssSelector("#partNumber_1");
	private By qtyField=By.cssSelector("#quantity_1");
	private By formHeader=By.cssSelector(".row.borderBottom strong");
	
	public void selectLinkFromMainNav(String linkName) {
		if (linkName.equals(getB2bProp("checkout.quickOrder"))) {
			waitForExpectedElement(quickOrderLink).click();
		} else
			LOG.error("Cannot find the target link in current page or it's not supported yet: " + linkName);
	}
	
	public boolean canDisplayDefTextAndeSpotFromQuickOrder() {
		if (!isElementPresent(explanTextFiled)) {
			LOG.error("Cannot display the Explanatory text in quick order.");
			return false;
		}
		return true;
	}
	
	public List<String> getQuickOrderFormHeader(){
		return this.visibilityOfAllElementsLocatedBy(formHeader).parallelStream().map(WebElement::getText).collect(Collectors.toList());
	}
	
	public void uploadCSVFile(){
		waitForExpectedElement(quickOrderUploadBtn).click();
		String csvFilePath=getNewCsvFilePath();
		//Not working for the moment
		//TODO
		waitForExpectedElement(browseInboxBtn).sendKeys(csvFilePath);
		waitForExpectedElement(uploadBtn).click();
	}
	
	public void addQuickOrderInfoIntoBasket(String skuNumber, String quantity) {
		WebElement form = waitForExpectedElement(quickOrderForm);
		inputFromInputField(form.findElement(partNumberField), skuNumber);
		inputFromInputField(form.findElement(qtyField), quantity);
		form.findElement(addToBasketBtn).click();
	}
	
	private void inputFromInputField(WebElement inputField, String text) {
		inputField.clear();
		inputField.sendKeys(text);
	}
	
	public String getPopupMessageFromQuickOrder() {
		if (waitForExpectedElement(msgPopupFromQuickOrder).isDisplayed()) {
			return waitForExpectedElement(msgPopupFromQuickOrder).getText().trim();
		} else if (isElementPresent(alertPopup)) {
			return waitForExpectedElement(alertPopup).getText().trim();
		} else {
			LOG.warn("/n There is no message popup in header or tooltip in quick order page.");
			return "";
		}
	}
	
	
	private String getNewCsvFilePath() {
		File file = new File("Quickshop upload - Good - AAH.csv");
		if (file.exists()) {
			LOG.warn("/n File already exist. Use the existing file directly for uploading.");
//			String pathtest=file.getAbsolutePath();
//			boolean isclickable=this.isElementPresent(By.xpath("//input[@id='UpLoadedFile']"));
			
//			return "E://Test data//"+"Quickshop upload - Good - AAH.csv";
			return file.getAbsolutePath();
		} else {
			LOG.info("Now create a new csv file for quick order uploading." + file.getName());
			try {
				file.createNewFile();
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.append(getB2bProp("csv.upload.header"));
				fileWriter.append("\n");
				fileWriter.append(getB2bProp("csv.upload.content.line1"));
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				LOG.error("/n Error occurs while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
		return file.getAbsolutePath();
	}
}
