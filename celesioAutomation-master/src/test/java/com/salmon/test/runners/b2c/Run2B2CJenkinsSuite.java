package com.salmon.test.runners.b2c;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {
		"@b2c_paypal, @b2c_register, @b2c_pmed1, @b2c_pmed2, @b2c_e2e_CandC, @b2c_e2e_deliverToUk, @b2c_clickAndcollect, @b2c_registeredUserDelivery",
		"~@b2c_blocked", "~@b2c_wip", "~@b2c_failed" }, monochrome = true, plugin = { "pretty",
				"html:target/cucumber-report/run4b2cjenkins",
				"json:target/cucumber-report/run4b2cjenkins/cucumber.json",
				"rerun:target/cucumber-report/run4b2cjenkins/rerun.txt" }, glue = "com.salmon.test")
public class Run2B2CJenkinsSuite extends AbstractTestNGCucumberTests {
}
