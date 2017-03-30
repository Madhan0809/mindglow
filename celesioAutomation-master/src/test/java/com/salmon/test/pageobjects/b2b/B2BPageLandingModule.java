package com.salmon.test.pageobjects.b2b;

import com.salmon.test.framework.PageObject;
import com.salmon.test.framework.helpers.UrlBuilder;
import com.salmon.test.pageobjects.b2b.checkout.B2BCheckOutBasketPage;
import com.salmon.test.pageobjects.b2b.department.B2BDepartmentPage;
import com.salmon.test.pageobjects.b2b.home.B2BHomePageFooter;
import com.salmon.test.pageobjects.b2b.megamenu.B2BMegaMenu;
import com.salmon.test.pageobjects.b2b.register.B2BLoginPage;
import com.salmon.test.stepdefinitions.b2b.checkout.B2BCheckOutBasketSteps;
import com.salmon.test.stepdefinitions.b2b.department.B2BDepartmentSteps;
import com.salmon.test.stepdefinitions.b2b.homepage.B2BHomePageFooterSteps;
import com.salmon.test.stepdefinitions.b2b.megamenu.B2BMegaMenuSteps;
import com.salmon.test.stepdefinitions.b2b.pdp.B2BProductDetailPageSteps;
import com.salmon.test.stepdefinitions.b2b.register.B2BLoginSteps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class B2BPageLandingModule extends PageObject {
    private static final Logger LOG = LoggerFactory.getLogger(B2BLoginPage.class);

    private B2BLoginPage b2bLogin; 
//    private B2BHomePageFooterSteps b2bHomePageFooterStep;
    private B2BHomePageFooter b2bHomePageFooter;
//    private B2BDepartmentSteps b2bDepartmentStep;
    private B2BDepartmentPage b2bDepartment;
    private B2BMegaMenu b2bMegaMenu;
    private B2BCheckOutBasketPage b2bCheckOutBasket;
    
    public  B2BPageLandingModule(B2BHomePageFooter homeFooter,B2BLoginPage login,
    		B2BDepartmentPage department,B2BMegaMenu megaMenu,B2BCheckOutBasketPage CheckOutBasket){
    	b2bHomePageFooter=homeFooter;
    	b2bLogin = login;
    	b2bDepartment=department;
    	b2bMegaMenu=megaMenu;
    	b2bCheckOutBasket=CheckOutBasket;
    }
    
    
    
    public void goToTargetLandingPageFromAAHLogin(String pageNameToLand) throws Throwable{
        if (pageNameToLand.equalsIgnoreCase(getB2bProp("landing.loginHomePageName"))) {
            enterLoginLandingPage();
        } else if (pageNameToLand.equalsIgnoreCase(getB2bProp("landing.departmentPage"))) {
            enterDepartmentLandingPage();
        } else if (pageNameToLand.equalsIgnoreCase(getB2bProp("landing.pdp"))) {
            enterPDPLandingPage();
        } else if (pageNameToLand.equalsIgnoreCase(getB2bProp("landing.checkoutbasket"))){
        	enterCheckOutBasketPage();
        }
        else
        {
            //TODO: To be completed with all procedures in AAH b2b page
            try {
                throw new Exception("The landing type is not added or it's not supported in current module: " + pageNameToLand);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void enterLoginLandingPage() throws Throwable{
        LOG.info("Enter land the page to the homepage of AAH after sign in.");
//        b2bHomePageFooterStep.The_user_is_on_AAH_home_page();
        b2bHomePageFooter.goToHomePage();
        b2bLogin.loginWithDefaultUser();
        b2bLogin.dismissAllNotification();
    }

    private void enterDepartmentLandingPage() throws Throwable{
        LOG.info("Enter land the page to the default departmentPage.");
        goToTargetLandingPageFromAAHLogin(getB2bProp("landing.loginHomePageName"));
        b2bMegaMenu.onHoverOfMainMenu(getB2bProp("departmentPage.defaultMainMenu"));
        b2bDepartment.onClickOfTargetDepartment(getB2bProp("departmentPage.defaultDepartment"));
    }
    

	private void enterPDPLandingPage() throws Throwable{
        LOG.info("Enter land the page to the default PDP.");
        goToTargetLandingPageFromAAHLogin(getB2bProp("landing.departmentPage"));
        b2bDepartment.clickOnProductFromBestSellers(getB2bProp("landing.departmentPage"));
   }
	
	private void enterCheckOutBasketPage() throws Throwable{
		LOG.info("Enter checkout basket page from global header with default basket.");
		enterLoginLandingPage();	
		b2bCheckOutBasket.enterDefaultAAHBasketOrderFromHeader();
	}

}
