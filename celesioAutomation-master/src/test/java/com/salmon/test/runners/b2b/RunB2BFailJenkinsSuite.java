package com.salmon.test.runners.b2b;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@b2b_failed", "~@b2b_blocked", "~@b2b_wip"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/runb2bfailjenkins",
        "json:target/cucumber-report/runb2bfailjenkins/cucumber.json",
        "rerun:target/cucumber-report/runb2bfailjenkins/rerun.txt"},
        glue = "com.salmon.test")
public class RunB2BFailJenkinsSuite extends AbstractTestNGCucumberTests {
}
