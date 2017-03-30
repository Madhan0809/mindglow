package com.salmon.test.runners.b2b;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = { "@b2b, @autouser1", "~@b2b_register", "~@b2b_site_selection",
        "~@b2b_forgotPassword", "~@b2b_forgotUsername", "~@b2b_passwordExpired", "~@b2b_pdp", "~@b2b_plp_listView",
        "~@b2b_search", "~@b2b_department", "~@b2b_login", "~@b2b_quickOrder",
        "~@b2b_MyBasketPage", "~@b2b_favourites_list_details", "~@b2b_orderconfirmation", "~@b2b_FavouritesListOverlay", "~@b2b_orderReply",
        "~@autouser2", "~@b2b_blocked", "~@b2b_wip",
        "~@b2b_failed", "~@devsmoketestplp", "~@systest_only" }, monochrome = true, plugin = { "pretty",
                "html:target/cucumber-report/run1b2b_dbuild_jenkins",
                "json:target/cucumber-report/run1b2b_dbuild_jenkins/cucumber.json",
                "rerun:target/cucumber-report/run1b2b_dbuild_jenkins/rerun.txt" }, glue = "com.salmon.test")
public class Run1B2BDBuildJenkinsSuite extends AbstractTestNGCucumberTests {
}

