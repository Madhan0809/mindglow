package com.salmon.test.pageobjects.b2c.checkout;
import com.salmon.test.constants.LloydsPharmacyConstants;
import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.models.b2c.AddressForm;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LPDeliveryOptionPage extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(LPDeliveryOptionPage.class);
    private By clickCollectionOption = By.cssSelector("#deliveryOption0");
    private By formClickCollectArea = By.cssSelector(".row.clickAndCollect.formContainer");
    private By clickCollectionIcon = By.cssSelector("label[for='deliveryOption0']>i.iDelOptions.row0");
    private By clickCollectionTitle = By.cssSelector("label[for='deliveryOption0'] span.title>h6");
    private By collectionBox = By.cssSelector("div[class*='collectionBox']");
    private By clickAndCollectInputFields = By.xpath("//*[@class='row clickAndCollect formContainer']//input[@type='text' or @type='email']");
    private By changeStoreLink = By.cssSelector("#changeStore");
    private By preSelectedStore = By.cssSelector("div[class*='collectionPoint collectionBox']");
    private By deliveryToUKAddressOption = By.cssSelector("#deliveryOption1");
    private By formDeliveryAddress = By.cssSelector("#formDeliveryAddress");
    private By deliveryToUKAddressIcon = By.cssSelector("label[for='deliveryOption1']>i.iDelOptions.row1");
    private By deliveryToUKAddressTitle = By.cssSelector("label[for='deliveryOption1'] span.title>h6");
    private By detailInforInputs = By.cssSelector("#formDeliveryAddress .formContainer.userDetails input");
    private By selectAddress = By.cssSelector("#addressId");
    private By preferredDeliveryMethodForm = By.cssSelector(".formContainer.deliveryTypeSelect");
    private By deliveryTypes = By.cssSelector("fieldset div>label");
    private By deliveryInstruction = By.cssSelector(".formContainer.formGuest.deliveryInstruction>fieldset>textarea");
    private By storeLookupButton = By.cssSelector("a#storeLookup");
    private By manualAddress1Input = By.cssSelector(".deliveryAddressFields input[name='address1']");
    //	private By continueToPaymentBtn=By.xpath("//input[@type='submit' and @value='Continue to Payment']");
    private By continueToPaymentFromCC=By.cssSelector("#continueButton");
    private By continueToPaymentBtn = By.cssSelector("input.btnAction.btnValidated");
    private By continueAndRemoveBtn = By.cssSelector("div.removeItems>a");
    private By paymentPageArea = By.cssSelector("#paymentForm");
    private By errorMsg = By.cssSelector(".errorMsg");
    private By addNewAddressLink = By.partialLinkText("Add a new address");
    private By editAddressLink = By.partialLinkText("Edit Address");
    private By manualAddressEditLink = By.cssSelector("#manualAddress");
    //	private By editAddressLink=By.cssSelector(".detailsList>li>a:contains('Edit Address')");
    private By backBtn = By.cssSelector(".btnGrey.btnBack");
    private By findAddressBtn = By.cssSelector(".btnPrimary");
    private By saveAddressBtn = By.cssSelector(".btn.btnAction.btnSaveAddress");
    private By editDeliveryAddressForm = By.cssSelector(".panel");
    private By deliveryItems = By.xpath("//form[@id='formEditAddress']//input[@type='text']|//select[@name='country']");
    //For checkOutsignInPage
    private By guestSignInBtn = By.cssSelector("[name='guestLink']");
    //For order summary list
    private By orderSummary = By.cssSelector("div.orderSummary");
    //For homepage and signOutCheckin page elements
    private By hpLinkFromCheckin = By.xpath("//div[@class='row brandSearch']//a[starts-with(@href,'http://')]");
    private By hpLinkFromDeliveryOption = By.xpath("//div[@class='deliveryHeader rowContainer']//a");
    private By homePage = By.cssSelector("#homepage");
    //For headerInfo and homepage verify
    private By headerLogo = By.cssSelector("div[class*='brand']");
    private By headerHelpEspot = By.cssSelector(".left_espot");
    private By deliverySection = By.xpath("//div[@class='step active']/p[contains(text(),'Delivery')]");
    private By paymentSection = By.xpath("//div[@class='step']/p[contains(text(),'Payment')]");
    private By confirmationSection = By.xpath("//div[@class='step']/p[contains(text(),'Confirmation')]");
    //For footerInfo
    private By footerCheckOutEspot = By.cssSelector(".deliveryFooterLinks");
    private By providerImages = By.cssSelector(".paymentOptions");
    //StoreLocatorMap
    private By storeLocatorMap = By.cssSelector("#storeLocatorMap");
    private By storeLocatorResult = By.cssSelector(".storeLocatorResults");
    private By storeDetailPopup = By.cssSelector("div.gm-style-iw");
    private By storeResults = By.cssSelector("div.storeResultsPanel");
    private By storeDetailGoogleMap = By.cssSelector("#googleMap");
    private By storeDetailsRow = By.cssSelector(".row.storeDetails");
    private By selectStoreBtn = By.cssSelector(".btnPrimary,*[title='Select Store']");
    private By locatorListerColumns=By.cssSelector("div[class^='col-']>label:not([class='hide'])");

    
    public void selectDeliveryOptions(String optionMethod) {
        if (optionMethod.contains("Collect")) {
//			if(!this.isElementPresent(clickAndCollectInputFields)||isElementPresent(deliveryInstruction)){
            if (!this.waitForElementAvailableAndVisible(clickCollectionOption).isSelected()) {
                LOG.debug("Now click on 'Click and Collection' option for delivery.");
                getWebDriver().findElement(clickCollectionOption).click();
            }
        } else if (optionMethod.contains("address")) {
//			if(!this.isElementPresent(deliveryInstruction)){
            if (!this.waitForElementAvailableAndVisible(deliveryToUKAddressOption).isSelected()) {
                LOG.debug("Now click on 'delivery to UK address' option for delivery.");
                getWebDriver().findElement(deliveryToUKAddressOption).click();
            }
        } else {
            LOG.error("The delivery method is not found: " + optionMethod);
        }
    }

    public void startFromHomePageWithCookies() {
        LOG.info(" Navigating to Lloyds Pharmacy HomePage \n");
        if (isElementPresent(hpLinkFromCheckin)) {
            getWebDriver().findElement(hpLinkFromCheckin).click();
        } else if (isElementPresent(hpLinkFromDeliveryOption)) {
            getWebDriver().findElement(hpLinkFromDeliveryOption).click();
        }
        if (!isSwitchedToHomePage()) {
            LOG.warn("The link may not work at present. Now switch to homepage by using URL.");
            UrlBuilder.startAtHomePage();
        }
    }

    private boolean isSwitchedToHomePage() {
        boolean isHomePage = false;
        if (isElementPresent(homePage)) {
//        if(waitForExpectedElement(homePage).isDisplayed()) {
            isHomePage = true;
        }
        return isHomePage;
    }

    public boolean isDefaultDeliveryPageDisplayed() {
        if (!isDeliveryOptionRadioDisplayed()) {
            return false;
        } else if (!isDeliveryOptionTitleDisplayed()) {
            return false;
        } else if (!isDeliveryOptionIconDisplayed()) {
            return false;
        }
        return true;
    }

    private boolean isDeliveryOptionRadioDisplayed() {
        if (!this.isElementPresent(clickCollectionOption) || !isElementPresent(deliveryToUKAddressOption)) {
            LOG.error("The delivery option radio buttons is not presented.");
            return false;
        }
        return true;
    }

    private boolean isDeliveryOptionIconDisplayed() {
        if (!this.isElementPresent(clickCollectionIcon) || !isElementPresent(deliveryToUKAddressIcon)) {
            LOG.error("The icon for the delivery options is not presented.");
            return false;
        }
        return true;
    }

    private boolean isDeliveryOptionTitleDisplayed() {
        if (!this.isElementPresent(clickCollectionTitle) || !isElementPresent(deliveryToUKAddressTitle)) {
            LOG.error("The title for delivery options is not displayed");
            return false;
        }
        return true;
    }

    public boolean isFormTableDisplayed(String deliveryMethod) {
        if (deliveryMethod.contains("Click and Collect")) {
            return isElementPresent(formClickCollectArea);
        } else if (deliveryMethod.contains("Deliver to UK address")) {
            return isElementPresent(formDeliveryAddress);
        } else {
            LOG.debug("There is no delivery method: " + deliveryMethod + "in delivery option page");
            return false;
        }
    }

    public void inputDetailInformation(AddressForm addressForm) {
        LOG.debug("Now input detail delivery information.");
        List<WebElement> detailInfoFields = visibilityOfAllElementsLocatedBy(detailInforInputs);
        for (WebElement dt : detailInfoFields) {
            dt.clear();
        }
        LOG.debug("Now start to input detail information.");
        getDetailEditFieldByAttributeName("firstName").sendKeys(addressForm.getFirstName());
        getDetailEditFieldByAttributeName("lastName").sendKeys(addressForm.getLastName());
        getDetailEditFieldByAttributeName("email1").sendKeys(addressForm.getEmailAddress());
        getDetailEditFieldByAttributeName("phone1").sendKeys(addressForm.getPhoneNumber());
        LOG.debug("The detail information is fulfilled now.");
    }

    public void inputMobilePreferredInfo(AddressForm addressForm) {
        WebElement deliveryForm = getWebDriver().findElement(formDeliveryAddress);
        deliveryForm.findElement(By.name("zipCode")).sendKeys(addressForm.getPostCode());
        waitForExpectedElement(manualAddressEditLink).click();
        if(!this.isElementPresent(manualAddress1Input)){
        	LOG.warn("The postcode may be invalid. Cannot input detail info.");
        	return;
        }
        waitForExpectedElement(manualAddress1Input);

        getDetailEditFieldByAttributeName("address1").sendKeys(addressForm.getAddress1());
        getDetailEditFieldByAttributeName("address2").sendKeys(addressForm.getAddress2());
        getDetailEditFieldByAttributeName("city").sendKeys(addressForm.getCity());
        getDetailEditFieldByAttributeName("state").sendKeys(addressForm.getState());
    }

    private WebElement getDetailEditFieldByAttributeName(String fieldName) {
        WebElement deliveryForm = getWebDriver().findElement(formDeliveryAddress);
        String fieldNameCss = "#formDeliveryAddress" + " [name =  '" + fieldName + "']";
        if (!isElementPresent(By.cssSelector(fieldNameCss))) {
            return null;
        }
        return deliveryForm.findElement(By.name(fieldName));
    }

    //Optimized codes
    private WebElement getAddressEditFieldByAttributeName(String fieldName) {
        WebElement editDeliveryForm = waitForElementAvailableAndVisible(editDeliveryAddressForm);
        StringBuffer singleItemXpath = new StringBuffer("//form[@id='formEditAddress']//input[@type='text' and @name='");
        singleItemXpath.append(fieldName + "']|//select[@name='");
        singleItemXpath.append(fieldName + "']");
        if (!this.isElementPresent(By.xpath(singleItemXpath.toString()))) {
            return null;
        }
        return editDeliveryForm.findElement(By.xpath(singleItemXpath.toString()));
    }

    public void chooseDeliveryAddress(AddressForm addressForm) {
        WebElement deliveryForm = getWebDriver().findElement(formDeliveryAddress);
        Select addressChoose = new Select(deliveryForm.findElement(selectAddress));
        LOG.debug("Now start to choose the delivery address information.");
        for (WebElement singleItem : addressChoose.getAllSelectedOptions()) {
            if (singleItem.getText().trim().equalsIgnoreCase(addressForm.getChooseAddress())) {
                LOG.debug("Now select the delivery address: " + addressForm.getChooseAddress());
                addressChoose.selectByVisibleText(addressForm.getChooseAddress());
            } else {
                LOG.error("There is no selection item for target item: " + addressForm.getEmailAddress());
            }
        }
    }

    public void choosePreferredDeliveryMethod(AddressForm addressForm) {
        WebElement deliveryTypesForm = waitForExpectedElement(preferredDeliveryMethodForm);
        List<WebElement> types = deliveryTypesForm.findElements(deliveryTypes);
        String preferredDeliveryMethod = addressForm.getPreferredDeliveryMethod();
        for (WebElement deliveryType : types) {
            if (deliveryType.getText().contains(preferredDeliveryMethod)) {
                deliveryType.findElement(By.xpath("./parent::div/input")).click();
                break;
            }
        }
    }

    public void inputDeliveryInstructions(AddressForm addressForm) {
        WebElement instructionText = waitForExpectedElement(deliveryInstruction);
        instructionText.clear();
        instructionText.sendKeys(addressForm.getDeliveryInstructions());
    }

    public void clickOnContinueToPayment() {
        if (this.isElementPresent(continueToPaymentBtn)) {
            LOG.info("Continue to payment via delivery option.");
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(continueToPaymentBtn));
            scrollAndClick(continueToPaymentBtn);
        } else{
            LOG.info("Continue to payment via C&C.");
            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(continueToPaymentFromCC));
            scrollAndClick(continueToPaymentFromCC);
        }
    }

    public boolean isPaymentPageDisplays() {
        int retry=0;
        while(!isElementPresent(paymentPageArea)&&retry<3){
            LOG.warn("The payment page is not open. The page may under loading.");
            retry++;
        }
        return this.isElementPresent(paymentPageArea);
    }

    public boolean isErrorMessageComes() {
        return this.isElementPresent(errorMsg);
    }

    public boolean canDisplayAllErrorMessages(List<String> expectedErrMsg) {
        if (isElementPresent(formDeliveryAddress)) {
            getWebDriver().findElement(formDeliveryAddress).click();
        }
        else if(isElementPresent(formClickCollectArea)){
            getWebDriver().findElement(formClickCollectArea).click();
        }
        else
        	LOG.info("Now check the validation message in delivery page.");
        List<WebElement> actualErrorMsgs = getWebDriver().findElements(By.cssSelector("div[class='errorMsg']"));
        for (WebElement errMsg : actualErrorMsgs) {
            if (!expectedErrMsg.contains(errMsg.getText()) && errMsg.isDisplayed()) {
                LOG.error("The error message is not equal with the expected one. Actual is " + errMsg.getText());
                return false;
            }
        }
        return true;
    }

    public boolean canViewExceptionTable(String exceptionTableName) {
        String exceptionTableXpath = "//p[contains(text(),'" + exceptionTableName + "')]/parent::div[@class='restrictedItems panel']";
        if (!this.isElementPresent(By.xpath(exceptionTableXpath))) {
            LOG.debug("The exception table in C&C option is not displayed.");
            return false;
        }
        return true;
    }

    public void clickOnRemoveAndContinue(String deliveryOptionFormName) {
        String exceptionTableXpath = "//p[contains(text(),'" + deliveryOptionFormName + "')]/parent::div[@class='restrictedItems panel']";
        WebElement exceptionTable = this.waitForElementAvailableAndVisible(By.xpath(exceptionTableXpath));
        exceptionTable.findElement(continueAndRemoveBtn).click();
    }

    public boolean canViewCorectExceptionTableMessage(String exceptionTableName, List<String> errorMsgContent) {
        String exceptionTableXpath = "//p[contains(text(),'" + exceptionTableName + "')]/parent::div[@class='restrictedItems panel']";
        WebElement exceptionTable = getWebDriver().findElement(By.xpath(exceptionTableXpath));
        for (String e : errorMsgContent) {
            if (exceptionTable.findElement(By.cssSelector("div.restrictedItems.panel>p")).getText().trim().equals(e)) {
            } else if (exceptionTable.findElement(By.cssSelector("div.restrictedItems.panel>small")).getText().trim().equals(e)) {
            } else {
                LOG.error("The error message is not found: " + e);
                return false;
            }
        }
        return true;
    }

    public void inputClickCollectInformation(AddressForm clickAndCollectForm) {

        List<WebElement> collectInputFields = getWebDriver().findElements(clickAndCollectInputFields);
        LOG.debug("Now start to input click and collect information.");
        for (WebElement singleItem : collectInputFields) {
            if (!singleItem.isDisplayed()) {
                continue;
            }
            if (singleItem.getAttribute("id").equals("postcodeText")) {
                singleItem.sendKeys(clickAndCollectForm.getPostCode());
            } else if (singleItem.getAttribute("name").equals("firstName")) {
                singleItem.clear();
                singleItem.sendKeys(clickAndCollectForm.getFirstName());
            } else if (singleItem.getAttribute("name").equals("lastName")) {
                singleItem.clear();
                singleItem.sendKeys(clickAndCollectForm.getLastName());
            } else if (singleItem.getAttribute("name").equals("email1")) {
                singleItem.clear();
                singleItem.sendKeys(clickAndCollectForm.getEmailAddress());
            } else if (singleItem.getAttribute("name").equals("phone1")) {
                singleItem.clear();
                singleItem.sendKeys(clickAndCollectForm.getPhoneNumber());
            } else {
                LOG.error("The attribute of input detail information is not found.");
                break;
            }
        }
    }

    public void editDeliveryAddressByClickLink(String linkName) {
        if (linkName.contains("Add ")) {
            waitForExpectedElement(addNewAddressLink).click();
        } else if (linkName.contains("Edit")) {
            waitForExpectedElement(editAddressLink).click();
        } else {
            LOG.error("Fail to find the target link: " + linkName);
        }
    }

    public void editDeliveryAddressInfo(AddressForm deliveryEditInfo, String editMode) {
        LOG.debug("Now start to edit the delivery address information.");
        List<WebElement> itemsToEdit = this.visibilityOfAllElementsLocatedBy(deliveryItems);
        LOG.debug("Clear the existing input fields in address edit mode.");
        for (WebElement item : itemsToEdit) {
            if (item.getTagName().equals("input")) {
                item.clear();
            }
        }
        if (editMode.equalsIgnoreCase("Add")) {
            if (!(getAddressEditFieldByAttributeName("nickName") == null)) {
                getAddressEditFieldByAttributeName("nickName").sendKeys(deliveryEditInfo.getNickName());
            }
        }
        getAddressEditFieldByAttributeName("firstName").sendKeys(deliveryEditInfo.getFirstName());
        getAddressEditFieldByAttributeName("lastName").sendKeys(deliveryEditInfo.getLastName());
        getAddressEditFieldByAttributeName("zipCode").sendKeys(deliveryEditInfo.getZipCode());
        getAddressEditFieldByAttributeName("address1").sendKeys(deliveryEditInfo.getAddress1());
        getAddressEditFieldByAttributeName("address2").sendKeys(deliveryEditInfo.getAddress2());
        getAddressEditFieldByAttributeName("city").sendKeys(deliveryEditInfo.getCity());
        getAddressEditFieldByAttributeName("state").sendKeys(deliveryEditInfo.getState());
//	   Select countrySelector=new Select(getAddressEditFieldByAttributeName("country"));
//	   countrySelector.selectByVisibleText(deliveryEditInfo.getCountry());
    }

    public void clickOnButtonInDeliveryAddressEditMode(String buttonName) {
        if (buttonName.contains("Back")) {
            LOG.debug("Click on 'Back' button.");
            this.waitForExpectedElement(backBtn).click();
        } else if (buttonName.contains("Find Address")) {
            LOG.debug("Click on 'Find Address' button.");
            this.waitForExpectedElement(findAddressBtn).click();
        } else if (buttonName.contains("Save")) {
            LOG.debug("Click on 'Save Address' button.");
            this.waitForExpectedElement(saveAddressBtn).click();
        }
    }

    public void clickFindAddressInDeliveryOption(String optionName) {
        if (optionName.contains("Click and collect")) {
            WebElement clickAndCollectForm = waitForExpectedElement(formClickCollectArea);
            if (isElementPresent(preSelectedStore)) {
                LOG.info("User already has one pre-selected address. Skip the process to choose store.");
            } else
                clickAndCollectForm.findElement(storeLookupButton).click();
        } else if (optionName.contains("Delivery to UK")) {
            WebElement deliveryToUKForm = getWebDriver().findElement(formDeliveryAddress);
            deliveryToUKForm.findElement(findAddressBtn).click();
        }
    }

    public boolean isSummaryInfoPanelDisplayed() {
        LOG.debug("Check whether the summary order information is displayed in the right pane in delivery options page.");
        return isElementPresent(orderSummary);
    }

    public Map<String, String> getOrderSummaryInfo() {
        Map<String, String> actualOrderSummary = new HashMap<String, String>();
        List<WebElement> orderInfo = this.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartTotal li"));
        for (WebElement singleOrderInfo : orderInfo) {
            actualOrderSummary.put(setupKeyOfOrderColumn(singleOrderInfo.getText()), singleOrderInfo.findElement(By.xpath("./span")).getText());
        }
        return actualOrderSummary;
    }

    private String setupKeyOfOrderColumn(String originalColumn) {
        if (!originalColumn.isEmpty()) {
            String transCol = String.valueOf(originalColumn);
            return transCol.substring(0, transCol.lastIndexOf("\n"));
        }
        return null;
    }

    public boolean isOrderSummaryInfoSame(Map<String, String> actualOrderInfo, Map<String, String> expectedInfo) {
        for (String k : actualOrderInfo.keySet()) {
            if (!actualOrderInfo.get(k).equals(expectedInfo.get(k))) {
                LOG.error("The actual value " + actualOrderInfo.get(k) + " is not equals with expected data " + expectedInfo.get(k));
                return false;
            }
        }
        return true;
    }

    public boolean isProductDetailInfoFound(List<List<String>> productDetailInfo) {
        WebElement orderPanel = getWebDriver().findElement(orderSummary);
        List<WebElement> productRows = orderPanel.findElements(By.xpath("./div[@class='product row']"));
        for (WebElement row : productRows) {
            String rowProductName = row.findElement(By.xpath("./div[contains(@class,'productDesc')]//p")).getText();
            String rowProductQty = row.findElement(By.xpath("./div[contains(@class,'productDesc')]//span[starts-with(text(),'QTY')]")).getText();
            String rowProductPrice = row.findElement(By.xpath("./div[contains(@class,'productDesc')]//*[starts-with(text(),'£')]")).getText();
            for (List<String> singleDetailInfo : productDetailInfo) {
                if (singleDetailInfo.contains(rowProductName)) {
                    LOG.debug("Now the order detail information of product name: " + rowProductName + " is found");
                } else if (singleDetailInfo.contains(rowProductQty)) {
                    LOG.debug("Now the order detail information of product quanty: " + rowProductQty + " is found");
                } else if (singleDetailInfo.contains(rowProductPrice)) {
                    LOG.debug("Now the order detail information of product price: " + rowProductPrice + " is found");
                } else {
                    LOG.error("Fail to get the summary information of the products: " + singleDetailInfo);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isHeaderInfoExist() {
        if (!isElementPresent(headerLogo)) {
            LOG.error("The logo is not presented in header information area.");
            return false;
        } else if (!isElementPresent(headerHelpEspot)) {
            LOG.error("The help espot is not displayed.");
            return false;
        } else if (!isElementPresent(deliverySection)) {
            LOG.error("The 1st delivery section is not presented.");
            return false;
        } else if (!isElementPresent(paymentSection)) {
            LOG.error("The 2nd payment section is not presented.");
            return false;
        } else if (!isElementPresent(confirmationSection)) {
            LOG.error("The 3rd confirmation section is not presented.");
            return false;
        } else
            return true;
    }

    public boolean isFooterInfoExist() {
        if (!isElementPresent(footerCheckOutEspot)) {
            LOG.error("The footer check out links espot is not presented ");
            return false;
        } else if (!isElementPresent(providerImages)) {
            LOG.error("The footer provider images are not presented. ");
            return false;
        } else
            return true;
    }

    public void signInWithGuestuser() {
       waitForExpectedElement(guestSignInBtn).click();
    }

    public List<String> getProductsName() {
        List<String> productsName = new ArrayList<String>();
        WebElement orderSummaryPanel = getWebDriver().findElement(orderSummary);
        List<WebElement> productItems = orderSummaryPanel.findElements(By.xpath("//div[@class='product row']//p"));
        for (WebElement item : productItems) {
            productsName.add(item.getText());
        }
        return productsName;
    }

    public boolean isOptionAvailable(String optionName) {
//		WebElement clickCollectOption=getWebDriver().findElement(clickCollectionOption);
//		WebElement deliveryToUKOption=getWebDriver().findElement(deliveryToUKAddressOption);
        String deliveryOptionXpath = "//span/*[contains(text(),'" + optionName + " not available')]";
        if (this.isElementPresent(By.xpath(deliveryOptionXpath))) {
            LOG.debug("The click and collect option is not available. It may not supported for the current product or one of the product list.");
            return false;
        }
        return true;
    }

    public boolean isStoreLodcatorPageDisplayed() {
        if (!isElementPresent(storeLocatorMap)) {
            LOG.error("The store locator map is not presented.");
            return false;
        } else if (!isElementPresent(storeLocatorResult)) {
            LOG.error("The store locator result is not presented.");
            return false;
        } else if (!getWebDriver().getTitle().trim().equals(LloydsPharmacyConstants.STORE_LOCATOR)) {
            LOG.error("Fail to enter store locator page. The title is not the expected.");
            return false;
        } else
            return true;
    }

    public void selectStoreFromLocatorMap(String storeNumber) {
//        WebElement storeMap = waitForExpectedElement(storeLocatorMap);
        if (!isStoreFoundInLocatorMap(storeNumber)) {
            LOG.error("Cannot find the target store number from google map: " + storeNumber);
            return;
        }
        LOG.debug("Store information is found in map. Now select the store: " + storeNumber);
        ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", waitForExpectedElement(selectStoreBtn));
        waitForExpectedElement(selectStoreBtn).click();
//        storeMap.findElement(By.cssSelector(".btn.btnPrimary")).click();
    }
    public void selectStoreFromResultList(String storeNumber) {
        if (!isStoreFoundInResultList(storeNumber)) {
            LOG.error("Cannot find the target store number from result list: " + storeNumber);
            return;
        }
        getWebDriver().findElement(selectStoreBtn).click();
    }
    
    public void selectStoreFromStoreDetailsPage(){
    	this.waitForExpectedElement(selectStoreBtn).click();
    }

    public void clickStoreFromLocatorMap(String storeNumber) {
        WebElement storeMap = getWebDriver().findElement(storeLocatorMap);
        if (!isStoreFoundInLocatorMap(storeNumber)) {
            LOG.error("Cannot find the target store number: " + storeNumber);
            return;
        }
        storeMap.findElement(By.xpath("//div[@class='mapLabel' and text()='" + storeNumber + "' and @title='']")).click();
    }

    private boolean isStoreFoundInLocatorMap(String storeNumber) {
        return isElementPresent(By.xpath("//div[@class='mapLabel' and text()='" + storeNumber + "' and @title='']"));
    }

    private boolean isStoreFoundInResultList(String storeNumber) {
        return isElementPresent(By.xpath("//div[@class='row storeTableContent']//span[text()='" + storeNumber + "']"));
    }

    public boolean isCollectionPointFoundInDeliveryPage() {
        return isElementPresent(collectionBox);
    }

    public boolean isStoreDetailPopupDisplayed() {
        return isElementPresent(storeDetailPopup);
    }

    public void changeCollectionPoint() {
        if (!isElementPresent(collectionBox)) {
            LOG.error("There is no the collection box.");
        }
        getWebDriver().findElement(changeStoreLink).click();
    }

    public List<String> getStoreDetailInfoFromPopup() {
        List<String> storeDetailInfo = new ArrayList<String>();
        if (!isElementPresent(storeDetailPopup)) {
            LOG.debug("Cannot get the store info as the popup is not existed.");
            return null;
        } else {
            WebElement storePopup = getWebDriver().findElement(storeDetailPopup);
            List<WebElement> storeItems = storePopup.findElements(By.xpath("./div//ul/li[not(./a)]"));
            for (WebElement item : storeItems) {
                storeDetailInfo.add(item.getText().replace(",", ""));
            }
            return storeDetailInfo;
        }
    }

    public List<String> getStoreDetailInfoFromStoreList(String storeNumber) {
        List<String> storeDetailInfo = new ArrayList<String>();
        WebElement storeList = getWebDriver().findElement(storeLocatorResult);
        List<WebElement> storeInfoItems = storeList.findElements(By.cssSelector("div.row.storeTableContent div:nth-child(" + storeNumber + ")>div:nth-child(2)>ul>li"));
        for (WebElement item : storeInfoItems) {
            storeDetailInfo.add(item.getText().trim().replace(",", ""));
        }
        return storeDetailInfo;
    }

    public boolean isStorePopupInfoMatch(List<String> storePopupInfo, List<String> storeListInfo) {
        for (String popupItem : storePopupInfo) {
            if (!isItemFoundInList(popupItem, storeListInfo)) {
                LOG.error("The store item in popup: " + popupItem + "is not found the matching item in list.");
                return false;
            }
        }
        return true;
    }

    private boolean isItemFoundInList(String item, List<String> listToSearch) {
        for (String s : listToSearch) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void clickOnViewStoreDetails(String storeNumber) {
        WebElement storeList = getWebDriver().findElement(storeLocatorResult);
        storeList.findElement(By.cssSelector("div.row.storeTableContent div:nth-child(" + storeNumber + ")>div:nth-child(2)>ul>li>a")).click();
    }

    public void searchStoreInStoreLocator(String searchText) {
        WebElement storeResultPanel = getWebDriver().findElement(storeResults);
        WebElement storeInputField = storeResultPanel.findElement(By.cssSelector("input#postCodeInput"));
        storeInputField.clear();
        storeInputField.sendKeys(searchText);
        storeResultPanel.findElement(By.cssSelector("input[value='Find Collection Point']")).click();
        waitForExpectedElement(storeResults);
    }

    public boolean isStoreDetailsPageDisplayed() {
        if (!isElementPresent(storeDetailGoogleMap)) {
            LOG.error("Fail to display google map in store details page.");
            return false;
        } else if (!isElementPresent(storeDetailsRow)) {
            LOG.error("Fail to display store details info in store details page. ");
            return false;
        }
        return true;
    }
    
    public boolean isStoreListerInfomationDisplayed(){
    	if(!isElementPresent(storeLocatorMap)){
    		LOG.error("Fail to display store locator map in Store locator page.");
    		return false;
    	}
    	if(!isResultFormInCorrectFormat()){
    		LOG.error("The result form in store locator is not in correct format.");
    		return false;
    	}
    	return true;
    }
    
    private boolean isResultFormInCorrectFormat(){
    	List<WebElement> resultColumns=visibilityOfAllElementsLocatedBy(locatorListerColumns);
    	for(WebElement cl: resultColumns){
    	  if(cl.getText().trim().equals(this.getB2cProp("storeLocator.collectionPoint"))){
              continue; 
    	  }
    	  else if(cl.getText().trim().equals(this.getB2cProp("storeLocator.distance"))){
    		  continue;
    	  }
    	  else if(cl.getText().trim().equals(this.getB2cProp("storeLocator.openingTimes"))){
    		  continue;
    	  }
    	  else{
    		  LOG.error("Cannot find the colum with name as "+cl.getText().trim());
    		  return false;
    	  }
    	}
    	return true;
    }
    
    
    
    
}

