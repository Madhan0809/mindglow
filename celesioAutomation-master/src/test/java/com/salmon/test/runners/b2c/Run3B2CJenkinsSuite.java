package com.salmon.test.runners.b2c;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@b2c_e2e_prescriptions, @b2c_deliveryToUk , @b2c_storeLocator , @b2c_search , @b2c_plp", "~@b2c_blocked", "~@b2c_wip", "~@b2c_failed"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/run3b2cjenkins",
        "json:target/cucumber-report/run3b2cjenkins/cucumber.json",
        "rerun:target/cucumber-report/run3b2cjenkins/rerun.txt"},
        glue = "com.salmon.test")
public class Run3B2CJenkinsSuite extends AbstractTestNGCucumberTests {
}
