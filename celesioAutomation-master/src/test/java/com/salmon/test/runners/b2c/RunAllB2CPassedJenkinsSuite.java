package com.salmon.test.runners.b2c;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "target/test-classes", tags = {"@b2c","~@b2c_blocked","~@b2c_wip", "~@b2c_failed"}, 
monochrome = true, plugin = { "pretty","html:target/cucumber-report/runallb2cpassedjenkins",
                "json:target/cucumber-report/runallb2cpassedjenkins/cucumber.json",
                "rerun:target/cucumber-report/runallb2cpassedjenkins/rerun.txt" }, glue = "com.salmon.test")
public class RunAllB2CPassedJenkinsSuite extends AbstractTestNGCucumberTests{

}
