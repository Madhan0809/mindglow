package com.salmon.test.runners.b2c;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@b2c_failed", "~@b2c_blocked", "~@b2c_wip"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/run1b2cfailjenkins",
        "json:target/cucumber-report/run1b2cfailjenkins/cucumber.json",
        "rerun:target/cucumber-report/run1b2cfailjenkins/rerun.txt"},
        glue = "com.salmon.test")
public class Run1B2CFailJenkinsSuite extends AbstractTestNGCucumberTests {
}
